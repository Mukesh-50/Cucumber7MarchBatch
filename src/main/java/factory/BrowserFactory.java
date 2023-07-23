package factory;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import dataProvider.ConfigReader;


public class BrowserFactory 
{
	static WebDriver driver;
	
	// getter and setter method
	
	
	public static WebDriver getDriver()
	{
		return driver;
	}

	
	public static WebDriver startBrowser(String browserName,String applicationURL)
	{
			if(browserName.equalsIgnoreCase("Chrome") || browserName.equalsIgnoreCase("Google Chrome") || browserName.equalsIgnoreCase("GC"))
			{
				ChromeOptions opt=new ChromeOptions();
				
				if(ConfigReader.getProperty("headless").contains("true"))
				{
					opt.addArguments("--headless=new");	
				}
				
				driver=new ChromeDriver(opt);
			}
			else if(browserName.equalsIgnoreCase("Edge") || browserName.equalsIgnoreCase("Microsoft Edge"))
			{
				// add the same check for Edge
				driver=new EdgeDriver();
			}
			else if(browserName.equalsIgnoreCase("Firefox") || browserName.equalsIgnoreCase("FF"))
			{
				// add the same check for FF
				driver=new FirefoxDriver();
			}
			else
			{
				System.out.println("Sorry we do not support "+browserName + " Browser");
			}
		
			driver.manage().window().maximize();
			
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Integer.parseInt(ConfigReader.getProperty("pageLoad"))));
			
			driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(Integer.parseInt(ConfigReader.getProperty("scriptTimeOut"))));
			
			driver.get(applicationURL);
			
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(ConfigReader.getProperty("implicitWait"))));
			
			return driver;
	}

}
