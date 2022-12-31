package com.ecom.application.common.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import com.ecom.application.common.constants.OrderStatus;
import com.ecom.application.common.constants.PaymentMethod;
import com.ecom.application.common.constants.ShippingMethods;


@Getter
@Setter
public class ShipmentDTO {

    private Long shipmentId;

    @NotNull
    private Long orderId;

    @NotNull
    private OrderStatus status;

    @NotNull
    private ShippingMethods parcelServiceName;

    @NotNull
    private PaymentMethod paymentMethod;

    @NotNull
    private Double orderPrice;

    @Size(max = 255)
    private String trackingId;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime deliveryDate;

}
