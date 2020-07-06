package br.com.ath.exception.handler;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.tomcat.jni.Local;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
public class ErrorApiDetail {

    private HttpStatus status;
    private String detail;
    private LocalDate timestamp;


    public ErrorApiDetail(HttpStatus status, String detail, LocalDate timestamp) {
        this.timestamp = timestamp;
        this.status = status;
        this.detail = detail;
    }
}
