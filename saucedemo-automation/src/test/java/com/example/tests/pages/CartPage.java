// src/test/java/com/example/tests/pages/CartPage.java
package com.example.tests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By checkoutBtn = By.id("checkout");
    private By cartItems = By.className("cart_item"); // optional if you want to count items

    public CartPage(WebDriver driver, WebDriverWait wait){
        this.driver = driver;
        this.wait = wait;
    }

    // New method: check if cart page loaded
    public boolean isCartLoaded(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(checkoutBtn)).isDisplayed();
    }

    // Existing method
    public void clickCheckout(){
        wait.until(ExpectedConditions.elementToBeClickable(checkoutBtn)).click();
    }

    // Optional: count items in the cart
    public int countCartItems(){
        return driver.findElements(cartItems).size();
    }
}
