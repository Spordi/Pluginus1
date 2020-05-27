package wasdenn.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import wasdenn.Main;

public class KeinSchadenInLobbyListener implements Listener {
    private final Main plugin;

    public KeinSchadenInLobbyListener(Main main) {
        this.plugin = main;
    }

    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        if(e.getEntity() instanceof Player) {
            if(e.getEntity().getWorld().getName().equalsIgnoreCase("world")) {
                e.setCancelled(true);
            }
        }
    }
}
