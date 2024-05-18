package org.ngavm1.deliverysystem.service.impl;

import lombok.RequiredArgsConstructor;
import org.ngavm1.deliverysystem.exception.EmployeeException;
import org.ngavm1.deliverysystem.model.Employee;
import org.ngavm1.deliverysystem.payload.request.RequestEmployeeSignup;
import org.ngavm1.deliverysystem.payload.request.RequestUpdate;
import org.ngavm1.deliverysystem.payload.response.ResponseModel;
import org.ngavm1.deliverysystem.repository.EmployeeRepository;
import org.ngavm1.deliverysystem.service.EmployeeService;
import org.ngavm1.deliverysystem.utils.HeadersHTTP;
import org.ngavm1.deliverysystem.utils.MessageStringResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Override
    public ResponseEntity<ResponseModel> findAllEmployee() throws EmployeeException {
        List<Employee> employeeList = employeeRepository.findAllEmployee();

        if (employeeList != null) {
            ResponseModel response = new ResponseModel(MessageStringResponse.SUCCESS, MessageStringResponse.SUCCESS, employeeList);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(new MediaType(HeadersHTTP.MEDIA_TYPE, HeadersHTTP.MEDIA_SUBTYPE, StandardCharsets.UTF_8));
            return ResponseEntity.ok().headers(headers).body(response);
        } else {
            throw new EmployeeException(MessageStringResponse.EMPLOYEE_NOT_FOUND);
        }
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
