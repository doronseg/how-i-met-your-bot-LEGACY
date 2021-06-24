package me.nerdoron.oscar.commands.fun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

import com.fasterxml.jackson.databind.JsonNode;

import org.slf4j.Logger;

import io.github.cdimascio.dotenv.Dotenv;
import org.json.*;

import me.nerdoron.oscar.Global;
import me.nerdoron.oscar.commandManager.Command;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class Joke extends Command {

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    private static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }

    @Override
    public void execute(MessageReceivedEvent event, Object[] args, Logger logger, Dotenv dotenv) {
        final TextChannel channel = event.getTextChannel();
        try {
            JSONObject jo = readJsonFromUrl("https://official-joke-api.appspot.com/random_joke");
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
