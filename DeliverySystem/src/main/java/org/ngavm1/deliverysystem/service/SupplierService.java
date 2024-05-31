package org.ngavm1.deliverysystem.service;

import org.ngavm1.deliverysystem.exception.SupplierException;
import org.ngavm1.deliverysystem.payload.request.RequestEmployeeUpdate;
import org.ngavm1.deliverysystem.payload.response.ResponseModel;
import org.springframework.http.ResponseEntity;

public interface SupplierService {
    ResponseEntity<ResponseModel> findAllSupplier() throws SupplierException;

    ResponseEntity<ResponseModel> findSupplierByPhoneNumber(String phoneNumber) throws SupplierException;

    ResponseEntity<ResponseModel> findSupplierByName(String supplierName) throws SupplierException;

    ResponseEntity<ResponseModel> findSupplierByEmail(String email) throws SupplierException;

    ResponseEntity<ResponseModel> updateSupplier(RequestEmployeeUpdate requestUpdate) throws SupplierException;

    ResponseEntity<ResponseModel> resetPassword(RequestEmployeeUpdate requestUpdate) throws SupplierException;

}
