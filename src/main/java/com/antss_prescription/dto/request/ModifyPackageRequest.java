package com.antss_prescription.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ModifyPackageRequest {

    @NotNull
    private Long packageId;
}
