package ges.pages;

import static org.testng.Assert.assertEquals;

import java.sql.DriverManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ges.util.BaseTest;
import junit.framework.Assert;

public class CallbackPage extends BaseTest {
	
	/**************************************************************************/
	
	//private String ph_num = testdata_property_file_reader("CustomerNumber");
	
	/**************************************************************************/
	
	private By btn_createcallbck = By.xpath("//span[text()='Create Callback']");
	private By time_updated_column = By.xpath("//div[text()='Time Updated']");
	private By btn_createcallbck_clickable = By.xpath("//span[text()='Create Callback']/../../..");
	private By drpdwn_CallbckType = By.id("callback-type-select");
	private By txt_SrviceType = By.id("suggested-value-text-area");
	private By txt_custNumber = By.id("callback-number-input");
	private By btn_final_creatCallbck = By.id("submit-callback"); 
	private By btn_final_cancel = By.xpath("//button[contains(text(),'Cancel')]");
	private By btn_advanceOptions = By.xpath("//*[@id='callback-open-advanced']");
	private By btn_advanceOptions_parent = By.xpath("//*[@id='callback-open-advanced']/..");
	private By txt_serviceInput = By.className("mainInput");
	private By chk_services = By.xpath("//input[@title='Select/Unselect All']");
	private By btn_save_adv = By.id("save-advanced");
	private By btn_userid = By.xpath("(//button[@id='account-nav-button'])[1]");
	private By btn_logout = By.xpath("//*[text()='Log out']");
	private By btn_cancelCB = By.xpath("//div[@id='callback-open-cancel-callbacks']");
	private By btn_confirmCancel_CB = By.id("confirm-cancel-callbacks");
	private By btn_recreate = By.id("recreate-button");
	private By btn_callback_next = By.xpath("//button[@title='Next']");
	private By btn_callback_last = By.xpath("//button[@title='Last']");
	private By btn_callback_previous = By.xpath("//button[@title='Previous']");
	private By btn_callback_first = By.xpath("//button[@title='First']");
	private By page_number = By.xpath("//input[@data-role='input']");	
	private By search_box_callback = By.xpath("//*[@id='callback-view']/div[1]/div[1]/div[2]/div[2]/div/div[1]/div/div/div/input[2]");
	
	private By btn_add_custom = By.xpath("//*[@class='clickable modal-link add-field']");
	private By txt_name_custom = By.xpath("//*[@id='name-column']/input");
	private By txt_start_offset = By.xpath("//*[@id='start-column']/input");
	private By txt_end_offset = By.xpath("//*[@id='end-column']/input");
	private By btn_delete_custom = By.xpath("//*[@id='end-column']/span/button");
	private By go_to_last_page = By.xpath("//*[@id='footer']/div/div[2]/section/button[4]");
	
	
	/****************************************************************************/

	

	//private By select_callback_forCancel = By.xpath("(//div[@class='cell-content'][text()='"+ ph_num +"'])[last()]//ancestor::div[1]/preceding-sibling::div[4]/div/label");
	//private By btn_recreate_callback = By.xpath("(//div[@class='cell-content'][text()='"+ ph_num +"'])[last()]//ancestor::div[1]/following-sibling::div[3]//button");
	/****************************************************************************/
	
	private By chk_colTimeupdated = By.xpath("//input[@label='Include Time Updated Column']");
	private By chk_colServicename = By.xpath("//input[@label='Include Service Name Column']");
	private By chk_colServiceId = By.xpath("//input[@label='Include Service ID Column']");
	
	private By chk_time24hr = By.xpath("//input[@label='Show Time in 24 Hour']");
	private By chk_timeUTC = By.xpath("//input[@label='Show Times in UTC']"); 
	
	private By chk_stateScheduled = By.xpath("//input[@label='SCHEDULED']");
	private By chk_stateQueued = By.xpath("//input[@label='QUEUED']");
	private By chk_stateRouting = By.xpath("//input[@label='ROUTING']");
	private By chk_stateProcessing = By.xpath("//input[@label='PROCESSING']");
	private By chk_stateCompleted = By.xpath("//input[@label='COMPLETED']");
	
	private By txt_filterbox = By.xpath("//*[@class='filterSearch']//div/input[@class='mainInput']");
	private By drpdwn_search = By.xpath("//*[contains(@class,'searchBar')]/select");
	
	private By btn_gobottom = By.xpath("//div[@class='numOfCallbacksBanner']/button");
	private By btn_gotop = By.xpath("//div[@class='footerLeftGroup']");
	
	private By cell_serviceid = By.xpath("//div[@id='callbackTableBody']/div[contains(@class,'grid-row')][1]/div[2]/div");
	private By btn_hidemetrics = By.xpath("//div[@id='callbackTableBody']/div[contains(@class,'grid-row')][1]/div[2]/div/button[1]");
	private By btn_refresh = By.xpath("//div[@id='callbackTableBody']/div[contains(@class,'grid-row')][1]/div[2]/div/button[2]");
	private By btn_toggle_details = By.xpath("//div[@id='callbackTableBody']/div[contains(@class,'grid-row')][1]/div[2]/div/button[3]");
	
	/***********************************************************************************************/
	/***************************Important Functions*************************************************/
	
	
	
	public void createImmediateCallback_and_verify(String ph_num) {
		for(int i=1 ; i<=10 ; i++) {
			if(getElement(btn_createcallbck_clickable, 15).getAttribute("class").contains("disabled-clickable")) {
				sleepFor(1);
			}
			else
				break;
		}
		By chk_callback_created_by_phnum = By.xpath("(//div[@class='cell-content'][text()='"+ph_num+"'])[last()]");
		getElement(btn_createcallbck, 30).click();
		sleepFor(1);
		selectByVisibleText(drpdwn_CallbckType, testdata_property_file_reader("CallbckType_Imm"), 2);
		getElement(txt_SrviceType, 10).sendKeys(testdata_property_file_reader("Service"));
		getElement(txt_custNumber, 10).sendKeys(ph_num);
		getElement(btn_final_creatCallbck, 10).click();
		sleepFor(10);	
		getElement(btn_createcallbck, 30);
		getElement(time_updated_column, 30).click();
		Clear_all_notifications();
		sleepFor(3);
		
		/** The below method selects the filter to 'today'. If it had been set to some other filter, the verification of callback creation fails! **/ 
		filterUIDate("Today");
		
		if (getElement(go_to_last_page, 10).isEnabled()) {
			getElement(go_to_last_page, 10).click();
		};
		
		/** The below method checks for the latest callback that has been created for the same phone number: **/
		getElement(chk_callback_created_by_phnum, 20);
		
		sleepFor(2);
	}
	
	public void Clear_all_notifications() {
		sleepFor(2);
		int a = countOfElements(By.xpath("//button[@class='btn-close']"));
		for (int i=1;i<=a;i++)
		{
			getElement(By.xpath("(//button[@class='btn-close'])[1]"), 15).click();
			sleepFor(2);
		}
	}
	
	public void displayCallbackDetails_toConsole(String ph_num)
	{
		sleepFor(3);
		for(int i=1 ; i<=10 ; i++) {
			if(getElement(btn_createcallbck_clickable, 15).getAttribute("class").contains("disabled-clickable")) {
				sleepFor(1);
			}
			else
				break;
		}
		 By chk_callback_created_by_phnum = By.xpath("(//div[@class='cell-content'][text()='"+ ph_num +"'])[last()]");
		 By chk_callbacks_count = By.xpath("//div[@class='cell-content'][text()='"+ ph_num +"']");
		 By get_callback_service_id = By.xpath("(//div[@class='cell-content'][text()='"+ ph_num +"'])[last()]//ancestor::div[1]/preceding-sibling::div[3]/div");
		 By get_callback_state = By.xpath("(//div[@class='cell-content'][text()='"+ ph_num +"'])[last()]//ancestor::div[1]/preceding-sibling::div[2]/div");
		 By get_callback_time = By.xpath("(//div[@class='cell-content'][text()='"+ ph_num +"'])[last()]//ancestor::div[1]/preceding-sibling::div[1]/div");
		 By get_callback_service_name = By.xpath("(//div[@class='cell-content'][text()='"+ ph_num +"'])[last()]//ancestor::div[1]/following-sibling::div[1]/div");
		System.out.println("\n ************************************************************************");
		System.out.println("\n The callback Service Id is : " +getElement(get_callback_service_id,15).getText());
		System.out.println("\n The callback State is : " +getElement(get_callback_state, 15).getText());
		System.out.println("\n The callback Time is : " +getElement(get_callback_time, 15).getText());
		System.out.println("\n The callback Service Name is : " +getElement(get_callback_service_name, 15).getText()+ "\n");
		System.out.println("\n ************************************************************************");
	}
	
	public void createImmediateCallback_and_verify_throttle_limit_reached(String ph_num) {
		getElement(btn_createcallbck, 30).click();
		sleepFor(1);
		selectByVisibleText(drpdwn_CallbckType, testdata_property_file_reader("CallbckType_Imm"), 2);
		getElement(txt_SrviceType, 10).sendKeys(testdata_property_file_reader("Service"));
		getElement(txt_custNumber, 10).sendKeys(ph_num);
		getElement(btn_final_creatCallbck, 10).click();
		sleepFor(15);
		String msg = getElement(By.xpath("//div[@class='error-text']/h4"), 30).getText();
		String expected_response = "There was an Error Creating the Callback";
		System.out.println("\n(Throttle Limit) The callback error for " +ph_num+ " is : \n" +msg);
		Assert.assertEquals(expected_response, msg);
		getElement(btn_final_cancel, 10).click();
		sleepFor(2);	
	}
	
	public String Get_CallbackID (String ph_num)
	{
		By get_callback_service_id = By.xpath("(//div[@class='cell-content'][text()='"+ ph_num +"'])[last()]//ancestor::div[1]/preceding-sibling::div[3]/div");
		String a = getElement(get_callback_service_id, 15).getText();
		return a;
	}
	
	public void createScheduledCallback_and_verify(String ph_num,int add_days,int add_slot,String filter_select) {
		By chk_callback_created_by_phnum = By.xpath("(//div[@class='cell-content'][text()='"+ ph_num +"'])[last()]");
		By select_callback_forCancel = By.xpath("(//div[@class='cell-content'][text()='"+ ph_num +"'])[last()]//ancestor::div[1]/preceding-sibling::div[4]/div/label");
		getElement(btn_createcallbck, 30).click();
		sleepFor(1);
		selectByVisibleText(drpdwn_CallbckType, testdata_property_file_reader("CallbckType_Sch"), 2);
		getElement(txt_SrviceType, 10).sendKeys(testdata_property_file_reader("Service"));
		getElement(txt_custNumber, 10).sendKeys(ph_num);
		
		getElement(By.xpath("//button[contains(@class, 'calendar-button')]"), 20).click();
		
		Date date = new Date();
		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		int day = localDate.getDayOfMonth();
		//day = day + add_days;
		//if(day>30)
		int month = localDate.getMonthValue();
		LocalDate next_days = localDate.plusDays(add_days);
		int new_day = next_days.getDayOfMonth();
		int new_month = next_days.getMonthValue();

		if(new_month!=month)
		{
			//day = day - 30;
			String todays_date = Integer.toString(new_day);
			getElement(By.xpath("//*[@id='siamese-twin']/div[1]/div/button[2]"),20).click();
			getElement(By.xpath("//div[@class='calendars']//button[text()='"+todays_date+"'][not(contains(@class,'isExtra'))]"), 15).click();
		}
		else
		{
			String todays_date = Integer.toString(new_day);		
			getElement(By.xpath("//div[@class='calendars']//button[text()='"+todays_date+"'][not(contains(@class,'isExtra'))]"), 15).click();
		}
		
		System.out.println("\n Selecting the calendar date as date which is : "+new_day);
		
		// Select desired time
		getElement(By.id("callback-time-select"), 20).click();
		getElement(By.xpath("//*[@id='callback-time-select']/option["+add_slot+"]"), 20).click();
		System.out.println("\n Selecting the desired time :" +getElement(By.xpath("//*[@id='callback-time-select']/option["+add_slot+"]"), 20).getText());
		getElement(btn_final_creatCallbck, 10).click();
		sleepFor(2);	
		getElement(btn_createcallbck, 30);
		Clear_all_notifications();
		sleepFor(2);
		
		/** The below method selects the filter to 'today'. If it had been set to some other filter, the verification of callback creation fails! **/ 
		filterUIDate(filter_select);
		
		if (getElement(go_to_last_page, 10).isEnabled()) {
			getElement(go_to_last_page, 10).click();
		};
		
		/** The below method checks for the latest callback that has been created for the same phone number: **/
		getElement(chk_callback_created_by_phnum, 20);
		
	}
	
	public void cancel_Callback(String ph_num) {
		 By chk_callback_created_by_phnum = By.xpath("(//div[@class='cell-content'][text()='"+ ph_num +"'])[last()]");
		 By chk_callbacks_count = By.xpath("//div[@class='cell-content'][text()='"+ ph_num +"']");
		 By get_callback_service_id = By.xpath("(//div[@class='cell-content'][text()='"+ ph_num +"'])[last()]//ancestor::div[1]/preceding-sibling::div[3]/div");
		 By get_callback_state = By.xpath("(//div[@class='cell-content'][text()='"+ ph_num +"'])[last()]//ancestor::div[1]/preceding-sibling::div[2]/div");
		 By get_callback_time = By.xpath("(//div[@class='cell-content'][text()='"+ ph_num +"'])[last()]//ancestor::div[1]/preceding-sibling::div[1]/div");
		 By get_callback_service_name = By.xpath("(//div[@class='cell-content'][text()='"+ ph_num +"'])[last()]//ancestor::div[1]/following-sibling::div[1]/div");
		 By select_callback_forCancel = By.xpath("(//div[@class='cell-content'][text()='"+ ph_num +"'])[last()]//ancestor::div[1]/preceding-sibling::div[4]/div/label");
		sleepFor(2);
		getElement(select_callback_forCancel,20).click();
		sleepFor(2);
		getElement(btn_cancelCB,20).click();
		sleepFor(2);
		getElement(btn_confirmCancel_CB,20).click();
		sleepFor(5);
		Clear_all_notifications();
		sleepFor(2);
		
		if (getElement(go_to_last_page, 10).isEnabled()){
			getElement(go_to_last_page, 10).click();
		};
		System.out.println("\n The Callback " +getElement(get_callback_service_id,15).getText()+ " is cancelled successfully!");
		
		getElement(btn_createcallbck, 30);
		
		sleepFor(5);
		
		System.out.println("\n The cancelled callback state is : " +getElement(get_callback_state, 15).getText());
		
		if (getElement(get_callback_state, 15).getText() == "PROCESSING")
		
		{
			System.out.println("\n The callback cannot be cancelled as the callback state is : "+getElement(get_callback_state, 15).getText()+". Ensure that agents are logged out and re-run the tests again");
		}
		
		else
			
		{
			
		String state = "COMPLETED\n(CANCELLED_BY_ADMIN)";
		
		assertEquals(getElement(get_callback_state, 15).getText(),state);
		
		}
		
	}
	
	public void cancel_multiple_Callback(String ph_num) {
		 
		List<WebElement> callback_forCancel = get_webelements_list(By.xpath("//div[@class='cell-content'][text()='"+ ph_num +"'][last()]//ancestor::div[1]/preceding-sibling::div[4]/div/label"));
		 for ( WebElement checkbox : callback_forCancel ) {
		     if ( !checkbox.isSelected() ) {
		    	 checkbox.click();
		     }
		 }
		sleepFor(2);
		getElement(btn_cancelCB,20).click();
		sleepFor(2);
		getElement(btn_confirmCancel_CB,20).click();
		sleepFor(3);
		List<WebElement> get_callback_service_id = get_webelements_list(By.xpath("//div[@class='cell-content'][text()='"+ ph_num +"'][last()]//ancestor::div[1]/preceding-sibling::div[3]/div"));
		for ( WebElement callback_id : get_callback_service_id ) {
			System.out.println("\n The Callback " +callback_id.getText()+ " is cancelled successfully!");
		}
		
		List<WebElement> get_callback_state = get_webelements_list(By.xpath("//div[@class='cell-content'][text()='"+ ph_num +"'][last()]//ancestor::div[1]/preceding-sibling::div[2]/div"));
		for ( WebElement state : get_callback_state ) {
			System.out.println("\n The cancelled callback state is : " +state.getText());
			if (state.getText() == "PROCESSING"){
				System.out.println("\n The callback cannot be cancelled as the callback state is : "+state.getText()+". Ensure that agents are logged out and re-run the tests again");
			}
			else {
			String expected_state = "COMPLETED\n(CANCELLED_BY_ADMIN)";
			assertEquals(state.getText(),expected_state);			
			}
		}
		
	}
	
	public void recreate_Callback(String ph_num) {
		sleepFor(2);
		By chk_callback_created_by_phnum = By.xpath("(//div[@class='cell-content'][text()='"+ ph_num +"'])[last()]");
		 By chk_callbacks_count = By.xpath("//div[@class='cell-content'][text()='"+ ph_num +"']");
		 By get_callback_service_id = By.xpath("(//div[@class='cell-content'][text()='"+ ph_num +"'])[last()]//ancestor::div[1]/preceding-sibling::div[3]/div");
		 By get_callback_state = By.xpath("(//div[@class='cell-content'][text()='"+ ph_num +"'])[last()]//ancestor::div[1]/preceding-sibling::div[2]/div");
		 By get_callback_time = By.xpath("(//div[@class='cell-content'][text()='"+ ph_num +"'])[last()]//ancestor::div[1]/preceding-sibling::div[1]/div");
		 By get_callback_service_name = By.xpath("(//div[@class='cell-content'][text()='"+ ph_num +"'])[last()]//ancestor::div[1]/following-sibling::div[1]/div");
		By btn_recreate_callback = By.xpath("(//div[@class='cell-content'][text()='"+ ph_num +"'])[last()]//ancestor::div[1]/following-sibling::div[3]//button");
		int a = countOfElements(chk_callbacks_count);
		System.out.println("\n There are already " +a+ " callback(s) present for the given phone number : "+ph_num);
		System.out.println("\n Recreating the callback :" +getElement(get_callback_service_id,15).getText());
		getElement(btn_recreate_callback, 15).click();
		sleepFor(5);
		
		// Select desired date
		getElement(By.xpath("//button[contains(@class, 'calendar-button')]"), 20).click();
	
		Date date = new Date();
		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		int day = localDate.getDayOfMonth();
		int month = localDate.getMonthValue();
		String todays_date = Integer.toString(day);
		moveToElement(getElement(By.xpath("//div[@class='calendars']//button[text()='"+todays_date+"'][not(contains(@class,'isExtra'))]"), 15));
		getElement(By.xpath("//div[@class='calendars']//button[text()='"+todays_date+"'][not(contains(@class,'isExtra'))]"), 15).click();
		System.out.println("\n Selecting the calendar date as today's date which is : "+day);
		
		// Select desired time
		getElement(By.id("callback-time-select"), 20).click();
		getElement(By.xpath("//*[@id='callback-time-select']/option[2]"), 20).click();
		System.out.println("\n Selecting the desired time :" +getElement(By.xpath("//*[@id='callback-time-select']/option[2]"), 20).getText());
		
		
		// Click recreate button
		getElement(btn_recreate, 15).click();
		sleepFor(5);
		
		System.out.println("\n Recreated the callback successfully");
		System.out.println("\n ************************************************************************");
		System.out.println("\n Recreated callback id is : " +getElement(get_callback_service_id,15).getText());
		System.out.println("\n Recreated callback state is : "+getElement(get_callback_state, 15).getText());
		System.out.println("\n ************************************************************************");
		int b = countOfElements(chk_callbacks_count);
		System.out.println("\n Now there are " +b+ " callbacks present for the phone number : "+ph_num);
		sleepFor(3);
	}
	
	public String createImmediateCallback_and_returnServiceID(String ph_num) {
		By chk_callback_created_by_phnum = By.xpath("(//div[@class='cell-content'][text()='"+ ph_num +"'])[last()]");
		 By chk_callbacks_count = By.xpath("//div[@class='cell-content'][text()='"+ ph_num +"']");
		 By get_callback_service_id = By.xpath("(//div[@class='cell-content'][text()='"+ ph_num +"'])[last()]//ancestor::div[1]/preceding-sibling::div[3]/div");
		 By get_callback_state = By.xpath("(//div[@class='cell-content'][text()='"+ ph_num +"'])[last()]//ancestor::div[1]/preceding-sibling::div[2]/div");
		 By get_callback_time = By.xpath("(//div[@class='cell-content'][text()='"+ ph_num +"'])[last()]//ancestor::div[1]/preceding-sibling::div[1]/div");
		 By get_callback_service_name = By.xpath("(//div[@class='cell-content'][text()='"+ ph_num +"'])[last()]//ancestor::div[1]/following-sibling::div[1]/div");
		getElement(btn_createcallbck, 30).click();
		sleepFor(1);
		selectByVisibleText(drpdwn_CallbckType, testdata_property_file_reader("CallbckType_Imm"), 2);
		getElement(txt_SrviceType, 10).sendKeys(testdata_property_file_reader("Service"));
		getElement(txt_custNumber, 10).sendKeys(ph_num);
		getElement(btn_final_creatCallbck, 10).click();
		sleepFor(2);	
		getElement(btn_createcallbck, 30);
		Clear_all_notifications();
		sleepFor(2);
		
		/** The below method selects the filter to 'today'. If it had been set to some other filter, the verification of callback creation fails! **/ 
		filterUIDate("Today");
		
		sleepFor(2);
		if (getElement(go_to_last_page, 10).isEnabled()) {
			getElement(go_to_last_page, 10).click();
		};
		
		/** The below method checks for the latest callback that has been created for the same phone number: **/
		getElement(chk_callback_created_by_phnum, 20);
		
		String service_id = getElement(get_callback_service_id,15).getText();
				
		return service_id;
	}
	
	public void verify_Default_CallbackPage_Columns(){
		
		System.out.println("\n By Default without any filters - the columns would be displayed as : Service ID | State | Desired Callback Time | Phone Number | Service Name | Time Updated");
		List <String> default_col_headers = new ArrayList<String>();
		
		default_col_headers.add("Callback ID");
		default_col_headers.add("State");
		default_col_headers.add("Desired Callback Time");
		default_col_headers.add("Phone Number");
		default_col_headers.add("Service Name");
		default_col_headers.add("Time Updated");
		
		List <String> obtained_col_headers = new ArrayList<String>();
		int m = countOfElements(By.xpath("//div[contains(@class,'callback-grid')]/div/div[contains(@class,'grid-hcell')]"));
		System.out.println("\n There are totally "+m+" columns present in the UI.");
		// The first column is a checkbox and the last column is for recreate callback. So neglecting them to fetch the column
		System.out.println("\n The first column is a checkbox and the last column is for recreate callback, therfore, neglecting them ");
		
		sleepFor(3);
		System.out.println("\n Fetching the column headers from UI");
		for(int i = 2; i<=m-1;i++)
		{
			obtained_col_headers.add(getElement(By.xpath("(//div[contains(@class,'callback-grid')]/div/div[contains(@class,'grid-hcell')])["+i+"]"),15).getText());
		}
		
		System.out.println("\n The column headers fetched from the UI is : "+obtained_col_headers);
		
		Assert.assertEquals(obtained_col_headers,default_col_headers);
		
		System.out.println("\n The column headers are present in the page and verified successfully");
		
	}
	
	public void apply_filter_remove_ServiceIDcolumn(){
		Click_AdvancedOptions();
		
		// Verify the un-checked options. Select all the options.
		int n = 0;
		n = countOfElements(By.xpath("//div[@class='leftGroup']//input[@type='checkbox'][not(@checked='')]/ancestor::label"));
		System.out.println("\n There are "+n+ " unchecked fields");
		
		System.out.println("\n Selecting all the labels");
		sleepFor(3);
		for (int i=1; i<=n; i++)
		{
			getElement(By.xpath("(//div[@class='leftGroup']//input[@type='checkbox'][not(@checked='')]/ancestor::label)["+i+"]"), 15).click();
		}
		
		System.out.println("\n All the labels are now selected. Unchecking the service ID column..");
		
		getElement(By.id("enable-service-ID"),15).click();
		
		System.out.println("\n The Service ID column is unchecked");
		
		getElement(btn_save_adv, 3).click();
	}
	
public void verify_filtered_CallbackPage_Columns(){
		
		List <String> filtered_col_headers = new ArrayList<String>();
		filtered_col_headers.add("State");
		filtered_col_headers.add("Desired Callback Time");
		filtered_col_headers.add("Phone Number");
		filtered_col_headers.add("Service Name");
		filtered_col_headers.add("Time Updated");
		
		sleepFor(4);
		
		List <String> obtained_col_headers = new ArrayList<String>();
		int n = countOfElements(By.xpath("//div[contains(@class,'callback-grid')]/div/div[contains(@class,'grid-hcell')]"));
		System.out.println("\n There are totally "+n+" columns present in the UI after filter is applied");
		// The first column is a checkbox and the last column is for recreate callback. So neglecting them to fetch the column
		System.out.println("\n The first column is a checkbox and the last column is for recreate callback, therfore, neglecting them ");
		
		sleepFor(3);
		System.out.println("\n Fetching the column headers from UI");
		for(int i = 2; i<=n-1;i++)
		{
			obtained_col_headers.add(getElement(By.xpath("(//div[contains(@class,'callback-grid')]/div/div[contains(@class,'grid-hcell')])["+i+"]"),15).getText());
		}
		
		System.out.println("\n The column headers fetched from the UI is : "+obtained_col_headers);
		
		Assert.assertEquals(obtained_col_headers,filtered_col_headers);
		
		System.out.println("\n The column headers are present in the page as per the filter and verified successfully");
		
	}

public void reset_advancedOptions_ColumnView(){

	Click_AdvancedOptions();
	int p = 0;
	p = countOfElements(By.xpath("//div[@class='leftGroup']//input[@type='checkbox'][not(@checked='')]/ancestor::label"));
	System.out.println("\n There are "+p+ " unchecked fields");
	
	System.out.println("\n Selecting all the unselected labels. . . . . . . ");
	sleepFor(3);
	for (int i=1; i<=p; i++)
	{
		getElement(By.xpath("(//div[@class='leftGroup']//input[@type='checkbox'][not(@checked='')]/ancestor::label)["+i+"]"), 15).click();
	}
	
	System.out.println("\n All the labels are now selected suucessfully ");
	
	System.out.println("\n The column labels are now reset back");
	
	getElement(btn_save_adv, 3).click();

}

public void reset_advancedOptions_VQselection()
{
	
}
	
	/*****************************************************************************************************************/
	/*******************************************Reusable Functions****************************************************/
	
	public void filterUIDate(String filter_date) {
		getElement(By.xpath("//select/option[text()='"+filter_date+"']"),10).click();
		System.out.println("\n The date filter is set to : " + filter_date );
	}
	
	public String fetchCallbackServiceID(String ph_num) {
		sleepFor(3);
		 By get_callback_service_id = By.xpath("(//div[@class='cell-content'][text()='"+ ph_num +"'])[last()]//ancestor::div[1]/preceding-sibling::div[3]/div");
		return getElement(get_callback_service_id,15).getText();
	}
	
	public void Click_AdvancedOptions() {
		sleepFor(2);
		for(int i=1 ; i<=10 ; i++) {
			if(getElement(btn_advanceOptions_parent, 15).getAttribute("class").contains("disabled-clickable")) {
				sleepFor(1);
			}
			else
				break;
		}
		getElement(btn_advanceOptions, 15).click();
		System.out.println("\n Clicking the advanced option button. . . . . . . . ");
	}
	
	public void verifyAdvancedOptions_Headers() {
		sleepFor(2);
		List<String> headers = new ArrayList<String>();
		headers.add("Configure Columns");		
		headers.add("Configure Custom Date Range");
		headers.add("Visible Callback States");
		headers.add("Visible Callback Services");
	
		System.out.println("\n Fetching the headers from UI");
		
		List<String> headers_ui = new ArrayList<>();
		int n = countOfElements(By.xpath("//h3"));
		System.out.println("\n The total number of headers in UI is : " +n);
		for(int i=1; i<=n ; i++)
		{
			headers_ui.add(getElement(By.xpath("(//h3)["+i+"]"), 15).getText());
		}
		
		System.out.println("\n The column headers fetched from UI are :" +headers_ui);
		
		Assert.assertEquals(headers, headers_ui);
		System.out.println("\n The column headers are present in the page");
		getElement(btn_save_adv, 15).click();
	}
	
	public void signout() {
		sleepFor(4);
		getElement(btn_userid, 15).click();
		sleepFor(4);
		getElement(btn_logout, 15).click();
		System.out.println("\n Signing out. \n");
		sleepFor(2);
	}
	
	
	public void filterUIwithServiceName() {
		sleepFor(3);
		getElement(btn_advanceOptions, 15).click();
		sleepFor(2);
		if(getElement(chk_services, 3).isSelected()) {
			jsClick(getElement(chk_services, 3));
		}else {
			jsClick(getElement(chk_services, 3));
			sleepFor(3);
			jsClick(getElement(chk_services, 3));
		}		
		getElement(txt_serviceInput, 5).sendKeys(testdata_property_file_reader("Service"));
		jsClick(getElement(By.xpath("//input[@title='Select/Unselect']"), 3));
		getElement(btn_save_adv, 3).click();
		System.out.println("\n The " +testdata_property_file_reader("Service")+ " VQ is selected from the advanced options");
	}
	
	public void verify_callback_metrics(String ph_num) {
		 By btn_callback_metrics = By.xpath("(//div[@class='cell-content'][text()='"+ ph_num +"'])[last()]//ancestor::div[1]/preceding-sibling::div[3]/div/button");
		 getElement(btn_callback_metrics, 30).click();
		
	}

	public void Click_Callback_sort(String col) {
		
		System.out.println("\n Sorting the column: " + col);
		List <String> default_col_headers = new ArrayList<String>();
		
		default_col_headers.add("Callback ID");
		default_col_headers.add("State");
		default_col_headers.add("Desired Callback Time");
		default_col_headers.add("Phone Number");
		default_col_headers.add("Service Name");
		default_col_headers.add("Time Updated");
		sleepFor(3);
		
		int a = 0;
		
		List <String> fetched_col_headers = new ArrayList<String>();
		for (int i=0; i<default_col_headers.size(); i++)
		{
			if (default_col_headers.get(i) == col )
			{
				a = i+2;
				break;
			}	
		}		
		getElement(By.xpath("//*[@id='callbackTable']/div/div[1]/div["+a+"]"),15).click();
		//getElement(By.xpath("//*[@id='hrow-alarm-grid']/div["+a+"]/div"),15).click();
		
		// TODO Auto-generated method stub
		
	}

	public String Fetch_Callback_row(String col) {
		
		System.out.println("\n Fetching the column value : " + col);
		List <String> default_col_headers = new ArrayList<String>();
		
		default_col_headers.add("Callback ID");
		default_col_headers.add("State");
		default_col_headers.add("Desired Callback Time");
		default_col_headers.add("Phone Number");
		default_col_headers.add("Service Name");
		default_col_headers.add("Time Updated");
		default_col_headers.add("Completed Reason");
		sleepFor(3);
		
		int a = 0;
		int c = 0;
		
		List <String> fetched_col_headers = new ArrayList<String>();
		for (int i=0; i<default_col_headers.size(); i++)
		{
			if (default_col_headers.get(i) == col )
			{
				a = i+2;
				break;
			}	
		}		
		if (a==8){
			c=3;
			a=3;
		}
		String val = getElement(By.xpath("//*[@id='callbackTableBody']/div[1]/div["+a+"]/div"),15).getText();
		if (a==3){
		
		if (c==3){
			val = val.substring(0,val.indexOf(')'));
			val = val.substring(val.indexOf('(')+1,val.length());
			}
		else
		{
			val = val.substring(0,val.indexOf('('));}
		}
		return val;

	}

	public void Click_Filter_button() {
		System.out.println("\n Clicking the filter button");
		getElement(By.xpath("//*[@class='search-category-dropdown-inputs-container']/div/button"),15).click();
		sleepFor(3);
		
	}

	public void Verify_Filter_working_Callback(String col, String val) {
	
		List <String> default_col_headers = new ArrayList<String>();
		
		default_col_headers.add("Callback ID");
		default_col_headers.add("State");
		default_col_headers.add("Desired Callback Time");
		default_col_headers.add("Phone Number");
		default_col_headers.add("Service Name");
		default_col_headers.add("Time Updated");
		default_col_headers.add("Completed Reason");
		sleepFor(3);
		
		int a = 0;
		int b = 0;
		int c = 0;
		
		List <String> fetched_col_headers = new ArrayList<String>();
		for (int i=0; i<default_col_headers.size(); i++)
		{
			if (default_col_headers.get(i) == col )
			{
				a = i+2;
				b = i+1;
				break;
			}	
		}	
		if(b>2)
		{
			b=b+1;
		};
		if(b>7)
		{
			b=3;
		};

		System.out.println("\n Selecting the filter type");
		getElement(By.xpath("//div[@class='search-category-dropdown-container']/div[2]/div/div/div["+b+"]"),15).click();
		System.out.println("\n Input the value");
		getElement(search_box_callback, 15).click();
		getElement(search_box_callback, 15).clear();
		getElement(search_box_callback, 15).sendKeys(val);
	
		if (a==8){
			c=3;
			a=3;
		}
		String res = getElement(By.xpath("//*[@id='callbackTableBody']/div[1]/div["+a+"]/div"),15).getText();
		if (a==3){
		if (c==3){
			res = res.substring(0,res.indexOf(')'));
			res = res.substring(res.indexOf('(')+1,res.length());
			}
		else 
		{
			res = res.substring(0,res.indexOf('('));
		}
		}
		Assert.assertEquals(val,res);
		System.out.println("\n The filter is working properly for : " + col);
		
	}

	public void Click_Next_button() {
		System.out.println("\n Clicking the next button ");
		sleepFor(3);
		getElement(btn_callback_next, 15).click();			
	}
	
	public void Click_First_button() {		
		System.out.println("\n Clicking the first button ");
		sleepFor(3);
		getElement(btn_callback_first, 15).click();		
	}
	
	public void Click_Last_button() {		
		System.out.println("\n Clicking the last button ");
		sleepFor(3);
		getElement(btn_callback_last, 15).click();		
	}
	
	public void Click_Previous_button() {		
		System.out.println("\n Clicking the previous button ");
		sleepFor(3);
		getElement(btn_callback_previous, 15).click();		
	}

	public void Verify_Navigation(String num) {
		sleepFor(3);
		String fetched_num = getElement(page_number, 15).getAttribute("value");	
		if (Integer.parseInt(num)>3){
			num = getElement(By.xpath("//*[@id='footer']/div/div[2]/section/p"), 15).getText();
			num = num.substring(7);		
		}
		System.out.println("\n Fetched Number: "+fetched_num);
		Assert.assertEquals(num,fetched_num);
	}
	
	public int Fetch_Max_Page() {
		sleepFor(3);
		String num = getElement(By.xpath("//*[@id='footer']/div/div[2]/section/p"), 15).getText();
		num = num.substring(7);	
		System.out.println(num);
		return Integer.parseInt(num);
	}
	
	public void Click_Add_CustomDateRange() {
		sleepFor(2);
		getElement(btn_add_custom, 15).click();
		System.out.println("\n Clicking the add custom date range button. . . . . . . . ");
		getElement(txt_name_custom, 5).sendKeys("hello hello");
		getElement(txt_start_offset, 5).sendKeys("-2");
		getElement(txt_end_offset, 5).sendKeys("2");
		getElement(btn_save_adv, 30).click();
		filterUIDate("(Custom) hello hello");
		sleepFor(10);
	}
	
	public void Delete_CustomDateRange() {
		sleepFor(2);
		getElement(btn_delete_custom, 15).click();
		System.out.println("\n Clicking the delete custom date range button. . . . . . . . ");
		getElement(btn_save_adv, 30).click();
		sleepFor(10);
	}
	
}
