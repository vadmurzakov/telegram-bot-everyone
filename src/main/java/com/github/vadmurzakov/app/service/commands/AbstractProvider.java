package com.github.vadmurzakov.app.service.commands;

import com.github.vadmurzakov.app.config.client.TelegramBotExecutor;
import com.pengrad.telegrambot.model.Message;
import org.apache.commons.text.StringEscapeUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Абстрактный провайдер, который будет родителем для все поддерживаемых типов команд.
 */
public abstract class AbstractProvider implements CommandProvider {

    /**
     * Telegram client.
     */
    protected TelegramBotExecutor telegramBot;

    @Autowired
    protected final void setTelegramBot(TelegramBotExecutor telegramBot) {
        this.telegramBot = telegramBot;
    }

    /**
     * Дефолтная обработка сообщения, если не требуется чего-то особенного.
     *
     * @param message объект сообщения содержащий всю метаинформацию.
     */
    @Override
    public void execute(@NotNull Message message) {
    }

    /**
     * Безопасное представление строки в html-формате.
     * Экранирует символы: {@code <, >, @, "}.
     *
     * @param source исходная строка.
     * @return безопасная для html-строка.
     */
    protected String safetyHtml(String source) {
        return StringEscapeUtils.escapeHtml4(source);
    }

    /**
     * Попытка определить к какому провайдеру относится данное сообщение.
     * Необходимо для реализации сложных логик, когда тяжело идентифицировать к какому провайдеру
     * относится сообщение (например при игре в лотерею пользователь не только запускает лотерею,
     * но и делает ставку).
     *
     * @param message содержащий всю метаинформацию о сообщении из телеграмма.
     * @return true - если сообщение относится к текущему провайдеру (переопределяется в имплементациях),
     * false - дефолтная реализация если иного не требуется.
     */
    @Override
    public boolean defineCommand(@NotNull Message message) {
        return false;
    }
}
