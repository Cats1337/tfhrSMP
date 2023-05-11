package io.github.cats1337;

import java.util.Arrays;
import java.util.Random;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class tfhrCommands implements CommandExecutor {
  private tfhrMain plugin;
  
  private final PotionEffectType[] types;
  
  private final String[] effects;
  
  public tfhrCommands(tfhrMain plugin) {
    this
      
      .types = new PotionEffectType[] { 
        PotionEffectType.ABSORPTION, PotionEffectType.BAD_OMEN, PotionEffectType.BLINDNESS, PotionEffectType.DAMAGE_RESISTANCE, PotionEffectType.FAST_DIGGING, PotionEffectType.GLOWING, PotionEffectType.HEALTH_BOOST, PotionEffectType.HUNGER, PotionEffectType.INCREASE_DAMAGE, PotionEffectType.LEVITATION, 
        PotionEffectType.LUCK, PotionEffectType.NIGHT_VISION, PotionEffectType.REGENERATION, PotionEffectType.SATURATION, PotionEffectType.SLOW, PotionEffectType.SLOW_DIGGING, PotionEffectType.SLOW_FALLING, PotionEffectType.SPEED, PotionEffectType.WEAKNESS };
    this
      
      .effects = new String[] { 
        "Absorption", "Bad Omen", "Blindness", "Resistance", "Haste", "Glowing", "Health Boost", "Hunger", "Strength", "Levitation", 
        "Luck", "Night Vision", "Regeneration", "Saturation", "Slowness", "Mining Fatigue", "Slow Falling", "Speed", "Weakness" };
    this.plugin = plugin;
  }
  
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (command.getName().equalsIgnoreCase("rpot"))
      if (!(sender instanceof Player) || ((Player)sender).hasPermission("tfhrSMP.rpot")) {
        if (args.length == 1) {
          Player target = this.plugin.getServer().getPlayer(args[0]);
          if (target != null) {
            int lvl = (int)(Math.random() * 3.0D) + 1;
            PotionEffectType type = this.types[(int)(Math.random() * this.types.length)];
            Random random = new Random();
            int ttime = random.nextInt(601) + 600;
            if (type == PotionEffectType.POISON)
              lvl = 1; 
            target.addPotionEffect(new PotionEffect(type, ttime, lvl));
            int pos = Arrays.<PotionEffectType>asList(this.types).indexOf(type);
            String effect = this.effects[pos];
            if (type == PotionEffectType.BAD_OMEN || type == PotionEffectType.BLINDNESS || type == PotionEffectType.GLOWING || type == PotionEffectType.HUNGER || type == PotionEffectType.LEVITATION || type == PotionEffectType.SLOW || type == PotionEffectType.SLOW_DIGGING || type == PotionEffectType.WEAKNESS) {
              target.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&7You got unlucky and got &c" + effect + "&7!"));
            } else if (type == PotionEffectType.ABSORPTION || type == PotionEffectType.DAMAGE_RESISTANCE || type == PotionEffectType.FAST_DIGGING || type == PotionEffectType.HEALTH_BOOST || type == PotionEffectType.INCREASE_DAMAGE || type == PotionEffectType.LUCK || type == PotionEffectType.NIGHT_VISION || type == PotionEffectType.REGENERATION || type == PotionEffectType.SATURATION || type == PotionEffectType.SLOW_FALLING || type == PotionEffectType.SPEED) {
              target.sendMessage(ChatColor.translateAlternateColorCodes('&', "&2&7You got lucky and got &2" + effect + "&7!"));
            } else {
              target.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e&7You somehow got &e" + effect + "&7. &8Tell Cats please."));
            } 
          } else {
            sender.sendMessage(ChatColor.RED + "Player not found!");
          } 
        } else {
          sender.sendMessage(ChatColor.RED + "Invalid arguments!");
        } 
      } else {
        sender.sendMessage(ChatColor.RED + "You do not have permission to use this command!");
      }  
    if (command.getName().equalsIgnoreCase("rpotall"))
      if (!(sender instanceof Player) || ((Player)sender).hasPermission("tfhrSMP.rpotall")) {
        for (Player target : this.plugin.getServer().getOnlinePlayers()) {
          int lvl = (int)(Math.random() * 3.0D) + 1;
          PotionEffectType type = this.types[(int)(Math.random() * this.types.length)];
          Random random = new Random();
          int ttime = random.nextInt(601) + 600;
          if (type == PotionEffectType.POISON || type == PotionEffectType.INCREASE_DAMAGE || type == PotionEffectType.DAMAGE_RESISTANCE)
            lvl = 1; 
          target.addPotionEffect(new PotionEffect(type, ttime, lvl));
          int pos = Arrays.<PotionEffectType>asList(this.types).indexOf(type);
          String effect = this.effects[pos];
          if (type == PotionEffectType.BAD_OMEN || type == PotionEffectType.BLINDNESS || type == PotionEffectType.GLOWING || type == PotionEffectType.HUNGER || type == PotionEffectType.LEVITATION || type == PotionEffectType.SLOW || type == PotionEffectType.SLOW_DIGGING || type == PotionEffectType.WEAKNESS) {
            target.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&7You got unlucky and got &c" + effect + "&7!"));
            continue;
          } 
          if (type == PotionEffectType.ABSORPTION || type == PotionEffectType.DAMAGE_RESISTANCE || type == PotionEffectType.FAST_DIGGING || type == PotionEffectType.HEALTH_BOOST || type == PotionEffectType.INCREASE_DAMAGE || type == PotionEffectType.LUCK || type == PotionEffectType.NIGHT_VISION || type == PotionEffectType.REGENERATION || type == PotionEffectType.SATURATION || type == PotionEffectType.SLOW_FALLING || type == PotionEffectType.SPEED) {
            target.sendMessage(ChatColor.translateAlternateColorCodes('&', "&2&7You got lucky and got &2" + effect + "&7!"));
            continue;
          } 
          target.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e&7You somehow got &e" + type.getName() + "&7. &8Tell Cats please."));
        } 
      } else {
        sender.sendMessage(ChatColor.RED + "You do not have permission to use this command!");
      }  
    return true;
  }
}
