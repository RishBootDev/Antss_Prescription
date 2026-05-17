package com.antss_prescription.service;

import com.antss_prescription.dto.request.CreatePackageRequest;
import com.antss_prescription.dto.response.PackageResponse;

import java.util.List;

public interface PackageService {
    PackageResponse createPackage(CreatePackageRequest request);
    PackageResponse updatePackage(Long id, CreatePackageRequest request);
    void deletePackage(Long id);
    List<PackageResponse> getAllPackages();
}
