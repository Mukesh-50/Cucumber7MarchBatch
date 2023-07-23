package hooks;

import org.openqa.selenium.WebDriver;

import dataProvider.ConfigReader;
import factory.BrowserFactory;
import helper.Utility;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hook {
	
	public WebDriver driver;
	
	@Before
	public void startBrowser()
	{
		driver=BrowserFactory.startBrowser(ConfigReader.getProperty("browser"), ConfigReader.getProperty("url"));
	}
	
	@After
	public void closeBrowser()
	{
		driver.quit();
	}
	
	@AfterStep
	public void tearDown(Scenario scenario)
	{
		String name=scenario.getName();
		
		if(scenario.isFailed())
		{
			scenario.attach(Utility.captureScreenshotInByte(driver), "image/png", name);
		}
	}
	
	
	

}
