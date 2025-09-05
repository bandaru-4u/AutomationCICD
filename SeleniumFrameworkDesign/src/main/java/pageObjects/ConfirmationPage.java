package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import abstractComponents.AbstractComponent;

public class ConfirmationPage extends AbstractComponent {
	
	WebDriver  driver;
	
	@FindBy(css=".hero-primary")
	WebElement confirmationMessage;
	
	public ConfirmationPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
	}

	
	public String getOrderConfimation()
	{
		return confirmationMessage.getText();
//
				
	}
}
