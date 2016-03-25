package com.github.kory33.specificworldlogin;

import org.bukkit.event.Listener;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class LoginListener implements Listener {
	private SpecificWorldLogin plugin;
	private DataHandling d;

    public LoginListener(SpecificWorldLogin plugin, DataHandling d) {
    	//register events
    	plugin.getServer().getPluginManager().registerEvents(this, plugin);
        this.plugin = plugin;
        this.d = d;
    }

    //processed on player's login event
    @EventHandler
    public void loginProcession(PlayerLoginEvent e) {
    	//Process in the second tick from the join event
    	//to obtain the login world correctly
    	new BukkitRunnable() {
    		@Override
    		public void run(){
    			if(d.isFunctioning){
    				Player p = e.getPlayer();
    				World loginWorld = p.getWorld();
					plugin.getServer().getLogger().info("Player " + p.getName() + " has logged into " + loginWorld.getName());

    				//if the player's login world is not permitted
    				if(!d.isPermitted(loginWorld)){
    					plugin.getServer().getLogger().info("Not permitted to login into " + loginWorld.getName() + ".");
    					
    					Location dest = d.getTpDest();
    					plugin.getServer().getLogger().info("Teleporting " + p.getName() + " to " + dest.getWorld().getName());
    					
    					p.teleport(dest);
    				}
    			}
         	}
    	}.runTaskLater(plugin, 1);
    }
}

