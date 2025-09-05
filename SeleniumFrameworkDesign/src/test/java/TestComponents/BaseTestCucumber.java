package TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import pageObjects.LandingPage;

public class BaseTestCucumber {
	
	public WebDriver driver;
public LandingPage landingPage;
	
		@BeforeMethod
	public LandingPage launchMethod() throws IOException
	{
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		landingPage = new LandingPage(driver);
		landingPage.goTo();
		return landingPage;
	}

	
	@AfterMethod
	public void tearDown() throws InterruptedException
	{
		driver.close();
	}
	
	public List<HashMap<String, String>> getJsonDataToHashMap(String filePath) throws IOException
	{
		String jsonContent = FileUtils.readFileToString(new File(filePath),StandardCharsets.UTF_8);
		ObjectMapper mapper= new ObjectMapper();
	List<HashMap<String,String>> data=mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>(){});
return data;
	}
	
	
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException
	{
	TakesScreenshot ts = (TakesScreenshot) driver;
	File source=ts.getScreenshotAs(OutputType.FILE);
	String destinationFilePath = System.getProperty("user.dir"+"//reports//"+testCaseName+".png");
File destination = new File(destinationFilePath);
FileUtils.copyFile(source, destination);
return destinationFilePath;
	}
	
}
