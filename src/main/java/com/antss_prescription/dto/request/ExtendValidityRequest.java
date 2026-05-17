package com.antss_prescription.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class ExtendValidityRequest {

    @NotNull
    @Min(1)
    private Integer days;
}
