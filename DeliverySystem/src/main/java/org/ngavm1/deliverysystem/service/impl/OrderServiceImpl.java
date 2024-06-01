package org.ngavm1.deliverysystem.service.impl;

import lombok.RequiredArgsConstructor;
import org.ngavm1.deliverysystem.exception.CustomerException;
import org.ngavm1.deliverysystem.exception.EmployeeException;
import org.ngavm1.deliverysystem.exception.OrderException;
import org.ngavm1.deliverysystem.model.Customer;
import org.ngavm1.deliverysystem.model.Employee;
import org.ngavm1.deliverysystem.model.Order;
import org.ngavm1.deliverysystem.payload.request.RequestCreateOrder;
import org.ngavm1.deliverysystem.payload.response.ResponseModel;
import org.ngavm1.deliverysystem.repository.CustomerRepository;
import org.ngavm1.deliverysystem.repository.EmployeeRepository;
import org.ngavm1.deliverysystem.repository.OrderRepository;
import org.ngavm1.deliverysystem.service.OrderService;
import org.ngavm1.deliverysystem.utils.OrderStatus;
import org.ngavm1.deliverysystem.utils.PayingStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public ResponseEntity<ResponseModel> findAllOrder() throws OrderException {
        List<Order> orders = orderRepository.findAllOrder();
        ResponseModel response;

        if (orders.isEmpty()) {
            response = new ResponseModel("204", "No orders found", null);
            return ResponseEntity.status(204).body(response);
        } else {
            response = new ResponseModel("200", "Orders found", orders);
            return ResponseEntity.status(200).body(response);
        }
    }

    @Override
    public ResponseEntity<ResponseModel> findOrderById(Long orderID) throws OrderException {
        Order order = orderRepository.findOrderById(orderID);
        ResponseModel response;

        if (order == null) {
            response = new ResponseModel("204", "Order not found", null);
            return ResponseEntity.status(204).body(response);
        } else {
            response = new ResponseModel("200", "Order found", order);
            return ResponseEntity.status(200).body(response);
        }
    }

    @Override
    public ResponseEntity<ResponseModel> findOrderByStatus(OrderStatus orderStatus) throws OrderException {
        List<Order> orders = orderRepository.findOrderByStatus(orderStatus);
        ResponseModel response;

        if (orders.isEmpty()) {
            response = new ResponseModel("204", "No orders found", null);
            return ResponseEntity.status(204).body(response);
        } else {
            response = new ResponseModel("200", "Orders found", orders);
            return ResponseEntity.status(200).body(response);
        }
    }

    @Override
    public ResponseEntity<ResponseModel> findOrderByPayingStatus(PayingStatus payingStatus) throws OrderException {
        List<Order> orders = orderRepository.findOrderByPayingStatus(payingStatus);
        ResponseModel response;

        if (orders.isEmpty()) {
            response = new ResponseModel("204", "No orders found", null);
            return ResponseEntity.status(204).body(response);
        } else {
            response = new ResponseModel("200", "Orders found", orders);
            return ResponseEntity.status(200).body(response);
        }
    }

    @Override
    public ResponseEntity<ResponseModel> findOrderByCustomer(String email) throws OrderException, CustomerException {
        Customer customer = customerRepository.findByEmail(email);
        List<Order> orders = orderRepository.findOrderByCustomerID(customer.getCustomerID());
        ResponseModel response;

        if (orders.isEmpty()) {
            response = new ResponseModel("204", "No orders found", null);
            return ResponseEntity.status(204).body(response);
        } else {
            response = new ResponseModel("200", "Orders found", orders);
            return ResponseEntity.status(200).body(response);
        }
    }

    @Override
    public ResponseEntity<ResponseModel> findOrderByEmployee(String email) throws OrderException, EmployeeException {
        Employee employee = employeeRepository.findEmployeeByEmail(email);
        List<Order> orders = orderRepository.findOrderByEmployeeID(employee.getEmployeeID());
        ResponseModel response;

        if (orders.isEmpty()) {
            response = new ResponseModel("204", "No orders found", null);
            return ResponseEntity.status(204).body(response);
        } else {
            response = new ResponseModel("200", "Orders found", orders);
            return ResponseEntity.status(200).body(response);
        }
    }

    @Override
    public ResponseEntity<ResponseModel> findOrderByStoreID(Long storeID) throws OrderException {
        List<Order> orders = orderRepository.findOrderByStoreID(storeID);
        ResponseModel response;

        if (orders.isEmpty()) {
            response = new ResponseModel("204", "No orders found", null);
            return ResponseEntity.status(204).body(response);
        } else {
            response = new ResponseModel("200", "Orders found", orders);
            return ResponseEntity.status(200).body(response);
        }
    }

    @Override
    public ResponseEntity<ResponseModel> updateOrderStatus(OrderStatus orderStatus, Long orderID) throws OrderException {
        int result = orderRepository.updateOrderStatus(orderStatus, orderID);
        ResponseModel response;

        if (result == 0) {
            response = new ResponseModel("204", "Order not found", null);
            return ResponseEntity.status(204).body(response);
        } else {
            response = new ResponseModel("200", "Order status updated", null);
            return ResponseEntity.status(200).body(response);
        }
    }

    @Override
    public ResponseEntity<ResponseModel> createOrder(RequestCreateOrder requestCreateOrder, String emailEmployee) throws OrderException, CustomerException, EmployeeException {
        String customerFullName = requestCreateOrder.getCustomerFullName();
        String customerAddress = requestCreateOrder.getAddressTo();
        String customerPhoneNumber = requestCreateOrder.getCustomerPhoneNumber();

        Customer customer = customerRepository.findCustomerByFullNameAndAddressAndPhoneNumber(
                customerFullName,
                customerAddress,
                customerPhoneNumber);

        Long customerID;

        if (customer == null) {
            customer = new Customer();
            customer.setFullName(customerFullName);
            customer.setAddress(customerAddress);
            customer.setPhoneNumber(customerPhoneNumber);
            int insert = customerRepository.createCustomer(customer);

            if (insert == 0) {
                return ResponseEntity.status(500).body(new ResponseModel("500", "Failed to create customer", null));
            } else {
                Customer customer1 = customerRepository.findCustomerByFullNameAndAddressAndPhoneNumber(
                        customerFullName,
                        customerAddress,
                        customerPhoneNumber);

                customerID = customer1.getCustomerID();
            }
        } else {
            customerID = customer.getCustomerID();
        }

        Employee employee = employeeRepository.findEmployeeByEmail(emailEmployee);

        if (employee == null) {
            return ResponseEntity.status(404).body(new ResponseModel("404", "Employee not found", null));
        }

        Long employeeId = employee.getEmployeeID();

        Order order = new Order();
        order.setCustomerID(customerID);
        order.setStoreID(requestCreateOrder.getStoreID());
        order.setEmployeeID(employeeId);
        order.setOrigin(requestCreateOrder.getAddressFrom());
        order.setDestination(requestCreateOrder.getAddressTo());
        order.setStartShippingTime(requestCreateOrder.getStartShippingTime());
        order.setEstimatedTime(requestCreateOrder.getEstimatedTime());
        order.setFee(requestCreateOrder.getFee());
        order.setOrderStatus(OrderStatus.PENDING);
        order.setPayingStatus(requestCreateOrder.getPayingStatus());

        int insert = orderRepository.createOrder(order);

        if (insert == 0) {
            return ResponseEntity.status(500).body(new ResponseModel("500", "Failed to create order", null));
        } else {
            return ResponseEntity.status(201).body(new ResponseModel("201", "Order created", null));
        }
    }
}