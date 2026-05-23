package com.antss_prescription.security;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class ApprovalTokenUtils {

    public static String generateToken(String userId, String adminEmail, String jwtSecret) {
        try {
            String input = userId + "|" + adminEmail + "|" + jwtSecret;
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate approval token", e);
        }
    }

    public static boolean verifyToken(String userId, String adminEmail, String jwtSecret, String token) {
        if (token == null) {
            return false;
        }
        String expected = generateToken(userId, adminEmail, jwtSecret);
        return expected.equalsIgnoreCase(token);
    }
}
