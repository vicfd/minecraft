package plugin;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class rimtu implements org.bukkit.event.Listener
{
    @EventHandler
    public void asd(PlayerJoinEvent event)
    {
        Bukkit.broadcastMessage("Welcome to the server!");
    }
    
    @EventHandler
    public void BlockBreakEvent​(BlockBreakEvent event)
    {
    	Bukkit.getConsoleSender().sendMessage("Roto");
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event)
    {
    	Bukkit.getConsoleSender().sendMessage("Alguien hablo");
    }
    
    @EventHandler
    public void inventoryEvent(InventoryClickEvent event)
    {
    	if(event.getHotbarButton() == 0 && event.getClickedInventory().getType().toString().equals("CHEST")) 
    	{
        	Player p = (Player) event.getWhoClicked();
        	SortInventory sc = new SortInventory(event.getClickedInventory());
        	sc.sort();
        	p.closeInventory();
        	p.openInventory(event.getClickedInventory());
    	}
    }
    
    @EventHandler
    public void onChest(PlayerInteractEvent event) 
    {
    	/*
        Player p = event.getPlayer();
        Block b = event.getClickedBlock();
        
        if(event.getAction().equals(Action.RIGHT_CLICK_BLOCK) && p.isSneaking()) 
        {
            if(b.getType() == Material.CHEST)
            {
            	SortChest sc = new SortChest((Chest) b.getState());
            	sc.sort();
            }
        }
        */
    }
}