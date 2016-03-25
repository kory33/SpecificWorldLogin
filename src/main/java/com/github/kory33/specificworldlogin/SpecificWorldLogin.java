package com.github.kory33.specificworldlogin;

import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

public class SpecificWorldLogin extends JavaPlugin { 
	private DataHandling d;;
	
	@Override
    public void onEnable() {
		//process data handler
		this.d = new DataHandling(this);
		
		//process event handler
        getLogger().info("Resistering login event handler...");
        new LoginListener(this, d);
	}

    @Override
    public void onDisable() {
    	getLogger().info("Unregistering handlers");
    	HandlerList.unregisterAll(this);

    	getLogger().info("Successfully unregistered handlers");
    }
}

