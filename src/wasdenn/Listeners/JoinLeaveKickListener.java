package wasdenn.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;
import wasdenn.Main;
import wasdenn.Utils.Utils;

public class JoinLeaveKickListener implements Listener {

    private final Main plugin;
    public JoinLeaveKickListener(Main main) {
        this.plugin = main;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        initialize(p); //NEU erklär ich dir später
        if (p.isOp()) {
            e.setJoinMessage("§4" + p.getName() + " §bist dem Spiel beigetreten");
        } else {
            if(plugin.isWartung) {
                p.kickPlayer("§cDer Server befindet sich im Wartungsmodus! Warte bis die Arbeiten abgeschlossen sind.");
            }
            e.setJoinMessage("§b" + p.getName() + " §3ist schwul");
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        if (p.isOp()) {
            e.setQuitMessage("§bTschuessi §4" + p.getName() + " §b:^)");
        } else {
            e.setQuitMessage("§b" + p.getName() + " §3hat das Spiel verlassen");
        }
    }

    @EventHandler
    public void onKick(PlayerKickEvent e) {
        Player p = e.getPlayer();
        if(p.isBanned()){
            e.setReason("§3BigMac hat dich weggebannt, diggah!");
            return;
        }
        if(p.isOp()) {
            e.setLeaveMessage("§fdu Arschi §f:rage:");
        }
        else {
            e.setLeaveMessage("§fLass das §4:rage:");
        }
    }

    /*
    Eigene Funktionen
     */

    private void initialize(Player p) {
        p.getInventory().clear();
        new BukkitRunnable() {
            @Override
            public void run() {
                p.getInventory().setItem(4, Utils.kompass());
                p.updateInventory();
            }
        }.runTaskLater(plugin, 10);

        p.teleport(plugin.fm.getLocation("lobby"));
    }

}
