package com.investaSolutions.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.investaSolutions.utils.SeleniumUtils;

public class AssetManagerLoginPage {
	
	private WebDriver driver;

	private  final int WAIT_SECONDS = Integer.valueOf(SeleniumUtils.fetchUserDetails("GLOBAL_WAIT"));
	private  final By USERNAME_TEXT_FIELD = By.xpath("//input[@placeholder='Email']");
	private  final By PASSWORD_TEXT_FIELD = By.xpath("//input[@placeholder='Password']");
	private  final By LOGIN_BUTTON = By.xpath("//button[@class='btn btn-info btn-lg btn-block text-uppercase waves-effect waves-light']");
	private  final By DISMISS_COOKIE = By.xpath("//button[@aria-label='dismiss cookie message']");

	public AssetManagerLoginPage (WebDriver driver) {
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
	
	public void loginUser(String username, String password) throws Exception {
		try {
			typeUserName(username);
			typePassword(password);
			clickLogin();
		}catch (Exception e) {
			throw e;
		}
	}
	
}
