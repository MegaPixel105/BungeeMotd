package fr.megapixel.listener;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import fr.megapixel.Practice;
import fr.megapixel.game.Play;

public class GameListener implements Listener {
	
	@EventHandler
	public void onPlaceBlock(BlockPlaceEvent event) {
		
		Player player = event.getPlayer();
		Block b = event.getBlock();
		
		for (Play game : Practice.game) {
			if(game.getPlayers().contains(player)) {
				game.getBlockPlaced().add(b);
			} else {
				event.setCancelled(true);
			}
		}
		
	}
	
	@EventHandler
	public void onBreakBlock(BlockBreakEvent event) {
		
		Player player = event.getPlayer();
		Block b = event.getBlock();
		
		for (Play game : Practice.game) {
			if(game.getPlayers().contains(player)) {
				if(!game.getBlockPlaced().contains(b)) {
					event.setCancelled(true);
				}
			} else {
				event.setCancelled(true);
			}
		}
		
	}

}
