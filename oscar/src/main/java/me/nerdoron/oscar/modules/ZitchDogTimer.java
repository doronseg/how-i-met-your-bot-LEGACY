package me.nerdoron.oscar.modules;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class ZitchDogTimer extends ListenerAdapter {

    static Timer timer = new Timer();
    static Logger logger = LoggerFactory.getLogger(ChainChannelHandler.class);

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
        ZitchDog.sendMessage(":dog:").queue();
    }

}
