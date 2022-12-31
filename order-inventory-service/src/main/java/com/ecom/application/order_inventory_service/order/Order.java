package com.ecom.application.order_inventory_service.order;

import com.ecom.application.order_inventory_service.order_item.OrderItem;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.OffsetDateTime;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@Table(name = "orders")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Order {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @Column(nullable = false, unique = true)
    private String cartId;

    @Column
    private Long customerId;

    @Column
    @Enumerated(EnumType.STRING)
    private ShippingMethods shippingMenthod;

    @Column
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Column
    private Double totalAmount;

    @Column(nullable = false)
    private Boolean isPaid;

    @Column
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column(columnDefinition = "json")
    @Type(JsonStringType.class)
    private Comments comments;

    @Column
    private String trackingNumber;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderType orderType;

    @OneToMany(mappedBy = "items")
    private Set<OrderItem> items;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private OffsetDateTime dateCreated;

    @LastModifiedDate
    @Column(nullable = false)
    private OffsetDateTime lastUpdated;

}
