package ges.pages;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.codehaus.jackson.JsonNode;
import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;

import com.google.gson.Gson;

import ges.util.BaseTest;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import junit.framework.Assert;

public class CallinPage extends BaseTest{
	
	//public String call_in_num;
	
	/**************************************************************************/
	/********************** API data *************************/

    private String ctciURL = "http://ges-usw1-int.genhtcc.com:8080/engagement/v3/call-in/requests/create";
	private String x_apikey = "vZPEOqQTl54qsQ4oVCqfR6o5RLOFZtrk8HTbYeot";
    private static String groupName = "nonUniqueGroup1";
    //private static String fromPhoneNumber = "919952973883";
    private static String ccid = "217576a6-52b6-4ca6-8094-a29b726f7e9f";
    private static String access_code = null;
    public static Response r = null;
    public String response_string = null;
    public String id = null;
    
    /**************************************************************************/
	/********************** UI data *************************/
    
    private By tab_call_in = By.id("2");
    private By btn_refresh = By.xpath("//*[@id='callin-table-refresh']");
    private By id_callin_row = By.xpath("//div[@class='cell-content'][text()='"+id+"']");
    private By txt_box_search = By.xpath("//input[@class='mainInput']");
	private By btn_expiry_time = By.xpath("//*[@id='grid-call-in']/div[1]/div[5]/div/div");
    

	/**
     *  ************************************************************************/
    
    public void createCall_in(String val) throws ParseException{
        System.out.println("Request to create CTIC with valid data :\n\tPOST " + ctciURL);
        
        //Create Headers
        
        Map<String, String> headers = new HashMap<String, String>();
		headers.put("ccid", ccid);
		headers.put("Content-Type", "application/json");
		headers.put("x-api-key", x_apikey);
		
		// Send JSON body - groupName and fromPhoneNumber only
		
        JSONObject user=new JSONObject();
        user.put("groupName", groupName);
        user.put("fromPhoneNumber", val);
        String body_jsonData_str=user.toString();
        
        // Get response
        
		r = given().relaxedHTTPSValidation().contentType(ContentType.JSON).body(body_jsonData_str).headers(headers).post(ctciURL);
		response_string = r.then().extract().response().asString();
        System.out.println("\nResponse for create call-in request :\n\t"+ r.getStatusLine() + " "+ response_string);
        System.out.println(r.body());
        // Assert status code : 200
        r.then().assertThat().statusCode(200);
        
        System.out.println("\n The Call in is created successfully");
        
    }
    
    public void createCall_in_invalid(String val) throws ParseException{
        System.out.println("Request to create CTIC with invalid phone number :\n\tPOST " + ctciURL);
        
        //Create Headers
        
        Map<String, String> headers = new HashMap<String, String>();
		headers.put("ccid", ccid);
		headers.put("Content-Type", "application/json");
		headers.put("x-api-key", x_apikey);
		
		// Send JSON body - groupName and fromPhoneNumber only
		
        JSONObject user=new JSONObject();
        user.put("groupName", groupName);
        user.put("fromPhoneNumber", val);
        String body_jsonData_str=user.toString();
        
        // Get response
        
		r = given().relaxedHTTPSValidation().contentType(ContentType.JSON).body(body_jsonData_str).headers(headers).post(ctciURL);
		response_string = r.then().extract().response().asString();
        System.out.println("\nResponse for create call-in request :\n\t"+ r.getStatusLine() + " "+ response_string);
        System.out.println(r.body());
        // Assert status code : 200
        r.then().assertThat().statusCode(400);
        
        System.out.println("\n The Call-in request throws a 400 status code error");
        
    }
    
    
        public void fetchDatafromResponse() throws ParseException{
        	
        	JSONParser parser = new JSONParser();
        	JSONObject jsonObject = (JSONObject) parser.parse(response_string);
        	JSONObject status = (JSONObject) jsonObject.get("status");
        	JSONObject data = (JSONObject) jsonObject.get("data");
        	System.out.println("\n The status is : " +status);
        	System.out.println("\n The data is : " +data);
            

        	access_code = (String) data.get("accessCode");
        	System.out.println("\n Fetching the access code..");
        	System.out.println("\n The accesscode generated is : " +access_code);


        	String callin_id = (String) data.get("id");
        	System.out.println("\n Fetching the call-in id..");
        	System.out.println("\n The id generated is : " +callin_id);


        	String success_corr_id = (String) status.get("corrId");
        	System.out.println("\n Fetching the correlation id.. ");
        	System.out.println("\n The correlation id generated is : " +success_corr_id+ "\n");
        	
        }      	
            public String fetchCorrelationID() throws ParseException{
            	
            	JSONParser parser = new JSONParser();
            	JSONObject jsonObject = (JSONObject) parser.parse(response_string);
            	JSONObject status = (JSONObject) jsonObject.get("status");
            	JSONObject data = (JSONObject) jsonObject.get("data");
            	System.out.println("\n The status is : " +status);
            	System.out.println("\n The data is : " +data);
 
            	String corr_id = (String) status.get("corrId");
            	System.out.println("\n Fetching the correlation id.. ");
            	System.out.println("\n The correlation id generated is : " +corr_id+ "\n");
            	return corr_id;
            }
    	

/***************************************************************************************************************************/   

        
public String getID() throws ParseException{
        	
        	JSONParser parser = new JSONParser();
        	JSONObject jsonObject = (JSONObject) parser.parse(response_string);
        	JSONObject data = (JSONObject) jsonObject.get("data");

        	String callin_id = (String) data.get("id");
        	System.out.println("\n The Call-In id is "+callin_id);
        	return callin_id;
}
        
public void Navigate_To_CallIn_Tab() {
	sleepFor(2);
	// Clicking the Developer tab
	System.out.println("\n Clicking the Call-In tab");
	getElement(tab_call_in, 15).click();
	System.out.println("\n Navigated to the Call-In tab successfully");
}

public void Click_Refresh() {
	sleepFor(15);
	getElement(btn_refresh, 15).click();
	System.out.println("\n Refreshing the page..");
}

public void Verify_Callin_Column_Header() {
	sleepFor(3);
	List<String> headers = new ArrayList<String>();
	headers.add("ID");		
	headers.add("State");
	headers.add("Time Created");
	headers.add("ANI");
	headers.add("Expiry Time");
	headers.add("Group Name");

	System.out.println("\n Fetching the Call-In headers from UI");
	
	List<String> headers_ui = new ArrayList<>();
	int z = countOfElements(By.xpath("//div[contains(@class,'grid-hrow')]/div"));
	System.out.println("\n The total number of headers in UI is : " +z);
	for(int i=1; i<=z ; i++)
	{
		headers_ui.add(getElement(By.xpath("(//div[contains(@class,'grid-hrow')]/div)["+i+"]"), 15).getText());
	}
	
	System.out.println("\n The column headers fetched from UI are :" +headers_ui);
	
	Assert.assertEquals(headers, headers_ui);
	System.out.println("\n The column headers are present in the page");

}

public void Verify_created_CallIn(String callinid) {
	sleepFor(2);
	System.out.println("\n Verifying for the created Call-In by id :" +callinid);
	//getElement(id_callin_row, 15);
	//System.out.println("\n The created Call-In is present in the UI");

   // moveToElement(getElement(get_callin_state,15));
    
	
}
public void applyFilter_and_enterID(String id) {
	// TODO Auto-generated method stub
	sleepFor(2);
	System.out.println("\n Selecting the ID filter");
	getElement(By.xpath("//*[@class='mainInput']"),15).click();
	getElement(txt_box_search, 15).sendKeys(id);
	System.out.println("\n Entering the Call in ID : " +id);
	
	
	//verify that there is only one record displayed as "id" is a unique field.
	
	int a = countOfElements(By.xpath("//div[contains(@class,'grid-rowgroup')]/div"));
	Assert.assertEquals(1, a);
	System.out.println("\n There are "+a+" matching call-ins present in the UI for the selected ID filter");
}


public void applyFilter_and_enterANI(String ani) {
	// TODO Auto-generated method stub
	sleepFor(2);
	System.out.println("\n Selecting the ANI filter");
	getElement(By.xpath("//button[contains(@class, 'search-category-dropdown-button')]"),15).click();
	getElement(By.xpath("(//p[@class='label'])[4]"),15).click();
	getElement(txt_box_search, 15).sendKeys(ani);
	System.out.println("\n Entering the Call in ANI : " +ani);
	
	
	//verify that there is only one record displayed as "id" is a unique field.
	
	int a = countOfElements(By.xpath("//div[contains(@class,'grid-rowgroup')]/div"));
	Assert.assertEquals(1, a);
	System.out.println("\n There are "+a+" matching call-ins present in the UI for the selected ANI filter");
}

public void Click_expiry_time() {
	sleepFor(2);
	// Clicking the Developer tab
	System.out.println("\n Clicking the expiry time");
	getElement(btn_expiry_time, 15).click();
	getElement(btn_expiry_time, 15).click();
	System.out.println("\n Clicked successfully");
}


public void display_callin_details_toConsole(String call_in_num) {
	sleepFor(3);
	
	By get_callin_state = By.xpath("//div[@class='cell-content'][text()='"+call_in_num+"']//ancestor::div[2]/div[2]/div");
    By get_callin_timeCreated = By.xpath("//div[@class='cell-content'][text()='"+call_in_num+"']//ancestor::div[2]/div[3]/div");
    By get_callin_ANI = By.xpath("//div[@class='cell-content'][text()='"+call_in_num+"']//ancestor::div[2]/div[4]/div");
    By get_callin_expiry = By.xpath("//div[@class='cell-content'][text()='"+call_in_num+"']//ancestor::div[2]/div[5]/div");
    By get_callin_grpName = By.xpath("//div[@class='cell-content'][text()='"+call_in_num+"']//ancestor::div[2]/div[6]/div");
    
    System.out.println("\n ************************************************************************");
	System.out.println("\n The Call-In State is : " +getElement(get_callin_state,15).getText());
	System.out.println("\n The Call-In Time Created is : "+getElement(get_callin_timeCreated,30).getText());
	System.out.println("\n The Call-In ANI is : "+getElement(get_callin_ANI,30).getText());
	System.out.println("\n The Call-In Expiry Time is : "+getElement(get_callin_expiry,15).getText());
	System.out.println("\n The Call-In Group Name is : "+getElement(get_callin_grpName,15).getText());
    System.out.println("\n ************************************************************************");
}

public void calculate_no_of_callin_rows() {
	sleepFor(2);
	int callin_rows = countOfElements(By.xpath("//div[contains(@class,'grid-rowgroup')]/div"));
	System.out.println("\n Calculating the total number of call-ins by checking xpath.. ");
	System.out.println("\n There are totally "+callin_rows+" call-ins present in the UI");
	System.out.println("\n Now verifying with the total value displayed in the UI");
	String val = getElement(By.xpath("//div[@class='displayCountContainer']"), 15).getText();
	System.out.println("\n The value displayed in UI is :"+val);
	String no_of_rows = String.valueOf(callin_rows);
	Boolean isFound = val.contains(no_of_rows);
	if (isFound == true)
	{
		System.out.println("\n The total no of rows identified by xpath is equal to the value displayed in UI");
	}
	else
	{
		System.out.println("\n The total no of rows identified by xpath is not equal to the value displayed in UI. Needs verification.");
	}
	
}

}
