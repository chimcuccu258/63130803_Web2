package org.ngavm1.deliverysystem.repository;

import lombok.RequiredArgsConstructor;
import org.ngavm1.deliverysystem.exception.CustomerException;
import org.ngavm1.deliverysystem.mapper.CustomerMapper;
import org.ngavm1.deliverysystem.model.Customer;
import org.ngavm1.deliverysystem.payload.request.RequestLogin;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CustomerRepository {
    private final CustomerMapper customerMapper;

    public List<Customer> findAllCustomer() throws CustomerException {
        return customerMapper.findAllCustomer();
    }

    public Customer loginCustomer(RequestLogin requestLogin) throws CustomerException {
        return customerMapper.loginCustomer(requestLogin);
    }

}
