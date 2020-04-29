package fr.megapixel.game;

import org.bukkit.Location;

public class Arene {
	
	private Location loc1;
	private Location loc2;
	
	public Arene(Location loc1, Location loc2) {
		this.loc1 = loc1;
		this.loc2 = loc2;
	}
	
	public Location getLoc1() {
		return loc1;
	}
	
	public Location getLoc2() {
		return loc2;
	}
	
}
