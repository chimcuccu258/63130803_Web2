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
public class Vehicle {
    private Long vehicleID;
    private Boolean type;
    private Double capacity;
    private Boolean availabilityStatus;
    private Date created_at;
    private Date updated_at;
}
