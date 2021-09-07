package file;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class Settings extends YamlConfiguration
{
	private String fileName = "config";
	private FileConfiguration file = Files.getInstance().read(this.fileName);
	private static Settings instance;

	public static synchronized Settings getInstance() 
	{
		if (instance == null)
			instance = new Settings(); 
		return instance;
	}

	public boolean isSetServerPassword() 
	{
		return (getServerPassword().length() > 0);
	}

	public boolean isSetUserLogin() 
	{
		return this.file.getBoolean("userLogin");
	}

	public String getServerPassword()
	{
		return this.file.getString("serverPassword");
	}

	public void setServerPassword(String password) 
	{
		this.file.set("serverPassword", password);
		Files.getInstance().save(this.fileName, this.file);
	}
	
	public void setUserLogin(boolean in) 
	{
		this.file.set("userLogin", in);
		Files.getInstance().save(this.fileName, this.file);
	}
}