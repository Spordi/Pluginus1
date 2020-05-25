package wasdenn.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import wasdenn.Main;

public class SdmCommand implements CommandExecutor {
    private final Main plugin;

    public SdmCommand(Main main) {
        this.plugin = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("Du huescheler, das geht nicht. Was denkst du wer du bist?!?ß1ß1ß?!ß!ß1?!?!?!?!");
            return true;
        }
        Player p = (Player) sender;

        if (cmd.getName().equalsIgnoreCase("dersatzdesmeyssam")) {
            p.sendMessage("§6Meyssam hat immer recht.");
            p.sendMessage("§6da kann man nichts machen :/");
            return true;
        }

        return true;
    }
}
