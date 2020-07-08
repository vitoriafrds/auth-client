package br.com.ath.exception.handler;

import br.com.ath.exception.UserAlreadyExistsException;
import br.com.ath.exception.NotAuthenticatedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDate;

@RestControllerAdvice
public class HandlerController {

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorApiDetail> handlerDuplicateClientExcpetion(UserAlreadyExistsException duplicateClientException) {

        ErrorApiDetail error = ErrorApiDetail.builder().timestamp(LocalDate.now())
                .detail(duplicateClientException.getMessage())
                .status(HttpStatus.BAD_REQUEST)
                .build();

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotAuthenticatedException.class)
    public ResponseEntity<ErrorApiDetail> handlerNotAuthenticationExcpetion(NotAuthenticatedException notAuthenticatedException) {

        ErrorApiDetail error = ErrorApiDetail.builder().timestamp(LocalDate.now())
                .detail(notAuthenticatedException.getMessage())
                .status(HttpStatus.BAD_REQUEST)
                .build();

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
