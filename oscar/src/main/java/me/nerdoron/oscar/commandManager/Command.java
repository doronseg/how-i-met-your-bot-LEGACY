package me.nerdoron.oscar.commandManager;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.github.cdimascio.dotenv.Dotenv;
import me.nerdoron.oscar.Global;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public abstract class Command {

    public void executeGlobal(MessageReceivedEvent event, String cmd, Object[] args) {
        final Logger logger = LoggerFactory.getLogger(Command.class);
        Dotenv dotenv = Dotenv.configure().directory(Global.dotEnvLocation).load();
        try {
            execute(event, args, logger, dotenv);
        } catch (Exception ex) {
            event.getChannel().sendMessageEmbeds(Global.errorEmbed).queue();
            logger.error("Error while tryng to execute command", event.getMessage().getContentRaw());
            String error = ExceptionUtils.getStackTrace(ex);
            logger.error(error);
        }
    }

    public abstract void execute(MessageReceivedEvent event, Object[] args, Logger logger, Dotenv dotenv);
}
