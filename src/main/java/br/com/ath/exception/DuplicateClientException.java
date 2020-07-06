package br.com.ath.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class DuplicateClientException extends Exception {

    public DuplicateClientException() {
        super();
    }


    public DuplicateClientException(String message) {
        super(message);
    }
}
