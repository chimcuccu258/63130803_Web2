package org.ngavm1.deliverysystem.service;

import org.ngavm1.deliverysystem.exception.EmployeeException;
import org.ngavm1.deliverysystem.payload.request.RequestChangePassword;
import org.ngavm1.deliverysystem.payload.request.RequestEmployeeUpdate;
import org.ngavm1.deliverysystem.payload.request.RequestResetPassword;
import org.ngavm1.deliverysystem.payload.response.ResponseModel;
import org.springframework.http.ResponseEntity;

import java.sql.SQLIntegrityConstraintViolationException;

public interface EmployeeService {
    ResponseEntity<ResponseModel> findAllEmployee() throws EmployeeException;

    ResponseEntity<ResponseModel> findEmployeeById(Long employeeID) throws EmployeeException;

    ResponseEntity<ResponseModel> findEmployeeByEmail(String email) throws EmployeeException;

    ResponseEntity<ResponseModel> findEmployeeByFullName(String fullName) throws EmployeeException;

    ResponseEntity<ResponseModel> findEmployeeByPhoneNumber(String phoneNumber) throws EmployeeException;

    ResponseEntity<ResponseModel> updateEmployee(RequestEmployeeUpdate requestEmployeeUpdate) throws EmployeeException;

    ResponseEntity<ResponseModel> resetPassword(RequestResetPassword requestResetPassword) throws EmployeeException, SQLIntegrityConstraintViolationException;

    ResponseEntity<ResponseModel> setNewPassword(RequestResetPassword requestResetPassword) throws EmployeeException, SQLIntegrityConstraintViolationException;

    ResponseEntity<ResponseModel> changePassword(Long employeeID, RequestChangePassword requestChangePassword) throws EmployeeException, SQLIntegrityConstraintViolationException;
}
