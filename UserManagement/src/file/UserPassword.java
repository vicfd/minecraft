package file;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class UserPassword extends YamlConfiguration
{
	private String fileName = "userpassword";
	private FileConfiguration file = Files.getInstance().read(this.fileName);
	private static UserPassword instance;

	public static synchronized UserPassword getInstance() 
	{
		if (instance == null)
			instance = new UserPassword(); 
		return instance;
	}

	public boolean isRegister(String name) 
	{
		return this.file.contains(name);
	}

	public void register(String name, String password) 
	{
		this.file.set(name, password);
		Files.getInstance().save(this.fileName, this.file);
	}

	public boolean login(String name, String password) 
	{
		return password.equals(this.file.getString(name));
	}
}