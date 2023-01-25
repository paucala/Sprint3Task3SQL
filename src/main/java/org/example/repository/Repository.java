package org.example.repository;

import org.example.domain.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Repository implements Repo{

    private File flowershop;
    @Override
    public String init() {
        return null;
    }

    @Override
    public void createFlowerShop(String name) {
        this.flowershop = new File(name + ".txt");
    }
    @Override
    public boolean findbyId(int id, String type) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(flowershop));
        String line;
        boolean found = false;

        while ((line = reader.readLine()) != null) {
            if (line.contains(type) && line.contains(Integer.toString(id))) {
                return true;
            }
        }
        return found;
    }

    @Override
    public boolean findbyName(String name) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(flowershop));
        String line;
        boolean found = false;

        while ((line = reader.readLine()) != null) {
            if (line.contains(name)) {
                return true;
            }
        }
        return found;
    }

    @Override
    public List<Product> getAllProducts() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(flowershop));
        String line;
        ArrayList<Product> products = new ArrayList<>();

        while ((line = reader.readLine()) != null) {
            if (line.startsWith("Tree")){

        }

    }

        return products;
    }

    @Override
    public List<Sell> getAllSells() {
        return null;
    }

    @Override
    public Product getById(int id, String type) {
        return null;
    }

    @Override
    public void createFlower(Flower flower) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(flowershop, true));
        StringBuilder newflower = new StringBuilder();
        newflower.append("Flower");
        newflower.append(", ID: " + flower.getId());
        newflower.append(", name: " + flower.getName());
        newflower.append(", price: " + flower.getPrice());
        newflower.append(", quantity" + flower.getQuantity());
        newflower.append(", color: " + flower.getColor());
        writer.write(newflower.toString());
        writer.close();

    }

    @Override
    public void createTree(Tree tree) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(flowershop, true));
        StringBuilder newtree = new StringBuilder();
        newtree.append("Tree");
        newtree.append(", ID: " + tree.getId());
        newtree.append(", name: " + tree.getName());
        newtree.append(", price: " + tree.getPrice());
        newtree.append(", quantity" + tree.getQuantity());
        newtree.append(", color: " + tree.getHigh());
        writer.write(newtree.toString());
        writer.close();

    }

    @Override
    public void createDeco(Decoration decoration) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(flowershop, true));
        StringBuilder newdeco = new StringBuilder();
        newdeco.append("Decoration");
        newdeco.append(", ID: " + decoration.getId());
        newdeco.append(", name: " + decoration.getName());
        newdeco.append(", price: " + decoration.getPrice());
        newdeco.append(", quantity" + decoration.getQuantity());
        newdeco.append(", color: " + decoration.getColor());
        writer.write(newdeco.toString());
        writer.close();

    }

    @Override
    public void createTicket(Ticket ticket) {

    }

    @Override
    public void createSell(Sell sell) {

    }

    @Override
    public void updateProduct(Product product) {

    }


}
