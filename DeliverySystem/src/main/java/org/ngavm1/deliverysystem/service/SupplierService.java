package org.ngavm1.deliverysystem.service;

import org.ngavm1.deliverysystem.exception.SupplierException;
import org.ngavm1.deliverysystem.payload.request.RequestSupplierSignup;
import org.ngavm1.deliverysystem.payload.request.RequestUpdate;
import org.ngavm1.deliverysystem.payload.response.ResponseModel;
import org.springframework.http.ResponseEntity;

public interface SupplierService {
    ResponseEntity<ResponseModel> findAllSupplier() throws SupplierException;

    ResponseEntity<ResponseModel> findSupplierBySupplierCode(String supplierCode) throws SupplierException;

    ResponseEntity<ResponseModel> findSupplierByPhoneNumber(String phoneNumber) throws SupplierException;

    ResponseEntity<ResponseModel> findSupplierByName(String supplierName) throws SupplierException;

    ResponseEntity<ResponseModel> findSupplierByEmail(String email) throws SupplierException;

    ResponseEntity<ResponseModel> updateSupplier(RequestUpdate requestUpdate) throws SupplierException;

    ResponseEntity<ResponseModel> resetPassword(RequestUpdate requestUpdate) throws SupplierException;

}
