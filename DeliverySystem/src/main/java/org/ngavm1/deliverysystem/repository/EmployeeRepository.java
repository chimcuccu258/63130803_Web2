package org.ngavm1.deliverysystem.repository;

import lombok.RequiredArgsConstructor;
import org.ngavm1.deliverysystem.exception.EmployeeException;
import org.ngavm1.deliverysystem.mapper.EmployeeMapper;
import org.ngavm1.deliverysystem.model.Employee;
import org.ngavm1.deliverysystem.payload.request.RequestEmployeeSignup;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class EmployeeRepository {
    private final EmployeeMapper employeeMapper;

    public List<Employee> findAllEmployee() throws EmployeeException {
        return employeeMapper.findAllEmployee();
    }

    public Employee findEmployeeByEmail(String email) throws EmployeeException {
        return employeeMapper.findEmployeeByEmail(email);
    }

    public int createAccount(RequestEmployeeSignup requestEmployeeSignup) throws EmployeeException {
        return employeeMapper.createAccount(requestEmployeeSignup);
    }

    public boolean existsByEmail(String email) throws EmployeeException {
        return employeeMapper.existsByEmail(email).isPresent();
    }
}
