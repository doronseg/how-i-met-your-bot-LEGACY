package me.nerdoron.oscar;

import com.jagrosh.jdautilities.commons.waiter.EventWaiter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.github.cdimascio.dotenv.Dotenv;
import me.nerdoron.oscar.commandManager.CommandManager;
import me.nerdoron.oscar.commands.fun.EightBall;
import me.nerdoron.oscar.commands.fun.ReplyCommand;
import me.nerdoron.oscar.commands.fun.SayCommand;
import me.nerdoron.oscar.commands.useful.AboutCommand;
import me.nerdoron.oscar.commands.useful.DonateCommand;
import me.nerdoron.oscar.commands.useful.PingCommand;
import me.nerdoron.oscar.commands.useful.ReviveChat;
import me.nerdoron.oscar.commands.useful.UptimeCommand;
import me.nerdoron.oscar.commands.useful.afksystem.AFKCommand;
import me.nerdoron.oscar.commands.useful.afksystem.AFKMessageEvent;
import me.nerdoron.oscar.commands.useful.help.HelpButtons;
import me.nerdoron.oscar.commands.useful.help.HelpCommand;
import me.nerdoron.oscar.commands.useful.suggestions.BotSuggestCommand;
import me.nerdoron.oscar.commands.useful.suggestions.ChainSuggestCommand;
import me.nerdoron.oscar.commands.useful.suggestions.PollSuggestCommand;
import me.nerdoron.oscar.commands.useful.suggestions.ServerSuggestCommand;
import me.nerdoron.oscar.commands.useful.suggestions.VideoSuggestCommand;
import me.nerdoron.oscar.modules.FriendsCringe;
import me.nerdoron.oscar.modules.LeaveJoin;
import me.nerdoron.oscar.modules.Sweden;
import me.nerdoron.oscar.modules.YoutubeNotifications;
import me.nerdoron.oscar.modules.ZitchDogTest;
import me.nerdoron.oscar.modules.ZitchDogTimer;
import me.nerdoron.oscar.modules.chain.ChainChannelHandler;
import me.nerdoron.oscar.modules.chain.ChainEditing;
import me.nerdoron.oscar.modules.counting.CountingChannelHandler;
import me.nerdoron.oscar.modules.counting.CountingEditing;
import me.nerdoron.oscar.ticketsystem.CloseTicketCommand;
import me.nerdoron.oscar.ticketsystem.TicketCreate;
import me.nerdoron.oscar.utils.StatusTimer;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    private static String prefix = "*";
    private final static EventWaiter eventWaiter = new EventWaiter();

    public static void main(String[] args) {
        try {
            logger.info("Bot loading...");
            logger.info("Stage 1: Environment");
            setupEnv();
        } catch (Exception ex) {
            logger.error("An exception occured whilst trying to set up the bot!", ex);
        }
    }

    private static void setupEnv() {
        try {
            Dotenv dotenv = Dotenv.load();
            logger.info("Stage 1 complete");
            logger.info("Stage 2: bot login");
            login(dotenv);
        } catch (Exception ex) {
            logger.error("Exception occured whilst trying to setup the env file!", ex);
        }
    }

    public static void login(Dotenv dotenv) {
        String token = dotenv.get("TOKEN");
        try {
            JDA api = JDABuilder.createLight(token)
                    .enableIntents(GatewayIntent.GUILD_BANS, GatewayIntent.DIRECT_MESSAGES, GatewayIntent.GUILD_MEMBERS,
                            GatewayIntent.GUILD_MESSAGES, GatewayIntent.GUILD_MESSAGE_REACTIONS)
                    .build();
            logger.info("Logged in!");
            api.getPresence().setActivity(Activity.playing("Loading..."));
            StatusTimer.changeActivity(api);
            registration(api);
        } catch (Exception ex) {
            logger.error("Exception occured whilst trying to login!", ex);
        }
    }

    public static void registration(JDA jda) {
        try {
            new CommandManager(jda, prefix).registerCommand(new HelpCommand(), "help", "?")
                    .registerCommand(new EightBall(), "eightball", "8ball", "ball")
                    .registerCommand(new AboutCommand(), "about").registerCommand(new DonateCommand(), "donate")
                    .registerCommand(new UptimeCommand(), "uptime").registerCommand(new PingCommand(), "ping", "pong")
                    .registerCommand(new AFKCommand(), "afk")
                    .registerCommand(new ServerSuggestCommand(), "serversuggest", "ssuggestion", "ssuggest")
                    .registerCommand(new ReviveChat(), "revive")
                    .registerCommand(new VideoSuggestCommand(), "videosuggest", "vsuggestion", "vsuggest")
                    .registerCommand(new PollSuggestCommand(), "pollsuggest", "psuggestion", "psuggest")
                    .registerCommand(new ChainSuggestCommand(), "chainsuggest", "csuggestion", "csuggest")
                    .registerCommand(new BotSuggestCommand(), "botsuggest", "bsuggestion", "bsuggest")
                    .registerCommand(new SayCommand(), "say").registerCommand(new ReplyCommand(), "reply")
                    .registerCommand(new CloseTicketCommand(), "close");
            // .registerCommand(new ZitchDogTest(), "test");

            jda.addEventListener(new HelpButtons());
            jda.addEventListener(new AFKMessageEvent());
            jda.addEventListener(new ChainChannelHandler());
            jda.addEventListener(new ChainEditing());
            jda.addEventListener(new CountingEditing());
            jda.addEventListener(new LeaveJoin());
            jda.addEventListener(new CountingChannelHandler());
            jda.addEventListener(new FriendsCringe());
            jda.addEventListener(new TicketCreate());
            jda.addEventListener(new Sweden());
            jda.addEventListener(new YoutubeNotifications());
            // jda.addEventListener(new JinxCore());
            // jda.addEventListener(new JinxSystem());
            // jda.addEventListener(eventWaiter);

            // ZitchDogTimer.run(jda);

        } catch (Exception ex) {
            logger.error("Exception occured whilst trying to register the commands/events!", ex);
        }
    }

    public EventWaiter getEventWaiter() {
        return eventWaiter;
    }

}
