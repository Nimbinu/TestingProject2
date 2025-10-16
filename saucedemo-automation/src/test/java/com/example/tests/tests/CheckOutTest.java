package com.example.tests.tests;

import com.example.tests.base.BaseTest;
import com.example.tests.pages.CartPage;
import com.example.tests.pages.CheckoutPage;
import com.example.tests.pages.InventoryPage;
import com.example.tests.pages.LoginPage;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CheckOutTest extends BaseTest {

    @Test
    public void checkoutFlowTest() throws Exception {
        // 1. Login
        LoginPage login = new LoginPage(driver, wait);
        login.open();
        login.login("standard_user", "secret_sauce");

        // 2. Add first item to cart
        InventoryPage inventory = new InventoryPage(driver, wait);
        Assert.assertTrue(inventory.isLoaded(), "Inventory page must be loaded");
        inventory.addFirstItemToCart();
        inventory.openCart();

        // 3. Go to Cart page
        CartPage cart = new CartPage(driver, wait);
        Assert.assertTrue(cart.isCartLoaded(), "Cart page must be loaded");
        Assert.assertTrue(cart.countCartItems() >= 1, "There should be at least 1 item in the cart");

        // 4. Proceed to Checkout
        cart.clickCheckout();

        // 5. Create CheckoutPage and ensure step-one is loaded
        CheckoutPage checkout = new CheckoutPage(driver, wait);

        // DEBUG: if checkout isn't loaded, capture screenshot + URL
        if (!checkout.isCheckoutStepOneLoaded()) {
            // print URL
            System.out.println("DEBUG: URL after clickCheckout(): " + driver.getCurrentUrl());

            // take screenshot to project/test-output/debug/
            try {
                File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                String ts = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
                Path folder = Path.of("test-output", "debug");
                Files.createDirectories(folder);
                Path target = folder.resolve("checkout_not_loaded_" + ts + ".png");
                Files.copy(src.toPath(), target);
                System.out.println("DEBUG: screenshot saved to " + target.toAbsolutePath());
            } catch (Exception e) {
                System.out.println("DEBUG: failed to save screenshot: " + e.getMessage());
            }

            // fail with clear message
            Assert.fail("Checkout step-one did not load after clicking checkout. See debug screenshot and URL above.");
        }

        // 6. Fill Checkout info and continue
        checkout.fillInformation("John", "Doe", "12345");

        // 7. Finish and verify completion page
        checkout.clickFinish();
        Assert.assertTrue(checkout.isCompletePageDisplayed(), "Checkout completion page should be visible");
    }
}
