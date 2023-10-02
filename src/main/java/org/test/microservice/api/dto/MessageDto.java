package org.test.microservice.api.dto;

import lombok.Data;
import org.test.microservice.en.MessageType;

@Data
public class MessageDto {

  private long id;
  private String from;
  private String to;
  private String text;
  private MessageType type;
}
