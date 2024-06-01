package org.ngavm1.deliverysystem.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.ngavm1.deliverysystem.exception.OrderException;
import org.ngavm1.deliverysystem.model.Order;
import org.ngavm1.deliverysystem.utils.OrderStatus;
import org.ngavm1.deliverysystem.utils.PayingStatus;

import java.util.List;

@Mapper
public interface OrderMapper {
    @Select("SELECT * FROM Order")
    List<Order> findAllOrder() throws OrderException;

    @Select("SELECT * FROM Order WHERE orderID = #{orderID}")
    Order findOrderById(Long orderID) throws OrderException;

    @Select("SELECT * FROM Order WHERE orderStatus = #{orderStatus}")
    List<Order> findOrderByStatus(OrderStatus orderStatus) throws OrderException;

    @Select("SELECT * FROM Order WHERE PayingStatus = #{payingStatus}")
    List<Order> findOrderByPayingStatus(PayingStatus payingStatus) throws OrderException;

    @Select("SELECT * FROM Order WHERE customerID = #{customerID}")
    List<Order> findOrderByCustomerID(Long customerID) throws OrderException;

    @Select("SELECT * FROM Order WHERE employeeID = #{employeeID}")
    List<Order> findOrderByEmployeeID(Long employeeID) throws OrderException;

    @Select("SELECT * FROM Order WHERE storeID = #{storeID}")
    List<Order> findOrderByStoreID(Long storeID) throws OrderException;

    //update order status
    @Update("UPDATE Order SET orderStatus = #{orderStatus} WHERE orderID = #{orderID}")
    int updateOrderStatus(OrderStatus orderStatus, Long orderID) throws OrderException;

    @Insert("INSERT INTO Order (customerID, storeID, employeeID, origin, destination, " +
            "startShippingTime, estimatedTime, fee, orderStatus, payingStatus) " +
            "VALUES (#{customerID}, #{storeID}, #{employeeID}, #{origin}, #{destination}, " +
            "#{startShippingTime}, #{estimatedTime}, #{fee}, #{orderStatus}, #{payingStatus})")
    int createOrder(Order order) throws OrderException;
}