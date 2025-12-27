package me.angel.testdiscordbot.discord.discordlisteners;

import me.angel.testdiscordbot.DiscordChat;
import me.angel.testdiscordbot.utils.Utils;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.ChatColor;

import java.awt.*;

import static me.angel.testdiscordbot.DiscordChat.jda;

public class BotStartUpListener extends ListenerAdapter {

    @Override
    public void onReady(ReadyEvent event) {
        if(Utils.getConfig().getBoolean("discord-chat.enabled") && Utils.getConfig().getLong("discord-chat.channel-id") == 0) {
            TextChannel guildChannel = jda.getTextChannelById(Utils.getConfig().getLong("discord-chat.channel-id"));

            EmbedBuilder eb = new EmbedBuilder();
            eb.setColor(Color.WHITE);
            eb.setDescription(ChatColor.stripColor(":white_check_mark: **Server has Started**"));
            guildChannel.sendMessageEmbeds(eb.build()).complete();
        }
    }
}
