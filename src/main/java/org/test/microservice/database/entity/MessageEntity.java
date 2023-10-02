package org.test.microservice.database.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.test.microservice.en.MessageType;

@Table(name = "message")
@Entity
@Getter
public class MessageEntity {

  @Id
  private long id;
  private String from;
  private String to;
  private String text;
  @Enumerated(EnumType.STRING)
  private MessageType type;
}
