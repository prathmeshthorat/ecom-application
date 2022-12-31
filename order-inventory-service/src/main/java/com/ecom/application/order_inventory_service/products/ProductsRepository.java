package com.ecom.application.order_inventory_service.products;

import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductsRepository extends JpaRepository<Products, Long> {

    boolean existsBySkuIgnoreCase(String sku);

}
