package mc.aca.auraac;

import mc.aca.auraac.checks.*;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class AuraAC extends JavaPlugin {

    private static AuraAC instance;

    public static AuraAC getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;

        // Сохраняем дефолтный конфиг из ресурсов, если его нет
        saveDefaultConfig();
        // Загружаем настройки в статические переменные
        ConfigLoader.load(getConfig());

        // Регистрация основной команды
        getCommand("auraac").setExecutor(new AuraCommand());

        // Регистрация всех проверок из пакета mc.aca.auraac.checks
        getServer().getPluginManager().registerEvents(new Speed(), this);
        getServer().getPluginManager().registerEvents(new Spider(), this);
        getServer().getPluginManager().registerEvents(new Jesus(), this);
        getServer().getPluginManager().registerEvents(new Flight(), this);
        getServer().getPluginManager().registerEvents(new KillAura(), this);

        getLogger().info("AuraAC успешно активирован!");
    }

    @Override
    public void onDisable() {
        getLogger().info("AuraAC выключен!");
    }

    // Метод для оповещения администраторов
    public static void alertAdmins(String message) {
        String formatted = ConfigLoader.prefix + "&c" + message;
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (p.hasPermission("auraac.alerts")) {
                p.sendMessage(formatted);
            }
        }
        // Логируем в консоль для истории
        instance.getLogger().warning("AuraAC " + message);
    }
}