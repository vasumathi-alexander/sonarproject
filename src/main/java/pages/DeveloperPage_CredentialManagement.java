package ges.pages;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;

import ges.util.BaseTest;
import ges.util.DriverManager;
import junit.framework.Assert;

public class DeveloperPage_CredentialManagement extends BaseTest{
	
	/**************************************************************************/
	/******* Navigation Elements ***********/
	
	private By dev_credmgmt_option = By.xpath("//div[@value='credentialManagement']");
	private By push_notification_tab = By.id("tab-item-push");
	private By captcha_tab = By.id("tab-item-captcha");
	
	/**************************************************************************/

	/******* Credential Management Elements - Push Notification ***********/
	
	private By txtbox_projname = By.id("input-project-name");
	private By txtbox_clientemail = By.id("input-client-email");
	private By txtbox_privatekey = By.id("input-private-key");
	private By btn_updateCredentials = By.id("button-update-fcm-credentials");
	private By txtbox_senderID = By.id("input-sender-id");
	private By chkbox_enableapp = By.xpath("//label[@data-role='checkbox']");
	private By btn_test = By.id("button-test-fcm-credentials");
	private By new_http_Channel = By.id("button-add-new-http");
	private By header_Update_Http_Configuration = By.xpath("//*[@class='form-header']/h2");
	private By header_Test_Http_Endpoint = By.xpath("//*[@id='form-header']/h2");
	private By http_Channel_name = By.id("input-channel-name");
	private By http_Channel_url = By.id("input-url");
	private By http_Channel_update = By.id("button-update-http-credentials");
	private By http_Channel_delete = By.id("button-delete-http-credentials");
	private By http_endpoint_msg = By.id("input-message");
	private By http_endpoint_target = By.id("input-target");
	private By btn_channel_confirm_delete = By.xpath("//button[contains(text(),'Confirm')]");
	private By select_existing_notification = By.id("select-existing-notification");
	private By select_existing_channel = By.xpath("//*[@id='select-existing-notification']/option[@value='GENESYS']");

	/**************************************************************************/
	
	/******* Credential Management Elements - Captcha ***********/
	
	private By btn_addNewCaptcha = By.xpath("//button[@id='button-add-new-captcha']");
	private By txt_captcha_name = By.id("captcha-name-input");
	private By txt_siteKey = By.id("site-key-input");
	private By txt_secretKey = By.id("secret-key-input");
	private By btn_save_captcha = By.id("button-update-captcha-credentials");
	private By btn_delete_captcha = By.id("button-delete-captcha");
	private By btn_generate_test_page = By.id("button-generate-test-page");
	private By btn_submit = By.id("submit-button-captcha");
	private By iframe_element = By.xpath("//*[@id='captcha-test-iframe']");
	
	/**************************************************************************/
	
	String val_project_name = firebase_captcha_property_file_reader("Project_Name");
	String val_client_email = firebase_captcha_property_file_reader("Client_Email");
	String val_privatekey = firebase_captcha_property_file_reader("Private_Key");
	String val_sender_id = firebase_captcha_property_file_reader("Sender_ID");
	
	String V2visible_sitekey = firebase_captcha_property_file_reader("V2_Visible_SiteKey");
	String V2visible_secretkey = firebase_captcha_property_file_reader("V2_Visible_SecretKey");
	String V2Invisible_sitekey = firebase_captcha_property_file_reader("V2_Invisible_SiteKey");
	String V2Invisible_secretkey = firebase_captcha_property_file_reader("V2_Invisible_SecretKey");
	String V3_sitekey = firebase_captcha_property_file_reader("V3_SiteKey");
	String V3_secretkey = firebase_captcha_property_file_reader("V3_SecretKey");
	
	/****************************************************************************/
	/**********************  Push Notifications Tab *****************************/
	
	public void Select_CredentialManagement_Option() {
		// TODO Auto-generated method stub
		sleepFor(2);
		System.out.println("\n Clicking the Credential Management option");
		getElement(dev_credmgmt_option, 15).click();
	}
	
	public void Click_PushNotification_tab() {
		sleepFor(2);
		System.out.println("\n Clicking on the Push Notification tab");
		getElement(push_notification_tab, 15).click();
	}
	
	public void Enter_invalid_Firebase_Credentials() {
		sleepFor(2);
		
		String inv_project_name = "aaaa";
		String inv_client_email = "aaa.com";
		String inv_privatekey = "pppaopappapppappappaopappa";
		
		/**************************************************************/
		
		getElement(txtbox_projname,15).sendKeys(inv_project_name);
		getElement(txtbox_clientemail, 15).sendKeys(inv_client_email);
		getElement(txtbox_privatekey, 15).sendKeys(inv_privatekey);		
	}
	
	public void Enter_valid_Firebase_Credentials() {
		sleepFor(2);
		
		getElement(txtbox_projname,15).sendKeys(val_project_name);
		getElement(txtbox_clientemail, 15).sendKeys(val_client_email);
		getElement(txtbox_privatekey, 15).sendKeys(val_privatekey);		
	}
	
	public void Click_UpdateCredentials() {
		sleepFor(2);
		getElement(btn_updateCredentials, 15).click();
		sleepFor(5);
	}

	public void Clear_all_notifications() {
		sleepFor(2);
		int a = countOfElements(By.xpath("//button[@class='btn-close']"));
		for (int i=1;i<=a;i++)
		{
			//getElement(By.xpath("(//button[@class='btn-close'])['"+i+"']"), 15).click();
			getElement(By.xpath("(//button[@class='btn-close'])[1]"), 15).click();
			sleepFor(2);
		}
	}
	
	public void verifyErrorMessageNotification() {
		// TODO Auto-generated method stub
		String error_notification = "Firebase Credentials failed to update. Try again.";
		System.out.println("\n The expected error message from UI is :"+error_notification);
		System.out.println("\n Obtaining the error message...");
		String obtained_message = getElement(By.xpath("//div[@class='inner']/div[@class='content']/p"), 15).getText();
		System.out.println("\n The obtained error message from UI is :"+obtained_message);
		Assert.assertEquals(error_notification, obtained_message);
	}

	public void verifySuccessMessageNotification() {
		// TODO Auto-generated method stub
		String success_notification = "Firebase Credentials successfully updated!";
		System.out.println("\n The expected message from UI is :"+success_notification);
		System.out.println("\n Obtaining the success message...");
		String obtained_message = getElement(By.xpath("//div[@class='inner']/div[@class='content']/p"), 15).getText();
		System.out.println("\n The obtained message from UI is :"+obtained_message);
		Assert.assertEquals(success_notification, obtained_message);
	}
	
	public void enter_valid_SenderID() {
		sleepFor(2);
		getElement(txtbox_senderID, 15).sendKeys(val_sender_id);
		System.out.println("\n Selecting the checkbox which enables the app to deliver push notifications to browser");
		getElement(chkbox_enableapp, 30).click();
		sleepFor(2);
	}
	
	public void click_test_button() {
		sleepFor(2);
		getElement(btn_test, 15).click();
	}
	
	public void Click_NewHTTPChannel() {
		sleepFor(2);
		System.out.println("\n Clicking on the New HTTP Channel");
		getElement(new_http_Channel, 15).click();
	}
	
	public void verifyHttpHeaders() {
		sleepFor(2);
		String expected_header_Update_Http_Configuration = "Update Http Configuration";
		String actual_header_Update_Http_Configuration = getElement(header_Update_Http_Configuration, 15).getText();		
		Assert.assertEquals(expected_header_Update_Http_Configuration,actual_header_Update_Http_Configuration);
		
		String expected_header_Test_Http_Endpoint = "Test Http Endpoint";
		String actual_header_Test_Http_Endpoint = getElement(header_Test_Http_Endpoint, 15).getText();
		Assert.assertEquals(expected_header_Test_Http_Endpoint,actual_header_Test_Http_Endpoint);
	}
		
	public void Enter_valid_http_channel_details() {
		sleepFor(2);
		
		String channel_name = "GENESYS";
		String channel_url = "https://www.genesys.com/";
		
		getElement(http_Channel_name,15).sendKeys(channel_name);
		getElement(http_Channel_url, 15).sendKeys(channel_url);	
	}
	
	public void Enter_invalid_http_channel_details() {
		sleepFor(2);
		
		String inv_channel_name = "aaaa";
		String inv_channel_url = "aaa.com";
		
		getElement(http_Channel_name,15).sendKeys(inv_channel_name);
		getElement(http_Channel_url, 15).sendKeys(inv_channel_url);	
	}
	
	public void Click_UpdateHTTPChannel() {
		sleepFor(2);
		getElement(http_Channel_update, 15).click();
		sleepFor(5);
	}
	
	public void verifyHTTPErrorMessage() {
		// TODO Auto-generated method stub
		String error_notification = "Must be a valid URL";
		System.out.println("\n The expected error message from UI is :"+error_notification);
		String obtained_message = getElement(By.xpath("//*[@id='input-url-error']"), 15).getText();
		System.out.println("\n The obtained error message from UI is :"+obtained_message);
		Assert.assertEquals(error_notification, obtained_message);
	}
	
	public void Select_ExistingHTTPChannel() {
		// TODO Auto-generated method stub
		sleepFor(2);
		System.out.println("\n Clicking drop down for existing channels list");
		getElement(select_existing_notification, 15).click();
		sleepFor(1);
		System.out.println("\n Selecting the existing channel : GENESYS");
		getElement(select_existing_channel, 15).click();
	}
	
	public void Click_DeleteHTTPChannel() {
		sleepFor(2);
		getElement(http_Channel_delete, 15).click();
		sleepFor(2);
		getElement(btn_channel_confirm_delete,20).click();
		sleepFor(2);
	}
	
	public void verifyHTTPDeleteMessageNotification() {
		// TODO Auto-generated method stub
		String success_notification = "Credentials have been successfully removed";
		System.out.println("\n The expected error message from UI is :"+success_notification);
		System.out.println("\n Obtaining the success message...");
		String obtained_message = getElement(By.xpath("//div[@class='inner']/div[@class='content']/p"), 15).getText();
		System.out.println("\n The obtained message from UI is :"+obtained_message);
		Assert.assertEquals(success_notification, obtained_message);
	}
	
	public void Enter_invalid_http_endpoint_details() {
		sleepFor(2);
		
		String text_message = "test";
		String target = "dummy";
		
		getElement(http_endpoint_msg,15).sendKeys(text_message);
		getElement(http_endpoint_target, 15).sendKeys(target);	
	}
	
	public void verifyTestHTTPEndpointErrorMessage() {
		// TODO Auto-generated method stub
		String error_notification = "There is an error with your http endpoint. Please verify the configuration and try again";
		System.out.println("\n The expected error message from UI is :"+error_notification);
		String obtained_message = getElement(By.xpath("//div[@class='inner']/div[@class='content']/p"), 15).getText();
		System.out.println("\n The obtained error message from UI is :"+obtained_message);
		Assert.assertEquals(error_notification, obtained_message);
	}
	
	/**********************  Captcha Tab **************************************/
	
	public void Click_Captcha_tab() {
		sleepFor(2);
		System.out.println("\n Clicking on the Captcha tab");
		getElement(captcha_tab, 15).click();
	}
	
	public void Click_AddNew_Captcha_KeySet() {
		sleepFor(2);
		System.out.println("\n Clicking on the add new captcha key set");
		getElement(btn_addNewCaptcha, 15).click();
	}
	
	public void Select_CaptchaType(String captcha_type) {
		sleepFor(2);
		String convertedUpperTypeCaptcha = captcha_type.toUpperCase();
		getElement(By.xpath("//select[@id='captcha-type-input']/option[@value='"+convertedUpperTypeCaptcha+"']"),15).click();
	}
	
	public void Enter_Captcha_Credentials_v2vis() {
		sleepFor(2);
			getElement(txt_captcha_name,15).sendKeys("temp_v2_vis");
			getElement(txt_siteKey,15).sendKeys(V2visible_sitekey);
			getElement(txt_secretKey,15).sendKeys(V2visible_secretkey);
		}
	
	public void Enter_Captcha_Credentials_v2invis() {
		sleepFor(2);
			getElement(txt_captcha_name,15).sendKeys("temp_v2_invis");
			getElement(txt_siteKey,15).sendKeys(V2Invisible_sitekey);
			getElement(txt_secretKey,15).sendKeys(V2Invisible_secretkey);
		}
	
	public void Enter_Captcha_Credentials_v3() {
		sleepFor(2);
			getElement(txt_captcha_name,15).sendKeys("temp_v3");
			getElement(txt_siteKey,15).sendKeys(V3_sitekey);
			getElement(txt_secretKey,15).sendKeys(V3_secretkey);
		}

	
	public void Save_Captcha() {
		sleepFor(2);
		getElement(btn_save_captcha,15).click();
		System.out.println("\n Updated the Captcha successfully");
	}
	
	public void Delete_Captcha() {
		sleepFor(2);
		getElement(btn_delete_captcha,15).click();
		System.out.println("\n Deleting the Captcha created");
	}
	
	public void Verify_Success_Message() {
		sleepFor(2);
		String expected_msg = "Provisioning has been updated.";
		String obtained_msg = getElement(By.id("error-text-captcha"), 15).getText();
		Assert.assertEquals(obtained_msg, expected_msg);
	}
	
	public void Click_GenerateTestPage() {
		sleepFor(2);
		getElement(btn_generate_test_page,15).click();
		System.out.println("\n Clicking on the Generate Test Page Button");
	}
	
	public void Verify_Submit_Button_Captcha() {
		sleepFor(2);
		DriverManager.getDriver().switchTo().frame(DriverManager.getDriver().findElement(By.id("captcha-test-iframe")));
		sleepFor(2);
		System.out.println("\n Switched successfully");
		//int a = countOfElements(btn_submit);
		//Assert.assertEquals(1, a);
	}
}
