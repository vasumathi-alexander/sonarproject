package ges.pages;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import ges.util.BaseTest;
import junit.framework.Assert;

public class ToolsPage extends BaseTest{
	
	/**************************************************************************/
	/************************* Navigation Elements**************************************/
	
	private By tools_tab = By.id("tools-nav");
	private By refetch_tenantConfig = By.xpath("//div[@value='refetchTenantConfig']");
	private By callback_search = By.xpath("//div[@value='callbackSearch']");
	private By callback_id_filter = By.xpath("//*[@id='main-and-toaster']/div[1]/div/main/div/div/div[1]/div[2]/div/div[2]/div/div/div[1]");
	private By callback_number_filter = By.xpath("//*[@id='main-and-toaster']/div[1]/div/main/div/div/div[1]/div[2]/div/div[2]/div/div/div[2]");
	private By txtbox_Callback_detail = By.xpath("//input[@type='text'][2]");
	private By txtmsg_refetch_tenantConfig = By.xpath("//div[@value='refetchTenantConfig']/p");
	private By success_icon_tenantConfig = By.xpath("//div[@class='navBarIcon']");
	private By btn_callback_search = By.xpath("//button[contains(text(),'Search')]");
	private By capacity = By.xpath("//div[@value='capacity']");
	private By permitted_blocked_numbers = By.xpath("//div[@value='patterns']");
	private By queues = By.xpath("//div[@value='queues']");
	private By call_in_grp = By.xpath("//div[@value='callInConfig']");
	private By call_in_grp_name = By.xpath("//*[@id='main-and-toaster']/div[1]/div/main/div/div/div[2]/div[2]/div/div/input");
	private By call_in_dnis = By.id("new-dnis-input");
	private By input_dnis = By.xpath("//div[@class='cell-content']/input");
	private By error_text_accesscode = By.xpath("//*[@title='Out of valid range']");
	private By error_text_expiry = By.xpath("//*[@id='firstView']/div/div/div/div[2]/span/div[2]/div/div[2]/label");
	private By edit_accesscode = By.xpath("//*[@id='dnis-advanced-input-access-code']");
	private By edit_expiry = By.xpath("//*[@id='dnis-advanced-input-expiry']");
	private By chk_box_access_code = By.xpath("//*[@class='hiddenInput']");
	private By chk_box_access_code1 = By.xpath("//*[@class='surrogate'][1]");
	
	/**************************************************************************/
	private By btn_addNewCapacity = By.xpath("//span[text()='Add New Capacity']");
	private By btn_deleteCapacity = By.xpath("//span[text()='Delete Capacity']");
	private By btn_cnf_deleteCapacity = By.xpath("//*[@title='Confirm']");
	private By txtbox_capacityName = By.xpath("//span[text()='Capacity Name']/input");
	private By btn_createRule = By.xpath("//button[@title='Create Rule']");
	private By txt_capacityValue = By.xpath("//input[@time='00:00' and @day='mon']");
	private By btn_save = By.xpath("//button[text()='Save']");
	/**************************************************************************/
	private By btn_add_newrule = By.id("add-new-rule");
	private By txtbox_new_rule = By.xpath("//input[@placeholder='Please enter a name for the rule']");
	private By btn_save_rule = By.id("save-rule");
	private By btn_save_changes = By.id("save-changes-patterns");
	private By btn_delete_rule = By.id("deleteRuleButton");
	private By btn_create_grp = By.id("add-group-button");
	private By btn_save_call_in_grp = By.id("save-group-modal-button");
	private By btn_add_dnis = By.xpath("//*[@id='new-dnis-description']/button");
	private By btn_call_in_save_changes = By.xpath("//*[@id='callin-config-save-button']");
	private By btn_call_in_delete = By.xpath("//*[@id='delete-group-button']");
	private By btn_call_in_confirm_delete = By.xpath("//*[@title='Confirm']");
	private By btn_edit_dnis = By.xpath("//button[@title='Edit DNIS']");
	private By btn_save_dnis = By.xpath("//div[@class='cell-content']/span/button[1]");
	private By btn_cb_search_dropdown = By.xpath("//*[@id='main-and-toaster']/div[1]/div/main/div/div/div[1]/div[2]/div/div/div[2]/button");
	private By btn_filter = By.xpath("//*[@id='service-filter-button']");
	private By btn_region_affinity_delete = By.xpath("//*[@id='main-and-toaster']//button[1]");
	private By btn_region_affinity_confirm_delete = By.xpath("//*[@id='main-and-toaster']/div[1]/div/main/div/div/div[1]/div/section/div/div[2]/button");
		

	
	/**************************************************************************/
	private By search_box = By.xpath("//input[@class='mainInput']");
	
		
	// ----------- Region Affinity Elements-------------------//
	private By btn_create_RA_rule = By.xpath("//*[@id='main-and-toaster']/div[1]/div/main/div/div/div/div[2]/button[2]");
	private By Reg_Affinity = By.xpath("//div[@value='regionAffinity']");
	private By RA_rule_name = By.xpath("//*[@id='main-and-toaster']/div[1]/div/main/div/div/div[1]/div/div/span/input");
	private By btn_save_RA_rule = By.id("save-rule");
	
	private By btn_delete_RA_rule = By.xpath("//*[@id='main-and-toaster']/div[1]/div/main/div/div/div[1]/div[2]/button[1]");
	private By btn_cnf_delete_RA_rule=By.xpath("//*[@title='Confirm']");	
	private By From=By.xpath("//*[@id='main-and-toaster']/div[1]/div/main/div/div/div[2]/div[2]/div[3]/div/div[2]/div");
	private By To=By.xpath("//*[@id='main-and-toaster']/div[1]/div/main/div/div/div[2]/div[2]/div[1]/div/div[2]");
	private By btn_RA_rule_save = By.xpath("//*[@id='main-and-toaster']/div[1]/div/main/div/div/div[2]/div[1]/div/button[2]");	
	/**************************************************************************/
	
	private static HashMap<String,String> countryList = new HashMap<String,String>();
	
	public ToolsPage() {
		countryList.put("India", "IN");
		countryList.put("US", "US");
		countryList.put("UK", "GB");
	}
	
	/************************************************  Start of Tools - Navigate Functions  ***************************************************/
	
	public void Navigate_To_ToolsTab() {
		
		// Clicking the Tools tab
		System.out.println("\n Clicking the Tools tab");
		sleepFor(3);
		getElement(tools_tab, 15).click();
	}

	
	public void Click_Refetch_Tenant_Config() {
		sleepFor(2);
		System.out.println("\n Selecting - Tenant Configuration");
		getElement(refetch_tenantConfig, 15).click();
	}
	
	public void Click_Callback_search() {
		sleepFor(2);
		System.out.println("\n Selecting - Callback Search");
		getElement(callback_search, 15).click();
	}
	
	public void Click_Capacity() {
		sleepFor(2);
		System.out.println("\n Selecting - Capacity");
		getElement(capacity, 15).click();
	}
	
	public void Click_Permitted_and_Blocked_Numbers() {
		sleepFor(2);
		System.out.println("\n Selecting - Permitted & Blocked Numbers");
		getElement(permitted_blocked_numbers, 15).click();
	}
	
	public void Click_Queues() {
		sleepFor(2);
		System.out.println("\n Selecting - Queues");
		getElement(queues, 15).click();
	}
	public void Click_CallIn_Group() {
		sleepFor(2);
		System.out.println("\n Selecting - Click To CallIn Config");
		getElement(call_in_grp, 15).click();
	}
	
	public void Click_Region_Affinity() {
		sleepFor(2);
		System.out.println("\n Selecting - Region Affinity");
		getElement(Reg_Affinity, 15).click();
	}
	

	/************************************************  End of Tools - Navigate Functions  ***************************************************/
	
	
	public void Check_success_Refetch_Tenant_Config() {
		sleepFor(2);
		int count_success = countOfElements(success_icon_tenantConfig);
		//System.out.println("\n There are "+count_success+ " matching element(s)");
		System.out.println("\n The Tenant Configuration last fetched message is : " +getElement(txtmsg_refetch_tenantConfig, 15).getText());
			if (count_success == 0)
			{
				System.out.println("\n The tenant config is not refetched and the success icon is not present");
				
			}
			else if (count_success == 1)
			{
				System.out.println("\n The tenant config is refetched successfully and success icon is present");
				
			}	
	}
	
	public void Select_Filter_CallbackID() {
		System.out.println("\n Selecting the Callback ID from the drop down filter");
		getElement(btn_cb_search_dropdown, 15).click();
		//sleepFor(2);
		getElement(callback_id_filter, 15).click();
	}
	
	public void Select_Filter_CallbackNumber() {
		System.out.println("\n Selecting the Callback Number from the drop down filter");
		getElement(btn_cb_search_dropdown, 15).click();
		getElement(callback_number_filter, 15).click();
	}
	
	public void Enter_CallbackID(String callback_id) {
		System.out.println("\n Entering the Callback ID");
		getElement(txtbox_Callback_detail, 15).sendKeys(callback_id);
	}
	
	public void Enter_CallbackNumber(String number) {
		System.out.println("\n Entering the Callback Number");
		getElement(txtbox_Callback_detail, 15).sendKeys(number);
	}
	
	public void Click_SearchButton() {
		System.out.println("\n Clicking the Search button");
		getElement(btn_callback_search, 15).click();
		}
	
	public void Verify_CallbackDetail_ID(String callback_id) {
		System.out.println("\n Verifying the Callback ID used to create the callback");
		sleepFor(3);
		int m = countOfElements(By.xpath("//div[contains(@class,'grid-row')]//div[text()='"+callback_id+"']"));
		System.out.println("\n There is only "+m+" matching row(s) when searched using callback ID");
		System.out.println("\n The Created Callback is displayed successfully when filtered with Callback ID");
		Assert.assertEquals(1, m);
	}
	
	public void Verify_CallbackDetail_Number(String number) {
		System.out.println("\n Verifying the Callback ID used to create the callback");
		sleepFor(3);
		int m = countOfElements(By.xpath("//div[contains(@class,'grid-row')]//div[text()='+"+number+"']"));
		System.out.println("\n There are "+m+" matching row(s) when searched using Customer Number");
		if(m >= 1)
		{
			System.out.println("\n The Created Callback is displayed successfully when filtered with Customer Number");	
		}
		else
		{
			System.out.println("\n There are no callbacks displayed for the entered Callback Number");
		}
	}
	
	public void Verify_CallbackDetails_Headers() {
		sleepFor(3);
		System.out.println("\n By Default - the columns should be displayed as : \"From\" Number | State | Route Point | Last Updated Time | Queue | \"To\" Number | Callback ID | Scheduled Time | Diagnostic Data");
		List <String> default_col_headers = new ArrayList<String>();
		
		default_col_headers.add("\"From\" Number");
		default_col_headers.add("State");
		default_col_headers.add("Route Point");
		default_col_headers.add("Last Updated Time");
		default_col_headers.add("Queue");
		default_col_headers.add("\"To\" Number");
		default_col_headers.add("Callback ID");
		default_col_headers.add("Scheduled Time");
		default_col_headers.add("Diagnostic Data");
		
		List <String> obtained_col_headers = new ArrayList<String>();
		int m = countOfElements(By.xpath("//div[contains(@class,'grid-hrow')]/div[contains(@class,'callback-search-cell')]/div"));
		System.out.println("\n There are totally "+m+" columns present in the UI.");
		sleepFor(3);
		System.out.println("\n Fetching the column headers from UI");
		for(int i = 1; i<=m; i++)
		{
			obtained_col_headers.add(getElement(By.xpath("(//div[contains(@class,'grid-hrow')]/div[contains(@class,'callback-search-cell')]/div)["+i+"]"),15).getText());
		}
		
		System.out.println("\n The column headers fetched from the UI is : "+obtained_col_headers);
		
		Assert.assertEquals(obtained_col_headers,default_col_headers);
		
		System.out.println("\n The column headers are present in the page and verified successfully");
	
	}
	
	public void Click_Add_New_Capacity() {
		sleepFor(3);
		System.out.println("\n Selecting - Add New Capacity");
		getElement(btn_addNewCapacity, 15).click();	
	}
	
	public void Click_Delete_Capacity() {
		sleepFor(3);
		System.out.println("\n Selecting - Delete Capacity");
		getElement(btn_deleteCapacity, 15).click();
		getElement(btn_cnf_deleteCapacity, 15).click();
	}
	
	public void Enter_CapacityName() {
		sleepFor(3);
		System.out.println("\n Entering the Capacity Rule Name");
		getElement(txtbox_capacityName, 15).sendKeys("test");
	}
	
	public void Click_CreateRule_Button() {
		sleepFor(3);
		System.out.println("\n Clicking on Create Rule Button");
		getElement(btn_createRule, 15).click();
	}
	
	public void Enter_CapacityDetails() {
		sleepFor(3);
		System.out.println("\n Entering the capacity value - For testing purpose we would be entering capacity = 5 for monday, timeslot = 00:00");
		getElement(txt_capacityValue, 15).sendKeys("5");
	}
	
	public void Click_SaveButton() {
		sleepFor(3);
		System.out.println("\n Selecting Save button");
		getElement(btn_save, 15).click();
	}
	
	public void Verify_No_Callbacks_Found_Error_Message_id(String val) {
		sleepFor(3);
		System.out.println("\n Verifying the error message - No Callbacks Found");
		String obtained_text = getElement(By.xpath("//*[@id='main-and-toaster']/div[1]/div/main/div/div/div[2]/div/div[2]/div/div"),30).getText();
		String expected_text = "No Callbacks Found for Id: "+val;
		//System.out.println("\n Verifying the error message - No Callbacks Found "+obtained_text);
		//System.out.println("\n Verifying the error message - No Callbacks Found "+expected_text);
		Assert.assertEquals(expected_text, obtained_text);
	}
	
	public void Verify_No_Callbacks_Found_Error_Message_number(String val) {
		sleepFor(3);
		System.out.println("\n Verifying the error message - No Callbacks Found");
		String obtained_text = getElement(By.xpath("//*[@id='main-and-toaster']/div[1]/div/main/div/div/div[2]/div/div[2]/div/div"),30).getText();
		String expected_text = "No Callbacks Found for Number: "+val;
		//System.out.println("\n Verifying the error message - No Callbacks Found "+obtained_text);
		//System.out.println("\n Verifying the error message - No Callbacks Found "+expected_text);
		Assert.assertEquals(expected_text, obtained_text);
	}
	
	public void Create_New_Rule() {
		sleepFor(3);
		getElement(btn_add_newrule, 30).click();
    	String  num= RandomStringUtils.randomNumeric(3);
    	String rulesetname = "UI_Auto_rule"+num;
    	System.out.println("\n The rule set namer that is generated : "+rulesetname);
		getElement(txtbox_new_rule, 30).sendKeys(rulesetname);
		System.out.println("\n Entered the rule name and clicking on save button");
		getElement(btn_save_rule, 30).click();
		System.out.println("\n For test purpose we are allowing callbacks to country - India");
		getElement(By.xpath("//input[@id='IN']/ancestor::label"), 30).click();
		Click_Save_Changes_Button();
	}
	
	public void Create_New_Rule_UK() {
		sleepFor(3);
		getElement(btn_add_newrule, 30).click();
    	String  num= RandomStringUtils.randomNumeric(3);
    	String rulesetname = "UI_Auto_rule"+num;
    	System.out.println("\n The rule set namer that is generated : "+rulesetname);
		getElement(txtbox_new_rule, 30).sendKeys(rulesetname);
		System.out.println("\n Entered the rule name and clicking on save button");
		getElement(btn_save_rule, 30).click();
		System.out.println("\n For test purpose we are allowing callbacks to country - UK");
		getElement(By.xpath("//input[@id='GB']/ancestor::label"), 30).click();
		Click_Save_Changes_Button();
	}
	
	public void Create_New_Rule(String country) {
		sleepFor(3);
		getElement(btn_add_newrule, 30).click();
    	String  num= RandomStringUtils.randomNumeric(4);
    	String rulesetname = "UI_Auto_rule"+num;
    	System.out.println("\n The rule set namer that is generated : "+rulesetname);
		getElement(txtbox_new_rule, 30).sendKeys(rulesetname);
		System.out.println("\n Entered the rule name and clicking on save button");
		getElement(btn_save_rule, 30).click();
		System.out.println("\n For test purpose we are allowing callbacks to country - "+country+"\"");
		String countryCode = countryList.get(country);
		getElement(By.xpath("//input[@id='"+countryCode+"']/ancestor::label"), 30).click();
		Click_Save_Changes_Button();
	}
	
	public void Create_Rule_SpecialCharacters() {
		sleepFor(3);
		getElement(btn_add_newrule, 30).click();
		getElement(txtbox_new_rule, 30).sendKeys("@@@@");
		System.out.println("\n Entered the rule name with special characters and clicking on save button");
		getElement(btn_save_rule, 30).click();
		String expected_response = "Rule name must only contain letters and numbers.";
		String actual_response = getElement(By.xpath("//div[@class='error-text']/h4"), 30).getText();
		System.out.println("\n The error message displayed is :" +actual_response);
		Assert.assertEquals(expected_response, actual_response);
	}
	
	public void Close_RuleCreation_Modal() {
		sleepFor(3);
		getElement(By.xpath("//button[contains(@class,'close')]"), 30).click();
		
	}
	
	public void Click_Save_Changes_Button() {
		sleepFor(3);
		getElement(btn_save_changes, 30).click();
		
	}
	
	public void Enter_Valid_Test_PhoneNumber(String prefix) {
		sleepFor(3);
    	String num= RandomStringUtils.randomNumeric(8);
    	String test_ph_num = prefix+num;
    	System.out.println("\n The generated valid test phone number is :" +test_ph_num);
    	getElement(By.xpath("//input[@class='mainInput']"), 30).click();
    	getElement(By.xpath("//input[@class='mainInput']"), 30).sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
		getElement(By.xpath("//input[@class='mainInput']"), 30).sendKeys(test_ph_num);
	}
	
	public void Enter_Invalid_Test_PhoneNumber() {
		sleepFor(3);
    	String num= RandomStringUtils.randomNumeric(8);
    	String test_invalid_ph_num = "+921"+num;
    	System.out.println("\n The generated invalid test phone number is :" +test_invalid_ph_num);
		getElement(By.xpath("//input[@class='mainInput']"), 30).sendKeys(test_invalid_ph_num);
	}
	
	public void Enter_Invalid_Test_PhoneNumber_MoreDigit(String prefix) {
		sleepFor(3);
    	String num= RandomStringUtils.randomNumeric(10);
    	String test_invalid_ph_num = prefix+num;
    	System.out.println("\n The generated invalid test phone number is :" +test_invalid_ph_num);
		getElement(By.xpath("//input[@class='mainInput']"), 30).sendKeys(test_invalid_ph_num);
	}
	
	public void Enter_Test_PhoneNumber(String phnNum) {
		sleepFor(3);
    	System.out.println("\n Test phone number is :" +phnNum);
		getElement(By.xpath("//input[@class='mainInput']"), 30).sendKeys(phnNum);
		sleepFor(2);
	}
	
	public void Delete_Rule() {
		sleepFor(3);
		getElement(btn_delete_rule, 30).click();
		System.out.println("\n Deleting the Rule created");
		getElement(By.xpath("//button[@title='Confirm']"), 30).click();
	}
	
	public void Display_Valid_Result_TestPhoneNumber() {
		String msg = getElement(By.xpath("//div[@class='patterns-user-message success-message-patterns']/h4"), 30).getText();
		String expected_response = "Number passes country validation, no exceptions to test! Phone number is from country: India (IN).";
		System.out.println("\n The message displayed for the test phone number is : \n" +msg);
		Assert.assertEquals(expected_response, msg);
	}
	
	public void Display_Valid_Result_TestUKPhoneNumber() {
		String msg = getElement(By.xpath("//div[@class='patterns-user-message success-message-patterns']/h4"), 30).getText();
		String expected_response = "Number passes country validation, no exceptions to test! Phone number is from country: United Kingdom (GB).";
		System.out.println("\n The message displayed for the test phone number is : \n" +msg);
		Assert.assertEquals(expected_response, msg);
	}
	
	public void Display_Invalid_Result_TestPhoneNumber(String errorMessage) {
		String msg = getElement(By.xpath("//div[@class='patterns-user-message error-message-patterns']/h4"), 30).getText();
		//String expected_response = "Test Failed! The country of the phone number is not in the allowable list: Pakistan (PK).";
		System.out.println("\n The message displayed for the invalid test phone number is : \n" +msg);
		Assert.assertEquals(errorMessage, msg);
	}
	
	public void Display_Warning_Result_TestPhoneNumber(String warnMessage) {
		String msg = getElement(By.xpath("//div[@class='patterns-user-message warning-message-patterns']/h4"), 30).getText();
		System.out.println("\n The message displayed for the test phone number is : \n" +msg);
		Assert.assertEquals(warnMessage, msg);
	}
	public void display_currently_selected_countries() {
		sleepFor(3);
		int a = countOfElements(By.xpath("//ul[@class='selected-country-list']/li"));
		System.out.println("\n There are currently : " +a+ " country/countries selected");
		System.out.println("\n Currently selected countries are :");
		for (int i = 1; i<=a; i++)
		{
			System.out.println("\n "+getElement(By.xpath("(//ul[@class='selected-country-list']/li)["+i+"]"), 30).getText());
		}
		
	}
	
	public void Selecting_new_country() {
		System.out.println("\n For test purpose - we shall select Algeria");
		getElement(By.xpath("//input[@id='DZ']/ancestor::label"), 30).click();
	}
	public void Verify_VQ_Search(String val) {
		
		sleepFor(2);
		
		getElement(search_box, 15).sendKeys(val);
		getElement(btn_filter,15).click();
		System.out.println("\n Searching for the VQ with VQ name :" +val);
		int a = countOfElements(By.xpath("//div[(@id='service-row')]"));
		System.out.println("\n There should be only 1 record as service ID should be unique. \n There are : "+a+ " matching record(s).");
		assertEquals(a, 1);		
	}

public void Verify_CallbackQueues_Headers() {
	sleepFor(3);
	System.out.println("\n By Default - the columns should be displayed as : Queue | Expected Wait Time (s) | Calls | Average Quitting Time (s) | Queued Callbacks | Scheduled Callbacks | Open/Close Time | Number Validation Rule | Capacity Rule | URS Node | EWT Payload");
	List <String> default_col_headers = new ArrayList<String>();
	
	default_col_headers.add("Queue");
	default_col_headers.add("Expected Wait Time (s)");
	default_col_headers.add("Calls");
	default_col_headers.add("Average Quitting Time (s)");
	default_col_headers.add("Queued Callbacks");
	default_col_headers.add("Scheduled Callbacks");
	default_col_headers.add("Open/Close Time");
	default_col_headers.add("Region Affinity Rule");
	default_col_headers.add("Number Validation Rule");
	default_col_headers.add("Capacity Rule");
	default_col_headers.add("URS Node");
	default_col_headers.add("EWT Payload");
	
	List <String> obtained_col_headers = new ArrayList<String>();
	int m = countOfElements(By.xpath("//div[contains(@class,'grid-hrow')]/div"));
	System.out.println("\n There are totally "+m+" columns present in the UI.");
	sleepFor(3);
	System.out.println("\n Fetching the column headers from UI");
	for(int i = 1; i<=m-1; i++)
	{
		obtained_col_headers.add(getElement(By.xpath("(//div[contains(@class,'grid-hrow')]/div)["+i+"]"),15).getText());
	}
	
	System.out.println("\n The column headers fetched from the UI is : "+obtained_col_headers);
	
	Assert.assertEquals(obtained_col_headers,default_col_headers);
	
	System.out.println("\n The column headers are present in the page and verified successfully");
	sleepFor(63);
}

public void Validate_CallbackQueues_data(String expected_header, String expected_value) {
	String header_from_ui = "";
	String value_from_ui = "";
	sleepFor(10);
	
	int m = countOfElements(By.xpath("//div[contains(@class,'grid-hrow')]/div"));
	System.out.println("\n There are totally "+m+" columns present in the Queues Page.");
	sleepFor(3);
	
	System.out.println("\n Fetching the required column header and value from UI");
	for(int i = 1; i<=m-1; i++)
	{
		header_from_ui = getElement(By.xpath("(//div[contains(@class,'grid-hrow')]/div)["+i+"]"),15).getText();
		if (header_from_ui.equals(expected_header)) {
			sleepFor(5);
			value_from_ui = getElement(By.xpath("(//div[contains(@id,'service-row')]/div)["+i+"]"),15).getText();
			break;
		}
	}
	
	System.out.println("\n Actual Result is :\n Column header: '" + header_from_ui +"', Column value: "+ value_from_ui);
	if (!value_from_ui.contains(expected_value)) {
		System.out.println("\n Expected Result  :\n Column header: '" + expected_header +"', Column value: "+ expected_value);
		Assert.assertEquals(value_from_ui,expected_value);
	}
	System.out.println("\n Validation Passed");
}


	public void Create_CallIn_Group(String val) {
		sleepFor(1);
		try {
			if (getElement(By.xpath("//select/option[text()='"+val+"']"),5).isDisplayed()) {
				Delete_CallIn_Group(val);
				}
		} catch (Exception e) {}
		getElement(btn_create_grp, 15).click();
		getElement(call_in_grp_name, 15).sendKeys(val);
		getElement(btn_save_call_in_grp, 15).click();

		System.out.println("\n The call In Group is created with the name : "+val);

		Add_DNIS(val);

		System.out.println("\n The call In Group is saved with the name : "+val);

		sleepFor(2);
	}	

	public void Add_DNIS(String val) {
		//sleepFor(3);
		getElement(By.xpath("//select/option[text()='"+val+"']"),10).click();
		String dnis_no = Create_new_dnis_Number();

		getElement(call_in_dnis, 15).sendKeys(dnis_no);
		getElement(btn_add_dnis, 15).click();
		System.out.println("\n The dnis "+ dnis_no + " is added ");


		getElement(btn_call_in_save_changes, 15).click();


		sleepFor(2);
	}

	public void Edit_CallIn_Group(String val) {
		//sleepFor(3);
		getElement(By.xpath("//select/option[text()='"+val+"']"),10).click();
		getElement(btn_edit_dnis, 15).click();
		String dnis_no = Create_new_dnis_Number();
		getElement(input_dnis, 15).clear();
		getElement(input_dnis, 15).sendKeys(dnis_no);
		getElement(btn_save_dnis, 15).click();
		System.out.println("\n The dnis is modified to : "+ dnis_no);
		getElement(btn_call_in_save_changes, 15).click();

		System.out.println("\n The call In Group is saved with the name : "+val);

		//sleepFor(2);
	}

	public void Delete_CallIn_Group(String val) {

		getElement(By.xpath("//select/option[text()='"+val+"']"),10).click();
		sleepFor(3);
		getElement(btn_call_in_delete, 15).click();	
		getElement(btn_call_in_confirm_delete, 15).click();
		sleepFor(3);
		System.out.println("\n The call in group is created and deleted successfully:"+val);

	}

	public String Create_new_dnis_Number() {
		String randomNumbers = RandomStringUtils.randomNumeric(4);
		String dnis_number = randomNumbers;
		System.out.println("\n The dnis number that is generated : "+dnis_number);
		return dnis_number;
	}

	public void Set_accesscode_length(String val,String len) {
		//sleepFor(3);
		getElement(By.xpath("//select/option[text()='"+val+"']"),10).click();
		sleepFor(3);
		getElement(edit_accesscode, 15).clear();
		getElement(edit_accesscode, 15).sendKeys(len);
		String a = getElement(error_text_accesscode, 15).getText();
		assertEquals(a, "Out of valid range");	
		System.out.println("\n error is validated successfully ");
	}

	public void Set_expiry_time(String val,String len) {
		//sleepFor(3);
		getElement(By.xpath("//select/option[text()='"+val+"']"),10).click();
		sleepFor(3);
		sleepFor(2);
		System.out.println("\n Successfully selected the group ");
		getElement(edit_expiry, 15).sendKeys("1");
		getElement(edit_expiry, 15).sendKeys(Keys.BACK_SPACE, Keys.BACK_SPACE, Keys.BACK_SPACE, Keys.BACK_SPACE, Keys.BACK_SPACE);
		getElement(edit_expiry, 15).clear();
		System.out.println("\n successfully cleared the input box ");
		getElement(edit_expiry, 15).sendKeys(len);
		System.out.println("\n successfully entered the invalid value ");
		String a = getElement(error_text_expiry, 15).getText();
		System.out.println("\n successfully got the txt:"+a);
		assertEquals(a, "Out of range");	
		System.out.println("\n error is validated successfully ");
	}

	public void Check_Generate_Access_code(String val) {
		//sleepFor(3);
		getElement(By.xpath("//select/option[text()='"+val+"']"),10).click();
		sleepFor(3);
		if (getElement(chk_box_access_code, 15).isSelected())
		{
			System.out.println("\n Checkbox is checked successfully ");
		}
		else
		{
		System.out.println("\n Checkbox is not checked ");
		}
	}

	public void UnCheck_Generate_Access_code(String val) {
		//sleepFor(3);
		getElement(By.xpath("//select/option[text()='"+val+"']"),10).click();
		sleepFor(3);
		if (!getElement(chk_box_access_code, 15).isSelected())
		{
			System.out.println("\n Checkbox is unchecked successfully ");
		}
		else
		{
		System.out.println("\n Checkbox is unchecked ");
		}
	}

	public void Select_Generate_Access_code_chkbox(String val) {
		//sleepFor(3);
		getElement(By.xpath("//select/option[text()='"+val+"']"),10).click();
		sleepFor(3);
		if (getElement(chk_box_access_code, 15).isSelected())
		{
			getElement(chk_box_access_code1, 15).click();
			getElement(chk_box_access_code1, 15).click();
			System.out.println("\n Checkbox is checked successfully ");
		}
		else
		{
			getElement(chk_box_access_code1, 15).click();
			getElement(btn_call_in_save_changes, 15).click();
		System.out.println("\n Checkbox is checked ");
		}
	}

	public void UnSelect_Generate_Access_code_chkbox(String val) {
		//sleepFor(3);
		getElement(By.xpath("//select/option[text()='"+val+"']"),10).click();
		sleepFor(3);
		if (!getElement(chk_box_access_code, 15).isSelected())
		{
			getElement(chk_box_access_code1, 15).click();
			getElement(chk_box_access_code1, 15).click();
			//getElement(btn_call_in_save_changes, 15).click();
			System.out.println("\n Checkbox is unchecked successfully ");
		}
		else
		{
			getElement(chk_box_access_code1, 15).click();
			getElement(btn_call_in_save_changes, 15).click();
		System.out.println("\n Checkbox is unchecked ");
		}
	}
	

//-------------------------Region Affinity------------------------//

public void Region_Affinity_Drag_and_Drop(String val) {		
	dragAndDrop(From,To,15);
    System.out.println("\n drag and drop completed ");
    sleepFor(12);
    getElement(btn_RA_rule_save, 15).click();
  //*[@id="main-and-toaster"]/div[1]/div/main/div/div/div[2]/div[1]/div/button[2]
	System.out.println("\n The Region Affinity rule is created with the name : "+val);
	sleepFor(2);
}	

public void Create_Region_Affinity_Rule_name(String val) {
	sleepFor(1);
	getElement(btn_create_RA_rule, 15).click();
	getElement(RA_rule_name, 15).sendKeys(val);
	getElement(btn_save_RA_rule, 15).click();
	sleepFor(2);
}	

public void Display_Error_no_name() {
	String msg = getElement(By.xpath("//*[@id='main-and-toaster']/div[1]/div/main/div/div/div[1]/div/div/div/div/h4"), 30).getText();
	String expected_response = "Cannot Create a Rule Without a Name";
	System.out.println("\n The message displayed for rule creation without name is :" +msg);
	Assert.assertEquals(expected_response, msg);
}

public void Display_Error_lengthy_name() {
	String msg = getElement(By.xpath("//*[@id='main-and-toaster']/div[1]/div/main/div/div/div[1]/div/div/div/div/h4"), 30).getText();
	String expected_response = "Maximum 32 characters";
	System.out.println("\n The message displayed for rule creation with lengthy name is :" +msg);
	Assert.assertEquals(expected_response, msg);
}

public void Display_Error_no_region() {
	String msg = getElement(By.xpath("//*[@id='main-and-toaster']/div[1]/div/main/div/div/div[2]/div[1]/div/h4"), 30).getText();
	String expected_response = "Preferred region required";
	System.out.println("\n The message displayed for no region available :" +msg);
	Assert.assertEquals(expected_response, msg);
}


public void Delete_Region_Affinity_Rule(String val) {
	
	getElement(By.xpath("//*[@id='main-and-toaster']/div[1]/div/main/div/div/div[1]/div[1]/div/select"),10).click();
	//*[@id="main-and-toaster"]/div[1]/div/main/div/div/div[1]/div[1]/div/select/option[text()='f'
	sleepFor(30);
	getElement(btn_region_affinity_delete, 15).click();	
	getElement(btn_region_affinity_confirm_delete, 15).click();
	sleepFor(3);
	System.out.println("\n The call in group is created and deleted successfully:"+val);
}

public void Select_RA_Rule(String val) {
	//System.out.println("\n The call in gr");
	
	if(val.isEmpty()){
		getElement(By.xpath("//*[@id='services-select']/div/div/select/option[1]"),10).click();		
		sleepFor(3);
	System.out.println("\n The call in group is disassosciated with VQ");}
	else{
		getElement(By.xpath("//*[@id='services-select']/div/div/select/option[text()='"+val+"']"),10).click();	
		sleepFor(3);
		System.out.println("\n The call in group is assosciated with VQ");
	}
}

public void Delete_RA_Rule() {
	getElement(btn_delete_RA_rule, 15).click();	
	getElement(btn_cnf_delete_RA_rule, 15).click();
	sleepFor(3);
	System.out.println("\n The call in group is deleted successfully");

}	

}
