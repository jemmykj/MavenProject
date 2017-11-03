package mavenProject.MessengerAutomation;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class BaseClass {

	private AndroidDriver driver;

	@BeforeTest
	public void setUp() throws MalformedURLException {

		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "030834eed0218526");
		capabilities.setCapability("appPackage", "com.facebook.orca");
		capabilities.setCapability("appActivity", "com.facebook.orca.auth.StartScreenActivity");
		
		capabilities.setCapability("noReset", "true");
		capabilities.setCapability("fullReset", false);
		
		// ?? MalformedURLException
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
//System.out.println();
	}

	public AndroidDriver getDriver() {
	
		return driver;
	}

	@AfterTest
	public void end() {
		driver.quit();
	}
}
