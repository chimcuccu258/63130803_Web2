package org.ngavm1.deliverysystem.payload.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestSupplierUpdate {
    @NotNull
    @NotEmpty
    @NotBlank
    private Long supplierID;
    private String supplierName;
    private String address;
    private String phoneNumber;
}
