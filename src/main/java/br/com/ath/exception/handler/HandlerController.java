package br.com.ath.exception.handler;

import br.com.ath.exception.DuplicateClientException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDate;

@RestControllerAdvice
public class HandlerController {

    @ExceptionHandler(DuplicateClientException.class)
    public ResponseEntity<ErrorApiDetail> handlerDuplicateClientExcpetion(DuplicateClientException duplicateClientException) {

        ErrorApiDetail error = ErrorApiDetail.builder().timestamp(LocalDate.now())
                .detail(duplicateClientException.getMessage())
                .status(HttpStatus.BAD_REQUEST)
                .title("login already registered")
                .build();

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
