package wasdenn.gungame.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import wasdenn.Main;
import wasdenn.gungame.utils.GGMain;
import wasdenn.gungame.utils.GGState;

import java.util.Random;

/**
 * Created by Meyssam Saghiri on Mai 29, 2020
 */
public class GGListener implements Listener {

    private final Main plugin;

    public GGListener(Main main) {
        this.plugin = main;
    }

    @EventHandler
    public void on(PlayerChangedWorldEvent e) {
        if(e.getPlayer().getWorld().getName().equalsIgnoreCase("gungame1")) {
            System.out.println("Hallo");
            Bukkit.getPluginManager().callEvent(new GGJoinEvent(e.getPlayer(), e.getFrom(), e.getPlayer().getWorld()));
        }else if(e.getFrom().getName().equalsIgnoreCase("gungame1")) {
            System.out.println("pallo");
            Bukkit.getPluginManager().callEvent(new GGLeaveEvent(e.getPlayer(), e.getFrom(), e.getPlayer().getWorld()));
        }
    }

    @EventHandler
    public void on(GGJoinEvent e) {
        Player p = e.getPlayer();
        GGMain.sendToWorld("§e" + p.getName() + " §ahat das Spiel betreten");
        if(Main.ggState == GGState.LOBBY) {
            p.teleport(plugin.fm.getLocation("gungame.lobby"));
            p.getInventory().clear();
            if(!GGMain.counterStarted && GGMain.countPeople() > 1) {
                GGMain.startCounter(plugin);
            }
        }
    }

    @EventHandler
    public void on(GGLeaveEvent e) {
        Player p = e.getPlayer();
        GGMain.sendToWorld("§e" + p.getName() + " §chat das Spiel verlassen");
        if(Main.ggState == GGState.LOBBY) {
            if(GGMain.counterStarted && GGMain.countPeople() < 2) {
                GGMain.counterStarted = false;
            }
        }
    }

    @EventHandler
    public void on(EntityDamageEvent e) {
        if(Main.ggState != GGState.INGAME && GGMain.isWorld(e.getEntity().getWorld())) {
            e.setCancelled(true);
        }
        if(GGMain.isWorld(e.getEntity().getWorld()) && e.getCause() != EntityDamageEvent.DamageCause.ENTITY_ATTACK ) {
            if(e.getCause() != EntityDamageEvent.DamageCause.PROJECTILE) {
                e.setCancelled(true);
            }
        }
    }

    //@EventHandler
    //public void on(PlayerDeathEvent e) {
    //    Player p = e.getEntity();
    //    if(GGMain.isWorld(p.getWorld()) && Main.ggState == GGState.INGAME) {
    //        if(p.getKiller() != null) {
    //            Player killer = p.getKiller();
    //            GGMain.level.replace(killer, GGMain.level.get(killer)+1);
    //            int i = GGMain.level.get(p);
    //            if(i > 1) GGMain.level.replace(p, i-1);
    //            GGMain.updateInventory(killer);
    //            new BukkitRunnable() {
    //                @Override
    //                public void run() {
    //                    Random random = new Random();
    //                    int rdm = random.nextInt(7) + 1;
    //                    p.spigot().respawn();
    //                    p.teleport(plugin.fm.getLocation("gungame.spawn." + rdm));
    //                    GGMain.updateInventory(p);
    //                }
    //            }.runTaskLater(plugin, 3);
    //        }
    //    }
    //}

    @EventHandler
    public void on(EntityDamageByEntityEvent e) {
        if (e.getEntity() instanceof Player && Main.ggState == GGState.INGAME) {
            Player p = (Player) e.getEntity();
            if (GGMain.isWorld(p.getWorld())) {
                Player killer = (Player) e.getDamager();
                if (p.getHealth() <= e.getDamage()) {
                    e.setCancelled(true);
                    GGMain.level.replace(killer, GGMain.level.get(killer) + 1);
                    int i = GGMain.level.get(p);
                    if (i > 1) GGMain.level.replace(p, i - 1);
                    GGMain.updateInventory(killer);
                    Random random = new Random();
                    int rdm = random.nextInt(7) + 1;
                    GGMain.sendToWorld("§e" + p.getName() + " §cwurde von §e" + killer.getName() + " §cgetötet");
                    p.teleport(plugin.fm.getLocation("gungame.spawn." + rdm));
                    p.setHealth(20);
                    killer.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 5, 3));
                    GGMain.updateInventory(p);
                }
            }
        }
    }

}
