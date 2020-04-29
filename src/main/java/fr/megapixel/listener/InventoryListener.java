package fr.megapixel.listener;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.megapixel.Practice;
import fr.megapixel.game.Game;

public class InventoryListener implements Listener {
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		
		Player player = (Player) event.getWhoClicked();
		ItemStack it = event.getCurrentItem();
		Inventory inv = event.getInventory();
		
		if(it == null || inv == null) return;
		if(!it.hasItemMeta()) return;
		
		if(inv.getName().equalsIgnoreCase("§cPractice")) {
			
			for(Game game : Practice.games) {
				
				if(game.getInGame().contains(player) || game.getInWait().contains(player)) {
					return;
				}
				
				if(it.getItemMeta().getDisplayName().equalsIgnoreCase(game.getRepresentativeItem().getItemMeta().getDisplayName())) {
					
					game.getInWait().add(player);
					
					game.startGame();
					
					player.getInventory().clear();
					
					ItemStack quit = new ItemStack(Material.SPRUCE_DOOR_ITEM, 1);
					ItemMeta q = quit.getItemMeta();
					q.setDisplayName("§cQuitter la file d'attente");
					quit.setItemMeta(q);
					
				}
				
			}
			
		}
		
	}

}
