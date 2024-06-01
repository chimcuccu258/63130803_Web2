package org.ngavm1.deliverysystem.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.ngavm1.deliverysystem.exception.SupplierException;
import org.ngavm1.deliverysystem.payload.response.ResponseModel;
import org.ngavm1.deliverysystem.service.SupplierService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/supplier")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SupplierController {
    private final SupplierService supplierService;

    @GetMapping("/find-all")
    @Operation(summary = "Find All Supplier")
    public ResponseEntity<ResponseModel> findAllSupplier() throws SupplierException {
        return supplierService.findAllSupplier();
    }
}
