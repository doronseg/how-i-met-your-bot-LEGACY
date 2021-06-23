package me.nerdoron.oscar.commandManager;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class CommandManager extends ListenerAdapter {
    private String prefix;
    private HashMap<String, Command> commandHashMap;
    final Logger logger = LoggerFactory.getLogger(CommandManager.class);

    public CommandManager(JDA jda, String prefix2) {
        jda.addEventListener(this);
        this.prefix = prefix2;
        this.commandHashMap = new HashMap<>();
    }

    public CommandManager registerCommand(Command cmd, String name, String... aliases) {
        commandHashMap.put(name.toLowerCase(), cmd);
        if (aliases.length > 0) {
            for (String i : aliases)
                commandHashMap.put(i, cmd);

        }
        return this;
    }

    public String getPrefix() {
        return prefix;
    }

    public void onMessageReceived(MessageReceivedEvent event) {
        String msg = event.getMessage().getContentRaw();
        if (msg.startsWith(prefix)) {
            final String cmd = msg.substring(prefix.length()).split(" ")[0];
            final String[] args = !msg.contains(" ") || msg.split(" ").length <= 1 ? new String[] {}
                    : msg.substring(cmd.length() + 2).split(" ");
            if (commandHashMap.containsKey(cmd.toLowerCase())) {
                try {
                    commandHashMap.get(cmd.toLowerCase()).executeGlobal(event, cmd, args);
                } catch (Exception ex) {
                    logger.error("There has been an error while trying to register a command!", ex);
                }
            }
        }
    }
}
