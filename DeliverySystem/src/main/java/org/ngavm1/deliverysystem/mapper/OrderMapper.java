package org.ngavm1.deliverysystem.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.ngavm1.deliverysystem.exception.OrderException;
import org.ngavm1.deliverysystem.model.Order;
import org.ngavm1.deliverysystem.utils.OrderStatus;
import org.ngavm1.deliverysystem.utils.PayingStatus;

import java.util.Date;
import java.util.List;

@Mapper
public interface OrderMapper {
    @Select("SELECT * FROM `Order`")
    List<Order> findAllOrder() throws OrderException;

    @Select("SELECT * FROM `Order` WHERE orderID = #{orderID}")
    Order findOrderById(Long orderID) throws OrderException;

    @Select("SELECT * FROM `Order` WHERE orderStatus = #{orderStatus}")
    List<Order> findOrderByStatus(OrderStatus orderStatus) throws OrderException;

    @Select("SELECT * FROM `Order` WHERE PayingStatus = #{payingStatus}")
    List<Order> findOrderByPayingStatus(PayingStatus payingStatus) throws OrderException;

    @Select("SELECT * FROM `Order` WHERE customerID = #{customerID}")
    List<Order> findOrderByCustomerID(Long customerID) throws OrderException;

    @Select("SELECT * FROM `Order` WHERE employeeID = #{employeeID}")
    List<Order> findOrderByEmployeeID(Long employeeID) throws OrderException;

    @Select("SELECT * FROM `Order` WHERE supplierID = #{supplierID}")
    List<Order> findOrderBySupplierID(Long supplierID) throws OrderException;

    @Update("UPDATE `Order` SET orderStatus = #{orderStatus} WHERE orderID = #{orderID}")
    int updateOrderStatus(OrderStatus orderStatus, Long orderID) throws OrderException;

    @Update("UPDATE `Order` SET PayingStatus = #{payingStatus} WHERE orderID = #{orderID}")
    int updatePayingStatus(PayingStatus payingStatus, Long orderID) throws OrderException;

    @Insert("INSERT INTO `Order` (customerID, supplierID, employeeID, origin, destination, " +
            "startShippingTime, estimatedTime, fee, orderStatus, payingStatus) " +
            "VALUES (#{customerID}, #{supplierID}, #{employeeID}, #{origin}, #{destination}, " +
            "#{startShippingTime}, #{estimatedTime}, #{fee}, #{orderStatus}, #{payingStatus})")
    int createOrder(Order order) throws OrderException;

    @Select("SELECT * FROM `Order` WHERE customerID = #{customerID} AND supplierID = #{supplierID} AND employeeID = #{employeeID} " +
            "AND startShippingTime = #{startShippingTime} AND fee = #{fee}")
    Order findOrderByCustomerIDSupplierIDEmployeeIDStartShippingTimeFee(Long customerID, Long supplierID, Long employeeID,
                                                                     Date startShippingTime, Double fee) throws OrderException;
}