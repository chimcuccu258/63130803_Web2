package org.ngavm1.deliverysystem.mapper;

import jakarta.validation.Valid;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.ngavm1.deliverysystem.exception.EmployeeException;
import org.ngavm1.deliverysystem.model.Employee;
import org.ngavm1.deliverysystem.payload.request.RequestEmployeeSignup;
import org.ngavm1.deliverysystem.payload.request.RequestResetPassword;
import org.ngavm1.deliverysystem.payload.request.RequestUpdate;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;

@Mapper
public interface EmployeeMapper {
    @Select("SELECT * FROM Employee")
    List<Employee> findAllEmployee() throws EmployeeException;

    @Select("SELECT * FROM Employee WHERE employeeID = #{employeeID}")
    Employee findEmployeeById(Long employeeID) throws EmployeeException;

    @Select("SELECT * FROM Employee WHERE fullName = #{fullName}")
    Employee findEmployeeByFullName(String fullName) throws EmployeeException;

    @Select("SELECT * FROM Employee WHERE email= #{email}")
    Employee findEmployeeByEmail(String email) throws EmployeeException;

    @Select("SELECT * FROM Employee WHERE phoneNumber = #{phoneNumber}")
    Employee findEmployeeByPhoneNumber(String phoneNumber) throws EmployeeException;

    @Select("SELECT 1 FROM Employee WHERE email = #{email} LIMIT 1")
    Optional<Boolean> existsByEmail(String email) throws EmployeeException;

    @Select("SELECT 1 FROM Employee WHERE phoneNumber = #{phoneNumber} LIMIT 1")
    Optional<Boolean> existsByPhoneNumber(String phoneNumber) throws EmployeeException;

    @Insert("INSERT INTO Employee (fullName, dateOfBirth, gender, address, phoneNumber, email, password) VALUES (#{fullName}, #{dateOfBirth}, #{gender}, #{address}, #{phoneNumber}, #{email}, #{password})")
    int insertEmployee(@Valid RequestEmployeeSignup employee) throws EmployeeException;

    @Update("UPDATE Employee SET address = #{address}, phoneNumber = #{phoneNumber}, email = #{email}, password = #{password} WHERE employeeID = #{employeeID}")
    int updateEmployee(RequestUpdate requestUpdate) throws EmployeeException;

    @Update("UPDATE Employee SET avatar = #{mediaId} WHERE employeeID = #{employeeID}")
    int updateAvatar(Long employeeID, int mediaId) throws EmployeeException;

    @Update("UPDATE Employee SET password = #{newPassword} WHERE employeeID = #{employeeID}")
    int resetPassword(RequestResetPassword requestResetPassword) throws EmployeeException, SQLIntegrityConstraintViolationException;
}