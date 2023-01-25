package org.example.repository;

import org.example.domain.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface Repo {
    boolean findbyId(int id, String type) throws IOException;
    boolean findbyName(String name) throws IOException;
    List<Product> getAllProducts() throws IOException;
    List<Ticket> getAllSells() throws IOException;
    Product getById(int id, String type) throws IOException;
    void createFlower(Flower flower) throws IOException;
    void createTree(Tree tree) throws IOException;
    void createDeco(Decoration decoration) throws IOException;
    void createTicket(Ticket ticket) throws IOException;
    void createSell(Ticket sell) throws IOException;
    void updateTree(Tree tree) throws IOException;
    void updateFlower(Flower flower) throws IOException;
    void updateDeco(Decoration decoration) throws IOException;
    //Devuelve un String con el nombre del archivo o un empty si no existe
    String init();
    void createFlowerShop(String name) throws IOException;


}
