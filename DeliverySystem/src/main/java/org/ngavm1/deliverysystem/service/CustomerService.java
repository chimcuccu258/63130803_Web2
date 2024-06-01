package org.ngavm1.deliverysystem.service;

import org.ngavm1.deliverysystem.exception.CustomerException;
import org.ngavm1.deliverysystem.model.Customer;
import org.ngavm1.deliverysystem.payload.response.ResponseModel;
import org.springframework.http.ResponseEntity;

public interface CustomerService {
    ResponseEntity<ResponseModel> findAllCustomer() throws CustomerException;

    Customer findByFullNameAndAddressAndPhoneNumber(String fullName, String address, String phoneNumber) throws CustomerException;

}
