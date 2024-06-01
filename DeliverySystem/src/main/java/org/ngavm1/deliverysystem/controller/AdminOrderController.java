package org.ngavm1.deliverysystem.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.ngavm1.deliverysystem.exception.CustomerException;
import org.ngavm1.deliverysystem.exception.EmployeeException;
import org.ngavm1.deliverysystem.exception.OrderException;
import org.ngavm1.deliverysystem.payload.request.RequestCreateOrder;
import org.ngavm1.deliverysystem.payload.response.ResponseModel;
import org.ngavm1.deliverysystem.service.OrderService;
import org.ngavm1.deliverysystem.utils.OrderStatus;
import org.ngavm1.deliverysystem.utils.PayingStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/v1/manage-order")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AdminOrderController {
    private final OrderService orderService;

    @PostMapping("/all")
    @Operation(summary = "Find all orders")
    public ResponseEntity<ResponseModel> findAllOrder() throws OrderException {
        return orderService.findAllOrder();
    }

    @PostMapping("/status")
    @Operation(summary = "Find order by status")
    public ResponseEntity<ResponseModel> findOrderByStatus(@RequestParam OrderStatus orderStatus) throws OrderException {
        return orderService.findOrderByStatus(orderStatus);
    }

    @PostMapping("/paying-status")
    @Operation(summary = "Find order by paying status")
    public ResponseEntity<ResponseModel> findOrderByPayingStatus(@RequestParam PayingStatus payingStatus) throws OrderException {
        return orderService.findOrderByPayingStatus(payingStatus);
    }

    @PostMapping("/store")
    @Operation(summary = "Find order by store ID")
    public ResponseEntity<ResponseModel> findOrderByStoreID(@RequestParam Long storeID) throws OrderException {
        return orderService.findOrderByStoreID(storeID);
    }

    @GetMapping("/update-status")
    @Operation(summary = "Update order status")
    public ResponseEntity<ResponseModel> updateOrderStatus(@RequestParam OrderStatus orderStatus, @RequestParam Long orderID) throws OrderException {
        return orderService.updateOrderStatus(orderStatus, orderID);
    }

    @PostMapping("/create-order")
    @Operation(summary = "Create order")
    public ResponseEntity<ResponseModel> createOrder(@RequestBody RequestCreateOrder requestCreateOrder) throws
            OrderException, EmployeeException, CustomerException {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        String email = securityContext.getAuthentication().getName();
        return orderService.createOrder(requestCreateOrder, email);
    }

}
