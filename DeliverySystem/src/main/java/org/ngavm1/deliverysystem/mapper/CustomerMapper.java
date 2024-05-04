package org.ngavm1.deliverysystem.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.ngavm1.deliverysystem.model.Customer;

@Mapper
public interface CustomerMapper {
    @Select("SELECT * FROM Customer WHERE username = #{username}")
    Customer getUserByUsername(String username);

    @Select("SELECT * FROM Customer WHERE email = #{email}")
    Customer getUserByEmail(String email);

    @Select("SELECT * FROM Customer WHERE username = #{email} AND password = #{password}")
    Customer getUserByUsernameAndPassword(String email, String password);

}
