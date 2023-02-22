package ges.tests;


import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import ges.pages.CallbackPage;
import ges.pages.CallbackPage_Search;
import ges.pages.DeveloperPage;
import ges.pages.LoginPage;
import ges.util.BaseTest;

public class CallbackPage_Test extends BaseTest{
	
	/****************************************************************************/
	
	LoginPage loginpage = new LoginPage();
	CallbackPage callbackpage = new CallbackPage();
	DeveloperPage devpage = new DeveloperPage();
	CallbackPage_Search callback_search = new CallbackPage_Search();
	public String ges_ver;
	
	/****************************************************************************/
	
	private String admin_username = testdata_property_file_reader("Admin_username");
	private String admin_password = testdata_property_file_reader("Admin_password");
	
	/****************************************************************************/
	
	@BeforeClass
	
	public void beforeClass() {
		
	System.out.println(
			"***************************************************************************************** \n\n");
	System.out.println("				Running Functional UI testcases - Callback Tab");
	System.out.println(
			"\n\n*****************************************************************************************");
	}
	
	@Test(priority=1, enabled=true, groups = {"callback","current"}, testName = "Create and Verify an Immediate Callback", description = "Create an Immediate Callback and verify if it's created successfully. Finally cancel the created callback")
		
	public void Create_Callback_Immediate() throws IOException{
		try
		{
		String ph_no = Create_new_phoneNumber();
		loginpage.login_as(admin_username, admin_password);
		loginpage.verifyLogin(admin_username);
		//testdata_property_file_writer(ges_ver);
		callbackpage.createImmediateCallback_and_verify(ph_no);
		callbackpage.displayCallbackDetails_toConsole(ph_no);
		callbackpage.cancel_Callback(ph_no);
		}
		finally
		{
		callbackpage.signout();	
		}
	}
	
	@Test(priority=6, enabled=true, groups = {"callback"}, testName = "Create and Verify a Scheduled Callback", description = "Create a Scheduled Callback and verify if it's created successfully. Finally cancel the created callback")
	
	public void Create_Callback_Scheduled(){
		try
		{
		String ph_no = Create_new_phoneNumber();
		int add_days = 0;
		int add_slots = 2;
		String d= "Today";
		loginpage.login_as(admin_username, admin_password);
		loginpage.verifyLogin(admin_username);
		ges_ver = loginpage.getGESVersion();
		System.out.println("\n The GES version is : "+ges_ver);
		callbackpage.createScheduledCallback_and_verify(ph_no,add_days,add_slots,d);
		callbackpage.displayCallbackDetails_toConsole(ph_no);
		callbackpage.cancel_Callback(ph_no);
		}
		finally
		{
		callbackpage.signout();	
		}
}
	
@Test(priority=2, enabled=true, groups = {"callback"}, testName = "Create a Callback, Cancel the Callback and Apply Filter to view the callback", description = "Create a Scheduled Callback and verify if it's created successfully. Finally cancel the created callback")
	
	public void Create_Callback_and_Apply_Filter(){
		try
		{
		String ph_no = Create_new_phoneNumber();
		loginpage.login_as(admin_username, admin_password);
		loginpage.verifyLogin(admin_username);
		callbackpage.createImmediateCallback_and_verify(ph_no);
		callbackpage.displayCallbackDetails_toConsole(ph_no);
		callbackpage.cancel_Callback(ph_no);
		callbackpage.Click_AdvancedOptions();
		callbackpage.verifyAdvancedOptions_Headers();
		callbackpage.filterUIwithServiceName();
		callbackpage.verify_Default_CallbackPage_Columns();
		}
		finally
		{
		callbackpage.signout();	
		}
}
	
@Test(priority=3, enabled=true, groups = {"callback"}, testName = "Callback Page Column verification", description = "Verify the columns in callback page. Apply filter - remove a column. Verify if the column is not displayed anymore. Finally reset to original config")

public void Create_Callback_and_Apply_Filter_onColumns_ColumnsCheck(){
try
		{
	loginpage.login_as(admin_username, admin_password);
	loginpage.verifyLogin(admin_username);
	callbackpage.reset_advancedOptions_ColumnView();
	callbackpage.verify_Default_CallbackPage_Columns();
	callbackpage.apply_filter_remove_ServiceIDcolumn();
	callbackpage.verify_filtered_CallbackPage_Columns();
	callbackpage.reset_advancedOptions_ColumnView();
	}
		finally
		{
		callbackpage.signout();	
		}
}

@Test(priority=4, enabled=true, groups = {"callback"}, testName = "Create Callback and search by Service ID", description = "Verify if callback is created successfully and displayed when filtered by service ID")

public void Create_Callback_and_SearchByServiceID(){
	try
		{
	String ph_no = Create_new_phoneNumber();
	loginpage.login_as(admin_username, admin_password);
	loginpage.verifyLogin(admin_username);
	String serv_id = callbackpage.createImmediateCallback_and_returnServiceID(ph_no);
	callbackpage.displayCallbackDetails_toConsole(ph_no);
	callback_search.Verify_ServiceID_Search(serv_id);
	callbackpage.displayCallbackDetails_toConsole(ph_no);
	callbackpage.cancel_Callback(ph_no);
	}
		finally
		{
		callbackpage.signout();	
		}
}

@Test(priority=5, enabled=true, groups = {"callback"}, testName = "Recreate Callback", description = "Verify if callback is re-created successfully")

public void Recreate_Callback(){
	try
		{
	String ph_no = Create_new_phoneNumber();
	loginpage.login_as(admin_username, admin_password);
	loginpage.verifyLogin(admin_username);
	callbackpage.createImmediateCallback_and_verify(ph_no);
	callbackpage.displayCallbackDetails_toConsole(ph_no);
	callbackpage.cancel_Callback(ph_no);
	callbackpage.recreate_Callback(ph_no);
	callbackpage.displayCallbackDetails_toConsole(ph_no);
	callbackpage.cancel_Callback(ph_no);
	callbackpage.displayCallbackDetails_toConsole(ph_no);
	}
	finally
		{
		callbackpage.signout();	
		}
}

@Test(priority=7, enabled=false, groups = {"callback","current"}, testName = "Create an Immediate Callback and verify callback metrics", description = "Create an Immediate Callback and verify if it's created successfully. Finally verify the callback metrics")

public void Verify_Callback_Metrics(){
	try
		{
	String ph_no = Create_new_phoneNumber();
	loginpage.login_as(admin_username, admin_password);
	loginpage.verifyLogin(admin_username);
	ges_ver = loginpage.getGESVersion();
	System.out.println("\n The GES version is : "+ges_ver);
	//testdata_property_file_writer(ges_ver);
	callbackpage.createImmediateCallback_and_verify(ph_no);
	callbackpage.displayCallbackDetails_toConsole(ph_no);
	callbackpage.cancel_Callback(ph_no);
	callbackpage.verify_callback_metrics(ph_no);
	}
		finally
		{
		callbackpage.signout();	
		}
}

@Test(priority=8, enabled=true, testName = "Verify the sorting works in Callback page - Callback ID", description = "Navigate to Callback page and verify search is working properly for 'Callback ID' filter")
public void Verify_Filter_in_Callback_page_Callback_ID()
{
	try
		{
	String col = "Callback ID";
	loginpage.login_as(admin_username, admin_password);
	loginpage.verifyLogin(admin_username);
	ges_ver = loginpage.getGESVersion();
	System.out.println("\n The GES version is : "+ges_ver);
	callbackpage.Click_Callback_sort(col);
	String val = callbackpage.Fetch_Callback_row(col);
	System.out.println(val);
	callbackpage.Click_Filter_button();
	callbackpage.Verify_Filter_working_Callback(col,val);
	}
		finally
		{
		callbackpage.signout();	
		}	
}

@Test(priority=9, enabled=true, testName = "Verify the sorting works in Callback page - State", description = "Navigate to Callback page and verify search is working properly for 'State' filter")
public void Verify_Filter_in_Callback_page_State()
{
	try
		{
	String col = "State";
	loginpage.login_as(admin_username, admin_password);
	loginpage.verifyLogin(admin_username);
	ges_ver = loginpage.getGESVersion();
	System.out.println("\n The GES version is : "+ges_ver);
	callbackpage.Click_Callback_sort(col);
	String val = callbackpage.Fetch_Callback_row(col);
	System.out.println(val);
	callbackpage.Click_Filter_button();
	callbackpage.Verify_Filter_working_Callback(col,val);
	}
		finally
		{
		callbackpage.signout();	
		}
}

@Test(priority=10, enabled=true, testName = "Verify the sorting works in Callback page - Desired Callback Time", description = "Navigate to Callback page and verify search is working properly for 'Desired Callback Time' filter")
public void Verify_Filter_in_Callback_page_Desired_Callback_Time()
{
	try
		{
	String col = "Desired Callback Time";
	loginpage.login_as(admin_username, admin_password);
	loginpage.verifyLogin(admin_username);
	ges_ver = loginpage.getGESVersion();
	System.out.println("\n The GES version is : "+ges_ver);
	callbackpage.Click_Callback_sort(col);
	String val = callbackpage.Fetch_Callback_row(col);
	System.out.println(val);
	callbackpage.Click_Filter_button();
	callbackpage.Verify_Filter_working_Callback(col,val);
	}
		finally
		{
		callbackpage.signout();	
		}	
}

@Test(priority=11, enabled=true, testName = "Verify the sorting works in Callback page - Phone Number", description = "Navigate to Callback page and verify search is working properly for 'Phone Number' filter")
public void Verify_Filter_in_Callback_page_Phone_Number()
{
	try
		{
	String col = "Phone Number";
	loginpage.login_as(admin_username, admin_password);
	loginpage.verifyLogin(admin_username);
	ges_ver = loginpage.getGESVersion();
	System.out.println("\n The GES version is : "+ges_ver);
	callbackpage.Click_Callback_sort(col);
	String val = callbackpage.Fetch_Callback_row(col);
	System.out.println(val);
	callbackpage.Click_Filter_button();
	callbackpage.Verify_Filter_working_Callback(col,val);
	}
		finally
		{
		callbackpage.signout();	
		}	
}

@Test(priority=12, enabled=true, testName = "Verify the sorting works in Callback page - Service Name", description = "Navigate to Callback page and verify search is working properly for 'Service Name' filter")
public void Verify_Filter_in_Callback_page_Service_Name()
{
	try
		{
	String col = "Service Name";
	loginpage.login_as(admin_username, admin_password);
	loginpage.verifyLogin(admin_username);
	ges_ver = loginpage.getGESVersion();
	System.out.println("\n The GES version is : "+ges_ver);
	callbackpage.Click_Callback_sort(col);
	String val = callbackpage.Fetch_Callback_row(col);
	System.out.println(val);
	callbackpage.Click_Filter_button();
	callbackpage.Verify_Filter_working_Callback(col,val);
	}
		finally
		{
		callbackpage.signout();	
		}	
}

@Test(priority=13, enabled=true, testName = "Verify the sorting works in Callback page - Time Updated", description = "Navigate to Callback page and verify search is working properly for 'Time Updated' filter")
public void Verify_Filter_in_Callback_page_Desired_Time_Updated()
{
	try
		{
	String col = "Time Updated";
	loginpage.login_as(admin_username, admin_password);
	loginpage.verifyLogin(admin_username);
	ges_ver = loginpage.getGESVersion();
	System.out.println("\n The GES version is : "+ges_ver);
	callbackpage.Click_Callback_sort(col);
	String val = callbackpage.Fetch_Callback_row(col);
	System.out.println(val);
	callbackpage.Click_Filter_button();
	callbackpage.Verify_Filter_working_Callback(col,val);
	}
		finally
		{
		callbackpage.signout();	
		}	
}

@Test(priority=14, enabled=true, testName = "Verify the Pagination in Callback page - Next", description = "Navigate to Callback page and verify pagination is working properly for 'Next' button")
public void Verify_Navigation_in_Callback_page_Next()
{
	try
		{
			String slot = "Last 14 Days";
			loginpage.login_as(admin_username, admin_password);
			loginpage.verifyLogin(admin_username);
			ges_ver = loginpage.getGESVersion();
			System.out.println("\n The GES version is : "+ges_ver);
			callbackpage.filterUIDate(slot);
			if ((callbackpage.Fetch_Max_Page())>1)
			{
				callbackpage.Click_Next_button();
				callbackpage.Verify_Navigation("2");
				System.out.println("\n The navigation for next button is working properly");
			}
		}
	finally
		{
			callbackpage.signout();	
		}
}

@Test(priority=15, enabled=true, testName = "Verify the Pagination in Callback page - Last", description = "Navigate to Callback page and verify pagination is working properly for 'Last' button")
public void Verify_Navigation_in_Callback_page_Last()
{
	try
		{
			String slot = "Last 14 Days";
			loginpage.login_as(admin_username, admin_password);
			loginpage.verifyLogin(admin_username);
			ges_ver = loginpage.getGESVersion();
			System.out.println("\n The GES version is : "+ges_ver);
			callbackpage.filterUIDate(slot);
			if ((callbackpage.Fetch_Max_Page())>1)
			{
				callbackpage.Click_Last_button();
				callbackpage.Verify_Navigation("100");
				System.out.println("\n The navigation for Last button is working properly");
			}
		}
	finally
		{
		callbackpage.signout();	
		}
}

@Test(priority=16, enabled=true, testName = "Verify the Pagination in Callback page - Previous", description = "Navigate to Callback page and verify pagination is working properly for 'Previous' button")
public void Verify_Navigation_in_Callback_page_Previous()
{
	try
		{
			String slot = "Last 14 Days";
			loginpage.login_as(admin_username, admin_password);
			loginpage.verifyLogin(admin_username);
			ges_ver = loginpage.getGESVersion();
			System.out.println("\n The GES version is : "+ges_ver);
			callbackpage.filterUIDate(slot);
			if ((callbackpage.Fetch_Max_Page())>1)
			{
				callbackpage.Click_Next_button();
				//callbackpage.Click_Next_button();
				callbackpage.Click_Previous_button();
				callbackpage.Verify_Navigation("1");
				System.out.println("\n The navigation for Previous button is working properly");
			}	
		}
	finally
		{
			callbackpage.signout();
		}
}

@Test(priority=17, enabled=true, testName = "Verify the Pagination in Callback page - First", description = "Navigate to Callback page and verify pagination is working properly for 'First' button")
public void Verify_Navigation_in_Callback_page_First()
{
	try
		{
			String slot = "Last 14 Days";
			loginpage.login_as(admin_username, admin_password);
			loginpage.verifyLogin(admin_username);
			ges_ver = loginpage.getGESVersion();
			System.out.println("\n The GES version is : "+ges_ver);
			callbackpage.filterUIDate(slot);
			if ((callbackpage.Fetch_Max_Page())>1)
			{
				callbackpage.Click_Next_button();
				callbackpage.Click_First_button();
				callbackpage.Verify_Navigation("1");
				System.out.println("\n The navigation for First button is working properly");
			}
		}
	finally
		{
			callbackpage.signout();	
		}
}

@Test(priority=18, enabled=true, groups = {"callback"}, testName = "Create and Verify a Scheduled Callback in Next 24 Hours", description = "Create a Scheduled Callback and verify if it's created successfully. Finally cancel the created callback")

public void Create_Callback_Scheduled_24_hours(){
	try
		{
	String ph_no = Create_new_phoneNumber();
	int add_days = 1;
	int add_slots = 2;
	String d= "Next 24 Hours";
	loginpage.login_as(admin_username, admin_password);
	loginpage.verifyLogin(admin_username);
	ges_ver = loginpage.getGESVersion();
	System.out.println("\n The GES version is : "+ges_ver);
	callbackpage.createScheduledCallback_and_verify(ph_no,add_days,add_slots,d);
	callbackpage.displayCallbackDetails_toConsole(ph_no);
	callbackpage.cancel_Callback(ph_no);
	}
		finally
		{
		callbackpage.signout();	
		}
}

@Test(priority=19, enabled=true, groups = {"callback"}, testName = "Create and Verify a Scheduled Callback in Next 7 Days", description = "Create a Scheduled Callback and verify if it's created successfully. Finally cancel the created callback")

public void Create_Callback_Scheduled_Next_7_days(){
	try
		{
	String ph_no = Create_new_phoneNumber();
	int add_days = 5;
	int add_slots = 2;
	String d= "Next 7 Days";
	loginpage.login_as(admin_username, admin_password);
	loginpage.verifyLogin(admin_username);
	ges_ver = loginpage.getGESVersion();
	System.out.println("\n The GES version is : "+ges_ver);
	callbackpage.createScheduledCallback_and_verify(ph_no,add_days,add_slots,d);
	callbackpage.displayCallbackDetails_toConsole(ph_no);
	callbackpage.cancel_Callback(ph_no);
	}
		finally
		{
		callbackpage.signout();	
		}
}

@Test(priority=20, enabled=true, groups = {"callback"}, testName = "Create and Verify a Scheduled Callback in Next 14 Days", description = "Create a Scheduled Callback and verify if it's created successfully. Finally cancel the created callback")

public void Create_Callback_Scheduled_Next_14_days(){
	try
		{
	String ph_no = Create_new_phoneNumber();
	int add_days = 10;
	int add_slots = 2;
	String d= "Next 2 Weeks";
	loginpage.login_as(admin_username, admin_password);
	loginpage.verifyLogin(admin_username);
	ges_ver = loginpage.getGESVersion();
	System.out.println("\n The GES version is : "+ges_ver);
	callbackpage.createScheduledCallback_and_verify(ph_no,add_days,add_slots,d);
	callbackpage.displayCallbackDetails_toConsole(ph_no);
	callbackpage.cancel_Callback(ph_no);
	}
		finally
		{
		callbackpage.signout();	
		}
}

@Test(priority=21, enabled=true, testName = "Verify the filter works in Callback page - Completed Reason", description = "Navigate to Callback page and verify search is working properly for 'Completed Reason' filter")
public void Verify_Filter_in_Callback_page_Completed_Reason()
{
	try
		{
	String col = "State";
	String col1 = "Completed Reason";
	loginpage.login_as(admin_username, admin_password);
	loginpage.verifyLogin(admin_username);
	ges_ver = loginpage.getGESVersion();
	System.out.println("\n The GES version is : "+ges_ver);
	callbackpage.Click_Callback_sort(col);
	String val = callbackpage.Fetch_Callback_row(col1);
	System.out.println(val);
	callbackpage.Click_Filter_button();
	callbackpage.Verify_Filter_working_Callback(col1,val);
	}
		finally
		{
		callbackpage.signout();	
		}	
}

@Test(priority=22, enabled=true, groups = {"callback"}, testName = "Create and Verify a Custom date range with space in the name", description = "Create a Custom date range and verify if it's created successfully. Name should have a space included")

public void Create_CustomDateRange_with_namespace(){
	loginpage.login_as(admin_username, admin_password);
	loginpage.verifyLogin(admin_username);
	ges_ver = loginpage.getGESVersion();
	System.out.println("\n The GES version is : "+ges_ver);
	callbackpage.Click_AdvancedOptions();
	callbackpage.Click_Add_CustomDateRange();
	callbackpage.Click_AdvancedOptions();
	callbackpage.Delete_CustomDateRange();
	callbackpage.signout();
}

@Test(priority=23, enabled=true, groups = {"callback"}, testName = "Create and Verify an Immediate Callback with + prefix", description = "Create an Immediate Callback with + prefix and verify if it's created successfully. Finally cancel the created callback")

public void Create_Callback_Immediate_with_prefix() throws IOException{
	try
	{
	String ph_no = "+91"+ Create_new_phoneNumber();
	loginpage.login_as(admin_username, admin_password);
	loginpage.verifyLogin(admin_username);
	callbackpage.createImmediateCallback_and_verify(ph_no);
	callbackpage.displayCallbackDetails_toConsole(ph_no);
	callbackpage.cancel_Callback(ph_no);
	}
	finally
	{
	callbackpage.signout();	
	}
}

@Test(priority=24, enabled=true, groups = {"callback"}, testName = "Create Immediate Callback without + prefix and verify throttle limit", description = "Create Immediate Callback without + prefix and verify throttle limit reached error. Finally cancel the created callback")

public void Create_Callback_without_prefix_and_verify_throttle_limit() throws IOException{
	try
	{
	//Throttle limit is 3 for GES_UI_AUTO_VQ
	int throttle_limit = 3;
	String ph_no = "91"+ Create_new_phoneNumber();
	loginpage.login_as(admin_username, admin_password);
	loginpage.verifyLogin(admin_username);
	while (throttle_limit > 0) {
		callbackpage.createImmediateCallback_and_verify(ph_no);
		callbackpage.displayCallbackDetails_toConsole(ph_no);
		throttle_limit--;
	}
	callbackpage.createImmediateCallback_and_verify_throttle_limit_reached(ph_no);
	callbackpage.createImmediateCallback_and_verify_throttle_limit_reached("+"+ ph_no);
	callbackpage.cancel_multiple_Callback(ph_no);
	}
	
	finally
	{
	callbackpage.signout();	
	}
}

@Test(priority=25, enabled=true, groups = {"callback"}, testName = "Create Immediate Callback with + prefix and verify throttle limit", description = "Create Immediate Callback with + prefix and verify throttle limit reached error. Finally cancel the created callback")

public void Create_Callback_with_prefix_and_verify_throttle_limit() throws IOException{
	try
	{
	//Throttle limit is 3 for GES_UI_AUTO_VQ
	int throttle_limit = 3;
	String ph_no = "+91"+ Create_new_phoneNumber();
	loginpage.login_as(admin_username, admin_password);
	loginpage.verifyLogin(admin_username);
	while (throttle_limit > 0) {
		callbackpage.createImmediateCallback_and_verify(ph_no);
		callbackpage.displayCallbackDetails_toConsole(ph_no);
		throttle_limit--;
	}
	callbackpage.createImmediateCallback_and_verify_throttle_limit_reached(ph_no);
	callbackpage.createImmediateCallback_and_verify_throttle_limit_reached(ph_no.replace("+", ""));
	callbackpage.cancel_multiple_Callback(ph_no);
	}
	
	finally
	{
	callbackpage.signout();	
	}
}

}
