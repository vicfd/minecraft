package listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import player.playerManagement;

public class PlayerListener implements Listener 
{
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) 
	{
		playerManagement.getInstance().join(event.getPlayer());
	}

	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) 
	{
		playerManagement.getInstance().disconnect(event.getPlayer().getUniqueId());
	}


	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event) 
	{
		if (!playerManagement.getInstance().getPlayerStatus(event.getPlayer().getUniqueId()))
			event.setCancelled(true); 
	}
}