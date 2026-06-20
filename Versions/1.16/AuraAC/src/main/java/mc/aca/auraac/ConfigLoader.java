package mc.aca.auraac;

import com.destroystokyo.paper.event.executor.asm.ClassDefiner;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.NotNull;

public class ConfigLoader {

    public static String prefix;

    public static String speedMsg, killauraMsg, spiderMsg, jesusMsg, flightMsg;

    public static double speedMaxDist, killauraReach, spiderMaxY, flightMaxVert;

    public static boolean speedEnabled, killauraEnabled, spiderEnabled, jesusEnabled, flightEnabled;

    public static void load(FileConfiguration config) {
        prefix = ChatColor.translateAlternateColorCodes('&', config.getString("messages.prefix"));

        speedMsg = config.getString("messages.speed-violation");
        killauraMsg = config.getString("messages.killaura-violation");
        spiderMsg = config.getString("messages.spider-violation");
        jesusMsg = config.getString("messages.jesus-violation");
        flightMsg = config.getString("messages.flight-violation");

        speedMaxDist = config.getDouble("speed.max-distance");
        killauraReach = config.getDouble("killaura.max-reach");
        spiderMaxY = config.getDouble("spider.max-y-diff");
        flightMaxVert = config.getDouble("flight.max-vertical-speed");

        speedEnabled = config.getBoolean("speed.enabled");
        killauraEnabled = config.getBoolean("killaura.enabled");
        spiderEnabled = config.getBoolean("spider.enabled");
        jesusEnabled = config.getBoolean("jesus.enabled");
        flightEnabled = config.getBoolean("flight.enabled");
    }

    public static void load(Class<? extends @NotNull ClassDefiner> aClass) {
    }
}