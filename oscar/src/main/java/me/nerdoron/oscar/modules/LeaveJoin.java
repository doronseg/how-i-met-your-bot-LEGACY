package me.nerdoron.oscar.modules;

import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class LeaveJoin extends ListenerAdapter {

    @Override
    public void onGuildMemberRemove(GuildMemberRemoveEvent event) {
        TextChannel joinLeaves = event.getGuild().getTextChannelById("867770758976372766");
        joinLeaves.sendMessage("**" + event.getUser().getName() + "**#" + event.getUser().getDiscriminator()
                + " is now the Blitz! <:awman:853645381215715360>").queue();
    }

    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent event) {
        TextChannel joinLeaves = event.getGuild().getTextChannelById("867770758976372766");
        if (event.getMember() == null)
            return;
        joinLeaves.sendMessage("Welcome " + event.getMember().getAsMention()
                + ", to the Oscar Stinson Discord Server <:hello:851462988153618452>").queue();
    }
}