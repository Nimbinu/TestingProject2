package com.example.tests.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.example.tests.base.BaseTest;
import com.example.tests.data.CredentialData;
import com.example.tests.pages.LoginPage;
import com.example.tests.pages.InventoryPage;

public class LoginTest extends BaseTest {

    @Test(dataProvider = "loginCreds", dataProviderClass = CredentialData.class)
    public void loginWithDifferentUsers(String user, String pass, boolean shouldSucceed) {
        // Create the login page object
        LoginPage login = new LoginPage(driver, wait);
        login.open();
        login.login(user, pass);

        // Check whether login should succeed or fail
        if (shouldSucceed) {
            // Expect the inventory page to load successfully
            Assert.assertTrue(new InventoryPage(driver, wait).isLoaded(),
                    "✅ Inventory page should be visible for user: " + user);
        } else {
            // Expect an error message to appear
            Assert.assertTrue(login.getErrorMessage().length() > 0,
                    "❌ Error message should appear for locked/invalid user: " + user);
        }
    }
}
