package org.test.microservice.schedule;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.test.library.CalculateService;
import org.test.microservice.database.entity.CustomMessageEntity;
import org.test.microservice.en.MessageType;
import org.test.microservice.service.MessageService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@Log4j2
@RequiredArgsConstructor
public class MessageStatisticScheduler {
    private final MessageService messageService;
    private final CalculateService calculateService;

    @Value("${message.statistic.file-path}")
    private String filePath;

    @Scheduled(cron = "${message.statistic.schedule-cron}")
    public void execute() throws IOException {
        List<CustomMessageEntity> statistic = getMessageStatistic();
        calculateCounts(statistic);
        uploadToJsonFile(statistic);
    }

    private List<CustomMessageEntity> getMessageStatistic() {
        List<CustomMessageEntity> statistic = new ArrayList<>();
        Arrays.stream(MessageType.values()).forEach(messageType -> {
            statistic.add(new CustomMessageEntity(messageType, (long) messageService.getByType(messageType).size()));
        });
        return statistic;
    }

    private void calculateCounts(List<CustomMessageEntity> statistic) {
        long totalCount = statistic.stream()
                .map(CustomMessageEntity::getCount)
                .mapToLong(Long::valueOf)
                .sum();
        long smsCount = statistic.stream()
                .filter(s -> s.getType() == MessageType.SMS)
                .map(CustomMessageEntity::getCount)
                .mapToLong(Long::valueOf)
                .sum();
        int noSmsCount = Math.abs(calculateService.calculateWithoutSms((int) totalCount, (int) smsCount));
        log.info("Statistic: count messages except sms - {}", noSmsCount);
    }

    private void uploadToJsonFile(List<CustomMessageEntity> statistic) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String jsonContent = mapper.writeValueAsString(statistic);
        Files.createDirectories(Path.of(filePath).getParent());
        Files.deleteIfExists(Path.of(filePath));
        Files.writeString(Path.of(filePath), jsonContent);
        log.info("Statistic: json file uploaded");
    }
}