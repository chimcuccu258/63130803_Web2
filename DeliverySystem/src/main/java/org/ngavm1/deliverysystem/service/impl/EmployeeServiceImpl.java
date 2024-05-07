package org.ngavm1.deliverysystem.service.impl;

import org.ngavm1.deliverysystem.exception.EmployeeException;
import org.ngavm1.deliverysystem.payload.request.RequestEmployeeSignup;
import org.ngavm1.deliverysystem.payload.request.RequestUpdate;
import org.ngavm1.deliverysystem.payload.response.ResponseModel;
import org.ngavm1.deliverysystem.service.EmployeeService;
import org.springframework.http.ResponseEntity;

public class EmployeeServiceImpl implements EmployeeService {
    @Override
    public ResponseEntity<ResponseModel> findAllEmployee() throws EmployeeException {
        return null;
    }

    @Override
    public ResponseEntity<ResponseModel> findEmployeeByEmail(String email) throws EmployeeException {
        return null;
    }

    @Override
    public ResponseEntity<ResponseModel> findEmployeeByFullName(String fullName) throws EmployeeException {
        return null;
    }

    @Override
    public ResponseEntity<ResponseModel> findEmployeeByPhoneNumber(String phoneNumber) throws EmployeeException {
        return null;
    }

    @Override
    public ResponseEntity<ResponseModel> insertEmployee(RequestEmployeeSignup requestEmployeeSignup) throws EmployeeException {
        return null;
    }

    @Override
    public ResponseEntity<ResponseModel> updateEmployee(RequestUpdate requestUpdate) throws EmployeeException {
        return null;
    }

    @Override
    public ResponseEntity<ResponseModel> resetPassword(RequestUpdate requestUpdate) throws EmployeeException {
        return null;
    }
}
