package com.ecom.application.common.model;

import com.ecom.application.common.constants.OrderStatus;
import com.ecom.application.common.constants.OrderType;
import com.ecom.application.common.constants.PaymentMethod;
import com.ecom.application.common.constants.ShippingMethods;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class OrderDTO {

    private Long orderId;

    private String cartId;

    private Long customerId;

    private ShippingMethods shippingMenthod;

    private PaymentMethod paymentMethod;

    private Double totalAmount;

    @NotNull
    private Boolean isPaid;
    
    @NotNull
    private OrderStatus status;

    @Valid
    private Comments comments;

    @Size(max = 255)
    private String trackingNumber;

    @NotNull
    private OrderType orderType;

}
