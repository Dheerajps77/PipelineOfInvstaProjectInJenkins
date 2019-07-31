package com.investaSolutions.tests;

import java.lang.reflect.Method;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.investaSolutions.base.TestBase;
import com.investaSolutions.pages.VerifyPortfolioModelManagementPage;
import com.investaSolutions.utils.AllureLogUtil;
import com.investaSolutions.utils.UserFunctions;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

public class VerifyPortfolioModelManagementTest extends TestBase {

	SoftAssert softAssert;

	@Test(priority = 1, description = "Verifying portfolio model management page details test")
	@Severity(SeverityLevel.BLOCKER)
	@Description("Test Case Description: Verifying portfolio model management page details test")
	@Story("Story Name: To check pm_management page details")
	public void testPortfolioModelManagementTest(Method method) throws Throwable {

		try {
			String portfolioManagementTabText = testData.get("TC_4").get("PM_MANAGEMENTTITLE");
			String portfolioModelListTitleText = testData.get("TC_4").get("PORTFOLIOMODELLIST");
			String dColumnLabelText = testData.get("TC_4").get("D_COLUMNNAME");
			String cColumnLabelText = testData.get("TC_4").get("C_COLUMNNAME");
			String modelNameLabelText = testData.get("TC_4").get("MODELNAME_COLUMNNAME");
			String currencyLabelText = testData.get("TC_4").get("CURRENCY_COLUMNNAME");
			String contactDetailsLabelText = testData.get("TC_4").get("CONTACTDETAILS_COLUMNNAME");
			String modelStyleLabelText = testData.get("TC_4").get("MODELSTYLE_COLUMNNAME");
			String openCloseLabelText = testData.get("TC_4").get("OPENCLOSE_COLUMNNAME");
			String lastUpdatedLabelText = testData.get("TC_4").get("LASTUPDATED_COLUMNNAME");

			AllureLogUtil.StartLog(method.getName() + " test method has been started");			
			boolean titleOf_ModelListFlag;
			startTest(properties.getLogMessage("PortfolioManagementTitle"),
					properties.getLogMessage("PortfolioManagementDetail"));
			setTestCategory(properties.getLogMessage("PortfolioManagementDetail"));
			UserFunctions userFunctions = new UserFunctions(driver);
			userFunctions.loginUser(properties.getConstant("AssetManager"), properties.getConstant("AM_User1"));
			logInfo(properties.getLogMessage("LoginTestPassed"));
			VerifyPortfolioModelManagementPage objVerifyPortfolioModelManagementPageDetails = new VerifyPortfolioModelManagementPage(
					driver);
			boolean flag = objVerifyPortfolioModelManagementPageDetails.VerifyProtfolioManagementTabAndDetailsInPage(portfolioManagementTabText, dColumnLabelText, cColumnLabelText, modelNameLabelText,
					currencyLabelText, contactDetailsLabelText, modelStyleLabelText, openCloseLabelText,
					lastUpdatedLabelText);			
			Assert.assertTrue(flag, properties.getLogMessage("VerifyPortfolioManagementPageDetailsTestPassed"));			
					
			titleOf_ModelListFlag = objVerifyPortfolioModelManagementPageDetails.VerifyPortfolioModelListTitle(portfolioModelListTitleText.trim());			
			Assert.assertTrue(titleOf_ModelListFlag); /*properties.getLogMessage("VerifyPortfolioModelListTitlePassed")*/
			
			
			boolean backButton=objVerifyPortfolioModelManagementPageDetails.VerifyBackButton();
			Assert.assertTrue(backButton, properties.getLogMessage("VerifyBackButton"));
			
			boolean newPortfolioButton=objVerifyPortfolioModelManagementPageDetails.VerifyNewPortfolioButton();
			Assert.assertTrue(newPortfolioButton, properties.getLogMessage("VerifyNewPortfolioButton"));

								
		} catch (Throwable e) {
			logError(properties.getLogMessage("VerifyPortfolioManagementPageDetailsPTestFailed"));
			AllureLogUtil.EndLog(properties.getLogMessage("VerifyPortfolioManagementPageDetailsPTestFailed"));
			e.printStackTrace();
			logError(properties.getLogMessage(e.getMessage()));
			throw e;
		}		
	}

}
