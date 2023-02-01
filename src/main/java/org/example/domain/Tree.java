package org.example.domain;

public class Tree extends Product{
    double high;

    public Tree(String name, double price, int quantity, double high) {
        super(name, price, quantity);
        this.high = high;
    }

    public Tree(){}

    public Tree(int id, String name, double price, int quantity, double high) {
        super(id, name, price, quantity);
        this.high = high;
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    @Override
    public String toString() {
        return "Tree{" +
                "high = " + high +
                ", " + super.toString();
    }
}
