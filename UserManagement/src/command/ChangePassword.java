package command;

import file.Localization;
import file.Settings;
import file.UserPassword;
import player.playerManagement;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;

public class ChangePassword implements CommandExecutor
{
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) 
	{
		if (sender instanceof org.bukkit.entity.Player)
		{
			if (Settings.getInstance().isSetUserLogin())
			{
				if (playerManagement.getInstance().getPlayerStatus(((Entity) sender).getUniqueId()))
				{
					if (command.getName().equalsIgnoreCase("changepassword"))
					{
						if (args.length == 0) 
							sender.sendMessage(Localization.getInstance().getLocalization("changePassword_void"));
						else 
						{
							sender.sendMessage(Localization.getInstance().getLocalization("changePassword_succes") + " " + args[0]);
							UserPassword.getInstance().register(sender.getName(), args[0]);
						}
					}
				}
				else
					sender.sendMessage(Localization.getInstance().getLocalization("changePassword_online"));
			}
			else
				sender.sendMessage(Localization.getInstance().getLocalization("changePassword_disable"));

		}

		return true;
	}
}