package com.github.vadmurzakov.app.service.commands;

import com.github.vadmurzakov.app.entity.enums.CommandBotEnum;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Message;
import org.jetbrains.annotations.NotNull;

/**
 * Интерфейс для реализации бизнес-логики выполнения команд бота
 */
public interface CommandProvider {
    /**
     * Возвращает команду, которую обрабатывает реализация
     *
     * @return UNKNOWN - если команда неизвестная, CommandBotEnum - в противном случае
     */
    CommandBotEnum getCommand();

    /**
     * Выполняет бизнес логику команды бота
     *
     * @param message объект Message в рамках которого пришла команда на исполнение
     *                содержит в себе всю метаинформацию необходимую для выполнения команды
     */
    void execute(@NotNull Message message);

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
    boolean defineCommand(@NotNull Message message);
}
