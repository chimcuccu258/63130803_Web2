package org.ngavm1.deliverysystem.payload.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.ngavm1.deliverysystem.utils.PayingStatus;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestCreateOrder {
    private String customerFullName;
    private String customerPhoneNumber;
    private Long storeID;
    private String addressFrom;
    private String addressTo;
    private Date startShippingTime;
    private Date estimatedTime;
    private Double fee;
    private PayingStatus payingStatus;
    private List<RequestCreateOrderDetail> orderDetails;
}