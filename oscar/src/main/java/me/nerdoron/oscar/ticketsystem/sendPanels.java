package me.nerdoron.oscar.ticketsystem;

import org.slf4j.Logger;

import io.github.cdimascio.dotenv.Dotenv;
import me.nerdoron.oscar.commandManager.Command;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.interactions.components.Button;

public class sendPanels extends Command {
    @Override
    public void execute(MessageReceivedEvent event, Object[] args, Logger logger, Dotenv dotenv) {
        event.getChannel().sendMessageEmbeds(TicketPanels.AdminTickets)
                .setActionRow(Button.secondary("admin", "📇 Contact the Adminstration Team"))
                .queue();

        event.getChannel().sendMessageEmbeds(TicketPanels.GeneralTickets)
                .setActionRow(Button.secondary("general", "📇 Contact the Staff Team"))
                .queue();

        event.getChannel().sendMessageEmbeds(TicketPanels.selfPromoTicket)
                .setActionRow(Button.secondary("selfpromo", "📇 Submit a Self Promotion link"))
                .queue();
    }

}
