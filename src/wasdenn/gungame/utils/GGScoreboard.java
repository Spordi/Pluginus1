package wasdenn.gungame.utils;

import net.minecraft.server.v1_15_R1.PacketPlayOutTags;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

/**
 * Created by Meyssam Saghiri on Mai 30, 2020
 */
public class GGScoreboard {

    public static void createScoreboard(Player p) {
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective obj = scoreboard.registerNewObjective("aaa", "bbb", "ccc");

        obj.setDisplayName("§6§lGunGame");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);

        //obj.getScore("Tode").setScore(GGMain.deaths.get(p));
        //obj.getScore("Kills").setScore(GGMain.kills.get(p));

        setScores(p, obj);

        p.setScoreboard(scoreboard);
    }

    public static void updateScoreboard(Player p) {
        Objective obj = p.getScoreboard().getObjective("aaa");
        PacketPlayOutTags dans = new PacketPlayOutTags();
        //obj.getScore("Tode").setScore(GGMain.deaths.get(p));
        //obj.getScore("Kills").setScore(GGMain.kills.get(p));

        setScores(p, obj);
    }

    private static void setScores(Player p, Objective obj) {
        for(Player player : GGMain.world.getPlayers()) {
            if(player == p) {
                obj.getScore("§c"+player.getName()).setScore(GGMain.level.get(player)-1);
            }else {
                obj.getScore("§e"+player.getName()).setScore(GGMain.level.get(player)-1);
            }
        }
    }


}
