package org.ngavm1.deliverysystem.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.ngavm1.deliverysystem.exception.CustomerException;
import org.ngavm1.deliverysystem.model.Customer;

import java.util.List;

@Mapper
public interface CustomerMapper {
    @Select("SELECT * FROM Customer")
    List<Customer> findAllCustomer() throws CustomerException;

    @Select("SELECT * FROM Customer WHERE phoneNumber = #{phoneNumber}")
    Customer findCustomerByPhoneNumber(Long phoneNumber) throws CustomerException;

}
