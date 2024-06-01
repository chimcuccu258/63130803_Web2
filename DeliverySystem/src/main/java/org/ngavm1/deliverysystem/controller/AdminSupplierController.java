package org.ngavm1.deliverysystem.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.ngavm1.deliverysystem.exception.EmployeeException;
import org.ngavm1.deliverysystem.exception.SupplierException;
import org.ngavm1.deliverysystem.payload.request.RequestEmployeeUpdate;
import org.ngavm1.deliverysystem.payload.request.RequestSupplierUpdate;
import org.ngavm1.deliverysystem.payload.response.ResponseModel;
import org.ngavm1.deliverysystem.service.SupplierService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/v1/manage-supplier")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AdminSupplierController {
    private final SupplierService supplierService;

    @GetMapping("/find-all")
    @Operation(summary = "Find All Supplier")
    public ResponseEntity<ResponseModel> findAllSupplier() throws SupplierException {
        return supplierService.findAllSupplier();
    }

    @PostMapping("/update-supplier")
    @Operation(summary = "Update supplier information")
    public ResponseEntity<ResponseModel> updateSupplier(@RequestBody RequestSupplierUpdate requestSupplierUpdate) throws SupplierException {
        return supplierService.updateSupplier(requestSupplierUpdate);
    }
}
