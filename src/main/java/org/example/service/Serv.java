package org.example.service;

import org.example.domain.*;
import org.example.exception.GetMethodException;
import org.example.exception.SumMethodException;

import java.io.IOException;
import java.util.List;

public interface Serv {

    //TODO eliminar -> List<Ticket> getAllSells();

    //TODO eliminar -> void createSell(Ticket ticket);
    //per calcular el total de totes les vendes
    //TODO eliminar -> double sumSales();

    //per calcular el total d'unaa venda
    //TODO eliminar -> double sumSell();


    //region METHODS: CREATE
    void createFlowerShop(String name);

    boolean createProduct(Product product);

    boolean createTicket(Ticket ticket);

    //endregion METHODS: CREATE


    //region METHODS: GET
    List<Product> getAllProducts() throws GetMethodException;

    List<Decoration> getDecorationList() throws GetMethodException;

    List<Flower> getFlowerList() throws GetMethodException;

    List<Tree> getTreeList() throws GetMethodException;

    //endregion METHODS: GET


    //region METHODS: UPDATE

    /**
     * Mètode per actualitzar la info d'un producte
     * @param product
     * @return
     */
    boolean updateProduct(Product product);

    //endregion METHODS: UPDATE


    //region METHODS: OTHERS (INIT, SUM,...)
    //Devuelve un String con el nombre del archivo o un empty si no existe
    String init();

    /**
     * Mètode per sumar el total de l'estock
     *
     * @return
     */
    double sumStock() throws SumMethodException;

    /**
     * Metode per sumar el total d'un sol ticket
     *
     * @return
     */
    double sumTicket(Ticket ticket) throws SumMethodException;

    /**
     * Mètode per sumar el valor de tots els tiquets
     * @return la suma de tots els tikets
     */
    double sumAllTickets() throws SumMethodException;

    //endregion METHODS: OTHERS (INIT, SUM,...)


}
