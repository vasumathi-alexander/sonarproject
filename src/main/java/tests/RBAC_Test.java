package ges.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import junit.framework.Assert;

import ges.pages.*;
import ges.util.BaseTest;

public class RBAC_Test extends BaseTest{
	
	/****************************************************************************/
	
	LoginPage loginpage = new LoginPage();
	CallbackPage callbackpage = new CallbackPage();
	RBACPage rbac = new RBACPage();
	public String ges_ver;
	
	/****************************************************************************/
	
	private String admin_username = testdata_rbac_property_file_reader("Admin_username");
	private String admin_password = testdata_rbac_property_file_reader("Admin_password");
	private String developer_username = testdata_rbac_property_file_reader("Developer_username");
	private String developer_password = testdata_rbac_property_file_reader("Developer_password");
	private String supervisor_username = testdata_rbac_property_file_reader("Supervisor_username");
	private String supervisor_password = testdata_rbac_property_file_reader("Supervisor_password");
	private String monitor_username = testdata_rbac_property_file_reader("Monitor_username");
	private String monitor_password = testdata_rbac_property_file_reader("Monitor_password");
	private String readonly_username = testdata_rbac_property_file_reader("ReadOnly_username");
	private String readonly_password = testdata_rbac_property_file_reader("ReadOnly_password");
	private String testaccount_username = testdata_rbac_property_file_reader("TestAccount_username");
	private String testaccount_password = testdata_rbac_property_file_reader("TestAccount_password");
	
	
	/****************************************************************************/
	@BeforeClass
	public void beforeClass() {
	System.out.println(
			"***************************************************************************************** \n\n\n");
	System.out.println("				Running Functional UI testcases - RBAC Tests");
	System.out.println(
			"\n\n\n*****************************************************************************************");
	}
		
	@Test(priority=1, enabled=true, testName = "Login as Administrator", description = "Login to the GES external UI as an administrator and verify if all the privileges are enabled")
		public void Login_as_Administrator()		
		{
			try
		{
			loginpage.login_as(admin_username, admin_password);
			loginpage.verifyLogin(admin_username);
			ges_ver = loginpage.getGESVersion();
			System.out.println("\n The GES version tested is : "+ges_ver);
			boolean result = rbac.verifyAdminAccess();
			Assert.assertEquals(true, result);
			}
		finally
		{
		callbackpage.signout();	
		}
		}
	
	@Test(priority=2, enabled=true, testName = "Login as Developer", description = "Login to the GES external UI as a Developer and verify if the corresponding privileges are enabled")
	public void Login_as_Developer()		
		{
			try
		{
			loginpage.login_as(developer_username, developer_password);
			loginpage.verifyLogin(developer_username);
			boolean result = rbac.verifyDevAccess();
			Assert.assertEquals(true, result);
			}
		finally
		{
		callbackpage.signout();	
		}
		}
	
	@Test(priority=3, enabled=true,testName = "Login as Supervisor", description = "Login to the GES external UI as a Supervisor and verify if the corresponding privileges are enabled")
	public void Login_as_Supervisor()		
		{
			try
		{
			loginpage.login_as(supervisor_username,supervisor_password);
			loginpage.verifyLogin(supervisor_username);
			boolean result = rbac.verifySupervisorAccess();
			Assert.assertEquals(true, result);
			}
		finally
		{
		callbackpage.signout();	
		}
		}
	
	@Test(priority=4, enabled=true,testName = "Login as Monitor", description = "Login to the GES external UI as a Monitor and verify if the corresponding privileges are enabled")
	public void Login_as_Monitor()		
		{
			try
		{
			loginpage.login_as(monitor_username, monitor_password);
			loginpage.verifyLogin(monitor_username);
			boolean result = rbac.verifyMonitorAccess();
			Assert.assertEquals(true, result);
			}
		finally
		{
		callbackpage.signout();	
		}
		}
	
	
	@Test(priority=5, enabled=true,testName = "Login as ReadOnly", description = "Login to the GES external UI as a ReadOnly and verify if the corresponding privileges are enabled")
	public void Login_as_ReadOnly()		
		{
			try
		{
			loginpage.login_as(readonly_username, readonly_password);
			loginpage.verifyLogin(readonly_username);
			boolean result = rbac.verifyReadOnlyAccess();
			Assert.assertEquals(true, result);
			}
		finally
		{
		callbackpage.signout();	
		}
		}
	
	@Test(priority=6, enabled=true,testName = "Login as TestAccount", description = "Login to the GES external UI as a TestAccount and verify if the corresponding privileges are enabled")
	public void Login_as_TestAccount()		
		{
			try
		{
			loginpage.login_as(testaccount_username, testaccount_password);
			loginpage.verifyLogin(testaccount_username);
			boolean result = rbac.verifyTestAccountAccess();
			Assert.assertEquals(true, result);
			}
		finally
		{
		callbackpage.signout();	
		}
		}
		
}
