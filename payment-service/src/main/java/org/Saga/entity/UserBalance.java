package org.Saga.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity

public class UserBalance {
    @Id
    private int userId;
    private int price;

    public UserBalance() {

    }

    public UserBalance(int userId, int price) {
        this.userId = userId;
        this.price = price;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
