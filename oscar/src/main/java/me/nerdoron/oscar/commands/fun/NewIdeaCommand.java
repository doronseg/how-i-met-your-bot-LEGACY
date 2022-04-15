package me.nerdoron.oscar.commands.fun;

import org.slf4j.Logger;

import io.github.cdimascio.dotenv.Dotenv;
import me.nerdoron.oscar.commandManager.Command;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class NewIdeaCommand extends Command{
	public String prefix = "*";
	
	public void execute(MessageReceivedEvent event, Object[] args, Logger logger, Dotenv dotenv){}
		public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
			String[] args = event.getMessage().getContentRaw().split(" ");
			
			if(args[0].equalsIgnoreCase(prefix + "Robin")) {
				event.getMessage().reply("But um...").queue();			
	        }
			if(args[0].equalsIgnoreCase(prefix + "Barney")) {
				event.getMessage().reply("LEGEN... wait for it... DARY!").queue();	
		    }
			if(args[0].equalsIgnoreCase(prefix + "Ted")) {
				event.getMessage().reply("I want to find the love of my life.").queue();	
			}
			if(args[0].equalsIgnoreCase(prefix + "Marchal")) {
				event.getMessage().reply("Guys... I love Lily.").queue();	
			}
			if(args[0].equalsIgnoreCase(prefix + "Lily")) {
				event.getMessage().reply("Hey guys chews laudly.").queue();	
			}
        
	}
}
