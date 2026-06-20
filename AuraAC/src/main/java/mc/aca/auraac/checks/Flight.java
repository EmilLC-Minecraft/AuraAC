package mc.aca.auraac.checks;

import mc.aca.auraac.AuraAC;
import mc.aca.auraac.ConfigLoader;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class Flight implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        if (!ConfigLoader.flightEnabled) return;

        Player player = event.getPlayer();

        if (player.hasPermission("auraac.bypass") ||
                player.getGameMode() == GameMode.CREATIVE ||
                player.getGameMode() == GameMode.SPECTATOR ||
                player.isFlying()) return;

        if (player.isOnGround()) return;

        double yDiff = event.getTo().getY() - event.getFrom().getY();

        if (yDiff > ConfigLoader.flightMaxVert && !player.getLocation().getBlock().getType().toString().contains("WATER")) {

            if (!player.isGliding()) {
                event.setTo(event.getFrom());

                AuraAC.alertAdmins("Игрок " + player.getName() + " подозревается в Fly!");
            }
        }
    }
}