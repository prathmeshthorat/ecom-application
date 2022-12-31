package com.ecom.application.order_inventory_service.order;

import jakarta.validation.Valid;
import java.util.List;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Comments {

    @Valid
    private List<Comment> comments;

}
