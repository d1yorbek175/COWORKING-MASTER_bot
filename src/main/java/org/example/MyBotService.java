package org.example;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.send.SendLocation;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

public class MyBotService {

    public SendMessage teln(Long chatId){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText("Assalomu alaykum! Bizning xizmatlardan foydalanish uchun telefon raqamingizni yuboring:");

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> rowList = new ArrayList<>();

        KeyboardRow row = new KeyboardRow();
        KeyboardButton button = new KeyboardButton();
        button.setText("📱 Raqamni yuborish");
        button.setRequestContact(true);
        row.add(button);
        rowList.add(row);

        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setKeyboard(rowList);
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        return sendMessage;
    }

    public SendMessage bronqilish(Long chatId){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText("O'zingizga qulay bo'lgan filialni tanlang \uD83C\uDFE2");

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> rowList = new ArrayList<>();

        KeyboardRow row1 = new KeyboardRow();
        KeyboardButton button1 = new KeyboardButton();
        button1.setText("HUB Coworking");
        row1.add(button1);
        KeyboardButton button2 = new KeyboardButton();
        button2.setText("Rooms Coworking");
        row1.add(button2);

        KeyboardRow row2 = new KeyboardRow();
        KeyboardButton button3 = new KeyboardButton();
        button3.setText("C-Space Chust");
        row2.add(button3);
        KeyboardButton button4 = new KeyboardButton();
        button4.setText("Mall Vega");
        row2.add(button4);

        KeyboardRow row3 = new KeyboardRow();
        KeyboardButton button5 = new KeyboardButton();
        button5.setText("IMPACT.T technology hub");
        row3.add(button5);
        KeyboardButton button6 = new KeyboardButton();
        button6.setText("IMPACT.T Studio");
        row3.add(button6);

        KeyboardRow row4 = new KeyboardRow();
        KeyboardButton back = new KeyboardButton();
        back.setText("⬅\uFE0F Ortga");
        row4.add(back);

        rowList.add(row1);
        rowList.add(row2);
        rowList.add(row3);
        rowList.add(row4);

        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setKeyboard(rowList);
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        return sendMessage;
    }

    public SendPhoto filialMenu(Long chatId, String filialName, String photoUrl, String description){
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setChatId(chatId);
        sendPhoto.setPhoto(new InputFile(photoUrl));
        sendPhoto.setCaption("Siz tanlagan filial: " + filialName + "\n\n" + description);

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();

        List<InlineKeyboardButton> row1 = new ArrayList<>();
        InlineKeyboardButton button1 = new InlineKeyboardButton();
        button1.setText("\uD83D\uDCCB Bron qilish");
        button1.setCallbackData("bron_" + filialName);
        row1.add(button1);

        List<InlineKeyboardButton> row2 = new ArrayList<>();
        InlineKeyboardButton button2 = new InlineKeyboardButton();
        button2.setText("\uD83D\uDCCD Lokatsiyani ko'rish");
        button2.setCallbackData("loc_" + filialName);
        row2.add(button2);

        rowList.add(row1);
        rowList.add(row2);

        inlineKeyboardMarkup.setKeyboard(rowList);
        sendPhoto.setReplyMarkup(inlineKeyboardMarkup);
        return sendPhoto;
    }

    public SendMessage tarifMenu(Long chatId, String filialName){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(filialName + " uchun qaysi tarifda bron qilmoqchisiz?");

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();

        List<InlineKeyboardButton> row1 = new ArrayList<>();
        InlineKeyboardButton b1 = new InlineKeyboardButton();
        b1.setText("\uD83D\uDD52 Soatlik");
        b1.setCallbackData("tarif_soatlik_" + filialName);
        InlineKeyboardButton b2 = new InlineKeyboardButton();
        b2.setText("\uD83D\uDDD3 Kunlik");
        b2.setCallbackData("tarif_kunlik_" + filialName);
        row1.add(b1);
        row1.add(b2);

        List<InlineKeyboardButton> row2 = new ArrayList<>();
        InlineKeyboardButton b3 = new InlineKeyboardButton();
        b3.setText("\uD83D\uDCC6 Haftalik");
        b3.setCallbackData("tarif_haftalik_" + filialName);
        InlineKeyboardButton b4 = new InlineKeyboardButton();
        b4.setText("\uD83D\uDCC5 Oylik");
        b4.setCallbackData("tarif_oylik_" + filialName);
        row2.add(b3);
        row2.add(b4);

        rowList.add(row1);
        rowList.add(row2);

        inlineKeyboardMarkup.setKeyboard(rowList);
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);
        return sendMessage;
    }

    public SendMessage sanaTanlash(Long chatId, String filialName, String tarifType, int year, int month) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);

        String[] oylar = {"Yanvar", "Fevral", "Mart", "Aprel", "May", "Iyun",
                "Iyul", "Avgust", "Sentabr", "Oktabr", "Noyabr", "Dekabr"};

        sendMessage.setText("📅 *" + oylar[month-1] + " " + year + "* — bron qilish kunini tanlang:");
        sendMessage.setParseMode("Markdown");

        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();

        List<InlineKeyboardButton> navRow = new ArrayList<>();
        InlineKeyboardButton prev = new InlineKeyboardButton();
        prev.setText("◀");
        int prevMonth = month == 1 ? 12 : month - 1;
        int prevYear = month == 1 ? year - 1 : year;
        prev.setCallbackData("kal_" + filialName + "_" + tarifType + "_" + prevYear + "_" + prevMonth);

        InlineKeyboardButton title = new InlineKeyboardButton();
        title.setText(oylar[month-1] + " " + year);
        title.setCallbackData("ignore");

        InlineKeyboardButton next = new InlineKeyboardButton();
        next.setText("▶");
        int nextMonth = month == 12 ? 1 : month + 1;
        int nextYear = month == 12 ? year + 1 : year;
        next.setCallbackData("kal_" + filialName + "_" + tarifType + "_" + nextYear + "_" + nextMonth);

        navRow.add(prev);
        navRow.add(title);
        navRow.add(next);
        rows.add(navRow);

        List<InlineKeyboardButton> weekRow = new ArrayList<>();
        for (String d : new String[]{"Du", "Se", "Ch", "Pa", "Ju", "Sh", "Ya"}) {
            InlineKeyboardButton b = new InlineKeyboardButton();
            b.setText(d);
            b.setCallbackData("ignore");
            weekRow.add(b);
        }
        rows.add(weekRow);

        YearMonth yearMonth = YearMonth.of(year, month);
        int daysInMonth = yearMonth.lengthOfMonth();
        LocalDate today = LocalDate.now();
        DayOfWeek firstDay = yearMonth.atDay(1).getDayOfWeek();
        int startOffset = firstDay.getValue() - 1;

        List<InlineKeyboardButton> row = new ArrayList<>();
        for (int i = 0; i < startOffset; i++) {
            InlineKeyboardButton empty = new InlineKeyboardButton();
            empty.setText(" ");
            empty.setCallbackData("ignore");
            row.add(empty);
        }

        for (int day = 1; day <= daysInMonth; day++) {
            LocalDate date = LocalDate.of(year, month, day);
            InlineKeyboardButton btn = new InlineKeyboardButton();

            if (date.isBefore(today)) {
                btn.setText("·");
                btn.setCallbackData("ignore");
            } else {
                btn.setText(String.valueOf(day));
                btn.setCallbackData("sana_" + filialName + "_" + tarifType + "_" + year + "_" + month + "_" + day);
            }
            row.add(btn);

            if (row.size() == 7) {
                rows.add(row);
                row = new ArrayList<>();
            }
        }
        if (!row.isEmpty()) {
            while (row.size() < 7) {
                InlineKeyboardButton empty = new InlineKeyboardButton();
                empty.setText(" ");
                empty.setCallbackData("ignore");
                row.add(empty);
            }
            rows.add(row);
        }

        markup.setKeyboard(rows);
        sendMessage.setReplyMarkup(markup);
        return sendMessage;
    }

    public SendMessage soatTanlash(Long chatId, String filialName, String sana) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText("🕐 *" + sana + "* — qaysi soatda boshlaysiz?");
        sendMessage.setParseMode("Markdown");

        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();

        int[] soatlar = {8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22};
        List<InlineKeyboardButton> row = new ArrayList<>();

        for (int soat : soatlar) {
            InlineKeyboardButton btn = new InlineKeyboardButton();
            String label = String.format("%02d:00", soat);
            btn.setText(label);
            btn.setCallbackData("soat_" + filialName + "_" + sana + "_" + label);
            row.add(btn);

            if (row.size() == 3) {
                rows.add(row);
                row = new ArrayList<>();
            }
        }
        if (!row.isEmpty()) rows.add(row);

        markup.setKeyboard(rows);
        sendMessage.setReplyMarkup(markup);
        return sendMessage;
    }

    public SendMessage muddatSoatlik(Long chatId, String filialName, String sana, String soat) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText("⏱ *" + sana + " " + soat + "* — necha soatga bron qilasiz?");
        sendMessage.setParseMode("Markdown");

        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();

        List<InlineKeyboardButton> row = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            InlineKeyboardButton btn = new InlineKeyboardButton();
            btn.setText(i + " soat");
            btn.setCallbackData("confirm_" + filialName + "_" + sana + "_" + soat + "_" + i + "soat");
            row.add(btn);
            if (row.size() == 3) {
                rows.add(row);
                row = new ArrayList<>();
            }
        }
        if (!row.isEmpty()) rows.add(row);

        markup.setKeyboard(rows);
        sendMessage.setReplyMarkup(markup);
        return sendMessage;
    }

    public SendMessage muddatTanlash(Long chatId, String filialName, String tarifType, String sana) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText("⏱ *" + sana + "* — qancha muddatga bron qilasiz?");
        sendMessage.setParseMode("Markdown");

        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();

        String label = tarifType.equals("kunlik") ? " kun" :
                tarifType.equals("haftalik") ? " hafta" : " oy";
        int max = tarifType.equals("oylik") ? 3 : 6;

        List<InlineKeyboardButton> row = new ArrayList<>();
        for (int i = 1; i <= max; i++) {
            InlineKeyboardButton btn = new InlineKeyboardButton();
            btn.setText(i + label);
            btn.setCallbackData("confirm_" + filialName + "_" + sana + "__" + i + label);
            row.add(btn);
            if (row.size() == 3) {
                rows.add(row);
                row = new ArrayList<>();
            }
        }
        if (!row.isEmpty()) rows.add(row);

        markup.setKeyboard(rows);
        sendMessage.setReplyMarkup(markup);
        return sendMessage;
    }

    public SendMessage joyBand(Long chatId, String filial, String sana, String soat) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText("❌ *Kechirasiz!*\n\n" +
                "🏢 " + filial + "\n" +
                "📅 " + sana + "\n" +
                "🕐 " + soat + "\n\n" +
                "Bu joy *band*! Iltimos, boshqa sana yoki soatni tanlang.");
        sendMessage.setParseMode("Markdown");
        return sendMessage;
    }

    public SendMessage tolovMenu(Long chatId, String details){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText("Siz tanladingiz:\n" + details + "\n\nTo'lov turini tanlang \uD83D\uDCB3:");

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();

        List<InlineKeyboardButton> row1 = new ArrayList<>();
        InlineKeyboardButton b1 = new InlineKeyboardButton();
        b1.setText("💳 Click orqali");
        b1.setCallbackData("tolov_Click_" + details);
        InlineKeyboardButton b2 = new InlineKeyboardButton();
        b2.setText("💳 Payme orqali");
        b2.setCallbackData("tolov_Payme_" + details);
        row1.add(b1);
        row1.add(b2);

        List<InlineKeyboardButton> row2 = new ArrayList<>();
        InlineKeyboardButton b3 = new InlineKeyboardButton();
        b3.setText("💵 Naqd pul (joyida)");
        b3.setCallbackData("tolov_Naqd_" + details);
        row2.add(b3);

        rowList.add(row1);
        rowList.add(row2);

        inlineKeyboardMarkup.setKeyboard(rowList);
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);
        return sendMessage;
    }

    public SendMessage tolovRekvizit(Long chatId, String tolovTuri, String details) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        String text = "💳 *" + tolovTuri + " orqali to'lov:*\n\n" +
                "Karta raqami: `5555 3630 0972 9369`\n\n" +
                "Bron: " + details + "\n\n" +
                "To'lov qilgandan so'ng *screenshot* yuboring ✅";
        sendMessage.setText(text);
        sendMessage.setParseMode("Markdown");
        return sendMessage;
    }

    public SendMessage screenshotQabul(Long chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText("✅ Screenshot qabul qilindi!\n\nAdminlar tez orada tekshirib, broningizni tasdiqlashadi. 😊");
        return sendMessage;
    }

    public SendPhoto adminPhotoNotification(Long adminId, Long clientChatId, String details, String userName, String fileId) {
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setChatId(adminId);
        sendPhoto.setPhoto(new InputFile(fileId));
        sendPhoto.setCaption(
                "🔔 *Yangi to'lov keldi!*\n\n" +
                        "👤 Ism: " + userName + "\n" +
                        "🆔 Chat ID: `" + clientChatId + "`\n" +
                        "📋 Bron: " + details
        );
        sendPhoto.setParseMode("Markdown");

        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();
        List<InlineKeyboardButton> row = new ArrayList<>();

        InlineKeyboardButton tasdiqlandi = new InlineKeyboardButton();
        tasdiqlandi.setText("✅ Tasdiqlandi");
        tasdiqlandi.setCallbackData("admin_approve_" + clientChatId);

        InlineKeyboardButton tasdiqlanmadi = new InlineKeyboardButton();
        tasdiqlanmadi.setText("❌ Tasdiqlanmadi");
        tasdiqlanmadi.setCallbackData("admin_reject_" + clientChatId);

        row.add(tasdiqlandi);
        row.add(tasdiqlanmadi);
        rows.add(row);

        markup.setKeyboard(rows);
        sendPhoto.setReplyMarkup(markup);
        return sendPhoto;
    }

    public SendMessage bronTasdiqlandi(Long chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText("🎉 *Tabriklaymiz!*\n\nBroningiz admin tomonidan *tasdiqlandi!*\n\nSizni coworkingimizda ko'rishdan xursand bo'lamiz! 😊");
        sendMessage.setParseMode("Markdown");
        return sendMessage;
    }

    public SendMessage bronTasdiqlanmadi(Long chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText("❌ *Afsuski, broningiz tasdiqlanmadi.*\n\nTo'lov tekshirishda muammo aniqlandi. Iltimos, qayta urinib ko'ring yoki admin bilan bog'laning: @CoworkingAdmin");
        sendMessage.setParseMode("Markdown");
        return sendMessage;
    }

    public SendMessage finalTasdiq(Long chatId, String details, String tolovTuri){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText("✅ Bron muvaffaqiyatli qabul qilindi!\n\n" +
                "Siz tanladingiz: " + details + "\n" +
                "To'lov turi: " + tolovTuri + "\n\n" +
                "Tez orada adminlarimiz siz bilan bog'lanishadi. \uD83D\uDE0A");
        return sendMessage;
    }

    public SendMessage ortgaFilialMenu(Long chatId){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText("Ortga qaytish uchun tugmani bosing.");

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> rowList = new ArrayList<>();

        KeyboardRow row = new KeyboardRow();
        KeyboardButton back = new KeyboardButton();
        back.setText("⬅\uFE0F Filiallar menyusiga");
        row.add(back);
        rowList.add(row);

        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setKeyboard(rowList);
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        return sendMessage;
    }

    public SendLocation sendFilialLocation(Long chatId, String filialName) {
        SendLocation location = new SendLocation();
        location.setChatId(chatId);
        if (filialName.contains("HUB") || filialName.contains("Rooms")) {
            location.setLatitude(41.311081);
            location.setLongitude(69.240562);
        } else if (filialName.contains("Chust")) {
            location.setLatitude(41.000300);
            location.setLongitude(71.233500);
        } else {
            location.setLatitude(41.299496);
            location.setLongitude(69.240073);
        }
        return location;
    }

    public SendMessage aloqa(Long chatId){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText("\uD83D\uDCDE *Biz bilan bog'lanish*\n\n" +
                "\uD83D\uDC64 Admin: @CoworkingAdmin\n" +
                "\uD83D\uDCF1 Telefon: +998 90 123 45 67\n" +
                "\uD83D\uDCE7 Email: info@coworking.uz\n\n" +
                "Sizga xizmat ko'rsatishdan mamnunmiz! \uD83D\uDE0A");
        sendMessage.setParseMode("Markdown");
        return sendMessage;
    }

    public SendMessage faq(Long chatId){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText("❓ *Ko'p beriladigan savollar*\n\n" +
                "1️⃣ *Ish vaqti qanday?*\n— 24/7 ishlaydi!\n\n" +
                "2️⃣ *Wi-Fi tezligi?*\n— 500 Mb/s gacha.\n\n" +
                "3️⃣ *Ovqat olib kelsam bo'ladimi?*\n— Ha, albatta.\n\n" +
                "4️⃣ *Meeting room?*\n— Aloqa bo'limi orqali bog'laning.");
        sendMessage.setParseMode("Markdown");
        return sendMessage;
    }

    public SendMessage meningBronlarim(Long chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);

        List<String> bronlar = BronRepository.getUserBronlar(chatId);

        if (bronlar.isEmpty()) {
            sendMessage.setText("❌ Sizda hali hech qanday bron yo'q.\n\nBron qilish uchun «📋 Bron qilish» tugmasini bosing.");
        } else {
            StringBuilder text = new StringBuilder("📜 *Sizning bronlaringiz:*\n\n");
            for (int i = 0; i < bronlar.size(); i++) {
                text.append("*").append(i + 1).append("-bron:*\n");
                text.append(bronlar.get(i));
                text.append("\n\n─────────────────\n\n");
            }
            sendMessage.setText(text.toString());
            sendMessage.setParseMode("Markdown");
        }
        return sendMessage;
    }
}