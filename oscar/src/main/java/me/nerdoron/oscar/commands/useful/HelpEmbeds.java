package me.nerdoron.oscar.commands.useful;

import me.nerdoron.oscar.Global;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

public class HelpEmbeds {
        // main menu
        public static MessageEmbed HelpMainMenu = new EmbedBuilder().setTitle("🔮 Help Menu")
                        .setDescription("Click on the category's button to view the commands in the category.")
                        .setFooter("Oscar Stinson Bot | Developed by nerdoron / Judge Fudge",
                                        "https://cdn.discordapp.com/avatars/857223819714625577/502f4031ae28f3033764831361259be2.webp?size=128")
                        .setColor(Global.embedColor).build();
        public static MessageEmbed HelpFunMenu = new EmbedBuilder().setTitle("🦩 Fun Commands")
                        .setDescription("🦩 Fun Commands")
                        .setDescription("A list of all fun commands: \n\n`*8ball` - Ask the magic eight ball a question.\n*Aliases:* `*eightball`, `*ball`")
                        .setFooter("Oscar Stinson Bot | Developed by nerdoron / Judge Fudge",
                                        "https://cdn.discordapp.com/avatars/857223819714625577/502f4031ae28f3033764831361259be2.webp?size=128")
                        .setColor(Global.embedColor).build();
}
