package me.nerdoron.oscar.commands.fun;

import org.slf4j.Logger;

import io.github.cdimascio.dotenv.Dotenv;
import me.nerdoron.oscar.commandManager.Command;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class SayCommand extends Command {

    @Override
    public void execute(MessageReceivedEvent event, Object[] args, Logger logger, Dotenv dotenv) {
        if (!(event.getMember().hasPermission(Permission.MESSAGE_MANAGE)))
            return;
        if (args.length == 0) {
            event.getChannel().sendMessage(event.getAuthor().getName() + " you stupid idiot you have to put a message").queue();
            return;
        }

        String input = " ";
        for (int i = 0; i < args.length; i++) {
            input = input + (i == 0 ? "" : " ") + args[i].toString();
        }
        event.getMessage().delete().queue();
        event.getChannel().sendMessage(input).queue();

    }
}
