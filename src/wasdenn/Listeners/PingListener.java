package wasdenn.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;
import wasdenn.Main;

/**
 * Created by Meyssam Saghiri on Mai 27, 2020
 */
public class PingListener implements Listener {

    private final Main plugin;

    public PingListener(Main main) {
        this.plugin = main;
    }

    @EventHandler
    public void onPing(ServerListPingEvent e) {
        if(plugin.isWartung) {
            e.setMaxPlayers(0);
            e.setMotd("§cDer Server befindet sich derzeit im Wartungsmodus!");
        }
    }

}
