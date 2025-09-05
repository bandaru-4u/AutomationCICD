package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {

	WebDriver driver;
	
	public LandingPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	//WebElement userEmail = driver.findElement(By.id("userEmail"));

	//PageFactory
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	//PageFactory
		@FindBy(id="userPassword")
		WebElement userpassword;
	
	@FindBy(id="login")
	WebElement submit;
 
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
	
	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public ProductCatalog LoginApplication(String email, String password)
	{
		userEmail.sendKeys(email);
		userpassword.sendKeys(password);
		submit.click();
		ProductCatalog productCatalog = new ProductCatalog(driver);
		return productCatalog;
	}
	
	public String getErrorMessage()
	{
	return	errorMessage.getText();
	}
}
