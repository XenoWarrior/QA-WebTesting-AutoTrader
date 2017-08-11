package com.projectge.main;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.IOException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import com.projectge.elements.*;

import com.projectge.utils.HackyTools;
import com.projectge.utils.Screenshot;
import com.projectge.utils.SpreadSheetReader;

/**
 * 
 * AutoTrader Test Project
 * 
 * Requirements:
 * 10 tests: 1 completed.
 * Excel Input Sheet: completed.
 * Results Report: completed.
 * 
 * Issues: 
 * -> One main advanced search is missing due to lack of time to complete the project.
 * -> Eight misc tests are missing due to lack of time to complete the projects.
 * 
 * Output:
 * -> Report will be placed within the root of the project (eclipse) in "Report\Report.html" or under "C:\Report\Report.html" (others, as far as I know)
 * -> TestData.xlsx must be within the root of the project (eclipse) or under "C:\TestData.xlsx" (others, as far as I know)
 * 
 * @author Administrator
 *
 */
public class AppTest {

	/**
	 * WebDriver and Data fetch
	 */
	public static WebDriver webDriver;
	private static SpreadSheetReader sheetReader;
	
	/**
	 * Test reporting and save file path
	 */
	private static ExtentReports reportTests;
	private static String reportFilePath = System.getProperty("user.dir") + File.separatorChar + "Report" + File.separatorChar + "Report.html";
    
	/**
	 * Test objects
	 */
	private static ExtentTest excelFileReaderTest;
	public static ExtentTest advancedSearchTest;
	
	/**
	 * Browser configuration
	 */
	private static List<String> browserConfig = new ArrayList<String>();
	private static List<String> searchValues = new ArrayList<String>();
	
	/**
	 * Initialise the entire test setup
	 */
	@BeforeClass
	public static void beforeClass() {
		// Create new report
        reportTests = new ExtentReports();
        
		
        // Configure new report
        ExtentHtmlReporter extentHtmlReporter = new ExtentHtmlReporter(reportFilePath);
        extentHtmlReporter.config().setReportName("AutoTrader");
        extentHtmlReporter.config().setDocumentTitle("Functionality Tests");
        reportTests.attachReporter(extentHtmlReporter);

        // Add a test to the report
        excelFileReaderTest = reportTests.createTest("Excel Reader Tests");
        advancedSearchTest = reportTests.createTest("Advanced Search");
        
        // Try fetching data from spreadsheet
		try {
			excelFileReaderTest.debug("Reading Excel file: TestData.xlsx");
			sheetReader = new SpreadSheetReader("TestData.xlsx");
			browserConfig = sheetReader.readRow(1, "BrowserConfiguration");
			
			// Debug print the data
			System.out.println(browserConfig.toString());
			System.out.println(browserConfig.size());
			
			// Log to report
			if(browserConfig.size() > 0) {
				excelFileReaderTest.pass("No errors found, got data: " + browserConfig.toString());
			}
			else {
				excelFileReaderTest.fail("No data was fetched from TestData.xlsx");
				fail("No data was fetched from TestData.xlsx");
			}
		}
		catch (IOException e) {
			// No file found most likely
			excelFileReaderTest.fail("Opening TestData.xlsx threw an error: " + e.getMessage());
			reportTests.flush();
			
			fail("Opening TestData.xlsx threw an error: " + e.getMessage());
		}
	}
	
	/**
	 * Initialise specific tests
	 */
	@Before
	public void beforeTest() {
		int bId = (int)Double.parseDouble(browserConfig.get(0));

		// Pick the webdriver for this test
		switch(bId) {
		case 1:
			webDriver = new ChromeDriver();
			//webDriver.manage().window().maximize();
			System.out.println("Using Chrome Driver: " + webDriver.getClass().getName());
			break;
		case 2:
			// This feature has not been worked on fully as there was a short time limit to complete the project.
			// It was not feasible for me to configure my project as there was a lot more going on and possible issues with Firefox itself.
			// Therefore, FireFox will fail on all tests.
			webDriver = new FirefoxDriver();
			System.out.println("Using Firefox Driver: " + webDriver.getClass().getName());
			break;
		default:
			fail("Invalid browser input");
			break;
				
		}
		// Navigate to defined url
		webDriver.navigate().to(browserConfig.get(1));
	}

	@Test
	public void testOneAdvancedSearchAll() {
        // Try fetching data from spreadsheet
		try {
			excelFileReaderTest.debug("Reading Excel file: TestData.xlsx");
			sheetReader = new SpreadSheetReader("TestData.xlsx");
			searchValues = sheetReader.readRow(1, "AdvancedSearchValues");
			
			// Debug print the data
			System.out.println(searchValues.toString());
			System.out.println(searchValues.size());
			
			// Log to report
			if(browserConfig.size() > 0) {
				excelFileReaderTest.pass("No errors found, got data: " + searchValues.toString());
			}
			else {
				excelFileReaderTest.fail("No data was fetched from TestData.xlsx");
				fail("No data was fetched from TestData.xlsx");
			}
		}
		catch (IOException e) {
			// No file found most likely
			excelFileReaderTest.fail("Opening TestData.xlsx threw an error: " + e.getMessage());
			reportTests.flush();
			
			fail("Opening TestData.xlsx threw an error: " + e.getMessage());
		}
		
		NavigationBar navBar = PageFactory.initElements(webDriver, NavigationBar.class);
		navBar.openSearch();
		
		AdvancedSearchForm advSearch = PageFactory.initElements(webDriver, AdvancedSearchForm.class);
		
		advSearch.sendPostcode(searchValues.get(2));
		advSearch.selectDistanceByValue(searchValues.get(3));

		if(searchValues.get(4).equals("no")) {
			advSearch.toggleCheckboxUsed();
		}
		if(searchValues.get(5).equals("no")) {
			advSearch.toggleCheckboxNearlyNew();
		}
		if(searchValues.get(6).equals("no")) {
			advSearch.toggleCheckboxNew();
		}

		advSearch.selectMakeByPartialText(searchValues.get(0));
		advSearch.selectModelByPartialText(searchValues.get(1));

		advSearch.selectMinPriceByValue(searchValues.get(7));
		advSearch.selectMaxPriceByValue(searchValues.get(8));
		
		advSearch.clickSearch();
		
		ResultsNav resultPage = PageFactory.initElements(webDriver, ResultsNav.class);
		
		if(resultPage.getResultsElement().isDisplayed()) {
			advancedSearchTest.pass("Results were returned successfully!");
		}
		else {
			advancedSearchTest.fail("No results found.");
		}
		
		assertTrue("Results view should be shown.", resultPage.getResultsElement().isDisplayed());
	}
	
	@After
	public void afterTest() { 
		webDriver.quit();
	}
	
	@AfterClass
	public static void afterClass() {
		reportTests.flush();
	}
}
