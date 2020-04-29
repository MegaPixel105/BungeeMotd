package fr.megapixel.game;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import fr.megapixel.Practice;
import fr.megapixel.game.task.WinTask;
import fr.megapixel.util.TitleUtils;

public class Play {
	
	private List<Player> players = new ArrayList<>();
	private List<Player> allPlayers = new ArrayList<>();
	private Arene arene;
	private String gameName;
	private boolean ranked;
	private List<Block> blockPlaced = new ArrayList<>(); 
	
	public Play(Arene arene, String gameName, boolean ranked) {
		this.arene = arene;
		this.gameName = gameName;
		this.ranked = ranked;
	}
	
	public Arene getArene() {
		return arene;
	}
	
	public List<Player> getPlayers() {
		return players;
	}
	
	public List<Player> getAllPlayers() {
		return allPlayers;
	}
	
	public String getGameName() {
		return gameName;
	}
	
	public boolean isRanked() {
		return ranked;
	}
	
	private boolean win() {
		if(getPlayers().size() == 0 || getPlayers().size() == 1) {
			return true;
		}
		return false;
	}
	
	public List<Block> getBlockPlaced() {
		return blockPlaced;
	}
	
	public void checkWin() {
		if(win()) {
			
			for(Game game : Practice.games) {
				if(game.getName().equalsIgnoreCase(gameName)) {
					game.getArenes().add(arene);
				}
			}
			
			Player player1 = getAllPlayers().get(0);
			Player player2 = getAllPlayers().get(1);
			
			Player winner = getPlayers().get(0);
			
			TitleUtils.sendTitle(winner, "§aVictoire..", "§aVous avez gagné", 80);
			
			if(winner == player1) {
				TitleUtils.sendTitle(player2, "§cDéfaite..", "§cVous avez perdu", 80);
			} else {
				TitleUtils.sendTitle(player1, "§cDéfaite..", "§cVous avez perdu", 80);
			}
			
			if(ranked) {
				//on lui ajoute des élos 
			}
			
			WinTask task = new WinTask(player1, player2, getPlayers(), getAllPlayers());
			task.runTaskTimer(Practice.getInstance(), 20, 20);
			
			for(Block b : getBlockPlaced()) {
				b.setType(Material.AIR);
			}
			
		}
	}

}
