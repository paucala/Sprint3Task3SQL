package org.example.service;

import org.example.domain.*;
import org.example.repository.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Service implements Serv {

    //region MAIN METHODS

    /**
     * Method to get all products there is on DDBB
     * @return List of all products
     */
    @Override
    public List<Product> getAllProducts() throws IOException {
        //region DEFINITION VARIABLES
        List<Product> productList;
        Repository repoCls;

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        try{
            // INIT VARIABLES
            repoCls = new Repository();
            productList = new ArrayList<Product>();

            // CALL REPOSITORY METHOD
            productList.addAll(repoCls.getAllProducts());

        }catch (Exception ex) {
            throw ex;
        }

        //endregion ACTIONS


        // OUT
        return productList;

    }

    @Override
    public List<Ticket> getAllSells() {
        //region DEFINITION VARIABLES
        List<Ticket> sellList;
        Service sercCls;

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        try{
            // INIT VARIABLES
            sercCls = new Service();
            sellList = new ArrayList<Ticket>();


            // CALL REPOSITORY METHOD
            sellList.addAll(repoCls.getAllSells());

        }catch(Exception ex) {
            throw ex;
        }
        //endregion ACTIONS


        // OUT
       return sellList;
    }

    @Override
    public void createProduct(Product product) throws IOException {
        //region DEFINITION VARIABLES
        Repository repoCls;

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        try{
            // INIT VARIABLES
            repoCls = new Repository();

            // CALL REPOSITORY METHODS
            if(product.getClass() == Decoration.class){
                repoCls.createDeco((Decoration) product);
            }else if(product.getClass() == Flower.class){
                repoCls.createFlower((Flower) product);
            }else if(product.getClass() == Tree.class){
                repoCls.createTree((Tree) product);
            }else{
                //TODO Llançar error que la classe no es correcte
            }


        }catch(Exception ex){
            throw ex;
        }

        //endregion ACTIONS


        // OUT


    }

    @Override
    public void createTicket(Ticket ticket) {
        //region DEFINITION VARIABLES
        Double totalPrice;
        List<ProductforSale> productsList;
        Sell sell;
        Repository repoCls;

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        try{
            // INIT VARIABLES
            productsList = new ArrayList<>();
            sell = new Sell();
            repoCls = new Repository();

            // 1) CALCULATE TOTAL PRICE
            productsList.addAll(ticket.getProductforSales());
            totalPrice = productsList.stream().mapToDouble(p-> p.getPrice()).sum();

            // 2) SAVE SELL
//            sell.
//            repoCls.createTicket();

            // 3) SAVE TICKET'S PRODUCTS


            // CALL REPOSITORY METHOD
            repoCls.createTicket(ticket);

        }catch(Exception ex){
            throw ex;
        }

        //endregion ACTIONS


        // OUT

    }

    @Override
    public void createSell(Ticket ticket) {
        //region DEFINITION VARIABLES
        Repository repoCls;

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        try{
            // INIT VARIABLES
            repoCls = new Repository();

            // CALL REPOSITORY METHOD

            sercCls.createSell(ticket);


        }catch(Exception ex){
            throw ex;
        }

        //endregion ACTIONS


        // OUT


    }

    /**
     * Get total value of sales
     * @return Double of value.
     */
    @Override
    public double sumSales() {
        //region DEFINITION VARIABLES

        List<Ticket> sellList = new ArrayList<Ticket>();
        Service sercCls;


        //endregion DEFINITION VARIABLES


        //region ACTIONS
        try {
            // INIT VARIABLES
            repoCls = new Repository();

            // GET LIST OF SALES
            sellList.addAll(repoCls.getAllSells());

            for (Ticket s: sellList) {

            }

        } catch (Exception ex) {
            throw ex;
        }
        //endregion ACTIONS


        // OUT


        return 0;
    }

    @Override
    public double sumSell() {
        return 0;
    }

    @Override
    public double sumStock() {
        return 0;
    }

    @Override
    public String init() {
        //region DEFINITION VARIABLES
        String name;
        Repository repoCls;

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        try {
            // INIT VARIABLES
            repoCls = new Repository();
            name="";

            // CALL REPOSITORY METHOD
            name = repoCls.init();


        } catch (Exception ex) {
            throw ex;
        }
        //endregion ACTIONS


        // OUT
        return name;
    }

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
            throw ex;
        }

        //endregion ACTIONS

    }

    //endregion MAIN METHODS


    //region AUXILARY METHODS
    private double sumSellAux(Ticket sell){
        //region DEFINITION VARIABLES
        double totalValue = 0;
        List<Ticket> ticketList;
        Repository repoCls;


        //endregion DEFINITION VARIABLES


        try {
            //region ACTIONS
            // INIT VARIABLES
            repoCls = new Repository();
            ticketList = new ArrayList<>();
            //ticketList.addAll(sercCls.)


            //endregion ACTIONS


            // OUT
            return totalValue;
        }catch(Exception ex){
            throw ex;
        }


    }

    //endregion AUXILARY METHODS

}

