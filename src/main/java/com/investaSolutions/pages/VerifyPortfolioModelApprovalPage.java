package com.investaSolutions.pages;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.investaSolutions.base.TestBase;
import com.investaSolutions.utils.GenericUtils;
import com.investaSolutions.utils.SeleniumUtils;

import io.qameta.allure.Step;

public class VerifyPortfolioModelApprovalPage {

	WebDriver driver;

	public VerifyPortfolioModelApprovalPage(WebDriver driver) {
		this.driver = driver;
	}

	public final By portfolioModelApprovalTab = By.xpath(
			"//div[@class='nano-content menu-scroll-content']//ul//li//a//span[text()='Portfolio Model Approval ']");
	public final By table_Column_Values = By
			.xpath("//div[@class='ui-table-wrapper ng-star-inserted']//table//thead//tr//th");
	public final By statusIconLabelName = By.xpath(
			"//div[@class='layout-content']//child::div[@class='ui-table-wrapper ng-star-inserted']//following::div[1]//span/text()");
	public final By pm_ApprovalBankPermissionTitle = By.xpath("//div[@class='layout-content']//legend");
	public final By statusIcon=By.xpath("//div[@class='layout-content']//child::div[@class='ui-table-wrapper ng-star-inserted']//following::div[1]//span");

	@Step("Verify the title of portfolio model approval Tab")
	public boolean VerifyPortfolioModelApprovalTabTitle(String portfolioModelApprovalTabLabelText) {
		SeleniumUtils objSeleniumUtils = null;
		String getTitleOfPMTab = "";
		boolean flag = false;
		try {

			objSeleniumUtils = new SeleniumUtils();
			getTitleOfPMTab = objSeleniumUtils.getTitleText(driver, portfolioModelApprovalTab);
			WebElement portfolioModelApprovalElement = driver.findElement(portfolioModelApprovalTab);
			if (portfolioModelApprovalElement.isDisplayed()
					&& getTitleOfPMTab.equalsIgnoreCase(portfolioModelApprovalTabLabelText.trim())) {
				flag = true;
				TestBase.logInfo(
						String.format(TestBase.properties.getLogMessage("VerifyPortfolioModelApprovalTabTitlePassed"),
								portfolioModelApprovalTabLabelText, getTitleOfPMTab));
			}

		} catch (Exception e) {
			TestBase.logError(
					String.format(TestBase.properties.getLogMessage("VerifyPortfolioModelApprovalTabTitleFailed"),
							portfolioModelApprovalTabLabelText, getTitleOfPMTab));
			throw e;
		}
		return flag;
	}

	@Step("Click on portfolio model approval tab")
	public void ClickOnPortfolioModelApprovalTab() {
		try {

			SeleniumUtils.waitForElementClickable(driver, portfolioModelApprovalTab, 25).click();
			TestBase.logInfo(String.format(TestBase.properties.getLogMessage("PortfolioModelApprovalTabClicked")));

		} catch (Exception e) {
			throw e;
		}
	}

	@Step("Verify the portfolio model approval bank permission title on the page")
	public boolean VerifyPortfolioModelApprovalBankPermissionsTitle(String portfolioModelApprovalBankPermissionTitle) {
		boolean flag = false;
		SeleniumUtils objSeleniumUtils = null;
		WebElement element = null;
		String titleOfBankPermission = "";
		try {
			objSeleniumUtils = new SeleniumUtils();
			element = driver.findElement(pm_ApprovalBankPermissionTitle);
			titleOfBankPermission = objSeleniumUtils.getTitleText(driver, pm_ApprovalBankPermissionTitle);

			if (element.isDisplayed()
					&& titleOfBankPermission.equalsIgnoreCase(portfolioModelApprovalBankPermissionTitle.trim())) {
				flag = true;
				TestBase.logInfo(String.format(
						TestBase.properties.getLogMessage("VerifyPortfolioModelListBankPermissionsTitlePassed"),
						portfolioModelApprovalBankPermissionTitle, titleOfBankPermission));
			}

		} catch (Exception e) {
			TestBase.logError(String.format(
					TestBase.properties.getLogMessage("VerifyPortfolioModelListBankPermissionsTitleFailed"),
					portfolioModelApprovalBankPermissionTitle, titleOfBankPermission));
			throw e;
		}
		return flag;
	}

	@Step("Verify the portfolio model approval tab and details on the page")
	public boolean VerifyPortfolioModelApprovalTabAndDetails(String portfolioModelApprovalTabLabelText,
			String portfolioModelName, String bankName, String portfolioModelApprovalBankPermissionTitle,
			String notSubmittedIconText, String submittedToBankIconText, String approvedByBankIconText,
			String cancelledByAssetManagerIconText, String revokedByBankIconText, String rejectedByBankIconText) throws Exception {
		boolean flag = false;
		try {

			if (VerifyPortfolioModelApprovalTabTitle(portfolioModelApprovalTabLabelText)) {
				ClickOnPortfolioModelApprovalTab();
				if (VerifyPortfolioModelApprovalBankPermissionsTitle(portfolioModelApprovalBankPermissionTitle)) {
					Thread.sleep(2000);
					if (VerifyPortfolioModelApprovalDetails(portfolioModelName, bankName)) {
						if (VerifyStatusIconImageText(notSubmittedIconText, submittedToBankIconText,
								approvedByBankIconText, cancelledByAssetManagerIconText, revokedByBankIconText,
								rejectedByBankIconText)) {
							flag = true;
						}
					}
				}
			}

		} catch (Exception e) {
			throw e;
		}
		return flag;
	}

	public boolean VerifyPortfolioModelApprovalDetails(String portfolioModelName, String bankName) {
		boolean flag = false;
		ArrayList<String> arrayList = null;
		GenericUtils objGenericUtils = null;
		String tableListValue = "";
		try {
			objGenericUtils = new GenericUtils();
			arrayList = objGenericUtils.TableColumnNameValues1(driver, table_Column_Values);
			Iterator<String> strIterator = arrayList.iterator();
			while (strIterator.hasNext()) {
				String str = strIterator.next();
				tableListValue = tableListValue + ", " + str;
			}
			if (arrayList.contains(portfolioModelName) && arrayList.contains(bankName)) {
				flag = true;
				TestBase.logInfo(String.format(
						TestBase.properties.getLogMessage("VerifyPortfolioModelApprovalPageDetailsPassed"),
						portfolioModelName, bankName, tableListValue));
			}
		} catch (Exception e) {
			TestBase.logError(
					String.format(TestBase.properties.getLogMessage("VerifyPortfolioModelApprovalPageDetailsFailed"),
							portfolioModelName, bankName, tableListValue));
			throw e;
		}
		return flag;
	}

	// Below method will return the column name in the arraylist
	public ArrayList<String> TableColumnNameValuesPortalfolioModelApproval() {

		ArrayList<String> arrayList = null;
		try {
			arrayList = new ArrayList<String>();
			List<WebElement> element = driver.findElements(statusIconLabelName);
			int totalNumberOfColumn = element.size();
			for (int i = 1; i < totalNumberOfColumn; i++) {
				WebElement elementOfColumnText = driver.findElement(By.xpath(
						"//div[@class='layout-content']//child::div[@class='ui-table-wrapper ng-star-inserted']//following::div[1]//span//text()["
								+ i + "]"));
				String stringValueOfElement = elementOfColumnText.getText();
				arrayList.add(stringValueOfElement);
			}
		} catch (Exception e) {
			throw e;
		}
		return arrayList;
	}

	public boolean VerifyStatusIconImageText(String notSubmittedIconText, String submittedToBankIconText,
			String approvedByBankIconText, String cancelledByAssetManagerIconText, String revokedByBankIconText,
			String rejectedByBankIconText) {
		boolean flag = false;
		String statusValue="";
		//WebElement statusElement=null;
		try {			
			statusValue=SeleniumUtils.waitForElementPresence(driver, statusIcon, 40).getText();						
			if (statusValue.contains(notSubmittedIconText) && statusValue.contains(submittedToBankIconText)
					&& statusValue.contains(approvedByBankIconText) && statusValue.contains(cancelledByAssetManagerIconText)
					&& statusValue.contains(revokedByBankIconText) && statusValue.contains(rejectedByBankIconText)) {
				flag = true;
				TestBase.logInfo(String.format(TestBase.properties.getLogMessage("VerifyStatusIconTestPassed"),
						notSubmittedIconText, submittedToBankIconText, approvedByBankIconText,
						cancelledByAssetManagerIconText, revokedByBankIconText, rejectedByBankIconText, statusValue));
			}
		} catch (Exception e) {
			TestBase.logError(String.format(TestBase.properties.getLogMessage("VerifyStatusIconTestFailed"),
					notSubmittedIconText, submittedToBankIconText, approvedByBankIconText,
					cancelledByAssetManagerIconText, revokedByBankIconText, rejectedByBankIconText, statusValue));
			throw e;
		}
		return flag;
	}
}
