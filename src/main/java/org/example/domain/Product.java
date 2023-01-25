package org.example.domain;

public abstract class Product {

    private int id;
    private String name;
    private int price;
    private int quantity;

    public Product(String name, int price, int quantity) {
        this.price = price;
        this.name = name;
        this.quantity = quantity;
    }

    public Product(int id, String name, int price, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "id = " + id + ", name = " + name +
                ", price = " + price +
                ", quantity = " + quantity +
                '}';
    }
}
