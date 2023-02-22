package ges.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.testng.IInvokedMethod;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.Reporter;




public class ConsolidatedHTMLReport {

	private FileWriter cons_file_out;
	private PrintWriter cons_print_out;
	private final String testReportPath = "./FunctionalTestReport/";
	private final String testLogPath = System.getProperty("user.dir") + "//Logs//";
	Logger reportLog = Logger.getLogger(Thread.currentThread().getStackTrace()[1].getClassName());
	//private final static String GES_DATA_PATH = "src/main/resources/prop/GES_Version.properties";
	public String version = null;
	public static String currentTime = null;
	//public LoginPage login = new LoginPage();
	//public String ges_v = login.getGESVersion();

	/***
	 * To create a HTML report for the TestNG Suite level
	 * 
	 */
	protected void createSuiteReport(String suiteName) {

		try {
			File customReport = new File(testReportPath);
			if (!customReport.exists())
				customReport.mkdirs();
			customReport = new File(testReportPath + currentTime);
			customReport.mkdirs();

			cons_file_out = new FileWriter(testReportPath + currentTime + "/" + suiteName + ".html");
		} catch (IOException e) {
			Reporter.log("Error in Suite Report File Creation" + e);
			reportLog.error(e.getMessage());
		}
		cons_print_out = new PrintWriter(cons_file_out);
	}
	
	/***
	 * 
	 * To depict the suite overall execution details - Passed, Failed and
	 * Skipped with Percentage Provide Pie-Chart based on the suite execution
	 * results
	 * 
	 * @param timeDuration
	 * @param count
	 * 
	 * 
	 */
	protected void suiteOverAllExecutionDetails(String timeDuration, int[] count) {

		int passPercentile = (count[1] > 0) ? Math.round(count[1] * 100 / count[0]) : 0;
		int failPercentile = (count[2] > 0) ? Math.round(count[2] * 100 / count[0]) : 0;
		int skipPercentile = (count[3] > 0) ? Math.round(count[3] * 100 / count[0]) : 0;

		cons_print_out.println(
				"<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\"><html xmlns=\"http://www.w3.org/1999/xhtml\">");
		cons_print_out.println("<head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=iso-8859-1\" />");
		cons_print_out.println("<title>ConsolidateTestReport</title>");
		cons_print_out.println(
				"<link href='http://fonts.googleapis.com/css?family=Roboto:400,300,500,700' rel='stylesheet' type='text/css'/>");
		cons_print_out.println(
				"<link href='http://fonts.googleapis.com/css?family=Roboto+Condensed:400,700,300' rel='stylesheet' type='text/css'/>");
		cssStyle(cons_print_out);
		cons_print_out.println("<script>window.onload=function(){ passed=" + passPercentile + "; failed="
				+ failPercentile + "; skipped=" + skipPercentile + ";}</script>");
		cons_print_out.println("<script type=\"text/javascript\" src=\"https://www.google.com/jsapi\"></script>");
		cons_print_out.println("<script type=\"text/javascript\">");
		cons_print_out.println("google.load(\"visualization\", \"1\", {packages:[\"corechart\"]});");
		cons_print_out.println("google.setOnLoadCallback(drawChart);");
		cons_print_out.println("function drawChart(){");
		cons_print_out.println(
				"var data = google.visualization.arrayToDataTable([['Test_report', 'Percentage'],['Pass', passed],['Fail', failed],['Skipped', skipped]]);");
		cons_print_out.println("var options = {");
		cons_print_out.println("legend: { position : 'right', textStyle: {color: 'black', fontSize: 14}},");
		cons_print_out.println("chartArea:{left:0,top:0,width:\"100%\",height:\"100%\"},");
		cons_print_out.println("is3D: true,");
		cons_print_out.println("backgroundColor: 'transparent',");
		cons_print_out.println("slices: {0: {color: '#B8FF70'}, 1:{color: '#FF6666'}, 2:{color: '#FFFFA3'}},");
		cons_print_out.println("pieSliceTextStyle: {color: 'black', fontName: 'Arial', fontSize: '22'}};");

		cons_print_out
				.println("var chart = new google.visualization.PieChart(document.getElementById('piechart_3d'));");
		cons_print_out.println("chart.draw(data, options);}");
		cons_print_out.println("</script>");

		cons_print_out.println("</head>");

		cons_print_out.println(
				"<body><div id=\"shell\"><div id=\"header\"><div id=\"header-inner\"><span class=\"consolidate-report\">Consolidated Functional Test Suite Report</span></div>");
		cons_print_out.println("</div>");
		cons_print_out.println(
				"<div class=\"content clearfix\"><div id=\"left-table\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">");

		// Left Table-Executor Details
//		cons_print_out
//				.println("<tr><td class=\"left-active-color\">Executed Browser</td><td class=\"right-active-color\">"
//						+ WrapperFunctions.ENVIRONMENT + "</td></tr>");
//
//		cons_print_out.println(
//				"<tr><td class=\"left-active-color\">Application Environment</td><td class=\"right-active-color\">"
//										+ "</td></tr>");

		cons_print_out.println("<tr><td class=\"left-active-color\">Platform</td><td class=\"right-active-color\">"
				+ System.getProperty("os.name") + "</td></tr>");
		
		cons_print_out.println("<tr><td class=\"left-active-color\">GES Version</td><td class=\"right-active-color\">"
				+ "GES Version" + "</td></tr>");

		cons_print_out.println("<tr><td class=\"left-active-color\">Total Test Cases</td><td class=\"right-active-color\">"
						+ count[0] + "</td></tr>");

		cons_print_out
				.println("<tr><td class=\"left-active-color\">Test Cases Passed</td><td class=\"right-active-color\">"
						+ count[1] + "</td></tr>");

		cons_print_out
				.println("<tr><td class=\"left-active-color\">Test Cases Failed</td><td class=\"right-active-color\">"
						+ count[2] + "</td></tr>");

		cons_print_out
				.println("<tr><td class=\"left-active-color\">Test Cases Skipped</td><td class=\"right-active-color\">"
						+ count[3] + "</td></tr>");

		cons_print_out.println(
				"<tr><td class=\"left-active-color\">Overall Test Duration (H : M : S)</td><td class=\"right-active-color\">"
						+ timeDuration + "</td></tr>");

		cons_print_out.println("</table></div>");

		// PieChart Report
		cons_print_out.println("<div id=\"right-piereport\">");
		cons_print_out.println(
				"<div id=\"piechart_3d\" style=\"width: 590px; height: 290px;  position: relative;\"></div></div>");

	}

	/***
	 * 
	 * To create suite report header
	 * 
	 */
	protected void suiteReportHeader() {

		cons_print_out.println(
				"<div id=\"table-datas\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">");

		cons_print_out.println("<tr>" + "<th width=\"18%\"><strong>" + "Module Name" + "</strong></td>");
		cons_print_out.println("<th width=\"14%\"><strong>" + "Total Tests" + "</strong></td>"
				+ "<th width=\"14%\"><strong>" + "Tests Passed" + "</strong></td>" + "<th width=\"14%\"><strong>"
				+ "Tests Failed" + "</strong></td>" + "<th width=\"14%\"><strong>" + "Tests Skipped"
				+ "</strong></td>" + "<th width=\"10%\"><strong>" + "Pass %" + "</strong></td>");
	}

	/**
	 * 
	 * To append the Test level report to the Suite Report
	 * 
	 * @param testName
	 * @param passedMethods
	 * @param failedMethods
	 * @param skippedMethods
	 * @param executionTime
	 * 
	 * 
	 */
	protected void appendingTestStatusOnSuiteReport(int class_count, String testName, int numberOfTestdata,
			int passedMethods, int failedMethods, int skippedMethods, String executionTime) {

		if (class_count % 2 == 0) {
			cons_print_out.println("<tr class=\"active-color\">");
		} else {
			cons_print_out.println("<tr class=\"inactive-color\">");
		}

		int totalMethods = passedMethods + failedMethods + skippedMethods;
		int passPercentage = (passedMethods > 0) ? Math.round(passedMethods * 100 / totalMethods) : 0;

		cons_print_out.println("<td align=\"center\"><strong><a href=\"" + testName + ".html\" target=\"_blank\">"
				+ testName + "</a></strong></td>");

		cons_print_out.println("<td align=\"center\">" + totalMethods + "</td>");
		cons_print_out.println("<td align=\"center\">" + passedMethods + "</td>");
		cons_print_out.println("<td align=\"center\">" + failedMethods + "</td>");
		cons_print_out.println("<td align=\"center\">" + skippedMethods + "</td>");
		cons_print_out.println("<td align=\"center\">" + passPercentage + "%" + "</td>");
	}

	/***
	 * 
	 * To close the suite HTML report
	 * 
	 */
	protected void endSuiteHTMLReport() {
		cons_print_out.println("</table></div></div>");
		cons_print_out.println("</body></html>");
		cons_print_out.flush();
		cons_print_out.close();
	}

	/**
	 * 
	 * Load styles for the HTML file
	 * 
	 * @param PrintWtriter
	 *            object of the HTML file
	 */
	protected void cssStyle(PrintWriter pw) {

		pw.println("<style>");
		pw.println("* { margin:0; padding:0; }");
		pw.println("body { background-color:#FFFFFF; }");
		pw.println("#shell { margin:0; min-width:1000px; }");
		// header
		pw.println(
				"#header { width:100%; height:112px; background-color:#1c8888; background-position:right; background-repeat:no-repeat; }");
		pw.println("#header-left { float:left; margin-left:20px; margin-top:38px; }");
		pw.println("#header-left p { font-size:17px; color:#FFFFFF; font-family: 'Roboto', sans-serif; }");
		pw.println(
				".module-report { font-size:36px; font-family: 'Open Sans', sans-serif; font-weight:600; color:#fbc815; text-transform:uppercase; }");
		pw.println("#header-inner { float:left; margin-left:20px; margin-top:33px; }");
		pw.println(
				".consolidate-report { font-size:36px; font-family: 'Open Sans', sans-serif; font-weight:600; color:#fbc815; text-transform:uppercase; }");
		pw.println("#logo { float:right; margin-right:85px; margin-top:15px; }");
		// images
		pw.println("img { height:20px; width:20px; }");
		// Tables
		pw.println(".content { min-height:527px; height:527px; height:auto !important; }");
		pw.println(
				"#table-datas { margin-left:2%; margin-right:2%; width:96%; margin-top:2%; margin-bottom:2%; float:left; }");
		pw.println(
				"#table-datas table tr th, #table-datas table tr td { border-bottom:1px solid #dddddd; border-right:1px solid #dddddd; }");
		pw.println("#table-datas table { border:1px solid #dddddd; border-right:none; border-bottom:none; }");
		pw.println(
				"#table-datas table tr th { background-color:#5c5c5c; height:33px; color:#FFFFFF; font-size:14px; font-weight:normal; font-family: 'Roboto', sans-serif; }");
		pw.println("#table-datas table tr td { height:24px; }");
		// pw.println("#table-datas table tr:nth-child(even)
		// {background-color:#efefef; font-size:13px; font-weight:300;
		// font-family: 'Roboto', sans-serif;}");
		// pw.println("#table-datas table td:nth-child(even)
		// {padding-left:10px;}");
		// pw.println("#table-datas table tr:nth-child(odd)
		// {background-color:#ffffff; font-size:13px; font-weight:300;
		// font-family: 'Roboto', sans-serif;}");
		// pw.println("#table-datas table td:nth-child(odd)
		// {padding-left:10px;}");
		pw.println(
				".active-color { background-color:#efefef; font-size:13px; font-weight:300; font-family: 'Roboto', sans-serif; }");
		pw.println(".active-color td { padding-left:10px; }");
		pw.println(
				".inactive-color { background-color:#ffffff; font-size:13px; font-weight:300; font-family: 'Roboto', sans-serif; }");
		pw.println(".inactive-color td { padding-left:10px; }");
		pw.println(".heading { font-size:14px; font-weight:500; color:#179c9c; font-family: 'Roboto', sans-serif; }");
		pw.println("#left-table { float:left; width:47%; margin-left:2%; margin-top:2%; }");
		pw.println("#left-table table tr td { border-bottom:1px solid #dddddd; border-right:1px solid #dddddd; }");
		pw.println("#left-table table { border:1px solid #dddddd; border-right:none; border-bottom:none; }");
		pw.println("#right-piereport { float:right; width:47%; margin-left:2%; margin-top:2%;}");
		pw.println(
				".left-active-color { background-color:#efefef; height:35px; width:55%; padding-left:20px; font-family: 'Roboto', sans-serif; font-size:13px; color:#000000; font-weight:700; }");
		pw.println(
				".right-active-color { background-color:#ffffff; height:35px; width:45%; padding-left:20px; font-family: 'Roboto', sans-serif; font-size:13px; color:#2e2e2e; font-weight:400; }");
		pw.println(".results { text-align:center; padding-left:0px !important; }");
		// Exceptin Toggle
		pw.println("#report div.show-exception { background: transparent; display: block;}");
		pw.println("#report div.up { background-position: 0 0;}");
		pw.println("#report tr.exception{ background-color:#FBFBF9;white-space: pre-wrap;font-size:12px;}");
		// Exception Filter
		pw.println("#exception-filters { margin-left:2%; margin-right:2%; width:96%; float:left; margin-top:2%;}");
		pw.println("#exception-left-table { float:left; width:30%; margin-left:2%; margin-top:2%; }");
		pw.println(
				"#exception-left-table table tr td { border-bottom:1px solid #dddddd; border-right:1px solid #dddddd; }");
		pw.println("#exception-left-table table { border:1px solid #dddddd; border-right:none; border-bottom:none; }");
		pw.println("#exception-right-piereport { float:right; width:64%; margin-left:2%; margin-top:2%;}");
		pw.println(
				".exception-left-active-color { background-color:#efefef; height:35px; width:40%; padding-left:20px; font-family: 'Roboto', sans-serif; font-size:13px; color:#000000; font-weight:700; }");
		pw.println(
				".exception-right-active-color { background-color:#ffffff; height:35px; width:15%; padding-left:20px; font-family: 'Roboto', sans-serif; font-size:13px; color:#2e2e2e; font-weight:400;}");
		/* Footer */
		pw.println("#footer { background-color:#2e2e2e; height:35px; }");
		pw.println("#footer-text { width:1000px; margin:0 auto; padding-top:8px; text-align:center; }");
		pw.println(
				"#footer-text span { font-size:12px; color:#FFFFFF; font-weight:300; font-family: 'Roboto', sans-serif; }");
		pw.println("#footer-text span a { color:#FFFFFF;}");
		// CLEAR FIX
		pw.println(
				".clearfix:after {	content: \".\"; display: block;	clear: both; visibility: hidden; line-height: 0; height: 0; }");
		pw.println(".clearfix 		{	display: inline-block; }");
		pw.println("html[xmlns] .clearfix {	display: block; }");
		pw.println("* html .clearfix { height: 1%; }");
		pw.println("</style>");
	}

	/***
	 * 
	 * To get the Passed, Failed and Skipped Test method count
	 * 
	 * @param suite
	 * 
	 */
	protected int[] toGetMethodCount(ISuite suite) {

		int[] methodCount = new int[4];
		int passedTCCount = 0;
		int failedTCCount = 0;
		int skippedTCCount = 0;

		Map<String, ISuiteResult> testsAll = suite.getResults();
		for (Map.Entry<String, ISuiteResult> tests : testsAll.entrySet()) {

			ISuiteResult suiteResult = tests.getValue();
			ITestContext testContext = suiteResult.getTestContext();

			// failed tests
			IResultMap failedTests = testContext.getFailedTests();
			Collection<ITestNGMethod> failedMethods = failedTests.getAllMethods();
			failedTCCount += failedMethods.size();

			// passed tests
			IResultMap passedTests = testContext.getPassedTests();
			Collection<ITestNGMethod> passedMethods = passedTests.getAllMethods();
			passedTCCount += passedMethods.size();

			// skipped tests
			IResultMap skippedTests = testContext.getSkippedTests();
			Collection<ITestNGMethod> skippedMethods = skippedTests.getAllMethods();
			skippedTCCount += skippedMethods.size();

		}
		methodCount[0] = passedTCCount + failedTCCount + skippedTCCount;
		methodCount[1] = passedTCCount;
		methodCount[2] = failedTCCount;
		methodCount[3] = skippedTCCount;

		return methodCount;
	}

	/***
	 * 
	 * To convert the time stamp from data type long to readable format
	 * 
	 * @param timeLength
	 * @return String To Convert the MilliSeconds to the Canonical time
	 */
	protected String convertLongToCanonicalLengthOfTime(long timeLength) {
		if (timeLength >= 86400000) {
			throw new IllegalArgumentException("Duration must be greater than zero or less than 24 hours!");
		}
		String hms = String.format("%02dh:%02dm:%02ds", TimeUnit.MILLISECONDS.toHours(timeLength),
				TimeUnit.MILLISECONDS.toMinutes(timeLength) % TimeUnit.HOURS.toMinutes(1),
				TimeUnit.MILLISECONDS.toSeconds(timeLength) % TimeUnit.MINUTES.toSeconds(1));
		return hms;
	}

	
	/**
	 * 
	 * To create a Test Report archive folder with time stamp
	 * 
	 */
	
	
	protected void archiveTestReports() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm");
		Date date = new Date();
		currentTime = dateFormat.format(date);

		File file = new File(testReportPath + currentTime);
		if (!file.exists()) {
			file.mkdir();
		} else {
			reportLog.error("Test Report folder already exist");
		}
	}

	

	/**
	 * 
	 * To create a Log archive folder with time stamp
	 * 
	 */
	protected void archiveLogs() {

		File file = new File(testReportPath + currentTime + "//Logs//");
		if (!file.exists()) {
			file.mkdir();
			try {
				copyFolder(new File(testLogPath), file, testLogPath);
			} catch (IOException e) {
				System.out.println("Error in Log file copy" + e.getMessage());
			}
		} else {
			System.out.println("Log folder already exist");
		}
		// Delete the Screenshot legacy folder
		try {
			// FileUtils.deleteDirectory(new File(testLogPath));
			deleteFile(new File(testLogPath));
		} catch (IOException e) {
			System.out.println("Error in delete Log legacy folder: " + e.getMessage());
		}
	}

	public static void deleteFile(File file) throws IOException {
		if (file.isDirectory()) {
			// directory is empty, then delete it
			if (file.list().length == 0) {
				file.delete();
				System.out.println("Directory is deleted : " + file.getAbsolutePath());
			} else {
				// list all the directory contents
				String files[] = file.list();

				for (String temp : files) {
					// construct the file structure
					File fileDelete = new File(file, temp);

					// recursive delete
					deleteFile(fileDelete);
				}
				// check the directory again, if empty then delete it
				if (file.list().length == 0) {
					file.delete();
					System.out.println("Directory is deleted : " + file.getAbsolutePath());
				}
			}
		} else {
			// if file, then delete it
			file.delete();
			System.out.println("File is deleted : " + file.getAbsolutePath());
		}
	}

	/**
	 * 
	 * Created a unique folder to display reports in CI
	 * 
	 * 
	 */
	protected void reportForCIDisplay() {
		System.out.println("Inside CI report generation");
		File file = new File(testReportPath + "HTMLReports");
		if (!file.exists()) {
			file.mkdir();
			try {
				copyFolder(new File(testReportPath + currentTime + "//"), file, testReportPath + currentTime + "//");
			} catch (IOException e) {
				reportLog.error("Error in Report copy for CI display" + e.getMessage());
			}
		} else {
			reportLog.info("CI Report folder already exist");
			try {
				FileUtils.deleteDirectory(file);
			} catch (IOException e) {
				reportLog.error("Error in delete HTMLReports folder " + e.getMessage());
			}
			reportForCIDisplay();
		}
	}

	

	

	/**
	 * 
	 * To copy the screenshot files to the archive screenshot folder
	 * 
	 * @param src
	 * @param dest
	 * @throws IOException
	 */
	protected void copyFolder(File src, File dest, String filePath) throws IOException {

		if (src.isDirectory()) {
			// list all the directory contents
			String files[] = src.list();

			for (String file : files) {
				// construct the src and dest file structure
				File srcFile = new File(src, file);
				File destFile = new File(dest, file);
				// recursive copy
				if (!new File(filePath + file).isDirectory()) {
					copyFolder(srcFile, destFile, filePath);
				}
			}
		} else {
			// if file, then copy it
			// Use bytes stream to support all file types
			InputStream in = new FileInputStream(src);
			OutputStream out = new FileOutputStream(dest);

			byte[] buffer = new byte[1024];

			int length;
			// copy the file content in bytes
			while ((length = in.read(buffer)) > 0) {
				out.write(buffer, 0, length);
			}

			in.close();
			out.close();
			// if (Files.getFileExtension(src.toString()).equals("png"))
			// src.delete();
		}
	}
}
