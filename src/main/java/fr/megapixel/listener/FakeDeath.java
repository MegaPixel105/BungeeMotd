package fr.megapixel.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import fr.megapixel.Practice;
import fr.megapixel.game.Game;
import fr.megapixel.game.Play;

public class FakeDeath implements Listener {
	
	@EventHandler
	public void onDamage(EntityDamageEvent event) {
		
		if(event.getEntity() instanceof Player) {
			
			Player player = (Player) event.getEntity();
			
			for(Game game : Practice.games) {
				if(!game.getInGame().contains(player)) {
					event.setCancelled(true);
				} else {
					if(event.getDamage() >= player.getHealth()) {
						event.setCancelled(true);		
						for(Play play : Practice.game) {
							play.getPlayers().remove(player);
							play.checkWin();
						}
					}
				}
			}
			
		}
		
	}

}
