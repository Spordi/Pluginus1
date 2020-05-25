package wasdenn.Commands;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import wasdenn.Main;

public class GmCommand implements CommandExecutor {

    private final Main plugin;

    public GmCommand(Main main) {
this.plugin = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        if (cmd.getName().equalsIgnoreCase("gm"))
            if (p.isOp()) {
                if(args.length==1) {
                    if(args[0].equalsIgnoreCase("0")) {
                        p.setGameMode(GameMode.SURVIVAL);
                        p.sendMessage("Set own gamemode to Survival Mode");
                    }
                    else if(args[0].equalsIgnoreCase("1")) {
                        p.setGameMode(GameMode.CREATIVE);
                        p.sendMessage("Set own gamemode to Creative Mode");
                    }
                    else if(args[0].equalsIgnoreCase("2")) {
                        p.setGameMode(GameMode.ADVENTURE);
                        p.sendMessage("Set own gamemode to Adventure Mode");
                    }
                    else if(args[0].equalsIgnoreCase("3")) {
                        p.setGameMode(GameMode.SPECTATOR);
                        p.sendMessage("Set own gamemode to Spectator Mode");
                    }
                }
                if(args.length==2) {
                    Player p1 = Bukkit.getPlayer(args[1]);
                    if(p1==null) {
                        p.sendMessage("§cGeht nicht du huso");
                        return true;
                    }
                    if(args[0].equalsIgnoreCase("0")) {
                        p1.setGameMode(GameMode.SURVIVAL);
                        p1.sendMessage(p.getDisplayName() + " set your gamemode to Survival Mode");
                        p.sendMessage("Set" + p1.getDisplayName() + "'s gamemode to Survival Mode");
                    }
                    else if(args[0].equalsIgnoreCase("1")) {
                        p1.setGameMode(GameMode.CREATIVE);
                        p1.sendMessage(p.getDisplayName() + " set your gamemode Creative Mode");
                        p.sendMessage("Set" + p1.getDisplayName() + "'s gamemode to Creative Mode");
                    }
                    else if(args[0].equalsIgnoreCase("2")) {
                        p1.setGameMode(GameMode.ADVENTURE);
                        p1.sendMessage(p.getDisplayName() + " set your gamemode to Adventure Mode");
                        p.sendMessage("Set" + p1.getDisplayName() + "'s gamemode to Adventure Mode");
                    }
                    else if(args[0].equalsIgnoreCase("3")) {
                        p1.setGameMode(GameMode.SPECTATOR);
                        p1.sendMessage(p.getDisplayName() + " set your gamemode to Spectator Mode");
                        p.sendMessage("Set" + p1.getDisplayName() + "'s gamemode to Spectator Mode");
                    }
                    return true;

                }
                return true;
            }
        return false;
    }
}



