package org.example;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.*;

public class DatabaseManager {

    private static String URL = System.getenv("DB_URL");
    private static String USER = System.getenv("DB_USER");
    private static String PASSWORD = System.getenv("DB_PASSWORD");

    static {
        String databaseUrl = System.getenv("DATABASE_URL");
        if (databaseUrl != null && !databaseUrl.isBlank()) {
            // Parse DATABASE_URL like: postgres://user:pass@host:port/dbname
            try {
                URI dbUri = new URI(databaseUrl);
                String userInfo = dbUri.getUserInfo();
                if (userInfo != null) {
                    String[] parts = userInfo.split(":");
                    if (parts.length >= 1) USER = parts[0];
                    if (parts.length >= 2) PASSWORD = parts[1];
                }
                String host = dbUri.getHost();
                int port = dbUri.getPort();
                String path = dbUri.getPath();
                URL = "jdbc:postgresql://" + host + ":" + port + path;
            } catch (URISyntaxException e) {
                System.err.println("DATABASE_URL parsing failed: " + e.getMessage());
            }
        }

        if (URL == null || URL.isBlank()) {
            // fallback default (for local dev)
            URL = "jdbc:postgresql://localhost:5432/coworking_db";
        }
        if (USER == null) USER = "postgres";
        if (PASSWORD == null) PASSWORD = "root";
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void createTables() {
        String sql = """
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
            System.err.println("Failed to create tables: " + e.getMessage());
            e.printStackTrace();
        }
    }
}