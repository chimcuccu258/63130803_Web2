package org.ngavm1.deliverysystem.repository;

import lombok.RequiredArgsConstructor;
import org.ngavm1.deliverysystem.exception.SupplierException;
import org.ngavm1.deliverysystem.mapper.SupplierMapper;
import org.ngavm1.deliverysystem.model.Supplier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class SupplierRepository {
    private final SupplierMapper supplierMapper;

    public List<Supplier> findAllSupplier() throws SupplierException {
        return supplierMapper.findAllSupplier();
    }

    public Supplier findSupplierBySupplierCode(String supplierCode) throws SupplierException {
        return supplierMapper.findSupplierBySupplierCode(supplierCode);
    }

    public Supplier findSupplierByPhoneNumber(String phoneNumber) throws SupplierException {
        return supplierMapper.findSupplierByPhoneNumber(phoneNumber);
    }

    public Supplier findSupplierByName(String supplierName) throws SupplierException {
        return supplierMapper.findSupplierByName(supplierName);
    }

    public Supplier findSupplierByEmail(String email) throws SupplierException {
        return supplierMapper.findSupplierByEmail(email);
    }
}
