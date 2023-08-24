package ru.chemakin.TemperatureSensor.util;

import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

public class ErrorHandlingUtils {
    @SneakyThrows
    public static ResponseEntity<HttpStatus> formatErrorMessageFromFieldErrors(BindingResult bindingResult,
                                                                               Class<? extends RuntimeException> exceptionClass) {
        StringBuilder errorMessage = new StringBuilder();
        List<FieldError> errors = bindingResult.getFieldErrors();
        for (FieldError error : errors) {
            errorMessage.append(error.getField())
                    .append(" - ")
                    .append(error.getDefaultMessage())
                    .append("; ");
        }
        throw exceptionClass.getConstructor(String.class).newInstance(errorMessage.toString());
    }

}
