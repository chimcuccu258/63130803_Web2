package org.ngavm1.deliverysystem.service.impl;

import lombok.RequiredArgsConstructor;
import org.ngavm1.deliverysystem.exception.CustomerException;
import org.ngavm1.deliverysystem.model.Customer;
import org.ngavm1.deliverysystem.payload.response.ResponseModel;
import org.ngavm1.deliverysystem.repository.CustomerRepository;
import org.ngavm1.deliverysystem.service.CustomerService;
import org.ngavm1.deliverysystem.utils.MessageStringResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    @Override
    public ResponseEntity<ResponseModel> findAllCustomer() throws CustomerException {
        List<Customer> customerList = customerRepository.findAllCustomer();

        if (customerList != null) {
            ResponseModel response = new ResponseModel(MessageStringResponse.SUCCESS, MessageStringResponse.SUCCESS, customerList);
            return ResponseEntity.ok().headers(new HttpHeaders()).body(response);
        } else {
            throw new CustomerException(MessageStringResponse.CUSTOMER_NOT_FOUND);
        }
    }


    @Override
    public Customer findByFullNameAndAddressAndPhoneNumber(String fullName, String address, String phoneNumber) throws CustomerException {
        return customerRepository.findCustomerByFullNameAndAddressAndPhoneNumber(fullName, address, phoneNumber);
    }
}