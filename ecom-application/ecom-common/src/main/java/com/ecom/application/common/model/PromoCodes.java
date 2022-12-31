package com.ecom.application.common.model;

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
