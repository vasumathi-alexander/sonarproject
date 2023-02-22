package ges.util;



import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import ges.util.DriverManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.Driver;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;

import java.util.Set;
import java.util.concurrent.TimeUnit;
 
import org.openqa.selenium.Cookie;

public class BaseTest {

	private final static String ENVIRONMENT_PROP_PATH = "src/main/resources/prop/Environment.properties";
	private final static String TEST_DATA_PATH = "src/main/resources/prop/Testdata.properties";
	private final static String TEST_DATA_RBAC_PATH = "src/main/resources/prop/Testdata_rbac.properties";
	private final static String FIREBASE_CAPTCHA_PATH = "src/main/resources/prop/Firebase_&_Captcha.properties";
	
	public static String Browser = "Chrome";
	public static String GESBaseURL = env_property_file_reader("BaseURL");
	public static String DesignerURL = env_property_file_reader("DesignerURL");
	
    ITestContext ctx;

    public ITestContext getContext(){
        return this.ctx;
    }
	
  @BeforeTest(alwaysRun=true, groups={"current"})
  public void Setup() {
	  System.out.println("Before Test");
  }
  
  @BeforeMethod(alwaysRun=true, groups={"current"})
  public void beforeMethod(Method method) throws InterruptedException {
	  WebDriver driver = null;
	  if(Browser.equalsIgnoreCase("Firefox")) {
		  System.setProperty("webdriver.gecko.driver", "src/main/resources/browserdrivers/geckodriver.exe");
		  driver = new FirefoxDriver();
	  }else {
		  System.setProperty("webdriver.chrome.driver", "src/main/resources/browserdrivers/chromedriver.exe");
		  
		  System.out.println("\n BaseTest - Current User Directory :");
		  System.out.println(System.getProperty("user.dir"));
		  
		  ChromeOptions ChromeOptions = new ChromeOptions();
		  //ChromeOptions.addArguments("--headless", "window-size=1024,768", "--no-sandbox");
		  HashMap<String, Object> chromePref = new HashMap<>();
		  chromePref.put("download.default_directory", System.getProperty("user.dir"));
		  ChromeOptions.setExperimentalOption("prefs", chromePref);
		  ChromeOptions.addArguments("window-size=1024,768", "--no-sandbox", "--disable-dev-shm-usage");
		  driver=new ChromeDriver(ChromeOptions);
	  }	  
	  DriverManager.setWebDriver(driver);
	  DriverManager.getDriver().manage().window().maximize();
	  
	  String URL = GESBaseURL;
	  //String URL = env_property_file_reader("BaseURL");
	  DriverManager.getDriver().get(URL);
	  sleepFor(2); {
			Test test = method.getAnnotation(Test.class);
		  	//String testName = method.getName();
			System.out.println(
					" \n\n\n######################################################################################## \n\n\n");
			System.out.println("	Test name :  " + test.testName());
			System.out.println("	Test description : " + test.description());
			System.out.println(
					" \n\n\n########################################################################################  \n\n\n");
  }
	  DriverManager.getDriver().manage().deleteAllCookies();
  }
  
  @AfterMethod(alwaysRun=true, groups={"current"})
  public void afterMethod() {

      //DriverManager.getDriver().manage().deleteAllCookies();
	  DriverManager.getDriver().quit();
	  System.out.println("Deleting the cookies");
  }
  

  @AfterTest(alwaysRun=true, groups={"current"})
  public void afterTest() {
	  System.out.println("Done");
  }
  
  
  
  //***************************Reusable Methods**************************************
  
  	//To read data from environment properties file
	public static String env_property_file_reader(String propertyname) {
		File file = new File(ENVIRONMENT_PROP_PATH);

		FileInputStream fileInput = null;
		try {
			fileInput = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Properties prop = new Properties();

		// load properties file
		try {
			prop.load(fileInput);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return prop.getProperty(propertyname);
	}
	
    public String Create_new_phoneNumber() {
    	String randomNumbers = RandomStringUtils.randomNumeric(6);
    	String phone_number = 9952+randomNumbers;
    	System.out.println("\n The phone number that is generated : "+phone_number);
    	return phone_number;
    }
    
    public String Create_random_names() {
    	String randomNames = RandomStringUtils.randomAlphabetic(4);
    	String name = "test-"+randomNames;
    	System.out.println("\n The new random generated name : "+name);
    	return name;
    }
	
  	//To read data from Testdata properties file
	public static String testdata_property_file_reader(String propertyname) {
		File file = new File(TEST_DATA_PATH);

		FileInputStream fileInput = null;
		try {
			fileInput = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Properties prop = new Properties();

		// load properties file
		try {
			prop.load(fileInput);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return prop.getProperty(propertyname);
	}
	
  	//To read data from firebase and captcha properties file
	public static String firebase_captcha_property_file_reader(String propertyname) {
		File file = new File(FIREBASE_CAPTCHA_PATH);

		FileInputStream fileInput = null;
		try {
			fileInput = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Properties prop = new Properties();

		// load properties file
		try {
			prop.load(fileInput);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return prop.getProperty(propertyname);
	}
	
  	//To read data from RBAC Testdata properties file
	public static String testdata_rbac_property_file_reader(String propertyname) {
		File file = new File(TEST_DATA_RBAC_PATH);

		FileInputStream fileInput = null;
		try {
			fileInput = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Properties prop = new Properties();

		// load properties file
		try {
			prop.load(fileInput);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return prop.getProperty(propertyname);
	}
	
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
	
	
	//drag and drop
public void drag_and_drop(By element, By element2, int sec) {
	Wait<WebDriver> wait = new WebDriverWait(DriverManager.getDriver(), sec);
	try {
		//Element which needs to drag.    		
    	//WebElement From=wait.until(ExpectedConditions.presenceOfElementLocated(element));
    	WebElement From=DriverManager.getDriver().findElement(element);

     //Element on which need to drop.		
     //WebElement To=wait.until(ExpectedConditions.presenceOfElementLocated(element2));
    WebElement To=DriverManager.getDriver().findElement(element2);
     //Using Action class for drag and drop.		
     				

//Dragged and dropped.		
     //-----method-2 -----------
     //org.openqa.selenium.interactions.Action dragAndDrop = act.clickAndHold(From).moveToElement(To).release(To).build();

    		//dragAndDrop.perform();
     //method -1 ---
     //act.clickAndHold(From).moveByOffset(0, 100).moveToElement(To, 0, 100).release().build().perform();

    
     
   //-----method-3 -----------
    Actions act=new Actions(DriverManager.getDriver());	
    //act.dragAndDrop(From, To).build().perform();
    
  //-----method-4 -----------
    //act.dragAndDropBy(From,439,40).build().perform();
    
  //-----method-5 -----------
    //act.keyDown(Keys.CONTROL).click(From).click(To).keyUp(Keys.CONTROL);
 //org.openqa.selenium.interactions.Action selectMultiple = act.build();
 //selectMultiple.perform(); 
    
//-----method-6 -----------
    //org.openqa.selenium.interactions.Action dragAndDrop = act.clickAndHold(From).moveToElement(To).release(To).build();
    //dragAndDrop.perform();
    
  //-----method-7 -----------
    //act.clickAndHold(From).pause(2000).moveToElement(To).release().build().perform();
    
  //-----method-8 -----------
    //var builder = new Actions(driver);
    act.clickAndHold(From);
    act.moveToElement(To, 440, 40);
    act.perform();
    sleepFor(20);
    act.release(To);
    act.perform();
    

	} catch (WebDriverException e) {
		
	}
}


//-------------try this-----------------------------------

public void dragAndDrop(By element, By element2, int sec) {
	JavascriptExecutor js = (JavascriptExecutor)DriverManager.getDriver();
	WebElement From=DriverManager.getDriver().findElement(element);
	WebElement To=DriverManager.getDriver().findElement(element2);
    js.executeScript("function createEvent(typeOfEvent) {\n" + "var event =document.createEvent(\"CustomEvent\");\n"
            + "event.initCustomEvent(typeOfEvent,true, true, null);\n" + "event.dataTransfer = {\n" + "data: {},\n"
            + "setData: function (key, value) {\n" + "this.data[key] = value;\n" + "},\n"
            + "getData: function (key) {\n" + "return this.data[key];\n" + "}\n" + "};\n" + "return event;\n"
            + "}\n" + "\n" + "function dispatchEvent(element, event,transferData) {\n"
            + "if (transferData !== undefined) {\n" + "event.dataTransfer = transferData;\n" + "}\n"
            + "if (element.dispatchEvent) {\n" + "element.dispatchEvent(event);\n"
            + "} else if (element.fireEvent) {\n" + "element.fireEvent(\"on\" + event.type, event);\n" + "}\n"
            + "}\n" + "\n" + "function simulateHTML5DragAndDrop(element, destination) {\n"
            + "var dragStartEvent =createEvent('dragstart');\n" + "dispatchEvent(element, dragStartEvent);\n"
            + "var dropEvent = createEvent('drop');\n"
            + "dispatchEvent(destination, dropEvent,dragStartEvent.dataTransfer);\n"
            + "var dragEndEvent = createEvent('dragend');\n"
            + "dispatchEvent(element, dragEndEvent,dropEvent.dataTransfer);\n" + "}\n" + "\n"
            + "var source = arguments[0];\n" + "var destination = arguments[1];\n"
            + "simulateHTML5DragAndDrop(source,destination);", From, To);

}



	public int countOfElements(By locator) {
		List<WebElement> ele_count = DriverManager.getDriver().findElements(locator);
		int count = ele_count.size();
		//System.out.println("There are : "+count+" matching element for " +locator);
		return count;
		
	}
	
	public void getDesignerURL() {
		sleepFor(2);
		DriverManager.getDriver().navigate().to(DesignerURL);
	}
	
	public void getGESURL() {
		sleepFor(2);
		DriverManager.getDriver().navigate().to(GESBaseURL);
	}
	
	public void switchToIframe(WebElement element) {
		sleepFor(2);
		DriverManager.getDriver().switchTo().frame(element);
	}
	
	public void jsClick(WebElement element) {
		JavascriptExecutor executor = (JavascriptExecutor) DriverManager.getDriver();
		// executor.executeScript("arguments[0].scrollIntoView();",
		// element);
		executor.executeScript("arguments[0].click();", element);
	}
	
	
}
