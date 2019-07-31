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

public class VerifyInstrumentsPage {

	WebDriver driver;

	public VerifyInstrumentsPage(WebDriver driver) {
		this.driver = driver;
	}

	public final By instrumentManagementTabText = By
			.xpath("//div[@class='nano-content menu-scroll-content']//ul//li//a//span[text()='Instrument Management']");
	public final By instrumentManagementDropDownValues = By
			.xpath("//div[@class='nano-content menu-scroll-content']//li[4]//ul//li//span");
	public final By instrumentDropdownText = By
			.xpath("//div[@class='nano-content menu-scroll-content']//li[4]//ul//li//span[text()='Instruments ']");
	public final By benchmarksDropdownText = By
			.xpath("//div[@class='nano-content menu-scroll-content']//li[4]//ul//li//span[text()='Benchmarks ']");
	public final By instrumentManagementTitleText = By.xpath("//div[@class='layout-content']//legend");
	public final By table_Column_Values = By
			.xpath("//div[@class='ui-table-wrapper ng-star-inserted']//table//thead//tr//th");
	public final By backButton = By.xpath("//button[@label='Back']");
	public final By newInstrumentButton = By.xpath("//button[@label='New Instrument']");
	public WebElement instrumentManagementElement;

	@Step("Verify the Instrument Management Tab title")
	public boolean VerifyInstrumentManagementTabTitle(String instrumentManagementTabTextValue) {
		SeleniumUtils objSeleniumUtils = null;
		String getTitleOfInstrumentManagementFromUI = "";
		boolean flag = false;
		try {

			objSeleniumUtils = new SeleniumUtils();
			getTitleOfInstrumentManagementFromUI = objSeleniumUtils.getTitleText(driver, instrumentManagementTabText);
			instrumentManagementElement = driver.findElement(instrumentManagementTabText);
			SeleniumUtils.waitForElementPresence(driver, instrumentManagementTabText, 30);
			if (instrumentManagementElement.isDisplayed()
					&& getTitleOfInstrumentManagementFromUI.equalsIgnoreCase(instrumentManagementTabTextValue)) {
				flag = true;
				TestBase.logInfo(
						String.format(TestBase.properties.getLogMessage("VerifyInstrumentManagementTabTitlePassed"),
								instrumentManagementTabTextValue, getTitleOfInstrumentManagementFromUI));
			}

		} catch (Exception e) {
			TestBase.logError(
					String.format(TestBase.properties.getLogMessage("VerifyInstrumentManagementTabTitleFailed"),
							instrumentManagementTabTextValue, getTitleOfInstrumentManagementFromUI));
			throw e;
		}
		return flag;
	}

	// This function will return the dropdown text values in array list
	public ArrayList<String> GetInstrumentManagementDropDownValueText() {
		ArrayList<String> arrayList;
		List<WebElement> instrumentDropDownTextElement;
		WebElement element;
		String dropdownTextValue;
		try {
			arrayList = new ArrayList<String>();
			instrumentDropDownTextElement = driver.findElements(instrumentManagementDropDownValues);
			for (int i = 1; i <= instrumentDropDownTextElement.size(); i++) {
				element = driver.findElement(
						By.xpath("//div[@class='nano-content menu-scroll-content']//li[4]//ul//li[" + i + "]//span"));
				dropdownTextValue = element.getText();
				arrayList.add(dropdownTextValue);
			}
		} catch (Exception e) {
			throw e;
		}
		return arrayList;
	}

	@Step("Verify whether Instrument Management Tab is clicked")
	public boolean ClickOnInstrumentManagementTab() {
		boolean flag = false;
		try {

			if (instrumentManagementElement.isDisplayed()) {
				SeleniumUtils.waitForElementClickable(driver, instrumentManagementTabText, 30).click();
				flag = true;
				TestBase.logInfo(String.format(TestBase.properties.getLogMessage("InstrumentManagementTabClicked")));
			}

		} catch (Exception e) {
			TestBase.logError(String.format(TestBase.properties.getLogMessage("InstrumentManagementTabDoNotClicked")));
			throw e;
		}
		return flag;
	}

	@Step("Verify whether instruments dropdown value is clicked")
	public boolean ClickOnInstrumentsDropDownValue() throws Exception {
		boolean flag = false;
		try {
			//Thread.sleep(2000);
			SeleniumUtils.waitForElementClickable(driver, instrumentDropdownText, 30).click();
			flag = true;
			TestBase.logInfo(String.format(TestBase.properties.getLogMessage("InstrumentsDropDownValueClicked")));

		} catch (Exception e) {
			TestBase.logError(String.format(TestBase.properties.getLogMessage("InstrumentsDropDownValueDoNotClicked")));
			throw e;
		}
		return flag;
	}

	@Step("Verify whether Instrument_list page is opened")
	public boolean VerifyInstrumentListPageOpened(String fractionTextValueOfUrl, String expectedPageURL) throws Exception {
		SeleniumUtils objSeleniumUtils;
		String getCurrentPageURL = "";
		boolean flag = false;
		try {
			objSeleniumUtils = new SeleniumUtils();
			Thread.sleep(2000);
			if (ClickOnInstrumentsDropDownValue()) {
				getCurrentPageURL = objSeleniumUtils.GetCurrentPageURL(driver);
				flag = objSeleniumUtils.WaitTillPageURLToBeLoad(driver, fractionTextValueOfUrl, 30);
				if (flag) {
					flag = true;
					TestBase.logInfo(String.format(TestBase.properties.getLogMessage("VerifyInstrumentListPagePassed"),
							expectedPageURL, getCurrentPageURL));
				}
			}

		} catch (Exception e) {
			TestBase.logError(String.format(TestBase.properties.getLogMessage("VerifyInstrumentListPageFailed"),
					expectedPageURL, getCurrentPageURL));
			throw e;
		}
		return flag;
	}

	@Step("Verify the dropdown values of instrument management tab")
	public boolean VerifyDropdownValuesOfInstrumentManagementTab(String instruments, String benchMarks) {
		ArrayList<String> arrayList;
		String dropDownsValue = "";
		String addedDropDownValues = "";
		boolean flag = false;
		try {
			arrayList = GetInstrumentManagementDropDownValueText();
			Iterator<String> iterator = arrayList.iterator();
			while (iterator.hasNext()) {
				dropDownsValue = iterator.next();
				addedDropDownValues = addedDropDownValues + ", " + dropDownsValue;
			}
			if (arrayList.contains(instruments.trim()) && arrayList.contains(benchMarks.trim())) {
				flag = true;
				TestBase.logInfo(String.format(
						TestBase.properties.getLogMessage("VerifyInstrumentManagemenDropdownValuesPassed"), instruments,
						benchMarks, addedDropDownValues));
			}

		} catch (Exception e) {
			TestBase.logError(
					String.format(TestBase.properties.getLogMessage("VerifyInstrumentManagemenDropdownValuesFailed"),
							instruments, benchMarks, addedDropDownValues));
			throw e;
		}
		return flag;
	}

	public boolean VerifyInstrumentManagementTabAndDropDownValues(String instrumentManagementTabTextValue,
			String instruments, String benchMarks, String fractionTextValueOfUrl, String expectedPageURL) throws Exception {
		boolean flag = false;
		try {

			if (VerifyInstrumentManagementTabTitle(instrumentManagementTabTextValue)) {
				if (ClickOnInstrumentManagementTab()) {
					Thread.sleep(2000);
					if (VerifyDropdownValuesOfInstrumentManagementTab(instruments, benchMarks)) {
						if (VerifyInstrumentListPageOpened(fractionTextValueOfUrl, expectedPageURL)) {
							flag = true;
							TestBase.logInfo(String
									.format(TestBase.properties.getLogMessage("VerifyInstrumentListPageOpenPassed")));
						}
					}
				}
			}

		} catch (Exception e) {
			TestBase.logError(String.format(TestBase.properties.getLogMessage("VerifyInstrumentListPageOpenFailed")));
			e.printStackTrace();
			throw e;
		}
		return flag;
	}

	@Step("Verify the Back button of Instrument Management in the instrument_list page")
	public boolean VerifyBackButtonInInstrumentManagementPage(String backbtnTextValue) {
		WebElement backButtonElement;		
		String backButtonText="";		
		boolean flag=false;
		try {
			backButtonElement = driver.findElement(backButton);
			backButtonText = backButtonElement.getText();
			if (backButtonElement.isDisplayed() && backButtonText.equalsIgnoreCase(backbtnTextValue)) 
			{
				flag=true;
				TestBase.logInfo(String.format(TestBase.properties.getLogMessage("VerifyBackButtonPassed"), backButtonText, backbtnTextValue));
			}

		} catch (Exception e) {
			TestBase.logError(String.format(TestBase.properties.getLogMessage("VerifyBackButtonFailed"), backButtonText, backbtnTextValue));
			e.printStackTrace();
			throw e;
		}
		return flag;
	}
	
	@Step("Verify the New Instrument button of Instrument Management in the instrument_list page")
	public boolean VerifyNewInstrumentButtonInInstrumentManagementPage(String newInstrumentbtnTextValue) {
		WebElement newInstrumentElement;		
		String newInstrumentButtonText="";		
		boolean flag=false;
		try {
			newInstrumentElement = driver.findElement(newInstrumentButton);
			newInstrumentButtonText = newInstrumentElement.getText();
			if (newInstrumentElement.isDisplayed() && newInstrumentButtonText.equalsIgnoreCase(newInstrumentbtnTextValue)) 
			{
				flag=true;
				TestBase.logInfo(String.format(TestBase.properties.getLogMessage("VerifyNewInstrumentButtonPassed"), newInstrumentButtonText, newInstrumentbtnTextValue));
			}

		} catch (Exception e) {
			TestBase.logError(String.format(TestBase.properties.getLogMessage("VerifyNewInstrumentButtonFailed"), newInstrumentButtonText, newInstrumentbtnTextValue));
			e.printStackTrace();
			throw e;
		}
		return flag;
	}

	@Step("Verify the column name of Instrument Management in the instrument_list page")
	public boolean VerifyInstrumentManagementTableColumnName(String dColumnName, String isinColumnName,
			String nameColumnName, String productTypeColumnName, String majorAssertClassColumnName,
			String currencyColumnName, String lastPriceColumnName, String lastPriceDateColumnName) {
		boolean flag = false;
		GenericUtils objGenericUtils;
		ArrayList<String> arrayList;
		String columnNameFromUI = "";
		try {
			objGenericUtils = new GenericUtils();
			arrayList = objGenericUtils.TableColumnNameValues(driver, table_Column_Values);
			Iterator<String> iterator = arrayList.iterator();
			while (iterator.hasNext()) {
				String iteratorStringValue = iterator.next();
				columnNameFromUI = columnNameFromUI + ", " + iteratorStringValue;
			}

			if (arrayList.contains(dColumnName) && arrayList.contains(isinColumnName)
					&& arrayList.contains(nameColumnName) && arrayList.contains(productTypeColumnName)
					&& arrayList.contains(majorAssertClassColumnName) && arrayList.contains(currencyColumnName)
					&& arrayList.contains(lastPriceColumnName) && arrayList.contains(lastPriceColumnName)) {
				flag = true;
				TestBase.logInfo(String.format(
						TestBase.properties.getLogMessage("VerifyInstrumentManagementTableColumnNamePassed"),
						dColumnName, isinColumnName, nameColumnName, productTypeColumnName, majorAssertClassColumnName,
						currencyColumnName, lastPriceColumnName, lastPriceDateColumnName));
			}

		} catch (Exception e) {
			TestBase.logError(String.format(
					TestBase.properties.getLogMessage("VerifyInstrumentManagementTableColumnNameFailed"), dColumnName,
					isinColumnName, nameColumnName, productTypeColumnName, majorAssertClassColumnName,
					currencyColumnName, lastPriceColumnName, lastPriceDateColumnName));
			throw e;
		}
		return flag;
	}

	@Step("Verify the title of Instrument Management in the instrument_list page")
	public boolean VerifyInstrumentManagementTitle(String instrumentManagementTitle) throws Exception {
		SeleniumUtils objSeleniumUtils;
		String getTitleOfInstrumentManagement = "";
		WebElement element;
		boolean flag = false;
		try {
			objSeleniumUtils = new SeleniumUtils();
			SeleniumUtils.waitForElementPresence(driver, instrumentManagementTitleText, 30);			
			element = driver.findElement(instrumentManagementTitleText);
			getTitleOfInstrumentManagement = objSeleniumUtils.getTitleText(driver, instrumentManagementTitleText);

			if (element.isDisplayed() && getTitleOfInstrumentManagement.equalsIgnoreCase(instrumentManagementTitle.trim())) {
				flag = true;
				TestBase.logInfo(
						String.format(TestBase.properties.getLogMessage("VerifyInstrumentManagementTitlePassed"),
								instrumentManagementTitle, getTitleOfInstrumentManagement));
			}

		} catch (Exception e) {
			TestBase.logError(String.format(TestBase.properties.getLogMessage("VerifyInstrumentManagementTitleFailed"),
					instrumentManagementTitle, getTitleOfInstrumentManagement));
			e.printStackTrace();
			throw e;
		}
		return flag;
	}
	
	public final By rowDataValues=By.xpath("//div[@class='ui-table ui-widget ui-table-responsive']//table//tbody[@class='ui-table-tbody']//tr");
	public final By cellDataValues=By.xpath("//div[@class='ui-table ui-widget ui-table-responsive']//table//tbody[@class='ui-table-tbody']//tr//td");
	
	@Step("Verify records present in instrument lists table in the instrument_list page")
	public void VerifyRecordsPresentInInstrumentListTable()
	{
		List<WebElement> rowElement=null;
		List<WebElement> cellElement = null;
		ArrayList<String> list=null;
		WebElement cellValuElement=null;
		String cellStringValue="";
		try {
			list=new ArrayList<String>();
			rowElement=driver.findElements(rowDataValues);
			cellElement=driver.findElements(cellDataValues);			
			int rowSize=rowElement.size();
			int cellSize=cellElement.size();
			System.out.println("Total Row present in instrumentTable is : " + rowSize);
			System.out.println("Total cell values present in instrumentTable is : " + cellSize);			
			for(int i=1;i<=rowSize;i++)
			{
				List<WebElement> listOfParticularRowCellValues=driver.findElements(By.xpath("//div[@class='ui-table ui-widget ui-table-responsive']//table//tbody[@class='ui-table-tbody']//tr["+i+"]//td"));
				int totalNoOfCellInParticularRow=listOfParticularRowCellValues.size();
				for(int j=2;j<=totalNoOfCellInParticularRow;j++)
				{
					cellValuElement=driver.findElement(By.xpath("//div[@class='ui-table ui-widget ui-table-responsive']//table//tbody[@class='ui-table-tbody']//tr["+i+"]//td["+j+"]"));
					cellStringValue=cellValuElement.getText();
					System.out.print(cellStringValue + " || ");
					list.add(cellStringValue);
				}
				System.out.println();
			}
			
 		} catch (Exception e) {
			throw e;
		}
	}
}
