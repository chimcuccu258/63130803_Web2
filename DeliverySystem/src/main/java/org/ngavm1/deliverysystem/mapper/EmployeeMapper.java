package org.ngavm1.deliverysystem.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.ngavm1.deliverysystem.exception.EmployeeException;
import org.ngavm1.deliverysystem.model.Employee;
import org.ngavm1.deliverysystem.payload.request.RequestEmployeeSignup;

import java.util.List;
import java.util.Optional;

@Mapper
public interface EmployeeMapper {

    @Select("SELECT * FROM Employee")
    List<Employee> findAllEmployee() throws EmployeeException;

    @Select("SELECT * FROM Employee WHERE employeeID= #{employeeID}")
    Employee findEmployeeById(Long employeeID) throws EmployeeException;

    @Select("SELECT * FROM Employee WHERE email= #{email}")
    Employee findEmployeeByEmail(String email) throws EmployeeException;

    @Insert("INSERT INTO Employee (fullName, address, phoneNumber, email, password) VALUES (#{fullName}, #{address}, #{phoneNumber}, #{email}, #{password})")
    int createAccount(RequestEmployeeSignup requestEmployeeSignup) throws EmployeeException;

    @Select("SELECT 1 FROM Employee WHERE email = #{email} LIMIT 1")
    Optional<Boolean> existsByEmail(String email) throws EmployeeException;
}
