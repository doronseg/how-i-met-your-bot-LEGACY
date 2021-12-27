package me.nerdoron.oscar.modules.starboard;

import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class BoardReaction extends ListenerAdapter {

    @Override
    public void onGuildMessageReactionAdd(GuildMessageReactionAddEvent event) {
        // star
        if (!(event.getReactionEmote().getName().equals("\u2B50"))) {
            return;
        }

    }

}
