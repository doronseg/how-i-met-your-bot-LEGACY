package me.nerdoron.oscar.ticketsystem;

import me.nerdoron.oscar.Global;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

public class TicketPanels {

        // admin ticket
        public static MessageEmbed AdminTickets = new EmbedBuilder().setTitle("📇 Admin Ticket").setDescription(
                        "Click the button below to contact the **Administraton Team**\nThis should be used "
                                        + "that the Admins need to handle, so punishment appeals, mod reports, and etc.\n\n*Be advised that response times here "
                                        + "will be longer, so please use the other ticket system if it's not important*")
                        .setColor(Global.embedColor)
                        .setFooter("Oscar Stinson Bot | Developed by nerdoron",
                                        "https://cdn.discordapp.com/avatars/857223819714625577/502f4031ae28f3033764831361259be2.webp?size=128")
                        .build();

        public static MessageEmbed adminWelcome = new EmbedBuilder().setTitle("📇 Admin Ticket ").setDescription(
                        "Hello, the administration team will be with you as soon as they can. In the meantime. please let us know what you need.\nTo close this ticket, use `*close`")
                        .setColor(Global.embedColor)
                        .setFooter("Oscar Stinson Bot | Developed by nerdoron",
                                        "https://cdn.discordapp.com/avatars/857223819714625577/502f4031ae28f3033764831361259be2.webp?size=128")
                        .build();
        // general ticket
        public static MessageEmbed GeneralTickets = new EmbedBuilder().setTitle("📇 Staff Ticket")
                        .setDescription("Click the button below to contact the staff team.").setColor(Global.embedColor)
                        .setFooter("Oscar Stinson Bot | Developed by nerdoron",
                                        "https://cdn.discordapp.com/avatars/857223819714625577/502f4031ae28f3033764831361259be2.webp?size=128")
                        .build();

        public static MessageEmbed generalWelcome = new EmbedBuilder().setTitle("📇 Staff Ticket ").setDescription(
                        "Hello, the staff team will be with you as soon as they can. In the meantime. please let us know what you need.\nTo close this ticket, use `*close`")
                        .setColor(Global.embedColor)
                        .setFooter("Oscar Stinson Bot | Developed by nerdoron",
                                        "https://cdn.discordapp.com/avatars/857223819714625577/502f4031ae28f3033764831361259be2.webp?size=128")
                        .build();
}
