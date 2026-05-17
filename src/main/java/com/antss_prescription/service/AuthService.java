package com.antss_prescription.service;

import com.antss_prescription.dto.request.*;
import com.antss_prescription.dto.response.AuthResponse;

public interface AuthService {
    void register(RegisterRequest request);
    AuthResponse login(LoginRequest request);
    void logout(String token);
    void forgotPassword(ForgotPasswordRequest request);
    void resetPassword(ResetPasswordRequest request);
    AuthResponse refreshToken(RefreshTokenRequest request);
}
