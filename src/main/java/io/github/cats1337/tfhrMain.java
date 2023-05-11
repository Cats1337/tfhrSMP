package io.github.cats1337;

import java.util.logging.Logger;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class tfhrMain extends JavaPlugin implements Listener {
  static final Logger LOGGER = Logger.getLogger("24hrSMP");
  
  public void onEnable() {
    LOGGER.info("24hrSMP is enabled!");
    getServer().getPluginManager().registerEvents(this, (Plugin)this);
    getServer().getPluginManager().registerEvents(new tfhrListener(this), (Plugin)this);
    getCommand("rpot").setExecutor(new tfhrCommands(this));
    getCommand("rpotall").setExecutor(new tfhrCommands(this));
  }
  
  public void onDisable() {
    LOGGER.info("24hrSMP is disabled!");
  }
}
