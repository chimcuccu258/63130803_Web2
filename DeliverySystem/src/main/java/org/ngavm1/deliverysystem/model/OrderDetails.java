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
    private Long orderDetailID;
    private Long productTypeID;
    private String nameOfProduct;
    private Integer quantityOfProduct;
    private Double priceOfProduct;
    private String noteOfProduct;
    private String created_at;
    private String updated_at;
}