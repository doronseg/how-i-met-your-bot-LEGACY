package me.nerdoron.oscar.modules;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class ChainChannelHandler extends ListenerAdapter {
    static final Logger logger = LoggerFactory.getLogger(ChainChannelHandler.class);

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getMember() == null) {
            return;
        }
        if (!(event.getChannel().getId().equals("880421327494873098")))
            return;
        if (event.getMember().getUser().isBot() || event.getMember().getId().equals("221204198287605770")) {
            return;
        }

        String uid = event.getMember().getId();
        String messageContent = event.getMessage().getContentDisplay();

        event.getChannel().getHistory().retrievePast(1).queue(messages -> {
            if (!(messages.get(0).getContentDisplay().equals(messageContent))) {
                event.getMessage().delete().queue();
                return;
            }
            if (messages.get(0).getAuthor().getId().equals(uid)) {
                event.getMessage().delete().queue();
                return;
            }
        });

    }

}
