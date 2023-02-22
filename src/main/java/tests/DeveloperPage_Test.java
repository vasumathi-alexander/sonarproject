package ges.tests;

import org.json.simple.parser.ParseException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import ges.pages.CallbackPage;
import ges.pages.CallbackPage_Search;
import ges.pages.CallinPage;
import ges.pages.DeveloperPage;
import ges.pages.DeveloperPage_CredentialManagement;
import ges.pages.LoginPage;
import ges.util.BaseTest;

public class DeveloperPage_Test extends BaseTest {
	
	LoginPage loginpage = new LoginPage();
	CallbackPage callbackpage = new CallbackPage();
	DeveloperPage devpage = new DeveloperPage();
	DeveloperPage_CredentialManagement devpage_credmgmt = new DeveloperPage_CredentialManagement();
	CallbackPage_Search callback_search = new CallbackPage_Search();
	CallinPage ctci = new CallinPage();
	public String ges_ver;
	
	/****************************************************************************/
	
	private String admin_username = testdata_rbac_property_file_reader("Admin_username");
	private String admin_password = testdata_rbac_property_file_reader("Admin_password");
	private String invalid_password = "wrongpass";
	
	/****************************************************************************/
	
	@BeforeClass
	public void beforeClass() {
	System.out.println(
			"***************************************************************************************** \n\n");
	System.out.println("				Running Functional UI testcases - Developer Tab Tests");
	System.out.println(
			"\n\n*****************************************************************************************");
	}
	
	@Test(priority=1, enabled=true, groups = "developer", testName = "Validate API Key - valid API Key provided", description = "Enter a valid API Key and validate the response message")
	
	public void Validate_API_Key_validAPIKey() {
		try
		{
		loginpage.login_as(admin_username, admin_password);
		loginpage.verifyLogin(admin_username);
		devpage.Navigate_To_DeveloperTab();
		devpage.Select_APIKeyValidation_Option();
		devpage.Enter_APIKey_and_Validate();
		}
		finally
		{
		callbackpage.signout();	
		}
	}
	
@Test(priority=2, enabled=true, groups = "developer", testName = "Validate API Key - invalid API Key provided", description = "Enter an invalid API Key and validate the response message")
	
	public void Validate_API_Key_invalidAPIKey() {
		try
		{
		loginpage.login_as(admin_username, admin_password);
		loginpage.verifyLogin(admin_username);
		devpage.Navigate_To_DeveloperTab();
		devpage.Select_APIKeyValidation_Option();
		devpage.Enter_invalidAPIKey_and_Validate();
		}
		finally
		{
		callbackpage.signout();	
		}
	}

@Test(priority=3, enabled=true, groups = "developer", testName = "Validate API Key - No API Key provided", description = "Enter No API Key and validate the response message")

public void Validate_API_Key_noAPIKey() {
	try
		{
	loginpage.login_as(admin_username, admin_password);
	loginpage.verifyLogin(admin_username);
	devpage.Navigate_To_DeveloperTab();
	devpage.Select_APIKeyValidation_Option();
	devpage.Enter_noAPIKey_and_Validate();
	}
		finally
		{
		callbackpage.signout();	
		}
}

@Test(priority=4, enabled=true, groups = {"developer"}, testName = "Developer Errors page - column verification", description = "Verify the column headers in the developer page - errors")

public void Verify_Column_Headers_Errors_Tab() {
	try
		{
	loginpage.login_as(admin_username, admin_password);
	loginpage.verifyLogin(admin_username);
	devpage.Navigate_To_DeveloperTab();
	devpage.Navigate_to_ErrorsOption();
	devpage.verify_column_headers_error_tab();
	}
		finally
		{
		callbackpage.signout();	
		}
}

@Test(priority=5, enabled=true, groups = "developer", testName = "Verify API error in Errors page using correlation ID", description = "Create a call-in with improper phone number.Fetch the correlation ID and verify it in UI")

public void Verify_Errors_Errors_Page() throws ParseException {
	try
		{
	String invalid_ph_no = "last";
	ctci.createCall_in_invalid(invalid_ph_no);
	String corr_id = ctci.fetchCorrelationID();
	loginpage.login_as(admin_username, admin_password);
	loginpage.verifyLogin(admin_username);
	devpage.Navigate_To_DeveloperTab();
	devpage.Navigate_to_ErrorsOption();
	devpage.Enter_Correlation_ID(corr_id);
	devpage.verify_error_details();
	devpage.display_error_details();
	devpage.display_sidepane_details();
	}
		finally
		{
		callbackpage.signout();	
		}
}




@Test(priority=6, enabled=true, groups = "developer", testName = "Verify invalid GWS Credentials throws error message", description = "Navigate to GWS credntials tab and check the error response of invalid login details")

public void Verify_invalid_GWS_Credentials_Page() throws ParseException {
	try
		{
	loginpage.login_as(admin_username, admin_password);
	loginpage.verifyLogin(admin_username);
	devpage.Navigate_To_DeveloperTab();
	devpage_credmgmt.Select_CredentialManagement_Option();
	devpage.Clear_all_notifications();
	devpage.Navigate_to_GWS_Credentials();
	devpage.Enter_username(admin_username);
	devpage.Enter_password(invalid_password);
	devpage.Click_Update_button();
	devpage.Verify_invalid_GWS_Credentials();
	}
		finally
		{
		callbackpage.signout();	
		}
}

@Test(priority=7, enabled=true, groups = "developer", testName = "Verify GWS Credentials are deleted successfully", description = "Navigate to GWS credntials tab, update the credentials and delete the credentials")

public void Verify_delete_option_GWS_Credentials_Page() throws ParseException {
	try
		{
	loginpage.login_as(admin_username, admin_password);
	loginpage.verifyLogin(admin_username);
	devpage.Navigate_To_DeveloperTab();
	devpage_credmgmt.Select_CredentialManagement_Option();
	devpage.Clear_all_notifications();
	devpage.Navigate_to_GWS_Credentials();
	devpage.Enter_username(admin_username);
	devpage.Enter_password(admin_password);
	devpage.Click_Update_button();
	devpage.Click_Delete_button();
	devpage.Verify_delete_GWS_Credentials();
	}
		finally
		{
		callbackpage.signout();	
		}
}

@Test(priority=8, enabled=true, groups = "developer", testName = "Verify error response for empty GWS username ", description = "Navigate to GWS credntials tab, update the credentials without filling the username")

public void Verify_error_null_username() throws ParseException {
	try
		{
	loginpage.login_as(admin_username, admin_password);
	loginpage.verifyLogin(admin_username);
	devpage.Navigate_To_DeveloperTab();
	devpage_credmgmt.Select_CredentialManagement_Option();
	devpage.Navigate_to_GWS_Credentials();
	devpage.Enter_password(admin_password);
	devpage.Click_Update_button();
	devpage.Verify_error_GWS_Credentials(1);
	}
		finally
		{
		callbackpage.signout();	
		}
}

@Test(priority=9, enabled=true, groups = "developer", testName = "Verify error response for empty GWS password ", description = "Navigate to GWS credntials tab, update the credentials without filling the password")

public void Verify_error_null_password() throws ParseException {
	try
		{
	loginpage.login_as(admin_username, admin_password);
	loginpage.verifyLogin(admin_username);
	devpage.Navigate_To_DeveloperTab();
	devpage_credmgmt.Select_CredentialManagement_Option();
	devpage.Navigate_to_GWS_Credentials();
	devpage.Enter_username(admin_username);
	devpage.Click_Update_button();
	devpage.Verify_error_GWS_Credentials(2);
	}
		finally
		{
		callbackpage.signout();	
		}
}

@Test(priority=10, enabled=true, groups = "developer", testName = "Verify valid GWS Credentials tab", description = "Navigate to GWS credntials tab and check the validation of valid login details")

public void Verify_valid_GWS_Credentials_Page() throws ParseException {
	try
		{
	loginpage.login_as(admin_username, admin_password);
	loginpage.verifyLogin(admin_username);
	devpage.Navigate_To_DeveloperTab();
	devpage_credmgmt.Select_CredentialManagement_Option();
	devpage.Navigate_to_GWS_Credentials();
	devpage.Clear_all_notifications();
	devpage.Enter_username(admin_username);
	devpage.Enter_password(admin_password);
	devpage.Click_Update_button();
	devpage.Verify_GWS_Credentials();
	}
		finally
		{
		callbackpage.signout();	
		}
}

}


