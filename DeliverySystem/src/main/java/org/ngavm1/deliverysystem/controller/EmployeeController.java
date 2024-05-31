package org.ngavm1.deliverysystem.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.ngavm1.deliverysystem.exception.EmployeeException;
import org.ngavm1.deliverysystem.payload.request.RequestEmployeeUpdate;
import org.ngavm1.deliverysystem.payload.response.ResponseModel;
import org.ngavm1.deliverysystem.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


}
