package me.nerdoron.oscar.commands.useful.afksystem;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.slf4j.Logger;

import io.github.cdimascio.dotenv.Dotenv;
import me.nerdoron.oscar.Global;
import me.nerdoron.oscar.commandManager.Command;
import me.nerdoron.oscar.utils.Database;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class AFKCommand extends Command {

    @Override
    public void execute(MessageReceivedEvent event, Object[] args, Logger logger, Dotenv dotenv) {

        String reason = " ";

        if (args.length == 0) {
            reason = "No reason specified.";
            addAfkPerson(event.getAuthor().getId(), reason, logger, event);
            return;
        } else {
            for (int i = 0; i < args.length; i++) {
                reason = reason + (i == 0 ? "" : " ") + args[i].toString();
            }
            addAfkPerson(event.getAuthor().getId(), reason, logger, event);
            return;
        }
    }

    private void addAfkPerson(String UID, String reason, Logger logger, MessageReceivedEvent event) {
        Connection con = Database.connect();
        PreparedStatement ps = null;
        String SQL = "insert into afk (UID, REASON) values(?,?)";

        try {
            ps = con.prepareStatement(SQL);
            ps.setString(1, UID);
            ps.setString(2, reason);
            ps.execute();
            MessageEmbed afk = new EmbedBuilder().setTitle(":wave: Goodbye, " + event.getAuthor().getAsTag())
                    .setDescription("I've sucessfully set your AFK status!").addField("Reason", reason, false)
                    .setColor(Global.embedColor).build();
            event.getChannel().sendMessageEmbeds(afk).queue();
        } catch (Exception ex) {
            logger.error(ex.toString());
            event.getChannel().sendMessageEmbeds(Global.errorEmbed).queue();
        }

    }
}
