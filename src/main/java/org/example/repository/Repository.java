package org.example.repository;

import org.example.domain.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Repository implements Repo{

    private File flowershop;
    @Override
    public String init() {
        Path path = Path.of("\\DataBase");
        String filename = null;
        if (Files.isDirectory(path)) {
            try (Stream<Path> entries = Files.list(path)) {
                filename = entries.toString();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return filename;
    }

    @Override
    public void createFlowerShop(String name) {
        this.flowershop = new File("\\DataBase\\" + name + ".txt");
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
                String[] parts = line.split(";");
                Tree tree = new Tree(Integer.valueOf(parts[1]), parts[2], Double.valueOf(parts[3]),
                        Integer.valueOf(parts[4]), Double.valueOf(parts[5]));
                products.add(tree);
        } else if (line.startsWith("Flower")) {
                String[] parts = line.split(";");
                Flower flower = new Flower(Integer.valueOf(parts[1]), parts[2], Double.valueOf(parts[3]),
                        Integer.valueOf(parts[4]), parts[5]);
                products.add(flower);
            } else if (line.startsWith("Decoration")) {
                String[] parts = line.split(";");
               Decoration decoration = new Decoration(Integer.valueOf(parts[1]), parts[2], Double.valueOf(parts[3]),
                        Integer.valueOf(parts[4]), parts[5]);
                products.add(decoration);
            }

        }

        return products;
    }

    @Override
    public List<Sell> getAllSells() throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader(flowershop));
    String line;
    ArrayList<Sell> sells = new ArrayList<>();

        while ((line = reader.readLine()) != null) {
        if (line.startsWith("Sell")){
            String[] parts = line.split(";");
            Sell sell = new Sell(Integer.valueOf(parts[1]), Double.valueOf(parts[2]));
            sells.add(sell);
        }
        }

        return sells;
}

    @Override
    public Product getById(int id, String type) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(flowershop));
        String line;
        while ((line = reader.readLine()) != null) {
            if (line.contains(type) && line.contains(Integer.toString(id))){
                if (line.startsWith("Tree")){
                    String[] parts = line.split(";");
                    Tree tree = new Tree(Integer.valueOf(parts[1]), parts[2], Double.valueOf(parts[3]),
                            Integer.valueOf(parts[4]), Double.valueOf(parts[5]));
                    return tree;
                } else if (line.startsWith("Flower")) {
                    String[] parts = line.split(";");
                    Flower flower = new Flower(Integer.valueOf(parts[1]), parts[2], Double.valueOf(parts[3]),
                            Integer.valueOf(parts[4]), parts[5]);
                    return flower;
                } else if (line.startsWith("Decoration")) {
                    String[] parts = line.split(";");
                    Decoration decoration = new Decoration(Integer.valueOf(parts[1]), parts[2], Double.valueOf(parts[3]),
                            Integer.valueOf(parts[4]), parts[5]);
                    return decoration;
                }
            }
        }

        return null;
    }

    @Override
    public void createFlower(Flower flower) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(flowershop, true));
        StringBuilder newflower = new StringBuilder();
        newflower.append("Flower;");
        newflower.append(flower.getId() + ";");
        newflower.append(flower.getName()  + ";");
        newflower.append(flower.getPrice()  + ";");
        newflower.append(flower.getQuantity()  + ";");
        newflower.append(flower.getColor());
        writer.write(newflower.toString());
        writer.close();

    }

    @Override
    public void createTree(Tree tree) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(flowershop, true));
        StringBuilder newtree = new StringBuilder();
        newtree.append("Tree;");
        newtree.append(tree.getId() + ";");
        newtree.append(tree.getName() + ";");
        newtree.append(tree.getPrice() + ";");
        newtree.append(tree.getQuantity() + ";");
        newtree.append(tree.getHigh());
        writer.write(newtree.toString());
        writer.close();

    }

    @Override
    public void createDeco(Decoration decoration) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(flowershop, true));
        StringBuilder newdeco = new StringBuilder();
        newdeco.append("Decoration;");
        newdeco.append(decoration.getId() + ";");
        newdeco.append(decoration.getName() + ";");
        newdeco.append(decoration.getPrice() + ";");
        newdeco.append(decoration.getQuantity() + ";");
        newdeco.append(decoration.getMaterial());
        writer.write(newdeco.toString());
        writer.close();

    }

    @Override
    public void createTicket(Ticket ticket) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(flowershop, true));
        StringBuilder newticket = new StringBuilder();
        newticket.append("Ticket;");
        newticket.append(ticket.getId() + ";");
       for (ProductforSale pfs : ticket.getProductforSales()){
           newticket.append(pfs.getProduct().getId() + ";");
           newticket.append(pfs.getQuantity());
       }
        writer.write(newticket.toString());
        writer.close();
    }

    @Override
    public void createSell(Sell sell) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(flowershop, true));
        StringBuilder newsell = new StringBuilder();
        newsell.append("Sell;");
        newsell.append(sell.getId() + ";");
        newsell.append(sell.getTotalPrice());
    }

    @Override
    public void updateTree(Tree tree) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(flowershop, true));
        StringBuilder newtree = new StringBuilder();
        newtree.append("Tree;");
        newtree.append(tree.getId() + ";");
        newtree.append(tree.getName() + ";");
        newtree.append(tree.getPrice() + ";");
        newtree.append(tree.getQuantity() + ";");
        newtree.append(tree.getHigh());

        String oldline = getTreeString(tree);
        StringBuffer buffer = new StringBuffer();
        String fileContents = buffer.toString();
        fileContents = fileContents.replaceAll(oldline, newtree.toString());
        writer.append(fileContents);
        writer.flush();
        writer.close();

    }

    @Override
    public void updateFlower(Flower flower) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(flowershop, true));
        StringBuilder newflower = new StringBuilder();
        newflower.append("Flower;");
        newflower.append(flower.getId() + ";");
        newflower.append(flower.getName() + ";");
        newflower.append(flower.getPrice() + ";");
        newflower.append(flower.getQuantity() + ";");
        newflower.append(flower.getColor());

        String oldline = getFlowerString(flower);
        StringBuffer buffer = new StringBuffer();
        String fileContents = buffer.toString();
        fileContents = fileContents.replaceAll(oldline, newflower.toString());
        writer.append(fileContents);
        writer.flush();
        writer.close();

    }

    @Override
    public void updateDeco(Decoration decoration) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(flowershop, true));
        StringBuilder newdeco = new StringBuilder();
        newdeco.append("Flower;");
        newdeco.append(decoration.getId() + ";");
        newdeco.append(decoration.getName() + ";");
        newdeco.append(decoration.getPrice() + ";");
        newdeco.append(decoration.getQuantity() + ";");
        newdeco.append(decoration.getMaterial());

        String oldline = getDecoString(decoration);
        StringBuffer buffer = new StringBuffer();
        String fileContents = buffer.toString();
        fileContents = fileContents.replaceAll(oldline, newdeco.toString());
        writer.append(fileContents);
        writer.flush();
        writer.close();
    }
    public String getTreeString(Tree tree) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(flowershop));
        String line;
        String productString = null;

        while ((line = reader.readLine()) != null) {
            if (line.startsWith("Tree") && line.contains(Integer.toString(tree.getId()))){
               productString = line;
             }
        }
            return productString;
    }
    public String getFlowerString(Flower flower) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(flowershop));
        String line;
        String productString = null;

        while ((line = reader.readLine())  != null) {
            if (line.startsWith("Flower") && line.contains(Integer.toString(flower.getId()))){
                productString = line;
            }
        }
        return productString;
    }
    public String getDecoString(Decoration decoration) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(flowershop));
        String line;
        String productString = null;

        while ((line = reader.readLine())  != null) {
            if (line.startsWith("Flower") && line.contains(Integer.toString(decoration.getId()))){
                productString = line;
            }
        }
        return productString;
    }

}
