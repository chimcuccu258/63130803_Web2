package org.ngavm1.deliverysystem.service.impl;

import lombok.RequiredArgsConstructor;
import org.ngavm1.deliverysystem.exception.CustomerException;
import org.ngavm1.deliverysystem.model.Customer;
import org.ngavm1.deliverysystem.payload.request.RequestCustomerSignup;
import org.ngavm1.deliverysystem.payload.request.RequestLogin;
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
            ResponseModel response = new ResponseModel(200, MessageStringResponse.SUCCESS, customerList);
            return ResponseEntity.ok().headers(new HttpHeaders()).body(response);
        } else {
            throw new CustomerException(MessageStringResponse.CUSTOMER_NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<ResponseModel> loginCustomer(String email, String password) throws CustomerException {
        Customer customer = customerRepository.loginCustomer(new RequestLogin(email, password));

        if (customer != null) {
            ResponseModel response = new ResponseModel(200, MessageStringResponse.SUCCESS, customer);
            return ResponseEntity.ok().headers(new HttpHeaders()).body(response);
        } else {
            throw new CustomerException(MessageStringResponse.CUSTOMER_NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<ResponseModel> findCustomerById(Long customerID) throws CustomerException {
        Customer customer = customerRepository.findCustomerById(customerID);

        if (customer != null) {
            ResponseModel response = new ResponseModel(200, MessageStringResponse.SUCCESS, customer);
            return ResponseEntity.ok().headers(new HttpHeaders()).body(response);
        } else {
            throw new CustomerException(MessageStringResponse.CUSTOMER_NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<ResponseModel> createAccount(RequestCustomerSignup requestCustomerSignup) throws CustomerException {
        if (customerRepository.existsByEmail(requestCustomerSignup.getEmail())) {
            throw new CustomerException(MessageStringResponse.EMAIL_EXIST);
        }

        int result = customerRepository.createAccount(requestCustomerSignup);

        if (result > 0) {
            ResponseModel response = new ResponseModel(200, MessageStringResponse.SUCCESS, null);
            return ResponseEntity.ok().headers(new HttpHeaders()).body(response);
        } else {
            throw new CustomerException(MessageStringResponse.FAILED_CREATE_ACCOUNT);
        }
    }

    @Override
    public boolean existsByEmail(String email) throws CustomerException {
        return customerRepository.existsByEmail(email);
    }

}
