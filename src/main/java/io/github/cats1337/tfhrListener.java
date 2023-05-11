package io.github.cats1337;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class tfhrListener implements Listener {
  private final tfhrMain plugin;
  
  private static final Pattern HEX_PATTERN = Pattern.compile("&#([0-9a-fA-F]){6}");
  
  public static final String TFHR = "";
  
  private final String EXCLAIM = colorizeHex("&c!");
  
  private final String NOINF = colorizeHex("&cYou cannot have &#F3904FI&#D98554n&#BE7A59f&#A46F5Ei&#8A6462n&#705967i&#554E6Ct&#3B4371y &con your bow");
  
  private final String NO_STRENGTH = colorizeHex("&#E90700S&#EA0B00t&#EC0F00r&#ED1200e&#EF1600n&#F01A00g&#F11E00t&#F32200h &#F42600R&#F52900e&#F72D00d&#F83100u&#FA3500c&#FB3900e&#FC3C00d &#FE4000&#FF44001");
  
  private final String NO_RESIST = colorizeHex("&#828A9BR&#7E8698e&#7A8395s&#777F92i&#737B8Es&#6F788Bt&#6B7488a&#687085n&#646D82c&#60697Fe &#5C657BR&#586278e&#555E75d&#515A72u&#4D576Fc&#49536Ce&#464F68d &#424C65&#3E48621");
  
  public tfhrListener(tfhrMain plugin) {
    this.plugin = plugin;
  }
  
  public static String colorize(String str) {
    return ChatColor.translateAlternateColorCodes('&', str);
  }
  
  public static String colorizeHex(String str) {
    Matcher matcher = HEX_PATTERN.matcher(str);
    while (matcher.find()) {
      String group = matcher.group();
      str = str.replace(group, ChatColor.of(group.substring(1)).toString());
    } 
    return colorize(str);
  }
  
  @EventHandler
  public void onEntityShootBow(EntityShootBowEvent event) {
    if (event.getEntityType() == EntityType.PLAYER) {
      Player player = (Player)event.getEntity();
      ItemStack bow = event.getBow();
      if (bow.containsEnchantment(Enchantment.ARROW_INFINITE)) {
        player.sendMessage("" + this.NOINF + this.EXCLAIM);
        bow.removeEnchantment(Enchantment.ARROW_INFINITE);
      } 
    } 
  }
  
  @EventHandler
  void EntityPotionEffectEvent(EntityPotionEffectEvent event) {
    if (!(event.getEntity() instanceof Player))
      return; 
    Player player = (Player)event.getEntity();
    PotionEffect newEffect = event.getNewEffect();
    if (newEffect != null) {
      if (event.getNewEffect().getType().equals(PotionEffectType.INCREASE_DAMAGE) && event.getNewEffect().getAmplifier() >= 1) {
        event.setCancelled(true);
        int duration = event.getNewEffect().getDuration();
        player.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
        PotionEffect strength1 = new PotionEffect(PotionEffectType.INCREASE_DAMAGE, duration, 0, false, true, true);
        player.addPotionEffect(strength1);
        player.sendMessage("" + this.NO_STRENGTH + this.EXCLAIM);
      } 
      if (event.getNewEffect().getType().equals(PotionEffectType.DAMAGE_RESISTANCE) && event.getNewEffect().getAmplifier() >= 1) {
        event.setCancelled(true);
        int duration = event.getNewEffect().getDuration();
        player.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
        PotionEffect resistance1 = new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, duration, 0, false, true, true);
        player.addPotionEffect(resistance1);
        player.sendMessage("" + this.NO_RESIST + this.EXCLAIM);
      } 
    } 
  }
}
