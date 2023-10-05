package org.test.microservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.test.microservice.database.entity.MessageEntity;
import org.test.microservice.database.repository.MessageRepository;
import org.test.microservice.en.MessageType;
import org.test.microservice.usecase.model.mapper.MessageMapper;
import org.test.microservice.usecase.model.Message;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;
    private final MessageMapper messageMapper;

    @Override
    public List<Message> getAll() {
        return messageRepository.findAll().stream()
                .map(messageMapper::mapFromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public Message getById(long id) {
        MessageEntity messageEntity = messageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Message not found"));
        return messageMapper.mapFromEntity(messageEntity);
    }

    @Override
    public List<Message> getByType(MessageType type) {
        return messageRepository.findByType(type).stream()
                .map(messageMapper::mapFromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void save(Message message) {
        MessageEntity messageEntity = messageMapper.mapToEntity(message);
        messageRepository.save(messageEntity);
    }
}
