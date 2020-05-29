package wasdenn.gungame.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import wasdenn.Main;

/**
 * Created by Meyssam Saghiri on Mai 29, 2020
 */
public class MainCommand implements CommandExecutor {

    private Main plugin;

    public MainCommand(Main main) {
        this.plugin = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(!(sender instanceof Player)) {
           sender.sendMessage("§cDu musst ein Spieler sein!");
           return true;
        }
        Player p = (Player) sender;
        if(p.isOp()) {
            if(args[0].equalsIgnoreCase("setlobby")) {
                plugin.fm.setLocation("gungame.lobby", p.getLocation());
                p.sendMessage("§aDu hast erfolgreich die Lobby gesetzt!");
            } else if(args[0].equalsIgnoreCase("setspawn")) {
                if(args.length != 2) {
                    p.sendMessage("§c/gg setspawn <Nummer>");
                    return true;
                }
                int spawn;
                try {
                    spawn = Integer.parseInt(args[1]);
                }catch (NumberFormatException e) {
                    p.sendMessage("§cDein zweites Argument muss ne Nummer sein!");
                    return true;
                }
                if(spawn > 8) {
                    p.sendMessage("§cEs darf nur 8 Spawns geben!");
                    return true;
                }
                plugin.fm.setLocation("gungame.spawn." + spawn, p.getLocation());
                p.sendMessage("§aDu hast erfolgreich den Spawn " + spawn +  " gesetzt!");
            } else if(args[0].equalsIgnoreCase("setspec")) {
                plugin.fm.setLocation("gungame.lobby", p.getLocation());
                p.sendMessage("§aDu hast erfolgreich den Spectatorspawn gesetzt!");
            }

            else if(args[0].equalsIgnoreCase("tp")) {
                if(args[1].equalsIgnoreCase("lobby")) {
                    p.teleport(plugin.fm.getLocation("lobby"));
                    return true;
                }
                if(args[1].equalsIgnoreCase("gungame1")) p.teleport(plugin.fm.getLocation("gungame1"));
            }
        }
        return true;
    }
}
