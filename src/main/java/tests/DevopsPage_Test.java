package ges.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import ges.pages.*;
import ges.util.BaseTest;

public class DevopsPage_Test extends BaseTest{
	
	/****************************************************************************/
	
	LoginPage loginpage = new LoginPage();
	CallbackPage callbackpage = new CallbackPage();
	DevopsPage devops = new DevopsPage();
	public String ges_ver;
	
	/****************************************************************************/
	
	private String admin_username = testdata_property_file_reader("Admin_username");
	private String admin_password = testdata_property_file_reader("Admin_password");
	private String adv_username = "admin";
	private String adv_password = "letmein";
	private String invalid_password = "wrongpass";
		
	/****************************************************************************/
	@BeforeClass
	public void beforeClass() {
	System.out.println(
			"***************************************************************************************** \n\n\n");
	System.out.println("				Running Functional UI testcases - Devops Tab Tests");
	System.out.println(
			"\n\n\n*****************************************************************************************");
	}
		
	@Test(priority=1, enabled=true, testName = "Verify the GES Node Health - Coulmns should be displayed successfully", description = "Navigate to Server health and verify if all the columns are displayed successfully")
		public void Verify_GES_Node_Health()
		{
			try
		{
			loginpage.login_as(admin_username, admin_password);
			loginpage.verifyLogin(admin_username);
			ges_ver = loginpage.getGESVersion();
			System.out.println("\n The GES version is : "+ges_ver);
			devops.Navigate_To_DevopsTab();
			devops.Click_Server_Health();
			devops.Verify_Column_Headers_UI();
			}
		finally
		{
		callbackpage.signout();	
		}
		}
	
	@Test(priority=2, enabled=true, testName = "Verify that GES nodes are displayed successfully", description = "Nodes should be displayed for GES")
		public void Verify_GES_Nodes()
		{
			try
		{
			loginpage.login_as(admin_username, admin_password);
			loginpage.verifyLogin(admin_username);
			devops.Navigate_To_DevopsTab();
			devops.Click_Server_Health();
			devops.Fetch_number_of_nodes();
			}
		finally
		{
		callbackpage.signout();	
		}

		}
	
	@Test(priority=3, enabled=true, testName = "Verify that the Node details are fetched and displayed successfully", description = "There should be only 2 nodes. The node details should be retrieved and displayed successfully")
	public void Verify_Node_details_displayed_successfully()
	{
		try
		{
		loginpage.login_as(admin_username, admin_password);
		loginpage.verifyLogin(admin_username);
		devops.Navigate_To_DevopsTab();
		devops.Click_Server_Health();
		devops.Display_Node_Details(1);
		devops.Display_Node_Details(2);
		}
		finally
		{
		callbackpage.signout();	
		}

	}
	
	@Test(priority=4, enabled=true, testName = "Verify that uptime is changed after selecting refresh button", description = "The uptime should be changed(increased) after selecting refresh button")
	public void Verify_uptime_is_changed_on_refresh()
	{
		try
		{
		loginpage.login_as(admin_username, admin_password);
		loginpage.verifyLogin(admin_username);
		devops.Navigate_To_DevopsTab();
		devops.Click_Server_Health();
		int initial_uptime = devops.Capture_uptime();
		devops.Click_Refresh_Button();
		int latest_uptime = devops.Capture_uptime();
		if(latest_uptime > initial_uptime)
		{
			System.out.println("\n The uptime is increased after refresh and is working properly");
		}
		else
		{
			System.out.println("\n The uptime is not increased after refresh");
		}
		}
		finally
		{
		callbackpage.signout();	
		}
		
	}
	
	/*chandra
	@Test(priority=5, enabled=true, testName = "Verify the Active Alerts page - Coulmns should be displayed successfully", description = "Navigate to Active Alerts and verify if all the columns are displayed successfully")
	public void Verify_Active_Alerts_Column_Headers()
	{
		try
		{
		loginpage.login_as(admin_username, admin_password);
		loginpage.verifyLogin(admin_username);
		ges_ver = loginpage.getGESVersion();
		System.out.println("\n The GES version is : "+ges_ver);
		devops.Navigate_To_DevopsTab();
		devops.Click_Alarm_Alerts();
		devops.Verify_Column_Headers_Alerts();
		}
		finally
		{
		callbackpage.signout();	
		}
	}
	
	@Test(priority=6, enabled=true, testName = "Verify the Active Alarms page - Coulmns should be displayed successfully", description = "Navigate to Active Alarms and verify if all the columns are displayed successfully")
	public void Verify_Active_Alarms_Column_Headers()
	{
		try
		{
		loginpage.login_as(admin_username, admin_password);
		loginpage.verifyLogin(admin_username);
		ges_ver = loginpage.getGESVersion();
		System.out.println("\n The GES version is : "+ges_ver);
		devops.Navigate_To_DevopsTab();
		devops.Click_Alarm_Alerts();
		devops.Click_Active_Alarms();
		devops.Verify_Column_Headers_Alarms();
		}
		finally
		{
		callbackpage.signout();	
		}
	}
	
	@Test(priority=7, enabled=true, testName = "Verify the Alarm Types page - Coulmns should be displayed successfully", description = "Navigate to Alarm Typess and verify if all the columns are displayed successfully")
	public void Verify_Alarm_Types_Column_Headers()
	{
		try
		{
		loginpage.login_as(admin_username, admin_password);
		loginpage.verifyLogin(admin_username);
		ges_ver = loginpage.getGESVersion();
		System.out.println("\n The GES version is : "+ges_ver);
		devops.Navigate_To_DevopsTab();
		devops.Click_Alarm_Alerts();
		devops.Click_Alarm_Types();
		devops.Verify_Column_Headers_Alarm_Types();
		}
		finally
		{
		callbackpage.signout();	
		}
	}
	
	@Test(priority=8, enabled=false, testName = "Verify the 4 tabs in Alarm & Alerts page", description = "Navigate to Alarm Typess and verify if all the four tabs are present there")
	public void Verify_Tabs_in_Alarm_Alerts_page()
	{
		try
		{
		loginpage.login_as(admin_username, admin_password);
		loginpage.verifyLogin(admin_username);
		ges_ver = loginpage.getGESVersion();
		System.out.println("\n The GES version is : "+ges_ver);
		devops.Navigate_To_DevopsTab();
		devops.Click_Alarm_Alerts();
		devops.Verify_Rows_Alarm_Alerts_Page();
		}
		finally
		{
		callbackpage.signout();	
		}
	}
	

	
	@Test(priority=9, enabled=true, testName = "Verify the search in Active Alerts page - Type", description = "Navigate to Active Alerts page and verify search is working properly for 'Type' filter")
	public void Verify_Filter_in_Active_Alerts_page_Type()
	{
		try
		{
		loginpage.login_as(admin_username, admin_password);
		loginpage.verifyLogin(admin_username);
		ges_ver = loginpage.getGESVersion();
		System.out.println("\n The GES version is : "+ges_ver);
		devops.Navigate_To_DevopsTab();
		devops.Click_Alarm_Alerts();
		String col = "Type";
		devops.Click_Alerts_Sort(col);
		String val = devops.Fetch_Alerts_row(col);
		devops.Click_Filter_button();
		devops.Verify_Filter_working(col,val);
		System.out.println("\n The filter is working properly");
		}
		finally
		{
		callbackpage.signout();	
		}
	}
	
	@Test(priority=10, enabled=true, testName = "Verify the search in Active Alerts page - Severity", description = "Navigate to Active Alerts page and verify search is working properly for 'Severity' filter")
	public void Verify_Filter_in_Active_Alerts_page_Severity()
	{
		try
		{
		loginpage.login_as(admin_username, admin_password);
		loginpage.verifyLogin(admin_username);
		ges_ver = loginpage.getGESVersion();
		System.out.println("\n The GES version is : "+ges_ver);
		devops.Navigate_To_DevopsTab();
		devops.Click_Alarm_Alerts();
		String col = "Severity";
		devops.Click_Alerts_Sort(col);
		String val = devops.Fetch_Alerts_row(col);
		devops.Click_Filter_button();
		devops.Verify_Filter_working(col,val);
		System.out.println("\n The filter is working properly");
		}
		finally
		{
		callbackpage.signout();	
		}	
	}
	
	@Test(priority=11, enabled=true, testName = "Verify the search in Active Alerts page - Timestamp", description = "Navigate to Active Alerts page and verify search is working properly for 'Timestamp' filter")
	public void Verify_Filter_in_Active_Alerts_page_Timestamp()
	{
		try
		{
		loginpage.login_as(admin_username, admin_password);
		loginpage.verifyLogin(admin_username);
		ges_ver = loginpage.getGESVersion();
		System.out.println("\n The GES version is : "+ges_ver);
		devops.Navigate_To_DevopsTab();
		devops.Click_Alarm_Alerts();
		String col = "Timestamp";
		devops.Click_Alerts_Sort(col);
		String val = devops.Fetch_Alerts_row(col);
		devops.Click_Filter_button();
		devops.Verify_Filter_working(col,val);
		System.out.println("\n The filter is working properly");
		}
		finally
		{
		callbackpage.signout();	
		}
	}
	
	@Test(priority=12, enabled=true, testName = "Verify the search in Active Alerts page - Message", description = "Navigate to Active Alerts page and verify search is working properly for 'Message' filter")
	public void Verify_Filter_in_Active_Alerts_page_Message()
	{
		try
		{
		loginpage.login_as(admin_username, admin_password);
		loginpage.verifyLogin(admin_username);
		ges_ver = loginpage.getGESVersion();
		System.out.println("\n The GES version is : "+ges_ver);
		devops.Navigate_To_DevopsTab();
		devops.Click_Alarm_Alerts();
		String col = "Message";
		devops.Click_Alerts_Sort(col);
		String val = devops.Fetch_Alerts_row(col);
		String res = val.substring(0,50);
		devops.Click_Filter_button();
		devops.Verify_Filter_working(col,res);
		System.out.println("\n The filter is working properly");
		}
		finally
		{
		callbackpage.signout();	
		}
	}
	
	@Test(priority=13, enabled=true, testName = "Verify the search in Active Alerts page - Incident ID", description = "Navigate to Active Alerts page and verify search is working properly for 'Incident ID' filter")
	public void Verify_Filter_in_Active_Alerts_page_Incident_ID()
	{
		try
		{
		loginpage.login_as(admin_username, admin_password);
		loginpage.verifyLogin(admin_username);
		ges_ver = loginpage.getGESVersion();
		System.out.println("\n The GES version is : "+ges_ver);
		devops.Navigate_To_DevopsTab();
		devops.Click_Alarm_Alerts();
		String col = "Incident ID";
		devops.Click_Alerts_Sort(col);
		String val = devops.Fetch_Alerts_row(col);
		devops.Click_Filter_button();
		devops.Verify_Filter_working(col,val);
		System.out.println("\n The filter is working properly");
		}
		finally
		{
		callbackpage.signout();	
		}	
	}
	
	@Test(priority=14, enabled=true, testName = "Verify the search in Active Alerts page - Resource", description = "Navigate to Active Alerts page and verify search is working properly for 'Resource' filter")
	public void Verify_Filter_in_Active_Alerts_page_Resource()
	{
		try
		{
		loginpage.login_as(admin_username, admin_password);
		loginpage.verifyLogin(admin_username);
		ges_ver = loginpage.getGESVersion();
		System.out.println("\n The GES version is : "+ges_ver);
		devops.Navigate_To_DevopsTab();
		devops.Click_Alarm_Alerts();
		String col = "Resource";
		devops.Click_Alerts_Sort(col);
		String val = devops.Fetch_Alerts_row(col);
		devops.Click_Filter_button();
		devops.Verify_Filter_working(col,val);
		System.out.println("\n The filter is working properly");
		}
		finally
		{
		callbackpage.signout();	
		}
	}
	
	
	
	@Test(priority=15, enabled=true, testName = "Verify the search in Active Alerts page - Correlation ID", description = "Navigate to Active Alerts page and verify search is working properly for 'Correlation ID' filter")
	public void Verify_Filter_in_Active_Alerts_page_Correlation_ID()
	{
		try
		{
		loginpage.login_as(admin_username, admin_password);
		loginpage.verifyLogin(admin_username);
		ges_ver = loginpage.getGESVersion();
		System.out.println("\n The GES version is : "+ges_ver);
		devops.Navigate_To_DevopsTab();
		devops.Click_Alarm_Alerts();
		String col = "Correlation ID";
		devops.Click_Alerts_Sort(col);
		String val = devops.Fetch_Alerts_row(col);
		devops.Click_Filter_button();
		devops.Verify_Filter_working(col,val);
		System.out.println("\n The filter is working properly");
		}
		finally
		{
		callbackpage.signout();	
		}
	}

	@Test(priority=16, enabled=true, testName = "Verify the search in Active Alerts page - Hostname", description = "Navigate to Active Alerts page and verify search is working properly for 'Hostname' filter")
	public void Verify_Filter_in_Active_Alerts_page_Hostname()
	{
		try
		{
		loginpage.login_as(admin_username, admin_password);
		loginpage.verifyLogin(admin_username);
		ges_ver = loginpage.getGESVersion();
		System.out.println("\n The GES version is : "+ges_ver);
		devops.Navigate_To_DevopsTab();
		devops.Click_Alarm_Alerts();
		String col = "Hostname";
		devops.Click_Alerts_Sort(col);
		String val = devops.Fetch_Alerts_row(col);
		devops.Click_Filter_button();
		devops.Verify_Filter_working(col,val);
		System.out.println("\n The filter is working properly");
		}
		finally
		{
		callbackpage.signout();	
		}
	}
	
	@Test(priority=17, enabled=true, testName = "Verify the search in Active Alerts page - CCID", description = "Navigate to Active Alerts page and verify search is working properly for 'CCID' filter")
	public void Verify_Filter_in_Active_Alerts_page_CCID()
	{
		try
		{
		loginpage.login_as(admin_username, admin_password);
		loginpage.verifyLogin(admin_username);
		ges_ver = loginpage.getGESVersion();
		System.out.println("\n The GES version is : "+ges_ver);
		devops.Navigate_To_DevopsTab();
		devops.Click_Alarm_Alerts();
		String col = "CCID";
		devops.Click_Alerts_Sort(col);
		String val = devops.Fetch_Alerts_row(col);
		devops.Click_Filter_button();
		devops.Verify_Filter_working(col,val);
		System.out.println("\n The filter is working properly");
		}
		finally
		{
		callbackpage.signout();	
		}
	}

	@Test(priority=18, enabled=true, testName = "Verify the search in Active Alarms page - Type", description = "Navigate to Active Alarms page and verify search is working properly for 'Type' filter")
	public void Verify_Filter_in_Active_Alarms_page_Type()
	{
		try
		{
		loginpage.login_as(admin_username, admin_password);
		loginpage.verifyLogin(admin_username);
		ges_ver = loginpage.getGESVersion();
		System.out.println("\n The GES version is : "+ges_ver);
		devops.Navigate_To_DevopsTab();
		devops.Click_Alarm_Alerts();
		devops.Click_Active_Alarms();
		String col = "Type";
		devops.Click_Alarms_Sort(col);
		String val = devops.Fetch_Alarms_row(col);
		devops.Click_Filter_button();
		devops.Verify_Filter_working_Alarm(col,val);
		System.out.println("\n The filter is working properly");
		}
		finally
		{
		callbackpage.signout();	
		}
	}

	@Test(priority=19, enabled=true, testName = "Verify the search in Active Alarms page - Severity", description = "Navigate to Active Alarms page and verify search is working properly for 'Severity' filter")
	public void Verify_Filter_in_Active_Alarms_page_Severity()
	{
		try
		{
		loginpage.login_as(admin_username, admin_password);
		loginpage.verifyLogin(admin_username);
		ges_ver = loginpage.getGESVersion();
		System.out.println("\n The GES version is : "+ges_ver);
		devops.Navigate_To_DevopsTab();
		devops.Click_Alarm_Alerts();
		devops.Click_Active_Alarms();
		String col = "Severity";
		devops.Click_Alarms_Sort(col);
		String val = devops.Fetch_Alarms_row(col);
		devops.Click_Filter_button();
		devops.Verify_Filter_working_Alarm(col,val);
		System.out.println("\n The filter is working properly");
		}
		finally
		{
		callbackpage.signout();	
		}
	}
	
	@Test(priority=20, enabled=true, testName = "Verify the search in Active Alarms page - Timestamp", description = "Navigate to Active Alarms page and verify search is working properly for 'Timestamp' filter")
	public void Verify_Filter_in_Active_Alarms_page_Timestamp()
	{
		try
		{
		loginpage.login_as(admin_username, admin_password);
		loginpage.verifyLogin(admin_username);
		ges_ver = loginpage.getGESVersion();
		System.out.println("\n The GES version is : "+ges_ver);
		devops.Navigate_To_DevopsTab();
		devops.Click_Alarm_Alerts();
		devops.Click_Active_Alarms();
		String col = "Timestamp";
		devops.Click_Alarms_Sort(col);
		String val = devops.Fetch_Alarms_row(col);
		devops.Click_Filter_button();
		devops.Verify_Filter_working_Alarm(col,val);
		System.out.println("\n The filter is working properly");
		}
		finally
		{
		callbackpage.signout();	
		}	
	}
	
	@Test(priority=21, enabled=true, testName = "Verify the search in Active Alarms page - Message", description = "Navigate to Active Alarms page and verify search is working properly for 'Message' filter")
	public void Verify_Filter_in_Active_Alarms_page_Message()
	{
		try
		{
		loginpage.login_as(admin_username, admin_password);
		loginpage.verifyLogin(admin_username);
		ges_ver = loginpage.getGESVersion();
		System.out.println("\n The GES version is : "+ges_ver);
		devops.Navigate_To_DevopsTab();
		devops.Click_Alarm_Alerts();
		devops.Click_Active_Alarms();
		String col = "Message";
		devops.Click_Alarms_Sort(col);
		String val = devops.Fetch_Alarms_row(col);
		String res = val.substring(0,50);
		devops.Click_Filter_button();
		devops.Verify_Filter_working_Alarm(col,res);
		System.out.println("\n The filter is working properly");
		}
		finally
		{
		callbackpage.signout();	
		}
	}
	
	@Test(priority=22, enabled=true, testName = "Verify the search in Active Alarms page - Incident ID", description = "Navigate to Active Alarms page and verify search is working properly for 'Incident ID' filter")
	public void Verify_Filter_in_Active_Alarms_page_Incident_ID()
	{
		try
		{
		loginpage.login_as(admin_username, admin_password);
		loginpage.verifyLogin(admin_username);
		ges_ver = loginpage.getGESVersion();
		System.out.println("\n The GES version is : "+ges_ver);
		devops.Navigate_To_DevopsTab();
		devops.Click_Alarm_Alerts();
		devops.Click_Active_Alarms();
		String col = "Incident ID";
		devops.Click_Alarms_Sort(col);
		String val = devops.Fetch_Alarms_row(col);
		devops.Click_Filter_button();
		devops.Verify_Filter_working_Alarm(col,val);
		System.out.println("\n The filter is working properly");
		}
		finally
		{
		callbackpage.signout();	
		}	
	}
	
	@Test(priority=23, enabled=true, testName = "Verify the search in Active Alarms page - Resource", description = "Navigate to Active Alarms page and verify search is working properly for 'Resource' filter")
	public void Verify_Filter_in_Active_Alarms_page_Resource()
	{
		try
		{
		loginpage.login_as(admin_username, admin_password);
		loginpage.verifyLogin(admin_username);
		ges_ver = loginpage.getGESVersion();
		System.out.println("\n The GES version is : "+ges_ver);
		devops.Navigate_To_DevopsTab();
		devops.Click_Alarm_Alerts();
		devops.Click_Active_Alarms();
		String col = "Resource";
		devops.Click_Alarms_Sort(col);
		String val = devops.Fetch_Alarms_row(col);
		devops.Click_Filter_button();
		devops.Verify_Filter_working_Alarm(col,val);
		System.out.println("\n The filter is working properly");
		}
		finally
		{
		callbackpage.signout();	
		}	
	}
	
	
	
	@Test(priority=24, enabled=true, testName = "Verify the search in Active Alarms page - Correlation ID", description = "Navigate to Active Alarms page and verify search is working properly for 'Correlation ID' filter")
	public void Verify_Filter_in_Active_Alarms_page_Correlation_ID()
	{
		try
		{
		loginpage.login_as(admin_username, admin_password);
		loginpage.verifyLogin(admin_username);
		ges_ver = loginpage.getGESVersion();
		System.out.println("\n The GES version is : "+ges_ver);
		devops.Navigate_To_DevopsTab();
		devops.Click_Alarm_Alerts();
		devops.Click_Active_Alarms();
		String col = "Correlation ID";
		devops.Click_Alarms_Sort(col);
		String val = devops.Fetch_Alarms_row(col);
		devops.Click_Filter_button();
		devops.Verify_Filter_working_Alarm(col,val);
		System.out.println("\n The filter is working properly");
		}
		finally
		{
		callbackpage.signout();	
		}
	}

	@Test(priority=25, enabled=true, testName = "Verify the search in Active Alarms page - Hostname", description = "Navigate to Active Alarms page and verify search is working properly for 'Hostname' filter")
	public void Verify_Filter_in_Active_Alarms_page_Hostname()
	{
		try
		{
		loginpage.login_as(admin_username, admin_password);
		loginpage.verifyLogin(admin_username);
		ges_ver = loginpage.getGESVersion();
		System.out.println("\n The GES version is : "+ges_ver);
		devops.Navigate_To_DevopsTab();
		devops.Click_Alarm_Alerts();
		devops.Click_Active_Alarms();
		String col = "Hostname";
		devops.Click_Alarms_Sort(col);
		String val = devops.Fetch_Alarms_row(col);
		devops.Click_Filter_button();
		devops.Verify_Filter_working_Alarm(col,val);
		System.out.println("\n The filter is working properly");
		}
		finally
		{
		callbackpage.signout();	
		}
	}
	
	@Test(priority=26, enabled=true, testName = "Verify the search in Active Alarms page - CCID", description = "Navigate to Active Alarms page and verify search is working properly for 'CCID' filter")
	public void Verify_Filter_in_Active_Alarms_page_CCID()
	{
		try
		{
		loginpage.login_as(admin_username, admin_password);
		loginpage.verifyLogin(admin_username);
		ges_ver = loginpage.getGESVersion();
		System.out.println("\n The GES version is : "+ges_ver);
		devops.Navigate_To_DevopsTab();
		devops.Click_Alarm_Alerts();
		devops.Click_Active_Alarms();
		String col = "CCID";
		devops.Click_Alarms_Sort(col);
		String val = devops.Fetch_Alarms_row(col);
		devops.Click_Filter_button();
		devops.Verify_Filter_working_Alarm(col,val);
		System.out.println("\n The filter is working properly");
		}
		finally
		{
		callbackpage.signout();	
		}	
	}

	@Test(priority=27, enabled=true, testName = "Verify the Pagination in Active Alarms page - Next", description = "Navigate to Active Alarms page and verify pagination is working properly for 'Next' button")
	public void Verify_Navigation_in_Active_Alarms_page_Next()
	{
		try
		{
		loginpage.login_as(admin_username, admin_password);
		loginpage.verifyLogin(admin_username);
		ges_ver = loginpage.getGESVersion();
		System.out.println("\n The GES version is : "+ges_ver);
		devops.Clear_all_notifications();
		devops.Navigate_To_DevopsTab();
		devops.Click_Alarm_Alerts();
		devops.Click_Active_Alarms();
		devops.Click_Next_button();
		devops.Verify_Navigation("2");
		System.out.println("\n The navigation for next button is working properly");
		}
		finally
		{
		callbackpage.signout();	
		}
	}	
	
	@Test(priority=28, enabled=true, testName = "Verify the Pagination in Active Alarms page - Last", description = "Navigate to Active Alarms page and verify pagination is working properly for 'Last' button")
	public void Verify_Navigation_in_Active_Alarms_page_Last()
	{
		try
		{
		loginpage.login_as(admin_username, admin_password);
		loginpage.verifyLogin(admin_username);
		ges_ver = loginpage.getGESVersion();
		System.out.println("\n The GES version is : "+ges_ver);
		devops.Clear_all_notifications();
		devops.Navigate_To_DevopsTab();
		devops.Click_Alarm_Alerts();
		devops.Click_Active_Alarms();
		devops.Click_Last_button();
		devops.Verify_Navigation("100");
		System.out.println("\n The navigation for Last button is working properly");
		}
		finally
		{
		callbackpage.signout();	
		}
	}
	
	@Test(priority=29, enabled=true, testName = "Verify the Pagination in Active Alarms page - Previous", description = "Navigate to Active Alarms page and verify pagination is working properly for 'Previous' button")
	public void Verify_Navigation_in_Active_Alarms_page_Previous()
	{
		try
		{
		loginpage.login_as(admin_username, admin_password);
		loginpage.verifyLogin(admin_username);
		ges_ver = loginpage.getGESVersion();
		System.out.println("\n The GES version is : "+ges_ver);
		devops.Clear_all_notifications();
		devops.Navigate_To_DevopsTab();
		devops.Click_Alarm_Alerts();
		devops.Click_Active_Alarms();
		devops.Click_Next_button();
		devops.Click_Next_button();
		devops.Click_Previous_button();
		devops.Verify_Navigation("2");
		System.out.println("\n The navigation for Previous button is working properly");
		}
		finally
		{
		callbackpage.signout();	
		}
	}
	
	@Test(priority=30, enabled=true, testName = "Verify the Pagination in Active Alarms page - First", description = "Navigate to Active Alarms page and verify pagination is working properly for 'First' button")
	public void Verify_Navigation_in_Active_Alarms_page_First()
	{
		try
		{
		loginpage.login_as(admin_username, admin_password);
		loginpage.verifyLogin(admin_username);
		ges_ver = loginpage.getGESVersion();
		System.out.println("\n The GES version is : "+ges_ver);
		devops.Clear_all_notifications();
		devops.Navigate_To_DevopsTab();
		devops.Click_Alarm_Alerts();
		devops.Click_Active_Alarms();
		devops.Click_Next_button();
		devops.Click_First_button();
		devops.Verify_Navigation("1");
		System.out.println("\n The navigation for First button is working properly");
		}
		finally
		{
		callbackpage.signout();	
		}
	}
	*/

	@Test(priority=31, enabled=true, testName = "Verify the 9 tabs in Redis Stats page", description = "Navigate to Redis Status and verify if all the nine tabs are present there")
	public void Verify_Tabs_in_Redis_Stats_page()
	{
		try
		{
		loginpage.login_as(admin_username, admin_password);
		loginpage.verifyLogin(admin_username);
		ges_ver = loginpage.getGESVersion();
		System.out.println("\n The GES version is : "+ges_ver);
		devops.Navigate_To_DevopsTab();
		devops.Click_Redis_Status();
		devops.Verify_Rows_Redis_Status_Page();
		}
		finally
		{
		callbackpage.signout();	
		}
	}
	
	@Test(priority=32, enabled=true, testName = "Verify that uptime is changed after selecting refresh button for redis stats page", description = "The uptime should be changed(increased) after selecting refresh button for redis stats page")
	public void Verify_uptime_is_changed_on_refresh_redis()	
	{
		try
		{
		loginpage.login_as(admin_username, admin_password);
		loginpage.verifyLogin(admin_username);
		devops.Navigate_To_DevopsTab();
		devops.Click_Redis_Status();
		String initial_uptime = devops.Capture_lastupdated_redis();
		devops.Click_Refresh_Button_redis();
		String latest_uptime = devops.Capture_lastupdated_redis();
		if(latest_uptime != initial_uptime)
		{
			System.out.println("\n The last updated is changed after refresh and is working properly");
		}
		else
		{
			System.out.println("\n The last updated is changed after refresh");
		}
		}
		finally
		{
		callbackpage.signout();	
		}	
	}
	
	@Test(priority=33, enabled=true, testName = "Verify the search in Redis Stats Server Info page", description = "Navigate to Redis Status Server Info and verify search is working properly")
	public void Verify_Search_in_Redis_Stats_serverinfo_page()
	{
		loginpage.login_as(admin_username, admin_password);
		loginpage.verifyLogin(admin_username);
		ges_ver = loginpage.getGESVersion();
		System.out.println("\n The GES version is : "+ges_ver);
		devops.Navigate_To_DevopsTab();
		devops.Click_Redis_Status();
		String col = "arch_bits";
		int result = devops.Verify_Rows_Redis_Status_Page(col);
		if(result == 1)
		{
			System.out.println("\n The search is working properly");
			callbackpage.signout();
		}
		else
		{
			System.out.println("\n The search is not working properly");
		}		
	}

	@Test(priority=34, enabled=true, testName = "Verify the search in Redis Stats Redis Clients page", description = "Navigate to Redis Status Redis Clients and verify search is working properly")
	public void Verify_Search_in_Redis_Stats_redis_clients_page()
	{
		loginpage.login_as(admin_username, admin_password);
		loginpage.verifyLogin(admin_username);
		ges_ver = loginpage.getGESVersion();
		System.out.println("\n The GES version is : "+ges_ver);
		devops.Navigate_To_DevopsTab();
		devops.Click_Redis_Status();
		devops.Click_Redis_Clients();
		String col = "blocked_clients";
		int result = devops.Verify_Rows_Redis_Status_Page(col);
		if(result == 1)
		{
			System.out.println("\n The search is working properly");
			callbackpage.signout();
		}
		else
		{
			System.out.println("\n The search is not working properly");
		}		
	}
	
	@Test(priority=35, enabled=true, testName = "Verify the search in Redis Stats Memory Usage page", description = "Navigate to Redis Status Memory Usage and verify search is working properly")
	public void Verify_Search_in_Redis_Stats_Memory_Usage_page()
	{
		loginpage.login_as(admin_username, admin_password);
		loginpage.verifyLogin(admin_username);
		ges_ver = loginpage.getGESVersion();
		System.out.println("\n The GES version is : "+ges_ver);
		devops.Navigate_To_DevopsTab();
		devops.Click_Redis_Status();
		devops.Click_Memory_Usage();
		String col = "maxmemory_human";
		int result = devops.Verify_Rows_Redis_Status_Page(col);
		if(result == 1)
		{
			System.out.println("\n The search is working properly");
			callbackpage.signout();
		}
		else
		{
			System.out.println("\n The search is not working properly");
		}		
	}
	
	@Test(priority=36, enabled=true, testName = "Verify the search in Redis Stats Redis Persistence page", description = "Navigate to Redis Status Redis Persistence and verify search is working properly")
	public void Verify_Search_in_Redis_Stats_Redis_Persistence_page()
	{
		loginpage.login_as(admin_username, admin_password);
		loginpage.verifyLogin(admin_username);
		ges_ver = loginpage.getGESVersion();
		System.out.println("\n The GES version is : "+ges_ver);
		devops.Navigate_To_DevopsTab();
		devops.Click_Redis_Status();
		devops.Click_Redis_Persistence();
		String col = "aof_enabled";
		int result = devops.Verify_Rows_Redis_Status_Page(col);
		if(result == 1)
		{
			System.out.println("\n The search is working properly");
			callbackpage.signout();
		}
		else
		{
			System.out.println("\n The search is not working properly");
		}		
	}
	
	@Test(priority=37, enabled=true, testName = "Verify the search in Redis Stats CPU Usage page", description = "Navigate to Redis Status CPU Usage and verify search is working properly")
	public void Verify_Search_in_Redis_Stats_CPU_Usage_page()
	{
		loginpage.login_as(admin_username, admin_password);
		loginpage.verifyLogin(admin_username);
		ges_ver = loginpage.getGESVersion();
		System.out.println("\n The GES version is : "+ges_ver);
		devops.Navigate_To_DevopsTab();
		devops.Click_Redis_Status();
		devops.Click_CPU_Usage();
		String col = "used_cpu_sys_children";
		int result = devops.Verify_Rows_Redis_Status_Page(col);
		if(result == 1)
		{
			System.out.println("\n The search is working properly");
			callbackpage.signout();
		}
		else
		{
			System.out.println("\n The search is not working properly");
		}		
	}
	
	@Test(priority=38, enabled=true, testName = "Verify the search in Redis Stats Misc Stats page", description = "Navigate to Redis Status Misc Stats and verify search is working properly")
	public void Verify_Search_in_Redis_Stats_Misc_Stats_page()
	{
		loginpage.login_as(admin_username, admin_password);
		loginpage.verifyLogin(admin_username);
		ges_ver = loginpage.getGESVersion();
		System.out.println("\n The GES version is : "+ges_ver);
		devops.Navigate_To_DevopsTab();
		devops.Click_Redis_Status();
		devops.Click_Misc_Stats();
		String col = "evicted_keys";
		int result = devops.Verify_Rows_Redis_Status_Page(col);
		if(result == 1)
		{
			System.out.println("\n The search is working properly");
			callbackpage.signout();
		}
		else
		{
			System.out.println("\n The search is not working properly");
		}		
	}
	
	@Test(priority=39, enabled=true, testName = "Verify the search in Redis Stats GES Stats page", description = "Navigate to Redis Status GES Stats and verify search is working properly")
	public void Verify_Search_in_Redis_Stats_GES_Stats_page()
	{
		loginpage.login_as(admin_username, admin_password);
		loginpage.verifyLogin(admin_username);
		ges_ver = loginpage.getGESVersion();
		System.out.println("\n The GES version is : "+ges_ver);
		devops.Navigate_To_DevopsTab();
		devops.Click_Redis_Status();
		devops.Click_GES_Stats();
		String col = "monitorSize";
		int result = devops.Verify_Rows_Redis_Status_Page(col);
		if(result == 1)
		{
			System.out.println("\n The search is working properly");
			callbackpage.signout();
		}
		else
		{
			System.out.println("\n The search is not working properly");
		}		
	}
	
	
	
	@Test(priority=40, enabled=true, testName = "Verify the search in Redis Stats Redis Command Stats page", description = "Navigate to Redis Status Redis Command Stats and verify search is working properly")
	public void Verify_Search_in_Redis_Stats_Redis_Command_Stats_page()
	{
		loginpage.login_as(admin_username, admin_password);
		loginpage.verifyLogin(admin_username);
		ges_ver = loginpage.getGESVersion();
		System.out.println("\n The GES version is : "+ges_ver);
		devops.Navigate_To_DevopsTab();
		devops.Click_Redis_Status();
		devops.Click_Redis_Command_Stats();
		String col = "cmdstat_config";
		int result = devops.Verify_Rows_Redis_Status_Page(col);
		if(result == 1)
		{
			System.out.println("\n The search is working properly");
			callbackpage.signout();
		}
		else
		{
			System.out.println("\n The search is not working properly");
		}		
	}
	
	@Test(priority=41, enabled=true, testName = "Verify the search in Redis Stats Redis Keys Info page", description = "Navigate to Redis Status Redis Keys Info and verify search is working properly")
	public void Verify_Search_in_Redis_Stats_Redis_Keys_Info_page()
	{
		loginpage.login_as(admin_username, admin_password);
		loginpage.verifyLogin(admin_username);
		ges_ver = loginpage.getGESVersion();
		System.out.println("\n The GES version is : "+ges_ver);
		devops.Navigate_To_DevopsTab();
		devops.Click_Redis_Status();
		devops.Click_Redis_Keys_Info();
		String col = "db0";
		int result = devops.Verify_Rows_Redis_Status_Page(col);
		if(result == 1)
		{
			System.out.println("\n The search is working properly");
			callbackpage.signout();
		}
		else
		{
			System.out.println("\n The search is not working properly");
		}		
	}
	
	@Test(priority=42, enabled=true, testName = "Verify the entries in Redis Stats Server Info page", description = "Navigate to Redis Status Server Info and verify sall entries are present")
	public void Verify_entries_in_Redis_Stats_Redis_Server_Info_page()
	{
		try
		{
		loginpage.login_as(admin_username, admin_password);
		loginpage.verifyLogin(admin_username);
		ges_ver = loginpage.getGESVersion();
		System.out.println("\n The GES version is : "+ges_ver);
		devops.Navigate_To_DevopsTab();
		devops.Click_Redis_Status();
		devops.Verify_entries_Redis_Status_Server_Info_Page();
		System.out.println("\n All entries are present");
		}
		finally
		{
		callbackpage.signout();	
		}
	}
	
	@Test(priority=43, enabled=true, testName = "Verify the entries in Redis Stats Redis Clients page", description = "Navigate to Redis Status Redis Clients and verify sall entries are present")
	public void Verify_entries_in_Redis_Stats_Redis_Redis_Clients_page()
	{
		try
		{
		loginpage.login_as(admin_username, admin_password);
		loginpage.verifyLogin(admin_username);
		ges_ver = loginpage.getGESVersion();
		System.out.println("\n The GES version is : "+ges_ver);
		devops.Navigate_To_DevopsTab();
		devops.Click_Redis_Status();
		devops.Click_Redis_Clients();
		devops.Verify_entries_Redis_Status_Redis_Clients_Page();
		System.out.println("\n All entries are present");
		}
		finally
		{
		callbackpage.signout();	
		}
	}
	
	@Test(priority=44, enabled=true, testName = "Verify the entries in Redis Stats Memory Usage page", description = "Navigate to Redis Status Memory Usage and verify sall entries are present")
	public void Verify_entries_in_Redis_Stats_Redis_Memory_Usage_page()
	{
		try
		{
		loginpage.login_as(admin_username, admin_password);
		loginpage.verifyLogin(admin_username);
		ges_ver = loginpage.getGESVersion();
		System.out.println("\n The GES version is : "+ges_ver);
		devops.Navigate_To_DevopsTab();
		devops.Click_Redis_Status();
		devops.Click_Memory_Usage();
		devops.Verify_entries_Redis_Status_Memory_Usage_Page();
		System.out.println("\n All entries are present");
		}
		finally
		{
		callbackpage.signout();	
		}
	}
	
	@Test(priority=45, enabled=true, testName = "Verify the entries in Redis Stats Redis Persistence page", description = "Navigate to Redis Status Redis Persistence and verify sall entries are present")
	public void Verify_entries_in_Redis_Stats_Redis_Persistence_page()
	{
		try
		{
		loginpage.login_as(admin_username, admin_password);
		loginpage.verifyLogin(admin_username);
		ges_ver = loginpage.getGESVersion();
		System.out.println("\n The GES version is : "+ges_ver);
		devops.Navigate_To_DevopsTab();
		devops.Click_Redis_Status();
		devops.Click_Redis_Persistence();
		devops.Verify_entries_Redis_Status_Redis_Persistence_Page();
		System.out.println("\n All entries are present");
		}
		finally
		{
		callbackpage.signout();	
		}
	}
	
	@Test(priority=45, enabled=true, testName = "Verify the entries in Redis Stats CPU Usage page", description = "Navigate to Redis Status CPU Usage and verify sall entries are present")
	public void Verify_entries_in_Redis_Stats_CPU_Usage_page()
	{
		try
		{
		loginpage.login_as(admin_username, admin_password);
		loginpage.verifyLogin(admin_username);
		ges_ver = loginpage.getGESVersion();
		System.out.println("\n The GES version is : "+ges_ver);
		devops.Navigate_To_DevopsTab();
		devops.Click_Redis_Status();
		devops.Click_CPU_Usage();
		devops.Verify_entries_Redis_Status_CPU_Usage_Page();
		System.out.println("\n All entries are present");
		}
		finally
		{
		callbackpage.signout();	
		}	
	}
	
	@Test(priority=46, enabled=true, testName = "Verify the entries in Redis Stats Misc Stats page", description = "Navigate to Redis Status Misc Stats and verify sall entries are present")
	public void Verify_entries_in_Redis_Stats_Misc_Stats_page()
	{
		try
		{
		loginpage.login_as(admin_username, admin_password);
		loginpage.verifyLogin(admin_username);
		ges_ver = loginpage.getGESVersion();
		System.out.println("\n The GES version is : "+ges_ver);
		devops.Navigate_To_DevopsTab();
		devops.Click_Redis_Status();
		devops.Click_Misc_Stats();
		devops.Verify_entries_Redis_Status_Misc_Stats_Page();
		System.out.println("\n All entries are present");
		}
		finally
		{
		callbackpage.signout();	
		}
	}
	
	@Test(priority=47, enabled=true, testName = "Verify the entries in Redis Stats GES Stats page", description = "Navigate to Redis Status GES Stats and verify sall entries are present")
	public void Verify_entries_in_Redis_Stats_GES_Stats_page()
	{
		try
		{
		loginpage.login_as(admin_username, admin_password);
		loginpage.verifyLogin(admin_username);
		ges_ver = loginpage.getGESVersion();
		System.out.println("\n The GES version is : "+ges_ver);
		devops.Navigate_To_DevopsTab();
		devops.Click_Redis_Status();
		devops.Click_GES_Stats();
		devops.Verify_entries_Redis_Status_GES_Stats_Page();
		System.out.println("\n All entries are present");
		}
		finally
		{
		callbackpage.signout();	
		}
	}
	
	@Test(priority=48, enabled=true, testName = "Verify the entries in Redis Stats Redis Command Stats page", description = "Navigate to Redis Status Redis Command Stats and verify sall entries are present")
	public void Verify_entries_in_Redis_Stats_Redis_Command_Stats_page()
	{
		try
		{
		loginpage.login_as(admin_username, admin_password);
		loginpage.verifyLogin(admin_username);
		ges_ver = loginpage.getGESVersion();
		System.out.println("\n The GES version is : "+ges_ver);
		devops.Navigate_To_DevopsTab();
		devops.Click_Redis_Status();
		devops.Click_Redis_Command_Stats();
		devops.Verify_entries_Redis_Status_Redis_Command_Stats_Page();
		System.out.println("\n All entries are present");
		}
		finally
		{
		callbackpage.signout();	
		}	
	}
	
	@Test(priority=49, enabled=true, testName = "Verify the entries in Redis Stats Redis Keys Info page", description = "Navigate to Redis Status Redis Keys Info and verify sall entries are present")
	public void Verify_entries_in_Redis_Stats_Redis_Keys_Info_page()
	{
		try
		{
		loginpage.login_as(admin_username, admin_password);
		loginpage.verifyLogin(admin_username);
		ges_ver = loginpage.getGESVersion();
		System.out.println("\n The GES version is : "+ges_ver);
		devops.Navigate_To_DevopsTab();
		devops.Click_Redis_Status();
		devops.Click_Redis_Keys_Info();
		devops.Verify_entries_Redis_Status_Redis_Keys_Info_Page();
		System.out.println("\n All entries are present");
		}
		finally
		{
		callbackpage.signout();	
		}	
	}
	
	@Test(priority=50, enabled=true, testName = "Verify the rows in tenant configuration page", description = "Navigate to tenant configuration and verify all entries are present")
	public void Verify_tabs_in_Tenant_Configuration_page()
	{
		try
		{
		loginpage.login_as(admin_username, admin_password);
		loginpage.verifyLogin(admin_username);
		ges_ver = loginpage.getGESVersion();
		System.out.println("\n The GES version is : "+ges_ver);
		devops.Navigate_To_DevopsTab();
		devops.Click_Tenant_Config();
		devops.Verify_tabs_Tenant_Configuration_page();
		System.out.println("\n All entries are present");
		}
		finally
		{
		callbackpage.signout();	
		}
	}
	
	@Test(priority=51, enabled=true, testName = "Verify the queue details are displayed properly", description = "Navigate to service configuration, select a service and verify all details are correct")
	public void Verify_service_details_displayed_properly()
	{
		try
		{
		loginpage.login_as(admin_username, admin_password);
		loginpage.verifyLogin(admin_username);
		ges_ver = loginpage.getGESVersion();
		System.out.println("\n The GES version is : "+ges_ver);
		devops.Navigate_To_DevopsTab();
		devops.Click_Tenant_Config();
		devops.Click_Service_Config();
		devops.Click_Tenant_Selection();
		devops.Verify_service_properties();
		System.out.println("\n All entries are present");
		}
		finally
		{
		callbackpage.signout();	
		}
	}
	
	@Test(priority=52, enabled=false, testName = "Verify the search is working properly in service configuration page", description = "Navigate to service configuration, select a service and then a property then verify search is working fine")
	public void Verify_search_in_Service_Config_page()
	{
		loginpage.login_as(admin_username, admin_password);
		loginpage.verifyLogin(admin_username);
		ges_ver = loginpage.getGESVersion();
		System.out.println("\n The GES version is : "+ges_ver);
		devops.Navigate_To_DevopsTab();
		devops.Click_Tenant_Config();
		devops.Click_Service_Config();
		devops.Click_Tenant_Selection();
		devops.Select_Service();
		devops.Click_Service_Config();
		//devops.Click_Tenant_configuration();
		//devops.Click_Service_Config();
		int result = devops.Verify_Search_Select_Service();
		if(result == 1)
		{
			System.out.println("\n The search is working properly");
			callbackpage.signout();
		}
		else
		{
			System.out.println("\n The search is not working properly");
		}
	}
	
	@Test(priority=53, enabled=true, testName = "Verify the column names in ORS/URS Status page", description = "Navigate to ORS/URS Status page and then verify column names")
	public void Verify_column_names_ORS_URS_Status_page()
	{
		try
		{
		loginpage.login_as(admin_username, admin_password);
		loginpage.verifyLogin(admin_username);
		ges_ver = loginpage.getGESVersion();
		System.out.println("\n The GES version is : "+ges_ver);
		devops.Navigate_To_DevopsTab();
		devops.Click_Tenant_Config();
		devops.Click_ORS_URS_Status();
		devops.Verify_column_names_status();
		}
		finally
		{
		callbackpage.signout();	
		}
	}
	
	@Test(priority=54, enabled=true, testName = "Verify the entries in ORS/URS Status page", description = "Navigate to ORS/URS Status page and then verify entries")
	public void Verify_entries_ORS_URS_Status_page()
	{
		try
		{
		loginpage.login_as(admin_username, admin_password);
		loginpage.verifyLogin(admin_username);
		ges_ver = loginpage.getGESVersion();
		System.out.println("\n The GES version is : "+ges_ver);
		devops.Navigate_To_DevopsTab();
		devops.Click_Tenant_Config();
		devops.Click_ORS_URS_Status();
		devops.Click_Tenant_Selection();
		devops.Verify_entries_ORS_URS_Page();
		}
		finally
		{
		callbackpage.signout();	
		}
	}
	
	@Test(priority=55, enabled=true, testName = "Verify the message in ORS/URS Status page", description = "Navigate to ORS/URS Status page and then verify last response message")
	public void Verify_message_ORS_URS_Status_page()
	{
		try
		{
		loginpage.login_as(admin_username, admin_password);
		loginpage.verifyLogin(admin_username);
		ges_ver = loginpage.getGESVersion();
		System.out.println("\n The GES version is : "+ges_ver);
		devops.Navigate_To_DevopsTab();
		devops.Click_Tenant_Config();
		devops.Click_ORS_URS_Status();
		devops.Click_Tenant_Selection();
		devops.Click_ORS_entry();
		devops.Verify_message_ORS_URS_Page();
		}
		finally
		{
		callbackpage.signout();	
		}
	}
	
	@Test(priority=56, enabled=true, testName = "Verify the advanced login privileges", description = "Navigate to Advanced Devops login page and then validate credentials")
	public void Verify_advanced_login()
	{
		try
		{
		loginpage.login_as(admin_username, admin_password);
		loginpage.verifyLogin(admin_username);
		ges_ver = loginpage.getGESVersion();
		System.out.println("\n The GES version is : "+ges_ver);
		devops.Navigate_To_DevopsTab();
		devops.Click_Advanced_login();
		devops.Enter_username(adv_username);
		devops.Enter_password(adv_password);
		devops.Click_Login_button();
		devops.Verify_Advanced_Privileges();
		}
		finally
		{
		callbackpage.signout();	
		}
	}
	
	@Test(priority=57, enabled=true, testName = "Verify the error response for invalid advanced login", description = "Navigate to Advanced Devops login page and then validate error response for invalid login credentials")
	public void Verify_invalid_advanced_login()
	{
		try
		{
		loginpage.login_as(admin_username, admin_password);
		loginpage.verifyLogin(admin_username);
		ges_ver = loginpage.getGESVersion();
		System.out.println("\n The GES version is : "+ges_ver);
		devops.Clear_all_notifications();
		devops.Navigate_To_DevopsTab();
		devops.Click_Advanced_login();
		devops.Enter_username(adv_username);
		devops.Enter_password(invalid_password);
		devops.Click_Login_button();
		devops.Verify_error_response_invalid_access();
		}
		finally
		{
		callbackpage.signout();	
		}
	}
	
	@Test(priority=58, enabled=true, testName = "Verify the error response for empty username advanced login", description = "Navigate to Advanced Devops login page and then validate error response for empty username login credentials")
	public void Verify_empty_username_advanced_login()
	{
		try
		{
		loginpage.login_as(admin_username, admin_password);
		loginpage.verifyLogin(admin_username);
		ges_ver = loginpage.getGESVersion();
		System.out.println("\n The GES version is : "+ges_ver);
		devops.Clear_all_notifications();
		devops.Navigate_To_DevopsTab();
		devops.Click_Advanced_login();
		devops.Enter_username(adv_username);
		//devops.Enter_password(invalid_password);
		devops.Click_Login_button();
		devops.Verify_error_response_invalid_access();
		}
		finally
		{
		callbackpage.signout();	
		}
	}
	
	@Test(priority=59, enabled=true, testName = "Verify the error response for empty password advanced login", description = "Navigate to Advanced Devops login page and then validate error response for empty password login credentials")
	public void Verify_empty_password_advanced_login()
	{
		try
		{
		loginpage.login_as(admin_username, admin_password);
		loginpage.verifyLogin(admin_username);
		ges_ver = loginpage.getGESVersion();
		System.out.println("\n The GES version is : "+ges_ver);
		devops.Clear_all_notifications();
		devops.Navigate_To_DevopsTab();
		devops.Click_Advanced_login();
		//devops.Enter_username(adv_username);
		devops.Enter_password(adv_password);
		devops.Click_Login_button();
		devops.Verify_error_response_invalid_access();
		}
		finally
		{
		callbackpage.signout();	
		}
	}


	
//--------------------------- Run Time Logging testcases ----------------------------------------//
	
	
	@Test(priority=60, enabled=true, testName = "Verify that logging UI page is present after advanced login", description = "Navigate to Advanced Devops login page and then login and then check for the existence of logging page UI")
	public void Verify_LoggingUI_page_Availability_adv_Login()
	{
		try
		{
		loginpage.login_as(admin_username, admin_password);
		loginpage.verifyLogin(admin_username);
		ges_ver = loginpage.getGESVersion();
		System.out.println("\n The GES version is : "+ges_ver);
		devops.Navigate_To_DevopsTab();
		devops.Click_Advanced_login();
		devops.Enter_username(adv_username);
		devops.Enter_password(adv_password);
		devops.Click_Login_button();
		devops.Verify_Advanced_Privileges();
		devops.Navigate_To_DevopsTab();
		devops.Click_Logging();
		devops.Verify_Logging_Page_UI("");
		}
		finally
		{
		callbackpage.signout();	
		}
	}
	
	@Test(priority=61, enabled=true, testName = "Verify that logging UI page is present before advanced login", description = "Navigate to Advanced Devops login page and then check for the existence of logging page UI")
	public void Verify_LoggingUI_page_Availability()
	{
		try
		{
		loginpage.login_as(admin_username, admin_password);
		loginpage.verifyLogin(admin_username);
		ges_ver = loginpage.getGESVersion();
		System.out.println("\n The GES version is : "+ges_ver);
		devops.Navigate_To_DevopsTab();
		devops.Click_Logging();
		devops.Verify_Logging_Page_UI("");
		}
		finally
		{
		callbackpage.signout();	
		}
	}
	
//	Commenting the below the tests due to GES-2677
//	@Test(priority=62, enabled=true, testName = "Verify that logging UI page is changed to fatal level", description = "Navigate to Advanced Devops login page and then check for the existence of logging page UI and then change the log level to fatal")
//	public void Verify_LoggingUI_page_fatal()
//	{
//		try
//		{
//		loginpage.login_as(admin_username, admin_password);
//		loginpage.verifyLogin(admin_username);
//		ges_ver = loginpage.getGESVersion();
//		System.out.println("\n The GES version is : "+ges_ver);
//		devops.Navigate_To_DevopsTab();
//		devops.Click_Advanced_login();
//		devops.Enter_username(adv_username);
//		devops.Enter_password(adv_password);
//		devops.Click_Login_button();
//		devops.Verify_Advanced_Privileges();
//		devops.Navigate_To_DevopsTab();
//		devops.Click_Logging();
//		String Log_Level = devops.Get_Actual_Log_Level();
//		if(Log_Level.equals("fatal")){
//			Log_Level = "debug";
//			devops.Change_Log_level(Log_Level,"120");
//			devops.Verify_Logging_Page_UI(Log_Level);
//			devops.Navigate_To_DevopsTab();
//			devops.Click_Advanced_login();
//			devops.Navigate_To_DevopsTab();
//			devops.Click_Logging();
//			devops.Verify_Logging_Page_UI(Log_Level);
//		};
//		devops.Change_Log_level("fatal","60");
//		devops.Navigate_To_DevopsTab();
//		devops.Click_Advanced_login();
//		devops.Navigate_To_DevopsTab();
//		devops.Click_Logging();
//		devops.Verify_Logging_Page_UI("fatal");
//		sleepFor(60);
//		devops.Navigate_To_DevopsTab();
//		devops.Click_Advanced_login();
//		devops.Navigate_To_DevopsTab();
//		devops.Click_Logging();
//		devops.Verify_Logging_Page_UI(Log_Level);
//		sleepFor(60);
//		}
//		finally
//		{
//		callbackpage.signout();	
//		}
//	}
//	
//	@Test(priority=63, enabled=true, testName = "Verify that logging UI page is changed to error level", description = "Navigate to Advanced Devops login page and then check for the existence of logging page UI and then change the log level to error")
//	public void Verify_LoggingUI_page_error()
//	{
//		try
//		{
//		loginpage.login_as(admin_username, admin_password);
//		loginpage.verifyLogin(admin_username);
//		ges_ver = loginpage.getGESVersion();
//		System.out.println("\n The GES version is : "+ges_ver);
//		devops.Navigate_To_DevopsTab();
//		devops.Click_Advanced_login();
//		devops.Enter_username(adv_username);
//		devops.Enter_password(adv_password);
//		devops.Click_Login_button();
//		devops.Verify_Advanced_Privileges();
//		devops.Navigate_To_DevopsTab();
//		devops.Click_Logging();
//		String Log_Level = devops.Get_Actual_Log_Level();
//		if(Log_Level.equals("error")){
//			Log_Level = "debug";
//			devops.Change_Log_level(Log_Level,"120");
//			devops.Verify_Logging_Page_UI(Log_Level);
//			devops.Navigate_To_DevopsTab();
//			devops.Click_Advanced_login();
//			devops.Navigate_To_DevopsTab();
//			devops.Click_Logging();
//			devops.Verify_Logging_Page_UI(Log_Level);
//		};
//		devops.Change_Log_level("error","60");
//		devops.Navigate_To_DevopsTab();
//		devops.Click_Advanced_login();
//		devops.Navigate_To_DevopsTab();
//		devops.Click_Logging();
//		devops.Verify_Logging_Page_UI("error");
//		sleepFor(60);
//		devops.Navigate_To_DevopsTab();
//		devops.Click_Advanced_login();
//		devops.Navigate_To_DevopsTab();
//		devops.Click_Logging();
//		devops.Verify_Logging_Page_UI(Log_Level);
//		sleepFor(60);
//		}
//		finally
//		{
//		callbackpage.signout();	
//		}
//	}
//	
//	@Test(priority=64, enabled=true, testName = "Verify that logging UI page is changed to warn level", description = "Navigate to Advanced Devops login page and then check for the existence of logging page UI and then change the log level to warn")
//	public void Verify_LoggingUI_page_warn()
//	{
//		try
//		{
//		loginpage.login_as(admin_username, admin_password);
//		loginpage.verifyLogin(admin_username);
//		ges_ver = loginpage.getGESVersion();
//		System.out.println("\n The GES version is : "+ges_ver);
//		devops.Navigate_To_DevopsTab();
//		devops.Click_Advanced_login();
//		devops.Enter_username(adv_username);
//		devops.Enter_password(adv_password);
//		devops.Click_Login_button();
//		devops.Verify_Advanced_Privileges();
//		devops.Navigate_To_DevopsTab();
//		devops.Click_Logging();
//		String Log_Level = devops.Get_Actual_Log_Level();
//		if(Log_Level.equals("warn")){
//			Log_Level = "debug";
//			devops.Change_Log_level(Log_Level,"120");
//			devops.Verify_Logging_Page_UI(Log_Level);
//			devops.Navigate_To_DevopsTab();
//			devops.Click_Advanced_login();
//			devops.Navigate_To_DevopsTab();
//			devops.Click_Logging();
//			devops.Verify_Logging_Page_UI(Log_Level);
//		};
//		devops.Change_Log_level("warn","60");
//		devops.Navigate_To_DevopsTab();
//		devops.Click_Advanced_login();
//		devops.Navigate_To_DevopsTab();
//		devops.Click_Logging();
//		devops.Verify_Logging_Page_UI("warn");
//		sleepFor(60);
//		devops.Navigate_To_DevopsTab();
//		devops.Click_Advanced_login();
//		devops.Navigate_To_DevopsTab();
//		devops.Click_Logging();
//		devops.Verify_Logging_Page_UI(Log_Level);
//		sleepFor(60);
//		}
//		finally
//		{
//		callbackpage.signout();	
//		}
//	}
//	
//	@Test(priority=65, enabled=true, testName = "Verify that logging UI page is changed to info level", description = "Navigate to Advanced Devops login page and then check for the existence of logging page UI and then change the log level to info")
//	public void Verify_LoggingUI_page_info()
//	{
//		try
//		{
//		loginpage.login_as(admin_username, admin_password);
//		loginpage.verifyLogin(admin_username);
//		ges_ver = loginpage.getGESVersion();
//		System.out.println("\n The GES version is : "+ges_ver);
//		devops.Navigate_To_DevopsTab();
//		devops.Click_Advanced_login();
//		devops.Enter_username(adv_username);
//		devops.Enter_password(adv_password);
//		devops.Click_Login_button();
//		devops.Verify_Advanced_Privileges();
//		devops.Navigate_To_DevopsTab();
//		devops.Click_Logging();
//		String Log_Level = devops.Get_Actual_Log_Level();
//		if(Log_Level.equals("info")){
//			Log_Level = "debug";
//			devops.Change_Log_level(Log_Level,"120");
//			devops.Verify_Logging_Page_UI(Log_Level);
//			devops.Navigate_To_DevopsTab();
//			devops.Click_Advanced_login();
//			devops.Navigate_To_DevopsTab();
//			devops.Click_Logging();
//			devops.Verify_Logging_Page_UI(Log_Level);
//		};
//		devops.Change_Log_level("info","60");
//		devops.Navigate_To_DevopsTab();
//		devops.Click_Advanced_login();
//		devops.Navigate_To_DevopsTab();
//		devops.Click_Logging();
//		devops.Verify_Logging_Page_UI("info");
//		sleepFor(60);
//		devops.Navigate_To_DevopsTab();
//		devops.Click_Advanced_login();
//		devops.Navigate_To_DevopsTab();
//		devops.Click_Logging();
//		devops.Verify_Logging_Page_UI(Log_Level);
//		sleepFor(60);
//		}
//		finally
//		{
//		callbackpage.signout();	
//		}
//	}
//	
//	@Test(priority=66, enabled=true, testName = "Verify that logging UI page is changed to trace level", description = "Navigate to Advanced Devops login page and then check for the existence of logging page UI and then change the log level to trace")
//	public void Verify_LoggingUI_page_trace()
//	{
//		try
//		{
//		loginpage.login_as(admin_username, admin_password);
//		loginpage.verifyLogin(admin_username);
//		ges_ver = loginpage.getGESVersion();
//		System.out.println("\n The GES version is : "+ges_ver);
//		devops.Navigate_To_DevopsTab();
//		devops.Click_Advanced_login();
//		devops.Enter_username(adv_username);
//		devops.Enter_password(adv_password);
//		devops.Click_Login_button();
//		devops.Verify_Advanced_Privileges();
//		devops.Navigate_To_DevopsTab();
//		devops.Click_Logging();
//		String Log_Level = devops.Get_Actual_Log_Level();
//		if(Log_Level.equals("trace")){
//			Log_Level = "debug";
//			devops.Change_Log_level(Log_Level,"120");
//			devops.Verify_Logging_Page_UI(Log_Level);
//			devops.Navigate_To_DevopsTab();
//			devops.Click_Advanced_login();
//			devops.Navigate_To_DevopsTab();
//			devops.Click_Logging();
//			devops.Verify_Logging_Page_UI(Log_Level);
//		};
//		devops.Change_Log_level("trace","60");
//		devops.Navigate_To_DevopsTab();
//		devops.Click_Advanced_login();
//		devops.Navigate_To_DevopsTab();
//		devops.Click_Logging();
//		devops.Verify_Logging_Page_UI("trace");
//		sleepFor(60);
//		devops.Navigate_To_DevopsTab();
//		devops.Click_Advanced_login();
//		devops.Navigate_To_DevopsTab();
//		devops.Click_Logging();
//		devops.Verify_Logging_Page_UI(Log_Level);
//		sleepFor(60);
//		}
//		finally
//		{
//		callbackpage.signout();	
//		}
//	}
//
//	
//	@Test(priority=67, enabled=true, testName = "Verify that logging UI page is changed to debug level", description = "Navigate to Advanced Devops login page and then check for the existence of logging page UI and then change the log level to debug")
//	public void Verify_LoggingUI_page_debug()
//	{
//		try
//		{
//		loginpage.login_as(admin_username, admin_password);
//		loginpage.verifyLogin(admin_username);
//		ges_ver = loginpage.getGESVersion();
//		System.out.println("\n The GES version is : "+ges_ver);
//		devops.Navigate_To_DevopsTab();
//		devops.Click_Advanced_login();
//		devops.Enter_username(adv_username);
//		devops.Enter_password(adv_password);
//		devops.Click_Login_button();
//		devops.Verify_Advanced_Privileges();
//		devops.Navigate_To_DevopsTab();
//		devops.Click_Logging();
//		String Log_Level = devops.Get_Actual_Log_Level();
//		if(Log_Level.equals("debug")){
//			Log_Level = "trace";
//			devops.Change_Log_level(Log_Level,"120");
//			devops.Verify_Logging_Page_UI(Log_Level);
//			devops.Navigate_To_DevopsTab();
//			devops.Click_Advanced_login();
//			devops.Navigate_To_DevopsTab();
//			devops.Click_Logging();
//			devops.Verify_Logging_Page_UI(Log_Level);
//		};
//		devops.Change_Log_level("debug","60");
//		devops.Navigate_To_DevopsTab();
//		devops.Click_Advanced_login();
//		devops.Navigate_To_DevopsTab();
//		devops.Click_Logging();
//		devops.Verify_Logging_Page_UI("debug");
//		sleepFor(60);
//		devops.Navigate_To_DevopsTab();
//		devops.Click_Advanced_login();
//		devops.Navigate_To_DevopsTab();
//		devops.Click_Logging();
//		devops.Verify_Logging_Page_UI(Log_Level);
//		sleepFor(60);
//		}
//		finally
//		{
//		callbackpage.signout();	
//		}
//	}


}

