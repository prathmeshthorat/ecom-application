package com.ecom.application.order_inventory_service.order;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Comment {

    @Size(max = 255)
    private String commentText;

    private OrderStatus status;

}
