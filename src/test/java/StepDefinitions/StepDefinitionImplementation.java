package StepDefinitions;

import java.io.IOException;

import org.testng.Assert;

import TestComponents.BaseTestCucumber;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.CartPage;
import pageObjects.CheckOutPage;
import pageObjects.ConfirmationPage;
import pageObjects.LandingPage;
import pageObjects.ProductCatalog;

public class StepDefinitionImplementation extends BaseTestCucumber {

	LandingPage landingPage;
	ProductCatalog productCatalog;
	ConfirmationPage confirmationPage;
	
	
	
	@Given("^Logged with username (.+) and password (.+)$")
	public void Logged_with_username_and_password(String username, String password) throws IOException
	{landingPage =  launchMethod();
		productCatalog = landingPage.LoginApplication(username, password);		
	}
	
	@When("^I add product (.+) to Cart$")
	public void I_add_product_to_Cart(String productName)
	{
		productCatalog.addProductToCart(productName);
	}
	
	@When("^checkout (.+) and submit the order$")
	public void checkout_and_submit_the_order(String productName)
	{
		CartPage cartPage= productCatalog.goToCartPage();
		Boolean match=cartPage.verifyProductDisplay("productName");
		Assert.assertTrue(match);
		CheckOutPage checkOutPage=cartPage.goToCheckOut();
		checkOutPage.selectCountry("india");
		confirmationPage=checkOutPage.submitOrder();
	}
	
	@Then("{string} message is displayed on Confirmation page")
	public void message_is_displayed_on_Confirmation_page(String string)
	{
		//String orderConfirmationMessage=confirmationPage.getOrderConfimation();
		//Assert.assertTrue(orderConfirmationMessage.equalsIgnoreCase(string));
		driver.close();}
	
	@Then("{string} message should be displayed")
	public void loginErrorValidation(String string) throws IOException {
		// TODO Auto-generated method stub
		Assert.assertEquals(string, landingPage.getErrorMessage());
		driver.close();
	}
}
  

