package org.test.microservice.rabbit.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.test.microservice.en.MessageType;
import org.test.microservice.rabbit.dto.MessageRabbitDto;
import org.test.microservice.usecase.model.Message;

import java.util.List;

@Mapper
public interface MessageRabbitDtoMapper {
    @Mapping(target = "type", expression = "java(mapMessageType(messageRabbitDto.getType()))")
    Message map(MessageRabbitDto messageRabbitDto);

    List<Message> mapToList(List<MessageRabbitDto> messageRabbitDtoList);

    default MessageType mapMessageType(int type) {
        return MessageType.fromId(type);
    }
}
