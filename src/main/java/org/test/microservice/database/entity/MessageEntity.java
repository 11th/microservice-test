package org.test.microservice.database.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.test.microservice.en.MessageType;

@Entity
@Getter
@Setter
@Table(name = "message")
public class MessageEntity {
    @Id
    private long id;
    @Column(name = "sender")
    private String from;
    @Column(name = "receiver")
    private String to;
    private String text;
    @Enumerated(EnumType.STRING)
    private MessageType type;
}
