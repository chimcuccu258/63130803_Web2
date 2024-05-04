package org.ngavm1.deliverysystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class Employee {
    private Long employeeID;
    private String username;
    private String role;
    private String phoneNumber;
    private String email;
    private String password;
    private Date created_at;
    private Date updated_at;
}
