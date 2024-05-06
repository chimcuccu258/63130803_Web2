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
public class Customer {
    private Long customerID;
    private String fullName;
    private String address;
    private String phoneNumber;
    private Date created_at;
    private Date updated_at;
}
