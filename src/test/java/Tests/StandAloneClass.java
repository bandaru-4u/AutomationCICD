package Tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import pageObjects.LandingPage;

public class StandAloneClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String productName = "ZARA COAT 3";

		WebDriver driver = new ChromeDriver();
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(5));

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		driver.get("https://rahulshettyacademy.com/client");
		
		LandingPage landingPage = new LandingPage(driver);
		
		driver.findElement(By.id("userEmail")).sendKeys("pbandaru@clopay.com");
		driver.findElement(By.id("userPassword")).sendKeys("Clopay1!");
		driver.findElement(By.id("login")).click();

		List<WebElement> products = driver.findElements(By.className("mb-3"));
		WebElement prod = products.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst()
				.orElse(null);

		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();

		w.until(ExpectedConditions.invisibilityOf(driver.findElement(By.className("ng-animating"))));
		w.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("toast-container"))));

		driver.findElement(By.cssSelector("button[routerLink='/dashboard/cart']")).click();

		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean itemAvailability = cartProducts.stream().anyMatch(s -> s.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(itemAvailability);

		driver.findElement(By.cssSelector(".totalRow button")).click();

		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("input[placeholder='Select Country']")), "ind").build().perform();

		w.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("section[class*='ta-results']"))));

		driver.findElement(By.xpath("//button[contains(@class,'ta-item')][2]")).click();

		w.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".action__submit")));

		WebElement submit = driver.findElement(By.cssSelector(".action__submit"));

		a.moveToElement(submit).click().build().perform();

		String confirmationMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();

		Assert.assertTrue(confirmationMessage.equalsIgnoreCase("Thankyou for the order."));
	}

}
