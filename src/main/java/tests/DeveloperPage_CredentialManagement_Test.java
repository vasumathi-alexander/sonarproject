package ges.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import ges.pages.CallbackPage;
import ges.pages.CallbackPage_Search;
import ges.pages.DeveloperPage;
import ges.pages.DeveloperPage_CredentialManagement;
import ges.pages.LoginPage;
import ges.util.BaseTest;

public class DeveloperPage_CredentialManagement_Test extends BaseTest {
	
	/****************************************************************************/
	
	LoginPage loginpage = new LoginPage();
	CallbackPage callbackpage = new CallbackPage();
	DeveloperPage devpage = new DeveloperPage();
	DeveloperPage_CredentialManagement devpage_credmgmt = new DeveloperPage_CredentialManagement();
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
	System.out.println("				Running Functional UI testcases - Credential Management Tab");
	System.out.println(
			"\n\n*****************************************************************************************");
	}
	
	@Test(priority=1, enabled=true, groups = {"push"}, testName = "Update and verify message with Invalid Credentials", description = "Enter the invalid Firebase credential, Click on Update and verify the message in the push notification")
	public void Update_Test_Firebase_Invalid_Credential() {
		try
		{		
		loginpage.login_as(admin_username, admin_password);
		loginpage.verifyLogin(admin_username);
		devpage.Navigate_To_DeveloperTab();
		devpage_credmgmt.Select_CredentialManagement_Option();
		devpage_credmgmt.Click_PushNotification_tab();
		devpage_credmgmt.Clear_all_notifications();
		devpage_credmgmt.Enter_invalid_Firebase_Credentials();
		devpage_credmgmt.Click_UpdateCredentials();
		devpage_credmgmt.verifyErrorMessageNotification();
		devpage_credmgmt.Clear_all_notifications();
		}
		finally
		{
		callbackpage.signout();	
		}
	}

	@Test(priority=2, enabled=true, groups = {"push"}, testName = "Update and verify message with valid Firebase Credentials", description = "Enter the valid Firebase credential, Click on Update and verify the message in the push notification")
	public void Update_Test_Firebase_Valid_Credential() {
		try
		{
		loginpage.login_as(admin_username, admin_password);
		loginpage.verifyLogin(admin_username);
		devpage.Navigate_To_DeveloperTab();
		devpage_credmgmt.Select_CredentialManagement_Option();
		devpage_credmgmt.Click_PushNotification_tab();
		devpage_credmgmt.Clear_all_notifications();
		devpage_credmgmt.Enter_valid_Firebase_Credentials();
		devpage_credmgmt.Click_UpdateCredentials();
		devpage_credmgmt.verifySuccessMessageNotification();
		devpage_credmgmt.Clear_all_notifications();
		}
		finally
		{
		callbackpage.signout();	
		}
	}
	
	@Test(priority=3, enabled=true, groups= {"push"}, testName = "Test Firebase Credentials - valid Sender ID", description = "Enter a valid firebase credential , update credential and enter a valid sender ID to test the firebase credential ")
	public void Test_Firebase_Credential_Valid_SenderID() {
		try
		{
		loginpage.login_as(admin_username, admin_password);
		loginpage.verifyLogin(admin_username);
		devpage.Navigate_To_DeveloperTab();
		devpage_credmgmt.Select_CredentialManagement_Option();
		devpage_credmgmt.Click_PushNotification_tab();
		devpage_credmgmt.Clear_all_notifications();
		devpage_credmgmt.Enter_valid_Firebase_Credentials();
		devpage_credmgmt.Click_UpdateCredentials();
		devpage_credmgmt.verifySuccessMessageNotification();
		devpage_credmgmt.enter_valid_SenderID();
		devpage_credmgmt.click_test_button();
		devpage_credmgmt.Clear_all_notifications();
		}
		finally
		{
		callbackpage.signout();	
		}
	}
	
	/*
	@Test(priority=3, enabled=true, groups= {"push"}, testName = "Test New HTTP Channel - Invalid channel details", description = "Enter invalid http channel details and verify the error message")
	public void Test_New_HTTP_Channel_invalid_data() {
		try
		{
		loginpage.login_as(admin_username, admin_password);
		loginpage.verifyLogin(admin_username);
		devpage.Navigate_To_DeveloperTab();
		devpage_credmgmt.Select_CredentialManagement_Option();
		devpage_credmgmt.Click_PushNotification_tab();
		devpage_credmgmt.Clear_all_notifications();
		devpage_credmgmt.Click_NewHTTPChannel();
		devpage_credmgmt.verifyHttpHeaders();
		devpage_credmgmt.Enter_invalid_http_channel_details();
		devpage_credmgmt.Click_UpdateHTTPChannel();
		devpage_credmgmt.verifyHTTPErrorMessage();
		devpage_credmgmt.Clear_all_notifications();
		}
		finally
		{
		callbackpage.signout();	
		}
	}
	
	@Test(priority=4, enabled=true, groups= {"push"}, testName = "Test New HTTP Channel - valid channel details", description = "Enter a valid http channel details and verify the success message")
	public void Test_New_HTTP_Channel_valid_data() {
		try
		{
		loginpage.login_as(admin_username, admin_password);
		loginpage.verifyLogin(admin_username);
		devpage.Navigate_To_DeveloperTab();
		devpage_credmgmt.Select_CredentialManagement_Option();
		devpage_credmgmt.Click_PushNotification_tab();
		devpage_credmgmt.Clear_all_notifications();
		devpage_credmgmt.Click_NewHTTPChannel();
		devpage_credmgmt.verifyHttpHeaders();
		devpage_credmgmt.Enter_valid_http_channel_details();
		devpage_credmgmt.Click_UpdateHTTPChannel();
		devpage_credmgmt.verifySuccessMessageNotification();
		devpage_credmgmt.Clear_all_notifications();
		}
		finally
		{
		callbackpage.signout();	
		}
	}
	
	@Test(priority=5, enabled=true, groups= {"push"}, testName = "Test New HTTP Channel - delete an existing channel", description = "Delete an existing HTTP Channel and verify the status message")
	public void Test_New_HTTP_Channel_Delete_Existing_Channel() {
		try
		{
		loginpage.login_as(admin_username, admin_password);
		loginpage.verifyLogin(admin_username);
		devpage.Navigate_To_DeveloperTab();
		devpage_credmgmt.Select_CredentialManagement_Option();
		devpage_credmgmt.Click_PushNotification_tab();
		devpage_credmgmt.Clear_all_notifications();
		devpage_credmgmt.Select_ExistingHTTPChannel();
		devpage_credmgmt.verifyHttpHeaders();
		devpage_credmgmt.Click_DeleteHTTPChannel();
		devpage_credmgmt.verifyHTTPDeleteMessageNotification();
		devpage_credmgmt.Clear_all_notifications();
		}
		finally
		{
		callbackpage.signout();	
		}
	}
	
	@Test(priority=6, enabled=true, groups= {"push"}, testName = "Test New HTTP Channel - delete an existing channel", description = "Delete an existing HTTP Channel and verify the status message")
	public void Test_HTTP_Endpoint_with_wrong_configuration() {
		try
		{
		loginpage.login_as(admin_username, admin_password);
		loginpage.verifyLogin(admin_username);
		devpage.Navigate_To_DeveloperTab();
		devpage_credmgmt.Select_CredentialManagement_Option();
		devpage_credmgmt.Click_PushNotification_tab();
		devpage_credmgmt.Clear_all_notifications();
		devpage_credmgmt.Click_NewHTTPChannel();
		devpage_credmgmt.verifyHttpHeaders();
		devpage_credmgmt.Enter_invalid_http_endpoint_details();
		devpage_credmgmt.click_test_button();
		devpage_credmgmt.verifyTestHTTPEndpointErrorMessage();
		devpage_credmgmt.Clear_all_notifications();
		}
		finally
		{
		callbackpage.signout();	
		}
	}
	*/
	@Test(priority=7, enabled=true, groups= {"captcha"}, testName = "Verify New Captcha Key set is added successfully - V2 Visible", description = "Enter the valid captcha credentials and click on update and verify that it has been updated successfully")
	public void Test_Valid_Captcha_V2Visible() {
		try
		{
		loginpage.login_as(admin_username, admin_password);
		loginpage.verifyLogin(admin_username);
		devpage.Navigate_To_DeveloperTab();
		devpage_credmgmt.Select_CredentialManagement_Option();
		devpage_credmgmt.Click_Captcha_tab();
		devpage_credmgmt.Click_AddNew_Captcha_KeySet();
		devpage_credmgmt.Select_CaptchaType("v2-visible");
		devpage_credmgmt.Enter_Captcha_Credentials_v2vis();
		devpage_credmgmt.Save_Captcha();
		devpage_credmgmt.Verify_Success_Message();
		devpage_credmgmt.Click_GenerateTestPage();
		//devpage_credmgmt.Verify_Submit_Button_Captcha();
		devpage_credmgmt.Delete_Captcha();
		}
		finally
		{
		callbackpage.signout();	
		}
	}
	
	@Test(priority=8, enabled=true, groups= {"captcha"}, testName = "Verify New Captcha Key set is added successfully - V2 Invisible", description = "Enter the valid captcha credentials and click on update and verify that it has been updated successfully")
	public void Test_Valid_Captcha_V2Invisible() {
		try
		{
		loginpage.login_as(admin_username, admin_password);
		loginpage.verifyLogin(admin_username);
		devpage.Navigate_To_DeveloperTab();
		devpage_credmgmt.Select_CredentialManagement_Option();
		devpage_credmgmt.Click_Captcha_tab();
		devpage_credmgmt.Click_AddNew_Captcha_KeySet();
		devpage_credmgmt.Select_CaptchaType("v2-invisible");
		devpage_credmgmt.Enter_Captcha_Credentials_v2invis();
		devpage_credmgmt.Save_Captcha();
		devpage_credmgmt.Verify_Success_Message();
		devpage_credmgmt.Click_GenerateTestPage();
		//devpage_credmgmt.Verify_Submit_Button_Captcha();
		devpage_credmgmt.Delete_Captcha();
		}
		finally
		{
		callbackpage.signout();	
		}
	}

	@Test(priority=9, enabled=true, groups= {"captcha"}, testName = "Verify New Captcha Key set is added successfully - V3", description = "Enter the valid captcha credentials and click on update and verify that it has been updated successfully")
	public void Test_Valid_Captcha_V3() {
	try
		{
	loginpage.login_as(admin_username, admin_password);
	loginpage.verifyLogin(admin_username);
	devpage.Navigate_To_DeveloperTab();
	devpage_credmgmt.Select_CredentialManagement_Option();
	devpage_credmgmt.Click_Captcha_tab();
	devpage_credmgmt.Click_AddNew_Captcha_KeySet();
	devpage_credmgmt.Select_CaptchaType("v3");
	devpage_credmgmt.Enter_Captcha_Credentials_v3();
	devpage_credmgmt.Save_Captcha();
	devpage_credmgmt.Verify_Success_Message();
	devpage_credmgmt.Click_GenerateTestPage();
	//devpage_credmgmt.Verify_Submit_Button_Captcha();
	devpage_credmgmt.Delete_Captcha();
	}
		finally
		{
		callbackpage.signout();	
		}
	}
	
}
