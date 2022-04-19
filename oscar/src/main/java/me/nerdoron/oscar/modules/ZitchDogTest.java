package me.nerdoron.oscar.modules;

import java.util.concurrent.TimeUnit;

import com.jagrosh.jdautilities.commons.waiter.EventWaiter;

import org.slf4j.Logger;

import io.github.cdimascio.dotenv.Dotenv;
import me.nerdoron.oscar.commandManager.Command;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class ZitchDogTest extends Command {
    private final static EventWaiter eventWaiter = new EventWaiter();

    @Override
    public void execute(MessageReceivedEvent event, Object[] args, Logger logger, Dotenv dotenv) {
        TextChannel ZitchDog = event.getTextChannel();
        ZitchDog.sendMessage(":dog2:").queue();
        waiter(ZitchDog);
    }

    public void waiter(TextChannel ZitchDog) {
        eventWaiter.waitForEvent( // Setup Wait action once message was send
                GuildMessageReceivedEvent.class,
                e -> {
                    if (!e.getChannel().getId().equals(ZitchDog.getId())) // Check that channel is the same
                    {
                        return false;
                    }
                    return true;
                },
                e -> {
                    String authorMessage = e.getMessage().getContentRaw();
                    ZitchDog.sendMessage(authorMessage).queue();
                },
                10, TimeUnit.SECONDS,
                () -> {
                    ZitchDog.sendMessage("You didn't respond in time!").queue();
                });
    }
}
