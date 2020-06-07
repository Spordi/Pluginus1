package wasdenn.Commands;

import com.google.common.collect.Maps;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import net.minecraft.server.v1_15_R1.Packet;
import net.minecraft.server.v1_15_R1.PacketPlayOutEntityDestroy;
import net.minecraft.server.v1_15_R1.PacketPlayOutNamedEntitySpawn;
import net.minecraft.server.v1_15_R1.PacketPlayOutPlayerInfo;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_15_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import wasdenn.Main;
import wasdenn.Utils.GameProfileFetcher;
import wasdenn.Utils.NewNicker;
import wasdenn.Utils.UUIDFetcher;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;

/**
 * Created by Meyssam on 19.06.2016.
 */
public class NickCommand implements CommandExecutor {

    public String prefix = "§8[§5NICK§8] §r";
    public String perm = "§8[§5NICK§8] §r§cHättest du wohl gerne!";
    ArrayList<Player> nicked = new ArrayList<>();
    private static final Map<String, GameProfile> BY_NAME = Maps.newHashMap();
  //  private DisguiseAPI api;
    public static List<String> nicks = new ArrayList<>();
    private Field nameField;
    private HashMap<Player, String> playername = new HashMap<>();

    private Main plugin;

    public NickCommand(Main main) {
        this.plugin = main;
    }

    public void setValues() {
       // api = getServer().getServicesManager().getRegistration(DisguiseAPI.class).getProvider();
        nameField = getField(GameProfile.class, "name");
        Bukkit.getConsoleSender().sendMessage(prefix + "§2Plugin aktiviert!");
        if (plugin.getConfig().get("nicks") == null) {
            List<String> l = new ArrayList<>();
            l.add("Spordi");

            plugin.getConfig().set("nicks", l);
            plugin.saveConfig();
        }
        nicks = plugin.getConfig().getStringList("nicks");
    }


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("nick")) {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                if(p.isOp()) {
                    if (!nicked.contains(p)) {

                        playername.put(p, p.getName());
                        Random r = new Random();
                        String nick = nicks.get(r.nextInt(nicks.size()));
                        //  api.disguise(p, new PlayerDisguise(nick, false));
                        setNicked(p, nick);
                        nicked.add(p);
                        p.sendMessage(prefix + "§5Du spielst nun als: §6" + nick);
                    } else {
                        // api.undisguise(p);
                        setNicked(p, playername.get(p));
                        nicked.remove(p);
                        p.sendMessage(prefix + "§5Du spielst nun wieder als: §6" + p.getName());
                    }
                }else {
                    p.sendMessage(perm);
                }
            }else {
                sender.sendMessage(prefix + "§cDu musst ein Spieler sein!");
            }

            return true;
        }
        return false;
    }


    private void setNicked(Player p, String nickname) {
        changeName(p, nickname);
     //   changeSkin(p, nickname);
        final GameProfile profile2 = NewNicker.getProfile(nickname, p.getUniqueId());
        if (profile2 != null) {
            NewNicker.setProfile(p, profile2);
        }
    }


    private static Field getField(final Class<?> clazz) {
        Field field = null;
        for (final Field f : clazz.getDeclaredFields()) {
            f.setAccessible(true);
            if (f.getType().equals(GameProfile.class)) {
                field = f;
            }
        }
        if (field == null) {
            for (final Field f : clazz.getFields()) {
                f.setAccessible(true);
                if (f.getType().equals(GameProfile.class)) {
                    field = f;
                }
            }
        }
        if (clazz.getSuperclass() != null && field == null) {
            field = getField(clazz.getSuperclass());
        }
        return field;
    }



    private void changeName(Player p, String nick) {
        CraftPlayer cp = (CraftPlayer)p;
        try {
            nameField.set(cp.getProfile(), nick);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        PacketPlayOutEntityDestroy destroy = new PacketPlayOutEntityDestroy(cp.getEntityId());
        sendPacket(destroy);
        removeFromTab(p);
        new BukkitRunnable() {
            @Override
            public void run() {
                addToTablist(p);
                p.setDisplayName(nick);
                p.setCustomName(nick);
                p.setPlayerListName(nick);
                PacketPlayOutNamedEntitySpawn spawn = new PacketPlayOutNamedEntitySpawn(cp.getHandle());
                for(Player players : Bukkit.getOnlinePlayers()) {
                    if(players != p) {
                        ((CraftPlayer) players).getHandle().playerConnection.sendPacket(spawn);
                    }
                }
            }
        }.runTaskLater(plugin, 4);
    }

    private Field getField(Class<?> clazz, String name) {
        try {
            Field field = clazz.getDeclaredField(name);
            field.setAccessible(true);
            return field;
        } catch (NoSuchFieldException e) {
            return null;
        }

    }

    private void removeFromTab(Player p) {
        CraftPlayer cp = (CraftPlayer)p;
        PacketPlayOutPlayerInfo info = new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.REMOVE_PLAYER, cp.getHandle());
        sendPacket(info);
    }

    private void addToTablist(Player p) {
        CraftPlayer cp = (CraftPlayer)p;
        PacketPlayOutPlayerInfo info = new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, cp.getHandle());
        sendPacket(info);
    }

    private void changeSkin(Player p, String name) {
        CraftPlayer cp = (CraftPlayer)p;
        GameProfile skingp = cp.getProfile();
        try {
            skingp = GameProfileFetcher.fetch(UUIDFetcher.getUUID(name));
        } catch (IOException e) {
            p.sendMessage(prefix + "§cEs ist ein Fehler beim Laden des Skins unterlaufen!");
            e.printStackTrace();
            return;
        }
        Collection<Property> props = skingp.getProperties().get("textures");
        cp.getProfile().getProperties().removeAll("textures");
        cp.getProfile().getProperties().putAll("textures", props);

        //packets
        PacketPlayOutEntityDestroy destroy = new PacketPlayOutEntityDestroy(cp.getEntityId());
        sendPacket(destroy);
        PacketPlayOutPlayerInfo info = new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.REMOVE_PLAYER, cp.getHandle());
        sendPacket(info);
        new BukkitRunnable() {
            @Override
            public void run() {
                PacketPlayOutPlayerInfo addinfo = new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, cp.getHandle());
                sendPacket(addinfo);
                PacketPlayOutNamedEntitySpawn spawn = new PacketPlayOutNamedEntitySpawn(cp.getHandle());
                //
            //    HashMap<Player, Location> map = new HashMap<>();
            //    map.put(p, p.getLocation());
            //    p.setHealth(0D);
            //    p.spigot().respawn();
            //    p.teleport(map.get(p));
                //
                for(Player players : Bukkit.getOnlinePlayers()) {
                    if(players != p) {
                        ((CraftPlayer) players).getHandle().playerConnection.sendPacket(spawn);
                    }
                }
            }
        }.runTaskLater(plugin, 4);
        //packets
    }


    private void sendPacket(Packet<?> packet) {
        for(Player players : Bukkit.getOnlinePlayers()) {
            ((CraftPlayer)players).getHandle().playerConnection.sendPacket(packet);
        }
    }
}
