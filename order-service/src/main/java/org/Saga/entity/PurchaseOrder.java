package org.Saga.entity;

import jakarta.persistence.*;
import org.Saga.event.OrderStatus;
import org.Saga.event.PaymentStatus;

@Entity
@Table(name = "PURCHASES")
public class PurchaseOrder {
    @Id
    @GeneratedValue
    private Integer id;
    private Integer userId;
    private Integer productId;
    private Integer price;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    public PurchaseOrder(Integer userId, Integer productId, Integer price, OrderStatus orderStatus, PaymentStatus paymentStatus) {
        this.userId = userId;
        this.productId = productId;
        this.price = price;
        this.orderStatus = orderStatus;
        this.paymentStatus = paymentStatus;
    }

    public PurchaseOrder() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
