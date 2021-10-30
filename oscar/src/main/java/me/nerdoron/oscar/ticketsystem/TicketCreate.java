package me.nerdoron.oscar.ticketsystem;

import java.util.ArrayList;
import java.util.EnumSet;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.interaction.ButtonClickEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class TicketCreate extends ListenerAdapter {
        @Override
        public void onButtonClick(ButtonClickEvent event) {
                String id = event.getComponentId();

                Member member = event.getMember();
                EnumSet<Permission> perms = EnumSet.of(Permission.MESSAGE_HISTORY, Permission.MESSAGE_READ,
                                Permission.MESSAGE_WRITE);

                TextChannel ticketLogs = event.getGuild().getTextChannelById("904000779638157342");

                switch (id) {
                case "admin":
                        TextChannel adminTicket = event.getGuild().getCategoryById("850458148723621888")
                                        .createTextChannel("admin-" + member.getUser().getName().substring(0, 4) + "-"
                                                        + member.getUser().getDiscriminator())
                                        .addPermissionOverride(event.getGuild().getPublicRole(), new ArrayList<>(),
                                                        perms)
                                        .addPermissionOverride(member, perms, new ArrayList<>()).complete();
                        event.deferReply().setEphemeral(true)
                                        .setContent("Created a ticket for you, " + adminTicket.getAsMention()).queue();
                        adminTicket.sendMessage(member.getAsMention()).queue();
                        adminTicket.sendMessageEmbeds(TicketPanels.adminWelcome).queue();

                        break;
                case "general":
                        TextChannel staffTicket = event.getGuild().getCategoryById("850458148723621888")
                                        .createTextChannel("ticket-" + member.getUser().getName().substring(0, 4) + "-"
                                                        + member.getUser().getDiscriminator())
                                        .addPermissionOverride(event.getGuild().getPublicRole(), new ArrayList<>(),
                                                        perms)
                                        .addRolePermissionOverride(850439278717829190L, perms, new ArrayList<>())
                                        .addPermissionOverride(member, perms, new ArrayList<>()).complete();
                        event.deferReply().setEphemeral(true)
                                        .setContent("Created a ticket for you, " + staffTicket.getAsMention()).queue();
                        staffTicket.sendMessage(member.getAsMention()).queue();
                        staffTicket.sendMessageEmbeds(TicketPanels.generalWelcome).queue();
                        break;
                }

        }
}
