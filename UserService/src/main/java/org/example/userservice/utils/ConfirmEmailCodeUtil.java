package org.example.userservice.utils;

import java.util.Random;

public class ConfirmEmailCodeUtil {
    private ConfirmEmailCodeUtil() {}

    public static String generateCode() {
        String chars = "ABCDEFGHIJKLMNOPRSTUVWXYZ0123456789";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            int index = random.nextInt(chars.length());
            sb.append(chars.charAt(index));
        }
        return sb.toString();
    }
}
