package ges.tests;

import java.util.Random;

import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import ges.pages.CallbackPage;
import ges.pages.CallinPage;
import ges.pages.DeveloperPage;
import ges.pages.LoginPage;
import ges.util.BaseTest;
import ges.util.BaseTest_API;
import io.restassured.response.Response;

public class CallInPage_Test extends BaseTest{
	
	LoginPage loginpage = new LoginPage();
	CallbackPage callbackpage = new CallbackPage();
	DeveloperPage devpage = new DeveloperPage();
	CallinPage ctci = new CallinPage();
	public String id = null;
	public String ani = null;
	
	/****************************************************************************/
	
	private String admin_username = testdata_rbac_property_file_reader("Admin_username");
	private String admin_password = testdata_rbac_property_file_reader("Admin_password");
	
	/****************************************************************************/
	
	@BeforeClass
	public void beforeClass() {
	System.out.println(
			"***************************************************************************************** \n\n");
	System.out.println("				Running Functional UI testcases - CTCI Tests");
	System.out.println(
			"\n\n*****************************************************************************************");
	}
    
    @Test(priority=1, enabled=true, testName = "Verify Call-in created successfully in UI",groups = {"ctci"},description = "Create a Call-In and perform UI page verification for the created Call In")
    public void Verify_CTCI_creation_UI() throws ParseException{
    	try
		{
    	//String ph_no = ctci.Create_new_phoneNumber();
    	String ph_no = Create_new_phoneNumber();
		ctci.createCall_in(ph_no);
		id = ctci.getID();
		
		loginpage.login_as(admin_username, admin_password);
		loginpage.verifyLogin(admin_username);
		ctci.Navigate_To_CallIn_Tab();
		ctci.Click_Refresh();
		ctci.Verify_created_CallIn(id);
		ctci.Click_expiry_time();
		ctci.display_callin_details_toConsole(ph_no);
		}
		finally
		{
		callbackpage.signout();	
		}
    }
    
    @Test(priority=2, enabled=true, testName = "Verify Call-in column headers are displayed properly in UI",groups = {"ctci"},description = "Verify Call-In page columns verification")
    public void Verify_CTCI_column_headers() throws ParseException{
		try
		{
		loginpage.login_as(admin_username, admin_password);
		loginpage.verifyLogin(admin_username);
		ctci.Navigate_To_CallIn_Tab();
		ctci.Verify_Callin_Column_Header();
		}
		finally
		{
		callbackpage.signout();	
		}
    }
    
    @Test(priority=3, enabled=true, testName = "Verify Call-in created successfully in UI and displayed in filter by ID",groups = {"ctci"},description = "Create a call-in, verify the callin in UI and apply filter by ID. Verify Call-in is displayed in the filter")
    public void Verify_CTCI_Filter_by_ID () throws ParseException{
try
		{
    	String ph_no = Create_new_phoneNumber();

		ctci.createCall_in(ph_no);
		id = ctci.getID();
		
		loginpage.login_as(admin_username, admin_password);
		loginpage.verifyLogin(admin_username);
		ctci.Navigate_To_CallIn_Tab();
		ctci.Click_Refresh();
		ctci.Verify_created_CallIn(id);
		ctci.Click_expiry_time();
		ctci.display_callin_details_toConsole(ph_no);
		ctci.applyFilter_and_enterID(id);
		ctci.display_callin_details_toConsole(ph_no);
		}
		finally
		{
		callbackpage.signout();	
		}
    }
    
    @Test(priority=4, enabled=true, testName = "Verify Call-in created successfully in UI and verify total number of call-ins created",groups = {"ctci"},description = "Create a call-in, verify the callin in UI and verify the total number of call-ins created in UI")
    public void Verify_CTCI_Number_of_Rows() throws ParseException{
try
		{
    	String ph_no = Create_new_phoneNumber();

		ctci.createCall_in(ph_no);
		id = ctci.getID();
		
		loginpage.login_as(admin_username, admin_password);
		loginpage.verifyLogin(admin_username);
		ctci.Navigate_To_CallIn_Tab();
		ctci.Click_Refresh();
		ctci.Verify_created_CallIn(id);
		ctci.Click_expiry_time();
		ctci.display_callin_details_toConsole(ph_no);
		ctci.calculate_no_of_callin_rows();
		}
		finally
		{
		callbackpage.signout();	
		}
    }
    
    @Test(priority=5, enabled=true, testName = "Verify Call-in created successfully in UI and displayed in filter by ANI",groups = {"ctci"},description = "Create a call-in, verify the callin in UI and apply filter by ANI. Verify Call-in is displayed in the filter")
    public void Verify_CTCI_Filter_by_ANI() throws ParseException{
try
		{
    	String ph_no = Create_new_phoneNumber();
    	ani = ph_no;
		ctci.createCall_in(ph_no);
		loginpage.login_as(admin_username, admin_password);
		loginpage.verifyLogin(admin_username);
		ctci.Navigate_To_CallIn_Tab();
		ctci.Click_Refresh();
		ctci.Click_expiry_time();
		ctci.display_callin_details_toConsole(ph_no);
		ani = ph_no;
		ctci.applyFilter_and_enterANI(ani);
		ctci.display_callin_details_toConsole(ph_no);
		}
		finally
		{
		callbackpage.signout();	
		}
    }
    
}

