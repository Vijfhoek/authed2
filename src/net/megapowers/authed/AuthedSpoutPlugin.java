package net.megapowers.authed;

import java.util.HashMap;
import java.util.logging.Logger;

import org.bukkit.Location;
import org.bukkit.event.Event;
import org.bukkit.event.Event.Priority;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginManager;
import org.getspout.spoutapi.plugin.SpoutPlugin;

public class AuthedSpoutPlugin extends SpoutPlugin {
	Logger logger = null;
	PluginManager pluginManager = null;
	
	AuthedPlayerListener playerListener = null;
	
	public HashMap<String, HashMap<Integer, ItemStack>> playerInventories = null;
	public HashMap<String, Integer> playerLevel = null;
	public HashMap<String, Float> playerXP = null;
	public HashMap<String, Location> playerLocations = null;
	
	@Override
	public void onDisable() {
		pluginManager = null;
		
		logger.info("Version " + getVersion() + " disabled.");
		logger = null;
	}

	@Override
	public void onEnable() {
		logger = getLogger();
		
		pluginManager = getServer().getPluginManager();
		playerListener = new AuthedPlayerListener(this);
		
		logger.info("Version " + getVersion() + " enabled.");
	}
	
	public void registerEvents() {
		if (pluginManager == null) return;
		if (playerListener == null) return;
		
		pluginManager.registerEvent(Event.Type.PLAYER_JOIN, playerListener, Priority.High, this);
	}
}
