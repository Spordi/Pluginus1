package wasdenn.gungame.utils;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import wasdenn.Main;
import wasdenn.Utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Meyssam Saghiri on Mai 29, 2020
 */
public class GGMain {

    /*
    TODO: Was soll passieren wenn Spieleranzahl über 8 ist?
     */

    public static boolean counterStarted = false;
    public static int counter = 60;
    public static World world = Bukkit.getWorld("gungame1");
    public static String prefix = "§8[§7GG§8] ";
    public static ArrayList<Player> alive = new ArrayList<>();
    public static HashMap<Player, Integer> level = new HashMap<>();

    public static void startCounter(Main main) {
        counterStarted = true;
        counter = 60;
        new BukkitRunnable() {
            @Override
            public void run() {
                if(!counterStarted) {
                    world.getPlayers().forEach(player -> player.sendMessage(prefix + "§cDer Countdown wurde abgebrochen!"));
                    cancel();
                }
                if(counter > 0) {
                    if(counter == 60 || counter == 45 || counter == 30 || counter == 15 || counter == 10 || counter <= 5 && counter > 1) sendToWorld("§aDas Spiel startet in §e" + counter + " §aSekunden!");
                    if(counter == 1) sendToWorld("§aDas Spiel startet in §e" + counter + " §aSekunde!");
                    counter--;
                } else {
                    startGame(main);
                    cancel();
                }
            }
        }.runTaskTimer(main, 20, 20);
    }

    public static int countPeople() {
        return world.getPlayers().size();
    }

    public static void sendToWorld(String message) {
        world.getPlayers().forEach(player -> player.sendMessage(prefix + message));
    }

    public static void startGame(Main main) {
        counter = 10;
        counterStarted = false;
        Main.ggState = GGState.INGAME;
        int i = 1;
        sendToWorld("§aDas Spiel startet!");
        for(Player player : world.getPlayers()) {
            player.teleport(main.fm.getLocation("gungame.spawn." + i));
            alive.add(player);
            player.getInventory().clear();
            player.getInventory().setContents(GGInventories.goldsword());
            level.put(player, 1);
            i++;
        }
    }

    public static boolean isWorld(World world) {
        if(world == GGMain.world) {
            return true;
        }
        return false;
    }

    public static void updateInventory(Player player) {
        if(level.get(player) == 1) {
            player.getInventory().setContents(GGInventories.goldsword());
        }else if(level.get(player) == 2) {
            player.getInventory().setContents(GGInventories.ironShovel());
        }else if(level.get(player) == 3) {
            player.getInventory().setContents(GGInventories.diamondSword());
        }else if(level.get(player) == 4) {
            player.getInventory().setContents(GGInventories.arrow());
        }else if(level.get(player) == 5) {
            player.getInventory().setContents(GGInventories.woodenAxe());
        }
    }

    public static void endGame(Main plugin, Player winner) {
        world.getPlayers().forEach(player -> player.sendTitle("§e" + winner.getName(), "§ahat das Spiel gewonnen!", 10, 60, 10));
        Main.ggState = GGState.END;
        level.clear();
        new BukkitRunnable() {
            @Override
            public void run() {
                if(counter > 0) {
                    if(counter == 10 || counter < 6) sendToWorld("§cDie Lobby schließt in §e" + counter + " §cSekunden");
                    counter--;
                } else {
                    Main.ggState = GGState.LOBBY;
                    world.getPlayers().forEach(player -> Utils.lobbyteleport(plugin, player));
                    cancel();
                }
            }
        }.runTaskTimer(plugin, 0, 20);
    }
    
}
