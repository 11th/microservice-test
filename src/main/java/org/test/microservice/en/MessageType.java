package org.test.microservice.en;

public enum MessageType {

  SMS(10),
  EMAIL(20),
  TELEGRAM(30);

  private final int id;

  MessageType(int id) {this.id = id;}

  public int getId() {
    return id;
  }
}
