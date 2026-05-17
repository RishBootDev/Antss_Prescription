package com.antss_prescription.dto.response;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private Long id;
    private String fullName;
    private String clinicName;
    private String email;
    private String mobile;
    private String address;
    private String city;
    private String state;
    private String country;
    private String status;
    private String role;
    private Integer numDoctors;
    private String packageName;
    private Integer packageDoctorLimit;
    private Integer packageDeviceLimit;
    private LocalDate subscriptionStart;
    private LocalDate subscriptionEnd;
}
