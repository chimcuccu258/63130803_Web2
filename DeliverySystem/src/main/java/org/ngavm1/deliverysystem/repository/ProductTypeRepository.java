package org.ngavm1.deliverysystem.repository;

import lombok.RequiredArgsConstructor;
import org.ngavm1.deliverysystem.exception.ProductTypeException;
import org.ngavm1.deliverysystem.exception.SupplierException;
import org.ngavm1.deliverysystem.mapper.ProductTypeMapper;
import org.ngavm1.deliverysystem.model.ProductType;
import org.ngavm1.deliverysystem.model.Supplier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductTypeRepository {
    private final ProductTypeMapper productTypeMapper;

    public List<ProductType> findAllProductType() throws ProductTypeException {
        return productTypeMapper.findAllProductType();
    }
}
