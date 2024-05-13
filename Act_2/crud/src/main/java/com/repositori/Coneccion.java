package com.repositori;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Coneccion {
    private static Coneccion instance;
    private Connection conn;

    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3308/bduoc";
    static final String USER = "root";
    static final String PASS = "secret";

    Coneccion() throws ClassNotFoundException {
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Conexión establecida.");
        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
        }
    }

    public static Coneccion getInstance() throws ClassNotFoundException {
        if (instance == null) {
            instance = new Coneccion();
        }
        return instance;
    }

    public Connection getConnection() {
        return conn;
    }

    public void closeConnection() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            System.err.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }
    public PreparedStatement prepareStatement(String query) throws SQLException {
        if (conn == null) {
            throw new SQLException("La conexión no está inicializada.");
        }
        return conn.prepareStatement(query);
    }
}

