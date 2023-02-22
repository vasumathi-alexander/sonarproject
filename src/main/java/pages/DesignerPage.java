package ges.pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import ges.util.BaseTest;
import ges.util.DriverManager;

public class DesignerPage extends BaseTest{
	
	/**************************************************************************/
	/************************* Navigation Elements**************************************/
	
	private By business_controls_tab = By.xpath("//span[contains(text(),'Business Controls')]");
	private By emergency_flags = By.xpath("//a[contains(text(),'Emergency Flags')]");
	private By business_hours = By.xpath("//a[contains(text(),'Business Hours')]");
	private By special_days = By.xpath("//a[contains(text(),'Special Days')]");
	private By data_tables = By.xpath("//a[contains(text(),'Data Tables')]");
	private By callback_settings = By.xpath("//a[contains(text(),'CALLBACK_SETTINGS')]");
	private By callback_settings_Scroll_bar = By.xpath("//div[3]/div[2][@class='ui-grid-viewport ng-isolate-scope']");
	private By callback_settings_filter_vq = By.xpath("//div[1]/div[3]/div/div/input");
	private By callback_settings_filtered_vq = By.xpath("//div[3]/div[2]/div/div/div/div[1]/div");
	private By designer_body_html = By.cssSelector("body");
	private By callback_settings_column_headers = By.xpath("//div[2]/div[1]/span[1]");
	private By callback_settings_BH = By.xpath("//div[contains(text(),'GES_Auto_BH_')]");
	private By search_BH = By.xpath("//div[2]/div/div/input");
	private By select_BH = By.xpath("//div[3]/ul/li/a");
	private By modify_BH_day1 = By.xpath("//tr[1]/td[5]/label/span");
	private By modify_BH_day2 = By.xpath("//tr[2]/td[5]/label/span");
	private By modify_BH_day3 = By.xpath("//tr[3]/td[5]/label/span");
	private By modify_BH_day4 = By.xpath("//tr[4]/td[5]/label/span");
	private By modify_BH_day5 = By.xpath("//tr[5]/td[5]/label/span");
	private By modify_BH_day6 = By.xpath("//tr[6]/td[5]/label/span");
	private By modify_BH_day7 = By.xpath("//tr[7]/td[5]/label/span");
	private By select_button_BH = By.xpath("//button[contains(text(),'Select')]");
	private By save_button_Callback_Settings = By.xpath("//span[contains(text(),'Save Table')]");
	private By disabled_publish_button_Callback_Settings = By.xpath("//span[contains(text(),'Publish Table')]/ancestor::button");
	private By publish_button_Callback_Settings = By.xpath("//span[contains(text(),'Publish Table')]");
	private By publish_confirm_button_Callback_Settings = By.xpath("//span[contains(text(),'Confirm Publish')]");
	private By designer_application_tab = By.xpath("//a[contains(text(),'Applications')]");
	private By search_application = By.xpath("//input");
	private By application_tag = By.xpath("//tr/td[2]");
	private By export_application = By.xpath("//span[contains(text(),'Export Application')]");
	private By select_a_resource_type = By.xpath("//div[1]/select");
	private By select_bh = By.xpath("//option[contains(text(),'Business Hours')]");
	private By select_sd = By.xpath("//option[contains(text(),'Special Days')]");
	private By search_box_export_application = By.xpath("//form/div[2]//div/input");
	private By save_button_export_application = By.xpath("//button[contains(text(),'Save')]");
	private By export_button_export_application = By.xpath("//button/span[contains(text(),'Export')]");
	private By done_button = By.xpath("//button[contains(text(),'Done')]");
	private By more_options = By.xpath("//span[contains(text(),'More Options')]");
	private By delete_application = By.xpath("//span[contains(text(),'Delete Application')]");
	private By confirm_delete_application = By.xpath("//button[contains(text(),'Yes')]");
	private By import_application = By.xpath("//span[contains(text(),'Import Application')]");
	private By choose_file = By.xpath("//td[text()='Choose file']//following::td[1]//button");
	private By upload_button = By.xpath("//td[text()='Upload']//following::td[1]//button");
	private By confirm_button = By.xpath("//td[text()='Confirm']//following::td[1]//button");
	private By search_box = By.xpath("//div/div/div/input");
	
	/**************************************************************************/
	private By btn_designer_userid = By.xpath("//nav/div/ul[2]/li[2]/a/span");
	private By btn_designer_logout = By.xpath("//*[text()='Logout']");
	
	
	/************************************************  Start of Designer - Navigate Functions  ***************************************************/
	
	public void Navigate_To_Applications() {
		System.out.println("\n Clicking the Application Tab");
		sleepFor(5);
		getElement(designer_application_tab, 15).click();
		sleepFor(5);
	}
	
	public void Navigate_To_BusinessControls() {
		System.out.println("\n Clicking the Business Controls Tab");
		sleepFor(5);
		getElement(business_controls_tab, 15).click();
		sleepFor(5);
	}

	public void Click_Emergency_Flags() {
		sleepFor(2);
		System.out.println("\n Selecting - Emergency Flags");
		getElement(emergency_flags, 15).click();
		sleepFor(5);
	}
	
	public void Click_Business_Hours() {
		sleepFor(2);
		System.out.println("\n Selecting - Business Hours");
		getElement(business_hours, 15).click();
		sleepFor(5);
	}
	
	public void Click_Special_Days() {
		sleepFor(2);
		System.out.println("\n Selecting - Special Days");
		getElement(special_days, 15).click();
		sleepFor(5);
	}
	
	public void Click_Data_Tables() {
		sleepFor(2);
		System.out.println("\n Selecting - Data Tables");
		getElement(data_tables, 15).click();
		sleepFor(5);
	}
	
	public void Click_Callback_Settings() {
		sleepFor(10);
		System.out.println("\n Selecting - Callback Settings");
		getElement(callback_settings, 15).click();
		sleepFor(10);
		System.out.println("\n Clicked 'Callback Settings' under Business Controls Tab");
	}

	/************************************************  End of Tools - Navigate Functions  ***************************************************/
	
	
	public void Callback_Settings_Filter_VQ(String vq_name) {
		System.out.println("\n Entering the VQ name for filtering");
		getElement(callback_settings_filter_vq, 15).click();
		getElement(callback_settings_filter_vq, 15).sendKeys(vq_name);
		System.out.println("\n Filtered using VQ name: "+getElement(callback_settings_filtered_vq, 15).getText());
	}
	
	
	public Boolean ScrollTo_Column_In_Datatable(String column_name) {
		System.out.println("\n Scroll to 'Business Hours' Column");
		Boolean found_BH = false;
		getElement(callback_settings_Scroll_bar, 15).click();
		for (int i = 0; i < 10; i++) {
			getElement(designer_body_html, 15).sendKeys(Keys.ARROW_RIGHT,Keys.ARROW_RIGHT,Keys.ARROW_RIGHT,Keys.ARROW_RIGHT,Keys.ARROW_RIGHT);
			//JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
			//js.executeScript("argument[0].scrollIntoView()", callback_settings_BH);
			sleepFor(3);
			List<WebElement> column_headers = DriverManager.getDriver().findElements(callback_settings_column_headers);
			Integer column_size = column_headers.size();
			for (int j = 0; j < column_size; j++) {
				if (!column_headers.get(j).getText().equalsIgnoreCase(column_name)) {
					continue;
				} else {
					found_BH = true;
					System.out.println("\nFound column: "+column_headers.get(j).getText());
					moveToElement(column_headers.get(j));
					break;
				}
			}
			if (found_BH) {
				break;
			}
		};
		return found_BH;
	}
	
	public void Callback_Settings_Modify_BH(String business_hours) {
		System.out.println("\n Going to Modify Business Hours");
		ScrollTo_Column_In_Datatable("Business Hours");
		getElement(callback_settings_BH, 15).click();
		System.out.println("\n Updating the Business Hours");
		getElement(search_BH, 15).click();
		getElement(search_BH, 15).sendKeys(business_hours);
		getElement(select_BH, 15).click();
		getElement(select_button_BH, 15).click();
		System.out.println("\n Modified BH: "+business_hours);
	}
	
	public void Callback_Settings_Save() {
		System.out.println("\n Saving Callback Settings");
		getElement(save_button_Callback_Settings, 15).click();
		System.out.println("\n Callback Settings Saved");
	}
	
	public void Callback_Settings_Publish() {
		System.out.println("\n Publishing Callback Settings");
		Wait<WebDriver> wait = new WebDriverWait(DriverManager.getDriver(), 120);
		try {
			for (int i = 0; i < 60; i++) {
				sleepFor(2);
				String a = getElement(disabled_publish_button_Callback_Settings, 15).getAttribute("disabled");
				if (a == null) {break;}
			};
			getElement(publish_button_Callback_Settings, 15).click();
			wait.until(ExpectedConditions.presenceOfElementLocated(publish_confirm_button_Callback_Settings));
			getElement(publish_confirm_button_Callback_Settings, 15).click();
			sleepFor(10);
			wait.until(ExpectedConditions.presenceOfElementLocated(save_button_Callback_Settings));
			System.out.println("\n Callback Settings Published");
		} catch (Exception e) {
			System.out.println("\n Callback Settings failed to Publish");
		}
	}
	
	public void Callback_Settings_Modify_BH(String vq_name, String business_hours) {
		Navigate_To_BusinessControls();
		Click_Data_Tables();
		Click_Callback_Settings();
		Callback_Settings_Filter_VQ(vq_name);
		Callback_Settings_Modify_BH(business_hours);
		Callback_Settings_Save();
		Callback_Settings_Publish();
	}
	
	public void Export_Callback_Application(String app, String vq, String bh, String sd) {
		System.out.println("\n Going to export callback application : "+app);
		Navigate_To_Applications();
		getElement(search_application, 15).click();
		getElement(search_application, 15).clear();
		getElement(search_application, 15).sendKeys(app);
		getElement(application_tag, 15).click();
		sleepFor(5);
		
		getElement(export_application, 15).click();
		sleepFor(2);
		
		System.out.println("\n Selecting Business Hours for export : "+bh);
		getElement(select_a_resource_type, 15).click();
		getElement(select_bh, 15).click();
		getElement(search_box_export_application, 15).clear();
		getElement(search_box_export_application, 15).sendKeys(bh);
		By bh_check_box = By.xpath("//td/span[text()='"+bh+"']//following::td[4]/flow-checkbox");
		By bh_check_box_attr = By.xpath("//td/span[text()='"+bh+"']//following::td[4]/flow-checkbox/label/input");
		String bh_check_box_attr_value = getElement(bh_check_box_attr, 15).getAttribute("class");
		if (bh_check_box_attr_value.contains("ng-empty")) {
			getElement(bh_check_box, 15).click();
		}
		
		System.out.println("\n Selecting Special Days for export : "+sd);
		getElement(select_a_resource_type, 15).click();
		getElement(select_sd, 15).click();
		getElement(search_box_export_application, 15).clear();
		getElement(search_box_export_application, 15).sendKeys(sd);
		By sd_check_box = By.xpath("//td/span[text()='"+sd+"']//following::td[4]/flow-checkbox");
		By sd_check_box_attr = By.xpath("//td/span[text()='"+sd+"']//following::td[4]/flow-checkbox/label/input");
		String sd_check_box_attr_value = getElement(sd_check_box_attr, 15).getAttribute("class");
		if (sd_check_box_attr_value.contains("ng-empty")) {
			getElement(sd_check_box, 15).click();
		}
		
		System.out.println("\n Started Exporting . . .");
		if (getElement(save_button_export_application, 15).isDisplayed()) {
			getElement(save_button_export_application, 15).click();
		}
		getElement(export_button_export_application, 15).click();
		sleepFor(10);
		getElement(done_button, 15).click();
		System.out.println("\n Export Application - Done");
	}
	
	public void Delete_Callback_Application(String app) {
		System.out.println("\n Going to delete callback application : "+app);
		Navigate_To_Applications();
		getElement(search_application, 15).click();
		getElement(search_application, 15).clear();
		getElement(search_application, 15).sendKeys(app);
		getElement(application_tag, 15).click();
		sleepFor(10);
		
		getElement(more_options, 15).click();
		sleepFor(2);
		getElement(delete_application, 15).click();
		sleepFor(2);
		getElement(confirm_delete_application, 15).click();
		System.out.println("\n Application Deleted Successfully : "+app);
		sleepFor(10);
	}
	
	public void Modify_BH_And_SD(String bh, String sd) {
		Navigate_To_BusinessControls();
		Click_Business_Hours();
		getElement(search_box, 15).click();
		getElement(search_box, 15).sendKeys(bh);
		By select_the_bh = By.xpath("//td/p[text()='"+bh+"']//following::td[1]/div");
		getElement(select_the_bh, 15).click();
		getElement(modify_BH_day1, 15).click();
		getElement(modify_BH_day2, 15).click();
		getElement(modify_BH_day3, 15).click();
		getElement(modify_BH_day4, 15).click();
		getElement(modify_BH_day5, 15).click();
		getElement(modify_BH_day6, 15).click();
		getElement(modify_BH_day7, 15).click();
		System.out.println("\n BH Modified Successfully : "+bh);
		
		Navigate_To_BusinessControls();
		Click_Special_Days();
		getElement(search_box, 15).click();
		getElement(search_box, 15).sendKeys(sd);
		By sd_delete_button = By.xpath("//td/p[text()='"+sd+"']//following::td[3]//button[2]");
		getElement(sd_delete_button, 15).click();
		getElement(confirm_delete_application, 15).click();
		System.out.println("\n SD Deleted Successfully : "+bh);
	}
	
	public void Import_Callback_Application(String file_path, String file_name) {
		String file_full_path = file_path + File.separatorChar + file_name;
		File file = new File(file_full_path);
		if (file.exists()) {
			System.out.println("\n Exported File Exists : "+file_full_path);
			//file.delete();
		} else {
			System.out.println("\n Exported File Does Not Exists : "+file_full_path);
		}
		
		Navigate_To_Applications();
		getElement(import_application, 30).click();
		System.out.println("\n Import Application - Choose File");
		getElement(choose_file, 15).click();
		sleepFor(5);
		
		try {
			StringSelection exported_file = new StringSelection(file_full_path);
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(exported_file, null);
			
			Robot robot = new Robot();
		    robot.keyPress(java.awt.event.KeyEvent.VK_CONTROL);
		    robot.keyPress(java.awt.event.KeyEvent.VK_V);
		    robot.keyRelease(java.awt.event.KeyEvent.VK_V);
		    robot.keyRelease(java.awt.event.KeyEvent.VK_CONTROL);
		    sleepFor(2);
		    robot.keyPress(java.awt.event.KeyEvent.VK_ENTER);
		    robot.keyRelease(java.awt.event.KeyEvent.VK_ENTER);
		} catch (AWTException e) {
			e.printStackTrace();
		}
		
		System.out.println("\n Import Application - Upload File");
		getElement(upload_button, 15).click();
		sleepFor(5);
		System.out.println("\n Import Application - Confirm Upload File");
		getElement(confirm_button, 15).click();
		sleepFor(20);
		System.out.println("\n Import Application - Done");
		getElement(done_button, 15).click();
		sleepFor(3);
		file.delete();
		System.out.println("\n Test Passed");
	}
	
	public void signout() {
		sleepFor(4);
		getElement(btn_designer_userid, 15).click();
		sleepFor(4);
		getElement(btn_designer_logout, 15).click();
		System.out.println("\n Designer Signing out. \n");
		sleepFor(2);
	}
	
}
