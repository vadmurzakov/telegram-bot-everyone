package com.github.vadmurzakov.app.service.commands.providers;

import com.github.vadmurzakov.app.entity.enums.CommandBotEnum;
import com.github.vadmurzakov.app.service.commands.AbstractProvider;
import com.pengrad.telegrambot.model.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

/**
 * Реализация провайдера для неизвестных команд
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class UnknownProvider extends AbstractProvider {

    @Override
    public CommandBotEnum getCommand() {
        return CommandBotEnum.UNKNOWN;
    }

    @Override
    public boolean defineCommand(@NotNull Message message) {
        return StringUtils.isEmpty(message.text());
    }
}
