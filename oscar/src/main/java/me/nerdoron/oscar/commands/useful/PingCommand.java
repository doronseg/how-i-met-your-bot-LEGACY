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
                .setFooter("how i met your bot | Developed by nerdoron",
                        "https://media.discordapp.net/attachments/850432082738937896/901742492347691028/discord_bot_pfp.jpg")
                .build();
        event.getChannel().sendMessageEmbeds(ping).queue();
    }

}
