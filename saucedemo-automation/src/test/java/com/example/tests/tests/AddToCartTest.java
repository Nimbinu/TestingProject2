// src/test/java/com/example/tests/tests/AddToCartTest.java
package com.example.tests.tests;

import com.example.tests.base.BaseTest;
import com.example.tests.pages.InventoryPage;
import com.example.tests.pages.LoginPage;
import org.testng.annotations.Test;

public class AddToCartTest extends BaseTest {

    @Test
    public void addItemToCart_thenOpenCart(){
        LoginPage login = new LoginPage(driver, wait);
        login.open();
        login.login("standard_user", "secret_sauce");

        InventoryPage inv = new InventoryPage(driver, wait);
        inv.addFirstItemToCart();
        inv.openCart();
        // further assertions can be done by inspecting cart items
    }
}

