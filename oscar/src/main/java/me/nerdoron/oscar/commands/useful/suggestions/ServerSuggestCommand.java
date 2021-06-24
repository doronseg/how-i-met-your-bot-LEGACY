package me.nerdoron.oscar.commands.useful.suggestions;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.slf4j.Logger;

import io.github.cdimascio.dotenv.Dotenv;
import me.nerdoron.oscar.Global;
import me.nerdoron.oscar.commandManager.Command;
import me.nerdoron.oscar.utils.Database;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class ServerSuggestCommand extends Command {
    public static EmbedBuilder suggestionYesId;

    @Override
    public void execute(MessageReceivedEvent event, Object[] args, Logger logger, Dotenv dotenv) {
        if (args.length == 0) {
            event.getChannel().sendMessage("You must provide a suggestion!").queue();
            return;
        }

        TextChannel serversuggestion = event.getGuild().getTextChannelById("850624610830778401");
        MessageEmbed suggestNoId = new EmbedBuilder()
                .setAuthor(event.getAuthor().getAsTag() + "'s suggestion", null, event.getAuthor().getAvatarUrl())
                .setDescription("Getting suggestion...").addField("Author ID", event.getAuthor().getId(), true)
                .addField("Suggestion ID", "Getting id...", true).setColor(Global.embedColor)
                .setFooter("Oscar Stinson Bot | Developed by nerdoron / Judge Fudge",
                        "https://cdn.discordapp.com/avatars/857223819714625577/502f4031ae28f3033764831361259be2.webp?size=128")
                .build();

        serversuggestion.sendMessageEmbeds(suggestNoId).queue((message) -> {
            String messageId = message.getId();
            String suggestion = " ";
            for (int i = 0; i < args.length; i++) {
                suggestion = suggestion + (i == 0 ? "" : " ") + args[i].toString();
            }
            event.getChannel().sendMessage(
                    ":white_check_mark: Your suggestion was sent to the staff team!\n**Please remember that sending troll suggestions will result in a strike. \nIf you would like to retract your suggestion, use *sretract <id>**")
                    .queue();
            suggestionYesId = new EmbedBuilder()
                    .setAuthor(event.getAuthor().getAsTag() + "'s suggestion", null, event.getAuthor().getAvatarUrl())
                    .setDescription(suggestion).addField("Author ID", event.getAuthor().getId(), true)
                    .addField("Suggestion ID", messageId, true).setColor(Global.embedColor)
                    .setFooter("Oscar Stinson Bot | Developed by nerdoron / Judge Fudge",
                            "https://cdn.discordapp.com/avatars/857223819714625577/502f4031ae28f3033764831361259be2.webp?size=128");
            MessageEmbed finalSuggestion = suggestionYesId.build();
            Connection con = Database.connect();
            PreparedStatement ps = null;
            String SQL = "insert into serversug (SID, SAUTHOR, SANSWER) values(?,?,?)";

            try {
                ps = con.prepareStatement(SQL);
                ps.setString(1, messageId);
                ps.setString(2, event.getAuthor().getId());
                ps.setInt(3, 0);
                ps.execute();
            } catch (Exception ex) {
                logger.error(ex.toString());
            }
            message.editMessageEmbeds(finalSuggestion).queue();
        });
    }

}
