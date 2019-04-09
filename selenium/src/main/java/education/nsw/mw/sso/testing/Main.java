package education.nsw.mw.sso.testing;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Main {
	
	 private static ChromeDriverService service;
	  private static WebDriver driver;

	  public static void createAndStartService() throws IOException {
	    service = new ChromeDriverService.Builder()
	        .usingDriverExecutable(new File("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe"))
	        .usingAnyFreePort()
	        .build();
	    service.start();
	  }


	  public static void createAndStopService() {
	    service.stop();
	  }

	  public static void createDriver() {
	    driver = new RemoteWebDriver(service.getUrl(), DesiredCapabilities.chrome());
	  }

	  public static void quitDriver() {
	    driver.quit();
	  }

	public static void main(String[] args) {
	
		System.setProperty("webdriver.chrome.driver", 
			"C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");
		
		try {
			
			createAndStartService();
			createDriver();

	        // And now use this to visit Google
	        driver.get("http://www.google.com");
	        // Alternatively the same thing can be done like this
	        // driver.navigate().to("http://www.google.com");

	        // Find the text input element by its name
	        WebElement element = driver.findElement(By.name("q"));

	        // Enter something to search for
	        element.sendKeys("Cheese!");

	        // Now submit the form. WebDriver will find the form for us from the element
	        element.submit();

	        // Check the title of the page
	        System.out.println("Page title is: " + driver.getTitle());
	        
	        // Google's search is rendered dynamically with JavaScript.
	        // Wait for the page to load, timeout after 10 seconds
	        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
	            public Boolean apply(WebDriver d) {
	                return d.getTitle().toLowerCase().startsWith("cheese!");
	            }
	        });
	        
	        quitDriver();
	        
		}catch(IOException x) {
			x.printStackTrace();
		}
		
		
	}

}
