package wasdenn;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import wasdenn.Commands.GmCommand;
import wasdenn.Commands.HealCommand;
import wasdenn.Commands.SdmCommand;
import wasdenn.Commands.WahrheitCommand;
import wasdenn.Listeners.ChatFarbenListener;
import wasdenn.Listeners.JoinLeaveKickListener;

import java.util.ArrayList;

public class Main extends JavaPlugin implements Listener {

    public ArrayList<Player> marmeladenbrotmithonig = new ArrayList<>();

    @Override
    public void onEnable() {

        registerEvents();
        registerCommands();

        this.getServer().getPluginManager().registerEvents(this, this);

        System.out.println("[Info] Plugin erfolgreich aktiviert!");
    }
    @Override
    public void onDisable() {
        System.out.println("[Info] Plugin erfolgreich deaktiviert!");
    }

    public boolean onCommand(CommandSender sender, Command cmd, String cmdlabel, String[] args) {
        Player p = (Player) sender;

        return false;
    }
        public void registerCommands() {
            getCommand("dersatzdesmeyssam").setExecutor(new SdmCommand(this));
            getCommand("gm").setExecutor(new GmCommand(this));
            getCommand("heal").setExecutor(new HealCommand(this));
            getCommand("wahrheit").setExecutor(new WahrheitCommand(this));
    }
        public void registerEvents() {
            new JoinLeaveKickListener(this);
            new ChatFarbenListener(this);
        }
    }













  // public void resetWorld(World world) {
  //     Bukkit.unloadWorld(world, true);
  //   deleteDirectory(new File(world.getWorldFolder().getPath()));
  // new BukkitRunnable() {
  //     @Override
  //     public void run() {
  //        WorldCreator worldCreator = new WorldCreator("world");

  //worldCreator.environment(World.Environment.NORMAL);
  //   Bukkit.shutdown();
  // }
  //}.runTaskLater(this, 100);

  //

  //public static boolean deleteDirectory(File path) {
  //    if( path.exists() ) {
  //       File[] files = path.listFiles();
  //     for(int i=0; i<files.length; i++) {
  //         if(files[i].isDirectory()) {
  // deleteDirectory(files[i]);
  //}
  // else {
  // files[i].delete();
  //       }
  //  }
  //}
  // return( path.delete() );
//