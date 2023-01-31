package org.example.repository;

import org.example.domain.Decoration;
import org.example.domain.Flower;
import org.example.domain.Tree;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class Repository_SQL {

    Connect connector;
    PreparedStatement ps;
    static Flower flower; // eliminar
    static Tree tree; // eliminar
    static Decoration decoration; // eliminar
    public static void main (String [] args) {

        Repository_SQL repository_sql = new Repository_SQL();

        decoration = new Decoration("Silla", 15, 100,"madera");
        repository_sql.createDeco(decoration);
        flower = new Flower("Flor 2", 2.5, 100, "pink"); //
        repository_sql.createFlower(flower);
        tree = new Tree("Abedul", 8.5, 50, 1.8 );
        repository_sql.createTree(tree);
        }

    public void createFlower (Flower flower){ // throws IOException {

            try{
            String sql = "INSERT INTO flower (flower_name, flower_color, flower_price, flower_quantity)" +
                    " values (?, ?, ?, ?)";
            connector = new Connect();
            ps = connector.connect().prepareStatement(sql);
            ps.setString(1, flower.getName());
            ps.setString(2, flower.getColor());
            ps.setDouble(3, flower.getPrice());
            ps.setInt(4, flower.getQuantity());
            ps.executeUpdate();
            } catch (Exception e) { e.printStackTrace();}
    }
    public void createTree (Tree tree){ // throws IOException {

        try{
            String sql = "INSERT INTO tree (tree_name, tree_price, tree_quantity, tree_height)" +
                    " values (?, ?, ?, ?)";
            connector = new Connect();
            ps = connector.connect().prepareStatement(sql);
            ps.setString(1, tree.getName());
            ps.setDouble(2, tree.getPrice());
            ps.setInt(3, tree.getQuantity());
            ps.setDouble(4, tree.getQuantity());
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace();}
    }
    public void createDeco (Decoration decoration){ // throws IOException {

        try{
            String sql = "INSERT INTO decoration (decoration_name, decoration_material, decoration_price, decoration_quantity)" +
                    " values (?, ?, ?, ?)";
            connector = new Connect();
            ps = connector.connect().prepareStatement(sql);
            ps.setString(1, decoration.getName());
            ps.setString(2, decoration.getMaterial());
            ps.setDouble(3, decoration.getPrice());
            ps.setInt(4, decoration.getQuantity());
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace();}
    }

}



