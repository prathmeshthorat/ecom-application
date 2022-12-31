package com.ecom.application.shipment_invoice_service.invoice;

import com.ecom.application.shipment_invoice_service.shipment.PaymentMethod;
import com.ecom.application.shipment_invoice_service.shipment.ShippingMethods;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.OffsetDateTime;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Invoice {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long invoiceId;

    @Column(nullable = false, unique = true)
    private Long orderId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ShippingMethods shippingMethod;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Column(nullable = false)
    private Double totalAmount;

    @Column
    @Enumerated(EnumType.STRING)
    private OrderType type;

    @Column
    private Boolean isPaid;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String email;

    @Column
    private String phoneNumber;

    @Column(nullable = false)
    private Long billingAddressId;

    @Column
    private String pdfUrl;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private OffsetDateTime dateCreated;

    @LastModifiedDate
    @Column(nullable = false)
    private OffsetDateTime lastUpdated;

}
