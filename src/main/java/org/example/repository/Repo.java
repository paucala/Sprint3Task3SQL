package org.example.repository;

import org.example.domain.Product;
import org.example.domain.Sell;
import org.example.domain.Ticket;

import java.util.List;

public interface Repo {
    boolean findbyId(int id);
    boolean findbyName(String name);
    List<Product> getAllProducts();
    List<Sell> getAllSells();
    Product getById(int id);
    void createProduct(Product product);
    void createTicket(Ticket ticket);
    void createSell(Sell sell);
    void updateProduct(Product product);
    //Devuelve un String con el nombre del archivo o un empty si no existe
    String init();
    void createFlowerShop(String name);


}
