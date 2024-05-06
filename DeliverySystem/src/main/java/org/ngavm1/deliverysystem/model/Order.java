package org.ngavm1.deliverysystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private Long orderID;
    private Long customerID;
    private Long storeID;
    private Long employeeID;
    private String origin;
    private String destination;
    private Date startShippingTime;
    private Date estimatedTime;
    private Double fee;
    private Boolean orderStatus;
    private Boolean payingStatus;
    private Date created_at;
    private Date updated_at;
}
