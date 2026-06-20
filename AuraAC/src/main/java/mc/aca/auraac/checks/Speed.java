package mc.aca.auraac.checks;

import mc.aca.auraac.AuraAC;
import mc.aca.auraac.ConfigLoader;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class Speed implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        if (!ConfigLoader.speedEnabled) return;

        Player player = event.getPlayer();

        if (player.hasPermission("auraac.bypass") ||
                player.getGameMode() == GameMode.CREATIVE ||
                player.getGameMode() == GameMode.SPECTATOR) {
            return;
        }

        double distance = event.getFrom().distance(event.getTo());

        if (distance > ConfigLoader.speedMaxDist) {
            event.setTo(event.getFrom());

            AuraAC.alertAdmins("Игрок " + player.getName() + " подозревается в Speed!");
        }
    }
}