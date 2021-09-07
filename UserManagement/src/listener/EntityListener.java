package listener;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityTargetEvent;
import player.playerManagement;

public class EntityListener implements Listener
{
	@EventHandler
	public void onEntityTarget(EntityTargetEvent event) 
	{
		if (event.getTarget() != null && event.getTarget().getType() == EntityType.PLAYER && !playerManagement.getInstance().getPlayerStatus(event.getTarget().getUniqueId()))
			event.setCancelled(true);
	}

	@EventHandler
	public void onEntityDamageByEntity(EntityDamageByEntityEvent event)
	{
		if (event.getDamager().getType() == EntityType.PLAYER && !playerManagement.getInstance().getPlayerStatus(event.getDamager().getUniqueId())) 
			event.setCancelled(true);
	}

	@EventHandler
	public void onEntityDamage(EntityDamageEvent event) 
	{
		if (event.getEntity().getType() == EntityType.PLAYER) 
		{
			Player player = (Player)event.getEntity();

			if (!playerManagement.getInstance().getPlayerStatus(player.getUniqueId())) 
			{
				player.setFoodLevel(20);
				player.setFireTicks(0);
				player.setRemainingAir(player.getMaximumAir());
				event.setCancelled(true);
			} 
		}
	}

	@EventHandler
	public void onEntityDeath(EntityDeathEvent event) 
	{
		if (event.getEntity().getType() == EntityType.PLAYER)
		{
			if (!playerManagement.getInstance().getPlayerStatus(event.getEntity().getUniqueId())) 
			{
				event.setDroppedExp(0);
				event.getDrops().clear();
			} 
		}
	}
}