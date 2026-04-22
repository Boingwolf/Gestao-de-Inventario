package com.pt.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    private static final String URL = System.getenv().getOrDefault(
            "DB_URL",
            "jdbc:mysql://localhost:3306/inventario?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC");
    private static final String USER = System.getenv().getOrDefault("DB_USER", "root");
    private static final String PASSWORD = System.getenv().getOrDefault("DB_PASSWORD", "");

    public static Connection get() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            String mensagem = "Erro ao conectar à base de dados"
                    + " [url=" + URL
                    + ", user=" + USER
                    + ", sqlState=" + e.getSQLState()
                    + ", errorCode=" + e.getErrorCode()
                    + ", detalhe=" + e.getMessage() + "]";
            throw new RuntimeException(mensagem, e);
        }
    }
}
