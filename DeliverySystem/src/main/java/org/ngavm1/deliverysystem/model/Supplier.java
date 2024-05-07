package org.ngavm1.deliverysystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.ngavm1.deliverysystem.utils.SupplierStatus;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Supplier {
    private Long supplierID;
    private String supplierName;
    private String supplierCode;
    private SupplierStatus supplierStatus;
    private String email;
    private String address;
    private String phoneNumber;
    private String password;
    private Date created_at;
    private Date updated_at;
}
