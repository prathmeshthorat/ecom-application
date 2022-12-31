package com.ecom.application.order_inventory_service.order;


public enum OrderStatus {

    CREATED,
    INVOICE_CREATED,
    PAID,
    WAITING_SHIPMENT,
    SHIPPED,
    COMPLETE,
    IN_PREPARATION,
    OUT_FOR_DELIVERY,
    DELIVERED

}
