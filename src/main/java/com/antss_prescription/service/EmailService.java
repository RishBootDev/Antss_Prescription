package com.antss_prescription.service;

public interface EmailService {
    void sendRegistrationNotificationToAdmin(String adminEmail, String userFullName,
                                             String clinicName, String email,
                                             String packageName, int numDoctors);
    void sendApprovalEmail(String toEmail, String fullName);
    void sendRejectionEmail(String toEmail, String fullName);
    void sendExpiryReminderEmail(String toEmail, String fullName);
    void sendPasswordResetEmail(String toEmail, String fullName, String resetToken);
}
