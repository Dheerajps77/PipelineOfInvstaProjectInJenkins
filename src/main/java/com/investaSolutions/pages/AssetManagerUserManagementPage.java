package com.investaSolutions.pages;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.investaSolutions.base.TestBase;
import com.investaSolutions.utils.PropertiesManager;
import com.investaSolutions.utils.SeleniumUtils;

public class AssetManagerUserManagementPage {
	private WebDriver driver;
	public static PropertiesManager properties = PropertiesManager.getInstance();
	
	public AssetManagerUserManagementPage (WebDriver driver) {
		this.driver = driver;
	}

	private static final int WAIT_SECONDS = Integer.valueOf(SeleniumUtils.fetchUserDetails("GLOBAL_WAIT"));
	private static final By ASSET_MANAGER_TAB = By.xpath("//span[contains(text(),'Asset Manager')]");
	private static final By PROFILE_LINK = By.xpath("//span[contains(text(),'Profile')]");
	private static final By USER_MANAGEMENT_LINK = By.xpath("//span[contains(text(),'User Management')]");
	private static final By NEW_USER_BUTTON = By.xpath("//span[contains(text(),'New User')]");
	private static final By FIRSTNAME_TEXTBOX = By.xpath("//div[@class='ui-g-12']//div[1]//div[2]//span[1]//span[1]//input[1]");
	private static final By LASTNAME_TEXTBOX = By.xpath("//div[@class='ui-g-12']//div[1]//div[4]//span[1]//span[1]//input[1]");
	private static final By EMAIL_TEXTBOX = By.xpath("//div[@class='layout-main']//div[2]//div[2]//span[1]//span[1]");
	private static final By PASSWORD_TEXTBOX = By.xpath("//div[@class='ui-g-12 ui-md-6 ui-lg-3 ng-star-inserted']//span[@class='md-inputfield']//span[@class='md-inputfield']");
	private static final By EXPIRATION_DATE_TEXTBOX = By.xpath("//input[@class='ng-tns-c13-86 ui-inputtext ui-widget ui-state-default ui-corner-all ng-star-inserted']");
	private static final By CREATE_NEW_USER_BUTTON = By.xpath("//span[contains(text(),'Create new user')]");
	
	private static final By LIST_OF_USER_ROWS = By.xpath("//tbody[@class='ui-table-tbody']//tr");
	private static final By LIST_OF_USER_COLUMNS = By.xpath("//thead[@class='ui-table-thead']//tr[@class='ng-star-inserted']/th");
	
	public void clickAssetManagerTab() {
		SeleniumUtils.waitForElementVisibility(driver, ASSET_MANAGER_TAB, WAIT_SECONDS).click();
	}

	public void clickProfileLink() {
		SeleniumUtils.waitForElementPresence(driver, PROFILE_LINK, WAIT_SECONDS).click();
	}

	public void clickUserManagementLink() {
		SeleniumUtils.waitForElementVisibility(driver, USER_MANAGEMENT_LINK, WAIT_SECONDS).click();
	}

	public void clickNewUserButton() {
		SeleniumUtils.waitForElementPresence(driver, NEW_USER_BUTTON, WAIT_SECONDS).click();
	}

	public void enterFirstName(String firstName) {
		SeleniumUtils.clickAndEnterText(driver, FIRSTNAME_TEXTBOX, WAIT_SECONDS, firstName);				
	}

	public void enterLastName(String lastName) {
		SeleniumUtils.clickAndEnterText(driver, LASTNAME_TEXTBOX, WAIT_SECONDS, lastName);				
	}

	public void enterEmail(String email) {
		SeleniumUtils.clickAndEnterText(driver, EMAIL_TEXTBOX, WAIT_SECONDS, email);				
	}

	public void enterPassword(String password) {
		SeleniumUtils.clickAndEnterText(driver, PASSWORD_TEXTBOX, WAIT_SECONDS, password);				
	}

	public void clickCreateNewUserButton() {
		SeleniumUtils.waitForElementClickable(driver, CREATE_NEW_USER_BUTTON, WAIT_SECONDS).click();
	}

	
	public void createNewUser(String firstName, String lastName, String email, String password) throws Exception{
		try {
			clickAssetManagerTab();
			clickUserManagementLink();
			clickNewUserButton();
			TestBase.logInfo(String.format(properties.getLogMessage("NewUserData"), firstName, lastName, email, password));
			enterFirstName(firstName);
			enterLastName(lastName);
			enterEmail(email);
			enterPassword(password);
			SeleniumUtils.actionScrollToBottom(driver);
			SeleniumUtils.clickElement(driver, CREATE_NEW_USER_BUTTON);			
		} catch (Exception e) {
			throw e;
		}		
	}
	
	public ArrayList<String> getUsersList(){
		ArrayList<String> listOfUsers = new ArrayList<String>();
		SeleniumUtils.waitForElementVisibility(driver, NEW_USER_BUTTON, WAIT_SECONDS);
		int countOfUserRows = SeleniumUtils.getCountOfWebElements(driver, LIST_OF_USER_ROWS);
		int countOfUserColumns = SeleniumUtils.getCountOfWebElements(driver, LIST_OF_USER_COLUMNS);
		for(int i=1;i<=countOfUserRows;i++){
			String finalText = "";
			for(int j=3;j<=countOfUserColumns-2;j++){
				String columnText = driver.findElement(By.xpath("//tbody[@class='ui-table-tbody']//tr[" + i + "]/td[" + j + "]")).getText();
				if(j==3){
					finalText = columnText;
				}
				else{
					finalText = finalText + " || " + columnText;
				}
			}
			listOfUsers.add(finalText);
		}
		return listOfUsers;
	}
	
	public boolean verifyUserCreation(String firstName, String lastName, String email, String password) throws Exception{
		try {
			createNewUser(firstName, lastName, email, password);
			String expectedUserDetails;
			ArrayList<String> listOfUSerDetails = getUsersList();
			expectedUserDetails = firstName + " || " + lastName + " || " + email;
			if(listOfUSerDetails.contains(expectedUserDetails)){
				return true;
			}
			else{
				return false;
			}
		} catch (Exception e) {
			throw e;
		}
	}
}
