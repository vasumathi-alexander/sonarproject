package ges.pages;

import org.openqa.selenium.By;
import ges.util.BaseTest;

public class LoginPage extends BaseTest {

	public String GESVersion;
	
	private By txt_user = By.id("username");
	private By btn_continue = By.id("continue");
	private By txt_password = By.id("password");
	private By btn_login = By.id("login");
	private By lnk_callback = By.id("callback-nav");
	private By nav_username = By.id("account-nav-button"); 
	private By lbl_errmsg = By.xpath("//*[@id='loginForm']/div/div[3]/span[2]");
	private By lbl_formvalidation_msg = By.xpath("//span[@class='form-control-feedback']/span[@class='form-control-feedback-text']");
	private By lbl_Passvalidation_msg = By.xpath("//*[@class='input-container password-container form-group has-error has-feedback']/span[@class='form-control-feedback']/span[@class='form-control-feedback-text']");
	private By txt_gesversion = By.xpath("//div[@class='version-container']");
	
	public void enter_username(String user) {
		sleepFor(2);
		getElement(txt_user, 30).sendKeys(user);
	}
	
	public void enter_password(String password) {
		getElement(txt_password, 30).sendKeys(password);
	}
	
	public void clickNext() {
		getElement(btn_continue, 30).click();
		sleepFor(5);
	}
	
	public void login_as(String user,String password) {
		sleepFor(4);
		enter_username(user);
		getElement(btn_continue, 30).click();
		sleepFor(4);
		enter_password(password);
		getElement(btn_login, 30).click();
		sleepFor(2);
		System.out.println("\n Logging to the GES UI as :"+user);
	}
	
	public void login_Designer(String user,String password) {
		getDesignerURL();
		System.out.println("\n Designer UI page loaded for :"+user);
		sleepFor(10);
		enter_username(user);
		getElement(btn_continue, 30).click();
		sleepFor(4);
		enter_password(password);
		getElement(btn_login, 30).click();
		sleepFor(10);
		System.out.println("\n Logging to the Designer UI as : "+user);
	}
	
	public void login_GES(String user,String password) {
		getGESURL();
		sleepFor(4);
		System.out.println("\n GES UI page loaded for : "+user);
		login_as(user,password);
	}
	
	public boolean verifyLogin(String user) {
		sleepFor(4);
		//getGESVersion();
		getElement(lnk_callback, 30);
		if(getElement(lnk_callback, 45).isDisplayed() && getElement(nav_username, 45).getText().trim().contains(user.trim()))
		{
			System.out.println("\n Login successful");
			return true;
		}
		return false;
	}
	
	public boolean verifyLoginError(String Err) {
		sleepFor(3);
		System.out.println("\n The message is displayed as : "+getElement(lbl_errmsg, 15).getText());
		return getElement(lbl_errmsg, 15).getText().trim().contains(Err);
	}
	
	public boolean verifyFormvalidationMsg(String Err) {
		sleepFor(5);
		return getElement(lbl_formvalidation_msg, 10).getText().trim().contains(Err);
	}
	
	public boolean verifyFormMsgPassword(String Err) {
		sleepFor(3);
		return getElement(lbl_Passvalidation_msg, 10).getText().trim().contains(Err);
	}
	
	public String getGESVersion(){
		sleepFor(2);
		GESVersion = getElement(txt_gesversion, 20).getText();
		return GESVersion;
		
	}
}
