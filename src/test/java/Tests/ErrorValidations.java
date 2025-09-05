package Tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import TestComponents.BaseTest;
import pageObjects.CartPage;
import pageObjects.ProductCatalog;

public class ErrorValidations extends BaseTest {

	@Test
	public void loginErrorValidation() throws IOException {
		// TODO Auto-generated method stub
		landingPage.LoginApplication("pbandaru@clopay.com1", "Clopay1!");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
	}

	@Test
	public void productErrorValidation()
	{
		String productName = "ZARA COAT 3";
			
		ProductCatalog productCatalog = landingPage.LoginApplication("pbandaru@clopay.com", "Clopay1!");
		List <WebElement> products = productCatalog.getProductList();
		productCatalog.addProductToCart(productName);
		CartPage cartPage= productCatalog.goToCartPage();
		Boolean match=cartPage.verifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);
	}
	
}
