package ges.util;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BaseTest_API {
	
	private final static String GES_DATA_PATH = "src/main/resources/prop/GES_Version.properties";
  
  @BeforeMethod
  public void beforeMethod(Method method) throws InterruptedException {
	  
			Test test = method.getAnnotation(Test.class);
			System.out.println(
					" \n\n\n######################################################################################## \n\n\n");
			System.out.println("	Test name :  " + test.testName());
			System.out.println("	Test description : " + test.description());
			System.out.println(
					" \n\n\n########################################################################################  \n\n\n");
  }
   
  //***************************Reusable Methods**************************************
  
  	
	//get Element
	public WebElement getElement(By element, int sec) {
		Wait<WebDriver> wait = new WebDriverWait(DriverManager.getDriver(), sec);
		try {
			WebElement we = wait.until(ExpectedConditions.presenceOfElementLocated(element));
			return we;
		} catch (WebDriverException e) {
			return null;
		}
	}
	

	//Explicit sleep
	public void sleepFor(int i) {
		try {
			  Thread.sleep(i*1000);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	//Select dropdown 
	
	//To write data from Testdata properties file
			public static String testdata_property_file_writer(String vers) throws IOException {
				//File file = new File();

				 File file = new File(GES_DATA_PATH);
				 file.createNewFile();
				
				 Properties pr = new Properties();
				 pr.setProperty("GES_Version", vers);
				 
				 FileOutputStream fis = new FileOutputStream("GES.properties");
				 pr.store(fis, "GES_Version");
				 fis.close();
				 return vers;
			}
	
	public Select selectByVisibleText(By locator, String value, int WaitTime) {
		Select localeList;
		WebElement element = getElement(locator, WaitTime);
		if (element != null) {
			moveToElement(element);
			try {
				localeList = new Select(element);
			} catch (IllegalStateException e) {
				localeList = new Select(element);
			}
		} else {
			localeList = null;
		}
		try {
			localeList.selectByVisibleText(value);
		} catch (IllegalStateException e) {
			localeList.selectByVisibleText(value);
		} catch (NoSuchElementException e) {
			Assert.fail(locator + " LISTBOX NOT FOUND. TEST FAILED.");
		} catch (NullPointerException e) {
			Assert.fail(locator + " LISTBOX NOT FOUND. TEST FAILED.");
		} catch (org.openqa.selenium.WebDriverException e) {
			Assert.fail(locator + " ACTION FAILED. TEST FAILED.");
		}
		return localeList;
	}
	
	/**
	 * Move to specific web element.
	 * 
	 * @param ele
	 */
	public void moveToElement(WebElement ele) {
		for (int i = 0; i < 5; i++) {
			sleepFor(1);
			try {
				JavascriptExecutor executor = (JavascriptExecutor) DriverManager.getDriver();
				executor.executeScript("arguments[0].scrollIntoView(true);", ele);
				break;
			} catch (StaleElementReferenceException e) {
				continue;
			}
		}
	}

	/**
	 * Returns the List of WebElements
	 * 
	 * @param locator
	 * @return
	 */
	public List<WebElement> get_webelements_list(By locator) {
		try {
			getElement(locator, 30);
			//moveToElement(DriverManager.getDriver().findElement(locator));
		} catch (NoSuchElementException NSE) {
			System.out.println(NSE.getMessage());
		}
		return DriverManager.getDriver().findElements(locator);
	}

	public int countOfElements(By locator) {
		List<WebElement> ele_count = DriverManager.getDriver().findElements(locator);
		int count = ele_count.size();
		//System.out.println("There are : "+count+" matching element for " +locator);
		return count;
		
	}
	
	public void jsClick(WebElement element) {
		JavascriptExecutor executor = (JavascriptExecutor) DriverManager.getDriver();
		// executor.executeScript("arguments[0].scrollIntoView();",
		// element);
		executor.executeScript("arguments[0].click();", element);
	}
	
	
}
