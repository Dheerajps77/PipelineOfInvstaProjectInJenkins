package com.investaSolutions.pages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.investaSolutions.base.TestBase;
import com.investaSolutions.utils.GenericUtils;
import com.investaSolutions.utils.SeleniumUtils;

import io.qameta.allure.Step;

public class VerifyPortfolioModelManagementPage {

	public WebDriver driver;

	public VerifyPortfolioModelManagementPage(WebDriver driver) {
		this.driver = driver;
	}

	public final By pm_Management_Tab = By.xpath("//div[@class='nano-content menu-scroll-content']//ul//li//a//span[text()='Portfolio Model Management ']");
	public final By pm_List_Title = By.xpath("//div[@class='ui-g']//div[@class='ui-g-12']//legend");
	public final By table_Column_Values = By.xpath("//div[@class='ui-table-wrapper ng-star-inserted']//table//thead//tr//th");
	public final By backButton = By.xpath("//div[@class='ui-g-12 ui-md-3']//button[@label='Back']");
	public final By new_PortfolioButton = By.xpath("//div[@class='ui-g-12 ui-md-3']//button[@label='New Portfolio Model']");

	@Step("Verify the pm_Management_Tab title in the page")
	public boolean VerifyProtfolioManagementTabAndDetailsInPage(String portfolioMangementTabLabelText,
			String dColumnValue, String cColumnValue, String modelNameColumnValue, String currencyColumnValue,
			String contactDetailsColumnValue, String modelStyleColumnValue, String openCloseColumnValue,
			String lastUpdatedColumnValue) {
		boolean flag = false;
		try {
			if (VerifyPortfolioManagementTabTitle(portfolioMangementTabLabelText)) {
				ClickOnPortfolioManagementTab();
				if (VerifyDetailsOnPortfolioPortfolioManagement(dColumnValue, cColumnValue, modelNameColumnValue,
						currencyColumnValue, contactDetailsColumnValue, modelStyleColumnValue, openCloseColumnValue,
						lastUpdatedColumnValue)) {
					flag = true;					
				}
			}
		} catch (Exception e) {			
			throw e;
		}
		return flag;
	}
	
	@Step("Verify the back button")
	public boolean VerifyBackButton()
	{
		SeleniumUtils objSeleniumUtils = null;
		boolean flag;
		try {
			objSeleniumUtils=new SeleniumUtils();
			flag=objSeleniumUtils.IsElementDisplayed(driver, backButton);			
			if(flag)
			{
				TestBase.logInfo(String.format(TestBase.properties.getLogMessage("VerifyBackButtonPassed")));
			}
			
		} catch (Exception e) {
			TestBase.logError(String.format(TestBase.properties.getLogMessage("VerifyBackButtonFailed")));
			throw e;
		}
		return flag;
	}
	
	@Step("Verify the new portfolio button")
	public boolean VerifyNewPortfolioButton()
	{
		SeleniumUtils objSeleniumUtils = null;
		boolean flag;
		try {
			objSeleniumUtils=new SeleniumUtils();
			flag=objSeleniumUtils.IsElementDisplayed(driver, new_PortfolioButton);			
			if(flag)
			{
				TestBase.logInfo(String.format(TestBase.properties.getLogMessage("VerifyNewPortfolioButtonPassed")));
			}
			
		} catch (Exception e) {
			TestBase.logError(String.format(TestBase.properties.getLogMessage("VerifyNewPortfolioButtonFailed")));
			throw e;
		}
		return flag;
	}

	@Step("Below will click on portfolio management tab")
	public void ClickOnPortfolioManagementTab() {
		try {
			SeleniumUtils.waitForElementClickable(driver, pm_Management_Tab, 25).click();
			TestBase.properties.getLogMessage("PortfolioManagementTabClicked");

		} catch (Exception e) {
			throw e;
		}
	}

	@Step("Verify the title of portfolio management Tab")
	public boolean VerifyPortfolioManagementTabTitle(String portfolioMangementTabLabelText) {
		SeleniumUtils objSeleniumUtils = null;
		String getTitleOfPMTab = "";
		boolean flag = false;
		try {

			objSeleniumUtils = new SeleniumUtils();
			getTitleOfPMTab = objSeleniumUtils.getTitleText(driver, pm_Management_Tab);
			WebElement portfolioManElement = driver.findElement(pm_Management_Tab);
			if (portfolioManElement.isDisplayed() && portfolioMangementTabLabelText.equalsIgnoreCase(getTitleOfPMTab)) {
				flag = true;
				TestBase.logInfo(String.format(
						TestBase.properties.getLogMessage("VerifyPortfolioManagementTabTitlePassed"), portfolioMangementTabLabelText, getTitleOfPMTab));
			}

		} catch (Exception e) {
			TestBase.logError(String.format(
					TestBase.properties.getLogMessage("VerifyPortfolioManagementTabTitleFailed"), portfolioMangementTabLabelText, getTitleOfPMTab));
			throw e;
		}
		return flag;
	}

	@Step("Verify details in the portfolio management page")
	public boolean VerifyDetailsOnPortfolioPortfolioManagement(String dColumnValue, String cColumnValue,
			String modelNameColumnValue, String currencyColumnValue, String contactDetailsColumnValue,
			String modelStyleColumnValue, String openCloseColumnValue, String lastUpdatedColumnValue) {
		ArrayList<String> arrayList = null;
		GenericUtils objGenericUtils=new GenericUtils();
		String tableListValue="";
		boolean flag = false;
		try {			
			arrayList = new ArrayList<String>();							
			arrayList=objGenericUtils.TableColumnNameValues(driver, table_Column_Values);
			Iterator<String> strIterator=arrayList.iterator();
			while(strIterator.hasNext())
			{
				String str=strIterator.next();
				tableListValue=tableListValue+ ", " + str;
			}
			if (arrayList.contains(dColumnValue) && arrayList.contains(cColumnValue)
					&& arrayList.contains(modelNameColumnValue) && arrayList.contains(currencyColumnValue)
					&& arrayList.contains(contactDetailsColumnValue) && arrayList.contains(modelStyleColumnValue)
					&& arrayList.contains(openCloseColumnValue) && arrayList.contains(lastUpdatedColumnValue)) {
				flag = true;
				TestBase.logInfo(String.format(
						TestBase.properties.getLogMessage("VerifyPortfolioManagementPageDetailsPassed"),
						dColumnValue, cColumnValue, modelNameColumnValue, currencyColumnValue,
						contactDetailsColumnValue, modelStyleColumnValue, openCloseColumnValue,
						lastUpdatedColumnValue, tableListValue));
			}

		} catch (Exception e) {
			TestBase.logError(String.format(
					TestBase.properties.getLogMessage("VerifyPortfolioManagementPageDetailsFailed"), dColumnValue,
					cColumnValue, modelNameColumnValue, currencyColumnValue, contactDetailsColumnValue,
					modelStyleColumnValue, openCloseColumnValue, lastUpdatedColumnValue, tableListValue));
			throw e;
		}
		return flag;
	}
		

	@Step("Verify the Portfolio Model List title in the page")
	public boolean VerifyPortfolioModelListTitle(String portfolioModelListName) {
		String getTitleOfPMModelList = "";
		SeleniumUtils objSeleniumUtils = null;
		boolean flag = false;
		try {
			objSeleniumUtils = new SeleniumUtils();
			getTitleOfPMModelList = objSeleniumUtils.getTitleText(driver, pm_List_Title);
			WebElement element = driver.findElement(pm_List_Title);
			if (element.isDisplayed() && getTitleOfPMModelList.equalsIgnoreCase(portfolioModelListName)) {
				flag = true;
				TestBase.logInfo(String.format(TestBase.properties.getLogMessage("VerifyPortfolioModelListTitlePassed"),
						portfolioModelListName, getTitleOfPMModelList));
			}
		} catch (Exception e) {
			TestBase.logError(String.format(TestBase.properties.getLogMessage("VerifyPortfolioModelListTitleFailed"),
					portfolioModelListName, getTitleOfPMModelList));
			throw e;
		}
		return flag;
	}
	
	
}
