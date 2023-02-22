package ges.tests;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import ges.pages.CallbackPage;
import ges.pages.LoginPage;
import ges.pages.ToolsPage;
import ges.util.BaseTest;

public class ToolsPage_Test extends BaseTest {
	
	/****************************************************************************/
	
	LoginPage loginpage = new LoginPage();
	CallbackPage callbackpage = new CallbackPage();
	ToolsPage toolspage = new ToolsPage();
	
	/****************************************************************************/
	
	private String admin_username = testdata_property_file_reader("Admin_username");
	private String admin_password = testdata_property_file_reader("Admin_password");
	
	/****************************************************************************/
	
	private String invalid_callback_id = "abc123";
	private String invalid_customer_number = "1111111111";
	private String valid_VQ_name = "GES_UI_AUTO_VQ";
	
	/****************************************************************************/

	@BeforeClass
	
	public void beforeClass() {
		
	System.out.println(
			"***************************************************************************************** \n\n");
	System.out.println("				Running Functional UI testcases - Tools Page");
	System.out.println(
			"\n\n*****************************************************************************************");
	}
	
	@Test(priority=1, enabled=true, groups = {"tools"}, testName = "Verify if the Tenant Configuration is refetched successfully", description = "Click to refetch the tenant configuration")
	public void Verify_TenantConfiguration_refetched_successfully() {
		try
		{
		loginpage.login_as(admin_username, admin_password);
		loginpage.verifyLogin(admin_username);
		toolspage.Navigate_To_ToolsTab();
		toolspage.Click_Refetch_Tenant_Config();
		toolspage.Check_success_Refetch_Tenant_Config();
		toolspage.Click_Callback_search();
		}
		finally
		{
		callbackpage.signout();	
		}
	}
	
	@Test(priority=2, enabled=true, groups = {"tools"}, testName = "Verify if callback search column headers are displayed successfully", description = "Callback Search option should display the column headers successfully")
	public void Verify_Callback_is_searched_and_ColumnHeaders_verified_successfully() {
		try
		{
		String ph_no = Create_new_phoneNumber();
		loginpage.login_as(admin_username, admin_password);
		loginpage.verifyLogin(admin_username);
		//testdata_property_file_writer(ges_ver);
		callbackpage.createImmediateCallback_and_verify(ph_no);
		callbackpage.displayCallbackDetails_toConsole(ph_no);
		String Callback_id = callbackpage.Get_CallbackID(ph_no);
		System.out.println("\n The Callback ID is :" +Callback_id);
		callbackpage.cancel_Callback(ph_no);
		toolspage.Navigate_To_ToolsTab();
		toolspage.Click_Callback_search();
		toolspage.Select_Filter_CallbackID();
		toolspage.Enter_CallbackID(Callback_id);
		toolspage.Click_SearchButton();
		toolspage.Verify_CallbackDetails_Headers();
		}
		finally
		{
		callbackpage.signout();	
		}
	}
	
	@Test(priority=3, enabled=true, groups = {"tools"}, testName = "Verify if callback search is successful using callback ID", description = "Callback Search option using ID should retrieve the corresponding callback details successfully")
	public void Verify_Callback_is_searched_using_ID_successfully() {
		try
		{
		String ph_no = Create_new_phoneNumber();
		loginpage.login_as(admin_username, admin_password);
		loginpage.verifyLogin(admin_username);
		//testdata_property_file_writer(ges_ver);
		callbackpage.createImmediateCallback_and_verify(ph_no);
		callbackpage.displayCallbackDetails_toConsole(ph_no);
		String Callback_id = callbackpage.Get_CallbackID(ph_no);
		System.out.println("\n The Callback ID is :" +Callback_id);
		callbackpage.cancel_Callback(ph_no);
		toolspage.Navigate_To_ToolsTab();
		toolspage.Click_Callback_search();
		toolspage.Select_Filter_CallbackID();
		toolspage.Enter_CallbackID(Callback_id);
		toolspage.Click_SearchButton();
		toolspage.Verify_CallbackDetail_ID(Callback_id);
		}
		finally
		{
		callbackpage.signout();	
		}
	}
	
	@Test(priority=4, enabled=true, groups = {"tools"}, testName = "Verify if callback search is successful using customer number", description = "Callback Search option using Customer Number should retrieve the corresponding callback details successfully")
	public void Verify_Callback_is_searched_using_CustomerNumber_successfully() {
		try
		{
		String ph_no = Create_new_phoneNumber();
		loginpage.login_as(admin_username, admin_password);
		loginpage.verifyLogin(admin_username);
		//testdata_property_file_writer(ges_ver);
		callbackpage.createImmediateCallback_and_verify(ph_no);
		callbackpage.displayCallbackDetails_toConsole(ph_no);
		String Callback_id = callbackpage.Get_CallbackID(ph_no);
		System.out.println("\n The Callback ID is :" +Callback_id);
		callbackpage.cancel_Callback(ph_no);
		toolspage.Navigate_To_ToolsTab();
		toolspage.Click_Callback_search();
		toolspage.Select_Filter_CallbackNumber();
		toolspage.Enter_CallbackNumber(ph_no);
		toolspage.Click_SearchButton();
		toolspage.Verify_CallbackDetail_Number(ph_no);
		}
		finally
		{
		callbackpage.signout();	
		}
	}
	
	@Test(priority=5, enabled=true, groups={"tools"}, testName = "Verify if the callback search displays - 'No Callbacks found' when invalid callback ID is entered in the search field", description = "Callback Search option using invalid Callback ID")
	public void Verify_Callback_is_searched_using_invalid_Callback_ID() {
		try
		{
		loginpage.login_as(admin_username, admin_password);
		loginpage.verifyLogin(admin_username);
		toolspage.Navigate_To_ToolsTab();
		toolspage.Click_Callback_search();
		toolspage.Select_Filter_CallbackID();
		toolspage.Enter_CallbackID(invalid_callback_id);
		toolspage.Click_SearchButton();
		toolspage.Verify_No_Callbacks_Found_Error_Message_id(invalid_callback_id);
		}
		finally
		{
		callbackpage.signout();	
		}
	}
	
	@Test(priority=6, enabled=true, groups={"tools"}, testName = "Verify if the callback search displays - 'No Callbacks found' when invalid Customer number is entered in the search field", description = "Callback Search option using invalid Customer Number")
	public void Verify_Callback_is_searched_using_invalid_Customer_Number() {
		try
		{
		loginpage.login_as(admin_username, admin_password);
		loginpage.verifyLogin(admin_username);
		toolspage.Navigate_To_ToolsTab();
		toolspage.Click_Callback_search();
		toolspage.Select_Filter_CallbackNumber();
		toolspage.Enter_CallbackNumber(invalid_customer_number);
		toolspage.Click_SearchButton();
		toolspage.Verify_No_Callbacks_Found_Error_Message_number(invalid_customer_number);
		}
		finally
		{
		callbackpage.signout();	
		}
	}
	
	@Test(priority=7, enabled=true, groups = {"tools", "capacity"}, testName = "Verify if capacity rule is created and deleted successfully", description = "Create a new capacity rule. Delete the created capacity rule")
	public void Verify_Create_Capacity_Rule() {
		try
		{
		loginpage.login_as(admin_username, admin_password);
		loginpage.verifyLogin(admin_username);
		toolspage.Navigate_To_ToolsTab();
		toolspage.Click_Capacity();
		toolspage.Click_Add_New_Capacity();
		toolspage.Enter_CapacityName();
		toolspage.Click_CreateRule_Button();
		toolspage.Enter_CapacityDetails();
		toolspage.Click_SaveButton();
		toolspage.Click_Delete_Capacity();
		}
		finally
		{
		callbackpage.signout();	
		}
	}
	
	@Test(priority=8, enabled=true, groups = {"tools", "permitted and blocked numbers"}, testName = "Verify if rule is created and deleted successfully", description = "Create a new permitted and blocked numbers rule. Delete the created rule")
	public void Verify_Create_Permitted_and_Blocked_Numbers_Rule() {
		try
		{
		loginpage.login_as(admin_username, admin_password);
		loginpage.verifyLogin(admin_username);
		toolspage.Navigate_To_ToolsTab();
		toolspage.Click_Permitted_and_Blocked_Numbers();
		toolspage.Create_New_Rule("India");
		toolspage.display_currently_selected_countries();
		toolspage.Delete_Rule();
		}
		finally
		{
		callbackpage.signout();	
		}
		
	}
	
	@Test(priority=9, enabled=true, groups = {"tools", "permitted and blocked numbers"}, testName = "Verify if rule is created and an additional country is selected successfully", description = "Create a new permitted and blocked numbers rule. Add a new country to allow callbacks. lDelete the created rule")
	public void Verify_Create_Permitted_and_Blocked_Numbers_Rule_updateCountries() {
		try
		{
		loginpage.login_as(admin_username, admin_password);
		loginpage.verifyLogin(admin_username);
		toolspage.Navigate_To_ToolsTab();
		toolspage.Click_Permitted_and_Blocked_Numbers();
		toolspage.Create_New_Rule("India");
		toolspage.display_currently_selected_countries();
		toolspage.Selecting_new_country();
		toolspage.Click_Save_Changes_Button();
		toolspage.display_currently_selected_countries();
		toolspage.Delete_Rule();
		}
		finally
		{
		callbackpage.signout();	
		}
	}
	
	@Test(priority=10, enabled=true, groups = {"tools", "permitted and blocked numbers"}, testName = "Verify if test Indian phone number is in allowable list of countries successfully", description = "Create a new permitted and blocked numbers rule. Test phone number with correct country code. Delete the created rule")
	public void Verify_Valid_Test_PhoneNumbers() {
		try
		{
		loginpage.login_as(admin_username, admin_password);
		loginpage.verifyLogin(admin_username);
		toolspage.Navigate_To_ToolsTab();
		toolspage.Click_Permitted_and_Blocked_Numbers();
		toolspage.Create_New_Rule("India");
		toolspage.display_currently_selected_countries();
		toolspage.Enter_Valid_Test_PhoneNumber("+9198");
		toolspage.Display_Valid_Result_TestPhoneNumber();
		toolspage.Enter_Valid_Test_PhoneNumber("9198");
		toolspage.Display_Valid_Result_TestPhoneNumber();
		toolspage.Enter_Valid_Test_PhoneNumber("9198");
		toolspage.Display_Valid_Result_TestPhoneNumber();
		toolspage.Delete_Rule();
		}
		finally
		{
		callbackpage.signout();	
		}
	}
	
	@Test(priority=10, enabled=true, groups = {"tools", "permitted and blocked numbers"}, testName = "Verify if test UK phone number is in allowable list of countries successfully", description = "Create a new permitted and blocked numbers rule. Test UK phone number with correct country code. Delete the created rule")
	public void Verify_Valid_Test_UKPhoneNumber() {
		try
		{
		loginpage.login_as(admin_username, admin_password);
		loginpage.verifyLogin(admin_username);
		toolspage.Navigate_To_ToolsTab();
		toolspage.Click_Permitted_and_Blocked_Numbers();
		toolspage.Create_New_Rule_UK();
		toolspage.display_currently_selected_countries();
		toolspage.Enter_Valid_Test_PhoneNumber("+4473");
		toolspage.Display_Valid_Result_TestUKPhoneNumber();
		toolspage.Enter_Valid_Test_PhoneNumber("4473");
		toolspage.Display_Valid_Result_TestUKPhoneNumber();
		toolspage.Delete_Rule();
		}
		finally
		{
		callbackpage.signout();	
		}
	}
	/*
	@Test(priority=10, enabled=true, groups = {"tools", "permitted and blocked numbers"}, testName = "Verify if test UK phone number is in allowable list of countries successfully", description = "Create a new permitted and blocked numbers rule. Test UK phone number with correct country code. Delete the created rule")
	public void Verify_Valid_Test_UKPhoneNumber() {
		try
		{
		loginpage.login_as(admin_username, admin_password);
		loginpage.verifyLogin(admin_username);
		toolspage.Navigate_To_ToolsTab();
		toolspage.Click_Permitted_and_Blocked_Numbers();
		toolspage.Create_New_Rule("UK");
		toolspage.display_currently_selected_countries();
		toolspage.Enter_Valid_Test_PhoneNumber("+4473");
		toolspage.Display_Valid_Result_TestUKPhoneNumber();
		toolspage.Enter_Valid_Test_PhoneNumber("4473");
		toolspage.Display_Valid_Result_TestUKPhoneNumber();
		toolspage.Delete_Rule();
		}
		finally
		{
		callbackpage.signout();	
		}
	}
	*/
	@Test(priority=11, enabled=true, groups = {"tools", "permitted and blocked numbers"}, testName = "Verify if test phone number(invalid) fails and is not in allowable list of countries", description = "Create a new permitted and blocked numbers rule. Test phone number with another country code. Delete the created rule")
	public void Verify_Invalid_Test_PhoneNumbers() {
		try
		{
		loginpage.login_as(admin_username, admin_password);
		loginpage.verifyLogin(admin_username);
		toolspage.Navigate_To_ToolsTab();
		toolspage.Click_Permitted_and_Blocked_Numbers();
		toolspage.Create_New_Rule("India");
		toolspage.display_currently_selected_countries();
		toolspage.Enter_Invalid_Test_PhoneNumber();
		toolspage.Display_Invalid_Result_TestPhoneNumber("Test Failed! The country of the phone number is not in the allowable list: Pakistan (PK).");
		toolspage.Delete_Rule();
		}
		finally
		{
		callbackpage.signout();	
		}
	}
	
	@Test(priority=12, enabled=true, groups = {"tools", "permitted and blocked numbers"}, testName = "Verify if US test phone number(more than 10 digits) fails and is not able to determine region of phone number.", description = "Create a new permitted and blocked numbers rule. Test phone number with US country code and phone number more than 10 digit. Delete the created rule")
	public void Verify_Invalid_Test_PhoneNumbers_WithMoreThan10Digit() {
		try
		{
		loginpage.login_as(admin_username, admin_password);
		loginpage.verifyLogin(admin_username);
		toolspage.Navigate_To_ToolsTab();
		toolspage.Click_Permitted_and_Blocked_Numbers();
		toolspage.Create_New_Rule("India");
		toolspage.display_currently_selected_countries();
		toolspage.Enter_Invalid_Test_PhoneNumber_MoreDigit("+141");
		toolspage.Display_Invalid_Result_TestPhoneNumber("Unable to determine region of phone number.");
		toolspage.Delete_Rule();
		}
		finally
		{
		callbackpage.signout();	
		}
	}
	
	@Test(priority=12, enabled=true, groups = {"tools", "permitted and blocked numbers"}, testName = "Verify if US test premium phone number fails and shows warning for premium number", description = "Create a new permitted and blocked numbers rule. Test phone number with US country code and premium phone number. Delete the created rule")
	public void Verify_Premium_Test_USPhoneNumbers() {
		try
		{
		loginpage.login_as(admin_username, admin_password);
		loginpage.verifyLogin(admin_username);
		toolspage.Navigate_To_ToolsTab();
		toolspage.Click_Permitted_and_Blocked_Numbers();
		toolspage.Create_New_Rule("US");
		toolspage.display_currently_selected_countries();
		toolspage.Enter_Test_PhoneNumber("19007894561");
		toolspage.Display_Warning_Result_TestPhoneNumber("Number passes country validation, no exceptions to test! Phone number is from country: United States (US). This is a premium number and will be blocked by default.");
		toolspage.Delete_Rule();
		}
		finally
		{
		callbackpage.signout();	
		}
	}
	
	@Test(priority=13, enabled=true, groups = {"tools", "permitted and blocked numbers"}, testName = "Verify if error message is displayed when the rule is created with special characters", description = "Create a new permitted and blocked numbers rule with special characters")
	public void Verify_ErrorMessage_displayed_Rule_Creeated_with_specialCharacters() {
		try
		{
		loginpage.login_as(admin_username, admin_password);
		loginpage.verifyLogin(admin_username);
		toolspage.Navigate_To_ToolsTab();
		toolspage.Click_Permitted_and_Blocked_Numbers();
		toolspage.Create_Rule_SpecialCharacters();
		toolspage.Close_RuleCreation_Modal();
		}
		finally
		{
		callbackpage.signout();	
		}
	}
	
	@Test(priority=13, enabled=false, groups = {"tools", "Queues"}, testName = "Verify if callback queues column headers are displayed successfully", description = "Callback Queue page should display the column headers successfully")
	public void Verify_Callback_Queuepage_ColumnHeaders_successfully() {
		try
		{
		loginpage.login_as(admin_username, admin_password);
		loginpage.verifyLogin(admin_username);
		toolspage.Navigate_To_ToolsTab();
		toolspage.Click_Queues();
		toolspage.Verify_VQ_Search(valid_VQ_name);
		toolspage.Verify_CallbackQueues_Headers();
		}
		finally
		{
		callbackpage.signout();	
		}
	}
	
	@Test(priority=14, enabled=true, groups = {"tools", "call in groups"}, testName = "Verify if call in group is created and deleted successfully", description = "Call in group should be created and deleted successfully")
	public void Verify_create_Click_to_callin_group_successfully() {
		try
		{
		loginpage.login_as(admin_username, admin_password);
		loginpage.verifyLogin(admin_username);
		toolspage.Navigate_To_ToolsTab();
		toolspage.Click_CallIn_Group();
		toolspage.Create_CallIn_Group("pr");
		toolspage.Delete_CallIn_Group("pr");
		}
		finally
		{
		callbackpage.signout();	
		}
	}
	
	@Test(priority=15, enabled=true, groups = {"tools", "call in groups"}, testName = "Verify if call in group dnis is edited successfully", description = "Call in group should be created, dnis is edited and deleted successfully")
	public void Verify_edit_Click_to_callin_group_successfully() {
		try
		{
		loginpage.login_as(admin_username, admin_password);
		loginpage.verifyLogin(admin_username);
		toolspage.Navigate_To_ToolsTab();
		toolspage.Click_CallIn_Group();
		toolspage.Create_CallIn_Group("pr");
		toolspage.Edit_CallIn_Group("pr");
		toolspage.Delete_CallIn_Group("pr");
		}
		finally
		{
		callbackpage.signout();	
		}
	}
	
	@Test(priority=16, enabled=true, groups = {"tools", "call in groups"}, testName = "Verify if second dnis is added to existing call in group successfully", description = "Call in group should be created, add 2 dnis and deleted successfully")
	public void Verify_add_DNIS_to_Click_to_callin_group_successfully() {
		try
		{
		loginpage.login_as(admin_username, admin_password);
		loginpage.verifyLogin(admin_username);
		toolspage.Navigate_To_ToolsTab();
		toolspage.Click_CallIn_Group();
		toolspage.Create_CallIn_Group("pr");
		toolspage.Add_DNIS("pr");
		toolspage.Delete_CallIn_Group("pr");
		}
		finally
		{
		callbackpage.signout();	
		}
	}
	
	@Test(priority=17, enabled=true, groups = {"tools", "call in groups"}, testName = "Verify if error is thrown for minimum accesscode length ", description = "ERROR should be thrown when access code length is below 4")
	public void Verify_min_access_length_error() {
		try
		{
		loginpage.login_as(admin_username, admin_password);
		loginpage.verifyLogin(admin_username);
		toolspage.Navigate_To_ToolsTab();
		toolspage.Click_CallIn_Group();
		toolspage.Create_CallIn_Group("pr");
		toolspage.Set_accesscode_length("pr","3");
		//toolspage.Add_DNIS("pr");
		toolspage.Delete_CallIn_Group("pr");
		}
		finally
		{
		callbackpage.signout();	
		}
	}
	
	@Test(priority=18, enabled=true, groups = {"tools", "call in groups"}, testName = "Verify if error is thrown for maximum accesscode length ", description = "ERROR should be thrown when access code length is exceeds 12")
	public void Verify_max_access_length_error() {
		try
		{
		loginpage.login_as(admin_username, admin_password);
		loginpage.verifyLogin(admin_username);
		toolspage.Navigate_To_ToolsTab();
		toolspage.Click_CallIn_Group();
		toolspage.Create_CallIn_Group("pr");
		toolspage.Set_accesscode_length("pr","13");
		//toolspage.Add_DNIS("pr");
		toolspage.Delete_CallIn_Group("pr");
		}
		finally
		{
		callbackpage.signout();	
		}
	}
	
	@Test(priority=19, enabled=false, groups = {"tools", "call in groups"}, testName = "Verify if error is thrown for minimum expiry time ", description = "ERROR should be thrown when expiry time is below 10")
	public void Verify_min_expiry_time_error() {
		try
		{
		loginpage.login_as(admin_username, admin_password);
		loginpage.verifyLogin(admin_username);
		toolspage.Navigate_To_ToolsTab();
		toolspage.Click_CallIn_Group();
		toolspage.Create_CallIn_Group("pr");
		toolspage.Set_expiry_time("pr","9");
		//toolspage.Add_DNIS("pr");
		toolspage.Delete_CallIn_Group("pr");
		}
		finally
		{
		callbackpage.signout();	
		}
	}
	
	@Test(priority=20, enabled=false, groups = {"tools", "call in groups"}, testName = "Verify if error is thrown for maximum expiry time ", description = "ERROR should be thrown when expiry time is exceeds 1800")
	public void Verify_max_expiry_time_error() {
		try
		{
		loginpage.login_as(admin_username, admin_password);
		loginpage.verifyLogin(admin_username);
		toolspage.Navigate_To_ToolsTab();
		toolspage.Click_CallIn_Group();
		toolspage.Create_CallIn_Group("pr");
		toolspage.Set_expiry_time("pr","1801");
		//toolspage.Add_DNIS("pr");
		toolspage.Delete_CallIn_Group("pr");
		}
		finally
		{
		callbackpage.signout();	
		}
	}
	
	@Test(priority=21, enabled=true, groups = {"tools", "call in groups"}, testName = "Verify if Generate access code checkbox is checked by default", description = "Generate Access Code checkbox is checked by default when a group is created")
	public void Verify_access_code_checkbox() {
		try
		{
		loginpage.login_as(admin_username, admin_password);
		loginpage.verifyLogin(admin_username);
		toolspage.Navigate_To_ToolsTab();
		toolspage.Click_CallIn_Group();
		toolspage.Create_CallIn_Group("pr");
		toolspage.Check_Generate_Access_code("pr");
		//toolspage.Add_DNIS("pr");
		toolspage.Delete_CallIn_Group("pr");
		}
		finally
		{
		callbackpage.signout();	
		}
	}
	
	@Test(priority=22, enabled=true, groups = {"tools", "call in groups"}, testName = "Verify if Generate access code checkbox is unchecked", description = "Generate Access Code checkbox is unchecked when the user is navigated between different call in groups")
	public void Verify_access_code_checkbox_is_unchecked() {
		try
		{
		loginpage.login_as(admin_username, admin_password);
		loginpage.verifyLogin(admin_username);
		toolspage.Navigate_To_ToolsTab();
		toolspage.Click_CallIn_Group();
		toolspage.Create_CallIn_Group("pr");
		toolspage.Select_Generate_Access_code_chkbox("pr");
		toolspage.Create_CallIn_Group("pr1");
		toolspage.Check_Generate_Access_code("pr");
		//toolspage.Add_DNIS("pr");
		toolspage.Delete_CallIn_Group("pr");
		toolspage.Delete_CallIn_Group("pr1");
		}
		finally
		{
		callbackpage.signout();	
		}
	}
	
	@Test(priority=23, enabled=true, groups = {"tools", "call in groups"}, testName = "Verify if Generate access code checkbox is checked", description = "Generate Access Code checkbox is checked when the user is navigated between different call in groups")
	public void Verify_access_code_checkbox_is_checked() {
		try
		{
		loginpage.login_as(admin_username, admin_password);
		loginpage.verifyLogin(admin_username);
		toolspage.Navigate_To_ToolsTab();	
		toolspage.Click_CallIn_Group();
		toolspage.Create_CallIn_Group("pr");
		toolspage.UnSelect_Generate_Access_code_chkbox("pr");
		toolspage.Create_CallIn_Group("pr2");
		toolspage.UnCheck_Generate_Access_code("pr");
		//toolspage.Add_DNIS("pr");
		toolspage.Delete_CallIn_Group("pr");
		toolspage.Delete_CallIn_Group("pr2");
		}
		finally
		{
		callbackpage.signout();	
		}
	}
	

		
	// -------------- Region Affinity-------------------------//

	
	@Test(priority=24, enabled=false, groups = {"RA"}, testName = "Verify if Region Affinity Rule is created", description = "Create a Region affinity rule and verify it is created")
	public void Verify_Region_Affinity_Rule_Creation() {
		loginpage.login_as(admin_username, admin_password);
		loginpage.verifyLogin(admin_username);
		toolspage.Navigate_To_ToolsTab();	
		toolspage.Click_Region_Affinity();
		toolspage.Create_Region_Affinity_Rule_name("pr");
		toolspage.Region_Affinity_Drag_and_Drop("pr");
		toolspage.Delete_Region_Affinity_Rule("pr");
		callbackpage.signout();
	}
	
	@Test(priority=25, enabled=true, groups = {"RA"}, testName = "Verify if error is thrown if Region Affinity Rule is created with no name", description = "Create a Region affinity rule without name and verify error is thrown")
	public void Verify_Error_Region_Affinity_Rule_no_name() {
		loginpage.login_as(admin_username, admin_password);
		loginpage.verifyLogin(admin_username);
		toolspage.Navigate_To_ToolsTab();	
		toolspage.Click_Region_Affinity();
		toolspage.Create_Region_Affinity_Rule_name("");
		toolspage.Display_Error_no_name();
		//toolspage.Delete_Region_Affinity_Rule("pr");
		callbackpage.signout();
	}
	
	@Test(priority=26, enabled=true, groups = {"RA"}, testName = "Verify if error is thrown if Region Affinity Rule is created with lengthy name", description = "Create a Region affinity rule with name length more than 32 and verify error is thrown")
	public void Verify_Error_Region_Affinity_Rule_lengthy_name() {
		loginpage.login_as(admin_username, admin_password);
		loginpage.verifyLogin(admin_username);
		toolspage.Navigate_To_ToolsTab();	
		toolspage.Click_Region_Affinity();
		toolspage.Create_Region_Affinity_Rule_name("asdfghjkasdfghjkasdfghjkasdfghjk1");
		toolspage.Display_Error_lengthy_name();
		//toolspage.Delete_Region_Affinity_Rule("pr");
		callbackpage.signout();
	}
	
	@Test(priority=27, enabled=true, groups = {"RA"}, testName = "Verify if error is shown when no preferred region is available", description = "Create a Region affinity rule and check error msg is thrown when no preferred region is added")
	public void Verify_Error_message_RA_without_preferred_region() {
		loginpage.login_as(admin_username, admin_password);
		loginpage.verifyLogin(admin_username);
		toolspage.Navigate_To_ToolsTab();	
		toolspage.Click_Region_Affinity();
		toolspage.Create_Region_Affinity_Rule_name("pr");
		toolspage.Display_Error_no_region();
		//toolspage.Delete_Region_Affinity_Rule("pr");
		callbackpage.signout();
	}
	
	@Test(priority=28, enabled=true, groups = {"RA"}, testName = "Verify if RA rule is deleted successfully", description = "Create a RA rule and delete it successfully")
	public void Verify_RA_Rule_Deletion() {
		loginpage.login_as(admin_username, admin_password);
		loginpage.verifyLogin(admin_username);
		toolspage.Navigate_To_ToolsTab();	
		toolspage.Click_Region_Affinity();
		toolspage.Create_Region_Affinity_Rule_name("pr");
		toolspage.Delete_RA_Rule();
		//toolspage.Delete_Region_Affinity_Rule("pr");
		callbackpage.signout();
	}
	
}
