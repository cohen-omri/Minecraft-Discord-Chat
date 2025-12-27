package me.angel.testdiscordbot.listeners;

import me.angel.testdiscordbot.DiscordChat;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.awt.*;

public class PlayerLeaveListener implements Listener {

    @EventHandler
    public void onJoin(PlayerQuitEvent e){
        TextChannel guildChannel = DiscordChat.jda.getTextChannelById(818068417260421141L);

        EmbedBuilder eb = new EmbedBuilder();
        eb.setColor(Color.RED);
        //eb.setDescription(ChatColor.stripColor(e.getPlayer().getName().replace("_","\\_") + "  **Joined the server**"));
        eb.setAuthor(ChatColor.stripColor(e.getPlayer().getName() + "  left the server"), null, "https://crafatar.com/avatars/" + e.getPlayer().getUniqueId() + "?overlay");

        //guildChannel.sendMessage(ChatColor.stripColor(e.getPlayer().getDisplayName().replace("_","\\_") + " » " + message)).complete();
        guildChannel.sendMessageEmbeds(eb.build()).complete();

    }

}
