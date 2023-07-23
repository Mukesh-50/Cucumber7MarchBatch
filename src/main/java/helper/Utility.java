package helper;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import dataProvider.ConfigReader;


public class Utility {
	
	
	/*
	 * Find the element until its clickable and It will highlight as well
	 */
	public static WebElement waitForWebElement(WebDriver driver,By locator)
	{
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		
		WebElement element=wait.until(ExpectedConditions.elementToBeClickable(locator));
		
		if(ConfigReader.getProperty("highlightElement").contains("true"))
		{
			return highlightWebElement(driver, element);
		}
		else
		{
			return element;
		}
	
	}
	
	
	/*
	 * Find the element until its clickable (custom method)and It will highlight as well
	 */
	public static WebElement waitForElement(WebDriver driver,By locator)
	{
		WebElement element=null;
		
		for(int i=0;i<30;i++)
		{
			
			try 
			{
				element=driver.findElement(locator);
				
				if(element.isDisplayed() && element.isEnabled())
				{
					highlightWebElement(driver, locator);
					break;
				}
			} catch (Exception e) 
			{	
				System.out.println("Waiting for element conditions to be checked");
				wait(1);
			}
			
		}
			
		return element;
			
	}
	
	public static WebElement waitForElement(WebDriver driver,By locator,int time)
	{
		WebElement element=null;
		for(int i=0;i<time;i++)
		{
			
			try 
			{
				element=driver.findElement(locator);
				
				if(element.isDisplayed() && element.isEnabled())
				{
					highlightWebElement(driver, locator);
					break;
				}
			} catch (Exception e) 
			{	
				System.out.println("Waiting for element conditions to be checked");
				wait(1);
			}
			
		}
			
		return element;
			
	}
	
	
	

	
	public static void wait(int second)
	{
		
		try 
		{
			Thread.sleep(second*1000);
		} catch (InterruptedException e) {
			
			System.out.println("Something Went Wrong");
		}
		
		
	}
	
	
	public static WebElement highlightWebElement(WebDriver driver,WebElement ele)
	{	
		JavascriptExecutor js=(JavascriptExecutor)driver;

		js.executeScript("arguments[0].setAttribute('style','background: yellow; border: solid 2px red')",ele);

		Utility.wait(1);
		
		js.executeScript("arguments[0].setAttribute('style','border: solid 2px black')",ele);

		return ele;
	}

	
	public static WebElement highlightWebElement(WebDriver driver,By locator)
	{
		
		WebElement ele=driver.findElement(locator);
		
		JavascriptExecutor js=(JavascriptExecutor)driver;

		js.executeScript("arguments[0].setAttribute('style','background: yellow; border: solid 2px red')",ele);

		Utility.wait(1);
		
		js.executeScript("arguments[0].setAttribute('style','border: solid 2px black')",ele);

		
		return ele;
	}
		
	public static String getCurrentDate()
	{
		SimpleDateFormat myformat=new SimpleDateFormat("HH_mm_ss_dd_MM_yyyy");
		
		String newFormat=myformat.format(new Date());
		
		return newFormat;
	}
	
	public static String getCurrentDateNew()
	{
		return new SimpleDateFormat("HH_mm_ss_dd_MM_yyyy").format(new Date());
	}
	
	
	/*
	 * 	 This method will capture the WebElement Screenshot
	 *   @param - driver reference
	 *   @param - WebElement reference for which we need to take screenshot
	 */
	public static void captureScreenshotOfWebElement(WebElement ele)
	{
		
		File src=ele.getScreenshotAs(OutputType.FILE);
		
		try 
		{
			FileHandler.copy(src, new File("./screenshot/WebElement_"+Utility.getCurrentDate()+".png"));
			
		} catch (IOException e) {
			
			System.out.println("Can not take screenshot of WebElement");
		}
		
	}
	
	
	
	
	public static void captureScreenshot(WebDriver driver)
	{
		
		try 
		{
			FileHandler.copy(((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE), new File("./screenshot/screenshot"+getCurrentDate()+".png"));
			
		} catch (IOException e) {
			
			System.out.println("Exception "+e.getMessage());
		}
		
		
	}
	
	public static byte[] captureScreenshotInByte(WebDriver driver)
	{
		
		TakesScreenshot ts=(TakesScreenshot)driver;
		
		byte[] arr=ts.getScreenshotAs(OutputType.BYTES);
		
		return arr;
		
	}
	
	public static String captureScreenshotAsBase64(WebDriver driver)
	{
		
		TakesScreenshot ts=(TakesScreenshot)driver;
		
		String screenshot=ts.getScreenshotAs(OutputType.BASE64);
		
		return screenshot; 
	}
	
	/*
	 * 
	 * 	This method will wait till 15 second max for alert to appear
	 *  @param - WebDriver instance
	 *  @return - Alert interface reference
	 * 
	 */
	public static Alert waitForAlert(WebDriver driver)
	{
			
		Alert alt=null;
		
		for(int i=0;i<15;i++)
		{
			
			
			try 
			{
				alt=driver.switchTo().alert();

				break;
			}
			catch(NoAlertPresentException e)
			{
				try 
				{
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					
				}
				
				System.out.println("Alert Not Found - Retrying");
			}
			
			
		}
		
		return alt;
		
	}
	
	/*
	 * 
	 * 	This method will wait till the time that specified in parameter for alert to appear
	 *  @param - WebDriver instance
	 *  @param - time in seconds
	 *  @return - Alert interface reference
	 * 
	 */
	public static Alert waitForAlert(WebDriver driver,int seconds)
	{
			
		Alert alt=null;
		
		for(int i=0;i<seconds;i++)
		{
			
			
			try 
			{
				alt=driver.switchTo().alert();

				break;
			}
			catch(NoAlertPresentException e)
			{
				try 
				{
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					
				}
				
				System.out.println("Alert Not Found - Retrying");
			}
			
			
		}
		
		return alt;
		
	}
	
	
	public static void selectValueFromList(WebDriver driver,String xpathValue,String elementToSearch) 
	{
		
		List<WebElement> allValues=driver.findElements(By.xpath(xpathValue));
		
		for(WebElement ele:allValues)
		{
			if(ele.getText().contains(elementToSearch)) {
				ele.click();
				break;
			}
		}
	
	}
	
	public static void selectValueFromList(WebDriver driver,By locator,String elementToSearch) 
	{
		
		List<WebElement> allValues=driver.findElements(locator);
		
		for(WebElement ele:allValues)
		{
			if(ele.getText().contains(elementToSearch)) {
				ele.click();
				break;
			}
		}
	
	}

}
