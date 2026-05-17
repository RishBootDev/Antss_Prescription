package com.antss_prescription.configuration;

import com.antss_prescription.entity.User;
import com.antss_prescription.enums.RegistrationStatus;
import com.antss_prescription.repository.LoginSessionRepository;
import com.antss_prescription.repository.UserRepository;
import com.antss_prescription.service.EmailService;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Configuration
@EnableScheduling
public class SchedulerConfig {


    private final UserRepository userRepository;
    private final LoginSessionRepository loginSessionRepository;
    private final EmailService emailService;

    public SchedulerConfig(UserRepository userRepository,
                           LoginSessionRepository loginSessionRepository,
                           EmailService emailService) {
        this.userRepository = userRepository;
        this.loginSessionRepository = loginSessionRepository;
        this.emailService = emailService;
    }

    @Scheduled(cron = "0 0 0 * * ?") // Runs daily at midnight
    @Transactional
    public void expireSubscriptions() {
        log.info("Running daily subscription expiry check...");
        LocalDate today = LocalDate.now();

        List<User> expiringUsers = userRepository.findByStatusAndSubscriptionEndBefore(RegistrationStatus.APPROVED, today);

        for (User user : expiringUsers) {
            user.setStatus(RegistrationStatus.EXPIRED);
            userRepository.save(user);

            // Invalidate all active sessions
            loginSessionRepository.expireAllSessionsForUser(user);

            // Send expiry email
            emailService.sendExpiryReminderEmail(user.getEmail(), user.getFullName());

            log.info("User subscription expired: {}", user.getEmail());
        }

        log.info("Completed subscription expiry check. {} accounts expired.", expiringUsers.size());
    }
}
