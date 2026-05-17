package com.antss_prescription.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class RegisterRequest {

    @NotBlank
    private String fullName;
    @NotBlank
    private String clinicName;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String mobile;

    private String address;
    private String city;
    private String state;
    private String country;

    @NotNull
    private Long packageId;
    @NotNull
    @Min(1)
    private Integer numDoctors;
    @NotBlank
    @Size(min = 8)
    private String password;

    @NotBlank
    private String confirmPassword;
}
