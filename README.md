# SpecificWorldLogin
## Bukkit/Spigot plugin for restricting login world!
### Description
With this plugin, the player will be teleported to the specified place on the login to the 
unexpected world.

### How to use
This plugin does not have any commands and all the settings are done in config.yml generated in the plugin folder.

The config file consists of two parameters:
* `permittedWorlds` Names of the worlds to which the user can login.(follow YAML array format)
* `teleportDest` The location where the user will be teleported. World name for `world` and coordinates if required.

The teleport destination will be set to the spawn location of the specified world in case the coodinates are missing.
Note that the plugin stops functioning if none of the `permittedWorlds` exist or the `teleportDest.world` is invalid.

### Build
The latest version is 0.0.1-SNAPSHOT.
[Download .jar file here](https://github.com/Kory33/SpecificWorldLogin/raw/master/target/SpecificWorldLogin-0.0.1-SNAPSHOT.jar, "SpecificWorldLogin.jar")
