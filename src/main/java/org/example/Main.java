package org.example;

import org.example.domain.Flower;
import org.example.domain.Sell;
import org.example.domain.Ticket;
import org.example.domain.Tree;

public class Main {
    public static void main(String[] args) {
        Tree tree = new Tree("a", 2, 2, 2);
        System.out.println(tree.toString());
        Flower flower = new Flower("e", 3, 3, "verd");
        System.out.println(flower.toString());
        Ticket ticket = new Ticket();
        ticket.getProducts().add(tree);
        ticket.getProducts().add(flower);
        Sell sell = new Sell(ticket);
        System.out.println(sell);
        //
    }
}