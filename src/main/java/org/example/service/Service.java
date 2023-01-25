package org.example.service;

import org.example.domain.*;

import java.util.ArrayList;
import java.util.List;

public class Service implements Serv {

    //region MAIN METHODS

    /**
     * Method to get all products there is on DDBB
     * @return List of all products
     */
    @Override
    public List<Product> getAllProducts() {
        //region DEFINITION VARIABLES
        List<Product> productList;
        Service sercCls;

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        try{
            // INIT VARIABLES
            sercCls = new Service();
            productList = new ArrayList<Product>();

            // CALL REPOSITORY METHOD
            productList.addAll(sercCls.getAllProducts());

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
            sellList.addAll(sercCls.getAllSells());

        }catch(Exception ex) {
            throw ex;
        }
        //endregion ACTIONS


        // OUT
       return sellList;
    }

    @Override
    public void createProduct(Product product) {
        //region DEFINITION VARIABLES
        Service sercCls;

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        try{
            // INIT VARIABLES
            sercCls = new Service();

            // CALL REPOSITORY METHODS
            if(product.getClass() == Decoration.class){
                sercCls.createProduct(product);
            }else if(product.getClass() == Flower.class){
                sercCls.createProduct(product);
            }else if(product.getClass() == Tree.class){
                sercCls.createProduct(product);
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
        Service sercCls;


         //hola
        //endregion DEFINITION VARIABLES


        //region ACTIONS
        try{
            // INIT VARIABLES
            sercCls = new Service();

            // CALL REPOSITORY METHOD
            sercCls.createTicket(ticket);

        }catch(Exception ex){
            throw ex;
        }

        //endregion ACTIONS


        // OUT

    }

    @Override
    public void createSell(Ticket ticket) {
        //region DEFINITION VARIABLES
        Service sercCls;

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        try{
            // INIT VARIABLES
            sercCls = new Service();

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
            sercCls = new Service();

            // GET LIST OF SALES
            sellList.addAll(sercCls.getAllSells());

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
        Service sercCls;

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        try {
            // INIT VARIABLES
            sercCls = new Service();
            name="";

            // CALL REPOSITORY METHOD
            name = sercCls.init();


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
        Service sercCls;

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        try {
            // INIT VARIABLES
            sercCls = new Service();

            // CALL REPOSITORY METHOD
            sercCls.createFlowerShop(name);

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
        Service sercCls;



        //endregion DEFINITION VARIABLES


        try {
            //region ACTIONS
            // INIT VARIABLES
            sercCls = new Service();
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

