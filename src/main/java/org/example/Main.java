package org.example;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Main {
    public static void main(String[] args) {
        DatabaseManager.createTables();
        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(new MyBot());
            System.out.println("🚀 Bot muvaffaqiyatli ishga tushdi!");
        } catch (TelegramApiRequestException e) {
            System.err.println("❌ Botni ishga tushirishda xatolik yuz berdi (Webhook xatosi bo'lishi mumkin): " + e.getMessage());
            e.printStackTrace();
        } catch (TelegramApiException e) {
            System.err.println("❌ Botni ishga tushirishda xatolik yuz berdi: " + e.getMessage());
            e.printStackTrace();
        }
    }
}