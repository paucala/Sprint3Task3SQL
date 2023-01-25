package org.example.repository;

import org.example.domain.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface Repo {
    boolean findbyId(int id, String type) throws IOException;
    boolean findbyName(String name) throws IOException;
    List<Product> getAllProducts() throws IOException;
    List<Sell> getAllSells();
    Product getById(int id, String type);
    void createFlower(Flower flower) throws IOException;
    void createTree(Tree tree) throws IOException;
    void createDeco(Decoration decoration) throws IOException;
    void createTicket(Ticket ticket);
    void createSell(Sell sell);
    void updateProduct(Product product);
    //Devuelve un String con el nombre del archivo o un empty si no existe
    String init();
    void createFlowerShop(String name) throws IOException;


}
