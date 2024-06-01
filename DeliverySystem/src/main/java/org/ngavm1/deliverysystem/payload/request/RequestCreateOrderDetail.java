package org.ngavm1.deliverysystem.payload.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RequestCreateOrderDetail {
    private String nameOfProduct;
    private int quantityOfProduct;
    private Double priceOfProduct;
    private String noteOfProduct;
}