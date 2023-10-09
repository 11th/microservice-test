package org.test.microservice.database.repository;

import org.test.microservice.en.MessageType;

public interface TypeStatistic {
    MessageType getType();

    Long getCount();
}
