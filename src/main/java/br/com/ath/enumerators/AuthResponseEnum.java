package br.com.ath.enumerators;

import lombok.Getter;

@Getter
public enum AuthResponseEnum {

    USER_AUTHENTICATED("USER AUTHENTICATED"),
    USER_NOT_AUTHENTICATED("FAILED TO AUTHENTICATE"),
    USER_ALREADY_EXISTS("USER ALREADY REGISTERED");

    private String message;

    AuthResponseEnum(String message) {
        this.message = message;
    }
}
