package br.dev.diego.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionUtil {

    private static String url = "jdbc:mysql://localhost:3306/java-curso?serverTimezone=America/Fortaleza";
    private static String username = "root";
    private static String password = "root";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }


}
