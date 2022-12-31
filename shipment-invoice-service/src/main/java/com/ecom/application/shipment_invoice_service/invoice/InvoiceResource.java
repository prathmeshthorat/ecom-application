package com.ecom.application.shipment_invoice_service.invoice;

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

import com.ecom.application.common.model.InvoiceDTO;


@RestController
@RequestMapping(value = "/api/invoices", produces = MediaType.APPLICATION_JSON_VALUE)
public class InvoiceResource {

    private final InvoiceService invoiceService;

    public InvoiceResource(final InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping
    public ResponseEntity<List<InvoiceDTO>> getAllInvoices() {
        return ResponseEntity.ok(invoiceService.findAll());
    }

    @GetMapping("/{invoiceId}")
    public ResponseEntity<InvoiceDTO> getInvoice(@PathVariable final Long invoiceId) {
        return ResponseEntity.ok(invoiceService.get(invoiceId));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createInvoice(@RequestBody @Valid final InvoiceDTO invoiceDTO) {
        return new ResponseEntity<>(invoiceService.create(invoiceDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{invoiceId}")
    public ResponseEntity<Void> updateInvoice(@PathVariable final Long invoiceId,
            @RequestBody @Valid final InvoiceDTO invoiceDTO) {
        invoiceService.update(invoiceId, invoiceDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{invoiceId}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteInvoice(@PathVariable final Long invoiceId) {
        invoiceService.delete(invoiceId);
        return ResponseEntity.noContent().build();
    }

}
