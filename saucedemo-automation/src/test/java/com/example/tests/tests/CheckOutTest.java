package com.example.tests.tests;
import com.example.tests.pages.CartPage;

import com.example.tests.base.BaseTest;
import com.example.tests.pages.CartPage;
import com.example.tests.pages.CheckoutPage;
import com.example.tests.pages.InventoryPage;
import com.example.tests.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckOutTest extends BaseTest {

    @Test
    public void checkoutFlowTest() {
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

        // 5. Fill Checkout info
        CheckoutPage checkout = new CheckoutPage(driver, wait);
        checkout.fillInformation("John", "Doe", "12345");

        // Optional: Verify order complete page (you can add method in CheckoutPage)
        // Here we can add wait/assert if you added isCompletePageDisplayed()
        // Assert.assertTrue(checkout.isCompletePageDisplayed(), "Checkout complete page should appear");
    }
}

