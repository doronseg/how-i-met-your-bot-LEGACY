package me.nerdoron.oscar.modules.jinx;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Nonnull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import me.nerdoron.oscar.Global;
import me.nerdoron.oscar.utils.Database;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class JinxCore extends ListenerAdapter {

    static final Logger logger = LoggerFactory.getLogger(JinxCore.class);

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getChannel().getId().equals("880421327494873098")
                || event.getChannel().getId().equals("900090953233231964"))
            return;

        String messageContent = event.getMessage().getContentDisplay();
        String authorId = event.getMessage().getAuthor().getId();
        String channelId = event.getChannel().getId();

        Role jinxed = event.getGuild().getRoleById("931865640510316595");
        if (event.getMember().getRoles().contains(jinxed)) {
            brokeJinx(authorId, event);
            return;
        }
        deleteIfJinxed(authorId, event);

        checkJinxed(channelId, messageContent, authorId, event);

    }

    private static void brokeJinx(String authorId, MessageReceivedEvent event) {
        event.getGuild().removeRoleFromMember(authorId, event.getGuild().getRoleById("931865640510316595"))
                .queue();
        event.getGuild().getTextChannelById("935803758384918558")
                .sendMessage("<@&935959050313695322>\n<@" + authorId + "> has broke a jinx. :no_mouth:").queue();
        Connection con = Database.connect();
        String sql = "delete from jinx where JINXED=?";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, authorId);
            ps.execute();
            ps.close();
            con.close();

        } catch (Exception ex) {
            logger.error(ex.getLocalizedMessage());
            return;
        }
        return;
    }

    private static void unJinx(List<String> usersToUnjinx, MessageReceivedEvent event) {
        for (String userId : usersToUnjinx) {

            event.getGuild().removeRoleFromMember(userId, event.getGuild().getRoleById("931865640510316595"))
                    .queue();
            event.getTextChannel().sendMessage("Sucessfully unjinxed <@" + userId + ">").queue();
            Connection con = Database.connect();
            String sql = "delete from jinx where JINXED=?";
            PreparedStatement ps = null;
            try {
                ps = con.prepareStatement(sql);
                ps.setString(1, userId);
                ps.execute();
                ps.close();
                con.close();
            } catch (Exception ex) {
                logger.error(ex.getLocalizedMessage());
            }
        }
        return;
    }

    private static void deleteIfJinxed(String authorId, MessageReceivedEvent event) {
        List<User> users = event.getMessage().getMentionedUsers();
        if (users.isEmpty())
            return;
        List<String> usersToUnjinx = new ArrayList<String>();
        try {
            Connection con = Database.connect();
            String sql = "select JINXED from jinx where JINXEDBY=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, authorId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String userId = rs.getString(1);
                event.getGuild().retrieveMemberById("342369172870922243").queue(member -> {
                    User user = member.getUser();
                    if (users.contains(user)) {
                        usersToUnjinx.add(userId);
                    }
                });

            }
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        unJinx(usersToUnjinx, event);

        // for (int i = 0; i < users.size(); i++) {
        // String userId = users.get(i).getId();
        // Connection con = Database.connect();
        // String sql = "select JINXEDBY from jinx where JINXED=?";
        // PreparedStatement ps = null;
        // try {
        // ps = con.prepareStatement(sql);
        // ps.setString(1, userId);
        // ResultSet rs = ps.executeQuery();
        // String jinxerId = rs.getString(1);
        // if (rs.next() && jinxerId.equals(authorId)) {
        // usersToUnjinx.add(userId);
        // }
        // rs.close();
        // ps.close();
        // unJinx(usersToUnjinx, event);
        // } catch (Exception ex) {
        // logger.error(ex.getLocalizedMessage());
        // }
        // }

    }

    private static void checkJinxed(String channelId, String messageContent, String authorId,
            MessageReceivedEvent event) {
        MessageCache cache = JinxChannelCache.getCache(channelId);

        String prevUserId = cache.getUser(messageContent);
        if (prevUserId.isBlank()) {
            cache.put(messageContent, authorId);
            return;
        }

        if (prevUserId.equals(authorId))
            return;

        if (event.getAuthor().isBot())
            return;

        doJinx(authorId, prevUserId, event);
    }

    private static void doJinx(String jinxed, String jinxer, MessageReceivedEvent event) {
        Connection con = Database.connect();
        String sql = "insert into jinx(JINXED, JINXEDBY) values(?,?)";
        PreparedStatement ps = null;

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, jinxed);
            ps.setString(2, jinxer);
            ps.execute();
            ps.close();
            con.close();

            event.getGuild().addRoleToMember(jinxed,
                    event.getGuild().getRoleById("931865640510316595"))
                    .queue();

            MessageEmbed jinx = new EmbedBuilder().setTitle("JINX!!!!!!1")
                    .setDescription("<@" + jinxed + "> has been Jinxed by <@" + jinxer
                            + ">. The only way for them to get unjinxed is if the Jinxer mentions the Jinxed.\nIf they speak before getting unjinxed, they get publicly humiliated!")
                    .setColor(Global.embedColor).build();
            event.getChannel().sendMessageEmbeds(jinx).queue();

        } catch (Exception ex) {
            logger.error(ex.getLocalizedMessage());
            event.getChannel().sendMessage("I had some trouble registering the Jinx.")
                    .queue();
        }
    }

}
