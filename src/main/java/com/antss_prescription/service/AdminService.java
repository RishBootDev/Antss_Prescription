package com.antss_prescription.service;

import com.antss_prescription.dto.request.ExtendValidityRequest;
import com.antss_prescription.dto.request.ModifyPackageRequest;
import com.antss_prescription.dto.response.UserResponse;

import java.util.List;

public interface AdminService {
    List<UserResponse> getPendingRegistrations();
    UserResponse approveUser(Long userId);
    UserResponse rejectUser(Long userId);
    UserResponse modifyUserPackage(Long userId, ModifyPackageRequest request);
    UserResponse extendValidity(Long userId, ExtendValidityRequest request);
    UserResponse blockUser(Long userId);
    UserResponse unblockUser(Long userId);
}
