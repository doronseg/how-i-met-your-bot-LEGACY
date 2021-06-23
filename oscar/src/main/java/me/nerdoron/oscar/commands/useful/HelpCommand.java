package me.nerdoron.oscar.commands.useful;

import org.slf4j.Logger;

import io.github.cdimascio.dotenv.Dotenv;
import me.nerdoron.oscar.commandManager.Command;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.interactions.components.Button;

public class HelpCommand extends Command {

        @Override
        public void execute(MessageReceivedEvent event, Object[] args, Logger logger, Dotenv dotenv) {
                String userid = event.getAuthor().getId();
                event.getChannel().sendMessageEmbeds(HelpEmbeds.HelpMainMenu)
                                .setActionRow(Button.secondary(userid + ":HelpMain", "🔮 Main Menu"),
                                                Button.secondary(userid + ":HelpFun", "🦩 Fun Commands"),
                                                Button.secondary(userid + ":HelpUseful", "🛠️ Useful Commands"))
                                .queue();
        }

}
