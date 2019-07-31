package com.investaSolutions.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.investaSolutions.base.TestBase;
import com.investaSolutions.pages.AssetManagerUserManagementPage;

public class AssetManagerTest extends TestBase{
	
	@Test(priority=2)
	public void createNewUserTest() throws Throwable{
		try {
			String firstName = testData.get("TC_2").get("FIRSTNAME");
			String lastName = testData.get("TC_2").get("LASTNAME");
			String email = testData.get("TC_2").get("EMAIL");
			String password = testData.get("TC_2").get("PASSWORD");
			startTest(properties.getLogMessage("CreateNewUserTest"), properties.getLogMessage("CreateNewUserDetail"));
			setTestCategory(properties.getLogMessage("CategoryCreateNewUser"));
			AssetManagerUserManagementPage userManagementPage = new AssetManagerUserManagementPage(driver);
			Assert.assertTrue(userManagementPage.verifyUserCreation(firstName, lastName, email, password), String.format(properties.getLogMessage("UserCreationFailed"), firstName));
			logInfo(String.format(properties.getLogMessage("UserCreationPassed"), firstName));
		} catch (Throwable e) {		
			logError(properties.getLogMessage("CreateNewUserFailed"));
			e.printStackTrace();
			throw e;
		}
	}
}
