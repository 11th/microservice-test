package org.test.microservice.api.presenter;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.test.microservice.en.MessageType;
import org.test.microservice.api.dto.MessageDto;
import org.test.microservice.api.dto.mapper.MessageDtoMapper;
import org.test.microservice.usecase.GetMessageUseCase;

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
    public Page<MessageDto> getAll(Pageable pageable) {
        return getMessageUseCase.getAll(pageable).map(messageDtoMapper::map);
    }

    @Override
    @NotNull
    public MessageDto getById(long id) {
        return messageDtoMapper.map(getMessageUseCase.getById(id));
    }

    @Override
    @NotNull
    public List<MessageDto> getByType(@NotNull MessageType type) {
        return getMessageUseCase.getByType(type).stream()
                .map(messageDtoMapper::map)
                .collect(Collectors.toList());
    }
}
