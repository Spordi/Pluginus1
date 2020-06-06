package wasdenn.gungame.events;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import wasdenn.Main;
import wasdenn.Utils.Utils;
import wasdenn.gungame.utils.GGMain;
import wasdenn.gungame.utils.GGScoreboard;
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
            Bukkit.getPluginManager().callEvent(new GGJoinEvent(e.getPlayer(), e.getFrom(), e.getPlayer().getWorld()));
        }else if(e.getFrom().getName().equalsIgnoreCase("gungame1")) {
            Bukkit.getPluginManager().callEvent(new GGLeaveEvent(e.getPlayer(), e.getFrom(), e.getPlayer().getWorld()));
        }
    }

    @EventHandler
    public void on(GGJoinEvent e) {
        Player p = e.getPlayer();
        GGMain.sendToWorld("§e" + p.getName() + " §ahat das Spiel betreten");
        if(Main.ggState == GGState.LOBBY) {

            p.setGameMode(GameMode.ADVENTURE);
            p.teleport(plugin.fm.getLocation("gungame.lobby"));
            p.getInventory().clear();
            p.getInventory().setItem(8, Utils.hubBett());
            if(!GGMain.counterStarted && GGMain.countPeople() > 1) {
                GGMain.startCounter(plugin);
            }
        } else if(Main.ggState == GGState.INGAME || Main.ggState == GGState.END) {
            p.teleport(plugin.fm.getLocation("gungame.spec"));
            p.getInventory().clear();
            p.getInventory().setItem(8, Utils.hubBett());
            p.setGameMode(GameMode.SPECTATOR);
            p.sendMessage(GGMain.prefix + "§9Das Spiel hat bereits begonnen, du kannst aber zuschauen!");
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
        } else if(Main.ggState == GGState.INGAME) {
            GGMain.kills.remove(p);
            GGMain.ingame.remove(p);
            GGMain.deaths.remove(p);
            GGMain.level.remove(p);
            if(GGMain.ingame.size() == 1) {
                GGMain.endGame(plugin, GGMain.ingame.get(0));
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
            p.setFoodLevel(20);
            p.setSaturation(0);
            if (GGMain.isWorld(p.getWorld())) {
                Player killer;
                if(e.getDamager() instanceof Arrow) {
                    Arrow arrow = (Arrow) e.getDamager();
                    killer = (Player) arrow.getShooter();
                }else {
                    killer = (Player) e.getDamager();
                }
                if (p.getHealth() <= e.getDamage()) {
                    e.setCancelled(true);
                    onKill(p, killer);
                }
            }
        }
    }

    public void onKill(Player p, Player killer) {
        p.setInvulnerable(true);
        new BukkitRunnable() {
            @Override
            public void run() {
                p.setInvulnerable(false);
            }
        }.runTaskLater(plugin, 20);
        Random random = new Random();
        int rdm = random.nextInt(7) + 1;
        p.teleport(plugin.fm.getLocation("gungame.spawn." + rdm));
        p.playSound(p.getLocation(), Sound.ENTITY_GENERIC_BIG_FALL, 1, 1);
        p.setHealth(20);
        GGMain.updateInventory(p);
        GGMain.kills.replace(killer, GGMain.kills.get(killer) + 1);
        GGMain.deaths.replace(p, GGMain.deaths.get(p) + 1);
        GGMain.level.replace(killer, GGMain.level.get(killer) + 1);
        killer.playSound(killer.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
        int i = GGMain.level.get(p);
        if (i > 1) GGMain.level.replace(p, i - 1);
        GGMain.updateInventory(killer);
        GGMain.sendToWorld("§e" + p.getName() + " §cwurde von §e" + killer.getName() + " §cgetötet");
        killer.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 20, 3));
        GGMain.world.getPlayers().forEach(GGScoreboard::updateScoreboard);
        if(GGMain.level.get(killer) == 6) {
            GGMain.endGame(plugin, killer);
        }
    }

}
