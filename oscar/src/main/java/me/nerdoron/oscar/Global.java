package me.nerdoron.oscar;

import java.awt.Color;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

public class Global {

    public static Color embedColor = Color.decode("#2f3136");
    public static MessageEmbed errorEmbed = new EmbedBuilder().setTitle("Error!").setDescription(
            "There has been an error while executing this feature! Don't worry, already notified the developer about this.")
            .setColor(Color.RED).build();
}