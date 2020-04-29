package fr.megapixel.listener;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import fr.megapixel.util.ItemManager;

public class PlayerJoin implements Listener {
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		
		Player player = event.getPlayer();
		
		event.setJoinMessage("");
		
		ItemManager.sendJoinItem(player);
		
		player.setGameMode(GameMode.ADVENTURE);
		
	}

}
