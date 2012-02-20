package net.h31ix.assistant;

/*
 * Seen for Bukkit
 * 
 * @author H31IX
 * 
 * H31IX.NET
 */

import java.io.File;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import java.util.Date;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Assistant extends JavaPlugin {
    private FileConfiguration config;
    public File configFile = new File("plugins/Seen/config.yml");
    public static Assistant plugin;
    
    
    @Override
    public void onDisable() {
        // TODO: Place any custom disable code here.
        System.out.println(this + " is now disabled!");
    }
    

    @Override
    public void onEnable() {
    PluginManager pluginManager = getServer().getPluginManager();
    pluginManager.registerEvents(new AssistantPlayerListener(this), this);
    new File("plugins/Seen").mkdir();
		if(!configFile.exists()) {
                    saveDefaultConfig();
		}
	    config = getConfig(); 
            getCommand("seen").setExecutor(new CommandExecutor() {
            public boolean onCommand(CommandSender cs, Command cmnd, String alias, String[] args) {
                Player player = (Player)cs;
                String safenick = player.getName().toLowerCase().replaceAll("'", "\"");
                String safenick1 = safenick.replaceAll("§f", "");
                if (args.length > 0) {
                String cel = (args[0].toLowerCase());
                String seen = config.getString(cel);
                String first = config.getString(cel+"+");
                if (seen != null)
                {
                Player target = Bukkit.getServer().getPlayer(args[0]);
                long lStartTime = Long.parseLong(seen);
                long lEndTime = new Date().getTime(); //end time
                long difference = lEndTime - lStartTime; //check different
                long finaltime = difference/(60*1000);
                String operation = "minute(s)";
                if (finaltime > 60)
                {
                    finaltime = finaltime/60;
                    operation = "hour(s)";
                if (finaltime > 24)
                {
                    finaltime = finaltime/24;
                    operation = "day(s)";
                }
                }
                
                if (first != null)
                {      
                long lStartTime1 = Long.parseLong(first);
                long lEndTime1 = new Date().getTime(); //end time
                long difference1 = lEndTime1 - lStartTime1; //check different
                long finaltime1 = difference1/(60*1000);
                String operation2 = "minute(s)";
                if (finaltime1 > 60)
                {
                    finaltime1 = finaltime1/60;
                    operation2 = "hour(s)";
                if (finaltime1 > 24)
                {
                    finaltime1 = finaltime1/24;
                    operation2 = "day(s)";
                }
                }
                
                
                if (cel.equalsIgnoreCase(safenick1)){
                    player.sendMessage(ChatColor.RED + "Still trying to find yourself, bud?");
                }
                else {
                List list = getServer().matchPlayer(cel);
                if(list.size() == 1){
                    player.sendMessage(ChatColor.GREEN + cel+" is online right now! Say hey!");
                }
                else {
                if (seen==null) {
                    player.sendMessage(ChatColor.RED + "That player has never been here before.");
                }
                else {    
                player.sendMessage(ChatColor.RED + cel+" was last seen on this server "+finaltime+" "+operation+" ago.");
                player.sendMessage(ChatColor.RED + cel+"'s first logon was "+finaltime1+" "+operation2+" ago.");
                }
                }
                }
                } else { player.sendMessage(ChatColor.RED + "That player has never been here before."); }
                } else { player.sendMessage(ChatColor.RED + "That player has never been here before."); }
                }else player.sendMessage(ChatColor.RED + "Usage: /seen <username>");
                return true;
            }
           });
            getCommand("playtime").setExecutor(new CommandExecutor() {
            public boolean onCommand(CommandSender cs, Command cmnd, String alias, String[] args) {
                Player player = (Player)cs;
                String safenick = player.getName().toLowerCase().replaceAll("'", "\"");
                String safenick1 = safenick.replaceAll("§f", "");
                if (args.length < 1) {
                String first = config.getString(safenick1+"+");
                  if (first != null)
                {      
                long lStartTime1 = Long.parseLong(first);
                long lEndTime1 = new Date().getTime(); //end time
                long difference1 = lEndTime1 - lStartTime1; //check different
                long finaltime1 = difference1/(60*1000);
                String operation2 = "minute(s)";
                if (finaltime1 > 60)
                {
                    finaltime1 = finaltime1/60;
                    operation2 = "hour(s)";
                if (finaltime1 > 24)
                {
                    finaltime1 = finaltime1/24;
                    operation2 = "day(s)";
                }
                }
                player.sendMessage(ChatColor.RED + "Your first login was "+finaltime1+" "+operation2+" ago.");
                }
            }else player.sendMessage(ChatColor.RED + "Usage: /playtime");
                return true;
            }
           });
        System.out.println(this + " is now enabled."); 
    } 
}    

