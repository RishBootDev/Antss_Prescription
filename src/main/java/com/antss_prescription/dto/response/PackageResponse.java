package com.antss_prescription.dto.response;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class PackageResponse {

    private Long id;
    private String name;
    private Integer validityDays;
    private Integer doctorLimit;
    private Integer deviceLimit;
    private BigDecimal price;
    private boolean active;
}
