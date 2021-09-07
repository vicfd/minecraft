package player;

import file.Settings;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class player
{
	Player p;
	boolean online;

	public player(Player p) 
	{
		this.p = p;

		if (!Settings.getInstance().isSetServerPassword() && !Settings.getInstance().isSetUserLogin()) 
			this.online = true;
		else
		{
			this.online = false;
			this.p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 9999999, 15));
		} 
	}

	public void connect() 
	{
		this.online = true;
		this.p.removePotionEffect(PotionEffectType.BLINDNESS);
	}

	public boolean getOnline() 
	{
		return this.online;
	}
}