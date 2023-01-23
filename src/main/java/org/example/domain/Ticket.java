package org.example.domain;

import java.util.ArrayList;

public class Ticket {

   private ArrayList<Product> products;

    public Ticket() {
        this.products = new ArrayList<>();
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "products=" + products.toString() +
                '}';
    }
}
