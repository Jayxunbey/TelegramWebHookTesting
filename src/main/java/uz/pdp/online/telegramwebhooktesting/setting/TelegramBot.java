package uz.pdp.online.telegramwebhooktesting.setting;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.Serializable;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Component
public class TelegramBot extends TelegramWebhookBot {
    @Value("${telegram.bot.name}")
    private String botName;

    @Value("${telegram.bot.token}")
    private String botToken;

    @Value("${telegram.bot.webhook-url}")
    private String botUri;

    @PostConstruct
    public void init() {
//        updateProcessor.registerBot(this);
        try {
            var setWebhook = SetWebhook.builder()
                    .url(botUri)
                    .build();
            this.setWebhook(setWebhook);
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }



    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        return null;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public String getBotPath() {
        return "/update";
    }

    @Override
    public String getBotUsername() {
        return botName;
    }

}
