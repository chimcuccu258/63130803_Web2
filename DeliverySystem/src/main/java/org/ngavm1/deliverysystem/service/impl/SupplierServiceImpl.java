package org.ngavm1.deliverysystem.service.impl;

import org.ngavm1.deliverysystem.exception.SupplierException;
import org.ngavm1.deliverysystem.payload.response.ResponseModel;
import org.ngavm1.deliverysystem.service.SupplierService;
import org.springframework.http.ResponseEntity;

public class SupplierServiceImpl implements SupplierService {
    @Override
    public ResponseEntity<ResponseModel> findAllSupplier() throws SupplierException {
        return null;
    }

    @Override
    public ResponseEntity<ResponseModel> findSupplierBySupplierCode(String supplierCode) throws SupplierException {
        return null;
    }

    @Override
    public ResponseEntity<ResponseModel> findSupplierByPhoneNumber(String phoneNumber) throws SupplierException {
        return null;
    }

    @Override
    public ResponseEntity<ResponseModel> findSupplierByName(String supplierName) throws SupplierException {
        return null;
    }

    @Override
    public ResponseEntity<ResponseModel> findSupplierByEmail(String email) throws SupplierException {
        return null;
    }
}
