package org.ngavm1.deliverysystem.service;

import org.ngavm1.deliverysystem.exception.EmployeeException;
import org.ngavm1.deliverysystem.payload.request.RequestEmployeeSignup;
import org.ngavm1.deliverysystem.payload.request.RequestUpdate;
import org.ngavm1.deliverysystem.payload.response.ResponseModel;
import org.springframework.http.ResponseEntity;

public interface EmployeeService {
    ResponseEntity<ResponseModel> findAllEmployee() throws EmployeeException;

    ResponseEntity<ResponseModel> findEmployeeById(Long employeeID) throws EmployeeException;

    ResponseEntity<ResponseModel> findEmployeeByEmail(String email) throws EmployeeException;

    ResponseEntity<ResponseModel> findEmployeeByFullName(String fullName) throws EmployeeException;

    ResponseEntity<ResponseModel> findEmployeeByPhoneNumber(String phoneNumber) throws EmployeeException;

    ResponseEntity<ResponseModel> insertEmployee(RequestEmployeeSignup requestEmployeeSignup) throws EmployeeException;

    ResponseEntity<ResponseModel> updateEmployee(RequestUpdate requestUpdate) throws EmployeeException;

    ResponseEntity<ResponseModel> resetPassword(RequestUpdate requestUpdate) throws EmployeeException;
}
