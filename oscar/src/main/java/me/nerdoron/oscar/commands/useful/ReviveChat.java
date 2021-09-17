package me.nerdoron.oscar.commands.useful;

import org.slf4j.Logger;

import io.github.cdimascio.dotenv.Dotenv;
import me.nerdoron.oscar.commandManager.Command;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class ReviveChat extends Command {

    @Override
    public void execute(MessageReceivedEvent event, Object[] args, Logger logger, Dotenv dotenv) {
        if (event.getMember().hasPermission(Permission.MESSAGE_MANAGE)) {
            event.getChannel().sendMessage("<@&888562459676409877>").queue();
            event.getChannel()
                    .sendMessage("https://tenor.com/view/googas-wet-wet-cat-dead-chat-dead-chat-xd-gif-20820186")
                    .queue();
            event.getMessage().delete().queue();

        } else {
            return;
        }

    }

}
