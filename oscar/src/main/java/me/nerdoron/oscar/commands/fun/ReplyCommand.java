package me.nerdoron.oscar.commands.fun;

import org.slf4j.Logger;

import io.github.cdimascio.dotenv.Dotenv;
import me.nerdoron.oscar.commandManager.Command;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class ReplyCommand extends Command {

    @Override
    public void execute(MessageReceivedEvent event, Object[] args, Logger logger, Dotenv dotenv) {
        if (!(event.getMember().hasPermission(Permission.MESSAGE_MANAGE)))
            return;
        if (args.length < 1) {
            event.getChannel().sendMessage("Not enough arguments").queue();
            return;
        }
        String messageId = args[0].toString();
        Message message = event.getTextChannel().retrieveMessageById(messageId).complete();

        String input = " ";
        for (int i = 1; i < args.length; i++) {
            input = input + (i == 0 ? "" : " ") + args[i].toString();
        }
        event.getMessage().delete().queue();
        message.reply(input).queue();

    }

}
