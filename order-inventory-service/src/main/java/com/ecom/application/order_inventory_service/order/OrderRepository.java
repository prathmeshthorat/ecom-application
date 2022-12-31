package com.ecom.application.order_inventory_service.order;

import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderRepository extends JpaRepository<Order, Long> {

    boolean existsByCartIdIgnoreCase(String cartId);

}
