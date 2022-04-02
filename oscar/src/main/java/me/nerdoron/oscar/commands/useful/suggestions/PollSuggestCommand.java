package me.nerdoron.oscar.commands.useful.suggestions;

import org.slf4j.Logger;

import io.github.cdimascio.dotenv.Dotenv;
import me.nerdoron.oscar.Global;
import me.nerdoron.oscar.commandManager.Command;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class PollSuggestCommand extends Command {
    public static EmbedBuilder suggestionYesId;

    @Override
    public void execute(MessageReceivedEvent event, Object[] args, Logger logger, Dotenv dotenv) {
        if (args.length == 0) {
            event.getChannel().sendMessage("You must provide a suggestion!").queue();
            return;
        }

        TextChannel serversuggestion = event.getGuild().getTextChannelById("869854335562027018");
        MessageEmbed suggestNoId = new EmbedBuilder()
                .setAuthor(event.getAuthor().getAsTag() + "'s suggestion", null, event.getAuthor().getAvatarUrl())
                .setDescription("Getting suggestion...").addField("Author ID", event.getAuthor().getId(), true)
                .addField("Suggestion ID", "Getting id...", true).setColor(Global.embedColor)
                .setFooter("how i met your bot | Developed by nerdoron",
                        "https://media.discordapp.net/attachments/850432082738937896/901742492347691028/discord_bot_pfp.jpg")
                .build();

        serversuggestion.sendMessageEmbeds(suggestNoId).queue((message) -> {
            String messageId = message.getId();
            String suggestion = " ";
            for (int i = 0; i < args.length; i++) {
                suggestion = suggestion + (i == 0 ? "" : " ") + args[i].toString();
            }
            event.getChannel().sendMessage(
                    ":white_check_mark: Your suggestion was sent to the staff team!\nPlease remember that sending troll suggestions will result in a strike.")
                    .queue();
            suggestionYesId = new EmbedBuilder()
                    .setAuthor(event.getAuthor().getAsTag() + "'s suggestion", null, event.getAuthor().getAvatarUrl())
                    .setDescription(suggestion).addField("Author ID", event.getAuthor().getId(), true)
                    .addField("Suggestion ID", messageId, true).setColor(Global.embedColor)
                    .setFooter("how i met your bot | Developed by nerdoron",
                            "https://media.discordapp.net/attachments/850432082738937896/901742492347691028/discord_bot_pfp.jpg");
            MessageEmbed finalSuggestion = suggestionYesId.build();
            message.editMessageEmbeds(finalSuggestion).queue();
            message.addReaction("✅").queue();
            message.addReaction("❌").queue();
        });
    }

}
