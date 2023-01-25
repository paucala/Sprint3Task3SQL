package org.example.service;

import org.example.domain.Product;
import org.example.domain.Ticket;

import java.util.List;

public interface Serv {
    List<Product> getAllProducts();
    List<Ticket> getAllSells();
    void createProduct(Product product);
    void createTicket(Ticket ticket);
    void createSell(Ticket ticket);
    //per calcular el total de totes les ventes
    double sumSales();
    //per calcular el total d'unaa venta
    double sumSell();
    double sumStock();
    //Devuelve un String con el nombre del archivo o un empty si no existe
    String init();

    void createFlowerShop(String name);
}
