package me.nerdoron.oscar.commands.fun;

import org.json.JSONObject;
import org.slf4j.Logger;

import io.github.cdimascio.dotenv.Dotenv;

import me.nerdoron.oscar.Global;
import me.nerdoron.oscar.commandManager.Command;
import me.nerdoron.oscar.utils.JsonReader;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class Joke extends Command {

    @Override
    public void execute(MessageReceivedEvent event, Object[] args, Logger logger, Dotenv dotenv) {
        final TextChannel channel = event.getTextChannel();
        try {
            JSONObject jo = JsonReader.readJsonFromUrl("https://official-joke-api.appspot.com/random_joke");
            String setup = jo.getString("setup");
            String punch = jo.getString("punchline");

            MessageEmbed jokeEmbed = new EmbedBuilder().setTitle(setup).setDescription("||" + punch + "||").setFooter(
                    "Oscar Stinson Bot | Developed by nerdoron / Judge Fudge",
                    "https://cdn.discordapp.com/avatars/857223819714625577/502f4031ae28f3033764831361259be2.webp?size=128")
                    .setColor(Global.embedColor).build();
            channel.sendMessageEmbeds(jokeEmbed).queue();
        } catch (Exception ex) {
            event.getChannel().sendMessageEmbeds(Global.errorEmbed).queue();
        }

    }
}
