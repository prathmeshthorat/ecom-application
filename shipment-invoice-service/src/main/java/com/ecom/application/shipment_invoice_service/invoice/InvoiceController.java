package com.ecom.application.shipment_invoice_service.invoice;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ecom.application.common.constants.OrderType;
import com.ecom.application.common.constants.PaymentMethod;
import com.ecom.application.common.constants.ShippingMethods;
import com.ecom.application.common.model.InvoiceDTO;
import com.ecom.application.shipment_invoice_service.util.WebUtils;

import jakarta.validation.Valid;


@Controller
@RequestMapping("/invoices")
public class InvoiceController {

    private final InvoiceService invoiceService;

    public InvoiceController(final InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @ModelAttribute
    public void prepareContext(final Model model) {
        model.addAttribute("shippingMethodValues", ShippingMethods.values());
        model.addAttribute("paymentMethodValues", PaymentMethod.values());
        model.addAttribute("typeValues", OrderType.values());
    }

    @GetMapping
    public String list(final Model model) {
        model.addAttribute("invoices", invoiceService.findAll());
        return "invoice/list";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute("invoice") final InvoiceDTO invoiceDTO) {
        return "invoice/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("invoice") @Valid final InvoiceDTO invoiceDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (!bindingResult.hasFieldErrors("orderId") && invoiceService.orderIdExists(invoiceDTO.getOrderId())) {
            bindingResult.rejectValue("orderId", "Exists.invoice.orderId");
        }
        if (bindingResult.hasErrors()) {
            return "invoice/add";
        }
        invoiceService.create(invoiceDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("invoice.create.success"));
        return "redirect:/invoices";
    }

    @GetMapping("/edit/{invoiceId}")
    public String edit(@PathVariable final Long invoiceId, final Model model) {
        model.addAttribute("invoice", invoiceService.get(invoiceId));
        return "invoice/edit";
    }

    @PostMapping("/edit/{invoiceId}")
    public String edit(@PathVariable final Long invoiceId,
            @ModelAttribute("invoice") @Valid final InvoiceDTO invoiceDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (!bindingResult.hasFieldErrors("orderId") &&
                !invoiceService.get(invoiceId).getOrderId().equals(invoiceDTO.getOrderId()) &&
                invoiceService.orderIdExists(invoiceDTO.getOrderId())) {
            bindingResult.rejectValue("orderId", "Exists.invoice.orderId");
        }
        if (bindingResult.hasErrors()) {
            return "invoice/edit";
        }
        invoiceService.update(invoiceId, invoiceDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("invoice.update.success"));
        return "redirect:/invoices";
    }

    @PostMapping("/delete/{invoiceId}")
    public String delete(@PathVariable final Long invoiceId,
            final RedirectAttributes redirectAttributes) {
        invoiceService.delete(invoiceId);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("invoice.delete.success"));
        return "redirect:/invoices";
    }

}
