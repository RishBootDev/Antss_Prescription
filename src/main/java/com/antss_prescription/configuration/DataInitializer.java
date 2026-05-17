package com.antss_prescription.configuration;

import com.antss_prescription.entity.User;
import com.antss_prescription.enums.RegistrationStatus;
import com.antss_prescription.enums.Role;
import com.antss_prescription.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class DataInitializer implements CommandLineRunner {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${app.admin.email}")
    private String adminEmail;

    @Value("${app.admin.default-password}")
    private String adminDefaultPassword;

    public DataInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        if (!userRepository.existsByEmail(adminEmail)) {
            User admin = new User();
            admin.setFullName("System Admin");
            admin.setClinicName("Admin HQ");
            admin.setEmail(adminEmail);
            admin.setMobile("0000000000");
            admin.setPassword(passwordEncoder.encode(adminDefaultPassword));
            admin.setStatus(RegistrationStatus.APPROVED);
            admin.setRole(Role.ROLE_ADMIN);
            admin.setNumDoctors(0);

            userRepository.save(admin);
            log.info("Default admin user created with email: {}", adminEmail);
        }
    }
}
