package org.test.microservice.api.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.test.microservice.api.dto.MessageDto;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@TestPropertySource("/application-test.properties")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MessageControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    TestRestTemplate restTemplate;

    String baseUrl = "http://localhost:";

    @BeforeEach
    void setup() {
        baseUrl = baseUrl + port;
        jdbcTemplate.execute(
                "INSERT INTO message(id, sender, receiver, text, type) VALUES(1, 'sender', 'receiver', 'text', 'SMS')"
        );
    }

    @AfterEach
    void clearData() {
        jdbcTemplate.execute("DELETE FROM message");
    }

    @Test
    @DisplayName("Get all messages -> Ok")
    void getAll_Ok() {
        //When
        ResponseEntity<CustomPageImpl<MessageDto>> responseEntity = restTemplate.exchange(
                (baseUrl + "/v1/message"),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<CustomPageImpl<MessageDto>>() {
                }
        );
        //Then
        assertAll(
                () -> assertEquals(responseEntity.getStatusCode(), HttpStatus.OK),
                () -> assertNotNull(responseEntity.getBody()),
                () -> assertNotNull(Objects.requireNonNull(responseEntity.getBody()).getContent()),
                () -> assertEquals(Objects.requireNonNull(responseEntity.getBody()).getContent().size(), 1)
        );
    }

    @Test
    @DisplayName("Get message by id -> Ok")
    void getById_Ok() {
        //When
        ResponseEntity<MessageDto> responseEntity = restTemplate.getForEntity(
                (baseUrl + "/v1/message/1"),
                MessageDto.class
        );
        //Then
        assertAll(
                () -> assertEquals(responseEntity.getStatusCode(), HttpStatus.OK),
                () -> assertNotNull(responseEntity.getBody()),
                () -> assertEquals(Objects.requireNonNull(responseEntity.getBody()).getId(), 1)
        );
    }

    @Test
    @DisplayName("Get message by id -> Not found")
    void getById_NotFound() {
        //When
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(
                (baseUrl + "/v1/message/2"),
                String.class
        );
        //Then
        assertAll(
                () -> assertEquals(responseEntity.getStatusCode(), HttpStatus.NOT_FOUND)
        );
    }

    @Test
    @DisplayName("Get messages by type -> Ok")
    void getByType_Ok() {
        //When
        ResponseEntity<List<MessageDto>> responseEntity = restTemplate.exchange(
                (baseUrl + "/v1/message?type=SMS"),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<MessageDto>>() {
                }
        );
        //Then
        assertAll(
                () -> assertEquals(responseEntity.getStatusCode(), HttpStatus.OK),
                () -> assertNotNull(responseEntity.getBody()),
                () -> assertEquals(Objects.requireNonNull(responseEntity.getBody()).get(0).getType().name(), "SMS")
        );
    }
}