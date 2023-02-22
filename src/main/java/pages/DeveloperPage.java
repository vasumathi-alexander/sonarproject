package ges.pages;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;

import ges.util.BaseTest;
import junit.framework.Assert;

public class DeveloperPage extends BaseTest{
	
	/**************************************************************************/
	
	private String apikey = testdata_property_file_reader("ApiKey");
	private String invalid_apikey = "abc123";
	private String no_apikey = "";
	
	/**************************************************************************/
	/******* Navigation Elements ***********/
	
	private By dev_tab = By.id("developer-nav");
	private By apiKeyValidation = By.xpath("//div[@value='apiKeyValidation']");
	private By dev_errors_option = By.xpath("//div[@value='errors']");
	private By dev_credmgmt_option = By.xpath("//div[@value='credentialManagement']");
	private By push_notification_tab = By.id("tab-item-push");
	private By dev_GWS_option = By.xpath("//*[@id='tab-item-gws-credentials']");
	
	/**************************************************************************/
	/******* APIKey Page Elements ***********/
	
	private By txtbox_apikey = By.id("api-key-input");
	private By btn_validate_apikey = By.id("validate-api-key");
	
	/****************************************************************************/
	/******* Errors Page Elements ***********/
	
	private By txtbox_searchErrors = By.xpath("//input[@class='mainInput']");
	private By btn_search = By.xpath("//button[contains(@class,'search-button')]");
	
	/****************************************************************************/
	/******* Credential Management Elements ***********/
	
	private By txtbox_projname = By.id("input-project-name");
	private By txtbox_clientemail = By.id("input-client-email");
	private By txtbox_privatekey = By.id("input-private-key");
	private By btn_updateCredentials = By.id("button-update-fcm-credentials");
	private By txtbox_senderID = By.id("input-sender-id");
	private By btn_test = By.id("button-test-fcm-credentials");
	
	/****************************************************************************/
	/******* GWS Page Elements ***********/
	
	private By GWS_Username = By.xpath("//input[@id='input-gws-username']");
	private By GWS_Password = By.xpath("//input[@id='input-gws-password']");
	private By GWS_Update = By.xpath("//button[@id='button-update-gws-credentials']");
	private By GWS_Delete = By.xpath("//button[@id='button-delete-gws-credentials']");
	/****************************************************************************/
	
	/************************************************  Start of Validate APIKey Functions  ***************************************************/
	
	public void Navigate_To_DeveloperTab() {
		sleepFor(4);
		// Clicking the Developer tab
		System.out.println("\n Clicking the Developer tab");
		getElement(dev_tab, 30).click();
	}
	
	public void Navigate_to_ErrorsOption() {
		sleepFor(4);
		System.out.println("\n Clicking the Errors option");
		getElement(dev_errors_option, 30).click();
	}
	
	public void Select_APIKeyValidation_Option() {
		sleepFor(4);
		// Clicking the Api Key Validation option
		System.out.println("\n Selecting the API Key Validation option");
		getElement(apiKeyValidation, 30).click();
	}
	
	public void Enter_APIKey_and_Validate() {
		sleepFor(4);
		System.out.println("\n Entering valid API Key");
		getElement(txtbox_apikey, 30).sendKeys(apikey);
		
		// Click on the Validate API Key
		getElement(btn_validate_apikey, 15).click();
		
		// Get text for success message
		String msg = "Valid API Key";
		sleepFor(2);
		assertEquals(getElement(By.xpath("//div[@class='api-response']"), 15).getText(), msg);
		System.out.println("\n The API Key : " +apikey+ " is valid");
	}
	
	public void Enter_invalidAPIKey_and_Validate() {
		// TODO Auto-generated method stub
		sleepFor(2);
		System.out.println("\n Entering invalid API Key");
		getElement(txtbox_apikey, 15).sendKeys(invalid_apikey);
		
		// Click on the Validate API Key
		getElement(btn_validate_apikey, 15).click();
		
		// Get text for success message
		String msg = "Invalid API Key";
		sleepFor(2);
		assertEquals(getElement(By.xpath("//div[@class='api-response']"), 15).getText(), msg);
		System.out.println("\n The API Key : " +invalid_apikey+ " is invalid");
	}
	
	public void Enter_noAPIKey_and_Validate() {
		// TODO Auto-generated method stub
		sleepFor(4);
		System.out.println("\n Entering no API Key");
		getElement(txtbox_apikey, 30).sendKeys(no_apikey);
		
		// Click on the Validate API Key
		getElement(btn_validate_apikey, 30).click();
		
		// Get text for success message
		String msg = "Please Enter An API Key";
		sleepFor(2);
		assertEquals(getElement(By.xpath("//div[@class='api-response']"), 30).getText(), msg);
		System.out.println("\n " +msg+ " message is displayed");
	}

	/************************************************  End of Validate APIKey Functions  ***************************************************/
	
	/************************************************  Start of Errors option Functions  ***************************************************/
	
	public void Select_Errors_Option() {
		sleepFor(4);
		// Clicking the Errros option
		System.out.println("\n Selecting the Errors option");
		getElement(dev_errors_option, 30).click();
	}
	
	public void Enter_Correlation_ID(String val) {
		sleepFor(2);
		getElement(txtbox_searchErrors, 30).sendKeys(val);
		System.out.println("\n The Correlation ID :" +val+ " is entered in the text box.");
		getElement(btn_search, 30).click();
	}
	
	public void verify_error_details() {
		int a = countOfElements(By.xpath("//div[contains(@class,'grid-rowgroup')]/div"));
		System.out.println("\n There are "+a+" matching error data");
		Assert.assertEquals(1, a);
	}
	
	public void display_error_details() {
		System.out.println("\n *****************************************************************");
		System.out.println("\n The Correlation ID is :" +getElement(By.xpath("(//div[contains(@class,'grid-rowgroup')]//div[contains(@class,'cell-content')])[1]"),15).getText());
		System.out.println("\n The URL is :" +getElement(By.xpath("(//div[contains(@class,'grid-rowgroup')]//div[contains(@class,'cell-content')])[2]"),15).getText());
		System.out.println("\n The Method is :" +getElement(By.xpath("(//div[contains(@class,'grid-rowgroup')]//div[contains(@class,'cell-content')])[3]"),15).getText());
		System.out.println("\n The Time Stamp is :" +getElement(By.xpath("(//div[contains(@class,'grid-rowgroup')]//div[contains(@class,'cell-content')])[4]"),15).getText());
		System.out.println("\n The Status Code is :" +getElement(By.xpath("(//div[contains(@class,'grid-rowgroup')]//div[contains(@class,'cell-content')])[5]"),15).getText());
		System.out.println("\n *****************************************************************");
	}
	
	public void verify_column_headers_error_tab() {
		
		System.out.println("\n The columns would be displayed as : Correlation ID | URL | Method | Time Stamp | Status Code");
		List <String> default_col_headers = new ArrayList<String>();
		
		default_col_headers.add("Correlation ID");
		default_col_headers.add("URL");
		default_col_headers.add("Method");
		default_col_headers.add("Time Stamp");
		default_col_headers.add("Status Code");
		
		sleepFor(2);
		
		List <String> obtained_col_headers = new ArrayList<String>();
		int d = countOfElements(By.xpath("//div[contains(@class,'grid-hrow')]/div"));
		System.out.println("\n There are totally "+d+" columns present in the UI.");
		
		sleepFor(3);
		System.out.println("\n Fetching the column headers from UI");
		for(int i = 1; i<=d;i++)
		{
			obtained_col_headers.add(getElement(By.xpath("(//div[contains(@class,'grid-hrow')]//div[@class='cell-content'])["+i+"]"),15).getText());
		}
		
		System.out.println("\n The column headers fetched from the UI is : "+obtained_col_headers);
		
		Assert.assertEquals(obtained_col_headers,default_col_headers);
		
		System.out.println("\n The column headers are present in the page and verified successfully");
			
	}

	public void display_sidepane_details() {
		// TODO Auto-generated method stub
		getElement(By.xpath("(//div[contains(@class,'grid-rowgroup')]//div[contains(@class,'cell-content')])[1]"),15).click();
		String ui_header_name = "Details";
		sleepFor(3);
		String obtained_header_name = getElement(By.xpath("//*[@id='secondView']/div/div/div/h1"),15).getText();
		System.out.println("\n The headers fetched from the UI is : "+obtained_header_name+ "; Expected is"+ui_header_name);
		//Assert.assertEquals(ui_header_name, obtained_header_name);
		System.out.println("\n *****************************************************************");
		System.out.println("\n The side pane details for Status code is : "+getElement(By.xpath("//label[text()='Status Code: ']/ancestor::div[1]/p"),15).getText());
		System.out.println("\n The side pane details for Message is : "+getElement(By.xpath("//label[text()='Message: ']/ancestor::div[1]/p"),15).getText());
		System.out.println("\n The side pane details for Cause is : "+getElement(By.xpath("//label[text()='Cause: ']/ancestor::div[1]/p"),15).getText());
		System.out.println("\n The side pane details for HTTP Status code is : "+getElement(By.xpath("//label[text()='Http Status Code: ']/ancestor::div[1]/p"),15).getText());
		System.out.println("\n *****************************************************************");
	}

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
		String inv_client_email = "aaa@aaa.com";
		String inv_privatekey = "pppaopappapppappappaopappa";
		
		/**************************************************************/
		
		getElement(txtbox_projname,15).sendKeys(inv_project_name);
		getElement(txtbox_clientemail, 15).sendKeys(inv_client_email);
		getElement(txtbox_privatekey, 15).sendKeys(inv_privatekey);		
	}
	
	public void Enter_valid_Firebase_Credentials() {
		sleepFor(2);
		
		String val_project_name = "my-project-1536737203664";
		String val_client_email = "firebase-adminsdk-z0fzt@my-project-1536737203664.iam.gserviceaccount.com";
		String val_privatekey = "-----BEGIN PRIVATE KEY-----\nMIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC1TYguIUIMYnyu\n1dfqTdDAlYF8JmZysTNEMjMlMKK8Kvn/+oOk4P5XBtoqRnHjo1G139r/wJPuAVGX\ntK6f/fVB7Ja+DVoy9zj70KtPxbqoezaxiNrVFGligHmUi5Rrs1J3fpBHwrdlBGRe\nOOAw5SzU3Fz55gDSc/bDeoBSyQiaEbzRf3XuVmARXIVo0QydVSru0CU1IvJU4NE8\nkYFUB8Sa64Yu79LLTvcaNgFQkiJYDc99M6OCox137nyY2juN89+HzED/07vVNfRk\nx0mYMtAJUOomORHG7E7WDqwxszKe+TaqE/Qfu9HRl9D2HwvrBwGyR0Qb59DKC0WM\nYeUqzbs9AgMBAAECggEAB15MzA8m94jjJ1niig+6jbElz41h+YRRIJLnuDm3S211\ntp0QT5mjTJcnM7OkOkuvbPRZmq5RdMlR7xf1RuRT3OeIItY5FbwtcTKEW9wd324O\nhX2Z4zIOrGWrS0m28P+bOOcIZxo3SOA6Ugf+uI3/2fDQyIk7/FGhNEWcxICOHHDN\nl4cj18rFG32tVuni6DhTeffDkM9/PZ9mprqhqvXfwziy7B1Z8Va3QaYBKdNoReyT\nQsdcjhZzXGptP1EDFsgwteI8ybwD+X7lPMHzJqsElJNSeZnONmvAeOfOGb+NlLL4\ndkZ2Y7sbSNDCfRSYFd7SXvf9Ls8mWRifyB19IFZOswKBgQDbXASSnrCDY0Xfcvls\neAsHlOTD/ab7J4KvKhxdWyg2qOkGrVYffRcC8D3rSaGtHQJD0G78gmXUDo/aHAm9\nTC4WimOAPhNypPLha5Tv8pRGXgzB6dsUIC1SJ+v+1fAOTP8Ryd77G9BNRN3hCmH3\nkZGNieF0JvxQvrkQkzyx6qBASwKBgQDTli9CLqt+jykSCC2qOjhWjUT0dNja3C+K\nGmhqUaFxfIOQxtMG6BUB0z/B2PEfCE+4hrH5YCkn4W/GgcmVAflZ3/SI0jScqZ5c\nMptdGIHrgRTdOeOnzLbseEjuFhCUB1btQAo78LKRlKbIADP07G0d3hEfB62it8Cn\nX8TrLowNlwKBgDHVyPAqfz2KSzCFpWbgKtlRGVvxf4MlqcnzzjfjYtJwM2ih7qP+\nvkCbpeWTd9ilc4NGkkBpu4qeuE27+vZ3nSzfydUzxSVRliBiWZbdzJPkAA/5+hyr\nZsB4c2QU5JHLKlO3QiJYYk17rNDYlAMznhbC0WabCM2rVs4ONUbSYA/bAoGBAMRq\neLhxZpX4A+JPYUTTAxWl0ssz5VNWb3M+jIuV987IDcJKMAALVeT53AfxgfSS3J6p\nu3GKSOpSplaaiS0gRf+enPyeVmTQOMfR2tQTOhgLHfk4EhPlQDJIfzs1lHI2VgWs\nG1QFX18xpZmoRpMpHypIyZAKqq+Q8PDvQHgqH61FAoGASHR8xK1Fnk2N1P2Dzzng\nBGhM/NcS4+AIXRPAxzDw0MgB344C4HRotw3tjdPCCklD58GMXvPjkBteYWpBbraE\nao817r9lcB0nW4N6Z1UtR8yhR/nZQPtdqP/Gt+ckiS5ruGvzrIEDTX5ZpTT5Rp0l\n+tsG0pjsaaKpbDSgNVq5mpE=\n-----END PRIVATE KEY-----";
		
		/**************************************************************/
		
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
		System.out.println("\n The expected error message from UI is :"+success_notification);
		System.out.println("\n Obtaining the success message...");
		String obtained_message = getElement(By.xpath("//div[@class='inner']/div[@class='content']/p"), 15).getText();
		System.out.println("\n The obtained error message from UI is :"+obtained_message);
		Assert.assertEquals(success_notification, obtained_message);
	}
	
	public void enter_valid_SenderID() {
		sleepFor(2);
		String val_sender_id = "161185933723";
		getElement(txtbox_senderID, 15).sendKeys(val_sender_id);
	}
	
	public void click_test_button() {
		sleepFor(2);
		getElement(btn_test, 15).click();
	}
	
	/************************************************  End of Errors option Functions   ***************************************************/
	
	/************************************************  Start of GWS Credentials Functions  ***************************************************/
	
	public void Navigate_to_GWS_Credentials() {
		sleepFor(2);
		System.out.println("\n Clicking the GWS Credentials");
		getElement(dev_GWS_option, 15).click();
	}
	
	public void Enter_username(String user) {
		sleepFor(2);
		System.out.println("\n Entering the username");
		getElement(GWS_Username, 15).sendKeys(user);
	}
	
	public void Enter_password(String user) {
		sleepFor(2);
		System.out.println("\n Entering the password");
		getElement(GWS_Password, 15).sendKeys(user);
	}
	
	public void Click_Update_button() {
		sleepFor(2);
		System.out.println("\n Clicking the update button");
		getElement(GWS_Update, 15).click();
	}
	
	public void Click_Delete_button() {
		sleepFor(2);
		System.out.println("\n Clicking the update button");
		getElement(GWS_Delete, 15).click();
	}
	
	public void Verify_GWS_Credentials() {
		sleepFor(2);
		String To_be_displayed = "GWS Credentials successfully updated!";
		String Actually_displayed = getElement(By.xpath("//*[@id='main-and-toaster']/div[2]/div/ul/li/aside/div/div[2]/p"), 15).getText();
		Assert.assertEquals(To_be_displayed,Actually_displayed);
	}
	
	public void Verify_invalid_GWS_Credentials() {
		sleepFor(2);
		String To_be_displayed = "Invalid GWS credentials, failed to update credentials.";
		String Actually_displayed = getElement(By.xpath("//*[@id='main-and-toaster']/div[2]/div/ul/li/aside/div/div[2]/p"), 15).getText();
		Assert.assertEquals(To_be_displayed,Actually_displayed);
	}
	
	public void Verify_delete_GWS_Credentials() {
		sleepFor(2);
		String To_be_displayed = "GWS credentials have been successfully removed";
		String Actually_displayed = getElement(By.xpath("//*[@id='main-and-toaster']/div[2]/div/ul/li[2]/aside/div/div[2]/p"), 15).getText();
		Assert.assertEquals(To_be_displayed,Actually_displayed);
	}
	
	public void Verify_error_GWS_Credentials(int i) {
		sleepFor(2);
		if(i==1){
			String To_be_displayed = "Please input a username";
			String Actually_displayed = getElement(By.xpath("//*[@id='credentials-gws']/div[2]/div[1]/div/div[2]/label"), 15).getText();
			Assert.assertEquals(To_be_displayed,Actually_displayed);
		}else{
			String To_be_displayed = "Please input a password";
			String Actually_displayed = getElement(By.xpath("//*[@id='credentials-gws']/div[2]/div[2]/div/div[2]/label"), 15).getText();
			Assert.assertEquals(To_be_displayed,Actually_displayed);
			
		}	
	}
	
}
