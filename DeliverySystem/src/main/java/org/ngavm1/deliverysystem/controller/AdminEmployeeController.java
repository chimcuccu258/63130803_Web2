package org.ngavm1.deliverysystem.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.ngavm1.deliverysystem.exception.EmployeeException;
import org.ngavm1.deliverysystem.payload.response.ResponseModel;
import org.ngavm1.deliverysystem.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/v1/manage-employee")
public class AdminEmployeeController {
    private final EmployeeService employeeService;

    @GetMapping("/find-all")
    @Operation(summary = "Find All Employee")
    public ResponseEntity<ResponseModel> findAllEmployee() throws EmployeeException {
        return employeeService.findAllEmployee();
    }
}
