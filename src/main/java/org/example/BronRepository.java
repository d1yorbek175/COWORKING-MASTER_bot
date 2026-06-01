package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BronRepository {

    public static void saveUser(Long chatId, String phone, String name) {
        String sql = "INSERT INTO users (chat_id, phone, name) VALUES (?, ?, ?) ON CONFLICT (chat_id) DO UPDATE SET phone = ?, name = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, chatId);
            stmt.setString(2, phone);
            stmt.setString(3, name);
            stmt.setString(4, phone);
            stmt.setString(5, name);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String getUserName(Long chatId) {
        String sql = "SELECT name FROM users WHERE chat_id = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, chatId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return rs.getString("name");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Noma'lum";
    }

    public static void saveBron(Long chatId, String filial, String tarif, String sana, String soat, String muddat, String tolovTuri) {
        String sql = "INSERT INTO bronlar (chat_id, filial, tarif, sana, soat, muddat, tolov_turi) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, chatId);
            stmt.setString(2, filial);
            stmt.setString(3, tarif);
            stmt.setString(4, sana);
            stmt.setString(5, soat);
            stmt.setString(6, muddat);
            stmt.setString(7, tolovTuri);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<String> getUserBronlar(Long chatId) {
        List<String> bronlar = new ArrayList<>();
        String sql = "SELECT filial, tarif, sana, soat, muddat, tolov_turi, status FROM bronlar WHERE chat_id = ? ORDER BY created_at DESC";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, chatId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String soat = rs.getString("soat");
                String bron = String.format(
                        "🏢 Filial: %s\n📋 Tarif: %s\n📅 Sana: %s\n🕐 Soat: %s\n⏱ Muddat: %s\n💳 To'lov: %s\n📌 Status: %s",
                        rs.getString("filial"),
                        rs.getString("tarif"),
                        rs.getString("sana"),
                        soat != null && !soat.isEmpty() ? soat : "—",
                        rs.getString("muddat"),
                        rs.getString("tolov_turi"),
                        rs.getString("status")
                );
                bronlar.add(bron);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bronlar;
    }

    public static void savePendingDetails(Long chatId, String details) {
        String sql = "INSERT INTO pending_tolov (chat_id, details) VALUES (?, ?) ON CONFLICT (chat_id) DO UPDATE SET details = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, chatId);
            stmt.setString(2, details);
            stmt.setString(3, details);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String getPendingDetails(Long chatId) {
        String sql = "SELECT details FROM pending_tolov WHERE chat_id = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, chatId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return rs.getString("details");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static void deletePending(Long chatId) {
        String sql = "DELETE FROM pending_tolov WHERE chat_id = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, chatId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateLastBronTolov(Long chatId, String tolovTuri) {
        String sql = "UPDATE bronlar SET tolov_turi = ? WHERE id = (SELECT id FROM bronlar WHERE chat_id = ? ORDER BY created_at DESC LIMIT 1)";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, tolovTuri);
            stmt.setLong(2, chatId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean isBronBand(String filial, String sana, String soat) {
        String sql = "SELECT COUNT(*) FROM bronlar WHERE filial = ? AND sana = ? AND soat = ? AND status = 'active'";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, filial);
            stmt.setString(2, sana);
            stmt.setString(3, soat);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return rs.getInt(1) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}