package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {

	WebDriver driver;
	
	public CartPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	

	@FindBy(css=".cartSection h3")
	List<WebElement> cartProducts;
	
	@FindBy(css = ".totalRow button")
    WebElement checkOut;	 
	
	
	public boolean verifyProductDisplay(String productName)
	{
		Boolean match = cartProducts.stream().anyMatch(s -> s.getText().contains("ZARA COAT 3"));
		return match;
	}
	
	
	public CheckOutPage goToCheckOut()
	{
		checkOut.click();
		CheckOutPage checkOutPage = new CheckOutPage(driver);
		return checkOutPage;
	 
	}
}
