package me.angel.testdiscordbot.discord;

import me.angel.testdiscordbot.discord.discordlisteners.BotMessageReceived;
import me.angel.testdiscordbot.discord.discordlisteners.BotStartUpListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.utils.Compression;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

import javax.security.auth.login.LoginException;

public class DiscordMain {

    private JDA jda;

    public JDA getJda() {
        return jda;
    }

    public DiscordMain(String token) {
        JDABuilder builder = JDABuilder.createDefault(token);

        // Disable parts of the cache
        builder.disableCache(CacheFlag.MEMBER_OVERRIDES, CacheFlag.VOICE_STATE);
        // Enable the bulk delete event
        builder.setBulkDeleteSplittingEnabled(false);
        // Disable compression (not recommended)
        builder.setCompression(Compression.NONE);
        // Set activity (like "playing Something")
        builder.setActivity(Activity.listening("Spotify & Angel's heart beats"));
        builder.addEventListeners(new BotStartUpListener(), new BotMessageReceived());
        try {
            jda = builder.build();
        } catch (LoginException e){
            e.printStackTrace();
        }
    }
}
