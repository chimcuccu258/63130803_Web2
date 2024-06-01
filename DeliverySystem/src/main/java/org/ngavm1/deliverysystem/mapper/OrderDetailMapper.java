package org.ngavm1.deliverysystem.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.ngavm1.deliverysystem.model.OrderDetails;

@Mapper
public interface OrderDetailMapper {
    @Insert("INSERT INTO orderdetails(orderID, nameOfProduct, quantityOfProduct, priceOfProduct, noteOfProduct) " +
            "VALUES(#{orderID}, #{nameOfProduct}, #{quantityOfProduct}, #{priceOfProduct}, #{noteOfProduct})")
    int createOrderDetail(OrderDetails orderDetails);
}