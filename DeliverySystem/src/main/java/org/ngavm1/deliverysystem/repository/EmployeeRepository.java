package org.ngavm1.deliverysystem.repository;

import lombok.RequiredArgsConstructor;
import org.ngavm1.deliverysystem.exception.EmployeeException;
import org.ngavm1.deliverysystem.mapper.EmployeeMapper;
import org.ngavm1.deliverysystem.model.Employee;
import org.ngavm1.deliverysystem.payload.request.RequestEmployeeSignup;
import org.ngavm1.deliverysystem.payload.request.RequestResetPassword;
import org.ngavm1.deliverysystem.payload.request.RequestUpdate;
import org.springframework.stereotype.Repository;

import java.sql.SQLIntegrityConstraintViolationException;
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

    public Employee findEmployeeByFullName(String fullName) throws EmployeeException {
        return employeeMapper.findEmployeeByFullName(fullName);
    }

    public Employee findEmployeeByPhoneNumber(String phoneNumber) throws EmployeeException {
        return employeeMapper.findEmployeeByPhoneNumber(phoneNumber);
    }

    public int insertEmployee(Employee employee) throws EmployeeException {
        return employeeMapper.insertEmployee(employee);
    }

    public int updateEmployee(RequestUpdate requestUpdate) throws EmployeeException {
        return employeeMapper.updateEmployee(requestUpdate);
    }

    public boolean existsByEmail(String email) throws EmployeeException {
        return employeeMapper.existsByEmail(email).isPresent();
    }

    public int resetPassword(RequestResetPassword requestResetPassword) throws EmployeeException, SQLIntegrityConstraintViolationException {
        return employeeMapper.resetPassword(requestResetPassword);
    }

    public int updateAvatar(Long employeeID, int mediaId) throws EmployeeException {
        return employeeMapper.updateAvatar(employeeID, mediaId);
    }
}
