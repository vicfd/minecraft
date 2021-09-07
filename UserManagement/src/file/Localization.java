package file;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class Localization extends YamlConfiguration
{
	private String fileName = "localization";
	private FileConfiguration file = Files.getInstance().read(this.fileName);
	private static Localization instance;

	public static synchronized Localization getInstance() 
	{
		if (instance == null)
			instance = new Localization(); 
		return instance;
	}

	public String getLocalization(String name) 
	{
		return this.file.getString(name);
	}
}