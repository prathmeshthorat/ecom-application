package com.ecom.application.common.model;

import com.ecom.application.common.constants.OrderStatus;

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
