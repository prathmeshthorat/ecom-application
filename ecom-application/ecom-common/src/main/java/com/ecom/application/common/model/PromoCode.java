package com.ecom.application.common.model;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PromoCode {

    @Size(max = 10)
    private String code;

    private Double discount;

}
