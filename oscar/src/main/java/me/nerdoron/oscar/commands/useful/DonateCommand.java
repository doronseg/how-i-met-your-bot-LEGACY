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
                .setFooter("how i met your bot | Developed by nerdoron",
                        "https://media.discordapp.net/attachments/850432082738937896/901742492347691028/discord_bot_pfp.jpg")
                .setColor(Global.embedColor).build();
        event.getChannel().sendMessageEmbeds(donateEmbed).queue();

    }

}
