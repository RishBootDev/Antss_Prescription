package com.antss_prescription.service.impl;

import com.antss_prescription.dto.request.CreatePackageRequest;
import com.antss_prescription.dto.response.PackageResponse;
import com.antss_prescription.entity.SubscriptionPackage;
import com.antss_prescription.exception.BusinessException;
import com.antss_prescription.exception.ResourceNotFoundException;
import com.antss_prescription.repository.PackageRepository;
import com.antss_prescription.service.PackageService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
public class PackageServiceImpl implements PackageService {


    private final PackageRepository packageRepository;
    private final ModelMapper modelMapper;

    public PackageServiceImpl(PackageRepository packageRepository, ModelMapper modelMapper) {
        this.packageRepository = packageRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public PackageResponse createPackage(CreatePackageRequest request) {
        if (packageRepository.existsByName(request.getName())) {
            throw new BusinessException("Package with name '" + request.getName() + "' already exists");
        }

        SubscriptionPackage pkg = new SubscriptionPackage();
        pkg.setName(request.getName());
        pkg.setValidityDays(request.getValidityDays());
        pkg.setDoctorLimit(request.getDoctorLimit());
        pkg.setDeviceLimit(request.getDeviceLimit());
        pkg.setPrice(request.getPrice());
        pkg.setActive(request.isActive());

        SubscriptionPackage saved = packageRepository.save(pkg);
        log.info("Package created: {}", saved.getName());
        return modelMapper.map(saved, PackageResponse.class);
    }

    @Override
    public PackageResponse updatePackage(Long id, CreatePackageRequest request) {
        SubscriptionPackage pkg = packageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Package", id));

        pkg.setName(request.getName());
        pkg.setValidityDays(request.getValidityDays());
        pkg.setDoctorLimit(request.getDoctorLimit());
        pkg.setDeviceLimit(request.getDeviceLimit());
        pkg.setPrice(request.getPrice());
        pkg.setActive(request.isActive());

        SubscriptionPackage saved = packageRepository.save(pkg);
        log.info("Package updated: {}", saved.getName());
        return modelMapper.map(saved, PackageResponse.class);
    }

    @Override
    public void deletePackage(Long id) {
        SubscriptionPackage pkg = packageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Package", id));
        pkg.setActive(false);
        packageRepository.save(pkg);
        log.info("Package soft-deleted: {}", pkg.getName());
    }

    @Override
    @Transactional(readOnly = true)
    public List<PackageResponse> getAllPackages() {
        return packageRepository.findAll()
                .stream()
                .map(pkg -> modelMapper.map(pkg, PackageResponse.class))
                .collect(Collectors.toList());
    }
}
