package org.example.service;

import org.example.domain.*;
import org.example.exception.GetMethodException;
import org.example.exception.SumMethodException;
import org.example.repository.Repo;
import org.example.repository.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.XMLFormatter;
import java.util.stream.Collectors;

public class Service implements Serv {


    //region METHODS: CHECK

    @Override
    public boolean checkStock(ProductforSale proSale) {
        //region DEFINITION VARIABLES
        boolean resul = false, contin = false;
        int i = 0;
        List<Decoration> decoList;
        List<Flower> flowerList;
        List<Tree> treeList;

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        try {
            // 1) GET ALL PRODUCTS OF SAME TYPE
            if (proSale.getProduct().getClass() == Decoration.class) {
                decoList = new ArrayList<>();
                decoList.addAll(getDecoProducts());

                // 2) CHECK PRODUCT STOCK
                while (i < decoList.size() || !contin) {
                    if (decoList.get(i).getId() == proSale.getProduct().getId()) {
                        contin = true;
                        if (proSale.getQuantity() < decoList.get(i).getQuantity()) {
                            resul = true;
                        } else {
                            resul = false;
                        }
                    }
                    i++;
                }
            } else if (proSale.getProduct().getClass() == Flower.class) {
                flowerList = new ArrayList<>();
                flowerList.addAll(getFlowerProducts());

                // 2) CHECK PRODUCT STOCK
                while (i < flowerList.size() || !contin) {
                    if (flowerList.get(i).getId() == proSale.getProduct().getId()) {
                        contin = true;
                        if (proSale.getQuantity() < flowerList.get(i).getQuantity()) {
                            resul = true;
                        } else {
                            resul = false;
                        }
                    }
                    i++;
                }
            } else if (proSale.getProduct().getClass() == Tree.class) {
                treeList = new ArrayList<>();
                treeList.addAll(getTreeProducts());

                // 2) CHECK PRODUCT STOCK
                while (i < treeList.size() || !contin) {
                    if (treeList.get(i).getId() == proSale.getProduct().getId()) {
                        contin = true;
                        if (proSale.getQuantity() < treeList.get(i).getQuantity()) {
                            resul = true;
                        } else {
                            resul = false;
                        }
                    }
                    i++;
                }
            }

        } catch (Exception ex) {
            resul = false;
        }

        //endregion ACTIONS


        // OUT
        return resul;
    }

    //nedregion METHODS: CHECK


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
                if (repoCls.findbyName(product.getName(), "Decoration")) {
                    repoCls.createDeco((Decoration) product);
                    resul = true;
                }
            } else if (product.getClass() == Flower.class) {
                if (repoCls.findbyName(product.getName(), "Flower")) {
                    repoCls.createFlower((Flower) product);
                    resul = true;
                }
            } else if (product.getClass() == Tree.class) {
                if (repoCls.findbyName(product.getName(), "Tree")) {
                    repoCls.createTree((Tree) product);
                    resul = true;
                }
            } else {
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


    @Override
    public List<Decoration> getDecorationList() throws GetMethodException {
        //region DEFINITION VARIABLES
        List<Decoration> decoList;

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        try {
            // INIT VARIABLES
            decoList = new ArrayList<>();

            // GET DECORATION PRODUCTS
            decoList.addAll(getDecoProducts());

        } catch (Exception ex) {
            throw new GetMethodException(2);
        }
        //endregion ACTIONS


        // OUT
        return decoList;
    }


    @Override
    public List<Flower> getFlowerList() throws GetMethodException {
        //region DEFINITION VARIABLES
        List<Flower> flowerList;

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        try {
            // INIT VARIABLES
            flowerList = new ArrayList<>();

            // GET FLOWERS PRODUCTS
            flowerList.addAll(getFlowerProducts());

        } catch (Exception ex) {
            throw new GetMethodException(3);
        }

        //endregion ACTIONS


        // OUT
        return flowerList;

    }

    @Override
    public int[] getStock() throws GetMethodException {
        //region DEFINITION VARIABLES
        int[] results = new int[3];
        List<Product> productList;
        Repository repoCls;

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        try {
            // INIT
            productList = new ArrayList<>();
            repoCls = new Repository();
            results[0] = 0;
            results[1] = 0;
            results[2] = 0;

            // 1) GET STOCK
            productList.addAll(repoCls.getAllProducts());

            // 3) SUM ALL STOCK
            for (Product p : productList) {
                if (p.getClass() == Decoration.class) {
                    results[0] += p.getQuantity();
                } else if (p.getClass() == Flower.class) {
                    results[1] += p.getQuantity();
                } else if (p.getClass() == Tree.class) {
                    results[2] += p.getQuantity();
                }
            }

        } catch (Exception ex) {
            //TODO control errors
            throw new GetMethodException(5);
        }

        //endregion ACTIONS


        // OUT
        return results;
    }

    @Override
    public List<Tree> getTreeList() throws GetMethodException {
        //region DEFINITION VARIABLES
        List<Tree> treeList;

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        try {
            // INIT VARIABLES
            treeList = new ArrayList<>();

            // GET TREE PRODUCTS
            treeList.addAll(getTreeProducts());

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

    @Override
    public boolean updateProduct(Product product) {
        //region DEFINITION VARIABLES
        boolean result;
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
            } else if (product.getClass() == Flower.class) {
                repoCls.updateFlower((Flower) product);
                result = true;
            } else if (product.getClass() == Tree.class) {
                repoCls.updateTree((Tree) product);
                result = true;
            } else {
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
        String name = null;
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

    @Override
    public double sumStock() throws SumMethodException {
        //region DEFINITION VARIABLES
        double result = 0;
        List<Product> productList;
        Repository repoCls;

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        try {
            // INIT VALUES
            productList = new ArrayList<>();
            repoCls = new Repository();

            /// 1) GET PRODCUTS
            productList.addAll(repoCls.getAllProducts());

            // 2) SUM SCTOCK VALUE
            for (Product p : productList) {
                result += p.getQuantity();
            }

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
            throw new SumMethodException(1);
        }

        //endregion ACTIONS


        // OUT
        return result;

    }

    //endregion METHODS: OTHERS (SUM,...)


    //region PRIVATE METHODS

    /**
     * Mètode que retorna tots els prodcutes tipus Decoration.
     *
     * @return Tipus List<Decoration>. La llista amb els productes tipus Decoration.
     * @throws IOException En el cas que hi hagi algun error, saltarà aquesta execpció.
     */
    private List<Decoration> getDecoProducts() throws IOException {
        //region DEFINITION VARIABLES
        List<Decoration> decoList;
        List<Product> productList;
        Repository repoCls;

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        // INIT VARIABLES
        decoList = new ArrayList<>();
        productList = new ArrayList<>();
        repoCls = new Repository();

        // 1) GET ALL PRODUCTS
        productList.addAll(repoCls.getAllProducts());

        // 2) FIND DECORATION PRODUCTS
        for (Product p : productList) {
            if (p.getClass() == Decoration.class) {
                decoList.add((Decoration) p);
            }
        }

        //endregion ACTIONS


        // OUT
        return decoList;

    }

    /**
     * Mètode que retorna tots els prodcutes tipus Flower.
     *
     * @return Tipus List<Flower>. La llista amb els productes tipus Flower.
     * @throws IOException En el cas que hi hagi algun error, saltarà aquesta execpció.
     */
    private List<Flower> getFlowerProducts() throws IOException {
        //region DEFINITION VARIABLES
        List<Flower> flowerList;
        List<Product> productList;
        Repository repoCls;

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        // INIT VARIABLES
        flowerList = new ArrayList<>();
        productList = new ArrayList<>();
        repoCls = new Repository();

        // 1) GET ALL PRODUCTS
        productList.addAll(repoCls.getAllProducts());

        // 2) FIND DECORATION PRODUCTS
        for (Product p : productList) {
            if (p.getClass() == Flower.class) {
                flowerList.add((Flower) p);
            }
        }

        //endregion ACTIONS


        // OUT
        return flowerList;

    }

    /**
     * Mètode que retorna tots els prodcutes tipus Tree.
     *
     * @return Tipus List<Decoration>. La llista amb els productes tipus Tree.
     * @throws IOException En el cas que hi hagi algun error, saltarà aquesta execpció.
     */
    private List<Tree> getTreeProducts() throws IOException {
        //region DEFINITION VARIABLES
        List<Tree> treeList;
        List<Product> productList;
        Repository repoCls;

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        // INIT VARIABLES
        treeList = new ArrayList<>();
        productList = new ArrayList<>();
        repoCls = new Repository();

        // 1) GET ALL PRODUCTS
        productList.addAll(repoCls.getAllProducts());

        // 2) FIND DECORATION PRODUCTS
        for (Product p : productList) {
            if (p.getClass() == Tree.class) {
                treeList.add((Tree) p);
            }
        }

        //endregion ACTIONS


        // OUT
        return treeList;

    }

    //endregion PRIVATE METHODS


}

