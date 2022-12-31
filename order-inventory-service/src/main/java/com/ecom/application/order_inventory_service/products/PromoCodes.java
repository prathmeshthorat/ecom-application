package com.ecom.application.order_inventory_service.products;

import jakarta.validation.Valid;
import java.util.List;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PromoCodes {

    @Valid
    private List<PromoCode> promoCodes;

}
