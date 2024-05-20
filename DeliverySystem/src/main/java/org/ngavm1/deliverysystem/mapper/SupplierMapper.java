package org.ngavm1.deliverysystem.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.ngavm1.deliverysystem.exception.SupplierException;
import org.ngavm1.deliverysystem.model.Supplier;
import org.ngavm1.deliverysystem.payload.request.RequestResetPassword;
import org.ngavm1.deliverysystem.payload.request.RequestSupplierSignup;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;

@Mapper
public interface SupplierMapper {
    @Select("SELECT * FROM Supplier")
    List<Supplier> findAllSupplier() throws SupplierException;

    @Select("SELECT * FROM Supplier WHERE supplierCode = #{supplierCode}")
    Supplier findSupplierBySupplierCode(String supplierCode) throws SupplierException;

    @Select("SELECT * FROM Supplier WHERE phoneNumber = #{phoneNumber}")
    Supplier findSupplierByPhoneNumber(String phoneNumber) throws SupplierException;

    @Select("SELECT * FROM Supplier WHERE supplierName = #{supplierName}")
    Supplier findSupplierByName(String supplierName) throws SupplierException;

    @Select("SELECT * FROM Supplier WHERE email = #{email}")
    Supplier findSupplierByEmail(String email) throws SupplierException;

    @Select("SELECT 1 FROM Supplier WHERE email = #{email} LIMIT 1")
    Optional<Boolean> existsByEmail(String email) throws SupplierException;

    @Select("SELECT 1 FROM Supplier WHERE phoneNumber = #{phoneNumber} LIMIT 1")
    Optional<Boolean> existsByPhoneNumber(String phoneNumber) throws SupplierException;

    @Insert("INSERT INTO Supplier (supplierName, email, address, phoneNumber, password) VALUES (#{supplierName}, #{email}, #{address}, #{phoneNumber}, #{password})")
    int insertSupplier(RequestSupplierSignup Supplier) throws SupplierException;

    @Update("UPDATE Supplier SET address = #{address}, phoneNumber = #{phoneNumber}, password = #{password} WHERE email = #{email}")
    int updateSupplier(Supplier Supplier) throws SupplierException;

    @Update("UPDATE Supplier SET password = #{newPassword} WHERE supplierID = #{supplierID}")
    int resetPassword(RequestResetPassword requestResetPassword) throws SupplierException, SQLIntegrityConstraintViolationException;
}
