package org.ngavm1.deliverysystem.service.impl;

import lombok.RequiredArgsConstructor;
import org.ngavm1.deliverysystem.exception.CustomerException;
import org.ngavm1.deliverysystem.exception.EmployeeException;
import org.ngavm1.deliverysystem.exception.SupplierException;
import org.ngavm1.deliverysystem.model.Customer;
import org.ngavm1.deliverysystem.model.Employee;
import org.ngavm1.deliverysystem.model.Supplier;
import org.ngavm1.deliverysystem.repository.CustomerRepository;
import org.ngavm1.deliverysystem.repository.EmployeeRepository;
import org.ngavm1.deliverysystem.repository.SupplierRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final CustomerRepository customerRepository;
    private final EmployeeRepository employeeRepository;
    private final SupplierRepository supplierRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Get user from supplierRepository
        try {

            Supplier supplier = supplierRepository.findSupplierByEmail(email);
            if (supplier != null) {
                String role = "SUPPLIER";

                return org.springframework.security.core.userdetails.User.builder()
                        .username(supplier.getEmail())
                        .password(supplier.getPassword())
                        .roles(role)
                        .build();
            }

        } catch (SupplierException e) {
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