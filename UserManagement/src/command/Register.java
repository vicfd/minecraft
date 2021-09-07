package command;

import file.Localization;
import file.Settings;
import file.UserPassword;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Register implements CommandExecutor
{
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) 
	{
		if (sender instanceof org.bukkit.entity.Player && command.getName().equals("register") && Settings.getInstance().isSetUserLogin())
		{
			if (args.length == 1) 
			{
				if (!UserPassword.getInstance().isRegister(sender.getName())) 
				{
					UserPassword.getInstance().register(sender.getName(), args[0]);
					sender.sendMessage(String.valueOf(Localization.getInstance().getLocalization("register_succes")) + args[0]);
				}
				else
					sender.sendMessage(Localization.getInstance().getLocalization("register_already"));
			} 
			else 
				sender.sendMessage(Localization.getInstance().getLocalization("too_many_arguments"));
		}

		return true;
	}
}