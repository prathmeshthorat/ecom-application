package com.ecom.application.shipment_invoice_service.shipment;

import org.springframework.data.jpa.repository.JpaRepository;


public interface ShipmentRepository extends JpaRepository<Shipment, Long> {

    boolean existsByOrderId(Long orderId);

    boolean existsByTrackingIdIgnoreCase(String trackingId);

}
