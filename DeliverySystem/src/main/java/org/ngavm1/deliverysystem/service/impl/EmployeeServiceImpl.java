package org.ngavm1.deliverysystem.service.impl;

import lombok.RequiredArgsConstructor;
import org.ngavm1.deliverysystem.exception.EmployeeException;
import org.ngavm1.deliverysystem.model.Employee;
import org.ngavm1.deliverysystem.payload.request.RequestChangePassword;
import org.ngavm1.deliverysystem.payload.request.RequestEmployeeUpdate;
import org.ngavm1.deliverysystem.payload.request.RequestResetPassword;
import org.ngavm1.deliverysystem.payload.response.ResponseModel;
import org.ngavm1.deliverysystem.repository.EmployeeRepository;
import org.ngavm1.deliverysystem.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.ngavm1.deliverysystem.utils.HeadersHTTP;
import org.ngavm1.deliverysystem.utils.MessageStringResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder encoder;

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
    public ResponseEntity<ResponseModel> findEmployeeById(Long employeeID) throws EmployeeException {
        if (employeeRepository.findEmployeeById(employeeID) != null) {
            Employee employee = employeeRepository.findEmployeeById(employeeID);
            ResponseModel response = new ResponseModel(MessageStringResponse.SUCCESS, MessageStringResponse.SUCCESS, employee);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(new MediaType(HeadersHTTP.MEDIA_TYPE, HeadersHTTP.MEDIA_SUBTYPE, StandardCharsets.UTF_8));
            return ResponseEntity.ok().headers(headers).body(response);
        } else {
            throw new EmployeeException(MessageStringResponse.EMPLOYEE_NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<ResponseModel> findEmployeeByEmail(String email) throws EmployeeException {
        if (employeeRepository.findEmployeeByEmail(email) != null) {
            Employee employee = employeeRepository.findEmployeeByEmail(email);
            ResponseModel response = new ResponseModel(MessageStringResponse.SUCCESS, MessageStringResponse.SUCCESS, employee);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(new MediaType(HeadersHTTP.MEDIA_TYPE, HeadersHTTP.MEDIA_SUBTYPE, StandardCharsets.UTF_8));
            return ResponseEntity.ok().headers(headers).body(response);
        } else {
            throw new EmployeeException(MessageStringResponse.EMPLOYEE_NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<ResponseModel> findEmployeeByFullName(String fullName) throws EmployeeException {
        if (employeeRepository.findEmployeeByFullName(fullName) != null) {
            Employee employee = employeeRepository.findEmployeeByFullName(fullName);
            ResponseModel response = new ResponseModel(MessageStringResponse.SUCCESS, MessageStringResponse.SUCCESS, employee);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(new MediaType(HeadersHTTP.MEDIA_TYPE, HeadersHTTP.MEDIA_SUBTYPE, StandardCharsets.UTF_8));
            return ResponseEntity.ok().headers(headers).body(response);
        } else {
            throw new EmployeeException(MessageStringResponse.EMPLOYEE_NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<ResponseModel> findEmployeeByPhoneNumber(String phoneNumber) throws EmployeeException {
        if (employeeRepository.findEmployeeByPhoneNumber(phoneNumber) != null) {
            Employee employee = employeeRepository.findEmployeeByPhoneNumber(phoneNumber);
            ResponseModel response = new ResponseModel(MessageStringResponse.SUCCESS, MessageStringResponse.SUCCESS, employee);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(new MediaType(HeadersHTTP.MEDIA_TYPE, HeadersHTTP.MEDIA_SUBTYPE, StandardCharsets.UTF_8));
            return ResponseEntity.ok().headers(headers).body(response);
        } else {
            throw new EmployeeException(MessageStringResponse.EMPLOYEE_NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<ResponseModel> updateEmployee(RequestEmployeeUpdate requestEmployeeUpdate) throws EmployeeException {
        if (employeeRepository.findEmployeeById(requestEmployeeUpdate.getEmployeeID()) != null) {
            int result = employeeRepository.updateEmployee(requestEmployeeUpdate);
            if (result > 0) {
                ResponseModel response = new ResponseModel(MessageStringResponse.SUCCESS, MessageStringResponse.SUCCESS, requestEmployeeUpdate);
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(new MediaType(HeadersHTTP.MEDIA_TYPE, HeadersHTTP.MEDIA_SUBTYPE, StandardCharsets.UTF_8));
                return ResponseEntity.ok().headers(headers).body(response);
            } else {
                throw new EmployeeException(MessageStringResponse.UPDATE_FAILED);
            }
        } else {
            throw new EmployeeException(MessageStringResponse.EMPLOYEE_NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<ResponseModel> resetPassword(RequestResetPassword requestResetPassword) throws EmployeeException, SQLIntegrityConstraintViolationException {
        Employee employee = employeeRepository.findEmployeeByEmail(requestResetPassword.getEmail());
        if (employee == null) {
            throw new EmployeeException(MessageStringResponse.EMPLOYEE_NOT_FOUND);
        }

        int result = employeeRepository.resetPassword(requestResetPassword);

        if (result == 1) {
            ResponseModel responseModel = new ResponseModel(MessageStringResponse.SUCCESS,
                    MessageStringResponse.CHANGE_PASSWORD_SUCCESSFULLY, null);
            return new ResponseEntity<>(responseModel, HttpStatus.OK);
        } else {
            throw new EmployeeException(MessageStringResponse.CHANGE_PASSWORD_FAILED);
        }
    }

    @Override
    public ResponseEntity<ResponseModel> changePassword(String email, RequestChangePassword requestChangePassword) throws EmployeeException, SQLIntegrityConstraintViolationException {
        //get password of user
        Employee employee = employeeRepository.findEmployeeByEmail(email);
        String password = employee.getPassword();

        //check old password is equal with password of user
        if (encoder.matches(requestChangePassword.getOldPassword(), password)) {

            requestChangePassword.setNewPassword(encoder.encode(requestChangePassword.getNewPassword()));
            if (employeeRepository.changePassword(requestChangePassword.getNewPassword(),
                    employee.getEmployeeID()) == 1) { // If password is changed successfully
                ResponseModel responseModel = new ResponseModel(MessageStringResponse.SUCCESS,
                        MessageStringResponse.CHANGE_PASSWORD_SUCCESSFULLY, null);
                return new ResponseEntity<>(responseModel, HttpStatus.OK);
            } else {
                throw new EmployeeException(MessageStringResponse.CHANGE_PASSWORD_FAILED);
            }
        } else {    // If old password is incorrect
            throw new EmployeeException(MessageStringResponse.OLD_PASSWORD_INCORRECT);
        }
    }
}
