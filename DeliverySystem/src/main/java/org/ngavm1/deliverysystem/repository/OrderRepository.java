package org.ngavm1.deliverysystem.repository;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.ngavm1.deliverysystem.exception.OrderException;
import org.ngavm1.deliverysystem.mapper.OrderDetailMapper;
import org.ngavm1.deliverysystem.mapper.OrderMapper;
import org.ngavm1.deliverysystem.model.Order;
import org.ngavm1.deliverysystem.model.OrderDetails;
import org.ngavm1.deliverysystem.utils.OrderStatus;
import org.ngavm1.deliverysystem.utils.PayingStatus;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {
    private final OrderMapper orderMapper;
    private final OrderDetailMapper orderDetailMapper;

    public List<Order> findAllOrder() throws OrderException {
        return orderMapper.findAllOrder();
    }

    public Order findOrderById(Long orderID) throws OrderException {
        return orderMapper.findOrderById(orderID);
    }

    public List<Order> findOrderByStatus(OrderStatus orderStatus) throws OrderException {
        return orderMapper.findOrderByStatus(orderStatus);
    }

    public List<Order> findOrderByPayingStatus(PayingStatus payingStatus) throws OrderException {
        return orderMapper.findOrderByPayingStatus(payingStatus);
    }

    public List<Order> findOrderByCustomerID(Long customerID) throws OrderException {
        return orderMapper.findOrderByCustomerID(customerID);
    }

    public List<Order> findOrderByEmployeeID(Long employeeID) throws OrderException {
        return orderMapper.findOrderByEmployeeID(employeeID);
    }

    public List<Order> findOrderByStoreID(Long storeID) throws OrderException {
        return orderMapper.findOrderByStoreID(storeID);
    }

    public int updateOrderStatus(OrderStatus orderStatus, Long orderID) throws OrderException {
        return orderMapper.updateOrderStatus(orderStatus, orderID);
    }

    public int updatePayingStatus(PayingStatus payingStatus, Long orderID) throws OrderException {
        return orderMapper.updatePayingStatus(payingStatus, orderID);
    }

    public int createOrder(Order order) throws OrderException {
        return orderMapper.createOrder(order);
    }

    public Order findOrderByCustomerIDStoreIDEmployeeIDStartShippingTimeFee(Long customerID, Long storeID, Long employeeID, Date startShippingTime, Double fee) throws OrderException {
        return orderMapper.findOrderByCustomerIDStoreIDEmployeeIDStartShippingTimeFee(customerID, storeID, employeeID, startShippingTime, fee);
    }

    public int createOrderDetail(OrderDetails orderDetails) throws OrderException {
        return orderDetailMapper.createOrderDetail(orderDetails);
    }
}