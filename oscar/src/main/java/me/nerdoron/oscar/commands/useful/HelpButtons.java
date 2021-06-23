package me.nerdoron.oscar.commands.useful;

import java.util.concurrent.TimeUnit;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.interaction.ButtonClickEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class HelpButtons extends ListenerAdapter {

    @Override
    public void onButtonClick(ButtonClickEvent event) {
        String[] id = event.getComponentId().split(":");
        String authorId = id[0];
        String type = id[1];
        Message message = event.getMessage();

        if (!authorId.equals(event.getUser().getId())) {
            event.getChannel()
                    .sendMessage(event.getUser().getAsMention()
                            + ", only the person which has executed the command can click on the buttons!")
                    .queue(tempMessage -> {
                        tempMessage.delete().queueAfter(5, TimeUnit.SECONDS);
                    });
            return;
        }
        if (!message.getAuthor().getId().equals(event.getJDA().getSelfUser().getId()))
            return;

        event.deferEdit().queue();

        switch (type) {
            case "HelpMain":
                message.editMessageEmbeds(HelpEmbeds.HelpMainMenu).queue();
                break;
            case "HelpFun":
                message.editMessageEmbeds(HelpEmbeds.HelpFunMenu).queue();
                break;
            case "HelpUseful":
                message.editMessageEmbeds(HelpEmbeds.HelpUsefulMenu).queue();
                break;

        }
    }

}
