package org.example.repository;

import org.example.domain.*;

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

    public static Repository_SQL instance;

    //region CONSTRUCTOR
    private Repository_SQL(){

    }

    public static Repository_SQL getInstance(){
        if (instance == null){
            instance = new Repository_SQL();
        }
        return instance;
    }

    //endregion CONSTRUCTOR


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

    public List<Product> getAllProducts() throws SQLException {
        List<Product> allProducts = null;
            allProducts = new ArrayList<>();
            String sql1 = ("select * from decoration");
            String sql2 = ("select * from flower");
            String sql3 = ("select * from tree");
            connector = new Connect();
            ps = connector.connect().prepareStatement(sql1);
            rs = ps.executeQuery();

            while (rs.next()) {
                Decoration d = new Decoration();
                d.setId(rs.getInt("decoration_id"));
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
                f.setId(rs.getInt("flower_id"));
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
                t.setId(rs.getInt("tree_id"));
                t.setName(rs.getString("tree_name"));
                t.setHigh(rs.getDouble("tree_height"));
                t.setPrice(rs.getDouble("tree_price"));
                t.setQuantity(rs.getInt("tree_quantity"));
                allProducts.add(t);
            }
            ps.close();
            rs.close();

        return allProducts;
    }

	@Override
	public boolean findbyName(String name, String type) throws SQLException {

		String nombre = name;
		boolean resp = false;
		connector = new Connect();
		String sql_decoration = "SELECT * from decoration where decoration_name = ?";
		String sql_flower = "SELECT * from flower where flower_name = ?";
		String sql_tree = "SELECT * from tree where tree_name = ?";

		if(type.equalsIgnoreCase("decoration")) {
            ps = connector.connect().prepareStatement(sql_decoration);
		}else if (type.equalsIgnoreCase("flower")) {
            ps = connector.connect().prepareStatement(sql_flower);
		}else if (type.equalsIgnoreCase("tree")) {
            ps = connector.connect().prepareStatement(sql_tree);
		}

        ps.setString(1, nombre);
        rs = ps.executeQuery();
        while (rs.next()){
            resp =  true;
        }
		return resp;
	}

	@Override
	public List<Ticket> getAllSells() throws SQLException {

        List<Ticket> ticketList = new ArrayList<>();
		connector = new Connect();

        String sql = ("select * from sell");

            ps = connector.connect().prepareStatement(sql);
            rs = ps.executeQuery();

        while (rs.next()) {
            Ticket t = new Ticket();
            t.setTotalPrice(rs.getDouble("sell_amount"));
            ticketList.add(t);
        }
        return ticketList;
	}

	@Override
	public void createTicket(Ticket ticket) throws SQLException {

        List <ProductforSale> ticketDetail = ticket.getProductforSales();
        int ticketNumber = 0;
        connector = new Connect();
        String sqlAmount = "INSERT INTO sell (sell_amount) values (?)";
        ps = connector.connect().prepareStatement(sqlAmount);
        ps.setDouble(1, ticket.getTotalPrice());
        ps.executeUpdate();

        Statement statement = connector.connect().createStatement();
        rs = statement.executeQuery("SELECT sell_id FROM sell ORDER BY sell_id DESC");
        if (rs.next()){
            ticketNumber = rs.getInt("sell_id");
        }

        String sql = "INSERT INTO ticket (ticket_prod_id, ticket_prod_name, ticket_prod_quantity, sell_sell_id)" +
                " values (?, ?, ?, ?)";

        ps = connector.connect().prepareStatement(sql);

        for(ProductforSale productforSale : ticketDetail){

           ps.setInt(1, 1);
           ps.setString(2, productforSale.getProduct().getName());
           ps.setInt (3, productforSale.getQuantity());
           ps.setInt(4, ticketNumber);
           ps.executeUpdate();
        }
	}

	@Override
	public void updateTree(Tree tree) throws SQLException {

        connector = new Connect();
        String sql = "UPDATE tree SET tree_quantity = ? WHERE tree_name = ?";
        ps = connector.connect().prepareStatement(sql);
        ps.setInt(1, tree.getQuantity());
        ps.setString(2, tree.getName());
        ps.executeUpdate();

	}

	@Override
	public void updateFlower(Flower flower) throws SQLException { // TODO
        connector = new Connect();
        String sql = "UPDATE flower SET flower_quantity = ? WHERE flower_name = ?";
        ps = connector.connect().prepareStatement(sql);
        ps.setInt(1, flower.getQuantity());
        ps.setString(2, flower.getName());
        ps.executeUpdate();
	}

	@Override
	public void updateDeco(Decoration decoration) throws SQLException {

		connector = new Connect();
        String sql = "UPDATE decoration SET decoration_quantity = ? WHERE decoration_name = ?";

            ps = connector.connect().prepareStatement(sql);
            ps.setInt(1, decoration.getQuantity());
            ps.setString(2, decoration.getName());
            ps.executeUpdate();

	}

	@Override
	public String init() throws SQLException {

        String name = null;
        connector = new Connect();
        Statement statement = connector.connect().createStatement();
        rs = statement.executeQuery("SELECT flower_shop_name FROM flower_shop");
            if(rs.next()){
                name = rs.getString("flower_shop_name");
            }
        return name;
	}

	@Override
	public void createFlowerShop(String name) throws SQLException {

        connector = new Connect();
        String sql = "INSERT INTO flower_shop (flower_shop_name) values (?)";
        ps = connector.connect().prepareStatement(sql);
        ps.setString(1, name);
        ps.executeUpdate();
	}

    public int chechStock(String produType, String produName) throws SQLException {
        //region DEFINITION VARIABLES
        int resul = 0;

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        connector = new Connect();
        ps = connector.connect().prepareStatement(String.format("select %s_quantity from %s where %s_name like \"%s\"",
                produType, produType, produType, produName));
        rs = ps.executeQuery();

        while (rs.next()){
            resul = rs.getInt(String.format("%s_quantity", produType));
        }

        //endregion ACTIONS


        // OUT
        return resul;

    }
}

