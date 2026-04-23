package com.modvalley.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    public static Connection getConexion() throws SQLException {

        String url = "jdbc:mysql://localhost:3306/modvalley";
        String user = "root";
        String pwd = "1234";

        return DriverManager.getConnection(url, user, pwd);

    }
}
