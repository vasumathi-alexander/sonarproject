package ges.util;

import org.openqa.selenium.WebDriver;

/**
 * @author Santhosh
 * 
 * 
 */
public class DriverManager {

	// To get and set the driver
	private static ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();

	public static WebDriver getDriver() {
		return webDriver.get();
	}

	protected static void setWebDriver(WebDriver driver) {
		webDriver.set(driver);
	}
}
