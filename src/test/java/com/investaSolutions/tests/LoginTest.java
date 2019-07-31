package com.investaSolutions.tests;

import static org.testng.Assert.assertEquals;

import java.lang.reflect.Method;

import org.testng.SkipException;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.investaSolutions.base.TestBase;
import com.investaSolutions.utils.AllureLogUtil;
import com.investaSolutions.utils.SeleniumUtils;
import com.investaSolutions.utils.UserFunctions;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

public class LoginTest extends TestBase{
	
	SoftAssert softAssert;
	@Test(priority = 0, description = "verifying login page title test")
	@Severity(SeverityLevel.BLOCKER)
	@Description("Test Case Description: Verify login page title test on Login Page")
	@Story("Story Name: To check login page title")
	public void testUserLoginFunctionality(Method method) throws Throwable {
		try {
			AllureLogUtil.StartLog(method.getName() + " test method has been started");
			String title;
			startTest(properties.getLogMessage("LoginVerification"), properties.getLogMessage("LoginVerificationDetail"));
			setTestCategory(properties.getLogMessage("CategoryLogin"));
			UserFunctions userFunctions = new UserFunctions(driver);
			userFunctions.loginUser(properties.getConstant("AssetManager"), properties.getConstant("AM_User1")); 
			// To login to Bank Portal - (properties.getConstant("Bank"), properties.getConstant("Bank_User1"));
			logInfo(properties.getLogMessage("LoginTestPassed"));
			title = SeleniumUtils.getCurrentPageTitle(driver);			
			assertEquals(title, testData.get("TC_1").get("DASHBOARDTITLE"));
			AllureLogUtil.Info("Verify title of the page : " + "Expected title of page is : " + title + " with actual title of page : " + testData.get("TC_1").get("DASHBOARDTITLE"));
			logInfo(String.format(properties.getLogMessage("VerifiedDashboardTitle"), title));
			AllureLogUtil.StartLog(method.getName() + " test method has been completed");
		} catch (Throwable e) {
			logError(properties.getLogMessage("LoginTestFailed"));
			AllureLogUtil.EndLog(properties.getLogMessage("LoginTestFailed"));
			e.printStackTrace();
			throw e;
		}
	}
}
