package org.test.microservice.api.dto.mapper;

import org.mapstruct.Mapper;
import org.test.microservice.api.dto.MessageDto;
import org.test.microservice.usecase.model.Message;

@Mapper
public interface MessageDtoMapper {
    MessageDto map(Message message);
}
