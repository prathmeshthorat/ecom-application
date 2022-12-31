package com.ecom.application.shipment_invoice_service.shipment;

import com.ecom.application.common.model.ShipmentDTO;
import com.ecom.application.shipment_invoice_service.util.NotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class ShipmentService {

    private final ShipmentRepository shipmentRepository;

    public ShipmentService(final ShipmentRepository shipmentRepository) {
        this.shipmentRepository = shipmentRepository;
    }

    public List<ShipmentDTO> findAll() {
        final List<Shipment> shipments = shipmentRepository.findAll(Sort.by("shipmentId"));
        return shipments.stream()
                .map((shipment) -> mapToDTO(shipment, new ShipmentDTO()))
                .collect(Collectors.toList());
    }

    public ShipmentDTO get(final Long shipmentId) {
        return shipmentRepository.findById(shipmentId)
                .map(shipment -> mapToDTO(shipment, new ShipmentDTO()))
                .orElseThrow(() -> new NotFoundException());
    }

    public Long create(final ShipmentDTO shipmentDTO) {
        final Shipment shipment = new Shipment();
        mapToEntity(shipmentDTO, shipment);
        return shipmentRepository.save(shipment).getShipmentId();
    }

    public void update(final Long shipmentId, final ShipmentDTO shipmentDTO) {
        final Shipment shipment = shipmentRepository.findById(shipmentId)
                .orElseThrow(() -> new NotFoundException());
        mapToEntity(shipmentDTO, shipment);
        shipmentRepository.save(shipment);
    }

    public void delete(final Long shipmentId) {
        shipmentRepository.deleteById(shipmentId);
    }

    private ShipmentDTO mapToDTO(final Shipment shipment, final ShipmentDTO shipmentDTO) {
        shipmentDTO.setShipmentId(shipment.getShipmentId());
        shipmentDTO.setOrderId(shipment.getOrderId());
        shipmentDTO.setStatus(shipment.getStatus());
        shipmentDTO.setParcelServiceName(shipment.getParcelServiceName());
        shipmentDTO.setPaymentMethod(shipment.getPaymentMethod());
        shipmentDTO.setOrderPrice(shipment.getOrderPrice());
        shipmentDTO.setTrackingId(shipment.getTrackingId());
        shipmentDTO.setDeliveryDate(shipment.getDeliveryDate());
        return shipmentDTO;
    }

    private Shipment mapToEntity(final ShipmentDTO shipmentDTO, final Shipment shipment) {
        shipment.setOrderId(shipmentDTO.getOrderId());
        shipment.setStatus(shipmentDTO.getStatus());
        shipment.setParcelServiceName(shipmentDTO.getParcelServiceName());
        shipment.setPaymentMethod(shipmentDTO.getPaymentMethod());
        shipment.setOrderPrice(shipmentDTO.getOrderPrice());
        shipment.setTrackingId(shipmentDTO.getTrackingId());
        shipment.setDeliveryDate(shipmentDTO.getDeliveryDate());
        return shipment;
    }

    public boolean orderIdExists(final Long orderId) {
        return shipmentRepository.existsByOrderId(orderId);
    }

    public boolean trackingIdExists(final String trackingId) {
        return shipmentRepository.existsByTrackingIdIgnoreCase(trackingId);
    }

}
