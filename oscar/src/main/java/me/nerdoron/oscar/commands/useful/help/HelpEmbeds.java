package me.nerdoron.oscar.commands.useful.help;

import me.nerdoron.oscar.Global;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

public class HelpEmbeds {
        // main menu
        public static MessageEmbed HelpMainMenu = new EmbedBuilder().setTitle("🔮 Help Menu")
                        .setDescription("Click on the category's button to view the commands in the category.")
                        .setFooter("how i met your bot | Developed by nerdoron",
                                        "https://media.discordapp.net/attachments/850432082738937896/901742492347691028/discord_bot_pfp.jpg")
                        .setColor(Global.embedColor).build();
        public static MessageEmbed HelpFunMenu = new EmbedBuilder().setTitle("🦩 Fun Commands")
                        .setDescription("🦩 Fun Commands")
                        .setDescription("A list of all fun commands: \n\n`*8ball` - Ask the magic eight ball a question.\n*Aliases:* `*eightball`, `*ball`")
                        .setFooter("how i met your bot | Developed by nerdoron",
                                        "https://media.discordapp.net/attachments/850432082738937896/901742492347691028/discord_bot_pfp.jpg")
                        .setColor(Global.embedColor).build();
        public static MessageEmbed HelpUsefulMenu = new EmbedBuilder().setTitle("🛠️ Useful Commands")
                        .setDescription("A list of all useful commands: "
                                        + "\n\n`*help` - Shows this menu.\n*Aliases:* `*?`"
                                        + "\n\n`*uptime` - Shows the bot's uptime"
                                        + "\n\n`*ping` - Shows the bot's ping\n*Aliases:* `*pong`"
                                        + "\n\n`*about` - Shows information about the bot."
                                        + "\n\n`*donate` - Gives you the link to donate to the author."
                                        + "\n\n`*afk [reason]` - Sets you as AFK"
                                        + "\n\n`*ssuggest [suggestion]` - Send a server suggestion\n*Aliases:* `*serversuggest`, `*ssuggestion`"
                                        + "\n\n`*vsuggest [suggestion]` - Send a video suggestion\n*Aliases:* `*videosuggest`, `*vsuggestion`"
                                        + "\n\n`*psuggest [suggestion]` - Send a poll suggestion\n*Aliases:* `*pollsuggest`, `*psuggestion`"
                                        + "\n\n`*bsuggest [suggestion]` - Send a bot suggestion\n*Aliases:* `*botsuggest`, `*bsuggestion`")
                        .setFooter("how i met your bot | Developed by nerdoron",
                                        "https://media.discordapp.net/attachments/850432082738937896/901742492347691028/discord_bot_pfp.jpg")
                        .setColor(Global.embedColor).build();
}
