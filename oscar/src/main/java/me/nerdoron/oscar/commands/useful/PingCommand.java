package me.nerdoron.oscar.commands.useful;

import org.slf4j.Logger;

import io.github.cdimascio.dotenv.Dotenv;
import me.nerdoron.oscar.Global;
import me.nerdoron.oscar.commandManager.Command;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class PingCommand extends Command {

    @Override
    public void execute(MessageReceivedEvent event, Object[] args, Logger logger, Dotenv dotenv) {
        MessageEmbed ping = new EmbedBuilder().setTitle("Ping")
                .setDescription("Pong! " + event.getJDA().getGatewayPing() + "ms.").setColor(Global.embedColor)
                .setFooter("Oscar Stinson Bot | Developed by nerdoron",
                        "https://cdn.discordapp.com/avatars/857223819714625577/502f4031ae28f3033764831361259be2.webp?size=128")
                .build();
        event.getChannel().sendMessageEmbeds(ping).queue();
    }

}
