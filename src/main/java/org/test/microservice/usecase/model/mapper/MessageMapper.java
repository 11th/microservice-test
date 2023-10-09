package org.test.microservice.usecase.model.mapper;

import org.mapstruct.Mapper;
import org.test.microservice.database.entity.MessageEntity;
import org.test.microservice.usecase.model.Message;

@Mapper
public interface MessageMapper {
    MessageEntity mapToEntity(Message message);

    Message mapFromEntity(MessageEntity messageEntity);
}
