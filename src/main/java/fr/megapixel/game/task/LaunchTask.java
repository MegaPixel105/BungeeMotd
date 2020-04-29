package fr.megapixel.game.task;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import fr.megapixel.game.Arene;
import fr.megapixel.util.TitleUtils;

public class LaunchTask extends BukkitRunnable {

	private Player player1;
	private Player player2;
	private Arene arene;
	private int timer = 5;
	
	public LaunchTask(Player player1, Player player2, Arene arene) {
		this.player1 = player1;
		this.player2 = player2;
		this.arene = arene;
	}

	@Override
	public void run() {
		
		timer--;
		
		TitleUtils.sendTitle(player1, "§aLancement..", "§adans §b" + timer, 20);
		TitleUtils.sendTitle(player2, "§aLancement..", "§adans §b" + timer, 20);
		
		if(timer == 0) {
			
			player1.setGameMode(GameMode.SURVIVAL);
			player2.setGameMode(GameMode.SURVIVAL);
			
			player1.teleport(arene.getLoc1());
			player2.teleport(arene.getLoc2());
			
			cancel();
			
		}
		
	}

}
