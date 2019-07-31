package com.investaSolutions.utils;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.gherkin.model.And;
import com.investaSolutions.base.TestBase;

import io.qameta.allure.Step;

public class UserFunctions {
	
	private WebDriver driver;
	private  final int WAIT_SECONDS = Integer.valueOf(SeleniumUtils.fetchUserDetails("GLOBAL_WAIT"));
	private  final By USERNAME_TEXT_FIELD = By.xpath("//input[@placeholder='Email']");
	private  final By PASSWORD_TEXT_FIELD = By.xpath("//input[@placeholder='Password']");
	private  final By LOGIN_BUTTON = By.xpath("//button[@class='btn btn-info btn-lg btn-block text-uppercase waves-effect waves-light']");
	private  final By LOGOUT_BUTTON= By.xpath("//*[text()='Disconnect ']");
	private final String AM_ENVIRONMENT = TestBase.properties.getConfig("ASSETMANAGER_URL");
	private final String BANK_ENVIRONMENT = TestBase.properties.getConfig("BANK_URL");
	private final String JSONPATH = System.getProperty("user.dir") + TestBase.properties.getConfig("JSON_USERS_PATH");
	
	public UserFunctions (WebDriver driver) {
		this.driver = driver;
	}
	
	public void typeUserName(String username) {
		SeleniumUtils.waitForElementPresence(driver, USERNAME_TEXT_FIELD, WAIT_SECONDS).sendKeys(username);
	}

	public void typePassword(String password) {
		SeleniumUtils.waitForElementPresence(driver, PASSWORD_TEXT_FIELD, WAIT_SECONDS).sendKeys(password);
	}	

	public void clickLogin() {
		SeleniumUtils.waitForElementPresence(driver, LOGIN_BUTTON, WAIT_SECONDS).click();
	}
	
	public void clickLogout() {
		SeleniumUtils.waitForElementVisibility(driver, LOGOUT_BUTTON, WAIT_SECONDS).click();
	}
	
	@Step("Enter below user login details ")
	public void enterDetailsAndLogin(String user) throws Exception{
		try {
			GenericUtils genericUtils=new GenericUtils();
			HashMap<String, String> userDetails = genericUtils.getUserDetailsFromJSON(JSONPATH, user);
			AllureLogUtil.Info("User name  : " + userDetails.get("username") +" and password : " + userDetails.get("password"));
			typeUserName(userDetails.get("username"));
			typePassword(userDetails.get("password"));
			clickLogin();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
		
	public void loginUser(String portal, String user) throws Exception {
		try {
			if(portal.equals(TestBase.properties.getConstant("AssetManager"))){
				AllureLogUtil.Info("portal is : " + portal +" and user : " + user);
				driver.get(AM_ENVIRONMENT);
				enterDetailsAndLogin(user);
			}
			if(portal.equals(TestBase.properties.getConstant("Bank"))){
				AllureLogUtil.Info("portal is : " + portal +" and user : " + user);
				driver.get(BANK_ENVIRONMENT);
				enterDetailsAndLogin(user);
			}
		}catch (Exception e) {
			throw e;
		}
	}
	
    public void LogOut() throws InterruptedException{
    	AllureLogUtil.Info("Log out from the portal");    	
    	clickLogout();
	}
}
