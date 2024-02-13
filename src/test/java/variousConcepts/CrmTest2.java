package variousConcepts;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class CrmTest2 {
	WebDriver driver;

	String browser;
	String url;
	String username;
	String password;

	String DASHBOARD_VALIDATION_TEXT = "Dashboard";
	String USERNAME_ALERT_TEXT = "Please enter your user name";

	By USER_NAME_FIELD = By.xpath("//input[@id='user_name']");
	By USER_PASSWORD_FIELD = By.xpath("//*[@id='password']");
	By SIGN_IN_BUTTON = By.xpath("//*[@id='login_submit']");
	By DASHBOARD_VALIDATION_FIELD = By.xpath("/html/body/div[1]/section/div/div[2]/div/div/header/div/strong");

	@BeforeTest
	public void readConfig() {

		try {
			InputStream input = new FileInputStream("src\\main\\java\\config\\config2.properties");
			Properties prop = new Properties();
			prop.load(input);
			browser = prop.getProperty("browser");
			System.out.println("Current browser is " + browser);
			url = prop.getProperty("url");
			username = prop.getProperty("username");
			password = prop.getProperty("password");

		} catch (Exception e) {
			e.getStackTrace();
		}

	}

	@BeforeMethod
	public void init() {

		if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("edge")) {
			System.setProperty("webdriver.edge.driver", "drivers\\edgedriver.exe");
			driver = new EdgeDriver();
		} else {
			System.out.println("Please select a valid browser.");
		}

		driver.manage().deleteAllCookies();
		driver.get("https://codefios.com/ebilling/login");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}

	@Test(priority = 1)
	public void testLogin() {

		driver.findElement(USER_NAME_FIELD).sendKeys(username);
		driver.findElement(USER_PASSWORD_FIELD).sendKeys(password);
		driver.findElement(SIGN_IN_BUTTON).click();
		Assert.assertEquals(driver.findElement(DASHBOARD_VALIDATION_FIELD).getText(), DASHBOARD_VALIDATION_TEXT,
				"Dashboard not found");
	}

	@Test(priority = 2)
	public void testAlert() {
		driver.findElement(SIGN_IN_BUTTON).click();
		Assert.assertEquals(driver.switchTo().alert().getText(), USERNAME_ALERT_TEXT, "Please enter username");
		driver.switchTo().alert().accept();
	}

	@AfterMethod
	public void tearDown() {
		driver.close();
		driver.quit();
	}

}
