package com.investaSolutions.tests;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.investaSolutions.base.TestBase;
import com.investaSolutions.pages.VerifyInstrumentsPage;
import com.investaSolutions.pages.VerifyPortfolioModelApprovalPage;
import com.investaSolutions.utils.AllureLogUtil;
import com.investaSolutions.utils.UserFunctions;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

public class VerifyInstrumentManagementsTest extends TestBase {

	@Test(priority = 2, description = "Verifying instrument management tab title in the page")
	@Severity(SeverityLevel.BLOCKER)
	@Description("Test Case Description: Verifying instrument management tab details")
	@Story("Story Name: To instrument management tab page details")
	public void testInstrumentManagementTest(Method method) throws Throwable {
		String instrumentManagementTabText = "";
		String instrumentsDropdownValueText = "";
		String benchMarksDropdownValueText = "";
		String instrumentsPageURL = "";
		String instrumentsPageURLFractions = "";
		String instrumentManagementTitleText = "";
		
		String d_ColumnName = "";
		String isin_ColumnName = "";
		String name_ColumnName = "";
		String productType_ColumnName = "";
		String majorAssertClass_ColumnName = "";
		String currency_ColumnName = "";
		String lastPrice_ColumnName = "";
		String lastPriceDate_ColumnName = "";
		String backbtnTextValue="";
		String newInstrumentbtnTextValue="";
		
		boolean flagInstrumentManagementTabAndDetails;
		boolean flagBackButtonInInstrumentManagement;
		boolean flagNewInstrumentButtonInInstrumentManagement;
		boolean flagInstrumentManagementTitle;
		VerifyInstrumentsPage objInstrumentManagementPage = null;
		try {
			objInstrumentManagementPage = new VerifyInstrumentsPage(driver);
			instrumentManagementTabText = testData.get("TC_6").get("INSTRUMENTMANAGEMENT_TAB_TITLE");
			instrumentsDropdownValueText = testData.get("TC_6").get("INSTRUMENTS");
			benchMarksDropdownValueText = testData.get("TC_6").get("BENCHMARKS");
			instrumentsPageURL = testData.get("TC_6").get("INSTRUMENTS_PAGE_URL");
			instrumentsPageURLFractions = testData.get("TC_6").get("INSTRUMENTS_PAGE_FRACTIONS");
			instrumentManagementTitleText = testData.get("TC_6").get("INSTRUMENTMANAGEMENT_TITLE");			
			d_ColumnName = testData.get("TC_6").get("D_COLUMNNAME");
			isin_ColumnName = testData.get("TC_6").get("ISIN_COLUMNNAME");
			name_ColumnName = testData.get("TC_6").get("NAME_COLUMNNAME");
			productType_ColumnName = testData.get("TC_6").get("PRODUCTTYPE_COLUMNNAME");
			majorAssertClass_ColumnName = testData.get("TC_6").get("MAJORASSETCLASS_COLUMNNAME");
			currency_ColumnName = testData.get("TC_6").get("CURRENCY_COLUMNNAME");
			lastPrice_ColumnName = testData.get("TC_6").get("LASTPRICE_COLUMNNAME");
			lastPriceDate_ColumnName = testData.get("TC_6").get("LASTPRICEDATE_COLUMNNAME");			
			backbtnTextValue = testData.get("TC_6").get("BACKBUTTON");
			newInstrumentbtnTextValue = testData.get("TC_6").get("NEW_INSTRUMENTBUTTON");

			AllureLogUtil.StartLog(method.getName() + " test method has been started");
			startTest(properties.getLogMessage("InstrumentManagement"),
					properties.getLogMessage("PortfolioModelApprovalDetail"));
			setTestCategory(properties.getLogMessage("InstrumentManagementTabAndDetail"));
			UserFunctions userFunctions = new UserFunctions(driver);
			userFunctions.loginUser(properties.getConstant("AssetManager"), properties.getConstant("AM_User1"));

			flagInstrumentManagementTabAndDetails=objInstrumentManagementPage.VerifyInstrumentManagementTabAndDropDownValues(instrumentManagementTabText,
					instrumentsDropdownValueText, benchMarksDropdownValueText, instrumentsPageURLFractions,
					instrumentsPageURL);
			Assert.assertTrue(flagInstrumentManagementTabAndDetails, properties.getLogMessage("VerifyInstrumentManagementTabAndDropDownValuesTestPassed"));
			
			flagInstrumentManagementTitle=objInstrumentManagementPage.VerifyInstrumentManagementTitle(instrumentManagementTitleText);
			Assert.assertTrue(flagInstrumentManagementTitle);
			flagBackButtonInInstrumentManagement=objInstrumentManagementPage.VerifyBackButtonInInstrumentManagementPage(backbtnTextValue);
			Assert.assertTrue(flagBackButtonInInstrumentManagement, properties.getLogMessage("VerifyBackButton"));
			
			flagNewInstrumentButtonInInstrumentManagement=objInstrumentManagementPage.VerifyNewInstrumentButtonInInstrumentManagementPage(newInstrumentbtnTextValue);
			Assert.assertTrue(flagNewInstrumentButtonInInstrumentManagement, properties.getLogMessage("VerifyNewInstrumentButton"));

			objInstrumentManagementPage.VerifyRecordsPresentInInstrumentListTable();
		} catch (Exception e) {
			logError(properties.getLogMessage("VerifyInstrumentManagementTabAndDropDownValuesTestFailed"));
			AllureLogUtil.EndLog(properties.getLogMessage("VerifyInstrumentManagementTabAndDropDownValuesTestFailed"));
			e.printStackTrace();
			logError(properties.getLogMessage(e.getMessage()));
			throw e;
		}
	}

}
