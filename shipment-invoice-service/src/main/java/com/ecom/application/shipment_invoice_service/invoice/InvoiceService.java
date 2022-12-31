package com.ecom.application.shipment_invoice_service.invoice;

import com.ecom.application.common.model.InvoiceDTO;
import com.ecom.application.shipment_invoice_service.util.NotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;

    public InvoiceService(final InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    public List<InvoiceDTO> findAll() {
        final List<Invoice> invoices = invoiceRepository.findAll(Sort.by("invoiceId"));
        return invoices.stream()
                .map((invoice) -> mapToDTO(invoice, new InvoiceDTO()))
                .collect(Collectors.toList());
    }

    public InvoiceDTO get(final Long invoiceId) {
        return invoiceRepository.findById(invoiceId)
                .map(invoice -> mapToDTO(invoice, new InvoiceDTO()))
                .orElseThrow(() -> new NotFoundException());
    }

    public Long create(final InvoiceDTO invoiceDTO) {
        final Invoice invoice = new Invoice();
        mapToEntity(invoiceDTO, invoice);
        return invoiceRepository.save(invoice).getInvoiceId();
    }

    public void update(final Long invoiceId, final InvoiceDTO invoiceDTO) {
        final Invoice invoice = invoiceRepository.findById(invoiceId)
                .orElseThrow(() -> new NotFoundException());
        mapToEntity(invoiceDTO, invoice);
        invoiceRepository.save(invoice);
    }

    public void delete(final Long invoiceId) {
        invoiceRepository.deleteById(invoiceId);
    }

    private InvoiceDTO mapToDTO(final Invoice invoice, final InvoiceDTO invoiceDTO) {
        invoiceDTO.setInvoiceId(invoice.getInvoiceId());
        invoiceDTO.setOrderId(invoice.getOrderId());
        invoiceDTO.setShippingMethod(invoice.getShippingMethod());
        invoiceDTO.setPaymentMethod(invoice.getPaymentMethod());
        invoiceDTO.setTotalAmount(invoice.getTotalAmount());
        invoiceDTO.setType(invoice.getType());
        invoiceDTO.setIsPaid(invoice.getIsPaid());
        invoiceDTO.setFirstName(invoice.getFirstName());
        invoiceDTO.setLastName(invoice.getLastName());
        invoiceDTO.setEmail(invoice.getEmail());
        invoiceDTO.setPhoneNumber(invoice.getPhoneNumber());
        invoiceDTO.setBillingAddressId(invoice.getBillingAddressId());
        invoiceDTO.setPdfUrl(invoice.getPdfUrl());
        return invoiceDTO;
    }

    private Invoice mapToEntity(final InvoiceDTO invoiceDTO, final Invoice invoice) {
        invoice.setOrderId(invoiceDTO.getOrderId());
        invoice.setShippingMethod(invoiceDTO.getShippingMethod());
        invoice.setPaymentMethod(invoiceDTO.getPaymentMethod());
        invoice.setTotalAmount(invoiceDTO.getTotalAmount());
        invoice.setType(invoiceDTO.getType());
        invoice.setIsPaid(invoiceDTO.getIsPaid());
        invoice.setFirstName(invoiceDTO.getFirstName());
        invoice.setLastName(invoiceDTO.getLastName());
        invoice.setEmail(invoiceDTO.getEmail());
        invoice.setPhoneNumber(invoiceDTO.getPhoneNumber());
        invoice.setBillingAddressId(invoiceDTO.getBillingAddressId());
        invoice.setPdfUrl(invoiceDTO.getPdfUrl());
        return invoice;
    }

    public boolean orderIdExists(final Long orderId) {
        return invoiceRepository.existsByOrderId(orderId);
    }

}
