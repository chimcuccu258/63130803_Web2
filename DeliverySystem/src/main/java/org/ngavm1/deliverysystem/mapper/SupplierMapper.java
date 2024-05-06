package org.ngavm1.deliverysystem.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.ngavm1.deliverysystem.exception.SupplierException;
import org.ngavm1.deliverysystem.model.Supplier;

import java.util.List;

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

}
