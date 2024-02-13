package variousConcepts;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Data_Provider {


	
	WebDriver driver;
//	ExtentReports extent;
//	ExtentTest test;

	// Element list - By type
	By USER_NAME_FIELD = By.xpath("//input[@id='user_name']");
	By PASSWORD_FIELD = By.xpath("//*[@id=\"password\"]");
	By SINGIN_BUTTON_FIELD = By.xpath("//*[@id=\"login_submit\"]");
	By DASHBOARD_VALIDATION_FIELD = By.xpath("/html/body/div[1]/section/div/div[2]/div/div/header/div/strong");

////	@BeforeClass
//	public void reportGenerator() {
//		
//		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("extentReport.html");
//		
//		extent = new ExtentReports();
//		extent.attachReporter(htmlReporter);
//		test = extent.createTest("Parallel Execution Test", "Description");
//		
//	}
//	
//	@AfterClass
//	public void reporterClose() {
//		extent.flush();
//	}
	
	@BeforeMethod
	public void init() {
		System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.get("https://codefios.com/ebilling/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@DataProvider(name = "loginData")
	public String[][] loginData() {
		
		String[][] data = new String[][] {
			{"demo@codefios.com", "abc123"},
			{"demo5@codefios.com", "abc123"}
		};
		return data;
	}
	
	@Test(dataProvider = "loginData")
	public void testLogin(String userName, String password) {
		driver.findElement(USER_NAME_FIELD).sendKeys(userName);
		driver.findElement(PASSWORD_FIELD).sendKeys(password);
		driver.findElement(SINGIN_BUTTON_FIELD).click();

		
	}
	
	

}


