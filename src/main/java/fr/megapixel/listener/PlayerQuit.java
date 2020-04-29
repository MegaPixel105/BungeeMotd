package fr.megapixel.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import fr.megapixel.Practice;
import fr.megapixel.game.Game;
import fr.megapixel.game.Play;

public class PlayerQuit implements Listener {
	
	@EventHandler
	public void onQuit(PlayerQuitEvent event) {
		
		Player player = event.getPlayer();
		
		event.setQuitMessage(null);
		
		for(Game game : Practice.games) {
			game.getInGame().remove(player);
			game.getInWait().remove(player);
		}
		
		for(Play play : Practice.game) {
			play.getPlayers().remove(player);
			play.checkWin();
		}
		
	}

}
