package org.ngavm1.deliverysystem.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.ngavm1.deliverysystem.exception.EmployeeException;
import org.ngavm1.deliverysystem.payload.request.RequestEmployeeUpdate;
import org.ngavm1.deliverysystem.payload.response.ResponseModel;
import org.ngavm1.deliverysystem.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/v1/manage-employee")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AdminEmployeeController {
    private final EmployeeService employeeService;

    @GetMapping("/find-all")
    @Operation(summary = "Find All Employee")
    public ResponseEntity<ResponseModel> findAllEmployee() throws EmployeeException {
        return employeeService.findAllEmployee();
    }

    @GetMapping("/find-by-id/{employeeID}")
    @Operation(summary = "Find employee by ID")
    public ResponseEntity<ResponseModel> findEmployeeById(@PathVariable Long employeeID) throws EmployeeException {
        return employeeService.findEmployeeById(employeeID);
    }

    @GetMapping("/find-by-email")
    @Operation(summary = "Find employee by email")
    public ResponseEntity<ResponseModel> findEmployeeByEmail(@RequestParam String email) throws EmployeeException {
        return employeeService.findEmployeeByEmail(email);
    }

    @PostMapping("/update-employee")
    @Operation(summary = "Update employee information")
    public ResponseEntity<ResponseModel> updateEmployee(@RequestBody RequestEmployeeUpdate requestEmployeeUpdate) throws EmployeeException {
        return employeeService.updateEmployee(requestEmployeeUpdate);
    }
}
