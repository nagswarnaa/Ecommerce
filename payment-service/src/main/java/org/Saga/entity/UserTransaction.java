package org.Saga.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class UserTransaction {
    @Id
    private Integer orderId;
    private int userId;
    private int amount;

    public UserTransaction() {

    }

    public UserTransaction(Integer orderId, int userId, int amount) {
        this.orderId = orderId;
        this.userId = userId;
        this.amount = amount;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
