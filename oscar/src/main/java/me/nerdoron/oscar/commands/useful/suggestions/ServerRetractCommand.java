package me.nerdoron.oscar.commands.useful.suggestions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.slf4j.Logger;

import io.github.cdimascio.dotenv.Dotenv;
import me.nerdoron.oscar.Global;
import me.nerdoron.oscar.commandManager.Command;
import me.nerdoron.oscar.utils.Database;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class ServerRetractCommand extends Command {

    @Override
    public void execute(MessageReceivedEvent event, Object[] args, Logger logger, Dotenv dotenv) {
        String authorId = event.getAuthor().getId();
        String authorQuestionSlection = args[0].toString();
        try {
            String SQL1 = "select * from serversug where SID=?";

            Connection con = Database.connect();
            PreparedStatement ps1 = con.prepareStatement(SQL1);
            ps1.setString(1, authorQuestionSlection);
            ResultSet rs = ps1.executeQuery();
            authorQuestionSlection = rs.getString("SID");
            String questionAuthorId = rs.getString("SAUTHOR");
            int answeredAlready = rs.getInt("SANSWER");
            ps1.close();

            if (answeredAlready == 1) {
                event.getChannel().sendMessage(":x: Question already answered!").queue();
                return;
            }

            if (!(questionAuthorId.equals(authorId))) {
                event.getChannel().sendMessage(":x: Only the author of the original question can do this!").queue();
                return;
            }

            TextChannel askstaff = event.getGuild().getTextChannelById("857560927134285824");
            MessageEmbed eb = new EmbedBuilder().setTitle("[RETRACTED]")
                    .setDescription("[suggestions retracted by author]")
                    .setFooter("Oscar Stinson Bot | Developed by nerdoron / Judge Fudge",
                            "https://cdn.discordapp.com/avatars/857223819714625577/502f4031ae28f3033764831361259be2.webp?size=128")
                    .build();

            askstaff.editMessageEmbedsById(authorQuestionSlection, eb).queue();

            String SQL2 = "delete from serversug where SID=?";
            PreparedStatement ps2 = con.prepareStatement(SQL2);
            ps2.setString(1, authorQuestionSlection);
            ps2.execute();

            event.getChannel().sendMessage(":white_check_mark: Success!").queue();

        } catch (Exception ex) {
            logger.error("Erorr whilst trying to retract a suggestion", ex);
            event.getChannel().sendMessageEmbeds(Global.errorEmbed).queue();
        }
    }

}
