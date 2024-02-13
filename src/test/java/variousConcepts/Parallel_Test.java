package variousConcepts;


	import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

	public class Parallel_Test {

		public WebDriver driver;

		@Test
		public void EdgeTest() {

			System.out.println("The thread ID for Firefox is " + Thread.currentThread().getId());
			System.setProperty("webdriver.edge.driver", "drivers\\msedgedriver.exe");
			driver = new EdgeDriver();
			driver.get("https://www.yahoo.com/");

		}

		@Test
		public void ChromeTest() {

			System.out.println("The thread ID for Chrome is " + Thread.currentThread().getId());
			System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.get("https://www.yahoo.com/");
		} 
		
		@AfterMethod
		public void tearDown() {
			driver.close();
			driver.quit();
		}

}


