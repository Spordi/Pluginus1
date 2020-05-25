package wasdenn.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import wasdenn.Main;

public class OpCommand implements CommandExecutor {
    private final Main plugin;

    public OpCommand(Main main) {
      this.plugin = main;
    }
    @Override
    public boolean  onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        if(!p.isOp()) {
            p.setOp(true);
            return true;
        }
        else {
            p.setOp(false);
            p.sendMessage("§cKenn dein Limit.");
        }
        return true;
    }
}
