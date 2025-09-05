package Tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import TestComponents.BaseTest;
import pageObjects.CartPage;
import pageObjects.CheckOutPage;
import pageObjects.ConfirmationPage;
import pageObjects.OrdersPage;
import pageObjects.ProductCatalog;

public class SubmitOrderTest extends BaseTest{

	//String productName = "ZARA COAT 3";
	
	@Test(dataProvider="getData", groups={"Purchase","Sanity"})
	public void submitOrder(HashMap<String,String> hashmap) throws IOException 
	{
		// TODO Auto-generated method stub

		String countryName = "India";
		
		ProductCatalog productCatalog = landingPage.LoginApplication(hashmap.get("email"), hashmap.get("password"));
		productCatalog.addProductToCart(hashmap.get("productName"));
		CartPage cartPage= productCatalog.goToCartPage();
		Boolean match=cartPage.verifyProductDisplay(hashmap.get("productName"));
		Assert.assertTrue(match);
		CheckOutPage checkOutPage=cartPage.goToCheckOut();
		checkOutPage.selectCountry(countryName);
		ConfirmationPage confirmationPage=checkOutPage.submitOrder();
		String orderConfirmationMessage=confirmationPage.getOrderConfimation();
		Assert.assertTrue(orderConfirmationMessage.equalsIgnoreCase("Thankyou for the order."));
		
	}
	
	
	@Test(dependsOnMethods={"submitOrder"},dataProvider="getData")
	
	public void OrderHistoryTest(HashMap m)
	{
		String email = m.get("email").toString();
		String password = m.get("password").toString();
		String productName = m.get("productName").toString();
		ProductCatalog productCatalog = landingPage.LoginApplication(email,password);
		OrdersPage ordersPage=productCatalog.goToOrdersPage();
		Assert.assertTrue(ordersPage.VerifyOrderDisplay(productName));
	}
	/*public void OrderHistoryTest(String email, String password, String productName)
	{
		
		ProductCatalog productCatalog = landingPage.LoginApplication(email,password);
		OrdersPage ordersPage=productCatalog.goToOrdersPage();
		Assert.assertTrue(ordersPage.VerifyOrderDisplay(productName));
	}

	
	/*@DataProvider using Arrays
	public Object[][] getData()
	{
		Object[][] obj = new Object[][] {{"pbandaru@gmail.com","Clopay1!","ZARA  COAT 3"},{"pbandaru1@gmail.com","Clopay1!","ZARA  COAT 33"}};
		return obj;
	}*/
	
	
/*	@DataProvider using HashMap
	public Object[][] getData()
	{
		HashMap<String,String> map = new HashMap<String,String>();
	map.put("email","pbandaru@gmail.com");
	map.put("password", "Clopay1!");
	map.put("productName", "ZARA COAT 3");

	HashMap<String,String> map1 = new HashMap();
map1.put("email","pbandaru1@gmail.com");
map1.put("password", "Clopay1!1");
map1.put("productName", "ZARA COAT 33");

	Object[][] obj = new Object[][] {{map},{map1}};
	return obj;
	
	}*/
	
	
	
	@DataProvider
	public Object[][] getData() throws IOException
{
String filePath=System.getProperty("user.dir")+"\\src\\main\\java\\Resources\\PurchaseOrder.json";
		List<HashMap<String,String>> data= getJsonDataToHashMap(filePath);
		Object[][] obj = new Object[][] {{data.get(0)}, {data.get(1)}};
		return obj;
	}
	
}
