package com.example.consumirApi.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;
import java.util.Map;

import static com.example.consumirApi.exceptions.Constants.NAME_NOT_FOUND;
import static com.example.consumirApi.exceptions.Constants.RESPONSE_ERROR_MESSAGE_KEY;


@ControllerAdvice
@Slf4j
public class ControllerAdvisorExc {

    @ExceptionHandler(NameNotFoundException.class)
    public ResponseEntity<Map<String, String>> nameNotFoundException(
            NameNotFoundException nameNotFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(RESPONSE_ERROR_MESSAGE_KEY, NAME_NOT_FOUND));
    }


    @ExceptionHandler(ErrorResponseException.class)
    public ProblemDetail handleErrorResponseException(ErrorResponseException ex) {
        return ex.getBody();
    }

    @ExceptionHandler(RuntimeException.class)
    public ProblemDetail handleRuntimeException(RuntimeException ex) {
        log.error(ex.getMessage(),ex);
        return ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
