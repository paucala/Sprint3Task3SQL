package org.example.domain;

public class Sell {

    private int id;
    private double totalPrice;

    public Sell() {
    }

    public Sell(int id, double totalPrice) {
        this.id = id;
        this.totalPrice = totalPrice;
    }

    public int getId() {
        return id;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

}
