package com.modvalley.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    public static Connection conectar() {

        String url = "jdbc:mysql://localhost:3306/modvalley";
        String user = "root";
        String pwd = "mysql";
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection(url, user, pwd);
        } catch (ClassNotFoundException e) {
            System.out.println("Error: ¡No se ha encontrado el archivo .jar del conector!");
        } catch (SQLException e) {
            System.out.println("Error de SQL: " + e.getMessage());
        }
        return conn;

    }

}
