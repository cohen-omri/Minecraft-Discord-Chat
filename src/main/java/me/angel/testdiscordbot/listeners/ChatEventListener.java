package me.angel.testdiscordbot.listeners;

import me.angel.testdiscordbot.DiscordChat;
import me.angel.testdiscordbot.utils.Utils;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.awt.*;

public class ChatEventListener implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e){
        if(Utils.getConfig().getBoolean("minecraft-chat.enabled")){
            String message = e.getMessage();
            TextChannel guildChannel = DiscordChat.jda.getTextChannelById(Utils.getConfig().getLong("discord-chat.channel-id"));

            EmbedBuilder eb = new EmbedBuilder();
            eb.setColor(Color.MAGENTA);
            eb.setDescription(ChatColor.stripColor(e.getPlayer().getDisplayName().replace("_","\\_") + " » **" + message + "**"));

            guildChannel.sendMessage(ChatColor.stripColor(e.getPlayer().getDisplayName().replace("_","\\_") + "  » " + message)).complete();
            //guildChannel.sendMessageEmbeds(eb.build()).complete();
        }
    }

}
