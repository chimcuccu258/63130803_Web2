package org.ngavm1.deliverysystem.service.impl;

import lombok.RequiredArgsConstructor;
import org.ngavm1.deliverysystem.exception.EmployeeException;
import org.ngavm1.deliverysystem.exception.SupplierException;
import org.ngavm1.deliverysystem.model.Employee;
import org.ngavm1.deliverysystem.model.Supplier;
import org.ngavm1.deliverysystem.payload.request.RequestSupplierSignup;
import org.ngavm1.deliverysystem.payload.request.RequestUpdate;
import org.ngavm1.deliverysystem.payload.response.ResponseModel;
import org.ngavm1.deliverysystem.repository.SupplierRepository;
import org.ngavm1.deliverysystem.service.SupplierService;
import org.ngavm1.deliverysystem.utils.HeadersHTTP;
import org.ngavm1.deliverysystem.utils.MessageStringResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SupplierServiceImpl implements SupplierService {
    private final SupplierRepository supplierRepository;

    @Override
    public ResponseEntity<ResponseModel> findAllSupplier() throws SupplierException {
        List<Supplier> supplierList = supplierRepository.findAllSupplier();

        if (supplierList != null) {
            ResponseModel response = new ResponseModel(MessageStringResponse.SUCCESS, MessageStringResponse.SUCCESS, supplierList);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(new MediaType(HeadersHTTP.MEDIA_TYPE, HeadersHTTP.MEDIA_SUBTYPE, StandardCharsets.UTF_8));
            return ResponseEntity.ok().headers(headers).body(response);
        } else {
            throw new SupplierException(MessageStringResponse.SUPPLIER_NOT_FOUND);
        }
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

    @Override
    public ResponseEntity<ResponseModel> insertSupplier(RequestSupplierSignup requestSupplierSignup) throws SupplierException {
        return null;
    }

    @Override
    public ResponseEntity<ResponseModel> updateSupplier(RequestUpdate requestUpdate) throws SupplierException {
        return null;
    }

    @Override
    public ResponseEntity<ResponseModel> resetPassword(RequestUpdate requestUpdate) throws SupplierException {
        return null;
    }
}
