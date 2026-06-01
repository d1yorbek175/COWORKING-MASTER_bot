package org.example;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class BizZoneService {

    public SendMessage bizhaqimizda(Long chatId){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText("Asosiy menyuga xush kelibsiz! \uD83D\uDC47 Pastdagi tugmalar orqali kerakli bo'limni tanlang:");

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> rowList = new ArrayList<>();

        KeyboardRow row1 = new KeyboardRow();
        KeyboardButton button = new KeyboardButton();
        button.setText("\uD83C\uDFE2 Co working");
        row1.add(button);

        KeyboardButton button0 = new KeyboardButton();
        button0.setText("\uD83D\uDCCB Bron qilish");
        row1.add(button0);

        KeyboardRow row2 = new KeyboardRow();
        KeyboardButton button1 = new KeyboardButton();
        button1.setText("\uD83D\uDCDC Biz haqimizda");
        row2.add(button1);

        KeyboardButton button2 = new KeyboardButton();
        button2.setText("📞 Aloqa");
        row2.add(button2);

        KeyboardRow row3 = new KeyboardRow();
        KeyboardButton button3 = new KeyboardButton();
        button3.setText("❓ FAQ");
        row3.add(button3);

        KeyboardButton button4 = new KeyboardButton();
        button4.setText("📜 Mening bronlarim");
        row3.add(button4);

        rowList.add(row1);
        rowList.add(row2);
        rowList.add(row3);

        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setKeyboard(rowList);

        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        return sendMessage;
    }

    public SendPhoto bizhaqimizdabosilganda(Long chatId){
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setChatId(chatId);
        sendPhoto.setPhoto(new InputFile("https://raw.githubusercontent.com/telegramdesktop/tdesktop/dev/Telegram/Resources/art/bg_initial.jpg"));
        sendPhoto.setCaption("✨ BIZ HAQIMIZDA ✨\n" +
                "\n" +
                "\uD83C\uDFE2 Assalomu alaykum!\n" +
                "Bizning Coworking Hub — bu zamonaviy va qulay ish muhiti yaratishga mo'ljallangan maskan.\n" +
                "\n" +
                "\uD83D\uDCBB Bu yerda siz:\n" +
                "— Bemalol ishlashingiz\n" +
                "— Yangi g'oyalar ustida ishlashingiz\n" +
                "— Jamoa bilan hamkorlik qilishingiz mumkin\n" +
                "\n" +
                "\uD83D\uDE80 Biz sizga quyidagilarni taklif qilamiz:\n" +
                "✔\uFE0F Tezkor internet (Wi-Fi 6)\n" +
                "✔\uFE0F Qulay ish joylari (ergonomik mebellar)\n" +
                "✔\uFE0F Meeting xonalar (konferensiyalar uchun)\n" +
                "✔\uFE0F Do'stona muhit va Networking\n" +
                "\n" +
                "☕ Ishlash bilan birga dam olish uchun ham barcha sharoitlar (choy, kofe, shirinliklar) mavjud!\n" +
                "\n" +
                "\uD83E\uDD1D Bizning maqsadimiz — sizning rivojlanishingiz va muvaffaqiyatingizga hissa qo'shish!\n" +
                "\n" +
                "\uD83D\uDCCD Biz bilan birga ishlang va o'sishda davom eting!");

        return sendPhoto;
    }
}