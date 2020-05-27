package wasdenn.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import wasdenn.Main;

/**
 * Created by Meyssam Saghiri on Mai 27, 2020
 */
public class Wartungsmodus implements CommandExecutor {

    private Main plugin;

    public Wartungsmodus(Main main) {
        this.plugin = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(!sender.isOp()) {
            sender.sendMessage("§cDu bist eine Frau, deswegen hast du keine Rechte!");
            return true;
        }
        if(!plugin.isWartung) {
            plugin.isWartung = true;
            sender.sendMessage("§aDer Server befindet sich jetzt im Wartungsmodus!");
            Bukkit.broadcastMessage("§e" + sender.getName() + " §ahat den Server in den Wartungsmodus gesetzt");
            Bukkit.getOnlinePlayers().forEach(player -> {
                if(!player.isOp()) {
                    player.kickPlayer("§cDer Server wird derzeit gewartet! Schau doch später wieder vorbei.");
                }
            });
        } else {
            plugin.isWartung = false;
            sender.sendMessage("§aDer Server befindet sich jetzt nicht mehr im Wartungsmodus!");
            Bukkit.broadcastMessage("§e" + sender.getName() + " §chat den Wartungsmodus deaktiviert");
        }
        return true;
    }
}
