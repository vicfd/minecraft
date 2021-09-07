package file;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import plugin.plugin;

public class Files
{
	private static Files instance;
	private HashMap<String, File> file = new HashMap<>();

	public static synchronized Files getInstance() 
	{
		if (instance == null)
			instance = new Files(); 
		return instance;
	}

	public FileConfiguration read(String fileName) 
	{
		YamlConfiguration yamlConfiguration;
		File aux_file = new File(plugin.getInstance().getDataFolder(), String.valueOf(fileName) + ".yml");

		if (!aux_file.exists())
			yamlConfiguration = YamlConfiguration.loadConfiguration(new InputStreamReader(plugin.getInstance().getResource(String.valueOf(fileName) + ".yml")));
		else
			yamlConfiguration = YamlConfiguration.loadConfiguration(aux_file);

		this.file.put(fileName, aux_file);
		save(fileName, (FileConfiguration)yamlConfiguration);

		return (FileConfiguration)yamlConfiguration;
	}

	public void save(String fileName, FileConfiguration f) 
	{
		try 
		{
			f.save(this.file.get(fileName));
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		} 
	}
}