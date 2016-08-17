package com.github.kory33.specificworldlogin;

import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.Location;

import java.io.File;
import java.util.ArrayList;


public class DataHandling {
    private final SpecificWorldLogin plugin;
    private ArrayList<World> permittedLoginWorld;
    private Location teleportLocation;

    //when this variable is false, this plugin does not attempt to teleport players
    public boolean isFunctioning;
	
    public DataHandling(SpecificWorldLogin plugin) {
        this.plugin = plugin;

        if(!(new File("config.yml")).exists()){
        	plugin.saveResource("config.yml", false);
        }
        
        //read all the required data
        this.readPermittedLoginWorld();
    }

    private void readPermittedLoginWorld(){
    	YamlConfiguration ymlData = YamlConfiguration.loadConfiguration(new File(this.plugin.getDataFolder(), "config.yml"));
    	
    	this.isFunctioning = true;
    	
    	//for the each world under permittedWorldName section
    	permittedLoginWorld = new ArrayList<World>();
    	for(String worldName: ymlData.getStringList("permittedWorlds")){
    		World w = plugin.getServer().getWorld(worldName);

    		//in case the world is not found
    		if(w == null){
    			plugin.getServer().getLogger().warning("World " + worldName + " is not found! Check the configuartion file.");
    		}else{
    			//otherwise add to the list
    			permittedLoginWorld.add(w);
    		}
    	}
    	if(permittedLoginWorld.isEmpty()){
    		this.isFunctioning = false;
    	}

    	//get the teleportations destination
    	String tpWorldName = ymlData.getString("teleportDest.world");
    	this.teleportLocation = null;
    	
    	
    	if(tpWorldName != null){
    		World tpWorld = plugin.getServer().getWorld(tpWorldName);

    		if(tpWorld == null){
    			this.isFunctioning = false;
    			plugin.getServer().getLogger().warning("Teleport destination is not found! Check the configuraation file.");
    		}else{
    			//by default teleport to the spawn locaiton
    			Location tpDest = tpWorld.getSpawnLocation();

    			//if the location is defined
    			if(ymlData.contains("teleportDest.x") && ymlData.contains("teleportDest.y") && ymlData.contains("teleportDest.z")){
        			//get coordinates
        			double x, y, z;
        			x = ymlData.getDouble("teleportDest.x");
        			y = ymlData.getDouble("teleportDest.y");
        			z = ymlData.getDouble("teleportDest.z");    
        			
        			tpDest = new Location(tpWorld, x, y, z);
    			}else{
        			plugin.getServer().getLogger().warning("Teleport coordinates not found. setting them to the spawn location...");    				
    			}
    			
    			this.teleportLocation = tpDest;
       		}
    	}
    	if(this.isFunctioning == false){
    		plugin.getServer().getLogger().warning("The settings are invalid. Plugin no longer works!");
    	}
    }
    
    public boolean isPermitted(World w){
    	return this.permittedLoginWorld.contains(w);
    }
    
    public Location getTpDest(){
    	return this.teleportLocation;
    }
}
