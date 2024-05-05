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
public class Product {
    private Long productID;
    private String name;
    private String description;
    private String quantityAvailable;
    private Double unitPrice;
    private Date created_at;
    private Date updated_at;
}
