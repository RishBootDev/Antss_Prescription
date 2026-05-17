package com.antss_prescription.dto.request;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class CreatePackageRequest {

    @NotBlank
    private String name;
    @NotNull
    @Min(1)
    private Integer validityDays;
    @NotNull
    @Min(1)
    private Integer doctorLimit;
    @NotNull
    @Min(1)
    private Integer deviceLimit;
    @NotNull
    @DecimalMin("0.0")
    private BigDecimal price;

    private boolean active = true;
}
