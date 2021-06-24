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
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class ServerSuggestionAnswerCommand extends Command {

    @Override
    public void execute(MessageReceivedEvent event, Object[] args, Logger logger, Dotenv dotenv) {
        if (!(event.getMember().hasPermission(Permission.MESSAGE_MANAGE))) {
            event.getChannel().sendMessage("No permission!").queue();
            return;
        }

        String messageId = args[0].toString();
        String answer = " ";
        for (int i = 1; i < args.length; i++) {
            answer = answer + (i == 1 ? "" : " ") + args[i].toString();
        }

        String SQL1 = "select * from serversug where SID=?";

        TextChannel suggest = event.getGuild().getTextChannelById("850624610830778401");

        try {
            Connection con = Database.connect();
            PreparedStatement ps1 = con.prepareStatement(SQL1);
            ps1.setString(1, messageId);
            ResultSet rs = ps1.executeQuery();
            messageId = rs.getString("SID");
            String authorId = rs.getString("SAUTHOR");
            int answeredAlready = rs.getInt("SANSWER");
            ps1.close();

            if (answeredAlready == 1) {
                event.getChannel().sendMessage(":x: Question already answered!").queue();
                return;
            }
            String SQL2 = "replace into serversug(SID, SAUTHOR, SANSWER) values(?,?,?)";
            PreparedStatement ps2 = con.prepareStatement(SQL2);
            ps2.setString(1, messageId);
            ps2.setString(2, authorId);
            ps2.setInt(3, 1);
            ps2.execute();

            EmbedBuilder eb = ServerSuggestCommand.suggestionYesId;

            eb.addField("Answer by " + event.getAuthor().getAsTag() + ":", answer, false).build();

            MessageEmbed embed = new EmbedBuilder(eb).build();

            suggest.editMessageEmbedsById(messageId, embed).queue();

            event.getChannel().sendMessage(":white_check_mark: Success!").queue();

        } catch (Exception ex) {
            event.getChannel().sendMessageEmbeds(Global.errorEmbed).queue();
            logger.error(ex.toString());
        }

    }

}
