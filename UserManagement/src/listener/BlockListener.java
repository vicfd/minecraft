package listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import player.playerManagement;

public class BlockListener implements Listener
{
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event) 
	{
		if (!playerManagement.getInstance().getPlayerStatus(event.getPlayer().getUniqueId())) 
		{
			event.setBuild(false);
			event.setCancelled(true);
		} 
	}

	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) 
	{
		if (!playerManagement.getInstance().getPlayerStatus(event.getPlayer().getUniqueId()))
			event.setCancelled(true); 
	}
}