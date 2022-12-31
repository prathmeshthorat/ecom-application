package com.ecom.application.shipment_invoice_service.shipment;

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
@RequestMapping(value = "/api/shipments", produces = MediaType.APPLICATION_JSON_VALUE)
public class ShipmentResource {

    private final ShipmentService shipmentService;

    public ShipmentResource(final ShipmentService shipmentService) {
        this.shipmentService = shipmentService;
    }

    @GetMapping
    public ResponseEntity<List<ShipmentDTO>> getAllShipments() {
        return ResponseEntity.ok(shipmentService.findAll());
    }

    @GetMapping("/{shipmentId}")
    public ResponseEntity<ShipmentDTO> getShipment(@PathVariable final Long shipmentId) {
        return ResponseEntity.ok(shipmentService.get(shipmentId));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createShipment(@RequestBody @Valid final ShipmentDTO shipmentDTO) {
        return new ResponseEntity<>(shipmentService.create(shipmentDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{shipmentId}")
    public ResponseEntity<Void> updateShipment(@PathVariable final Long shipmentId,
            @RequestBody @Valid final ShipmentDTO shipmentDTO) {
        shipmentService.update(shipmentId, shipmentDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{shipmentId}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteShipment(@PathVariable final Long shipmentId) {
        shipmentService.delete(shipmentId);
        return ResponseEntity.noContent().build();
    }

}
