package me.angel.testdiscordbot.discord.discordlisteners;

import me.angel.testdiscordbot.utils.Utils;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

public class BotMessageReceived extends ListenerAdapter {

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        super.onMessageReceived(event);
        if(event.getAuthor().isBot()) return;

        if(Utils.getConfig().getBoolean("discord-chat.enabled")) {
            if (event.getTextChannel().getIdLong() == Utils.getConfig().getLong("discord-chat.channel-id")) {
                String author = event.getAuthor().getName();
                String message = event.getMessage().getContentDisplay();

                Bukkit.broadcastMessage(Utils.color("&b[Discord]&r &f" + author + " &a»&r " + message));
            }
        }
    }
}
