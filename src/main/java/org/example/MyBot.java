package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.time.LocalDate;

public class MyBot extends TelegramLongPollingBot {
    MyBotService myBotService = new MyBotService();
    BizZoneService bizZoneService = new BizZoneService();

    private static final Long ADMIN_ID;

    static {
        String adminEnv = System.getenv("ADMIN_ID");
        Long admin = 7076305865L;
        try {
            if (adminEnv != null && !adminEnv.isBlank()) admin = Long.parseLong(adminEnv);
        } catch (NumberFormatException ignored) {
        }
        ADMIN_ID = admin;
    }

    @Override
    public void onUpdateReceived(Update update){

        if (update.hasMessage() && update.getMessage().hasPhoto()) {
            Long chatId = update.getMessage().getChatId();
            String fileId = update.getMessage().getPhoto()
                    .get(update.getMessage().getPhoto().size() - 1).getFileId();

            String details = BronRepository.getPendingDetails(chatId);

            if (details != null && !details.isEmpty()) {
                String userName = BronRepository.getUserName(chatId);
                try {
                    execute(myBotService.screenshotQabul(chatId));
                    execute(myBotService.adminPhotoNotification(ADMIN_ID, chatId, details, userName, fileId));
                    BronRepository.deletePending(chatId);
                    execute(bizZoneService.bizhaqimizda(chatId));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            } else {
                SendMessage msg = new SendMessage();
                msg.setChatId(chatId);
                msg.setText("📸 Bu rasmning FILE_ID kodi:\n\n`" + fileId + "`\n\n(Buni nusxalab, koddagi linklar o'rniga qo'ysangiz 100% xatosiz ishlaydi!)");
                msg.setParseMode("Markdown");
                try {
                    execute(msg);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        }

        if (update.hasMessage() && update.getMessage().hasText()){
            String text = update.getMessage().getText();
            Long chatId = update.getMessage().getChatId();

            if (text.equals("/start")) {
                try {
                    execute(myBotService.teln(chatId));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            } else if (text.equals("\uD83D\uDCDC Biz haqimizda")) {
                try {
                    execute(bizZoneService.bizhaqimizdabosilganda(chatId));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            } else if (text.equals("\uD83C\uDFE2 Co working") || text.equals("\uD83D\uDCCB Bron qilish")) {
                try {
                    execute(myBotService.bronqilish(chatId));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            } else if (text.equals("📞 Aloqa")) {
                try {
                    execute(myBotService.aloqa(chatId));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            } else if (text.equals("❓ FAQ")) {
                try {
                    execute(myBotService.faq(chatId));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            } else if (text.equals("📜 Mening bronlarim")) {
                try {
                    execute(myBotService.meningBronlarim(chatId));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            } else if (text.equals("⬅\uFE0F Ortga") || text.equals("⬅\uFE0F Filiallar menyusiga")) {
                try {
                    execute(myBotService.bronqilish(chatId));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            } else if (text.contains("HUB Coworking")) {
                try {
                    execute(myBotService.filialMenu(chatId, "HUB Coworking", "https://t.me/uzbek_frontend7/152", "Shahar markazida joylashgan premium darajadagi coworking!"));
                    execute(myBotService.ortgaFilialMenu(chatId));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            } else if (text.contains("Rooms Coworking")) {
                try {
                    execute(myBotService.filialMenu(chatId, "Rooms Coworking", "https://t.me/uzbek_frontend7/153", "Sokin muhit va jamoaviy ishlash uchun ideal maskan!"));
                    execute(myBotService.ortgaFilialMenu(chatId));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            } else if (text.contains("C-Space Chust")) {
                try {
                    execute(myBotService.filialMenu(chatId, "C-Space Chust", "https://t.me/uzbek_frontend7/154", "IT mutaxassislar va frilanserlar uchun maxsus joy!"));
                    execute(myBotService.ortgaFilialMenu(chatId));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            } else if (text.contains("Mall Vega")) {
                try {
                    execute(myBotService.filialMenu(chatId, "Mall Vega", "https://t.me/uzbek_frontend7/155", "Savdo markazi ichida joylashgan qulay va fayzli coworking."));
                    execute(myBotService.ortgaFilialMenu(chatId));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            } else if (text.contains("IMPACT.T technology hub")) {
                try {
                    execute(myBotService.filialMenu(chatId, "IMPACT.T technology hub", "https://t.me/uzbek_frontend7/156", "Katta jamoalar va startaplar uchun texnologik xab."));
                    execute(myBotService.ortgaFilialMenu(chatId));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            } else if (text.contains("IMPACT.T Studio")) {
                try {
                    execute(myBotService.filialMenu(chatId, "IMPACT.T Studio", "https://t.me/uzbek_frontend7/157", "Ijodkorlar va dizaynerlar uchun maxsus studiya formati."));
                    execute(myBotService.ortgaFilialMenu(chatId));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            if (text.equals("⬅\uFE0F Ortga")){
                try {
                    execute(bizZoneService.bizhaqimizda(chatId));
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        if (update.hasMessage() && update.getMessage().hasContact()) {
            Long chatId = update.getMessage().getChatId();
            String phone = update.getMessage().getContact().getPhoneNumber();
            String name = update.getMessage().getContact().getFirstName();
            if (update.getMessage().getContact().getLastName() != null) {
                name += " " + update.getMessage().getContact().getLastName();
            }
            BronRepository.saveUser(chatId, phone, name);
            try {
                execute(bizZoneService.bizhaqimizda(chatId));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }

        if (update.hasCallbackQuery()) {
            String callData = update.getCallbackQuery().getData();
            Long chatId = update.getCallbackQuery().getMessage().getChatId();
            Integer messageId = update.getCallbackQuery().getMessage().getMessageId();

            try {
                if (callData.equals("ignore")) {
                    // hech narsa
                }
                else if (callData.startsWith("loc_")) {
                    String filialName = callData.substring(4);
                    execute(myBotService.sendFilialLocation(chatId, filialName));
                }
                else if (callData.startsWith("bron_")) {
                    String filialName = callData.substring(5);
                    DeleteMessage deleteMessage = new DeleteMessage(chatId.toString(), messageId);
                    execute(deleteMessage);
                    execute(myBotService.tarifMenu(chatId, filialName));
                }
                else if (callData.startsWith("tarif_")) {
                    String[] parts = callData.split("_", 3);
                    String tarifType = parts[1];
                    String filialName = parts[2];
                    DeleteMessage deleteMessage = new DeleteMessage(chatId.toString(), messageId);
                    execute(deleteMessage);
                    LocalDate today = LocalDate.now();
                    execute(myBotService.sanaTanlash(chatId, filialName, tarifType, today.getYear(), today.getMonthValue()));
                }
                else if (callData.startsWith("kal_")) {
                    String[] parts = callData.split("_", 5);
                    String filialName = parts[1];
                    String tarifType = parts[2];
                    int year = Integer.parseInt(parts[3]);
                    int month = Integer.parseInt(parts[4]);
                    DeleteMessage deleteMessage = new DeleteMessage(chatId.toString(), messageId);
                    execute(deleteMessage);
                    execute(myBotService.sanaTanlash(chatId, filialName, tarifType, year, month));
                }
                else if (callData.startsWith("sana_")) {
                    String[] parts = callData.split("_", 6);
                    String filialName = parts[1];
                    String tarifType = parts[2];
                    String sana = parts[3] + "-" + String.format("%02d", Integer.parseInt(parts[4])) + "-" + String.format("%02d", Integer.parseInt(parts[5]));
                    DeleteMessage deleteMessage = new DeleteMessage(chatId.toString(), messageId);
                    execute(deleteMessage);

                    if (tarifType.equals("soatlik")) {
                        execute(myBotService.soatTanlash(chatId, filialName, sana));
                    } else {
                        execute(myBotService.muddatTanlash(chatId, filialName, tarifType, sana));
                    }
                }
                else if (callData.startsWith("soat_")) {
                    String[] parts = callData.split("_", 4);
                    String filialName = parts[1];
                    String sana = parts[2];
                    String soat = parts[3];
                    DeleteMessage deleteMessage = new DeleteMessage(chatId.toString(), messageId);
                    execute(deleteMessage);
                    execute(myBotService.muddatSoatlik(chatId, filialName, sana, soat));
                }
                else if (callData.startsWith("confirm_")) {
                    String sub = callData.substring(8);
                    int firstUnderscore = sub.indexOf("_");
                    String filialName = sub.substring(0, firstUnderscore);
                    String rest = sub.substring(firstUnderscore + 1);

                    String[] restParts = rest.split("_");
                    String sana = restParts.length > 0 ? restParts[0] : "";
                    String soat = restParts.length > 1 && restParts[1].contains(":") ? restParts[1] : "";
                    String muddat = restParts[restParts.length - 1];
                    String tarif = soat.isEmpty() ? "kunlik/haftalik/oylik" : "soatlik";

                    DeleteMessage deleteMessage = new DeleteMessage(chatId.toString(), messageId);
                    execute(deleteMessage);

                    // Band ekanligini tekshirish
                    if (!soat.isEmpty() && BronRepository.isBronBand(filialName, sana, soat)) {
                        execute(myBotService.joyBand(chatId, filialName, sana, soat));
                        LocalDate today = LocalDate.now();
                        execute(myBotService.sanaTanlash(chatId, filialName, tarif, today.getYear(), today.getMonthValue()));
                    } else {
                        String details = filialName + " | " + sana +
                                (soat.isEmpty() ? "" : " " + soat) +
                                " | " + muddat;

                        BronRepository.saveBron(chatId, filialName, tarif, sana, soat, muddat, "aniqlanmagan");
                        execute(myBotService.tolovMenu(chatId, details));
                    }
                }
                else if (callData.startsWith("tolov_")) {
                    String[] parts = callData.split("_", 3);
                    String tolovTuri = parts[1];
                    String details = parts[2];

                    DeleteMessage deleteMessage = new DeleteMessage(chatId.toString(), messageId);
                    execute(deleteMessage);

                    BronRepository.updateLastBronTolov(chatId, tolovTuri);

                    if (tolovTuri.equals("Naqd")) {
                        execute(myBotService.finalTasdiq(chatId, details, tolovTuri));
                        execute(bizZoneService.bizhaqimizda(chatId));
                    } else {
                        execute(myBotService.tolovRekvizit(chatId, tolovTuri, details));
                        BronRepository.savePendingDetails(chatId, details);
                    }
                }
                else if (callData.startsWith("admin_approve_")) {
                    Long clientChatId = Long.parseLong(callData.substring(14));
                    DeleteMessage deleteMessage = new DeleteMessage(chatId.toString(), messageId);
                    execute(deleteMessage);
                    execute(myBotService.bronTasdiqlandi(clientChatId));
                }
                else if (callData.startsWith("admin_reject_")) {
                    Long clientChatId = Long.parseLong(callData.substring(13));
                    DeleteMessage deleteMessage = new DeleteMessage(chatId.toString(), messageId);
                    execute(deleteMessage);
                    execute(myBotService.bronTasdiqlanmadi(clientChatId));
                }
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String getBotUsername(){
        return "@CoworkingHub_bot";
    }

    @Override
    public String getBotToken(){
        String token = System.getenv("BOT_TOKEN");
        if (token == null || token.isBlank()) {
            throw new RuntimeException("BOT_TOKEN is not set. Set environment variable BOT_TOKEN with your Telegram bot token.");
        }
        return token;
    }
}