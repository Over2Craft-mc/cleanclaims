package fr.quentin.cleanclaims;

import me.angeschossen.lands.api.integration.LandsIntegration;
import org.bukkit.Bukkit;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public final class Cleanclaims extends JavaPlugin {

    private static LandsIntegration landsIntegration;

    private static Cleanclaims instance;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        landsIntegration = new LandsIntegration(this);
        getCommand("cleanclaims").setExecutor(new CleanClaimsCommand());
    }

    public static LandsIntegration getLandsIntegration() {
        return landsIntegration;
    }

    public static Cleanclaims getInstance() {
        return instance;
    }

    public static void logInfo(CommandSender sender, String message) {

        if (sender instanceof Player) {
            log((Player) sender, Level.INFO, message);
            return;
        }

        logInfo(message);
    }

    public static void logInfo(String message) {
        log(null, Level.INFO, message);
    }

    public static void logInfo(Player p, String message) {
        log(p, Level.INFO, message);
    }

    public static void log(Player p, Level level, String message) {

        message = String.format(Configuration.LOG_FORMAT, message).replace("&", "§");

        if (p != null && p.isOnline() && Configuration.LOG_SENT_TO_PLAYER_THAT_STARTED_CLEANING) {
            p.sendMessage(message);
        }

        if (Configuration.LOG_SENT_TO_CONSOLE) {
            Bukkit.getLogger().log(level, message);
        }
    }

    public static void sendMessage(CommandSender sender, String message) {
        sender.sendMessage(message.replace("&", "§"));
    }
}
