package fr.megapixel.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.megapixel.Practice;
import fr.megapixel.game.task.LaunchTask;
import fr.megapixel.util.ItemManager;

public class Game {
	
	private String name;
	private List<ItemStack> kit;
	private List<Arene> arenes;
	private Material representative;
	private List<Player> inWait = new ArrayList<>();
	private List<Player> inGame = new ArrayList<>();
	
	public Game(String name, List<ItemStack> kit, List<Arene> arenes, Material it) {
		this.name = name;
		this.kit = kit;
		this.arenes = arenes;
		this.representative = it;
	}
	
	public String getName() {
		return name;
	}
	
	public List<ItemStack> getKit() {
		return kit;
	}
	
	public List<Arene> getArenes() {
		return arenes;
	}
	
	public List<Player> getInGame() {
		return inGame;
	}
	
	public List<Player> getInWait() {
		return inWait;
	}
	
	public ItemStack getRepresentativeItem() {
		ItemStack it = new ItemStack(representative, getInGame().size());
		ItemMeta i = it.getItemMeta();
		i.setDisplayName(name);
		i.setLore(Arrays.asList("§eEn attente : §6" + getInWait().size(), "§eEn jeu : §6" + getInGame().size()));
		it.setItemMeta(i);
		return it;
	}
	
	private boolean checkLaunch() {
		if(getInWait().size() >= 2) {
			return true;
		}
		return false;
	}
	
	public void startGame() {
		if(checkLaunch()) {
			
			Player player1 = getInWait().get(0);
			Player player2 = getInWait().get(1);
			
			if(getArenes().size() == 0) {
				player1.sendMessage("§cIl n'y a plus d'arène disponible !");
				player2.sendMessage("§cIl n'y a plus d'arène disponible !");
				return;
			}
			
			getInGame().add(player1);
			getInGame().add(player2);
			
			getInWait().remove(player1);
			getInWait().remove(player2);
			
			Arene arene = getArenes().get(0);
			
			Play game = new Play(arene, name, false);
			
			game.getPlayers().add(player1);
			game.getPlayers().add(player2);
			
			game.getAllPlayers().add(player1);
			game.getAllPlayers().add(player2);
			
			Practice.game.add(game);
			
			getArenes().remove(arene);
			
			player1.teleport(arene.getLoc1());
			player2.teleport(arene.getLoc2());
			
			ItemManager.sendKit(name, player1);
			ItemManager.sendKit(name, player2);
		}
	}
	
	public void startGameRanked() {
		if(checkLaunch()) {
			
			Player player1 = getInWait().get(0);
			Player player2 = getInWait().get(1);
			
			if(getArenes().size() == 0) {
				player1.sendMessage("§cIl n'y a plus d'arène disponible !");
				player2.sendMessage("§cIl n'y a plus d'arène disponible !");
				return;
			}
			
			getInGame().add(player1);
			getInGame().add(player2);
			
			getInWait().remove(player1);
			getInWait().remove(player2);
			
			Arene arene = getArenes().get(0);
			
			Play game = new Play(arene, name, true);
			
			game.getPlayers().add(player1);
			game.getPlayers().add(player2);
			
			Practice.game.add(game);
			
			getArenes().remove(arene);
			
			player1.teleport(arene.getLoc1());
			player2.teleport(arene.getLoc2());
			
			ItemManager.sendKit(name, player1);
			ItemManager.sendKit(name, player2);
			
			player1.setGameMode(GameMode.SPECTATOR);
			player2.setGameMode(GameMode.SPECTATOR);
			
			LaunchTask task = new LaunchTask(player1, player2, arene);
			task.runTaskTimer(Practice.getInstance(), 20, 20);
			
		}
	}
	
}
