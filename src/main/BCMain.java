package main;

import com.sun.xml.internal.ws.client.AsyncInvoker;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_16_R2.entity.CraftItem;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BCMain extends JavaPlugin implements Listener{
    SItems items = new SItems();
    public void print(String msg)
    {
        getLogger().info(msg);
    }

    public boolean EqualsItem (ItemStack item1, ItemStack item2){
        if (item1.equals(item2))
            return true;
        else
            return false;
    }

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        this.getCommand("테스트").setTabCompleter(new Commands());

        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                for (Entity entity: Bukkit.getServer().getWorld("world").getEntities()){
                    if (entity.getCustomName() == null)
                        continue;
                    if (entity.getCustomName().equalsIgnoreCase("단검")) {
                        Particle.DustOptions dustOptions = new Particle.DustOptions(Color.RED, 1);
                        for (Location location: getCircle(entity.getLocation(), 1.75, 50)) {
                            entity.getLocation().getWorld().spawnParticle(Particle.REDSTONE, location.add(0,0.1,0), 1, 0.0, 0.0, 0.0, 0, dustOptions);
                        }
                        entity.getLocation().getWorld().spawnParticle(Particle.REDSTONE, entity.getLocation(), 10, 0.0, 0.5, 0.0, 0, dustOptions);
                    }
                    else if (entity.getCustomName().startsWith("영혼")) {
                        Particle.DustOptions dustOptions = new Particle.DustOptions(Color.fromRGB(171, 179, 255), 1);
                        for (Location location: getCircle(entity.getLocation(), 5, 50)) {
                            entity.getLocation().getWorld().spawnParticle(Particle.REDSTONE, location.add(0,0.1,0), 1, 0.0, 0.0, 0.0, 0, dustOptions);
                        }
                        entity.getLocation().getWorld().spawnParticle(Particle.SOUL, entity.getLocation().add(0,0.5,0), 1, 0.0, 0.0, 0.0, 0);
                    }
                    else if (entity.getCustomName().endsWith("님의 언데드")){
                        int i = 0;
                        for(Entity e: entity.getNearbyEntities(20,20,20)){
                            if (e instanceof Player)
                            {
                                Player player = (Player)e;
                                if (player.getName().equalsIgnoreCase(entity.getCustomName().replace("님의 언데드","")))
                                {
                                    i++;
                                }
                            }
                        }
                        if (i==0){
                            entity.teleport(Bukkit.getPlayer(entity.getCustomName().replace("님의 언데드","")));
                        }
                    }
                }
            }
        }, 0L, 4L);

        print("플러그인 활성화 완료.");
    }

    @Override
    public void onDisable()
    {
        print("플러그인 비활성화 완료");
    }


    int task1 = -1;
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("테스트")) {
            if (args[0].equalsIgnoreCase("아이템")) {
                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    Inventory inventory = Bukkit.createInventory(null, 9 * 6, ChatColor.GRAY + "특수 아이템 리스트");
                    inventory.addItem(items.검은_오러의_검());
                    inventory.addItem(items.붉은_빛의_검());
                    inventory.addItem(items.신성한_왕의_검());
                    inventory.addItem(items.푸른_빛의_검());
                    inventory.addItem(items.피해망상증());
                    inventory.addItem(items.황금_빛의_검());
                    inventory.addItem(items.소드_마스터의_검());
                    player.openInventory(inventory);
                }
                else
                {
                    sender.sendMessage("이 명령어는 플래이어만 입력할 수 있습니다.");
                }
            }
            else if (args[0].equalsIgnoreCase("원")){
                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    player.sendMessage("실행");
                    if (task1 != -1)
                        Bukkit.getScheduler().cancelTask(task1);
                    task1 = Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
                        @Override
                        public void run() {
                                for (Location location : getCircle(player.getLocation(), Double.valueOf(args[2]), Integer.parseInt(args[3]))) {
                                    player.getWorld().spawnParticle(Particle.valueOf(args[1]), location, 0);
                                    this.notifyAll();
                                }
                        }
                    }, 0L, 1L);
                }
                else
                {
                    sender.sendMessage("이 명령어는 플래이어만 입력할 수 있습니다.");
                }
            }
            else if (args[0].equalsIgnoreCase("FALL")){
                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    player.teleport(player.getLocation().add(0,100,0));
                }
                else
                {
                    sender.sendMessage("이 명령어는 플래이어만 입력할 수 있습니다.");
                }
            }
            else if (args[0].equalsIgnoreCase("단검")){
                if (sender instanceof  Player)
                {
                    Player player = (Player) sender;
                    ArmorStand entity = (ArmorStand) player.getWorld().spawnEntity(player.getLocation().subtract(0, 1, 0), EntityType.ARMOR_STAND);
                    entity.setVisible(false);
                    entity.setRemoveWhenFarAway(false);
                    entity.setMarker(false);
                    entity.setCustomName("단검");
                    entity.setCustomNameVisible(false);
                    entity.setSmall(true);
                }
            }

            return true;
        }
        return false;
    }

    Map<String, Integer> SaveCoolDown_피해망상 = new HashMap<String, Integer>();

    public ArrayList<Location> getCircle(Location center, double radius, int amount)
    {
        World world = center.getWorld();
        double increment = (2 * Math.PI) / amount;
        ArrayList<Location> locations = new ArrayList<Location>();
        for(int i = 0;i < amount; i++)
        {
            double angle = i * increment;
            double x = center.getX() + (radius * Math.cos(angle));
            double z = center.getZ() + (radius * Math.sin(angle));
            locations.add(new Location(world, x, center.getY(), z));
        }
        return locations;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        event.setJoinMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "+" + ChatColor.GRAY + "] " + event.getPlayer().getName() + "님이 서버에 접속하였습니다.");
        if (SaveCoolDown_피해망상.get(event.getPlayer().getUniqueId().toString()) != null)
            event.getPlayer().setCooldown(Material.BLACK_DYE, SaveCoolDown_피해망상.get(event.getPlayer().getUniqueId().toString()));
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        event.setQuitMessage(ChatColor.GRAY + "[" + ChatColor.RED + "+" + ChatColor.GRAY + "] " + event.getPlayer().getName() + "님이 서버를 떠났습니다.");
        SaveCoolDown_피해망상.put(event.getPlayer().getUniqueId().toString(), event.getPlayer().getCooldown(Material.BLACK_DYE));
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK) || event.getAction().equals(Action.RIGHT_CLICK_AIR)) {
            if(player.isSneaking()){
                for(Entity entity: player.getNearbyEntities(5,5,5)){
                    if(entity.getCustomName() == null)
                        continue;
                    if(entity.getCustomName().startsWith("영혼")){
                        try {
                            String soulUUID = entity.getCustomName().replace("영혼:", "");
                            entity.remove();
                            Entity e = player.getWorld().spawnEntity(entity.getLocation(), SoulType.get(soulUUID));
                            e.setCustomNameVisible(true);
                            e.setCustomName(player.getName() + "님의 언데드");
                            EntityAddTeam(e, player);
                            SoulType.remove(soulUUID);
                        }
                        catch (Exception e)
                        {

                        }
                        return;
                    }
                }
                return;
            }

            if (!event.getPlayer().getInventory().getItemInMainHand().hasItemMeta() || !event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasCustomModelData()) {
                return;
            }

            //피해망상증
            if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == items.피해망상증().getItemMeta().getCustomModelData()) {
                event.setCancelled(true);
                if (player.getCooldown(Material.BLACK_DYE) > 0) {
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(
                            ChatColor.DARK_GRAY + "피해망상증" + ChatColor.GRAY + "의 쿨타임이 " + String.format("%.1f", (double) player.getCooldown(Material.BLACK_DYE) / 20D) + "초 남았습니다."
                    ));
                } else {
                    player.setCooldown(Material.BLACK_DYE, 600);
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100, 5, false, true, false));

                    for (Entity entity : player.getNearbyEntities(10, 10, 10)) {
                        if (entity.getType().equals(EntityType.PLAYER)) {
                            Player p = (Player) entity;
                            if (p.equals(player)) continue;
                            p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 100, 1, false, false, false));
                            p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.WHITE + player.getName() + ChatColor.GRAY + "로 인해 갑자기 모든 것이 무서워진다."));
                        }
                    }
                }
            }
            //신성한 왕의 검
            if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == items.소드_마스터의_검().getItemMeta().getCustomModelData()) {
                event.setCancelled(true);
                if (player.getCooldown(Material.IRON_SWORD) > 0) {
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(
                            ChatColor.DARK_GRAY + "일섬" + ChatColor.GRAY + "의 쿨타임이 " + String.format("%.1f", (double) player.getCooldown(Material.IRON_SWORD) / 20D) + "초 남았습니다."
                    ));
                } else {
                    player.setCooldown(Material.IRON_SWORD, 60);
                    Location playerLocation = player.getLocation();
                    int i = 1;
                    Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
                        @Override
                        public void run() {
                            for (Location location : getCircle(playerLocation, 5, 100)) {
                                playerLocation.getWorld().spawnParticle(Particle.SWEEP_ATTACK, location.add(0, 0.5, 0), 1, 0.0, 0.0, 0.0, 0);
                                playerLocation.getWorld().playSound(playerLocation, Sound.BLOCK_IRON_TRAPDOOR_OPEN, 2, 1);
                            }
                        }
                    }, 20);
                    for (Entity entity : playerLocation.getWorld().getNearbyEntities(playerLocation, 5, 5, 5)) {
                        if (entity.getType().equals(EntityType.PLAYER))
                            continue;
                        if (entity instanceof CraftItem)
                            continue;
                        if (entity instanceof LivingEntity)
                            ((LivingEntity) entity).setAI(false);
                        Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
                            @Override
                            public void run() {
                                entity.getWorld().spawnParticle(Particle.FIREWORKS_SPARK, entity.getLocation().add(0, 1, 0), 100, 0.5, 0.5, 0.5, 0.2);
                                entity.getWorld().playSound(entity.getLocation(), Sound.BLOCK_CHAIN_BREAK, 1, 1);
                                entity.remove();
                            }
                        }, 40 + i*1);
                        i++;
                    }
                }
            }
        }
    }

    Map<String, String> EntityTeam = new HashMap<String, String>();
    @EventHandler
    public void onEntityTarget(EntityTargetEvent event) {
        if (event.getTarget() instanceof Player){
            Player player = (Player)event.getTarget();
            String entityUUID = event.getEntity().getUniqueId().toString();
            if (EntityTeam.get(entityUUID) != null){
                if (EntityTeam.get(entityUUID).equalsIgnoreCase(player.getUniqueId().toString())) {
                    event.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (player.getGameMode().equals(GameMode.SPECTATOR))
            return;
        for (Entity entity: player.getNearbyEntities(1.75, 0.5, 1.75)){
            if (entity.getCustomName() == null)
                continue;
            if (entity.getCustomName().equalsIgnoreCase("단검"))
            {
                Particle.DustOptions dustOptions = new Particle.DustOptions(Color.RED, 2);
                List<Location> locations = getCircle(player.getLocation(), 1.75, 100);
                List<Location> locations2 = getCircle(player.getLocation(), 1.25, 100);
                List<Location> locations3 = getCircle(player.getLocation(), 0.75, 100);
                for (int i = 0; i < (locations.size()/2); i += 8) {
                    int o = i;

                    Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
                        @Override
                        public void run() {
                            for (int v = 0; v <= 7; v++) {

                                int amount = 1;
                                double xx = 0;
                                double yy = 0;
                                double zz = 0;
                                double speed = 0;
                                Vector vector = new Vector(0,0.25,0);

                                player.getLocation().getWorld().spawnParticle(Particle.REDSTONE, locations.get(o+v).add(vector), amount, xx, yy, zz, speed, dustOptions);
                                player.getLocation().getWorld().spawnParticle(Particle.REDSTONE, locations.get(o+v + (locations.size() / 2)).add(vector), amount, xx, yy, zz, speed, dustOptions);

                                player.getLocation().getWorld().spawnParticle(Particle.REDSTONE, locations2.get(o).add(vector), amount, xx, yy, zz, speed, dustOptions);
                                player.getLocation().getWorld().spawnParticle(Particle.REDSTONE, locations2.get(o+v + (locations.size() / 2)).add(vector), amount, xx, yy, zz, speed, dustOptions);

                                player.getLocation().getWorld().spawnParticle(Particle.REDSTONE, locations3.get(o).add(vector), amount, xx, yy, zz, speed, dustOptions);
                                player.getLocation().getWorld().spawnParticle(Particle.REDSTONE, locations3.get(o+v + (locations.size() / 2)).add(vector), amount, xx, yy, zz, speed, dustOptions);
                            }
                        }
                    }, 1*i/8);
                }
                entity.remove();
            }
        }
    }

    Map<String, EntityType> SoulType = new HashMap<String, EntityType>();

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event){
        if(!event.getEntity().getType().equals(EntityType.PLAYER)){
            if (event.getEntity().getCustomName() == null) {
                ArmorStand entity = (ArmorStand) event.getEntity().getWorld().spawnEntity(event.getEntity().getLocation().subtract(0, 0, 0), EntityType.ARMOR_STAND);
                entity.setVisible(false);
                entity.setRemoveWhenFarAway(false);
                entity.setMarker(false);

                int randomUUID = (int) (Math.random() * 100000000);
                entity.setCustomName("영혼:" + randomUUID);

                SoulType.put(String.valueOf(randomUUID), event.getEntity().getType());

                entity.setCustomNameVisible(false);
                entity.setSmall(true);

                Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
                    @Override
                    public void run() {
                        try {
                            SoulType.remove(String.valueOf(randomUUID));
                        } catch (Exception e) {

                        }
                        entity.remove();
                    }
                }, 200);
            }
        }
    }

    public void EntityAddTeam(Entity entity, Player player){
        EntityTeam.put(entity.getUniqueId().toString(), player.getUniqueId().toString());
    }
}