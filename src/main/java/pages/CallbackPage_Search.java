package ges.pages;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;

import ges.util.BaseTest;

public class CallbackPage_Search extends BaseTest{
	
	private By search_box = By.xpath("//input[@class='mainInput']");
	
	private By searchby_serviceid = By.xpath("//p[text()='Service Id']");
	
	private By btn_search_options = By.xpath("//*[@class='search-category-dropdown-inputs-container']/div[2]/button");
	
	/**************************************************************************/
	
	public void Verify_ServiceID_Search(String val) {
		
		sleepFor(2);
		
		//click the dropdown for the search filters
		getElement(btn_search_options, 15).click();
		
		// set the filter for the service id search
		getElement(searchby_serviceid, 15).click();
		
		// enter the service id
		getElement(search_box, 15).sendKeys(val);
		System.out.println("\n Searching for the callback with service id :" +val);
		int a = countOfElements(By.xpath("//div[contains(@class,'callbackTableCell')]/div"));
		System.out.println("\n There should be only 1 record as service ID should be unique. \n There are : "+a+ " matching record(s).");
		assertEquals(a, 1);
		
	}
	
	
	
}
