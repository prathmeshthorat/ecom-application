package com.ecom.application.order_inventory_service.order_item;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/orderItems", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderItemResource {

    private final OrderItemService orderItemService;

    public OrderItemResource(final OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    @GetMapping
    public ResponseEntity<List<OrderItemDTO>> getAllOrderItems() {
        return ResponseEntity.ok(orderItemService.findAll());
    }

    @GetMapping("/{itemId}")
    public ResponseEntity<OrderItemDTO> getOrderItem(@PathVariable final Long itemId) {
        return ResponseEntity.ok(orderItemService.get(itemId));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createOrderItem(
            @RequestBody @Valid final OrderItemDTO orderItemDTO) {
        return new ResponseEntity<>(orderItemService.create(orderItemDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{itemId}")
    public ResponseEntity<Void> updateOrderItem(@PathVariable final Long itemId,
            @RequestBody @Valid final OrderItemDTO orderItemDTO) {
        orderItemService.update(itemId, orderItemDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{itemId}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteOrderItem(@PathVariable final Long itemId) {
        orderItemService.delete(itemId);
        return ResponseEntity.noContent().build();
    }

}
