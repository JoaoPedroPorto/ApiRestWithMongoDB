package com.apirestwithmongodb.util;

import java.security.SecureRandom;
import java.util.Random;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class ApplicationUtil {

    private static Random RANDOM = new SecureRandom();
    private static String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static int PASSWORDLENGHT = 15;

    public static  String generatePassword() {
        StringBuilder password = new StringBuilder(PASSWORDLENGHT);
        for (int i = 0; i < PASSWORDLENGHT; i++) {
            password.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
        }
        return new BCryptPasswordEncoder().encode(password);
    }

    public static boolean verifyPasswordValid(String password, String encryptedPassword) {
        return BCrypt.checkpw(password, encryptedPassword);
    }

}
