package me.nerdoron.oscar.commands.fun;

import java.util.Random;

import org.slf4j.Logger;

import io.github.cdimascio.dotenv.Dotenv;
import me.nerdoron.oscar.commandManager.Command;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class EightBall extends Command {
    String[] responses = {
            "http://www.redkid.net/generator/8ball/newsign.php?line1=As+I+&line2=see+it%2C&line3=Yes.&Shake+Me=Shake+Me",
            "http://www.redkid.net/generator/8ball/newsign.php?line1=Ask&line2=Again&line3=Later&Shake+Me=Shake+Me",
            "http://www.redkid.net/generator/8ball/newsign.php?line1=Cannot&line2=predict&line3=now&Shake+Me=Shake+Me",
            "http://www.redkid.net/generator/8ball/newsign.php?line1=Don%27t&line2=count&line3=on+it&Shake+Me=Shake+Me",
            "http://www.redkid.net/generator/8ball/newsign.php?line1=Decidedly&line2=so&line3=&Shake+Me=Shake+Me",
            "http://www.redkid.net/generator/8ball/newsign.php?line1=Most&line2=likely&line3=&Shake+Me=Shake+Me",
            "http://www.redkid.net/generator/8ball/newsign.php?line1=My+reply&line2=is&line3=no&Shake+Me=Shake+Me",
            "http://www.redkid.net/generator/8ball/newsign.php?line1=Outlook+&line2=not+so&line3=good&Shake+Me=Shake+Me",
            "http://www.redkid.net/generator/8ball/newsign.php?line1=I+don%27t&line2=think+&line3=so&Shake+Me=Shake+Me",
            "http://www.redkid.net/generator/8ball/newsign.php?line1=Very&line2=doubtfu&line3=l&Shake+Me=Shake+Me",
            "http://www.redkid.net/generator/8ball/newsign.php?line1=Without&line2=a&line3=doubt&Shake+Me=Shake+Me",
            "http://www.redkid.net/generator/8ball/newsign.php?line1=&line2=Yes.&line3=&Shake+Me=Shake+Me",
            "http://www.redkid.net/generator/8ball/newsign.php?line1=You+may&line2=relay&line3=on+it&Shake+Me=Shake+Me",
            "http://www.redkid.net/generator/8ball/newsign.php?line1=Probably&line2=not&line3=&Shake+Me=Shake+Me",
            "http://www.redkid.net/generator/8ball/newsign.php?line1=Reply+&line2=Hazy.&line3=&Shake+Me=Shake+Me",
            "http://www.redkid.net/generator/8ball/newsign.php?line1=I+don%27t&line2=think&line3=so&Shake+Me=Shake+Me" };
    Random random = new Random();

    @Override
    public void execute(MessageReceivedEvent event, Object[] args, Logger logger, Dotenv dotenv) {
        if (args.length == 0) {
            event.getChannel().sendMessage("I don't see a question!").queue();
            return;
        }

        event.getChannel().sendMessage(responses[random.nextInt(responses.length)]).queue();
    }

}
