package net.h31ix.assistant;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Logger;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerPreLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.config.Configuration;

public class AssistantPlayerListener extends PlayerListener {
    private Configuration config;
    private final Assistant plugin;
    public final Logger logger = Logger.getLogger("Minecraft");
    
       
    
    public AssistantPlayerListener(Assistant plugin) {
        this.plugin = plugin;
    }
    
            
    @Override
    public void onPlayerQuit(PlayerQuitEvent event) {
                Player player = event.getPlayer();
                String safenick = player.getName().toLowerCase().replaceAll("'", "\"");
                String safenick1 = safenick.replaceAll("§f", "");
                config = plugin.getConfiguration();
                config.load();
                long lStartTime = new Date().getTime(); //start time
                config.setProperty(safenick1, lStartTime);
                config.save();
    }
    
    @Override
    public void onPlayerJoin (PlayerJoinEvent event)
    {
        Player player = event.getPlayer();
        String safenick = player.getName().toLowerCase().replaceAll("'", "\"");
        String safenick1 = safenick.replaceAll("§f", "");
        String safenick2 = (safenick1+"+");
        config = plugin.getConfiguration();
        String seen = config.getString(safenick1); 
        String seen2 = config.getString(safenick2);
        if(seen == null || seen2 == null)
        {
            long start1 = new Date().getTime(); //start time
            config.load();
            config.setProperty(safenick2, start1);
            config.save();
        }
    }
}
