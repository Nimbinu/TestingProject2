package com.example.tests.data;

import org.testng.annotations.DataProvider;

public class CredentialData {

    // This DataProvider gives 3 sets of login data
    @DataProvider(name = "loginCreds")
    public static Object[][] loginCreds() {
        return new Object[][] {
                {"standard_user", "secret_sauce", true},   // valid user
                {"locked_out_user", "secret_sauce", false}, // locked user
                {"problem_user", "secret_sauce", true}      // another valid user
        };
    }
}
