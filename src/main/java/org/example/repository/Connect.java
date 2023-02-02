package org.example.repository;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connect {

    private final static String url = "jdbc:mysql://localhost:3306/floristeria";
    private final static String user = "root";
    private final static String pass = "administrador"; // "1234"; //

    public Connection connect(){

    	Connection connection = null;
       
    	try {
            connection = DriverManager.getConnection(url, user, pass);
            //return connection;
        }catch (Exception e){
            e.printStackTrace();
        }
		return connection;

        
        
    }

}
