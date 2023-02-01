package org.example.repository;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connect {

    private static String url = "jdbc:mysql://localhost:3306/floristeria";
    private static String user = "root";
    private static String pass = "1234"; // "administrador";

    public Connection connect(){

        try {
            Connection connection = DriverManager.getConnection(url, user, pass);
            return connection;
        }catch (Exception e){
            e.printStackTrace();
        }

        //return connection;
        return null;
    }

}
