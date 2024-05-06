package org.ngavm1.deliverysystem.service.impl;

import lombok.RequiredArgsConstructor;
import org.ngavm1.deliverysystem.exception.CustomerException;
import org.ngavm1.deliverysystem.exception.EmployeeException;
import org.ngavm1.deliverysystem.model.Customer;
import org.ngavm1.deliverysystem.model.Employee;
import org.ngavm1.deliverysystem.repository.CustomerRepository;
import org.ngavm1.deliverysystem.repository.EmployeeRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final CustomerRepository customerRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Get user from customerRepository
        try {

            Customer customer = customerRepository.findCustomerByEmail(email);
            if (customer != null) {
                String role = "CUSTOMER";

                return org.springframework.security.core.userdetails.User.builder()
                        .username(customer.getEmail())
                        .password(customer.getPassword())
                        .roles(role)
                        .build();
            }

        } catch (CustomerException e) {
            // handle exception
            e.printStackTrace();
        }

        // Get user from employeeRepository
        try {

            Employee employee = employeeRepository.findEmployeeByEmail(email);
            if (employee == null) {
                throw new UsernameNotFoundException("User not found with email: " + email);
            }
            String role = "EMPLOYEE";
            return org.springframework.security.core.userdetails.User.builder()
                    .username(employee.getEmail())
                    .password(employee.getPassword())
                    .roles(role)
                    .build();

        } catch (EmployeeException e) {
            // handle exception
            e.printStackTrace();
        }

        throw new UsernameNotFoundException("User not found");
    }
}