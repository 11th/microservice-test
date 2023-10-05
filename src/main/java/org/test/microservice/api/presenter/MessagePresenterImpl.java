package org.test.microservice.api.presenter;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.test.microservice.en.MessageType;
import org.test.microservice.api.dto.MessageDto;
import org.test.microservice.api.dto.mapper.MessageDtoMapper;
import org.test.microservice.usecase.GetMessageUseCase;
import org.test.microservice.usecase.model.Message;

import java.util.*;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Log4j2
public class MessagePresenterImpl implements MessagePresenter {
    private final GetMessageUseCase getMessageUseCase;
    private final MessageDtoMapper messageDtoMapper;

    @Override
    @NotNull
    public List<MessageDto> getAll() {
        return getMessageUseCase.getAll().stream()
                .map(messageDtoMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    @NotNull
    public MessageDto getById(long id) {
        return messageDtoMapper.map(getMessageUseCase.getById(id));
    }

    @Override
    @NotNull
    public List<MessageDto> getByType(@NotNull MessageType type) {
        List<MessageDto> messageDtoList = new ArrayList<>();
        messageDtoList = getMessageUseCase.getByType(type).stream()
                .map(messageDtoMapper::map)
                .collect(Collectors.toList());
        log.debug("Message count {}", messageDtoList);
        return messageDtoList;
    }
}
