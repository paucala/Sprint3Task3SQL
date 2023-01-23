package org.example.domain;

public class Sell {

    private Ticket ticket;
    private static int id = 0;
    private double totalPrice;

    public Sell(Ticket ticket) {
        id++;
        this.ticket = ticket;
        this.totalPrice = calculate();
    }
    private double calculate(){
       double total = this.ticket.getProducts().stream().mapToDouble(Product::getPrice).sum();
        return total;
    }

    public static int getId() {
        return id;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    @Override
    public String toString() {
        return "Sell{" +
                "ticket=" + ticket +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
