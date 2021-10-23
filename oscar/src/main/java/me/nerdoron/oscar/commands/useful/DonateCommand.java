package me.nerdoron.oscar.commands.useful;

import org.slf4j.Logger;

import io.github.cdimascio.dotenv.Dotenv;
import me.nerdoron.oscar.Global;
import me.nerdoron.oscar.commandManager.Command;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class DonateCommand extends Command {

    @Override
    public void execute(MessageReceivedEvent event, Object[] args, Logger logger, Dotenv dotenv) {
        MessageEmbed donateEmbed = new EmbedBuilder().setTitle("Donate").setDescription(
                "Running this bot isn't free, it costs about $3/month and it currently comes out of nerdoron's personal pocket. If you want to help the bot to continue running without issues, please consider [donating](https://paypal.me/segevf)")
                .setFooter("Oscar Stinson Bot | Developed by nerdoron",
                        "https://cdn.discordapp.com/avatars/857223819714625577/502f4031ae28f3033764831361259be2.webp?size=128")
                .setColor(Global.embedColor).build();
        event.getChannel().sendMessageEmbeds(donateEmbed).queue();

    }

}
