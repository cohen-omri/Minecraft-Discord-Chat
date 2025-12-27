package me.angel.testdiscordbot;

import me.angel.testdiscordbot.commands.SendCommand;
import me.angel.testdiscordbot.discord.DiscordMain;
import me.angel.testdiscordbot.listeners.ChatEventListener;
import me.angel.testdiscordbot.listeners.PlayerJoinListener;
import me.angel.testdiscordbot.listeners.PlayerLeaveListener;
import me.angel.testdiscordbot.utils.Utils;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.TextChannel;
import org.bukkit.ChatColor;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.awt.*;
import java.util.logging.Level;

public final class DiscordChat extends JavaPlugin {

    private static DiscordChat instance;
    private DiscordMain discordMain;
    public static JDA jda;

    @Override
    public void onEnable() {
        instance = this;

        saveDefaultConfig();

        discordMain = new DiscordMain(getConfig().getString("token"));
        jda = discordMain.getJda();

        getLogger().log(Level.INFO, "Bot has been successfully logged in");

        getCommand("senddis").setExecutor(new SendCommand());
        getServer().getPluginManager().registerEvents(new ChatEventListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerLeaveListener(), this);

        if(getConfig().getLong("discord-chat.channel-id") <= 0){
            getLogger().log(Level.SEVERE, "Discord text channel id not found! Please insert one in the config file!");
        }

    }

    @Override
    public void onDisable() {

        if(Utils.getConfig().getBoolean("discord-chat.enabled") && Utils.getConfig().getLong("discord-chat.channel-id") != 0) {
            TextChannel guildChannel = jda.getTextChannelById(Utils.getConfig().getLong("discord-chat.channel-id"));

            EmbedBuilder eb = new EmbedBuilder();
            eb.setColor(Color.WHITE);
            eb.setDescription(ChatColor.stripColor(":octagonal_sign: **Server has Stopped**"));
            guildChannel.sendMessageEmbeds(eb.build()).complete();
        }

        jda.shutdown();
    }

    public static DiscordChat getInstance(){
        return instance;
    }

}
