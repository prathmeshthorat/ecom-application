package com.ecom.application.common.model;

import com.ecom.application.common.constants.OrderType;
import com.ecom.application.common.constants.PaymentMethod;
import com.ecom.application.common.constants.ShippingMethods;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class InvoiceDTO {

    private Long invoiceId;

    @NotNull
    private Long orderId;

    @NotNull
    private ShippingMethods shippingMethod;

    @NotNull
    private PaymentMethod paymentMethod;

    @NotNull
    private Double totalAmount;

    private OrderType type;

    private Boolean isPaid;

    @Size(max = 255)
    private String firstName;

    @Size(max = 255)
    private String lastName;

    @Size(max = 255)
    private String email;

    @Size(max = 255)
    private String phoneNumber;

    @NotNull
    private Long billingAddressId;

    @Size(max = 255)
    private String pdfUrl;

}
