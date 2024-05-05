package org.ngavm1.deliverysystem.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.ngavm1.deliverysystem.exception.CustomerException;
import org.ngavm1.deliverysystem.model.Customer;

import java.util.List;

@Mapper
public interface CustomerMapper {

    @Select("SELECT * FROM Customer WHERE email= #{email} AND password= #{password}")
    Customer customerLogin(String email, String password) throws CustomerException;

    @Insert("INSERT INTO Customer (fullName, address, phoneNumber, email, password, created_at, updated_at) VALUES (#{fullName}, #{address}, #{phoneNumber}, #{email}, #{password}, #{created_at}, #{updated_at})")
    int customerRegister(Customer customer) throws CustomerException;

    @Update("UPDATE Customer SET fullName= #{fullName}, address= #{address}, phoneNumber= #{phoneNumber}, email= #{email}, password= #{password}, updated_at= #{updated_at} WHERE customerID= #{customerID}")
    int customerUpdateInformation(Customer customer) throws CustomerException;

    @Select("SELECT * FROM Customer")
    List<Customer> findAllCustomer() throws CustomerException;


}
