package net.h31ix.assistant;

import java.io.IOException;
import java.util.Date;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class AssistantPlayerListener implements Listener {
    private FileConfiguration config;
    private final Assistant plugin;
    
       
    
    public AssistantPlayerListener(Assistant plugin) {
        this.plugin = plugin;
    }
    
            
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
                Player player = event.getPlayer();
                String safenick = player.getName().toLowerCase().replaceAll("'", "\"");
                String safenick1 = safenick.replaceAll("§f", "");
                config = plugin.getConfig();
                long lStartTime = new Date().getTime(); //start time
                config.set(safenick1, lStartTime);
    }
    
    @EventHandler
    public void onPlayerJoin (PlayerJoinEvent event)
    {
        Player player = event.getPlayer();
        String safenick = player.getName().toLowerCase().replaceAll("'", "\"");
        String safenick1 = safenick.replaceAll("§f", "");
        String safenick2 = (safenick1+"+");
        config = plugin.getConfig();
        String seen = config.getString(safenick1); 
        String seen2 = config.getString(safenick2);
        if(seen == null || seen2 == null)
        {
            long start1 = new Date().getTime(); //start time
            config.set(safenick2, start1);
        }
    }
}
