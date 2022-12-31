package com.ecom.application.order_inventory_service.order_item;

import com.ecom.application.common.model.OrderItemDTO;
import com.ecom.application.order_inventory_service.order.Order;
import com.ecom.application.order_inventory_service.order.OrderRepository;
import com.ecom.application.order_inventory_service.util.NotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;

    public OrderItemService(final OrderItemRepository orderItemRepository,
            final OrderRepository orderRepository) {
        this.orderItemRepository = orderItemRepository;
        this.orderRepository = orderRepository;
    }

    public List<OrderItemDTO> findAll() {
        final List<OrderItem> orderItems = orderItemRepository.findAll(Sort.by("itemId"));
        return orderItems.stream()
                .map((orderItem) -> mapToDTO(orderItem, new OrderItemDTO()))
                .collect(Collectors.toList());
    }

    public OrderItemDTO get(final Long itemId) {
        return orderItemRepository.findById(itemId)
                .map(orderItem -> mapToDTO(orderItem, new OrderItemDTO()))
                .orElseThrow(() -> new NotFoundException());
    }

    public Long create(final OrderItemDTO orderItemDTO) {
        final OrderItem orderItem = new OrderItem();
        mapToEntity(orderItemDTO, orderItem);
        return orderItemRepository.save(orderItem).getItemId();
    }

    public void update(final Long itemId, final OrderItemDTO orderItemDTO) {
        final OrderItem orderItem = orderItemRepository.findById(itemId)
                .orElseThrow(() -> new NotFoundException());
        mapToEntity(orderItemDTO, orderItem);
        orderItemRepository.save(orderItem);
    }

    public void delete(final Long itemId) {
        orderItemRepository.deleteById(itemId);
    }

    private OrderItemDTO mapToDTO(final OrderItem orderItem, final OrderItemDTO orderItemDTO) {
        orderItemDTO.setItemId(orderItem.getItemId());
        orderItemDTO.setQuantity(orderItem.getQuantity());
        orderItemDTO.setSku(orderItem.getSku());
        orderItemDTO.setSubTotal(orderItem.getSubTotal());
        orderItemDTO.setSubTaxTotal(orderItem.getSubTaxTotal());
        orderItemDTO.setSubDiscountTotal(orderItem.getSubDiscountTotal());
        orderItemDTO.setItems(orderItem.getItems() == null ? null : orderItem.getItems().getOrderId());
        return orderItemDTO;
    }

    private OrderItem mapToEntity(final OrderItemDTO orderItemDTO, final OrderItem orderItem) {
        orderItem.setQuantity(orderItemDTO.getQuantity());
        orderItem.setSku(orderItemDTO.getSku());
        orderItem.setSubTotal(orderItemDTO.getSubTotal());
        orderItem.setSubTaxTotal(orderItemDTO.getSubTaxTotal());
        orderItem.setSubDiscountTotal(orderItemDTO.getSubDiscountTotal());
        final Order items = orderItemDTO.getItems() == null ? null : orderRepository.findById(orderItemDTO.getItems())
                .orElseThrow(() -> new NotFoundException("items not found"));
        orderItem.setItems(items);
        return orderItem;
    }

}
