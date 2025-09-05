package abstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjects.CartPage;
import pageObjects.OrdersPage;

public class AbstractComponent {
	
	WebDriver driver;
	
	public AbstractComponent(WebDriver driver)
	{
	this.driver=driver;	
	PageFactory.initElements(driver,this);
	}
	
	@FindBy(css="button[routerLink='/dashboard/cart']")
	WebElement cartHeader;
	
	@FindBy(css="[routerlink='/dashboard/myorders']")
	WebElement orderHistoryHeader;
	
	public void waitForElementToAppear(By findBy)
	{
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(5));
		w.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	
	public void waitForElementToDisappear(WebElement ele)
	{
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(5));
		w.until(ExpectedConditions.invisibilityOf(ele));
	}
	
	public CartPage goToCartPage()
	{
		cartHeader.click();
		CartPage cartPage =  new CartPage(driver);
		 return cartPage;
	}
	
	public OrdersPage goToOrdersPage()
	{
		orderHistoryHeader.click();
		OrdersPage ordersPage =  new OrdersPage(driver);
		 return ordersPage;
	}
}
