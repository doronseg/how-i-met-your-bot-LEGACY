package me.nerdoron.oscar.commands.useful;

import org.slf4j.Logger;

import io.github.cdimascio.dotenv.Dotenv;
import me.nerdoron.oscar.Global;
import me.nerdoron.oscar.commandManager.Command;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class AboutCommand extends Command {

        @Override
        public void execute(MessageReceivedEvent event, Object[] args, Logger logger, Dotenv dotenv) {

                MessageEmbed aboutEmbed = new EmbedBuilder().setTitle("About").setDescription(
                                "The Oscar Stinson Discord Bot is a discord bot designed for the Oscar [Stinson Discord Server](https://discord.gg/himym). Below you could find all details regarding the bot.")
                                .addField("Library Version", "4.3.0_331", true)
                                .addField("Developer", "nerdoron#6483", true)
                                .addField("Ping", event.getJDA().getGatewayPing() + "ms.", true)
                                .addField("Uptime", UptimeCommand.getUptime(), true)
                                .addField("GitHub", "[Click me](https://github.com/nerdoron/OscarStinson_Bot/)", true)
                                .addField("Donate",
                                                "Running this bot isn't free, it costs about $3/month and it currently comes out of nerdoron's personal pocket. If you want to help the bot to continue running without issues, run `*donate`.",
                                                false)
                                .setColor(Global.embedColor)
                                .setFooter("how i met your bot | Developed by nerdoron",
                                                "https://media.discordapp.net/attachments/850432082738937896/901742492347691028/discord_bot_pfp.jpg")
                                .build();

                event.getChannel().sendMessageEmbeds(aboutEmbed).queue();
        }

}
