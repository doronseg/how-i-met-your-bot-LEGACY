package me.nerdoron.oscar.commands.useful;

import java.lang.management.ManagementFactory;

import org.slf4j.Logger;

import io.github.cdimascio.dotenv.Dotenv;
import me.nerdoron.oscar.Global;
import me.nerdoron.oscar.commandManager.Command;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class UptimeCommand extends Command {
        public static String getUptime() {
                long uptime = ManagementFactory.getRuntimeMXBean().getUptime();
                long uptimeSec = uptime / 1000;
                long hours = uptimeSec / (60 * 60);
                long minutes = (uptimeSec / 60) - (hours * 60);
                long seconds = uptimeSec % 60;

                String uptimeString = (Long.toString(hours) + " hours, " + Long.toString(minutes) + " minutes, "
                                + Long.toString(seconds) + " seconds.");

                return uptimeString;
        }

        @Override
        public void execute(MessageReceivedEvent event, Object[] args, Logger logger, Dotenv dotenv) {
                MessageEmbed uptime = new EmbedBuilder().setTitle("Uptime")
                                .setDescription("My uptime is " + getUptime()).setColor(Global.embedColor)
                                .setFooter("how i met your bot | Developed by nerdoron",
                                                "https://media.discordapp.net/attachments/850432082738937896/901742492347691028/discord_bot_pfp.jpg")
                                .build();
                event.getChannel().sendMessageEmbeds(uptime).queue();
        }

}
