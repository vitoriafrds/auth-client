package br.com.ath.entities.dto;

import br.com.ath.controller.AuthenticationController;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class AuthClientResponseDTO {

    private LocalDate timestamp;
    private String message;

    public AuthClientResponseDTO(LocalDate timestamp, String message) {
        this.timestamp = timestamp;
        this.message = message;
    }
}
