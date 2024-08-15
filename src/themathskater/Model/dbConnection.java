/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package themathskater.Model;

/**
 *
 * @author Christina.2114893 <christinadeborah458@gmail.com>
 */

import java.sql.*;

public class dbConnection {

    private static String url = "jdbc:derby:database;create=true";
    private static String drivername = "org.apache.derby.jdbc.EmbeddedDriver";
    private static String username = "christina";
    private static String password = "chris123456789..";
    private static Connection con;
    private static String urlstring;
    private static Statement stmt = null;

    public static dbConnection dbConnection;
 
//to ensure database is connected to system
    public Connection creatConnection() {
        if(con == null) {
            try {
                Class.forName(drivername);

                con = DriverManager.getConnection(url, username, password);
                System.out.println("connected..");
            } catch (Exception e) {
                System.out.println("something went wrong..." + e);
            }
        }
        return con;
    }


    private dbConnection(){
        creatConnection();
        createTable();
    }

    public static dbConnection getInstance(){
        if(dbConnection == null){
            dbConnection = new dbConnection();
        }

        return dbConnection;
    }


    public void closeConnection(){
        try {
            if(con != null && !con.isClosed()) con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
   //creating the database table to fetch data 
    private void createTable(){
        String TABLE_NAME = "Game";
        con = creatConnection();
        try{
            stmt = con.createStatement();
            DatabaseMetaData dbm = con.getMetaData();
            ResultSet tables = dbm.getTables(null,null,TABLE_NAME.toUpperCase(),null);

            if(tables.next()) System.out.println(TABLE_NAME+" table already exist");
            else{
                stmt.execute("CREATE TABLE "+TABLE_NAME+" (" +
                        "email varchar(40) primary key," +
                        "name varchar(25) ," +
                        "age int," +
                        "password varchar(25)," +
                        "gender varchar(15)," +
                        "high_score int" +
                        ")");

                System.out.println("Table created.....");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        finally {

        }
    }






   
}