package com.pt.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    private static final String URL = "jdbc:mysql://localhost:3306/inventario";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection get() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Erro ao conectar à base de dados: " + e.getMessage());
            return null;
        }
    }
}
