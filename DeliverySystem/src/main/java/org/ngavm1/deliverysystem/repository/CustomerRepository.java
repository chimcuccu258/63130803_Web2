package org.ngavm1.deliverysystem.repository;

import lombok.RequiredArgsConstructor;
import org.ngavm1.deliverysystem.mapper.CustomerMapper;
import org.ngavm1.deliverysystem.model.Customer;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CustomerRepository {
    private final CustomerMapper customerMapper;

    public Customer getUserByUsername(String username) {
        return customerMapper.getUserByUsername(username);
    }
}