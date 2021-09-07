package command;

import file.Localization;
import file.Settings;
import file.UserPassword;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import player.playerManagement;



public class Login implements CommandExecutor
{
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) 
	{
		if (sender instanceof Player && command.getName().equals("login"))
		{
			if (args.length <= 2) 
			{
				boolean login = false, server = false, user = false;

				for (int i = 0; i < args.length; i++) 
				{
					if (!server && Settings.getInstance().isSetServerPassword() && args[i].equals(Settings.getInstance().getServerPassword())) 
						server = true;
					
					if (!user && UserPassword.getInstance().isRegister(sender.getName()))
						user = UserPassword.getInstance().login(sender.getName(), args[i]);
				}

				if (Settings.getInstance().isSetServerPassword() && Settings.getInstance().isSetUserLogin()) 
				{
					if (!UserPassword.getInstance().isRegister(sender.getName()))
						sender.sendMessage(Localization.getInstance().getLocalization("login_noregister"));
					else if (!server && !user)
						sender.sendMessage(Localization.getInstance().getLocalization("login_error_both"));
					else if (!server) 
						sender.sendMessage(Localization.getInstance().getLocalization("login_error_server"));
					else if (!user)
						sender.sendMessage(Localization.getInstance().getLocalization("login_error_user"));
					
					login = (server && user);
				}
				else if (Settings.getInstance().isSetServerPassword()) 
				{
					if (!server)
						sender.sendMessage(Localization.getInstance().getLocalization("login_error_server"));

					login = server;
				}
				else if (Settings.getInstance().isSetUserLogin()) 
				{
					if (!UserPassword.getInstance().isRegister(sender.getName()))
						sender.sendMessage(Localization.getInstance().getLocalization("login_noregister"));
					else if (!user)
						sender.sendMessage(Localization.getInstance().getLocalization("login_error_user"));

					login = user;
				} 

				if (login) 
				{
					playerManagement.getInstance().connect(((Player)sender).getUniqueId());
					sender.sendMessage(Localization.getInstance().getLocalization("login_succes"));
				} 
			}
			else
				sender.sendMessage(Localization.getInstance().getLocalization("too_many_arguments"));
			
		}
		
		return true;
	}
}