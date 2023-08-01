package com.github.vadmurzakov.app.config.client;

import com.github.vadmurzakov.app.config.properties.TelegramProperties;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.response.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TelegramBotExecutor extends TelegramBot {

    private final TelegramProperties telegramProperties;

    @Autowired
    public TelegramBotExecutor(TelegramProperties telegramProperties) {
        super(telegramProperties.getToken());
        this.telegramProperties = telegramProperties;
    }

    public TelegramProperties properties() {
        return telegramProperties;
    }

    @Override
    public <T extends BaseRequest<T, R>, R extends BaseResponse> R execute(BaseRequest<T, R> request) {
        R execute = super.execute(request);
        if (!execute.isOk()) {
            log.error("Вызов api закончился ошибкой: {}", execute.description());
            throw new RuntimeException(execute.description());
        }
        return execute;
    }
}
