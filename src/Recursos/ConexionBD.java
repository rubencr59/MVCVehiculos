/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Recursos;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author dam
 */
public class ConexionBD {

        static String bd = "bdvehiculos";
        static String user = "root";
        static String password = "root";
        static String port = "3306";
        static String url = "jdbc:mysql://localhost:" + port + "/" + bd;

        
        public static Connection getConnection(){
            Connection conexion = null;

            try {
                conexion = DriverManager.getConnection(url, user, password);
            } catch (SQLException e) {
            e.printStackTrace();
            }
            
            return conexion;
        }

     
    
}
