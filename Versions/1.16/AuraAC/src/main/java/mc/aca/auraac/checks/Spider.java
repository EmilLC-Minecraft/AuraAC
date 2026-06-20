package mc.aca.auraac.checks;

import mc.aca.auraac.AuraAC; // Импортируем для вызова alertAdmins
import mc.aca.auraac.ConfigLoader;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class Spider implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        if (!ConfigLoader.spiderEnabled) return;

        Player player = event.getPlayer();

        if (player.hasPermission("auraac.bypass")) return;

        double yDiff = event.getTo().getY() - event.getFrom().getY();

        if (yDiff > ConfigLoader.spiderMaxY) {

            if (player.getLocation().getBlock().getType().toString().contains("WATER") ||
                    player.getLocation().getBlock().getType().toString().contains("LAVA")) {
                return;
            }

            event.setTo(event.getFrom());

            // Сообщение игроку
            player.sendMessage(ConfigLoader.prefix + ConfigLoader.spiderMsg);

            // Уведомление админов и лог в консоль
            AuraAC.alertAdmins("Игрок " + player.getName() + " подозревается в Spider!");
        }
    }
}