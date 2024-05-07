package org.ngavm1.deliverysystem.repository;

import lombok.RequiredArgsConstructor;
import org.ngavm1.deliverysystem.exception.CustomerException;
import org.ngavm1.deliverysystem.mapper.CustomerMapper;
import org.ngavm1.deliverysystem.model.Customer;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CustomerRepository {
    private final CustomerMapper customerMapper;

    public List<Customer> findAllCustomer() throws CustomerException {
        return customerMapper.findAllCustomer();
    }

    public Customer findCustomerByFullName(String fullName) throws CustomerException {
        return customerMapper.findCustomerByFullName(fullName);
    }

    public Customer findCustomerByPhoneNumber(String phoneNumber) throws CustomerException {
        return customerMapper.findCustomerByPhoneNumber(phoneNumber);
    }

}
