package fr.megapixel.listener;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import fr.megapixel.Practice;
import fr.megapixel.game.Game;
import fr.megapixel.util.ItemManager;

public class PlayerInteract implements Listener {

	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		
		Player player = event.getPlayer();
		ItemStack it = event.getItem();
		
		if(it == null) return;
		
		for(Game game : Practice.games) {
			if(it.getType() == Material.IRON_SWORD && !game.getInGame().contains(player)) {
				player.openInventory(Practice.invMenu);
				player.updateInventory();
			}
			if(it.getType() == Material.SPRUCE_DOOR_ITEM && !game.getInWait().contains(player)) {
				game.getInWait().remove(player);
				ItemManager.sendJoinItem(player);
			}
		}
		
	}
	
}
