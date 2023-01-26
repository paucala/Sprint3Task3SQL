package org.example.service;

import org.example.domain.*;
import org.example.exception.GetMethodException;
import org.example.exception.SumMethodException;
import org.example.repository.Repo;
import org.example.repository.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.XMLFormatter;

public class Service implements Serv {

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
     *
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
                resul = true;
            } else if (product.getClass() == Flower.class) {
                repoCls.createFlower((Flower) product);
                resul = true;
            } else if (product.getClass() == Tree.class) {
                repoCls.createTree((Tree) product);
                resul = true;
            } else {
                //TODO Llançar error que la classe no es correcte
                resul = false;
            }

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
        boolean result = false;
        Repository repoCls;

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        try {
            // INIT VARIABLES
            repoCls = new Repository();

            // 1) CALCULATE TOTAL PRICE
            ticket.setTotalPrice(sumTicket(ticket));

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

    //endregion METHODS: CREATE


    //region METHODS: GET

    /**
     * Mètode per aconseguir la llista de tots els productes
     *
     * @return Llista de tots els productes. Si es retorna un null, vol dir que hi hagut.
     * @throws GetMethodException
     */
    @Override
    public List<Product> getAllProducts() throws GetMethodException {
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
            throw new GetMethodException(1);
        }

        //endregion ACTIONS


        // OUT
        return productList;

    }

    /**
     * Mètode per aconseguir la llista de totes les decoracions.
     *
     * @return La llista de totes les decoracions. Si es retorna un null, vol dir que hi hagut.
     * @throws GetMethodException
     */
    @Override
    public List<Decoration> getDecorationList() throws GetMethodException {
        //region DEFINITION VARIABLES
        List<Decoration> decoList = null;
        List<Product>productList = null;
        Repository repoCls;

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        try {
            // INIT VARIABLES
            repoCls = new Repository();
            decoList = new ArrayList<>();
            productList = new ArrayList<>();

            // 1) CALL REPOSITORY METHOD
            productList.addAll(repoCls.getAllProducts());

            // 2) FIND DECORATION PRODUCT
            for (Product p: productList) {
                if(p.getClass() == Decoration.class){
                    decoList.add((Decoration) p);
                }
            }

        } catch (Exception ex) {
            //TODO control errors
            throw new GetMethodException(2);
        }
        //endregion ACTIONS


        // OUT
        return decoList;
    }

    /**
     * Mètode per aconseguir la llista de totes les flors.
     *
     * @return La llista de totes les flors. Si es retorna un null, vol dir que hi hagut.
     * @throws GetMethodException
     */
    @Override
    public List<Flower> getFlowerList() throws GetMethodException {
        //region DEFINITION VARIABLES
        List<Flower> flowerList = null;
        List<Product>productList = null;
        Repository repoCls;

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        try {
            // INIT VARIABLES
            repoCls = new Repository();
            flowerList = new ArrayList<Flower>();
            productList = new ArrayList<>();

            // 1) CALL REPOSITORY METHOD
            productList.addAll(repoCls.getAllProducts());

            // 2) FIND DECORATION PRODUCT
            for (Product p: productList) {
                if(p.getClass() == Flower.class){
                    flowerList.add((Flower) p);
                }
            }

        } catch (Exception ex) {
            //TODO control errors
            throw new GetMethodException(3);
        }
        //endregion ACTIONS


        // OUT
        return flowerList;
    }

    /**
     * Mètode que retorna una matriu de 3 integers amb el stock de cada element, on...
     * [0] és el stock de decorations
     * [1] és el stock de flowers
     * [2] és el stock d2 trees
     * @return la matriu de 3 integers.
     * @throws GetMethodException En el cas que hi hagi algun error, saltarà aquesta execpció.
     */
    @Override
    public int[] getStock() throws GetMethodException {
        //region DEFINITION VARIABLES
        int[]results = new int[3];
        List<Product> productList;
        Repository repoCls;

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        try{
            // INIT
            productList = new ArrayList<>();
            repoCls = new Repository();
            results[0]=0;
            results[1]=0;
            results[2]=0;

            // 1) GET STOCK
            productList.addAll(repoCls.getAllProducts());

            // 3) SUM ALL STOCK
            for (Product p:productList) {
                if(p.getClass() == Decoration.class){
                    results[0]+=p.getQuantity();
                }else if(p.getClass() == Flower.class){
                    results[1]+=p.getQuantity();
                }else if(p.getClass() == Tree.class){
                    results[2]+=p.getQuantity();
                }
            }

        }catch(Exception ex){
            //TODO control errors
            throw new GetMethodException(5);
        }

        //endregion ACTIONS


        // OUT
        return results;
    }

    /**
     * Mètode per aconseguir la llista de tots els arbres.
     *
     * @return La llista de tots els arbres. Si es retorna un null, vol dir que hi hagut.
     * @throws GetMethodException En el cas que hi hagi algun error, saltarà aquesta execpció.
     */
    @Override
    public List<Tree> getTreeList() throws GetMethodException {
        //region DEFINITION VARIABLES
        List<Tree> treeList = null;
        List<Product>productList = null;
        Repository repoCls;

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        try {
            // INIT VARIABLES
            repoCls = new Repository();
            treeList = new ArrayList<Tree>();
            productList = new ArrayList<>();

            // 1) CALL REPOSITORY METHOD
            productList.addAll(repoCls.getAllProducts());

            // 2) FIND DECORATION PRODUCT
            for (Product p: productList) {
                if(p.getClass() == Tree.class){
                    treeList.add((Tree) p);
                }
            }

        } catch (Exception ex) {
            //TODO control errors
            throw new GetMethodException(4);
        }

        //endregion ACTIONS


        // OUT
        return treeList;
    }

    //endregion METHODS: GET


    //region METHODS: UPDATE

    /**
     * Mètode per actualitzar la info d'un producte.
     *
     * @param product Necessita la classe del producte que s'ha de modificar.
     * @return false = hi hagut algun error; true = tot ha sortit bé.
     */
    @Override
    public boolean updateProduct(Product product) {
        //region DEFINITION VARIABLES
        boolean result = false;
        Repository repoCls;

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        try {
            // INIT VARIABLES
            repoCls = new Repository();

            // CALL REPOSITORY METHOD
            if (product.getClass() == Decoration.class) {
                repoCls.updateDeco((Decoration) product);
                result = true;
            }else if (product.getClass() == Flower.class) {
                repoCls.updateFlower((Flower) product);
                result = true;
            } else if (product.getClass() == Tree.class) {
                repoCls.updateTree((Tree) product);
                result = true;
            }else{
                result = false;
            }

        } catch (Exception ex) {
            //TODO control errors
            result = false;
        }

        //endregion ACTIONS


        // OUT
        return result;
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
     *  Mètode per sumar el valor de tot el stock de la floristeria.
     * @return el valor de la suma
     * @throws SumMethodException En el cas que hi hagi algun error, saltarà aquesta execpció.
     */
    @Override
    public double sumStock() throws SumMethodException {
        //region DEFINITION VARIABLES
        double result = 0;
        List<Decoration> decoList;
        List<Flower> flowersList;
        List<Tree> treeList;
        Repository repoCls;

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        try {
            // INIT VALUES
            decoList = new ArrayList<>();


        } catch (Exception ex) {
            //TODO control errors
            result = 0;
            throw new SumMethodException(3);
        }

        //endregion ACTIONS


        // OUT
        return result;
    }

    /**
     * Mètode per sumar el valor de tots els productesque hi ha en el ticket.
     * @param ticket Necessita una classe ticket amb tota la info.
     * @return el valor del ticket.
     * @throws SumMethodException En el cas que hi hagi algun error, saltarà aquesta execpció.
     */
    @Override
    public double sumTicket(Ticket ticket) throws SumMethodException {
        //region DEFINITION VARIABLES
        double result;
        List<ProductforSale> productsList;

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        try {
            // INIT VARIABLES
            productsList = new ArrayList<>();

            // SUM VALUES
            result = productsList.stream().mapToDouble(p -> p.getPrice()).sum();

        } catch (Exception ex) {
            //TODO control errors
            result = 0.0;
            throw new SumMethodException(2);
        }

        //endregion ACTIONS


        // OUT
        return result;
    }

    /**
     * Mètode per sumar el valor de tots els tickets que s'han creat
     * @return el valor de la suma. NOTA! Si el valor retornat és
     * @throws SumMethodException En el cas que hi hagi algun error, saltarà aquesta execpció.  
     */
    @Override
    public double sumAllTickets() throws SumMethodException {
        //region DEFINITION VARIABLES
        double result = 0;
        List<Ticket> ticketList;
        Repository repoCls;

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        try {
            // INIT VARIABLES
            ticketList = new ArrayList<Ticket>();
            repoCls = new Repository();

            // 1) GET ALL TICKETS ON DDBB
            ticketList.addAll(repoCls.getAllSells());

            // 2) SUM TICKETS VALUES
            for (Ticket t : ticketList) {
                result += t.getTotalPrice();
            }

        } catch (Exception ex) {
            //TODO control errors
            throw new SumMethodException(1);
        }

        //endregion ACTIONS


        // OUT
        return result;

    }

    //endregion METHODS: OTHERS (SUM,...)


    //region AUXILARY METHODS


    //endregion AUXILARY METHODS


}

