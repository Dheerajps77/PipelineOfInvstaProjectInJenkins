package com.investaSolutions.pages;

import org.openqa.selenium.WebDriver;

import com.investaSolutions.utils.SeleniumUtils;

public class AssetManagerDashboardPage {

	private WebDriver driver;
	
	public AssetManagerDashboardPage (WebDriver driver) {
		this.driver = driver;
	}

	private static final int WAIT_SECONDS = Integer.valueOf(SeleniumUtils.fetchUserDetails("GLOBAL_WAIT"));
	
	//public void get
}
