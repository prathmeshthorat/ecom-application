package com.ecom.application.order_inventory_service.order;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class OrderDTO {

    private Long orderId;

    @NotNull
    @Size(max = 255)
    private String cartId;

    private Long customerId;

    private ShippingMethods shippingMenthod;

    private PaymentMethod paymentMethod;

    private Double totalAmount;

    @NotNull
    private Boolean isPaid;

    private OrderStatus status;

    @Valid
    private Comments comments;

    @Size(max = 255)
    private String trackingNumber;

    @NotNull
    private OrderType orderType;

}
