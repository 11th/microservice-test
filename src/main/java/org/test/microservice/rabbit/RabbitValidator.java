package org.test.microservice.rabbit;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.ValidatorFactory;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.test.microservice.rabbit.dto.MessageRabbitDto;

import java.util.List;
import java.util.Set;

@Log4j2
public class RabbitValidator implements Validator {
    @Override
    public boolean supports(@NotNull Class<?> clazz) {
        return true;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void validate(@NotNull Object target, @NotNull Errors errors) {
        //todo: need to improve somehow
        List<MessageRabbitDto> messages = (List<MessageRabbitDto>) target;
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        jakarta.validation.Validator validator = factory.getValidator();
        messages.forEach(message -> {
            Set<ConstraintViolation<Object>> violations = validator.validate(message);
            violations.forEach(violation -> {
                String violationPropertyPath = violation.getPropertyPath().toString();
                String violationMessage = violation.getMessage();
                log.error("Error: " + violationPropertyPath + " " + violationMessage);
                errors.rejectValue(violationPropertyPath, "", violationMessage);
            });
        });
    }
}
