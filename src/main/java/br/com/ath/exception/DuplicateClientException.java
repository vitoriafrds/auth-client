package br.com.ath.exception;

public class DuplicateClientException extends Exception{
    
    public DuplicateClientException(String message) {
        super(message);
    }
}
