package mc.aca.auraac;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class CheckUtils {

    public static boolean hasBypass(Player player) {
        return player.hasPermission("auraac.bypass") || player.isOp();
    }

    public static String color(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    public static void sendAdminAlert(String message) {
        String alertMessage = color(ConfigLoader.prefix + "&7 " + message);

        Bukkit.getLogger().warning("AuraAC Alert: " + message);

        for (Player p : Bukkit.getOnlinePlayers()) {
            if (p.hasPermission("auraac.admin")) {
                p.sendMessage(alertMessage);
            }
        }
    }

    public static void sendMessage(Player player, String message) {
        player.sendMessage(color(ConfigLoader.prefix + " " + message));
    }
}