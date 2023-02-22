package ges.pages;

import ges.util.BaseTest;
import ges.pages.CallbackPage;
import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;

public class RBACPage extends BaseTest{
	
	/****************************************************************************/
	
	CallbackPage callbackpage = new CallbackPage();
	
	/****************************************************************************/
	
	private By callback_open_reports_option = By.xpath("//div[@id='callback-open-reports']");
	private By tab_developer = By.id("developer-nav");
	private By tab_call_in = By.id("2");
	private By btn_createcallbck = By.xpath("//span[text()='Create Callback']");
	private By tab_devops = By.id("devops-nav");

	/****************************************************************************/
	
	public int devtab_count;
	public int callintab_count;
	public int createcbbtn_count;
	public int exportbtn_count;
	public int devopstab_count;
	
	/****************************************************************************/
	

	
/* ----------------------------------- RBAC checks ------------------------------------- */
	
	public boolean verifyAdminAccess() {
		sleepFor(2);

		Check_Element_Visibility();
		int callback_write_access = Verify_CallbackPage_Write_Access();
		
		if(devtab_count == 1 && callintab_count == 1 && createcbbtn_count == 1 && exportbtn_count ==1 && devopstab_count == 1 && callback_write_access == 1)
		{
			System.out.println("\n The access privileges are proper for "+testdata_rbac_property_file_reader("Admin_username"));
			return true;
		}
		else
		{
			System.out.println("\n The access privileges are not proper. Please enter the right credential to verify the appropriate privileges ");
		}
		return false;

	}
	
	
	public boolean verifyDevAccess() {
		sleepFor(2);
		
		Check_Element_Visibility();
		int callback_write_access = Verify_CallbackPage_Write_Access();
		
		if(devtab_count == 1 && callintab_count == 1 && createcbbtn_count == 1 && exportbtn_count ==1 && devopstab_count == 1 && callback_write_access == 1)
		{
			System.out.println("\n The access privileges are proper for "+testdata_rbac_property_file_reader("Developer_username"));
			return true;
		}
		else
		{
			System.out.println("\n The access privileges are not proper. Please enter the right credential to verify the appropriate privileges ");
		}
		return false;
	}
	
	public boolean verifySupervisorAccess() {
		sleepFor(2);
		
		Check_Element_Visibility();
		int callback_write_access = Verify_CallbackPage_Write_Access();
		
		if(devtab_count == 0 && callintab_count == 1 && createcbbtn_count == 1 && exportbtn_count ==1 && devopstab_count == 0 && callback_write_access == 1)
		{
			System.out.println("\n The access privileges are proper for "+testdata_rbac_property_file_reader("Supervisor_username"));
			return true;
		}
		else
		{
			System.out.println("\n The access privileges are not proper. Please enter the right credential to verify the appropriate privileges ");
		}
		return false;
	}
	
	public boolean verifyMonitorAccess() {
		sleepFor(2);
	
		Check_Element_Visibility();
		int callback_write_access = Verify_CallbackPage_Write_Access();
		
		if(devtab_count == 0 && callintab_count == 1 && createcbbtn_count == 0 && exportbtn_count == 0 && devopstab_count == 0 && callback_write_access == 0)
		{
			System.out.println("\n The access privileges are proper for "+testdata_rbac_property_file_reader("Monitor_username"));
			return true;
		}
		
		else
		{
			System.out.println("\n The access privileges are not proper. Please enter the right credential to verify the appropriate privileges ");
		}
		return false;
	}
	
	public boolean verifyReadOnlyAccess() {
		sleepFor(2);
	
		Check_Element_Visibility();
		int callback_write_access = Verify_CallbackPage_Write_Access();
		
		if(devtab_count == 1 && callintab_count == 1 && createcbbtn_count == 0 && exportbtn_count == 0 && devopstab_count == 1 && callback_write_access == 0)
		{
			System.out.println("\n The access privileges are proper for "+testdata_rbac_property_file_reader("ReadOnly_username"));
			return true;
		}
		
		else
		{
			System.out.println("\n The access privileges are not proper. Please enter the right credential to verify the appropriate privileges ");
		}
		return false;
	}
	
	public boolean verifyTestAccountAccess() {
		sleepFor(2);
	
		Check_Element_Visibility();
		int callback_write_access = Verify_CallbackPage_Write_Access();
		
		if(devtab_count == 1 && callintab_count == 1 && createcbbtn_count == 1 && exportbtn_count ==1 && devopstab_count == 1 && callback_write_access == 1)
		{
			System.out.println("\n The access privileges are proper for "+testdata_rbac_property_file_reader("TestAccount_username"));
			return true;
		}
		
		else
		{
			System.out.println("\n The access privileges are not proper. Please enter the right credential to verify the appropriate privileges ");
		}
		return false;
	}
	
	public ArrayList<Integer> Check_Element_Visibility() {
		List<Integer> element_visibility = new ArrayList<Integer>();
		
		devtab_count = countOfElements(tab_developer);
		callintab_count = countOfElements(tab_call_in);
		createcbbtn_count = countOfElements(btn_createcallbck);
		exportbtn_count = countOfElements(callback_open_reports_option);
		devopstab_count = countOfElements(tab_devops);
		
		element_visibility.add(devtab_count);
		element_visibility.add(callintab_count);
		element_visibility.add(createcbbtn_count);
		element_visibility.add(exportbtn_count);
		element_visibility.add(devopstab_count);
		
		/***********************************************************/
		
		if(devtab_count == 1){
			System.out.println("\n The Developer tab is visible");
		}
		else if(devtab_count == 0){
			System.out.println("\n The Developer tab is not visible");
		}
		
		/***********************************************************/
		
		if(createcbbtn_count == 1){
			System.out.println("\n The Create Callback button is visible");
		}
		else if(createcbbtn_count == 0)
		{
			System.out.println("\n The Create Callback button is not visible");
		}
		
		/***********************************************************/
		
		if(callintab_count == 1){
			System.out.println("\n The Call-in tab is visible");
		}
		else if(callintab_count == 0)
		{
			System.out.println("\n The Call-in tab is not visible");
		}
		
		/***********************************************************/
		
		if(exportbtn_count == 1){
			System.out.println("\n The Export Cancelled Reports Button is visible");
		}
		else if(exportbtn_count == 0)
		{
			System.out.println("\n The Export Cancelled Reports Button is not visible");
		}
		
		/***********************************************************/
		
		if(devopstab_count == 1){
			System.out.println("\n The Devops tab is visible");
		}
		else if(devopstab_count == 0){
			System.out.println("\n The Devops tab is not visible");
		}
		
		/***********************************************************/
		
		//System.out.println("\n The elements count are : "+element_visibility);
		
		return (ArrayList<Integer>) element_visibility;
		
	}
	
	public int Verify_CallbackPage_Write_Access() {
		try
		{
			if (createcbbtn_count == 1) {
				String ph_no = Create_new_phoneNumber();
				callbackpage.createImmediateCallback_and_verify(ph_no);
				sleepFor(2);
				callbackpage.displayCallbackDetails_toConsole(ph_no);
				callbackpage.cancel_Callback(ph_no);
				System.out.println("\n The Callback Page has write access");
				return 1;
			} else {
				System.out.println("\n The Callback Page has only read access ");
				return 0;
			}
		}
		finally
		{
			System.out.println("\n");
		}
	}
	
}


