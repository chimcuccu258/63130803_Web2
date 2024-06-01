package org.ngavm1.deliverysystem.service.impl;

import lombok.RequiredArgsConstructor;
import org.ngavm1.deliverysystem.exception.EmployeeException;
import org.ngavm1.deliverysystem.exception.SupplierException;
import org.ngavm1.deliverysystem.model.Employee;
import org.ngavm1.deliverysystem.model.Supplier;
import org.ngavm1.deliverysystem.payload.request.RequestEmployeeUpdate;
import org.ngavm1.deliverysystem.payload.request.RequestSupplierUpdate;
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
    public ResponseEntity<ResponseModel> findSupplierBySupplierId(Long supplierID) throws SupplierException {
        if (supplierRepository.findSupplierBySupplierId(supplierID) != null) {
            Supplier supplier = supplierRepository.findSupplierBySupplierId(supplierID);
            ResponseModel response = new ResponseModel(MessageStringResponse.SUCCESS, MessageStringResponse.SUCCESS, supplier);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(new MediaType(HeadersHTTP.MEDIA_TYPE, HeadersHTTP.MEDIA_SUBTYPE, StandardCharsets.UTF_8));
            return ResponseEntity.ok().headers(headers).body(response);
        } else {
            throw new SupplierException(MessageStringResponse.SUPPLIER_NOT_FOUND);
        }
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
    public ResponseEntity<ResponseModel> updateSupplier(RequestSupplierUpdate requestSupplierUpdate) throws SupplierException {
        if (supplierRepository.findSupplierBySupplierId(requestSupplierUpdate.getSupplierID()) != null) {
            int result = supplierRepository.updateSupplier(requestSupplierUpdate);
            if (result > 0) {
                ResponseModel response = new ResponseModel(MessageStringResponse.SUCCESS, MessageStringResponse.SUCCESS, requestSupplierUpdate);
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(new MediaType(HeadersHTTP.MEDIA_TYPE, HeadersHTTP.MEDIA_SUBTYPE, StandardCharsets.UTF_8));
                return ResponseEntity.ok().headers(headers).body(response);
            } else {
                throw new SupplierException(MessageStringResponse.UPDATE_FAILED);
            }
        } else {
            throw new SupplierException(MessageStringResponse.SUPPLIER_NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<ResponseModel> resetPassword(RequestEmployeeUpdate requestUpdate) throws SupplierException {
        return null;
    }
}
