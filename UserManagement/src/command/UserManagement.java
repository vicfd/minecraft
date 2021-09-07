package command;

import file.Localization;
import file.Settings;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class UserManagement implements CommandExecutor
{
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) 
	{
		if (sender instanceof org.bukkit.entity.Player && sender.isOp() && command.getName().equalsIgnoreCase("usermanagement"))
		{
			if (args.length > 0)
			{
				if (args[0].equalsIgnoreCase("setserverpassword"))
				{
					if (args.length == 1)
					{
						Settings.getInstance().setServerPassword("");
						sender.sendMessage(Localization.getInstance().getLocalization("setPassword_void"));
					}
					else 
					{
						Settings.getInstance().setServerPassword(args[1]);
						sender.sendMessage(String.valueOf(Localization.getInstance().getLocalization("setPassword_succes")) + args[1]);
					} 
				}
				else if (args[0].equalsIgnoreCase("setuserlogin"))
				{
					if (Settings.getInstance().isSetUserLogin())
					{
						Settings.getInstance().setUserLogin(false);
						sender.sendMessage(String.valueOf(Localization.getInstance().getLocalization("userLogin_disable")));
					}
					else
					{
						Settings.getInstance().setUserLogin(true);
						sender.sendMessage(String.valueOf(Localization.getInstance().getLocalization("userLogin_enable")));
					}
				}
			}
		}

		return true;
	}
}