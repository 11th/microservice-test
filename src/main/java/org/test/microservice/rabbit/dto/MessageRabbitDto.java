package org.test.microservice.rabbit.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MessageRabbitDto {

  @NotNull
  private Long id;
  @Nullable
  private String from;
  @NotNull
  private String to;
  @NotNull
  private String text;
  @NotNull
  private Integer type;
}
