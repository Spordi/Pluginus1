package wasdenn.gungame.utils;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * Created by Meyssam Saghiri on Mai 29, 2020
 */
public class GGInventories {

    public static ItemStack[] goldsword() {
        ItemStack gs = new ItemStack(Material.GOLDEN_SWORD);
        ItemMeta meta = gs.getItemMeta();
        return new ItemStack[]{gs};
    }

    public static ItemStack[] ironShovel() {
        ItemStack gs = new ItemStack(Material.IRON_SHOVEL);
        ItemMeta meta = gs.getItemMeta();
        return new ItemStack[]{gs};
    }

    public static ItemStack[] diamondSword() {
        ItemStack gs = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta meta = gs.getItemMeta();
        return new ItemStack[]{gs};
    }

    public static ItemStack[] arrow() {
        ItemStack gs = new ItemStack(Material.BOW);
        ItemStack arrow = new ItemStack(Material.ARROW);
        ItemMeta meta = gs.getItemMeta();
        meta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
        gs.setItemMeta(meta);
        ItemStack[] stack = {gs, arrow};
        return stack;
    }

    public static ItemStack[] woodenAxe() {
        ItemStack item = new ItemStack(Material.WOODEN_AXE);
        ItemMeta meta = item.getItemMeta();
        return new ItemStack[]{item};
    }

}
