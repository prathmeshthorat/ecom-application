package com.ecom.application.shipment_invoice_service.shipment;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;


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

    private LocalDateTime deliveryDate;

}
