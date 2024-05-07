package org.ngavm1.deliverysystem.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.ngavm1.deliverysystem.exception.EmployeeException;
import org.ngavm1.deliverysystem.model.Employee;
import org.ngavm1.deliverysystem.payload.request.RequestEmployeeSignup;
import org.ngavm1.deliverysystem.payload.request.RequestResetPassword;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;

@Mapper
public interface EmployeeMapper {

    @Select("SELECT * FROM Employee")
    List<Employee> findAllEmployee() throws EmployeeException;

    @Select("SELECT * FROM Employee WHERE fullName = #{fullName}")
    Employee findEmployeeByFullName(String fullName) throws EmployeeException;

    @Select("SELECT * FROM Employee WHERE email= #{email}")
    Employee findEmployeeByEmail(String email) throws EmployeeException;

    @Select("SELECT 1 FROM Employee WHERE email = #{email} LIMIT 1")
    Optional<Boolean> existsByEmail(String email) throws EmployeeException;

    @Insert("INSERT INTO Employee (fullName, dateOfBirth, gender, address, phoneNumber, email, password) VALUES (#{fullName}, #{dateOfBirth}, #{gender}, #{address}, #{phoneNumber}, #{email}, #{password})")
    int insertEmployee(Employee employee) throws EmployeeException;

    @Update("UPDATE Employee SET address = #{address}, phoneNumber = #{phoneNumber}, password = #{password} WHERE email = #{email}")
    int updateEmployee(Employee employee) throws EmployeeException;

    @Update("UPDATE Employee SET avatar = #{mediaId} WHERE employeeID = #{employeeID}")
    int updateAvatar(Long employeeID, int mediaId) throws EmployeeException;

    @Update("UPDATE Employee SET password = #{newPassword} WHERE employeeID = #{employeeID}")
    int changePassword(RequestResetPassword requestResetPassword) throws EmployeeException, SQLIntegrityConstraintViolationException;
}