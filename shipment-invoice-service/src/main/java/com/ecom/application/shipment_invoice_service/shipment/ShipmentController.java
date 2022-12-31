package com.ecom.application.shipment_invoice_service.shipment;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ecom.application.common.constants.OrderStatus;
import com.ecom.application.common.constants.PaymentMethod;
import com.ecom.application.common.constants.ShippingMethods;
import com.ecom.application.common.model.ShipmentDTO;
import com.ecom.application.shipment_invoice_service.util.WebUtils;

import jakarta.validation.Valid;


@Controller
@RequestMapping("/shipments")
public class ShipmentController {

    private final ShipmentService shipmentService;

    public ShipmentController(final ShipmentService shipmentService) {
        this.shipmentService = shipmentService;
    }

    @ModelAttribute
    public void prepareContext(final Model model) {
        model.addAttribute("statusValues", OrderStatus.values());
        model.addAttribute("parcelServiceNameValues", ShippingMethods.values());
        model.addAttribute("paymentMethodValues", PaymentMethod.values());
    }

    @GetMapping
    public String list(final Model model) {
        model.addAttribute("shipments", shipmentService.findAll());
        return "shipment/list";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute("shipment") final ShipmentDTO shipmentDTO) {
        return "shipment/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("shipment") @Valid final ShipmentDTO shipmentDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (!bindingResult.hasFieldErrors("orderId") && shipmentService.orderIdExists(shipmentDTO.getOrderId())) {
            bindingResult.rejectValue("orderId", "Exists.shipment.orderId");
        }
        if (!bindingResult.hasFieldErrors("trackingId") && shipmentService.trackingIdExists(shipmentDTO.getTrackingId())) {
            bindingResult.rejectValue("trackingId", "Exists.shipment.trackingId");
        }
        if (bindingResult.hasErrors()) {
            return "shipment/add";
        }
        shipmentService.create(shipmentDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("shipment.create.success"));
        return "redirect:/shipments";
    }

    @GetMapping("/edit/{shipmentId}")
    public String edit(@PathVariable final Long shipmentId, final Model model) {
        model.addAttribute("shipment", shipmentService.get(shipmentId));
        return "shipment/edit";
    }

    @PostMapping("/edit/{shipmentId}")
    public String edit(@PathVariable final Long shipmentId,
            @ModelAttribute("shipment") @Valid final ShipmentDTO shipmentDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (!bindingResult.hasFieldErrors("orderId") &&
                !shipmentService.get(shipmentId).getOrderId().equals(shipmentDTO.getOrderId()) &&
                shipmentService.orderIdExists(shipmentDTO.getOrderId())) {
            bindingResult.rejectValue("orderId", "Exists.shipment.orderId");
        }
        if (!bindingResult.hasFieldErrors("trackingId") &&
                !shipmentService.get(shipmentId).getTrackingId().equalsIgnoreCase(shipmentDTO.getTrackingId()) &&
                shipmentService.trackingIdExists(shipmentDTO.getTrackingId())) {
            bindingResult.rejectValue("trackingId", "Exists.shipment.trackingId");
        }
        if (bindingResult.hasErrors()) {
            return "shipment/edit";
        }
        shipmentService.update(shipmentId, shipmentDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("shipment.update.success"));
        return "redirect:/shipments";
    }

    @PostMapping("/delete/{shipmentId}")
    public String delete(@PathVariable final Long shipmentId,
            final RedirectAttributes redirectAttributes) {
        shipmentService.delete(shipmentId);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("shipment.delete.success"));
        return "redirect:/shipments";
    }

}
