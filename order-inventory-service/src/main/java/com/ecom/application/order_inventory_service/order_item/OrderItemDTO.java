package com.ecom.application.order_inventory_service.order_item;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class OrderItemDTO {

    private Long itemId;

    @NotNull
    private Integer quantity;

    @NotNull
    @Size(max = 255)
    private String sku;

    private Double subTotal;

    private Double subTaxTotal;

    private Double subDiscountTotal;

    @NotNull
    private Long items;

}
