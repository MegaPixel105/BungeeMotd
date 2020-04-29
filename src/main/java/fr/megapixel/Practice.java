package fr.megapixel;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import fr.megapixel.game.Arene;
import fr.megapixel.game.Game;
import fr.megapixel.game.Play;
import fr.megapixel.listener.FakeDeath;
import fr.megapixel.listener.GameListener;
import fr.megapixel.listener.InventoryListener;
import fr.megapixel.listener.PlayerInteract;
import fr.megapixel.listener.PlayerJoin;
import fr.megapixel.listener.PlayerQuit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Practice extends JavaPlugin {
	
	public static List<Game> games = new ArrayList<>();
	public static List<Play> game = new ArrayList<>();
	public static Inventory invMenu;
	
	private static Practice instance;
	
	public static Practice getInstance() {
		return instance;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void onEnable() {
		
		saveDefaultConfig();
		
		instance = this;
		
		invMenu = Bukkit.createInventory(null, 27, "§cPractice");
		
		PluginManager pm = getServer().getPluginManager();
		
		pm.registerEvents(new PlayerJoin(), this);
		pm.registerEvents(new PlayerQuit(), this);
		pm.registerEvents(new PlayerInteract(), this);
		pm.registerEvents(new InventoryListener(), this);
		pm.registerEvents(new GameListener(), this);
		pm.registerEvents(new FakeDeath(), this);
		
		ConfigurationSection section = getConfig().getConfigurationSection("game");
		for(String s : section.getKeys(false)) {
			
			String name = section.getString(s + ".name");
			List<ItemStack> items = new ArrayList<>();
			List<Arene> arenes = new ArrayList<>();
			String m = section.getString(s + ".item");
			Material mats = Material.matchMaterial(m);
			
			ConfigurationSection arene = getConfig().getConfigurationSection("game." + s + ".arenes");
			for(String ar : arene.getKeys(false)) {
				
				int x1 = arene.getInt(ar + ".x1");
				int y1 = arene.getInt(ar + ".y1");
				int z1 = arene.getInt(ar + ".z1");
				float yaw1 = arene.getInt(ar + ".yaw1");
				float pitch1 = arene.getInt(ar + ".pitch1");
				
				int x2 = arene.getInt(ar + ".x2");
				int y2 = arene.getInt(ar + ".y2");
				int z2 = arene.getInt(ar + ".z2");
				float yaw2 = arene.getInt(ar + ".yaw2");
				float pitch2 = arene.getInt(ar + ".pitch2");
				
				Location loc1 = new Location(Bukkit.getWorld("world"), x1, y1, z1, yaw1, pitch1);
				Location loc2 = new Location(Bukkit.getWorld("world"), x2, y2, z2, yaw2, pitch2);
				
				Arene are = new Arene(loc1, loc2);
				arenes.add(are);
				
			}
			
			ConfigurationSection item = getConfig().getConfigurationSection("game." + s + ".kit");
			for(String it : item.getKeys(false)) {
				
				String mate = item.getString(it + ".material");
				int amount = item.getInt(it + ".amount");
				int data = item.getInt(it + ".data");
				Material mat = Material.matchMaterial(mate);
				
				ItemStack i = new ItemStack(Material.BARRIER, 1);
				
				ConfigurationSection enchantment = getConfig().getConfigurationSection("game." + s + ".kit.enchantment");
				if(enchantment != null) {
					
					for(String enchant : enchantment.getKeys(false)) {
						
						int enchantId = enchantment.getInt(enchant + ".id");
						int enchantLevel = enchantment.getInt(enchant + ".level") - 1;
						
						Map<Enchantment, Integer> ench = new HashMap<>();
						
						ench.put(Enchantment.getById(enchantId), enchantLevel);
						
						i.addEnchantments(ench);
						
					}
					
				} else {
					if(mat == null) mat = Material.BARRIER;
					i = new ItemStack(mat, amount, (short)data);
				}
				
				items.add(i);
				
			}
			
			Game game = new Game(name, items, arenes, mats);
			games.add(game);
			
		}
		
		List<ItemStack> items = new ArrayList<>();
		
		for(Game games : games) {
			items.add(games.getRepresentativeItem());
		}
		
		for(ItemStack it : items) {
			invMenu.addItem(it);
		}
		
	}
	
	@Override
	public void onDisable() {
		
	}
	
	public static Location getSpawn() {
		double x = getInstance().getConfig().getInt("spawn.x");
		double y = getInstance().getConfig().getInt("spawn.y");
		double z = getInstance().getConfig().getInt("spawn.z");
		float yaw = getInstance().getConfig().getInt("spawn.yaw");
		float pitch = getInstance().getConfig().getInt("spawn.pitch");
		Location loc = new Location(Bukkit.getWorld("world"), x, y, z, yaw, pitch);
		return loc;
	}

}
