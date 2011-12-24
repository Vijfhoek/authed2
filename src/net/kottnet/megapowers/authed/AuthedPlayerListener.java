package net.kottnet.megapowers.authed;

import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class AuthedPlayerListener extends PlayerListener {
	AuthedSpoutPlugin plugin = null;
	public AuthedPlayerListener(AuthedSpoutPlugin pl) {
		plugin = pl;
	}
	
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		
		// Put the inventory away
		PlayerInventory inv = player.getInventory();
		HashMap<Integer, ItemStack> items = new HashMap<Integer, ItemStack>();
		
		for (int i = 0; i < 36; ++i) {
			ItemStack item = inv.getItem(i);
			if (item.getTypeId() == 0) continue;
			if (item.getAmount() == 0) continue;
			
			items.put(i, item);
		}
		
		ItemStack helmet = inv.getHelmet();
		if (helmet.getTypeId() != 0) items.put(103, helmet);
		
		ItemStack chestplate = inv.getChestplate();
		if (chestplate.getTypeId() != 0) items.put(102, chestplate);
		
		ItemStack leggings = inv.getLeggings();
		if (leggings.getTypeId() != 0) items.put(101, leggings);
		
		ItemStack boots = inv.getBoots();
		if (boots.getTypeId() != 0) items.put(100, boots);
		
		plugin.playerInventories.put(player.getName(), items);
		
		// Store the player's location
		plugin.playerLocations.put(player.getName(), player.getLocation());
		
		// Store the player's level and XP
		plugin.playerLevel.put(player.getName(), player.getLevel());
		plugin.playerXP.put(player.getName(), player.getExp());
		
		
		// Clear the player's inventory
		inv.clear();
		
		// Teleport the player to spawn
		player.teleport(player.getWorld().getSpawnLocation());
		
		// Clear the player's level and XP
		player.setLevel(0);
		player.setExp(0.0F);
	}
}
