package com.ecom.application.order_inventory_service.products;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ProductsDTO {

    private Long productId;

    @NotNull
    @Size(max = 255)
    private String sku;

    @NotNull
    @Size(max = 255)
    private String name;

    @NotNull
    @Size(max = 255)
    private String description;

    @NotNull
    private Double price;

    @NotNull
    private Integer quantityAvailable;

    @NotNull
    private Integer maxOrderQuantity;

    @NotNull
    private Double purchacePrice;

    @NotNull
    private Double tax;

    @NotNull
    private Boolean isActive;

    @Valid
    private PromoCodes promocodes;

}
