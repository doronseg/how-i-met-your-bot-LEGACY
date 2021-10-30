package me.nerdoron.oscar.ticketsystem;

import org.slf4j.Logger;

import io.github.cdimascio.dotenv.Dotenv;
import me.nerdoron.oscar.commandManager.Command;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class CloseTicketCommand extends Command {

    @Override
    public void execute(MessageReceivedEvent event, Object[] args, Logger logger, Dotenv dotenv) {
        if (!(event.getChannel().getName().startsWith("ticket-"))
                && !(event.getChannel().getName().startsWith("admin-"))) {
            event.getChannel().sendMessage("This isn't a ticket channel!").queue();
            return;
        }
        event.getTextChannel().delete().queue();
    }

}
