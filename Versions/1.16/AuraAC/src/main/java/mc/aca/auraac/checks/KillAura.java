package mc.aca.auraac.checks;

import mc.aca.auraac.AuraAC;
import mc.aca.auraac.ConfigLoader;
import org.bukkit.GameMode;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class KillAura implements Listener {

    @EventHandler
    public void onAttack(EntityDamageByEntityEvent event) {
        if (!ConfigLoader.killauraEnabled) return;

        if (!(event.getDamager() instanceof Player)) return;

        Player player = (Player) event.getDamager();

        if (player.hasPermission("auraac.bypass") ||
                player.getGameMode() == GameMode.CREATIVE ||
                player.getGameMode() == GameMode.SPECTATOR) return;

        Entity target = event.getEntity();

        double distance = player.getLocation().distance(target.getLocation());

        if (distance > ConfigLoader.killauraReach) {
            event.setCancelled(true);

            AuraAC.alertAdmins("Игрок " + player.getName() + " подозревается в KillAura!");
        }
    }
}