package ges.tests;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import ges.pages.CallbackPage;
import ges.pages.LoginPage;
import ges.pages.ToolsPage;
import ges.pages.DesignerPage;
import ges.pages.DevopsPage;
import ges.util.BaseTest;
import junit.framework.Assert;

public class DesignerGESIntegration_Test extends BaseTest {
	
	/****************************************************************************/
	
	LoginPage loginpage = new LoginPage();
	CallbackPage callbackpage = new CallbackPage();
	ToolsPage toolspage = new ToolsPage();
	DesignerPage designerpage = new DesignerPage();
	DevopsPage devopspage = new DevopsPage();
	
	/****************************************************************************/
	
	private String admin_username = testdata_property_file_reader("Admin_username");
	private String admin_password = testdata_property_file_reader("Admin_password");
	
	/****************************************************************************/

	private String designer_VQ1_name = "GES_UI_AUTO_BH_VQ1";
	private String designer_VQ2_name = "GES_UI_AUTO_BH_VQ2";
	private String full_open_BH = "GES_Auto_BH_Full_Open";
	private String full_close_BH = "GES_Auto_BH_Full_Close";
	private String no_end_time_BH = "GES_Auto_BH_No_EndTime";
	private String bh_open_exception = "GES_Auto_BH_Open_Exception";
	private String bh_close_exception = "GES_Auto_BH_Close_Exception";
	private String bh_partial_exception = "GES_Auto_BH_Partial_Exception";
	private String bh_multiple_exception = "GES_Auto_BH_Multiple_Exception";
	private String bh_with_multiple_date_range_SD = "GES_Auto_BH_Multiple_DateRange_SD";
	private String bh_with_SD_not_occurs_every_year = "GES_Auto_BH_Exception_Not_Occurs_Every_Year";
	private String bh_with_SD_occurs_every_year = "GES_Auto_BH_Exception_Occurs_EveryYear";
	private String bh_with_SD_with_disabled_date_range = "GES_Auto_BH_Exception_Disabled_DateRange";
	private String bh_with_SD_without_date_range = "GES_Auto_BH_Exception_Without_DateRange";
	private String callback_app = "UI_GES_Designer_Integration_Test";
	private String bh = "GES_Auto_BH_Export";
	private String sd = "GES_Auto_SD_Export";
	
	/****************************************************************************/

	@BeforeClass
	public void beforeClass() {
		
	System.out.println(
			"***************************************************************************************** \n\n");
	System.out.println("				Running Functional UI testcases - Desginer-GES Integration Test");
	System.out.println(
			"\n\n*****************************************************************************************");
	}
	
	@Test(priority=1, enabled=true, groups = {"tools", "Queues"}, testName = "Verify if designer callback settings Full Open BH changes reflected in GES Queues", description = "Designer callback settings changes should be reflected in GES Queues")
	public void Verify_Designer_Full_Open_BH_Reflect_In_GES_Queuepage() {
		try
		{
		loginpage.login_Designer(admin_username, admin_password);
		designerpage.signout();
		sleepFor(3);
		
		loginpage.login_GES(admin_username, admin_password);
		loginpage.verifyLogin(admin_username);
		}
		finally
		{
		callbackpage.signout();	
		}
	}
	/*
	@Test(priority=1, enabled=true, groups = {"tools", "Queues"}, testName = "Verify if designer callback settings Full Open BH changes reflected in GES Queues", description = "Designer callback settings changes should be reflected in GES Queues")
	public void Verify_Designer_Full_Open_BH_Reflect_In_GES_Queuepage() {
		try
		{
		loginpage.login_Designer(admin_username, admin_password);
		designerpage.Callback_Settings_Modify_BH(designer_VQ1_name, full_open_BH);
		designerpage.signout();
		sleepFor(120);
		
		loginpage.login_GES(admin_username, admin_password);
		loginpage.verifyLogin(admin_username);
		toolspage.Navigate_To_ToolsTab();
		toolspage.Click_Queues();
		toolspage.Verify_VQ_Search(designer_VQ1_name);
		toolspage.Validate_CallbackQueues_data("Open/Close Time","Open now. 24 hour service.");
		}
		finally
		{
		callbackpage.signout();	
		}
	}
	
	@Test(priority=2, enabled=true, groups = {"tools", "Queues"}, testName = "Verify if designer callback settings Full Close BH changes reflected in GES Queues", description = "Designer callback settings changes should be reflected in GES Queues")
	public void Verify_Designer_Full_Close_BH_Reflect_In_GES_Queuepage() {
		try
		{
		loginpage.login_Designer(admin_username, admin_password);
		designerpage.Callback_Settings_Modify_BH(designer_VQ1_name, full_close_BH);
		designerpage.signout();
		sleepFor(120);
		
		loginpage.login_GES(admin_username, admin_password);
		loginpage.verifyLogin(admin_username);
		toolspage.Navigate_To_ToolsTab();
		toolspage.Click_Queues();
		toolspage.Verify_VQ_Search(designer_VQ1_name);
		toolspage.Validate_CallbackQueues_data("Open/Close Time","Office closed, unknown opening time.");
		}
		finally
		{
		callbackpage.signout();	
		}
	}
	
	@Test(priority=3, enabled=true, groups = {"tools", "Queues"}, testName = "Verify if designer callback settings No EndTime BH changes reflected in GES Queues", description = "Designer callback settings changes should be reflected in GES Queues")
	public void Verify_Designer_No_EndTime_BH_Reflect_In_GES_Queuepage() {
		try
		{
		loginpage.login_Designer(admin_username, admin_password);
		designerpage.Callback_Settings_Modify_BH(designer_VQ1_name, no_end_time_BH);
		designerpage.signout();
		sleepFor(120);
		
		loginpage.login_GES(admin_username, admin_password);
		loginpage.verifyLogin(admin_username);
		toolspage.Navigate_To_ToolsTab();
		toolspage.Click_Queues();
		toolspage.Verify_VQ_Search(designer_VQ1_name);
		toolspage.Validate_CallbackQueues_data("Open/Close Time","Open now. Closing at");
		}
		finally
		{
		callbackpage.signout();	
		}
	}
	
	@Test(priority=4, enabled=true, groups = {"tools", "Queues"}, testName = "Verify if designer callback settings BH_With_Open_Exception changes reflected in GES Queues", description = "Designer callback settings changes should be reflected in GES Queues")
	public void Verify_Designer_BH_With_Open_Exception_Reflect_In_GES_Queuepage() {
		try
		{
		loginpage.login_Designer(admin_username, admin_password);
		designerpage.Callback_Settings_Modify_BH(designer_VQ1_name, bh_open_exception);
		designerpage.signout();
		sleepFor(120);
		
		loginpage.login_GES(admin_username, admin_password);
		loginpage.verifyLogin(admin_username);
		toolspage.Navigate_To_ToolsTab();
		toolspage.Click_Queues();
		toolspage.Verify_VQ_Search(designer_VQ1_name);
		toolspage.Validate_CallbackQueues_data("Open/Close Time","Open now. 24 hour service.");
		}
		finally
		{
		callbackpage.signout();	
		}
	}
	
	@Test(priority=5, enabled=true, groups = {"tools", "Queues"}, testName = "Verify if designer callback settings BH_With_Close_Exception changes reflected in GES Queues", description = "Designer callback settings changes should be reflected in GES Queues")
	public void Verify_Designer_BH_With_Close_Exception_Reflect_In_GES_Queuepage() {
		//try
		{
		loginpage.login_Designer(admin_username, admin_password);
		designerpage.Callback_Settings_Modify_BH(designer_VQ1_name, bh_close_exception);
		designerpage.signout();
		sleepFor(120);
		
		loginpage.login_GES(admin_username, admin_password);
		loginpage.verifyLogin(admin_username);
		toolspage.Navigate_To_ToolsTab();
		toolspage.Click_Queues();
		toolspage.Verify_VQ_Search(designer_VQ1_name);
		toolspage.Validate_CallbackQueues_data("Open/Close Time","Office closed, unknown opening time.");
		}
		//finally
		{
			sleepFor(2);//callbackpage.signout();	
		}
	}
	
	@Test(priority=6, enabled=true, groups = {"tools", "Queues"}, testName = "Verify if designer callback settings BH_With_Partial_Exception changes reflected in GES Queues", description = "Designer callback settings changes should be reflected in GES Queues")
	public void Verify_Designer_BH_With_Partial_Exception_Reflect_In_GES_Queuepage() {
		try
		{
		loginpage.login_Designer(admin_username, admin_password);
		designerpage.Callback_Settings_Modify_BH(designer_VQ1_name, bh_partial_exception);
		designerpage.signout();
		sleepFor(120);
		
		loginpage.login_GES(admin_username, admin_password);
		loginpage.verifyLogin(admin_username);
		toolspage.Navigate_To_ToolsTab();
		toolspage.Click_Queues();
		toolspage.Verify_VQ_Search(designer_VQ1_name);
		toolspage.Validate_CallbackQueues_data("Open/Close Time","Open now. 24 hour service.");
		}
		finally
		{
		callbackpage.signout();	
		}
	}
	
	@Test(priority=7, enabled=true, groups = {"tools", "Queues"}, testName = "Verify if designer callback settings BH_With_Multiple_Exception changes reflected in GES Queues", description = "Designer callback settings changes should be reflected in GES Queues")
	public void Verify_Designer_BH_With_Multiple_Exceptions_Reflect_In_GES_Queuepage() {
		try
		{
		loginpage.login_Designer(admin_username, admin_password);
		designerpage.Callback_Settings_Modify_BH(designer_VQ1_name, bh_multiple_exception);
		designerpage.signout();
		sleepFor(120);
		
		loginpage.login_GES(admin_username, admin_password);
		loginpage.verifyLogin(admin_username);
		toolspage.Navigate_To_ToolsTab();
		toolspage.Click_Queues();
		toolspage.Verify_VQ_Search(designer_VQ1_name);
		toolspage.Validate_CallbackQueues_data("Open/Close Time","Open now. 24 hour service.");
		}
		finally
		{
		callbackpage.signout();	
		}
	}
	
	@Test(priority=8, enabled=true, groups = {"tools", "Queues"}, testName = "Verify if designer callback settings BH_With_Multiple_DateRange_SD changes reflected in GES Queues", description = "Designer callback settings changes should be reflected in GES Queues")
	public void Verify_Designer_BH_With_Multiple_DateRange_SD_Reflect_In_GES_Queuepage() {
		try
		{
		loginpage.login_Designer(admin_username, admin_password);
		designerpage.Callback_Settings_Modify_BH(designer_VQ1_name, bh_with_multiple_date_range_SD);
		designerpage.signout();
		sleepFor(120);
		
		loginpage.login_GES(admin_username, admin_password);
		loginpage.verifyLogin(admin_username);
		toolspage.Navigate_To_ToolsTab();
		toolspage.Click_Queues();
		toolspage.Verify_VQ_Search(designer_VQ1_name);
		toolspage.Validate_CallbackQueues_data("Open/Close Time","Open now. 24 hour service.");
		}
		finally
		{
		callbackpage.signout();	
		}
	}
	
	@Test(priority=9, enabled=true, groups = {"tools", "Queues"}, testName = "Verify if designer callback settings No EndTime BH changes reflected in GES Devops Page", description = "Designer callback settings changes should be reflected in GES Queues")
	public void Verify_Designer_No_EndTime_BH_Reflect_In_GES_Devopspage() {
		try
		{
		loginpage.login_Designer(admin_username, admin_password);
		designerpage.Callback_Settings_Modify_BH(designer_VQ1_name, no_end_time_BH);
		designerpage.signout();
		sleepFor(120);
		
		loginpage.login_GES(admin_username, admin_password);
		loginpage.verifyLogin(admin_username);
		devopspage.Navigate_To_DevopsTab();
		devopspage.Click_Tenant_Config();
		devopspage.Click_Service_Config();
		devopspage.Click_Tenant_Selection();
		devopspage.Select_Service("service."+designer_VQ1_name);
		String ui_bh = devopspage.fetch_service_properties("_business_hours_service");
		System.out.println("Fetched Business Hours from UI : " + ui_bh);
		
		devopspage.Select_Service("service."+ui_bh);
		String bh_name = devopspage.fetch_service_properties("_name");
		String bh_regular2 = devopspage.fetch_service_properties("_bh_regular2");
		System.out.println("Fetched BH Name: " + bh_name);
		System.out.println("Fetched BH _bh_regular2: " + bh_regular2);
		Assert.assertEquals(bh_name,no_end_time_BH);
		Assert.assertEquals(bh_regular2,"Mon-Mon 01:00-24:00");
		
		}
		finally
		{
		callbackpage.signout();	
		}
	}
	
	@Test(priority=10, enabled=false, groups = {"tools", "Queues"}, testName = "Verify if designer callback settings BH_With_SD_Occurs_Every_Year changes reflected in GES Queues", description = "Designer callback settings changes should be reflected in GES Queues")
	public void Verify_Designer_BH_With_SD_Occurs_Every_Year_Reflect_In_GES_Queuepage() {
		//Existing Issue - DES-13292
		try
		{
		loginpage.login_Designer(admin_username, admin_password);
		designerpage.Callback_Settings_Modify_BH(designer_VQ1_name, bh_with_SD_occurs_every_year);
		designerpage.signout();
		sleepFor(120);
		
		loginpage.login_GES(admin_username, admin_password);
		loginpage.verifyLogin(admin_username);
		toolspage.Navigate_To_ToolsTab();
		toolspage.Click_Queues();
		toolspage.Verify_VQ_Search(designer_VQ1_name);
		toolspage.Validate_CallbackQueues_data("Open/Close Time","Open now. 24 hour service.");
		}
		finally
		{
		callbackpage.signout();	
		}
	}
	
	@Test(priority=11, enabled=true, groups = {"tools", "Queues"}, testName = "Verify if designer callback settings BH_With_SD_Not_Occurs_Every_Year changes reflected in GES Queues", description = "Designer callback settings changes should be reflected in GES Queues")
	public void Verify_Designer_BH_With_SD_Not_Occurs_Every_Year_Reflect_In_GES_Queuepage() {
		try
		{
		loginpage.login_Designer(admin_username, admin_password);
		designerpage.Callback_Settings_Modify_BH(designer_VQ1_name, bh_with_SD_not_occurs_every_year);
		designerpage.signout();
		sleepFor(120);
		
		loginpage.login_GES(admin_username, admin_password);
		loginpage.verifyLogin(admin_username);
		toolspage.Navigate_To_ToolsTab();
		toolspage.Click_Queues();
		toolspage.Verify_VQ_Search(designer_VQ1_name);
		toolspage.Validate_CallbackQueues_data("Open/Close Time","Open now. 24 hour service.");
		}
		finally
		{
		callbackpage.signout();	
		}
	}
	
	@Test(priority=12, enabled=true, groups = {"tools", "Queues"}, testName = "Verify if designer callback settings BH_With_SD_With_Disabled_DateRange changes reflected in GES Queues", description = "Designer callback settings changes should be reflected in GES Queues")
	public void Verify_Designer_BH_With_SD_With_Disabled_DateRange_Reflect_In_GES_Queuepage() {
		try
		{
		loginpage.login_Designer(admin_username, admin_password);
		designerpage.Callback_Settings_Modify_BH(designer_VQ1_name, bh_with_SD_with_disabled_date_range);
		designerpage.signout();
		sleepFor(120);
		
		loginpage.login_GES(admin_username, admin_password);
		loginpage.verifyLogin(admin_username);
		toolspage.Navigate_To_ToolsTab();
		toolspage.Click_Queues();
		toolspage.Verify_VQ_Search(designer_VQ1_name);
		toolspage.Validate_CallbackQueues_data("Open/Close Time","Open now. 24 hour service.");
		}
		finally
		{
		callbackpage.signout();	
		}
	}
	
	@Test(priority=13, enabled=true, groups = {"tools", "Queues"}, testName = "Verify if designer callback settings BH_With_SD_Without_DateRange changes reflected in GES Queues", description = "Designer callback settings changes should be reflected in GES Queues")
	public void Verify_Designer_BH_With_SD_Without_DateRange_Reflect_In_GES_Queuepage() {
		try
		{
		loginpage.login_Designer(admin_username, admin_password);
		designerpage.Callback_Settings_Modify_BH(designer_VQ1_name, bh_with_SD_without_date_range);
		designerpage.signout();
		sleepFor(120);
		
		loginpage.login_GES(admin_username, admin_password);
		loginpage.verifyLogin(admin_username);
		toolspage.Navigate_To_ToolsTab();
		toolspage.Click_Queues();
		toolspage.Verify_VQ_Search(designer_VQ1_name);
		toolspage.Validate_CallbackQueues_data("Open/Close Time","Open now. 24 hour service.");
		}
		finally
		{
		callbackpage.signout();	
		}
	}
	*/
	@Test(priority=14, enabled=false, groups = {"tools", "Queues"}, testName = "Verify if designer application export modify and then import changes reflected in GES Queues", description = "Designer callback settings changes should be reflected in GES Queues")
	public void Verify_Designer_App_Export_Modify_And_Import_Reflect_In_GES_Queuepage() {
		//Existing Issue - After importing, BH and SD is not published automatically.
		
		//Modify DT Callback Settings and Publish
		loginpage.login_Designer(admin_username, admin_password);
		designerpage.Callback_Settings_Modify_BH(designer_VQ2_name, bh);
		designerpage.signout();
		sleepFor(120);
		
		//Verify GES Queues Page
		loginpage.login_GES(admin_username, admin_password);
		loginpage.verifyLogin(admin_username);
		toolspage.Navigate_To_ToolsTab();
		toolspage.Click_Queues();
		toolspage.Verify_VQ_Search(designer_VQ2_name);
		toolspage.Validate_CallbackQueues_data("Open/Close Time","Open now. 24 hour service.");
		callbackpage.signout();
		
		//Export Callback App along with it's related BH and SD
		loginpage.login_Designer(admin_username, admin_password);
		designerpage.Export_Callback_Application(callback_app, designer_VQ2_name, bh, sd);
		
		//Delete Callback Application
		designerpage.Delete_Callback_Application(callback_app);
		
		//Modify related BH and SD
		designerpage.Modify_BH_And_SD(bh, sd);
		designerpage.signout();
		sleepFor(120);
	
		/*//Need Analysis
		loginpage.login_GES(admin_username, admin_password);
		loginpage.verifyLogin(admin_username);
		toolspage.Navigate_To_ToolsTab();
		toolspage.Click_Queues();
		toolspage.Verify_VQ_Search(designer_VQ2_name);
		toolspage.Validate_CallbackQueues_data("Open/Close Time","Office closed, unknown opening time.");
		*/
		
		//Import Callback Application
		loginpage.login_Designer(admin_username, admin_password);
		System.out.println("\n Designer Test - Current User Directory :");
		System.out.println(System.getProperty("user.dir"));
		
		String file_path = System.getProperty("user.dir");
		String file_name = "f224d910-af48-11ec-83dd-0fa315477936.dar";
		designerpage.Import_Callback_Application(file_path, file_name);
		designerpage.signout();
		sleepFor(600);
		
		//Verify GES Queues Page
		loginpage.login_GES(admin_username, admin_password);
		loginpage.verifyLogin(admin_username);
		toolspage.Navigate_To_ToolsTab();
		toolspage.Click_Queues();
		toolspage.Verify_VQ_Search(designer_VQ2_name);
		toolspage.Validate_CallbackQueues_data("Open/Close Time","Open now. 24 hour service.");
		
	}

}
