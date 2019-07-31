package com.investaSolutions.base;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.investaSolutions.utils.AllureLogUtil;
import com.investaSolutions.utils.ExcelUtils;
import com.investaSolutions.utils.GenericUtils;
import com.investaSolutions.utils.PropertiesManager;
import com.investaSolutions.utils.SeleniumUtils;
import com.investaSolutions.utils.SendEmailUtils;
import com.investaSolutions.utils.UserFunctions;

public class TestBase {

	public static WebDriver driver;
	public static WebDriverWait wait;
	public static Capabilities cap;
	public static Logger log;
	public static HashMap<String, HashMap<String, String>> testData;
	public static PropertiesManager properties = PropertiesManager.getInstance();
	String hostName = properties.getConfig("HOST_NAME");
	String portID = properties.getConfig("PORT_ID");
	String senderEmail = properties.getConfig("SENDER_EMAIL_ID");
	String senderPassword = properties.getConfig("SENDER_EMAIL_PASSWORD");
	String receiverEmail = properties.getConfig("RECEIVER_EMAIL_ID");
	String ccEmail = properties.getConfig("CC_EMAIL_ID");
	private static final int WAIT_SECONDS = Integer.valueOf(properties.getConfig("GLOBAL_WAIT"));

	String[] receiverEmailArray = receiverEmail.split(";");
	String[] ccEmailArray = ccEmail.split(";");

	static String reportPath = System.getProperty("user.dir") + "/Execution Reports/HTML Report/INVESTA_QA_AUTOMATION_REPORT.html";

	public static ExtentReports extent;
	public static ExtentTest test;

	@BeforeSuite
	@Parameters("browser")
	public static void beforeSuite(String browser) throws MalformedURLException, Exception {
		try {
			testData = ExcelUtils.GetData(System.getProperty("user.dir") + properties.getConfig("TESTDATA_EXCELFILE_PATH"), properties.getConfig("EXCEL_SHEETNAME"));
			log = Logger.getLogger("Logger");
			PropertyConfigurator.configure(System.getProperty("user.dir") + properties.getConfig("LOG4J_PROPERTIES_FILENAME"));
			GenericUtils genericUtil = new GenericUtils();
			genericUtil.cleanUpFolder(System.getProperty("user.dir") + properties.getConfig("FAILEDTEST_SCREENSHOTS_PATH"));
			genericUtil.cleanUpFolder(System.getProperty("user.dir") + properties.getConfig("HTMLREPORT_PATH"));
			extent = createInstance(reportPath);

			AllureLogUtil.Info("Getting OS name");
			log.info("Getting OS name");
			if (System.getProperty("os.name").contains("Linux")) {
				AllureLogUtil.Info("OS is " + System.getProperty("os.name"));
				driver = getLinuxDriver();
				AllureLogUtil.Info("Getting Window name");
				log.info("Getting Windows name");
			} else if (System.getProperty("os.name").contains("Windows")) {
				AllureLogUtil.Info("OS is " + System.getProperty("os.name"));
				driver = getWindowsDriver(browser);
			} else {
				System.out.println("Invalid OS");
			}
			cap = ((RemoteWebDriver) driver).getCapabilities();
			driver.manage().window().setSize(new Dimension(1200, 1100));
			driver.manage().timeouts().implicitlyWait(WAIT_SECONDS, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(WAIT_SECONDS, TimeUnit.SECONDS);
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static WebDriver getLinuxDriver() throws MalformedURLException, Exception {
		new DesiredCapabilities();		
		URL serverurl = new URL("http://localhost:9515");
		AllureLogUtil.Info("Hitting URL  : " + serverurl);
		log.info("Hitting URL" + serverurl);
		log.info("Setting desiredCapabilties for chrome");
		AllureLogUtil.Info("Setting desiredCapabilties for chrome");
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		driver = new RemoteWebDriver(serverurl, capabilities);
		return driver;
	}

	public static WebDriver getWindowsDriver(String browser) throws MalformedURLException, Exception {
		if(browser.equalsIgnoreCase("chrome")){
			AllureLogUtil.Info("Chrome browser invoked");
		System.setProperty("webdriver.chrome.driver", ".\\Resources\\Drivers\\chromedriver.exe");
		DesiredCapabilities caps = DesiredCapabilities.chrome();
		LoggingPreferences logPrefs = new LoggingPreferences();
		logPrefs.enable(LogType.BROWSER, Level.ALL);
		caps.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
		driver = new ChromeDriver(caps);
		}
		else if(browser.equalsIgnoreCase("firefox")){
			AllureLogUtil.Info("Firfox browser invoked");
			System.setProperty("webdriver.gecko.driver", ".\\Resources\\Drivers\\geckodriver.exe");
			//DesiredCapabilities caps = DesiredCapabilities.firefox();
			LoggingPreferences logPrefs = new LoggingPreferences();
			logPrefs.enable(LogType.BROWSER, Level.ALL);
			//caps.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
			driver = new FirefoxDriver();
		}
		else if(browser.equalsIgnoreCase("ie")){
			AllureLogUtil.Info("Internet Explorer browser invoked");
			System.setProperty("webdriver.ie.driver", ".\\Resources\\Drivers\\IEDriverServer.exe");
			DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
			LoggingPreferences logPrefs = new LoggingPreferences();
			logPrefs.enable(LogType.BROWSER, Level.ALL);
			caps.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
			driver = new InternetExplorerDriver(caps);
		}
		else if(browser.equalsIgnoreCase("headless")){	
			AllureLogUtil.Info("Headless chrome browser invoked");
			System.setProperty("webdriver.chrome.driver", ".\\Resources\\Drivers\\chromedriver.exe");
			ChromeOptions option=new ChromeOptions();
			option.setHeadless(true);
			option.addArguments("window-size=1200,1100");
			option.addArguments("--proxy-server='direct://'");
			option.addArguments("--proxy-bypass-list=*");
			LoggingPreferences logPrefs = new LoggingPreferences();
			logPrefs.enable(LogType.BROWSER, Level.ALL);
			driver = new ChromeDriver(option);
		}
		return driver;
	}

	@AfterMethod
	public synchronized void afterMethod(ITestResult result) throws Exception {

		if (result.getStatus() == ITestResult.FAILURE) {
			log.info("Test " + result.getMethod().getMethodName() + " module has been started");
			test.log(Status.FAIL, MarkupHelper
					.createLabel(result.getName() + ": Test Case Failed due to below issues: ", ExtentColor.RED));
			test.fail(result.getThrowable());
			log.info("Test " + result.getMethod().getMethodName() + " module has been failed");
			log.info(result.getThrowable());
			String screenShotPath = SeleniumUtils.getScreenshot(driver, result.getName());
			test.addScreenCaptureFromPath(screenShotPath);
			SeleniumUtils.saveScreenshotPNG(driver);
		} else if (result.getStatus() == ITestResult.SKIP) {
			log.info("Test " + result.getMethod().getMethodName() + " module has been started");
			test.log(Status.SKIP,
					MarkupHelper.createLabel(result.getName() + ": Test Case Skipped: ", ExtentColor.ORANGE));
			log.info("Test " + result.getMethod().getMethodName() + " module has been skipped");
			log.info(result.getThrowable());
			test.skip(result.getThrowable());
		} else {
			test.log(Status.PASS,
					MarkupHelper.createLabel(result.getName() + ": Test Case Passed: ", ExtentColor.GREEN));
			test.pass("Test passed");
		}
		try {
			UserFunctions userFunctions = new UserFunctions(driver);
			userFunctions.LogOut();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		

	}

	@AfterSuite
	public void generateReport() throws Exception {
		extent.setSystemInfo("Browser", cap.getBrowserName());
		extent.setSystemInfo("Browser Version", cap.getVersion());
		extent.flush();

		driver.quit();

		SendEmailUtils.SendEmailNow(hostName, portID, senderEmail, senderPassword, receiverEmailArray, ccEmailArray, 0,
				reportPath);

	}

	public static ExtentReports createInstance(String fileName) {
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		htmlReporter.config().setChartVisibilityOnOpen(true);
		htmlReporter.config().setTheme(Theme.STANDARD);
		htmlReporter.config().setDocumentTitle("INVESTA SOFTWARE AUTOMATION REPORT");
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setReportName("INVESTA-QA AUTOMATION REPORT");

		extent = new ExtentReports();
		extent.setSystemInfo("OS Name:", System.getProperty("os.name"));
		extent.setSystemInfo("OS Version:", System.getProperty("os.version"));
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.attachReporter(htmlReporter);
		return extent;
	}

	public static void startTest(String testName, String description){
		test = extent.createTest(testName, description);
		log.info("Test " + "[" +testName + "]" + " has been started");
	}
	
	public static void setTestCategory(String category){
		test.assignCategory(category);	
	}
	
	public static void logInfo(String message){
		test.info(message);
		log.info(message);
	}
	
	public static void logError(String message){
		test.error(message);
		log.error(message);
	}
}
