package mc.aca.auraac;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class AuraCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // Проверка прав администратора
        if (!sender.hasPermission("auraac.admin")) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', ConfigLoader.prefix + " У вас нет прав!"));
            return true;
        }

        // Логика команды /auraac reload
        if (args.length > 0 && args[0].equalsIgnoreCase("reload")) {
            AuraAC.getInstance().reloadConfig();
            ConfigLoader.load(AuraAC.getInstance().getConfig());
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aAuraAC Настройки перезагружены!"));
            return true;
        }

        // Информационное сообщение
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6AuraAC v1.0 - Защита активна!"));
        return true;
    }
}