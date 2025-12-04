package com.unileste.projetofinal.utilitarios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    // URL de conexão com o MySQL do XAMPP
    private static final String URL =
            "jdbc:mysql://localhost:3306/banco_projeto?useSSL=false&serverTimezone=UTC";

    // Usuário e senha do MySQL no XAMPP
    private static final String USER = "root";
    private static final String PASSWORD = ""; // se tiver senha, coloque aqui

    // Método estático para obter uma conexão
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
