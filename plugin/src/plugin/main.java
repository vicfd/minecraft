package plugin;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class main extends JavaPlugin 
{
	public void onEnable() 
	{
		Bukkit.getConsoleSender().sendMessage("Rimtu: enable");
		getServer().getPluginManager().registerEvents(new rimtu(), this);
		this.saveDefaultConfig();
	}
}