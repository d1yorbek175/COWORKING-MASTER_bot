package org.example;

import java.sql.*;

public class DatabaseManager {

    private static final String URL = "jdbc:postgresql://localhost:5432/coworking_db";
    private static final String USER = "postgres";
    private static final String PASSWORD = "root";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void createTables() {
        String sql = """
            DROP TABLE IF EXISTS bronlar;
            DROP TABLE IF EXISTS users;
            DROP TABLE IF EXISTS pending_tolov;
            
            CREATE TABLE IF NOT EXISTS users (
                id BIGSERIAL PRIMARY KEY,
                chat_id BIGINT UNIQUE NOT NULL,
                phone VARCHAR(20),
                name VARCHAR(100),
                created_at TIMESTAMP DEFAULT NOW()
            );
            
            CREATE TABLE IF NOT EXISTS bronlar (
                id BIGSERIAL PRIMARY KEY,
                chat_id BIGINT NOT NULL,
                filial VARCHAR(100),
                tarif VARCHAR(50),
                sana VARCHAR(50),
                soat VARCHAR(20),
                muddat VARCHAR(50),
                tolov_turi VARCHAR(50),
                status VARCHAR(20) DEFAULT 'active',
                created_at TIMESTAMP DEFAULT NOW()
            );
            
            CREATE TABLE IF NOT EXISTS pending_tolov (
                chat_id BIGINT PRIMARY KEY,
                details VARCHAR(200)
            );
        """;

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Jadvallar yaratildi!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}