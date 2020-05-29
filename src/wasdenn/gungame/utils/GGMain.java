package wasdenn.gungame.utils;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import wasdenn.Main;

import java.util.ArrayList;

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

    public static void startCounter(Main main) {
        counterStarted = true;
        new BukkitRunnable() {
            @Override
            public void run() {
                if(!counterStarted) {
                    world.getPlayers().forEach(player -> player.sendMessage(prefix + "§cDer Countdown wurde abgebrochen!"));
                    cancel();
                }
                if(counter > 0) {
                    if(counter == 60 || counter == 45 || counter == 30 || counter == 15 || counter == 10 || counter <= 5 && counter > 1) sendToWorld("§aDas Spiel startet in §e" + counter + "§aSekunden!");
                    if(counter == 1) sendToWorld("§aDas Spiel startet in §e" + counter + "§aSekunde!");
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
        counterStarted = false;
        Main.ggState = GGState.GREACE;
        counter = 60;
        int i = 1;
        for(Player player : world.getPlayers()) {
            player.teleport(main.fm.getLocation("gungame.spawn." + i));
            alive.add(player);
            i++;
        }
    }

}
