package com.investaSolutions.tests;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.investaSolutions.base.TestBase;
import com.investaSolutions.pages.VerifyPortfolioModelApprovalPage;
import com.investaSolutions.utils.AllureLogUtil;
import com.investaSolutions.utils.UserFunctions;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

public class VerifyPortfolioModelApprovalTest extends TestBase {

	@Test(priority = 2, description = "Verifying portfolio model approval page details test")
	@Severity(SeverityLevel.BLOCKER)
	@Description("Test Case Description: Verifying portfolio model approval page details test")
	@Story("Story Name: To check pm-approval page details")
	public void testPortfolioModelApprovalTest(Method method) throws Throwable {
		String portfolioModelNameColumnLabelText = "";
		String bankColumnLabelText = "";
		String bankPermissionTitleText = "";
		String portfolioModelApprovalTabTitleText = "";
		String revokedByBankIconText = "";
		String notSubmittedIconText = "";
		String submittedToBankIconText = "";
		String approvedByBankIconText = "";
		String cancelledByAssetManagerIconText = "";
		String rejectedByBankIconText = "";
		boolean flagPortfolioModelApprovalTabAndDetails;
		VerifyPortfolioModelApprovalPage objApprovalPage = null;
		try {
			objApprovalPage = new VerifyPortfolioModelApprovalPage(driver);
			portfolioModelNameColumnLabelText = testData.get("TC_5").get("PORTFOLIOMODELNAME");
			bankColumnLabelText = testData.get("TC_5").get("BANK");
			bankPermissionTitleText = testData.get("TC_5").get("BANKPERMISSIONS");
			portfolioModelApprovalTabTitleText = testData.get("TC_5").get("PORTFOLIOMODELAPPROVALTABTITLE");
			revokedByBankIconText = testData.get("TC_5").get("REVOKEDBYBANK");
			notSubmittedIconText = testData.get("TC_5").get("NOTSUBMITTED");
			submittedToBankIconText = testData.get("TC_5").get("SUBMITTEDTOBANK");
			approvedByBankIconText = testData.get("TC_5").get("APPROVEDBYBANK");
			cancelledByAssetManagerIconText = testData.get("TC_5").get("CANCELLEDBYASSETMANAGER");
			rejectedByBankIconText = testData.get("TC_5").get("REJECTEDBYBANK");
			AllureLogUtil.StartLog(method.getName() + " test method has been started");						
			startTest(properties.getLogMessage("PortfolioModelApprovalTitle"),
					properties.getLogMessage("PortfolioModelApprovalDetail"));
			setTestCategory(properties.getLogMessage("PortfolioModelApprovalDetail"));
			UserFunctions userFunctions = new UserFunctions(driver);
			userFunctions.loginUser(properties.getConstant("AssetManager"), properties.getConstant("AM_User1"));
			flagPortfolioModelApprovalTabAndDetails = objApprovalPage.VerifyPortfolioModelApprovalTabAndDetails(portfolioModelApprovalTabTitleText,
					portfolioModelNameColumnLabelText, bankColumnLabelText, bankPermissionTitleText,
					notSubmittedIconText, submittedToBankIconText, approvedByBankIconText,
					cancelledByAssetManagerIconText, revokedByBankIconText, rejectedByBankIconText);
			Assert.assertTrue(flagPortfolioModelApprovalTabAndDetails);

		} catch (Exception e) {
			logError(properties.getLogMessage("VerifyPortfolioModelApprovalPageDetailsPTestFailed"));
			AllureLogUtil.EndLog(properties.getLogMessage("VerifyPortfolioModelApprovalPageDetailsPTestFailed"));
			e.printStackTrace();
			logError(properties.getLogMessage(e.getMessage()));			
			throw e;
		}
	}
}
