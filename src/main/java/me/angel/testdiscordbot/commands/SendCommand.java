package me.angel.testdiscordbot.commands;

import me.angel.testdiscordbot.DiscordChat;
import me.angel.testdiscordbot.utils.Utils;
import net.dv8tion.jda.api.entities.TextChannel;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class SendCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(Utils.getConfig().getBoolean("minecraft-chat.enabled")) {
            TextChannel guildChannel = DiscordChat.jda.getTextChannelById(Utils.getConfig().getLong("discord-chat.channel-id"));
            if (args.length == 0) {
                sender.sendMessage("Nothing provided");
                guildChannel.sendMessage("Nothing provided").complete();
                return false;
            }

            sender.sendMessage(args[0]);
            guildChannel.sendMessage(args[0]).complete();
        }
        return true;
    }

}
