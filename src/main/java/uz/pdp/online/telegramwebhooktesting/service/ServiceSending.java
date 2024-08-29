package uz.pdp.online.telegramwebhooktesting.service;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import uz.pdp.online.telegramwebhooktesting.setting.TelegramBot;

@Service
public class ServiceSending {


    private final TelegramBot telegramBot;

    public ServiceSending(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

    public void sendMessage(Update update) {
        SendMessage message = SendMessage.builder().chatId(update.getMessage().getChatId()).text(update.getMessage().getText()).replyToMessageId(5).build();
        try {
            telegramBot.execute(message);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
