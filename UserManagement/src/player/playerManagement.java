package player;

import java.util.HashMap;
import java.util.UUID;
import org.bukkit.entity.Player;

public class playerManagement
{
	private static playerManagement instance;
	HashMap<UUID, player> hashPlayer;

	public static synchronized playerManagement getInstance() 
	{
		if (instance == null)
			instance = new playerManagement(); 
		return instance;
	}

	public void load() 
	{
		this.hashPlayer = new HashMap<>();
	}

	public void join(Player p)
	{
		this.hashPlayer.put(p.getUniqueId(), new player(p));
	}

	public void disconnect(UUID p) 
	{
		this.hashPlayer.remove(p);
	}

	public void connect(UUID p) 
	{
		((player)this.hashPlayer.get(p)).connect();
	}

	public boolean getPlayerStatus(UUID p)
	{
		return ((player)this.hashPlayer.get(p)).getOnline();
	}
}