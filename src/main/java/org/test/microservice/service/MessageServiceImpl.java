package org.test.microservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.test.microservice.database.entity.MessageEntity;
import org.test.microservice.database.repository.MessageRepository;
import org.test.microservice.en.MessageType;
import org.test.microservice.exception.MessageNotFoundException;
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
    public Page<Message> getAll(Pageable pageable) {
        return messageRepository.findAll(pageable).map(messageMapper::mapFromEntity);
    }

    @Override
    public Message getById(long id) {
        MessageEntity messageEntity = messageRepository.findById(id)
                .orElseThrow(() -> new MessageNotFoundException("Message not found"));
        return messageMapper.mapFromEntity(messageEntity);
    }

    @Override
    @Cacheable("messagesByType")
    public List<Message> getByType(MessageType type) {
        List<Message> messages = messageRepository.findByType(type).stream()
                .map(messageMapper::mapFromEntity)
                .collect(Collectors.toList());
        log.debug("Message count {}", messages.size());
        return messages;
    }

    @Override
    public void save(Message message) {
        messageRepository.save(messageMapper.mapToEntity(message));
    }
}
