package plugin;

import command.ChangePassword;
import command.Login;
import command.Register;
import command.TabUserManagement;
import command.UserManagement;
import listener.BlockListener;
import listener.EntityListener;
import listener.PlayerListener;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import player.playerManagement;

public class plugin extends JavaPlugin 
{
	private static plugin instance;
	
	public static synchronized plugin getInstance() 
	{
		return instance;
	}

	public void onEnable() 
	{		
		instance = this;
		playerManagement.getInstance().load();
		getServer().getPluginManager().registerEvents((Listener)new BlockListener(), (Plugin)this);
		getServer().getPluginManager().registerEvents((Listener)new EntityListener(), (Plugin)this);
		getServer().getPluginManager().registerEvents((Listener)new PlayerListener(), (Plugin)this);
		getCommand("register").setExecutor((CommandExecutor)new Register());
		getCommand("login").setExecutor((CommandExecutor)new Login());
		getCommand("changepassword").setExecutor((CommandExecutor)new ChangePassword());
		getCommand("UserManagement").setExecutor((CommandExecutor)new UserManagement());
		getCommand("UserManagement").setTabCompleter((TabCompleter)new TabUserManagement());
		
		for(Player p : instance.getServer().getOnlinePlayers())
		{
			playerManagement.getInstance().join(p);
		}
	}
	
	public void onDisable()
	{
		for(Player p : instance.getServer().getOnlinePlayers())
		{
			playerManagement.getInstance().disconnect(p.getUniqueId());
		}
	}
}