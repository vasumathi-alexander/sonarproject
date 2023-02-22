package ges.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import ges.pages.CallbackPage;
import ges.pages.LoginPage;
import ges.util.BaseTest;

public class LoginPage_Test extends BaseTest {
	
	/****************************************************************************/
	
	LoginPage loginpage = new LoginPage();
	CallbackPage callbackpage = new CallbackPage();
	
	/****************************************************************************/
	
	private String admin_username = testdata_property_file_reader("Admin_username");
	private String admin_password = testdata_property_file_reader("Admin_password");
	private String invalid_admin_password = "Invalid_Password";
	
	/****************************************************************************/

	@BeforeClass
	
	public void beforeClass() {
		
	System.out.println(
			"***************************************************************************************** \n\n");
	System.out.println("				Running Functional UI testcases - Login Page");
	System.out.println(
			"\n\n*****************************************************************************************");
	}
	
	@Test(priority=1, enabled=true, groups = {"login"}, testName = "Login - valid credential", description = "Login with valid credential and verify")
	public void Login_valid_credential() {
		try
		{
		//System.out.print(loginpage);
		loginpage.login_as(admin_username, admin_password);
		loginpage.verifyLogin(admin_username);
		callbackpage.filterUIwithServiceName();
		}
		finally
		{
		callbackpage.signout();	
		}
	}
	
	@Test(priority=2, enabled=true, groups = {"login"}, testName = "Login - invalid credential", description = "Login with invalid credential and verify error message displayed")
	public void Login_invalid_credential() {
		loginpage.login_as(admin_username, invalid_admin_password);
		loginpage.verifyLoginError("Could not sign you in. Verify your credentials and try again.");
	}
}
