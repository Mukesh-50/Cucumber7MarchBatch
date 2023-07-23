package dataProvider;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader 
{
	
	public static String getProperty(String key)
	{
		
		String value = null;
		try 
		{
			Properties pro=new Properties();
			
			FileInputStream fis=new FileInputStream(new File(System.getProperty("user.dir")+"/Configuration/Config.properties"));
			
			pro.load(fis);
			
			value = pro.getProperty(key);
			
		} catch (FileNotFoundException e) 
		{
			System.out.println("File not present "+e.getMessage());
			
		} catch (IOException e) 
		{
			System.out.println("Could not read the file "+e.getMessage());
		}
		
		return value;
	}

}
