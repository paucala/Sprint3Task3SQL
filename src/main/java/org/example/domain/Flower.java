package org.example.domain;

public class Flower extends Product {

    private String color;

    public Flower(String name, double price, int quantity, String color) {
        super(name, price, quantity);
        this.color = color;
    }

    public Flower(int id, String name, double price, int quantity, String color) {
        super(id, name, price, quantity);
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Flower{" +
                "color = " + color +
                ", " + super.toString();
    }
}
