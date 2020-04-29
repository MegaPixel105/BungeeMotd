package fr.megapixel.util;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.megapixel.Practice;
import fr.megapixel.game.Game;

public class ItemManager {
	
	public static void sendJoinItem(Player player) {
		
		ItemStack unranked = new ItemStack(Material.IRON_SWORD, 1);
		ItemMeta u = unranked.getItemMeta();
		u.setDisplayName("§cUnRanked - 1 vs 1");
		unranked.setItemMeta(u);
		
		player.getInventory().setItem(0, unranked);
		
	}
	
	public static void sendKit(String gameName, Player player) {
		for(Game game : Practice.games) {
			if(game.getName().equalsIgnoreCase(gameName)) {
				for(ItemStack it : game.getKit()) {
					player.getInventory().addItem(it);
				}
			}
		}
	}

}
