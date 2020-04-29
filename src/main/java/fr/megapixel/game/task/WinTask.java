package fr.megapixel.game.task;

import java.util.List;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import fr.megapixel.Practice;

public class WinTask extends BukkitRunnable {

	private Player player1;
	private Player player2;
	List<Player> players;
	List<Player> player;
	private int timer = 4;
	
	public WinTask(Player player1, Player player2, List<Player> players, List<Player> player) {
		this.player1 = player1;
		this.player2 = player2;
		this.players = players;
		this.player = player;
	}

	@Override
	public void run() {
		
		timer--;
		
		if(timer == 0) {
			
			player1.teleport(Practice.getSpawn());
			player2.teleport(Practice.getSpawn());
			
			player1.setGameMode(GameMode.ADVENTURE);
			player2.setGameMode(GameMode.ADVENTURE);
			
			player.clear();
			players.clear();
			
		}
		
	}

}
