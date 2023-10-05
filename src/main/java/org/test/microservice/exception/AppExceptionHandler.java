package org.test.microservice.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Log4j2
public class AppExceptionHandler {
//    @ExceptionHandler(RabbitConsumerException.class)
//    public void handlerRabbitConsumerException(RuntimeException exception) {
//        log.error(exception.getMessage());
//    }

//    @ExceptionHandler(IllegalArgumentException.class)
//    public ResponseEntity<Object> handlerIllegalArgumentException(RuntimeException e) {
//        log.error(e.getMessage());
//        return new ResponseEntity<>(e.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
//    }
}
