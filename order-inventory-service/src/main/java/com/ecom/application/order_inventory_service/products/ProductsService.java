package com.ecom.application.order_inventory_service.products;

import com.ecom.application.order_inventory_service.util.NotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class ProductsService {

    private final ProductsRepository productsRepository;

    public ProductsService(final ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    public List<ProductsDTO> findAll() {
        final List<Products> productss = productsRepository.findAll(Sort.by("productId"));
        return productss.stream()
                .map((products) -> mapToDTO(products, new ProductsDTO()))
                .collect(Collectors.toList());
    }

    public ProductsDTO get(final Long productId) {
        return productsRepository.findById(productId)
                .map(products -> mapToDTO(products, new ProductsDTO()))
                .orElseThrow(() -> new NotFoundException());
    }

    public Long create(final ProductsDTO productsDTO) {
        final Products products = new Products();
        mapToEntity(productsDTO, products);
        return productsRepository.save(products).getProductId();
    }

    public void update(final Long productId, final ProductsDTO productsDTO) {
        final Products products = productsRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException());
        mapToEntity(productsDTO, products);
        productsRepository.save(products);
    }

    public void delete(final Long productId) {
        productsRepository.deleteById(productId);
    }

    private ProductsDTO mapToDTO(final Products products, final ProductsDTO productsDTO) {
        productsDTO.setProductId(products.getProductId());
        productsDTO.setSku(products.getSku());
        productsDTO.setName(products.getName());
        productsDTO.setDescription(products.getDescription());
        productsDTO.setPrice(products.getPrice());
        productsDTO.setQuantityAvailable(products.getQuantityAvailable());
        productsDTO.setMaxOrderQuantity(products.getMaxOrderQuantity());
        productsDTO.setPurchacePrice(products.getPurchacePrice());
        productsDTO.setTax(products.getTax());
        productsDTO.setIsActive(products.getIsActive());
        productsDTO.setPromocodes(products.getPromocodes());
        return productsDTO;
    }

    private Products mapToEntity(final ProductsDTO productsDTO, final Products products) {
        products.setSku(productsDTO.getSku());
        products.setName(productsDTO.getName());
        products.setDescription(productsDTO.getDescription());
        products.setPrice(productsDTO.getPrice());
        products.setQuantityAvailable(productsDTO.getQuantityAvailable());
        products.setMaxOrderQuantity(productsDTO.getMaxOrderQuantity());
        products.setPurchacePrice(productsDTO.getPurchacePrice());
        products.setTax(productsDTO.getTax());
        products.setIsActive(productsDTO.getIsActive());
        products.setPromocodes(productsDTO.getPromocodes());
        return products;
    }

    public boolean skuExists(final String sku) {
        return productsRepository.existsBySkuIgnoreCase(sku);
    }

}
