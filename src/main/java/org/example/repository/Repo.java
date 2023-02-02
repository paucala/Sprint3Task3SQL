package org.example.repository;

import org.example.domain.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface Repo {
    boolean findbyId(int id, String type) throws IOException;

    boolean findbyName(String name, String type) throws IOException, Exception;

    List<Product> getAllProducts() throws IOException;
    List<Ticket> getAllSells() throws IOException, SQLException;
    void createFlower(Flower flower) throws SQLException;
    void createTree(Tree tree) throws SQLException;
    void createDeco(Decoration decoration) throws SQLException;
    void createTicket(Ticket ticket) throws IOException, SQLException;
    void updateTree(Tree tree) throws IOException, SQLException;
    void updateFlower(Flower flower) throws IOException, SQLException;
    void updateDeco(Decoration decoration) throws IOException, SQLException;
    //Devuelve un String con el nombre del archivo o un empty si no existe
    String init() throws SQLException;
    void createFlowerShop(String name) throws IOException, SQLException;

}
//void createSell(Ticket sell) throws IOException;
// Product getById(int id, String type) throws IOException;