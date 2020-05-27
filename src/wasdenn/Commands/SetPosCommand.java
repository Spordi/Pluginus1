package wasdenn.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import wasdenn.Main;

public class SetPosCommand implements CommandExecutor {
    private final Main plugin;

    public SetPosCommand(Main main) {
        this.plugin = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("§cDu heuscheler, das geht nicht. Was denkst du wer du bist?!?ß1ß1ß?!ß!ß1?!?!?!?!");
            return true;
        }
        if(!sender.isOp()) {
            sender.sendMessage("§cDu Frau.");
            return true;
        }
        if(args.length != 1) {
            sender.sendMessage("§c/setpos <destination>");
            return true;
        }
            Player p = (Player) sender;
        plugin.fm.setLocation(args[0], p.getLocation());
        return true;
    }
}

