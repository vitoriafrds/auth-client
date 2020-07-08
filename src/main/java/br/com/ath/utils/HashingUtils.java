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

    public byte[] hashPassword(String password) {

        byte[] digest = null;

        try {
            MessageDigest messageDigest = MessageDigest.getInstance(SHA_256);
            digest = messageDigest.digest(password.getBytes(UTF_8));

        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            log.error("error in generate hash {}", e.getMessage());
        }

        return digest;
    }

    public String convertToHexadecimal(byte[] hash) {
        StringBuilder hexadecimal = new StringBuilder();
        for (byte b : hash) {
            hexadecimal.append(String.format("%02X", 0xFF & b));
        }
        return hexadecimal.toString();
    }
}
