package org.example.repository;

import org.example.domain.Decoration;
import org.example.domain.Flower;
import org.example.domain.Product;
import org.example.domain.Ticket;
import org.example.domain.Tree;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Repository_SQL implements Repo {

    Connect connector;
    PreparedStatement ps;
    ResultSet rs;
    static Flower flower; // eliminar
    static Tree tree; // eliminar
    static Decoration decoration; // eliminar
    
    public static void main (String [] args) { //eliminar

        Repository_SQL repository_sql = new Repository_SQL();

        //decoration = new Decoration("Mesa", 15, 100,"madera");
        //repository_sql.createDeco(decoration);
        //flower = new Flower("Flor 3", 2.5, 100, "pink"); //
        //repository_sql.createFlower(flower);
        //tree = new Tree("Pino", 8.5, 50, 1.8 );
        //repository_sql.createTree(tree);
        //repository_sql.getAllProducts();
        }

    
    /*
     *  >>>>>>>>>>>>>>>>>>>>>>>>>>>> CREATE <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
     */
    

    public void createFlower (Flower flower) throws SQLException {

            
            String sql = "INSERT INTO flower (flower_name, flower_color, flower_price, flower_quantity)" +
                    " values (?, ?, ?, ?)";
            connector = new Connect();
            ps = connector.connect().prepareStatement(sql);
            ps.setString(1, flower.getName());
            ps.setString(2, flower.getColor());
            ps.setDouble(3, flower.getPrice());
            ps.setInt(4, flower.getQuantity());
            ps.executeUpdate();
          
    }
    public void createTree (Tree tree)throws SQLException {

       
            String sql = "INSERT INTO tree (tree_name, tree_price, tree_quantity, tree_height)" +
                    " values (?, ?, ?, ?)";
            connector = new Connect();
            ps = connector.connect().prepareStatement(sql);
            ps.setString(1, tree.getName());
            ps.setDouble(2, tree.getPrice());
            ps.setInt(3, tree.getQuantity());
            ps.setDouble(4, tree.getQuantity());
            ps.executeUpdate();

    }
    
    public void createDeco (Decoration decoration)throws SQLException {

        
            String sql = "INSERT INTO decoration (decoration_name, decoration_material, decoration_price, decoration_quantity)" +
                    " values (?, ?, ?, ?)";
            connector = new Connect();
            ps = connector.connect().prepareStatement(sql);
            ps.setString(1, decoration.getName());
            ps.setString(2, decoration.getMaterial());
            ps.setDouble(3, decoration.getPrice());
            ps.setInt(4, decoration.getQuantity());
            ps.executeUpdate();
        
    }
/*
 *  >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> LIST <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<        
 */
        
        
    
    
    
    
    public List<Product> getAllProducts()  throws IOException {
        List<Product> allProducts = null;
        try {
            allProducts = new ArrayList<>();
            String sql1 = ("select * from decoration");
            String sql2 = ("select * from flower");
            String sql3 = ("select * from tree");
            connector = new Connect();
            ps = connector.connect().prepareStatement(sql1);
            rs = ps.executeQuery();

            while (rs.next()) {
                Decoration d = new Decoration();
                d.setName(rs.getString("decoration_name"));
                d.setMaterial(rs.getString("decoration_material"));
                d.setPrice(rs.getDouble("decoration_price"));
                d.setQuantity(rs.getInt("decoration_quantity"));
                allProducts.add(d);
            }
            ps = connector.connect().prepareStatement(sql2);
            rs = ps.executeQuery();

            while (rs.next()) {
                Flower f = new Flower();
                f.setName(rs.getString("flower_name"));
                f.setColor(rs.getString("flower_color"));
                f.setPrice(rs.getDouble("flower_price"));
                f.setQuantity(rs.getInt("flower_quantity"));
                allProducts.add(f);
            }
            ps = connector.connect().prepareStatement(sql3);
            rs = ps.executeQuery();

            while (rs.next()) {
                Tree t = new Tree();
                t.setName(rs.getString("tree_name"));
                t.setHigh(rs.getDouble("tree_height"));
                t.setPrice(rs.getDouble("tree_price"));
                t.setQuantity(rs.getInt("tree_quantity"));
                allProducts.add(t);
            }
            ps.close();
            rs.close();
            allProducts.forEach(x -> System.out.println(x));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return allProducts;
    }

	@Override
	public boolean findbyId(int id, String type) throws IOException { //
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean findbyName(String name, String type) throws Exception {
		
		boolean resp = false;
		connector = new Connect();
		String sql_decoration = "SELECT name from decoration where decoration_ name = ?";
		String sql_flower = "SELECT name from flower where flower_ name = ?";
		String sql_tree = "SELECT name from tree where tree_ name = ?";
		
		if(type.equalsIgnoreCase("decoration")) {
			ps = connector.connect().prepareStatement(sql_decoration);
		}else if (type.equalsIgnoreCase("flower")) {
			ps = connector.connect().prepareStatement(sql_flower);
		}else if (type.equalsIgnoreCase("tree")) {
			ps = connector.connect().prepareStatement(sql_tree);
		}
		
		rs = ps.executeQuery();
		if(rs.next()){
			resp =  true;
		} 
		
		return resp;
	}

	@Override
	public List<Ticket> getAllSells() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product getById(int id, String type) throws IOException { //
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createTicket(Ticket ticket) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createSell(Ticket sell) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateTree(Tree tree) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateFlower(Flower flower) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateDeco(Decoration decoration) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String init() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createFlowerShop(String name) throws IOException {
		// TODO Auto-generated method stub
		
	}

    }



