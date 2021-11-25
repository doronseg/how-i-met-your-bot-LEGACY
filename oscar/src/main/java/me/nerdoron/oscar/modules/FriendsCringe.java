package me.nerdoron.oscar.modules;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class FriendsCringe extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getMessage().getContentDisplay().toLowerCase().contains("friends")) {
            if (event.getAuthor().isBot())
                return;
            event.getChannel().sendMessage("friends? cringe lol").queue();
        }
    }

}
