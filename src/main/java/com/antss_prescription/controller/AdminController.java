package com.antss_prescription.controller;

import com.antss_prescription.dto.request.ExtendValidityRequest;
import com.antss_prescription.dto.request.ModifyPackageRequest;
import com.antss_prescription.dto.response.ApiResponse;
import com.antss_prescription.dto.response.UserResponse;
import com.antss_prescription.service.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Admin APIs", description = "Admin workflow for user approvals and management")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/registrations/pending")
    @Operation(summary = "Get all pending registrations")
    public ResponseEntity<ApiResponse<List<UserResponse>>> getPendingRegistrations() {
        List<UserResponse> responses = adminService.getPendingRegistrations();
        return ResponseEntity.ok(ApiResponse.success("Pending registrations fetched successfully", responses));
    }

    @PostMapping("/users/{id}/approve")
    @Operation(summary = "Approve a user registration")
    public ResponseEntity<ApiResponse<UserResponse>> approveUser(@PathVariable Long id) {
        UserResponse response = adminService.approveUser(id);
        return ResponseEntity.ok(ApiResponse.success("User approved successfully", response));
    }

    @PostMapping("/users/{id}/reject")
    @Operation(summary = "Reject a user registration")
    public ResponseEntity<ApiResponse<UserResponse>> rejectUser(@PathVariable Long id) {
        UserResponse response = adminService.rejectUser(id);
        return ResponseEntity.ok(ApiResponse.success("User rejected successfully", response));
    }

    @PutMapping("/users/{id}/package")
    @Operation(summary = "Modify a user's subscription package")
    public ResponseEntity<ApiResponse<UserResponse>> modifyPackage(@PathVariable Long id, @Valid @RequestBody ModifyPackageRequest request) {
        UserResponse response = adminService.modifyUserPackage(id, request);
        return ResponseEntity.ok(ApiResponse.success("User package updated successfully", response));
    }

    @PutMapping("/users/{id}/extend")
    @Operation(summary = "Extend a user's subscription validity")
    public ResponseEntity<ApiResponse<UserResponse>> extendValidity(@PathVariable Long id, @Valid @RequestBody ExtendValidityRequest request) {
        UserResponse response = adminService.extendValidity(id, request);
        return ResponseEntity.ok(ApiResponse.success("User validity extended successfully", response));
    }

    @PutMapping("/users/{id}/block")
    @Operation(summary = "Block an active user")
    public ResponseEntity<ApiResponse<UserResponse>> blockUser(@PathVariable Long id) {
        UserResponse response = adminService.blockUser(id);
        return ResponseEntity.ok(ApiResponse.success("User blocked successfully", response));
    }

    @PutMapping("/users/{id}/unblock")
    @Operation(summary = "Unblock a blocked user")
    public ResponseEntity<ApiResponse<UserResponse>> unblockUser(@PathVariable Long id) {
        UserResponse response = adminService.unblockUser(id);
        return ResponseEntity.ok(ApiResponse.success("User unblocked successfully", response));
    }
}
