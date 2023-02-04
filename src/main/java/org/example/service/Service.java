package org.example.service;

import org.example.domain.*;
import org.example.exception.GetMethodException;
import org.example.exception.InitExecption;
import org.example.exception.SumMethodException;
import org.example.repository.Repository_SQL;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Service implements Serv {

    //region ATTRIBUTES

    private static Repository_SQL repoCls_SQL;

    //endregion ATTRIBUTES


    //region METHODS: CHECK

    @Override
    public boolean checkStock(ProductforSale proSale, String nameIn) {
        //region DEFINITION VARIABLES
        boolean resul = false;

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        try {
            // 1) GET ALL PRODUCTS OF SAME TYPE
            if (proSale.getProduct().getClass() == Decoration.class) {
                // 2) CHECK PRODUCT STOCK
                resul = proSale.getQuantity() < repoCls_SQL.chechStock("decoration", nameIn);

            } else if (proSale.getProduct().getClass() == Flower.class) {
                // 2) CHECK PRODUCT STOCK
                resul = proSale.getQuantity() < repoCls_SQL.chechStock("flower", nameIn);

            } else if (proSale.getProduct().getClass() == Tree.class) {
                // 2) CHECK PRODUCT STOCK
                resul = proSale.getQuantity() < repoCls_SQL.chechStock("tree", nameIn);

            }

        } catch (Exception ex) {
            resul = false;
        }

        //endregion ACTIONS


        // OUT
        return resul;
    }

    @Override
    public boolean checkExistOnTicket(List<ProductforSale> proSafeListIn, String nameIn) {
        //region DEFINITION VARIABLES
        boolean resul = false, exit = false;
        int index = 0;

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        if (proSafeListIn.size() > 0) {
            do {
                if (proSafeListIn.get(index).getProduct().getName().equals(nameIn)) {
                    exit = true;
                    resul = true;
                }

                index++;
            } while (proSafeListIn.size() < index && !exit);
        }

        //endregion ACTIONS


        // OUT
        return resul;

    }

    //endregion METHODS: CHECK


    //region METHODS: CREATE

    @Override
    public void createFlowerShop(String name) {
        //region DEFINITION VARIABLES

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        try {
            // INIT VARIABLES
            if (repoCls_SQL == null) {
                repoCls_SQL = Repository_SQL.getInstance();
            }

            // CALL REPOSITORY METHOD
            repoCls_SQL.createFlowerShop(name);

        } catch (Exception ex) {
            //TODO control errors
        }

        //endregion ACTIONS

    }


    @Override
    public boolean createProduct(Product product) {
        //region DEFINITION VARIABLES
        boolean resul = false;

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        try {
            // INIT VARIABLES
            repoCls_SQL = Repository_SQL.getInstance();

            // CALL REPOSITORY METHODS
            if (product.getClass() == Decoration.class) {
                if (!repoCls_SQL.findbyName(product.getName(), "Decoration")) {
                    repoCls_SQL.createDeco((Decoration) product);
                    resul = true;
                }
            } else if (product.getClass() == Flower.class) {
                if (!repoCls_SQL.findbyName(product.getName(), "Flower")) {
                    repoCls_SQL.createFlower((Flower) product);
                    resul = true;
                }
            } else if (product.getClass() == Tree.class) {
                if (!repoCls_SQL.findbyName(product.getName(), "Tree")) {
                    repoCls_SQL.createTree((Tree) product);
                    resul = true;
                }
            }

        } catch (Exception ex) {
            resul = false;
        }

        //endregion ACTIONS


        // OUT
        return resul;

    }

    @Override
    public boolean createTicket(Ticket ticket) {
        //region DEFINITION VARIABLES
        boolean result;

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        try {
            // INIT VARIABLES
            repoCls_SQL = Repository_SQL.getInstance();

            // 1) CALCULATE TOTAL PRICE
            ticket.setTotalPrice(sumTicket(ticket));

            // 2) SAVE TICKET
            try {
                repoCls_SQL.createTicket(ticket);
            } catch (Exception e) {
                e.printStackTrace();
            }

            // 3) UPDATE STOCKS
            result = updateStock(ticket);

        } catch (Exception ex) {
            result = false;
        }

        //endregion ACTIONS


        // OUT
        return result;

    }

    //endregion METHODS: CREATE


    //region METHODS: GET
    @Override
    public List<Product> getAllProducts() throws GetMethodException {
        //region DEFINITION VARIABLES
        List<Product> productList;

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        try {
            // INIT VARIABLES
            repoCls_SQL = Repository_SQL.getInstance();

            // CALL REPOSITORY METHOD
            productList = new ArrayList<>(repoCls_SQL.getAllProducts());

        } catch (Exception ex) {
            throw new GetMethodException(1);
        }

        //endregion ACTIONS


        // OUT
        return productList;

    }


    //endregion METHODS: GET


    //region METHODS: OTHERS (INIT, SUM,...)

    @Override
    public String init() throws InitExecption {
        //region DEFINITION VARIABLES
        String name;

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        try {
            // INIT VARIABLES
            repoCls_SQL = Repository_SQL.getInstance();

            // CALL REPOSITORY METHOD
            name = repoCls_SQL.init();

        } catch (Exception ex) {
            throw new InitExecption();
        }

        //endregion ACTIONS


        // OUT
        return name;

    }

    @Override
    public double sumStock() throws SumMethodException {
        //region DEFINITION VARIABLES
        double result;
        List<Product> productList;

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        try {
            // INIT VALUES
            repoCls_SQL = Repository_SQL.getInstance();

            /// 1) GET PRODCUTS
            productList = new ArrayList<>(repoCls_SQL.getAllProducts());

            // 2) SUM SCTOCK VALUE
            result = productList.stream().mapToDouble(x -> x.getPrice() * x.getQuantity()).sum();

        } catch (Exception ex) {
            throw new SumMethodException(3);
        }

        //endregion ACTIONS


        // OUT
        return result;
    }

    @Override
    public double sumTicket(Ticket ticket) throws SumMethodException {
        //region DEFINITION VARIABLES
        double result = 0.0;

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        try {
            // SUM VALUES
            for (ProductforSale p : ticket.getProductforSales()) {
                result += p.getQuantity() * p.getPrice();
            }


        } catch (Exception ex) {
            throw new SumMethodException(2);
        }

        //endregion ACTIONS


        // OUT
        return result;

    }

    /**
     * Mètode per sumar el valor de tots els tiquets que s'han creat.
     *
     * @return El valor de la suma. NOTA! Si el valor retornat és
     * @throws SumMethodException En el cas que hi hagi algun error, saltarà aquesta execpció.
     */
    @Override
    public double sumAllTickets() throws SumMethodException {
        //region DEFINITION VARIABLES
        double result;
        List<Ticket> ticketList;

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        try {
            // INIT VARIABLES
            repoCls_SQL = Repository_SQL.getInstance();

            // 1) GET ALL TICKETS ON DDBB
            ticketList = new ArrayList<>(repoCls_SQL.getAllSells());

            // 2) SUM TICKETS VALUES
            result = ticketList.stream().mapToDouble(Ticket::getTotalPrice).sum();

        } catch (Exception ex) {
            throw new SumMethodException(1);
        }

        //endregion ACTIONS


        // OUT
        return result;

    }

    //endregion METHODS: OTHERS (SUM,...)


    //region PRIVATE METHODS

    /**
     * Mètode per actualitzar el stock de tots els articles
     *
     * @param ticket Classe del tipus Ticket amb la llista de tots els productes que s'han comprat.
     * @return Tipus boolean. False = hi ha hagut algun problema; True = No hi hagut cap problema.
     */
    private boolean updateStock(Ticket ticket) {
        //region DEFINITION VARIABLES
        boolean result = false;

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        try {
            // INIT
            repoCls_SQL = Repository_SQL.getInstance();

            // ITERATE FOR ALL PRODUCTS
            for (ProductforSale p : ticket.getProductforSales()) {
                p.getProduct().setQuantity(p.getProduct().getQuantity() - p.getQuantity());

                if (p.getProduct().getClass() == Decoration.class) {
                    repoCls_SQL.updateDeco((Decoration) p.getProduct());
                } else if (p.getProduct().getClass() == Flower.class) {
                    repoCls_SQL.updateFlower((Flower) p.getProduct());
                } else if (p.getProduct().getClass() == Tree.class) {
                    repoCls_SQL.updateTree((Tree) p.getProduct());
                }
            }

            result = true;
        } catch (Exception ex) {
            ex.printStackTrace();
            result = false;
        }

        //endregion ACTIONS


        // OUT
        return result;

    }

    //endregion PRIVATE METHODS


}

