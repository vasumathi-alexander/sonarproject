package ges.pages;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.*;

import org.openqa.selenium.By;

import ges.util.BaseTest;
import junit.framework.Assert;

public class DevopsPage extends BaseTest{
	
	/**************************************************************************/
	/************************* Navigation Elements**************************************/
	private By devops_tab = By.id("devops-nav");
	private By option_serverHealth = By.xpath("//div[@value='health']");
	private By btn_refresh = By.id("button-refresh-server-health");
	private By option_alarm_alerts = By.xpath("//div[@value='alarms']");
	private By option_active_alarms = By.xpath("//*[@id='tab-item-alarms']");
	private By option_alarm_types = By.xpath("//*[@id='tab-item-types']");
	private By option_Redis_Status = By.xpath("//div[@value='redis']");
	private By btn_refresh_redis = By.id("button-refresh-redis-stats");
	private By input_redis = By.xpath("//*[@id='redis-view-grid-container']/div/div[1]/div/div/div/div/input[2]");
	private By search_box = By.xpath("//*[@id='alert-view']/div[1]/div[2]/div/div/div[1]/div/div/div/input[2]");
	private By search_box_alarm = By.xpath("//*[@id='alarm-view']/div[1]/div[2]/div/div/div[1]/div/div/div/input[2]");
	private By option_Tenant_Config = By.xpath("//div[@value='tenant']");
	private By option_select_tenant = By.xpath("//*[@id='select-tenant']");
	private By option_2113_tenant = By.xpath("//option[@value='217576a6-52b6-4ca6-8094-a29b726f7e9f']");
	private By option_Service_Config = By.xpath("//*[@id='tab-item-service']");
	private By option_Service_Select = By.xpath("//*[@id='suggested-value-text-area']");
	private By option_Service_Select_property = By.xpath("//*[@id='display-service-config']/div[2]/div/div[1]/div/div/div/div/input[1]");
	private By option_Tenant_Configuration = By.xpath("//*[@id='tab-item-tenant']");
	private By option_ORS_URS_Status = By.xpath("//*[@id='tab-item-status']");
	/*********************************ADVANCED_LOGIN ELEMENTS*****************************************/
	private By option_Adv_login = By.xpath("//div[@value='advanced']");
	private By Adv_Username = By.xpath("//*[@id='input-dev-ops-username']");
	private By Adv_Password = By.xpath("//*[@id='input-dev-ops-password']");
	private By Adv_login = By.xpath("//button[@id='login-dev-ops-advanced']");
	/**************************************************************************/
	private By btn_alarm_next = By.xpath("//button[@title='Next']");
	private By btn_alarm_last = By.xpath("//button[@title='Last']");
	private By btn_alarm_previous = By.xpath("//button[@title='Previous']");
	private By btn_alarm_first = By.xpath("//button[@title='First']");
	private By page_number = By.xpath("//input[@data-role='input']");
	private By option_redis_clients = By.id("tab-item-Clients");
	private By option_memory_usage = By.id("tab-item-Memory");
	private By option_redis_persistence = By.id("tab-item-Persistence");
	private By option_cpu_usage = By.id("tab-item-CPU");
	private By option_misc_stats = By.id("tab-item-Stats");
	private By option_ges_stats = By.id("tab-item-GES");
	private By option_redis_command_stats = By.id("tab-item-Commandstats");
	private By option_redis_keys_info = By.id("tab-item-Keyspace");
	
	/*********************************ADVANCED_LOGIN ELEMENTS*****************************************/
	private By option_logging = By.xpath("//div[@value='logging']");
	//*[@id="view-devops"]/div/div/div[2]/input
	private By duration_input = By.xpath("//input");
	private By update_logging = By.xpath("//*[@id='update-logging-button']");
	
	
	/***************************************************************************/
	
	public void Navigate_To_DevopsTab() {
		// Clicking the Tools tab
		System.out.println("\n Clicking the Devops tab");
		sleepFor(3);
		getElement(devops_tab, 15).click();
	}
	
	public void Click_Server_Health() {
		// Selecting the server health
		System.out.println("\n Selecting the Server health option");
		sleepFor(3);
		getElement(option_serverHealth, 15).click();
	}
	
	public void Click_Refresh_Button() {
		// Selecting refresh button
		System.out.println("\n Selecting the Refresh Button");
		sleepFor(3);
		getElement(btn_refresh, 15).click();
		sleepFor(6);
	}
	
	public int Capture_uptime() {
		
		System.out.println("\n Capturing the uptime in secs");
		sleepFor(3);
		String a = getElement(By.xpath("((//div[contains(@class,'grid-rowgroup')]/div)[1]//div[@class='cell-content'])[2]"),15).getText();
		int uptime = Integer.parseInt(a);
		System.out.println("\n The uptime captured is : "+uptime);
		return uptime;
	}
	
	public void Clear_all_notifications() {
		sleepFor(2);
		int a = countOfElements(By.xpath("//button[@class='btn-close']"));
		for (int i=1;i<=a;i++)
		{
			getElement(By.xpath("(//button[@class='btn-close'])[1]"), 15).click();
			sleepFor(2);
		}
	}

	public void Verify_Column_Headers_UI() {
		
		System.out.println("\n The columns would be displayed as : Hostname | Uptime (in secs) | IP Address | DB | GWS-ENVIRONMENT | GWS-AUTH | GWS-AUTH Version | GWS-CONFIG | REDIS | Start Time | Version");
		List <String> default_col_headers = new ArrayList<String>();
		
		default_col_headers.add("Hostname");
		default_col_headers.add("Uptime (in secs)");
		default_col_headers.add("IP Address");
		default_col_headers.add("DB");
		default_col_headers.add("GWS-ENVIRONMENT");
		default_col_headers.add("GWS-AUTH");
		default_col_headers.add("GWS-AUTH Version");
		default_col_headers.add("GWS-CONFIG");
		default_col_headers.add("REDIS");
		default_col_headers.add("Start Time");
		default_col_headers.add("Version");
		

		int m = countOfElements(By.xpath("//div[@id='hrow-server-health-grid']//div[@class='cell-content']"));
		System.out.println("\n There are : "+m+ " headers in the page");
		
		List <String> fetched_col_headers = new ArrayList<String>();
		for (int i=1; i<=m; i++)
		{
			fetched_col_headers.add(getElement(By.xpath("(//div[@id='hrow-server-health-grid']//div[@class='cell-content'])["+i+"]"), 15).getText());
		}
		
		System.out.println("\n The column headers fetched from the UI is : "+fetched_col_headers);
		
		Assert.assertEquals(fetched_col_headers,default_col_headers);
		
		System.out.println("\n The column headers are present in the page and verified successfully");
	}
	
	public void Fetch_number_of_nodes() {
		System.out.println("\n Fetching the number of GES nodes.");
		sleepFor(3);
		int n = countOfElements(By.xpath("//div[contains(@class,'grid-rowgroup')]/div"));
		System.out.println("\n There are "+n+" number of nodes displayed in the page");
		Assert.assertTrue("Expecting at least 2 GES nodes should be present", n>=2);
	}
	
	public void Display_Node_Details(int i) {
		System.out.println("\n ************** Node "+i+" Details ****************\n");
		System.out.println("Hostname : "+getElement(By.xpath("((//div[contains(@class,'grid-rowgroup')]/div)["+i+"]//div[@class='cell-content'])[1]"),15).getText());
		System.out.println("Uptime (in secs) : "+getElement(By.xpath("((//div[contains(@class,'grid-rowgroup')]/div)["+i+"]//div[@class='cell-content'])[2]"),15).getText());
		System.out.println("IP Address : "+getElement(By.xpath("((//div[contains(@class,'grid-rowgroup')]/div)["+i+"]//div[@class='cell-content'])[3]"),15).getText());
		System.out.println("DB : "+getElement(By.xpath("((//div[contains(@class,'grid-rowgroup')]/div)["+i+"]//div[@class='cell-content'])[4]"),15).getText());
		System.out.println("GWS-Environment : "+getElement(By.xpath("((//div[contains(@class,'grid-rowgroup')]/div)["+i+"]//div[@class='cell-content'])[5]"),15).getText());
		System.out.println("GWS-Auth : "+getElement(By.xpath("((//div[contains(@class,'grid-rowgroup')]/div)["+i+"]//div[@class='cell-content'])[6]"),15).getText());
		System.out.println("GWS-Auth Version : "+getElement(By.xpath("((//div[contains(@class,'grid-rowgroup')]/div)["+i+"]//div[@class='cell-content'])[7]"),15).getText());
		System.out.println("GWS-Config : "+getElement(By.xpath("((//div[contains(@class,'grid-rowgroup')]/div)["+i+"]//div[@class='cell-content'])[8]"),15).getText());
		System.out.println("Redis : "+getElement(By.xpath("((//div[contains(@class,'grid-rowgroup')]/div)["+i+"]//div[@class='cell-content'])[9]"),15).getText());
		System.out.println("Start Time : "+getElement(By.xpath("((//div[contains(@class,'grid-rowgroup')]/div)["+i+"]//div[@class='cell-content'])[10]"),15).getText());
		System.out.println("Version : "+getElement(By.xpath("((//div[contains(@class,'grid-rowgroup')]/div)["+i+"]//div[@class='cell-content'])[11]"),15).getText());		
		System.out.println("\n *************************************************");	
		}
	public void Click_Alarm_Alerts() {
		// Selecting the server health
		System.out.println("\n Selecting the Alarm & Alerts option");
		sleepFor(3);
		getElement(option_alarm_alerts, 15).click();
		sleepFor(10);
	}
	
	public void Verify_Column_Headers_Alerts() {
		
		System.out.println("\n The columns would be displayed as : Type | Severity | Timestamp | Message | Incident ID | Resource | Adapter | Correlation ID | Hostname | CCID");
		List <String> default_col_headers = new ArrayList<String>();
		
		default_col_headers.add("Type");
		default_col_headers.add("Severity");
		default_col_headers.add("Timestamp");
		default_col_headers.add("Message");
		default_col_headers.add("Incident ID");
		default_col_headers.add("Resource");
		default_col_headers.add("Adapter");
		default_col_headers.add("Correlation ID");
		default_col_headers.add("Hostname");
		default_col_headers.add("CCID");
		sleepFor(3);


		int m = countOfElements(By.xpath("//div[@id='hrow-alert-grid']//div[@class='cell-content']"));
		System.out.println("\n There are : "+m+ " headers in the page");
		
		List <String> fetched_col_headers = new ArrayList<String>();
		for (int i=1; i<=m; i++)
		{
			fetched_col_headers.add(getElement(By.xpath("(//div[@id='hrow-alert-grid']//div[@class='cell-content'])["+i+"]"), 15).getText());
		}
		
		System.out.println("\n The column headers fetched from the UI is : "+fetched_col_headers);
		
		Assert.assertEquals(fetched_col_headers,default_col_headers);
		
		System.out.println("\n The column headers are present in the page and verified successfully");
	}
	
	public void Click_Active_Alarms() {
		// Selecting the server health
		System.out.println("\n Selecting the Active Alarms option");
		sleepFor(3);
		getElement(option_active_alarms, 15).click();
	}
	
	public void Verify_Column_Headers_Alarms() {
		
		System.out.println("\n The columns would be displayed as : Type | Severity | Timestamp | Message | Incident ID | Exceeds Tolerance | Resource | Adapter | Correlation ID | Hostname | CCID");
		List <String> default_col_headers = new ArrayList<String>();
		
		default_col_headers.add("Type");
		default_col_headers.add("Severity");
		default_col_headers.add("Timestamp");
		default_col_headers.add("Message");		
		default_col_headers.add("Incident ID");
		default_col_headers.add("Exceeds Tolerance");
		default_col_headers.add("Resource");
		default_col_headers.add("Adapter");
		default_col_headers.add("Correlation ID");
		default_col_headers.add("Hostname");
		default_col_headers.add("CCID");
		

		int m = countOfElements(By.xpath("//div[@id='hrow-alarm-grid']//div[@class='cell-content']"));
		System.out.println("\n There are : "+m+ " headers in the page");
		
		List <String> fetched_col_headers = new ArrayList<String>();
		for (int i=1; i<=m; i++)
		{
			fetched_col_headers.add(getElement(By.xpath("(//div[@id='hrow-alarm-grid']//div[@class='cell-content'])["+i+"]"), 15).getText());
		}
		
		System.out.println("\n The column headers fetched from the UI is : "+fetched_col_headers);
		
		Assert.assertEquals(fetched_col_headers,default_col_headers);
		
		System.out.println("\n The column headers are present in the page and verified successfully");
	}
	
	public void Click_Alarm_Types() {
		// Selecting the server health
		System.out.println("\n Selecting the Alarm Types option");
		sleepFor(3);
		getElement(option_alarm_types, 15).click();
	}
	
	public void Verify_Column_Headers_Alarm_Types() {
		
		System.out.println("\n The columns would be displayed as : Name | Severity | Tolerance -- Max | Tolerance -- Time | Tolerance -- Periods | Throttle -- Max | Throttle -- Time | Class");
		List <String> default_col_headers = new ArrayList<String>();
		
		default_col_headers.add("Name");
		default_col_headers.add("Severity");
		default_col_headers.add("Tolerance -- Max");
		default_col_headers.add("Tolerance -- Time");		
		default_col_headers.add("Tolerance -- Periods");
		default_col_headers.add("Throttle -- Max");
		default_col_headers.add("Throttle -- Time");
		default_col_headers.add("Class");
		

		int m = countOfElements(By.xpath("//div[@id='hrow-alarm-type-grid']//div[@class='cell-content']"));
		System.out.println("\n There are : "+m+ " headers in the page");
		
		List <String> fetched_col_headers = new ArrayList<String>();
		for (int i=1; i<=m; i++)
		{
			fetched_col_headers.add(getElement(By.xpath("(//div[@id='hrow-alarm-type-grid']//div[@class='cell-content'])["+i+"]"), 15).getText());
		}
		
		System.out.println("\n The column headers fetched from the UI is : "+fetched_col_headers);
		
		Assert.assertEquals(fetched_col_headers,default_col_headers);
		
		System.out.println("\n The column headers are present in the page and verified successfully");
	}
	
	public void Verify_Rows_Alarm_Alerts_Page() {
		
		System.out.println("\n The rows would be displayed as : Active Alerts | Active Alarms | Alarm Types | IP Whitelist");
		List <String> default_col_headers = new ArrayList<String>();
		
		default_col_headers.add("Active Alerts");
		default_col_headers.add("Active Alarms");
		default_col_headers.add("Alarm Types");
		default_col_headers.add("IP Whitelist");				

		int m = countOfElements(By.xpath("//*[@id='alarms-side-bar-container']/ul/li"));
		System.out.println("\n There are : "+m+ " rows in the page");
		String a = "["; 
		
		List <String> fetched_col_headers = new ArrayList<String>();
		for (int i=1; i<=m; i++)
		{
			System.out.println("\n inside loop");
			fetched_col_headers.add(getElement(By.xpath("(//*[@id='alarms-side-bar-container']/ul/li)["+i+"]"), 15).getText());
			System.out.println("\n The row headers fetched from the UI is : "+fetched_col_headers);
		}
		
		System.out.println("\n The row headers fetched from the UI is : "+fetched_col_headers);
		
		Assert.assertEquals(fetched_col_headers,default_col_headers);
		
		System.out.println("\n The row headers are present in the page and verified successfully");
	}
	
	
	public void Click_Alerts_Sort(String col) {

		System.out.println("\n Sorting the column: " + col);
		List <String> default_col_headers = new ArrayList<String>();
		
		default_col_headers.add("Type");
		default_col_headers.add("Severity");
		default_col_headers.add("Timestamp");
		default_col_headers.add("Message");
		default_col_headers.add("Incident ID");
		default_col_headers.add("Resource");
		default_col_headers.add("Adapter");
		default_col_headers.add("Correlation ID");
		default_col_headers.add("Hostname");
		default_col_headers.add("CCID");
		sleepFor(3);
		
		int a = 0;
		
		List <String> fetched_col_headers = new ArrayList<String>();
		for (int i=0; i<default_col_headers.size(); i++)
		{
			if (default_col_headers.get(i) == col )
			{
				a = i+1;
				break;
			}	
		}		
		getElement(By.xpath("//*[@id='hrow-alert-grid']/div["+a+"]/div"),15).click();
		getElement(By.xpath("//*[@id='hrow-alert-grid']/div["+a+"]/div"),15).click();
	}

	public String Fetch_Alerts_row(String col) {

		System.out.println("\n Fetching the column value : " + col);
		List <String> default_col_headers = new ArrayList<String>();
		
		default_col_headers.add("Type");
		default_col_headers.add("Severity");
		default_col_headers.add("Timestamp");
		default_col_headers.add("Message");
		default_col_headers.add("Incident ID");
		default_col_headers.add("Resource");
		default_col_headers.add("Adapter");
		default_col_headers.add("Correlation ID");
		default_col_headers.add("Hostname");
		default_col_headers.add("CCID");
		sleepFor(3);
		
		int a = 0;
		
		List <String> fetched_col_headers = new ArrayList<String>();
		for (int i=0; i<default_col_headers.size(); i++)
		{
			if (default_col_headers.get(i) == col )
			{
				a = i+1;
				break;
			}	
		}		
		return getElement(By.xpath("//*[@id='grid-alerts']/div[2]/div[1]/div["+a+"]/div"),15).getText();
	}

	public void Click_Filter_button() {

		System.out.println("\n Clicking the filter button");
		getElement(By.xpath("//*[@class='search-category-dropdown-inputs-container']/div[2]/button"),15).click();
		sleepFor(3);
	}
	
	public void Verify_Filter_working(String col,String val) {

		List <String> default_col_headers = new ArrayList<String>();
		
		default_col_headers.add("Type");
		default_col_headers.add("Severity");
		default_col_headers.add("Timestamp");
		default_col_headers.add("Message");
		default_col_headers.add("Incident ID");
		default_col_headers.add("Resource");
		//default_col_headers.add("Adapter");
		default_col_headers.add("Correlation ID");
		default_col_headers.add("Hostname");
		default_col_headers.add("CCID");
		sleepFor(3);
		
		int a = 0;
		
		List <String> fetched_col_headers = new ArrayList<String>();
		for (int i=0; i<default_col_headers.size(); i++)
		{
			if (default_col_headers.get(i) == col )
			{
				a = i+1;
				break;
			}	
		}		

		System.out.println("\n Selecting the filter type");
		getElement(By.xpath("//*[@id='alert-view']/div[1]/div[2]/div/div[2]/div/div/div["+a+"]"),15).click();
		System.out.println("\n Input the value");
		getElement(search_box, 15).click();
		getElement(search_box, 15).clear();
		getElement(search_box, 15).sendKeys(val);
		if(a>6)
		{
			a=a+1;
		};
		String res = getElement(By.xpath("//*[@id='grid-alerts']/div[2]/div[1]/div["+a+"]/div"),15).getText();
		if (a==4){
		res = res.substring(0,50);}
		Assert.assertEquals(val,res);
		System.out.println("\n The filter is working properly for : " + col);
	}	

	public void Click_Alarms_Sort(String col) {

		System.out.println("\n Sorting the column: " + col);
		List <String> default_col_headers = new ArrayList<String>();
		
		default_col_headers.add("Type");
		default_col_headers.add("Severity");
		default_col_headers.add("Timestamp");
		default_col_headers.add("Message");
		default_col_headers.add("Incident ID");
		default_col_headers.add("Exceeds Tolerance");
		default_col_headers.add("Resource");
		default_col_headers.add("Adapter");
		default_col_headers.add("Correlation ID");
		default_col_headers.add("Hostname");
		default_col_headers.add("CCID");
		sleepFor(3);
		
		int a = 0;
		
		List <String> fetched_col_headers = new ArrayList<String>();
		for (int i=0; i<default_col_headers.size(); i++)
		{
			if (default_col_headers.get(i) == col )
			{
				a = i+1;
				break;
			}	
		}		
		getElement(By.xpath("//*[@id='hrow-alarm-grid']/div["+a+"]/div"),15).click();
		getElement(By.xpath("//*[@id='hrow-alarm-grid']/div["+a+"]/div"),15).click();
	}

	public String Fetch_Alarms_row(String col) {

		System.out.println("\n Fetching the column value : " + col);
		List <String> default_col_headers = new ArrayList<String>();
		
		default_col_headers.add("Type");
		default_col_headers.add("Severity");
		default_col_headers.add("Timestamp");
		default_col_headers.add("Message");
		default_col_headers.add("Incident ID");
		default_col_headers.add("Exceeds Tolerance");
		default_col_headers.add("Resource");
		default_col_headers.add("Adapter");
		default_col_headers.add("Correlation ID");
		default_col_headers.add("Hostname");
		default_col_headers.add("CCID");
		sleepFor(3);
		
		int a = 0;
		
		List <String> fetched_col_headers = new ArrayList<String>();
		for (int i=0; i<default_col_headers.size(); i++)
		{
			if (default_col_headers.get(i) == col )
			{
				a = i+1;
				break;
			}	
		}		
		return getElement(By.xpath("//*[@id='grid-alarms']/div[2]/div[1]/div["+a+"]/div"),15).getText();
	}

	
	public void Verify_Filter_working_Alarm(String col,String val) {

		List <String> default_col_headers = new ArrayList<String>();
		
		default_col_headers.add("Type");
		default_col_headers.add("Severity");
		default_col_headers.add("Timestamp");
		default_col_headers.add("Message");
		default_col_headers.add("Incident ID");
		default_col_headers.add("Resource");
		default_col_headers.add("Correlation ID");
		default_col_headers.add("Hostname");
		default_col_headers.add("CCID");
		sleepFor(3);
		
		int a = 0;
		
		List <String> fetched_col_headers = new ArrayList<String>();
		for (int i=0; i<default_col_headers.size(); i++)
		{
			if (default_col_headers.get(i) == col )
			{
				a = i+1;
				break;
			}	
		}		

		System.out.println("\n Selecting the filter type");
		getElement(By.xpath("//*[@id='alarm-view']/div[1]/div[2]/div/div[2]/div/div/div["+a+"]"),15).click();
		System.out.println("\n Input the value");
		getElement(search_box_alarm, 15).click();
		getElement(search_box_alarm, 15).clear();
		getElement(search_box_alarm, 15).sendKeys(val);
		if(a>5)
		{
			a=a+1;
		};
		if(a>7)
		{
			a=a+1;
		};
		String res = getElement(By.xpath("//*[@id='grid-alarms']/div[2]/div[1]/div["+a+"]/div"),15).getText();
		if (a==4){
		res = res.substring(0,50);}
		Assert.assertEquals(val,res);
		System.out.println("\n The filter is working properly for : " + col);
	}	
	
	public void Click_Next_button() {		
		System.out.println("\n Clicking the next button ");
		sleepFor(3);
		getElement(btn_alarm_next, 15).click();		
	}	
	
	public void Click_First_button() {		
		System.out.println("\n Clicking the first button ");
		sleepFor(3);
		getElement(btn_alarm_first, 15).click();		
	}
	
	public void Click_Last_button() {		
		System.out.println("\n Clicking the last button ");
		sleepFor(3);
		getElement(btn_alarm_last, 15).click();		
	}
	
	public void Click_Previous_button() {		
		System.out.println("\n Clicking the previous button ");
		sleepFor(3);
		getElement(btn_alarm_previous, 15).click();		
	}

	public void Verify_Navigation(String num) {
		sleepFor(3);
		String fetched_num = getElement(page_number, 15).getAttribute("value");	
		System.out.println("\n Fetched Number: "+fetched_num);
		Assert.assertEquals(num,fetched_num);
	}

	public void Click_Redis_Status() {
		// Selecting the server health
		System.out.println("\n Selecting the Redis Status option");
		sleepFor(3);
		getElement(option_Redis_Status, 15).click();
	}
	
	public void Verify_Rows_Redis_Status_Page() {
		
		System.out.println("\n The rows would be displayed as : Server Info | Redis Clients | Memory Usage | Redis Persistence | CPU Usage | Misc. Stats | GES Stats | Redis Command Stats | Redis Keys Info");
		List <String> default_col_headers = new ArrayList<String>();
		
		default_col_headers.add("Server Info");
		default_col_headers.add("Redis Clients");
		default_col_headers.add("Memory Usage");
		default_col_headers.add("Redis Persistence");
		default_col_headers.add("CPU Usage");
		default_col_headers.add("Misc. Stats");
		default_col_headers.add("GES Stats");
		default_col_headers.add("Redis Command Stats");
		default_col_headers.add("Redis Keys Info");

		int m = countOfElements(By.xpath("//*[@id='redis-side-bar-container']/ul/li"));
		System.out.println("\n There are : "+m+ " rows in the page");
		
		List <String> fetched_col_headers = new ArrayList<String>();
		for (int i=1; i<=m; i++)
		{
			sleepFor(1);
			fetched_col_headers.add(getElement(By.xpath("//*[@id='redis-side-bar-container']/ul/li["+i+"]"), 15).getText());			
		}
		
		System.out.println("\n The row headers fetched from the UI is : "+fetched_col_headers);
		
		Assert.assertEquals(fetched_col_headers,default_col_headers);
		
		System.out.println("\n The row headers are present in the page and verified successfully");
	}
	
	public String Capture_lastupdated_redis() {
		
		System.out.println("\n Capturing the uptime in secs");
		sleepFor(3);
		String a = getElement(By.xpath("//*[@id='redis-grid-header']/div[2]/p"),15).getText();
		System.out.println("\n The time captured is : "+a);
		return a;
	}
	
	public void Click_Refresh_Button_redis() {
		// Selecting refresh button
		System.out.println("\n Selecting the Refresh Button");
		sleepFor(3);
		getElement(btn_refresh_redis, 15).click();
		sleepFor(6);
	}

	public int  Verify_Rows_Redis_Status_Page(String val) {	
		getElement(input_redis, 15).click();
		sleepFor(3);
		getElement(input_redis, 15).sendKeys(val);
		sleepFor(15);
		int m = countOfElements(By.xpath("//*[@id='grid-redis-stats']/div[2]/div"));
		System.out.println("\n There are : "+m+ " rows in the page");
		return m;
	}

	public void Click_Redis_Clients() {
		System.out.println("\n Clicking the redis Clients tab ");
		sleepFor(3);
		getElement(option_redis_clients, 15).click();	
	}
		
	public void Click_Memory_Usage() {
		System.out.println("\n Clicking the memory usage tab ");
		sleepFor(3);
		getElement(option_memory_usage, 15).click();	
	}
	
	public void Click_Redis_Persistence() {
		System.out.println("\n Clicking the Redis Persistence tab ");
		sleepFor(3);
		getElement(option_redis_persistence, 15).click();	
	}
	
	public void Click_CPU_Usage() {
		System.out.println("\n Clicking the Cpu Usage tab ");
		sleepFor(3);
		getElement(option_cpu_usage, 15).click();	
	}
	
	public void Click_Misc_Stats() {
		System.out.println("\n Clicking the Misc. Stats tab ");
		sleepFor(3);
		getElement(option_misc_stats, 15).click();	
	}
	
	public void Click_GES_Stats() {
		System.out.println("\n Clicking the GES Stats tab ");
		sleepFor(3);
		getElement(option_ges_stats, 15).click();	
	}
	
	public void Click_Redis_Command_Stats() {
		System.out.println("\n Clicking the Redis Command Stats tab ");
		sleepFor(3);
		getElement(option_redis_command_stats, 15).click();	
	}
	
	public void Click_Redis_Keys_Info() {
		System.out.println("\n Clicking the Redis Keys Info tab ");
		sleepFor(3);
		getElement(option_redis_keys_info, 15).click();	
	}
		
	public void Verify_entries_Redis_Status_Server_Info_Page() {
		
		System.out.println("\n The entries to be displayed are : arch_bits | config_file | executable | gcc_version | hz | lru_clock | multiplexing_api | os | process_id | redis_build_id | redis_git_dirty | redis_git_sha1 | redis_mode | redis_version | run_id | tcp_port | uptime_in_days | uptime_in_seconds");
		List <String> default_col_headers = new ArrayList<String>();
		
		default_col_headers.add("arch_bits");
		default_col_headers.add("config_file");
		default_col_headers.add("executable");
		default_col_headers.add("gcc_version");
		default_col_headers.add("hz");
		default_col_headers.add("lru_clock");
		default_col_headers.add("multiplexing_api");
		default_col_headers.add("os");
		default_col_headers.add("process_id");
		default_col_headers.add("redis_build_id");
		default_col_headers.add("redis_git_dirty");
		default_col_headers.add("redis_git_sha1");
		default_col_headers.add("redis_mode");
		default_col_headers.add("redis_version");
		default_col_headers.add("run_id");
		default_col_headers.add("tcp_port");
		default_col_headers.add("uptime_in_days");
		default_col_headers.add("uptime_in_seconds");

		getElement(input_redis, 15).click();
		sleepFor(3);
		int m = countOfElements(By.xpath("//*[@id='grid-redis-stats']/div[2]/div"));
		System.out.println("\n There are : "+m+ " headers in the page");
		
		List <String> fetched_col_headers = new ArrayList<String>();
		for (int i=1; i<=m; i++)
		{
			fetched_col_headers.add(getElement(By.xpath("//*[@id='grid-redis-stats']/div[2]/div["+i+"]/div[1]/div"), 15).getText());
		}
		
		System.out.println("\n The entries fetched from the UI are : "+fetched_col_headers);
		
		Assert.assertEquals(fetched_col_headers,default_col_headers);
		
		System.out.println("\n The entries are present in the page and verified successfully");
	}
	
	public void Verify_entries_Redis_Status_Redis_Clients_Page() {
		
		System.out.println("\n The entries to be displayed are : blocked_clients | client_biggest_input_buf | client_longest_output_list | connected_clients");
		List <String> default_col_headers = new ArrayList<String>();
		
		default_col_headers.add("blocked_clients");
		default_col_headers.add("client_biggest_input_buf");
		default_col_headers.add("client_longest_output_list");
		default_col_headers.add("connected_clients");

		getElement(input_redis, 15).click();
		sleepFor(3);
		int m = countOfElements(By.xpath("//*[@id='grid-redis-stats']/div[2]/div"));
		System.out.println("\n There are : "+m+ " headers in the page");
		
		List <String> fetched_col_headers = new ArrayList<String>();
		for (int i=1; i<=m; i++)
		{
			fetched_col_headers.add(getElement(By.xpath("//*[@id='grid-redis-stats']/div[2]/div["+i+"]/div[1]/div"), 15).getText());
		}
		
		System.out.println("\n The entries fetched from the UI are : "+fetched_col_headers);
		
		Assert.assertEquals(fetched_col_headers,default_col_headers);
		
		System.out.println("\n The entries are present in the page and verified successfully");
	}
	
	public void Verify_entries_Redis_Status_Memory_Usage_Page() {
		
		System.out.println("\n The entries to be displayed are : maxmemory | maxmemory_human | maxmemory_policy | mem_allocator | mem_fragmentation_ratio | used_memory | used_memory_human | used_memory_lua | used_memory_lua_human | used_memory_peak | used_memory_peak_human | used_memory_rss | used_memory_rss_human");
		List <String> default_col_headers = new ArrayList<String>();
		
		default_col_headers.add("maxmemory");
		default_col_headers.add("maxmemory_human");
		default_col_headers.add("maxmemory_policy");
		default_col_headers.add("mem_allocator");
		default_col_headers.add("mem_fragmentation_ratio");
		default_col_headers.add("used_memory");
		default_col_headers.add("used_memory_human");		
		default_col_headers.add("used_memory_lua");
		default_col_headers.add("used_memory_lua_human");
		default_col_headers.add("used_memory_peak");
		default_col_headers.add("used_memory_peak_human");
		default_col_headers.add("used_memory_rss");
		default_col_headers.add("used_memory_rss_human");

		getElement(input_redis, 15).click();
		sleepFor(3);
		int m = countOfElements(By.xpath("//*[@id='grid-redis-stats']/div[2]/div"));
		System.out.println("\n There are : "+m+ " headers in the page");
		
		List <String> fetched_col_headers = new ArrayList<String>();
		for (int i=1; i<=m; i++)
		{
			fetched_col_headers.add(getElement(By.xpath("//*[@id='grid-redis-stats']/div[2]/div["+i+"]/div[1]/div"), 15).getText());
		}
		
		System.out.println("\n The entries fetched from the UI are : "+fetched_col_headers);
		
		Assert.assertEquals(fetched_col_headers,default_col_headers);
		
		System.out.println("\n The entries are present in the page and verified successfully");
	}
	
	public void Verify_entries_Redis_Status_Redis_Persistence_Page() {
		
		System.out.println("\n The entries to be displayed are : aof_current_rewrite_time_sec | aof_enabled | aof_last_bgrewrite_status | aof_last_rewrite_time_sec | aof_last_write_status | aof_rewrite_in_progress | aof_rewrite_scheduled | loading | rdb_bgsave_in_progress | rdb_changes_since_last_save | rdb_current_bgsave_time_sec | rdb_last_bgsave_status | rdb_last_bgsave_time_sec | rdb_last_save_time");
		List <String> default_col_headers = new ArrayList<String>();
		
		default_col_headers.add("aof_current_rewrite_time_sec");
		default_col_headers.add("aof_enabled");
		default_col_headers.add("aof_last_bgrewrite_status");
		default_col_headers.add("aof_last_rewrite_time_sec");
		default_col_headers.add("aof_last_write_status");
		default_col_headers.add("aof_rewrite_in_progress");
		default_col_headers.add("aof_rewrite_scheduled");
		default_col_headers.add("loading");
		default_col_headers.add("rdb_bgsave_in_progress");
		default_col_headers.add("rdb_changes_since_last_save");
		default_col_headers.add("rdb_current_bgsave_time_sec");
		default_col_headers.add("rdb_last_bgsave_status");
		default_col_headers.add("rdb_last_bgsave_time_sec");
		default_col_headers.add("rdb_last_save_time");
		
		getElement(input_redis, 15).click();
		sleepFor(3);
		int m = countOfElements(By.xpath("//*[@id='grid-redis-stats']/div[2]/div"));
		System.out.println("\n There are : "+m+ " headers in the page");
		
		List <String> fetched_col_headers = new ArrayList<String>();
		for (int i=1; i<=m; i++)
		{
			fetched_col_headers.add(getElement(By.xpath("//*[@id='grid-redis-stats']/div[2]/div["+i+"]/div[1]/div"), 15).getText());
		}
		
		System.out.println("\n The entries fetched from the UI are : "+fetched_col_headers);
		
		Assert.assertEquals(fetched_col_headers,default_col_headers);
		
		System.out.println("\n The entries are present in the page and verified successfully");
	}
	
	public void Verify_entries_Redis_Status_CPU_Usage_Page() {
		
		System.out.println("\n The entries to be displayed are : used_cpu_sys | used_cpu_sys_children | used_cpu_user | used_cpu_user_children");
		List <String> default_col_headers = new ArrayList<String>();
		
		default_col_headers.add("used_cpu_sys");
		default_col_headers.add("used_cpu_sys_children");
		default_col_headers.add("used_cpu_user");
		default_col_headers.add("used_cpu_user_children");

		getElement(input_redis, 15).click();
		sleepFor(3);
		int m = countOfElements(By.xpath("//*[@id='grid-redis-stats']/div[2]/div"));
		System.out.println("\n There are : "+m+ " headers in the page");
		
		List <String> fetched_col_headers = new ArrayList<String>();
		for (int i=1; i<=m; i++)
		{
			fetched_col_headers.add(getElement(By.xpath("//*[@id='grid-redis-stats']/div[2]/div["+i+"]/div[1]/div"), 15).getText());
		}
		
		System.out.println("\n The entries fetched from the UI are : "+fetched_col_headers);
		
		Assert.assertEquals(fetched_col_headers,default_col_headers);
		
		System.out.println("\n The entries are present in the page and verified successfully");
	}
	
	public void Verify_entries_Redis_Status_Misc_Stats_Page() {
		
		System.out.println("\n The entries to be displayed are : evicted_keys | expired_keys | instantaneous_input_kbps | instantaneous_ops_per_sec | instantaneous_output_kbps | keyspace_hits | keyspace_misses | latest_fork_usec | migrate_cached_sockets | pubsub_channels | pubsub_patterns | rejected_connections | sync_full | sync_partial_err | sync_partial_ok | total_commands_processed | total_connections_received | total_net_input_bytes | total_net_output_bytes");
		List <String> default_col_headers = new ArrayList<String>();
		
		default_col_headers.add("evicted_keys");
		default_col_headers.add("expired_keys");
		default_col_headers.add("instantaneous_input_kbps");
		default_col_headers.add("instantaneous_ops_per_sec");
		default_col_headers.add("instantaneous_output_kbps");
		default_col_headers.add("keyspace_hits");
		default_col_headers.add("keyspace_misses");
		default_col_headers.add("latest_fork_usec");
		default_col_headers.add("migrate_cached_sockets");
		default_col_headers.add("pubsub_channels");
		default_col_headers.add("pubsub_patterns");
		default_col_headers.add("rejected_connections");
		default_col_headers.add("sync_full");
		default_col_headers.add("sync_partial_err");
		default_col_headers.add("sync_partial_ok");
		default_col_headers.add("total_commands_processed");
		default_col_headers.add("total_connections_received");
		default_col_headers.add("total_net_input_bytes");
		default_col_headers.add("total_net_output_bytes");

		getElement(input_redis, 15).click();
		sleepFor(3);
		int m = countOfElements(By.xpath("//*[@id='grid-redis-stats']/div[2]/div"));
		System.out.println("\n There are : "+m+ " headers in the page");
		
		List <String> fetched_col_headers = new ArrayList<String>();
		for (int i=1; i<=m; i++)
		{
			fetched_col_headers.add(getElement(By.xpath("//*[@id='grid-redis-stats']/div[2]/div["+i+"]/div[1]/div"), 15).getText());
		}
		
		System.out.println("\n The entries fetched from the UI are : "+fetched_col_headers);
		
		Assert.assertEquals(fetched_col_headers,default_col_headers);
		
		System.out.println("\n The entries are present in the page and verified successfully");
	}
	
	public void Verify_entries_Redis_Status_GES_Stats_Page() {
		
		System.out.println("\n The entries to be displayed are : activeMonitorSize | activePingSize | totalMonitorSize | totalPingSize");
		List <String> default_col_headers = new ArrayList<String>();
		
		default_col_headers.add("activeMonitorSize");
		default_col_headers.add("activePingSize");
		default_col_headers.add("totalMonitorSize");
		default_col_headers.add("totalPingSize");

		getElement(input_redis, 15).click();
		sleepFor(3);
		int m = countOfElements(By.xpath("//*[@id='grid-redis-stats']/div[2]/div"));
		System.out.println("\n There are : "+m+ " headers in the page");
		
		List <String> fetched_col_headers = new ArrayList<String>();
		for (int i=1; i<=m; i++)
		{
			fetched_col_headers.add(getElement(By.xpath("//*[@id='grid-redis-stats']/div[2]/div["+i+"]/div[1]/div"), 15).getText());
		}
		
		System.out.println("\n The entries fetched from the UI are : "+fetched_col_headers);
		
		Assert.assertEquals(fetched_col_headers,default_col_headers);
		
		System.out.println("\n The entries are present in the page and verified successfully");
	}
	
	public void Verify_entries_Redis_Status_Redis_Command_Stats_Page() {
		
		System.out.println("\n The entries to be displayed are : cmdstat_brpop | cmdstat_client | cmdstat_command | cmdstat_config | cmdstat_del | cmdstat_eval | cmdstat_evalsha | cmdstat_exec | cmdstat_exists | cmdstat_expire | cmdstat_expireat | cmdstat_get | cmdstat_hget | cmdstat_hgetall | cmdstat_hlen | cmdstat_hmget | cmdstat_hmset | cmdstat_hset | cmdstat_info | cmdstat_keys | cmdstat_latency | cmdstat_llen | cmdstat_lpush | cmdstat_lrange | cmdstat_multi | cmdstat_ping | cmdstat_psync | cmdstat_publish | cmdstat_replconf | cmdstat_rpop | cmdstat_rpush | cmdstat_sadd | cmdstat_script | cmdstat_set | cmdstat_smembers | cmdstat_subscribe | cmdstat_zadd | cmdstat_zcard | cmdstat_zcount | cmdstat_zrangebyscore | cmdstat_zrank | cmdstat_zrem | cmdstat_zremrangebyrank | cmdstat_zremrangebyscore | cmdstat_zrevrangebyscore | cmdstat_zscore");
		List <String> default_col_headers = new ArrayList<String>();
		default_col_headers.add("cmdstat_brpop");
		default_col_headers.add("cmdstat_client");
		default_col_headers.add("cmdstat_command");
		default_col_headers.add("cmdstat_config");
		default_col_headers.add("cmdstat_del");
		default_col_headers.add("cmdstat_eval");
		default_col_headers.add("cmdstat_evalsha");
		default_col_headers.add("cmdstat_exec");
		default_col_headers.add("cmdstat_exists");
		default_col_headers.add("cmdstat_expire");
		default_col_headers.add("cmdstat_expireat");
		default_col_headers.add("cmdstat_get");
		default_col_headers.add("cmdstat_hget");
		default_col_headers.add("cmdstat_hgetall");
		default_col_headers.add("cmdstat_hlen");
		default_col_headers.add("cmdstat_hmget");
		default_col_headers.add("cmdstat_hmset");
		default_col_headers.add("cmdstat_hset");
		default_col_headers.add("cmdstat_info");
		default_col_headers.add("cmdstat_keys");
		default_col_headers.add("cmdstat_latency");
		default_col_headers.add("cmdstat_llen");
		default_col_headers.add("cmdstat_lpush");
		default_col_headers.add("cmdstat_lrange");
		default_col_headers.add("cmdstat_multi");
		default_col_headers.add("cmdstat_ping");
		default_col_headers.add("cmdstat_psync");
		default_col_headers.add("cmdstat_publish");
		default_col_headers.add("cmdstat_replconf");
		default_col_headers.add("cmdstat_rpop");
		default_col_headers.add("cmdstat_rpush");
		default_col_headers.add("cmdstat_sadd");
		default_col_headers.add("cmdstat_script");
		default_col_headers.add("cmdstat_select");
		default_col_headers.add("cmdstat_set");
		default_col_headers.add("cmdstat_sismember");
		default_col_headers.add("cmdstat_slaveof");
		default_col_headers.add("cmdstat_smembers");
		default_col_headers.add("cmdstat_subscribe");
		default_col_headers.add("cmdstat_zadd");
		default_col_headers.add("cmdstat_zcard");
		default_col_headers.add("cmdstat_zcount");		
		default_col_headers.add("cmdstat_zrange");
		default_col_headers.add("cmdstat_zrangebyscore");
		default_col_headers.add("cmdstat_zrank");
		default_col_headers.add("cmdstat_zrem");
		default_col_headers.add("cmdstat_zremrangebyrank");
		default_col_headers.add("cmdstat_zremrangebyscore");
		default_col_headers.add("cmdstat_zrevrangebyscore");
		default_col_headers.add("cmdstat_zscore");
		
		getElement(input_redis, 15).click();
		sleepFor(3);
		int m = countOfElements(By.xpath("//*[@id='grid-redis-stats']/div[2]/div"));
		System.out.println("\n There are : "+m+ " headers in the page");
		
		List <String> fetched_col_headers = new ArrayList<String>();
		for (int i=1; i<=m; i++)
		{
			fetched_col_headers.add(getElement(By.xpath("//*[@id='grid-redis-stats']/div[2]/div["+i+"]/div[1]/div"), 15).getText());
		}
		
		System.out.println("\n The entries fetched from the UI are : "+fetched_col_headers);
		
		Assert.assertEquals(fetched_col_headers,default_col_headers);
		
		System.out.println("\n The entries are present in the page and verified successfully");
	}
	
	public void Verify_entries_Redis_Status_Redis_Keys_Info_Page() {
		
		System.out.println("\n The entries to be displayed are : db0");
		List <String> default_col_headers = new ArrayList<String>();
		
		default_col_headers.add("db0");

		getElement(input_redis, 15).click();
		sleepFor(3);
		int m = countOfElements(By.xpath("//*[@id='grid-redis-stats']/div[2]/div"));
		System.out.println("\n There are : "+m+ " headers in the page");
		
		List <String> fetched_col_headers = new ArrayList<String>();
		for (int i=1; i<=m; i++)
		{
			fetched_col_headers.add(getElement(By.xpath("//*[@id='grid-redis-stats']/div[2]/div["+i+"]/div[1]/div"), 15).getText());
		}
		
		System.out.println("\n The entries fetched from the UI are : "+fetched_col_headers);
		
		Assert.assertEquals(fetched_col_headers,default_col_headers);
		
		System.out.println("\n The entries are present in the page and verified successfully");
	}
	
	public void Click_Tenant_Config() {
		// Selecting the server health
		System.out.println("\n Selecting the Tenant Configuration option");
		sleepFor(3);
		getElement(option_Tenant_Config, 15).click();
	}
	
	public void Click_Service_Config() {
		// Selecting the server health
		System.out.println("\n Selecting the Service Configuration option");
		sleepFor(3);
		getElement(option_Service_Config, 15).click();
	}


	public void Click_Tenant_Selection() {
		System.out.println("\n Selecting the Tenant");
		sleepFor(3);
		getElement(option_select_tenant, 15).click();
		getElement(option_2113_tenant, 15).click();		
	}
	
	public void Click_Tenant_configuration() {
		System.out.println("\n Selecting the Tenant Configuration option");
		sleepFor(3);
		getElement(option_Tenant_Configuration, 15).click();
		
	}
	
	public void Click_ORS_URS_Status() {
		System.out.println("\n Selecting the ORS URS Status page");
		sleepFor(3);
		getElement(option_ORS_URS_Status, 15).click();		
	}	

	public void Verify_tabs_Tenant_Configuration_page() {
		System.out.println("\n The entries to be displayed are : Tenant Configuration | Service Configuration | ORS/URS Status");
		List <String> default_col_headers = new ArrayList<String>();
		
		default_col_headers.add("Tenant Configuration");
		default_col_headers.add("Service Configuration");
		default_col_headers.add("ORS/URS Status");

		int m = countOfElements(By.xpath("//*[@id='tenants-side-bar-container']/ul/li"));
		System.out.println("\n There are : "+m+ " headers in the page");
		
		List <String> fetched_col_headers = new ArrayList<String>();
		for (int i=1; i<=m; i++)
		{
			fetched_col_headers.add(getElement(By.xpath("//*[@id='tenants-side-bar-container']/ul/li["+i+"]"), 15).getText());
		}
		
		System.out.println("\n The entries fetched from the UI are : "+fetched_col_headers);
		
		Assert.assertEquals(fetched_col_headers,default_col_headers);
		
		System.out.println("\n The entries are present in the page and verified successfully");
	}

	public String fetch_service_properties(String header) {
		
		Dictionary<String, String> properties = new Hashtable<String, String>();
		String temp = "";
		
		int m = countOfElements(By.xpath("//*[@id='grid-service-config']/div[2]/div"));
		System.out.println("\n There are : "+m+ " entries in the page");
		
		for (int i=1; i<=m; i++)
		{	
			properties.put(getElement(By.xpath("//*[@id='grid-service-config']/div[2]/div["+i+"]/div[1]/div"), 15).getText(), getElement(By.xpath("//*[@id='grid-service-config']/div[2]/div["+i+"]/div[2]/div"), 15).getText());
		}
		for (Enumeration<String> j = properties.keys(); j.hasMoreElements();)
        {
			temp = j.nextElement();
            //System.out.println(temp + " : " + properties.get(temp));
            if (temp.equalsIgnoreCase(header)) {
            	break;
            }
        }
		return properties.get(temp);
		
		
		//Assert.assertEquals(fetched_col_headers,default_col_headers);
		
		//System.out.println("\n The entries are present in the page and verified successfully");
		
	}
	
	public void Verify_service_properties() {
		System.out.println("\n The entries to be displayed are : _attach_udata | _business_hours_service | _call_direction | _call_display_name | _call_display_number | _callback_purge_time | _cpd_enable | _cpd_timeguard | _designer_workspace | _dial_retry_timeout | _enable_in_queue_checking | _enable_reject_out_of_office_hours | _enable_throttle_header_parameters | _hold_enabled | _immediate_blackout | _immediate_enabled | _logged_in_check | _max_dial_attempts | _max_request_by_time_bucket, _min_time_before_callback, _ors, _prefix_dial_out, _push_notification_threshold, _pushed_callback_expiry_time, | _request_time_bucket | _route_point | _scheduled_enabled | _service | _service_type | _snooze_duration | _stream | _target | _throttle_request_parameters | _throttle_request_parameters_limit | _ttl | _type | _vq");
		
		List <String> default_col_headers = new ArrayList<String>();
		default_col_headers.add("_attach_udata");
		default_col_headers.add("_business_hours_service");
		default_col_headers.add("_call_direction");
		default_col_headers.add("_call_display_name");
		default_col_headers.add("_call_display_number");
		default_col_headers.add("_callback_purge_time");
		default_col_headers.add("_cpd_enable");
		default_col_headers.add("_cpd_timeguard");
		default_col_headers.add("_designer_workspace");
		default_col_headers.add("_dial_retry_timeout");
		default_col_headers.add("_enable_in_queue_checking");
		//default_col_headers.add("_enable_reject_out_of_office_hours");
		//default_col_headers.add("_enable_throttle_header_parameters");
		default_col_headers.add("_hold_enabled");
		default_col_headers.add("_immediate_blackout");
		default_col_headers.add("_immediate_enabled");
		default_col_headers.add("_logged_in_check");
		default_col_headers.add("_max_dial_attempts");
		default_col_headers.add("_max_request_by_time_bucket");
		default_col_headers.add("_min_time_before_callback");
		default_col_headers.add("_overwritable_options");
		default_col_headers.add("_prefix_dial_out");
		default_col_headers.add("_push_notification_threshold");
		default_col_headers.add("_pushed_callback_expiry_time");
		default_col_headers.add("_request_time_bucket");
		default_col_headers.add("_route_point");
		default_col_headers.add("_scheduled_enabled");
		default_col_headers.add("_service");
		default_col_headers.add("_service_type");
		default_col_headers.add("_snooze_duration");
		default_col_headers.add("_stream");
		default_col_headers.add("_target");
		default_col_headers.add("_throttle_request_parameters");
		default_col_headers.add("_throttle_request_parameters_limit");
		default_col_headers.add("_ttl");
		default_col_headers.add("_type");
		default_col_headers.add("_vq");
		default_col_headers.add("testdatetimerange");
		
		Select_Service();
		int m = countOfElements(By.xpath("//*[@id='grid-service-config']/div[2]/div"));
		System.out.println("\n There are : "+m+ " entries in the page");
		
		List <String> fetched_col_headers = new ArrayList<String>();
		for (int i=1; i<=m; i++)
		{
			fetched_col_headers.add(getElement(By.xpath("//*[@id='grid-service-config']/div[2]/div["+i+"]/div[1]/div"), 15).getText());
		}
		
		System.out.println("\n The entries fetched from the UI are : "+fetched_col_headers);
		
		Assert.assertEquals(fetched_col_headers,default_col_headers);
		
		System.out.println("\n The entries are present in the page and verified successfully");
		
	}
	
	public void Select_Service(String... service) 
	{
		getElement(option_Service_Select, 15).click();
		getElement(option_Service_Select, 15).clear();
		sleepFor(3);
		System.out.println("\n Input the value");
		String servicename = (service.length >= 1) ? service[0] : "service.GES_UI_AUTO_VQ";
		getElement(option_Service_Select, 15).sendKeys(servicename);
		sleepFor(3);
	}

	public int Verify_Search_Select_Service() 
	{
		System.out.println("\n Input the prperty");
		sleepFor(3);
		//getElement(option_Service_Select_property, 15).click();
		getElement(option_Service_Select_property, 15).clear();
		getElement(option_Service_Select_property, 15).sendKeys("_call_direction");		
		int m = countOfElements(By.xpath("//*[@id='grid-service-config']/div[2]/div"));
		System.out.println("\n There are : "+m+ " rows in the page");
		return m;
	}

	public void Verify_column_names_status() {
		System.out.println("\n The entries to be displayed are : Name | Status | Type | Role | Last Response Timestamp");
		List <String> default_col_headers = new ArrayList<String>();
		
		default_col_headers.add("Name");
		default_col_headers.add("Status");
		default_col_headers.add("Type");
		default_col_headers.add("Role");
		default_col_headers.add("Last Response Timestamp");

		int m = countOfElements(By.xpath("//*[@id='grid-server-status']/div[1]/div"));
		System.out.println("\n There are : "+m+ " headers in the page");
		
		List <String> fetched_col_headers = new ArrayList<String>();
		for (int i=1; i<=m; i++)
		{
			fetched_col_headers.add(getElement(By.xpath("//*[@id='grid-server-status']/div[1]/div["+i+"]/div"), 15).getText());
		}
		
		System.out.println("\n The entries fetched from the UI are : "+fetched_col_headers);
		
		Assert.assertEquals(fetched_col_headers,default_col_headers);
		
		System.out.println("\n The column names present in the page are verified successfully");
		
	}

	public void Verify_entries_ORS_URS_Page() {
		System.out.println("\n The entries to be displayed are : usw1gvm-2059-001.usw1.g1.genhtcc.com:9098 | usw1gvm-2059-002.usw1.g1.genhtcc.com:9098 | usw1gvm-2059-001.usw1.g1.genhtcc.com:3072 | usw1gvm-2059-002.usw1.g1.genhtcc.com:3072");
		List <String> default_col_headers = new ArrayList<String>();
		
		default_col_headers.add("usw1scl-2113-001.usw1.g1.genhtcc.com:9098");
		default_col_headers.add("usw1scl-2113-002.usw1.g1.genhtcc.com:9098");
		default_col_headers.add("usw1scl-2113-001.usw1.g1.genhtcc.com:3072");
		default_col_headers.add("usw1scl-2113-002.usw1.g1.genhtcc.com:3072");
		sleepFor(3);
		int m = countOfElements(By.xpath("//*[@id='grid-server-status']/div[2]/div"));
		System.out.println("\n There are : "+m+ " headers in the page");
		
		List <String> fetched_col_headers = new ArrayList<String>();
		for (int i=1; i<=m; i++)
		{
			fetched_col_headers.add(getElement(By.xpath("//*[@id='grid-server-status']/div[2]/div["+i+"]/div[1]/div"), 15).getText());
		}
		
		System.out.println("\n The entries fetched from the UI are : "+fetched_col_headers);
		
		Assert.assertEquals(fetched_col_headers,default_col_headers);
		
		System.out.println("\n The column names present in the page are verified successfully");	
	}
	
	public void Click_ORS_entry() {
		System.out.println("\n Clicking ORS server ");
		getElement(By.xpath("//*[@id='grid-server-status']/div[2]/div[1]/div[2]"), 15).click();
		sleepFor(3);
	}

	public void Verify_message_ORS_URS_Page() {
		System.out.println("\n Verifying message");
		String default_text = "Server Status Reason";
		String obtained_text = getElement(By.xpath("//*[@id='container-server-status']/div[3]/div/p"), 15).getText();
		obtained_text = obtained_text.substring(0,20);
		Assert.assertEquals(obtained_text,default_text);
		System.out.println("\n The last response message is present and verified successfully");	
	}
	
	public void Click_Advanced_login() {
		// Selecting the server health
		System.out.println("\n Selecting the Advanced option");
		sleepFor(3);
		getElement(option_Adv_login, 15).click();
	}
	
	public void Click_Logging() {
		// Selecting the server health
		System.out.println("\n Selecting the Logging option");
		sleepFor(3);
		getElement(option_logging, 15).click();
	}
	
	public void Enter_username(String user) {
		sleepFor(2);
		System.out.println("\n Entering the username");
		getElement(Adv_Username, 15).sendKeys(user);
	}
	
	public void Enter_password(String user) {
		sleepFor(2);
		System.out.println("\n Entering the password");
		getElement(Adv_Password, 15).sendKeys(user);
	}
	
	public void Click_Login_button() {
		sleepFor(2);
		System.out.println("\n Clicking the login button");
		getElement(Adv_login, 15).click();
	}
	
	public void Verify_Advanced_Privileges() {
		sleepFor(2);
		//System.out.println("\n Clicking the update button");
		String To_be_displayed = "User is logged in with advanced credentials";
		String Actually_displayed = getElement(By.xpath("//*[@id='view-devops']/div/div[2]"), 15).getText();
		Assert.assertEquals(To_be_displayed,Actually_displayed);
		System.out.println("\n Testcase is passed");
	}
	
	public void Verify_Logging_Page_UI(String level) {
		sleepFor(2);
		//System.out.println("\n Clicking the update button");
		String To_be_displayed = "Current log level is "+level;
		String Actually_displayed="";
		if(level==""){
		Actually_displayed = getElement(By.xpath("//*[@id='log-level']"), 15).getText().substring(0,21);
		}
		else{
		Actually_displayed = getElement(By.xpath("//*[@id='log-level']"), 15).getText();
		}
		//System.out.println(Actually_displayed);
		Assert.assertEquals(To_be_displayed,Actually_displayed);
		System.out.println("\n Testcase is passed");
	}
	
	public String Get_Actual_Log_Level() {
		sleepFor(2);
		String To_be_displayed = "Current log level is ";
		String Log_Level = getElement(By.xpath("//*[@id='log-level']"), 15).getText();
		String [] Actual_Log_Level = Log_Level.split(To_be_displayed,2);
		for (String log_lvl : Actual_Log_Level)
			Log_Level = log_lvl;
		System.out.println("\n Current log level is "+Log_Level);
		return Log_Level;
	}

	public void Verify_error_response_invalid_access() {
		sleepFor(2);
		//System.out.println("\n Clicking the update button");
		String To_be_displayed = "Login Failed, try again";
		String Actually_displayed = getElement(By.xpath("//*[@id='main-and-toaster']/div[2]/div/ul/li/aside/div/div[2]/p"), 15).getText();
		Assert.assertEquals(To_be_displayed,Actually_displayed);
		System.out.println("\n Testcase is passed");
	}
	
	public void Change_Log_level(String level,String time) {
		sleepFor(2);
		
		System.out.println("\n Selecting the column: " + level);
		getElement(By.xpath("//select/option[text()='"+level+"']"),10).click();
		if(time!="0"){
			getElement(duration_input,10).sendKeys(time);			
		}
		getElement(update_logging,10).click();
		//Assert.assertEquals(To_be_displayed,Actually_displayed);
		System.out.println("\n Testcase is passed");
	}
	

	
	
	}
