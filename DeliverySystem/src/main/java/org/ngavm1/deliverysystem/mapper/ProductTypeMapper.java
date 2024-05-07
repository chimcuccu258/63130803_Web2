package org.ngavm1.deliverysystem.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.ngavm1.deliverysystem.exception.ProductTypeException;
import org.ngavm1.deliverysystem.exception.SupplierException;
import org.ngavm1.deliverysystem.model.ProductType;
import org.ngavm1.deliverysystem.model.Supplier;

import java.util.List;

@Mapper
public interface ProductTypeMapper {
    @Select("SELECT * FROM ProductType")
    List<ProductType> findAllProductType() throws ProductTypeException;

    @Select("SELECT * FROM ProductType WHERE productTypeName = #{productTypeName}")
    Supplier findSupplierByProductTypeName(String productTypeName) throws ProductTypeException;

    @Insert("INSERT INTO ProductType (productTypeName) VALUES (#{productTypeName})")
    int insertProductType(ProductType productType) throws ProductTypeException;
}
