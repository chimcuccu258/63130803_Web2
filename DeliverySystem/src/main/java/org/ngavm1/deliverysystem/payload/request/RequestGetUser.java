package org.ngavm1.deliverysystem.payload.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestGetUser {
    private String fullName;
    private String email;
    private String phoneNumber;
}
