package br.com.ath.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.JdkIdGenerator;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
@Slf4j
public class HashingUtils {

    private static final String SHA_256 = "SHA-256";
    private static final String UTF_8 = "UTF-8";

    public String hashPassword(String password) {

        byte[] digest = null;
        String convertedPassword = "";

        try {
            MessageDigest messageDigest = MessageDigest.getInstance(SHA_256);
            digest = messageDigest.digest(password.getBytes(UTF_8));

            StringBuilder hexadecimal = new StringBuilder();
            for (byte b : digest) {
                hexadecimal.append(String.format("%02X", 0xFF & b));
            }
            convertedPassword = hexadecimal.toString();

        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            log.error("error in generate hash {}", e.getMessage());
        }

        return convertedPassword;
    }
}
