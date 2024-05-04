package org.ngavm1.deliverysystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class OderEmployee {
    private Long orderID;
    private Long employeeID;
    private Date created_at;
    private Date updated_at;
}
