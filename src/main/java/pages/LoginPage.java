package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import helper.Utility;



public class LoginPage {
	
	protected WebDriver driver;
	
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
		
	}
		
	private By errorMessage=By.className("errorMessage");

	private By username=By.id("email1");

	private By password=By.id("password1");
	
	private By loginButton=By.className("submit-btn");
	
	

	public void loginToApplication(String uname,String pass)
	{

		Utility.waitForWebElement(driver, username).sendKeys(uname);
		
		Utility.waitForWebElement(driver, password).sendKeys(pass);
		
		Utility.waitForWebElement(driver, loginButton).click();
		
	}
	
	public String captureErrorMessage()
	{
		
		WebElement element=Utility.waitForWebElement(driver, errorMessage);
		
		String error_msg=Utility.highlightWebElement(driver, element).getText();
		
		return error_msg;
	}
	
}
