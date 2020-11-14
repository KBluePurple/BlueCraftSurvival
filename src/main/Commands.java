package main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Commands implements TabCompleter {
    public List<String> onTabComplete (CommandSender sender, Command cmd, String alias, String[] args) {
        List<String> returnValue = new ArrayList<>();
        if (cmd.getName().equalsIgnoreCase("테스트"))
        {
            if (sender instanceof Player)
            {
                Player player = (Player) sender;
                if (args.length == 0)
                {
                    returnValue.add("아이템");
                    return returnValue;
                }
            }
        }
        return null;
    }
}
