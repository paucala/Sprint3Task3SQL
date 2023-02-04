package org.example.repository;

import org.example.domain.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface Repo {

    boolean findbyName(String name, String type) throws IOException, Exception;

    List<Product> getAllProducts() throws SQLException;
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
