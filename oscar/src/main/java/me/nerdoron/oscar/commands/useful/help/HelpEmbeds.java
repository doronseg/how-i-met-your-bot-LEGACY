package me.nerdoron.oscar.commands.useful.help;

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
                        .setDescription("A list of all fun commands: \n\n`*8ball` - Ask the magic eight ball a question.\n*Aliases:* `*eightball`, `*ball`"
                                        + "\n\n`*joke` - Get a random joke.\n*Aliases:* `*dadjoke`")
                        .setFooter("Oscar Stinson Bot | Developed by nerdoron / Judge Fudge",
                                        "https://cdn.discordapp.com/avatars/857223819714625577/502f4031ae28f3033764831361259be2.webp?size=128")
                        .setColor(Global.embedColor).build();
        public static MessageEmbed HelpUsefulMenu = new EmbedBuilder().setTitle("🛠️ Useful Commands")
                        .setDescription("A list of all useful commands: "
                                        + "\n\n`*help` - Shows this menu.\n*Aliases:* `*?`"
                                        + "\n\n`*uptime` - Shows the bot's uptime"
                                        + "\n\n`*ping` - Shows the bot's ping\n*Aliases:* `*pong`"
                                        + "\n\n`*about` - Shows information about the bot."
                                        + "\n\n`*donate` - Gives you the link to donate to the author."
                                        + "\n\n`*afk [reason]` - Sets you as AFK"
                                        + "\n\n`*ssuggest [suggestion]` - Send a server suggestion\n*Aliases:* `*serversuggest`, `*ssuggestion`")
                        .setFooter("Oscar Stinson Bot | Developed by nerdoron / Judge Fudge",
                                        "https://cdn.discordapp.com/avatars/857223819714625577/502f4031ae28f3033764831361259be2.webp?size=128")
                        .setColor(Global.embedColor).build();
}
