package command;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

public class TabUserManagement implements TabCompleter
{
	public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) 
	{
		List<String> l = new ArrayList<>();

		if (args.length == 1 && command.getName().equalsIgnoreCase("usermanagement"))
		{
			l.add("setServerPassword");
			l.add("setUserLogin");
		}

		return l;
	}
}