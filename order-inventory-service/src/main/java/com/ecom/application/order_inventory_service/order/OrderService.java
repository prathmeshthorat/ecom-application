package com.ecom.application.order_inventory_service.order;

import com.ecom.application.common.model.OrderDTO;
import com.ecom.application.order_inventory_service.util.NotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(final OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<OrderDTO> findAll() {
        final List<Order> orders = orderRepository.findAll(Sort.by("orderId"));
        return orders.stream()
                .map((order) -> mapToDTO(order, new OrderDTO()))
                .collect(Collectors.toList());
    }

    public OrderDTO get(final Long orderId) {
        return orderRepository.findById(orderId)
                .map(order -> mapToDTO(order, new OrderDTO()))
                .orElseThrow(() -> new NotFoundException());
    }

    public Long create(final OrderDTO orderDTO) {
        final Order order = new Order();
        mapToEntity(orderDTO, order);
        return orderRepository.save(order).getOrderId();
    }

    public void update(final Long orderId, final OrderDTO orderDTO) {
        final Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new NotFoundException());
        mapToEntity(orderDTO, order);
        orderRepository.save(order);
    }

    public void delete(final Long orderId) {
        orderRepository.deleteById(orderId);
    }

    private OrderDTO mapToDTO(final Order order, final OrderDTO orderDTO) {
        orderDTO.setOrderId(order.getOrderId());
        orderDTO.setCartId(order.getCartId());
        orderDTO.setCustomerId(order.getCustomerId());
        orderDTO.setShippingMenthod(order.getShippingMenthod());
        orderDTO.setPaymentMethod(order.getPaymentMethod());
        orderDTO.setTotalAmount(order.getTotalAmount());
        orderDTO.setIsPaid(order.getIsPaid());
        orderDTO.setStatus(order.getStatus());
        orderDTO.setComments(order.getComments());
        orderDTO.setTrackingNumber(order.getTrackingNumber());
        orderDTO.setOrderType(order.getOrderType());
        return orderDTO;
    }

    private Order mapToEntity(final OrderDTO orderDTO, final Order order) {
        order.setCartId(orderDTO.getCartId());
        order.setCustomerId(orderDTO.getCustomerId());
        order.setShippingMenthod(orderDTO.getShippingMenthod());
        order.setPaymentMethod(orderDTO.getPaymentMethod());
        order.setTotalAmount(orderDTO.getTotalAmount());
        order.setIsPaid(orderDTO.getIsPaid());
        order.setStatus(orderDTO.getStatus());
        order.setComments(orderDTO.getComments());
        order.setTrackingNumber(orderDTO.getTrackingNumber());
        order.setOrderType(orderDTO.getOrderType());
        return order;
    }

    public boolean cartIdExists(final String cartId) {
        return orderRepository.existsByCartIdIgnoreCase(cartId);
    }

}
