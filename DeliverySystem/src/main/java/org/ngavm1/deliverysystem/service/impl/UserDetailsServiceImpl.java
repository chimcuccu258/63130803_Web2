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
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final EmployeeRepository employeeRepository;
    private final SupplierRepository supplierRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        try {
            if (email.endsWith("@dls.co.com")) {
                Employee employee = employeeRepository.findEmployeeByEmail(email);

                if (employee != null) {
                    String role = "Admin";
                    return org.springframework.security.core.userdetails.User.builder()
                            .username(employee.getEmail())
                            .password(employee.getPassword())
                            .roles(role)
                            .build();
                }
            } else if (email.endsWith("@dls.em.com")) {
                Employee employee = employeeRepository.findEmployeeByEmail(email);

                if (employee != null) {
                    String role = "Employee";
                    return org.springframework.security.core.userdetails.User.builder()
                            .username(employee.getEmail())
                            .password(employee.getPassword())
                            .roles(role)
                            .build();
                }
            } else if (email.endsWith("@dls.su.com")) {
                Supplier supplier = supplierRepository.findSupplierByEmail(email);

                if (supplier != null) {
                    String role = "Supplier";
                    return org.springframework.security.core.userdetails.User.builder()
                            .username(supplier.getEmail())
                            .password(supplier.getPassword())
                            .roles(role)
                            .build();
                }
            }
        } catch (EmployeeException | SupplierException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            throw new InternalAuthenticationServiceException("Unexpected error occurred", e);
        }
        throw new UsernameNotFoundException("User not found");
    }
}