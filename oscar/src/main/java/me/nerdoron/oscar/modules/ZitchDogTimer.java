package me.nerdoron.oscar.modules;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import me.nerdoron.oscar.Main;
import me.nerdoron.oscar.modules.chain.ChainChannelHandler;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class ZitchDogTimer extends ListenerAdapter {

    static Timer timer = new Timer();
    static Logger logger = LoggerFactory.getLogger(ChainChannelHandler.class);
    private static Main main;

    public ZitchDogTimer(Main main) {
        ZitchDogTimer.main = main;
    }

    private static class Task extends TimerTask {
        JDA jda;

        public Task(JDA jda) {
            super();
            this.jda = jda;
        }

        @Override
        public void run() {
            int delay = (1 + new Random().nextInt(10)) * 1000;
            timer.schedule(new Task(jda), delay);
            ZitchDog(jda);
        }
    }

    public static void run(JDA jda) {
        new Task(jda).run();
    }

    public static void ZitchDog(JDA jda) {
        TextChannel ZitchDog = jda.getGuildById("850396197646106624").getTextChannelById("901497362160169010");
        ZitchDog.sendMessage(":dog2:")
                .queue(message -> main.getEventWaiter().waitForEvent(
                        GuildMessageReceivedEvent.class,
                        e -> {
                            if (!e.getChannel().getId().equals(ZitchDog.getId())) {
                                return false;
                            }
                            return e.getChannel().getId() == ZitchDog.getId();
                        },
                        e -> {
                            String msg = e.getMessage().getContentRaw();
                            if ((msg.equalsIgnoreCase("zitch dog")) || msg.equalsIgnoreCase("zitchdog")) {
                                ZitchDog.sendMessage("TEST");
                            }
                        },
                        1, TimeUnit.MINUTES,
                        () -> {
                            ZitchDog.sendMessage("Time out").queue();
                        }));

    }

}
