package org.ngavm1.deliverysystem.payload.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestSupplierSignup {
    @NotNull
    @NotEmpty
    @NotBlank
    private String supplierName;
    @NotNull
    @NotEmpty
    @NotBlank
    private String email;
    @NotNull
    @NotEmpty
    @NotBlank
    private String address;
    @NotNull
    @NotEmpty
    @NotBlank
    private String phoneNumber;
    @NotNull
    @NotEmpty
    @NotBlank
    private String password;
}
