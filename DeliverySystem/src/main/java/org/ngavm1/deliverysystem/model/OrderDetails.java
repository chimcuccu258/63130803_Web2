package org.ngavm1.deliverysystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetails {
private Long orderID;
    private Long productID;
    private Integer quantity;
    private Double price;
    private Double total;
    private String status;
    private String note;
    private String created_at;
    private String updated_at;
}
