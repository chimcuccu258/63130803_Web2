package org.ngavm1.deliverysystem.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.ngavm1.deliverysystem.exception.EmployeeException;
import org.ngavm1.deliverysystem.payload.request.RequestChangePassword;
import org.ngavm1.deliverysystem.payload.request.RequestEmployeeUpdate;
import org.ngavm1.deliverysystem.payload.request.RequestResetPassword;
import org.ngavm1.deliverysystem.payload.response.ResponseModel;
import org.ngavm1.deliverysystem.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLIntegrityConstraintViolationException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/employee")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class EmployeeController {
    private final EmployeeService employeeService;

    @PostMapping("/update")
    @Operation(summary = "Update employee information")
    public ResponseEntity<ResponseModel> updateEmployee(@RequestBody RequestEmployeeUpdate requestEmployeeUpdate) throws EmployeeException {
        return employeeService.updateEmployee(requestEmployeeUpdate);
    }

    @GetMapping("/find-by-email")
    @Operation(summary = "Find employee by email")
    public ResponseEntity<ResponseModel> findEmployeeByEmail(@RequestParam String email) throws EmployeeException {
        return employeeService.findEmployeeByEmail(email);
    }

    @PostMapping("/change-password")
    @Operation(summary = "Change employee password")
    public ResponseEntity<ResponseModel> changePassword(@RequestBody RequestChangePassword requestChangePassword) throws EmployeeException, SQLIntegrityConstraintViolationException {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        String username = securityContext.getAuthentication().getName();
        return employeeService.changePassword(username, requestChangePassword);
    }

    @PostMapping("/reset-password")
    @Operation(summary = "Reset employee password")
    public ResponseEntity<ResponseModel> resetPassword(@RequestBody RequestResetPassword requestResetPassword) throws EmployeeException, SQLIntegrityConstraintViolationException {
        return employeeService.resetPassword(requestResetPassword);
    }
}
