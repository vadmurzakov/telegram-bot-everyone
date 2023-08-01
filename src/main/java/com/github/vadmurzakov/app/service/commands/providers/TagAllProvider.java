package com.github.vadmurzakov.app.service.commands.providers;

import com.github.vadmurzakov.app.entity.enums.CommandBotEnum;
import com.github.vadmurzakov.app.service.commands.AbstractProvider;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.request.GetChatAdministrators;
import com.pengrad.telegrambot.request.SendMessage;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

/**
 * Реализация провайдера для комады "тегнуть всех"
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class TagAllProvider extends AbstractProvider {

    @Override
    public CommandBotEnum getCommand() {
        return CommandBotEnum.TAG_ALL;
    }

    /**
     * Регистрация нового игрока в игре.
     *
     * @param message объект Message в рамках которого пришла команда на исполнение
     *                содержит в себе всю метаинформацию необходимую для выполнения команды
     */
    @Override
    public void execute(final @NotNull Message message) {
        final var chatId = message.chat().id();
        final var messageId = message.messageId();
        final var replyMessageId = Optional.ofNullable(message.replyToMessage()).map(Message::messageId);

        var getChatAdministrators = new GetChatAdministrators(chatId);
        var execute = telegramBot.execute(getChatAdministrators);
        var usernames = execute.administrators()
            .stream()
            .filter(chatMember -> !chatMember.user().isBot())
            .filter(chatMember -> chatMember.user().username() != null)
            .map(chatMember -> "@" + chatMember.user().username())
            .toList();
        var msg = String.join(", ", usernames);
        var sendMessage = new SendMessage(chatId, msg).replyToMessageId(replyMessageId.orElse(messageId));

        telegramBot.execute(sendMessage);
    }

}
