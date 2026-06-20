package mc.aca.auraac.checks;

import mc.aca.auraac.AuraAC;
import mc.aca.auraac.ConfigLoader;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class Jesus implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        if (!ConfigLoader.jesusEnabled) return;

        Player player = event.getPlayer();

        if (player.hasPermission("auraac.bypass") ||
                player.getGameMode() == GameMode.CREATIVE ||
                player.getGameMode() == GameMode.SPECTATOR) return;

        Block block = player.getLocation().getBlock();

        if (block.getType() == Material.WATER) {

            if (player.getLocation().getY() % 1 > 0.8 && event.getFrom().getY() <= event.getTo().getY()) {

                if (player.isInsideVehicle()) return;

                player.teleport(player.getLocation().add(0, -0.1, 0));

                AuraAC.alertAdmins("Игрок " + player.getName() + " подозревается в Jesus!");
            }
        }
    }
}