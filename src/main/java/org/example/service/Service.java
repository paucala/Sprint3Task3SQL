package org.example.service;

import org.example.domain.*;
import org.example.repository.Repo;
import org.example.repository.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Service implements Serv {

    //region MAIN METHODS





    //endregion MAIN METHODS


    //region METHODS: CREATE
    @Override
    public void createFlowerShop(String name) {
        //region DEFINITION VARIABLES
        Repository repoCls;

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        try {
            // INIT VARIABLES
            repoCls = new Repository();

            // CALL REPOSITORY METHOD
            repoCls.createFlowerShop(name);

        } catch (Exception ex) {
            //TODO control errors
        }

        //endregion ACTIONS

    }

    /**
     * Metode per crear un nou tipus de producte, de qualsevol mena.
     * @param product Necessita una classe del tipus de producte que s'ha de crear.
     * @return false=> No s'ha pogut crear el producte; true => s'ha creat el producte correctament
     */
    @Override
    public boolean createProduct(Product product) {
        //region DEFINITION VARIABLES
        boolean resul = false;
        Repository repoCls;

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        try {
            // INIT VARIABLES
            repoCls = new Repository();

            // CALL REPOSITORY METHODS
            if (product.getClass() == Decoration.class) {
                repoCls.createDeco((Decoration) product);
            } else if (product.getClass() == Flower.class) {
                repoCls.createFlower((Flower) product);
            } else if (product.getClass() == Tree.class) {
                repoCls.createTree((Tree) product);
            } else {
                //TODO Llançar error que la classe no es correcte
            }

            resul = true;
        } catch (Exception ex) {
            //TODO control errors
            resul = false;
        }

        //endregion ACTIONS


        // OUT
        return resul;

    }

    @Override
    public boolean createTicket(Ticket ticket) {
        //region DEFINITION VARIABLES
        boolean result =false;
        Double totalPrice= 0.0;
        List<ProductforSale> productsList;
        Repository repoCls;

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        try {
            // INIT VARIABLES
            productsList = new ArrayList<>();
            repoCls = new Repository();

            // 1) CALCULATE TOTAL PRICE
            productsList.addAll(ticket.getProductforSales());
            ticket.setTotalPrice(productsList.stream().mapToDouble(p -> p.getPrice()).sum());

            // 2) SAVE TICKET
            repoCls.createTicket(ticket);

            result = true;
        } catch (Exception ex) {
            //TODO control erros
            result = false;
        }

        //endregion ACTIONS


        // OUT
        return result;

    }

    //TODO eliminar aquest mètode
//    @Override
//    public void createSell(Ticket ticket) {
//        //region DEFINITION VARIABLES
//        Repository repoCls;
//
//        //endregion DEFINITION VARIABLES
//
//
//        //region ACTIONS
//        try {
//            // INIT VARIABLES
//            repoCls = new Repository();
//
//            // CALL REPOSITORY METHOD
//
//            repoCls.createSell(ticket);
//
//
//        } catch (Exception ex) {
//            //TODO control errors
//        }
//
//        //endregion ACTIONS
//
//
//        // OUT
//
//
//    }

    //endregion METHODS: CREATE


    //region METHODS:GET

    /**
     * Method to get all products there is on DDBB
     *
     * @return List of all products
     */
    @Override
    public List<Product> getAllProducts() {
        //region DEFINITION VARIABLES
        List<Product> productList = null;
        Repository repoCls;

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        try {
            // INIT VARIABLES
            repoCls = new Repository();
            productList = new ArrayList<Product>();

            // CALL REPOSITORY METHOD
            productList.addAll(repoCls.getAllProducts());

        } catch (Exception ex) {
            //TODO control errors
        }

        //endregion ACTIONS


        // OUT
        return productList;

    }

    //endregion METHODS: GET


    //region METHODS: UPDATE

    /**
     * Mètode per actualitzar la info d'un producte.
     * @param product Necessita la classe del producte que s'ha de modificar.
     * @return false = hi hagut algun error; true = tot ha sortit bé.
     */
    @Override
    public boolean updateProduct(Product product) {
        return false;
    }
    //endregion METHODS: UPDATE


    //region METHODS: OTHERS (INIT, SUM,...)
    @Override
    public String init() {
        //region DEFINITION VARIABLES
        String name = "";
        Repository repoCls;

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        try {
            // INIT VARIABLES
            repoCls = new Repository();

            // CALL REPOSITORY METHOD
            name = repoCls.init();

        } catch (Exception ex) {
            //TODO control errors
        }
        //endregion ACTIONS


        // OUT
        return name;
    }

    /**
     * Mètode per sumar el valor de tot el stock de la floristeria.
     * @return el valor de la suma
     */
    @Override
    public double sumStock() {
        return 0;
    }

    /**
     * Mètode per sumar el valor de tots els productesque hi ha en el ticket.
     * @return el valor del tiquet.
     */
    @Override
    public double sumTicket(Ticket ticket) {
        return 0;
    }

    /**
     * Mètode per sumar el valor de tots els tickets que s'han creat
     * @return el valor de la suma
     */
    @Override
    public double sumAllTickets() {
        return 0;
    }
    //endregion METHODS: OTHERS (SUM,...)


    //region AUXILARY METHODS


    //endregion AUXILARY METHODS



}

