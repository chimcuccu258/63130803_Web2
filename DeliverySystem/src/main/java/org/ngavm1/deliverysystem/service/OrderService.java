package org.ngavm1.deliverysystem.service;

import org.ngavm1.deliverysystem.exception.CustomerException;
import org.ngavm1.deliverysystem.exception.EmployeeException;
import org.ngavm1.deliverysystem.exception.OrderException;
import org.ngavm1.deliverysystem.payload.request.RequestCreateOrder;
import org.ngavm1.deliverysystem.payload.response.ResponseModel;
import org.ngavm1.deliverysystem.utils.OrderStatus;
import org.ngavm1.deliverysystem.utils.PayingStatus;
import org.springframework.http.ResponseEntity;

public interface OrderService {
    ResponseEntity<ResponseModel> findAllOrder() throws OrderException;

    ResponseEntity<ResponseModel> findOrderById(Long orderID) throws OrderException;

    ResponseEntity<ResponseModel> findOrderByStatus(OrderStatus orderStatus) throws OrderException;

    ResponseEntity<ResponseModel> findOrderByPayingStatus(PayingStatus payingStatus) throws OrderException;

    ResponseEntity<ResponseModel> findOrderByCustomer(String email) throws OrderException, CustomerException;

    ResponseEntity<ResponseModel> findOrderByEmployee(String email) throws OrderException, EmployeeException;

    ResponseEntity<ResponseModel> findOrderByStoreID(Long storeID) throws OrderException;

    ResponseEntity<ResponseModel> updateOrderStatus(OrderStatus orderStatus, Long orderID) throws OrderException;

    ResponseEntity<ResponseModel> updatePayingStatus(PayingStatus payingStatus, Long orderID) throws OrderException;

    ResponseEntity<ResponseModel> createOrder(RequestCreateOrder requestCreateOrder, String email) throws OrderException, CustomerException, EmployeeException;
}