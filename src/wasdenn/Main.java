package wasdenn;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

public class Main extends JavaPlugin implements Listener {

    ArrayList<Player> marmeladenbrotmithonig = new ArrayList<>();

    @Override
    public void onEnable() {

        registerEvents();

        this.getServer().getPluginManager().registerEvents(this, this);


        System.out.println("[Info] Plugin erfolgreich aktiviert!");
    }

    @Override
    public void onDisable() {
        System.out.println("[Info] Plugin erfolgreich deaktiviert!");
    }

    public boolean onCommand(CommandSender sender, Command cmd, String cmdlabel, String[] args) {
        Player p = (Player) sender;

        if (cmd.getName().equalsIgnoreCase("wahrheit")) {
            p.sendMessage("§2[Info] §5Der Server ist so schwul wie Meyssus");
            p.sendMessage("§3imagine being as schwul as the Server");
            return true;
        }

        if (cmd.getName().equalsIgnoreCase("dersatzdesmeyssam")) {
            p.sendMessage("§6Meyssam hat immer recht.");
            p.sendMessage("§6da kann man nichts machen :/");
            return true;
        }


        if (cmd.getName().equalsIgnoreCase("heal")) {
            if (marmeladenbrotmithonig.contains(p) && !p.isOp()) {
                p.sendMessage("§6lass das §4:rage:");
                return true;
            }

            marmeladenbrotmithonig.add(p);
            p.setHealth(20);
            p.setFoodLevel(20);
            p.sendMessage("§2Treffen sich zwei Jäger im Wald");
            new BukkitRunnable() {
                @Override
                public void run() {
                    p.sendMessage("§6Beide tot.");
                }
            }.runTaskLater(this, 27);
            new BukkitRunnable() {
                @Override
                public void run() {
                    p.sendMessage("§e*sad Jäger noises*");
                }
            }.runTaskLater(this, 53);
            new BukkitRunnable() {
                @Override
                public void run() {
                    marmeladenbrotmithonig.remove(p);

                }
            }.runTaskLater(this, 6000);

            return true;
        }
        if (cmd.getName().equalsIgnoreCase("clear"))
            if (p.hasPermission("Main.clear")) {
                p.getInventory().clear();
                p.sendMessage("Your inventory has been cleared");
                return true;
            }
        if (cmd.getName().equalsIgnoreCase("gm0"))
            if (p.isOp()) {
                p.setGameMode(GameMode.SURVIVAL);
                p.sendMessage("Set own gamemode to Survival Mode");
                return true;
            }
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

public void registerEvents() {
        new JoinLeaveKickEvent(this);
        new ChatFarben(this);
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