package com.example.tests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By checkoutBtn = By.id("checkout");
    private By cartItems = By.className("cart_item");

    public CartPage(WebDriver driver, WebDriverWait wait){
        this.driver = driver;
        this.wait = wait;
    }

    public boolean isCartLoaded(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(checkoutBtn)).isDisplayed();
    }

    /**
     * Click checkout in a robust way:
     *  - wait until clickable
     *  - scroll into view
     *  - try normal click, if it fails use JS click
     */
    public void clickCheckout(){
        WebElement btn = wait.until(ExpectedConditions.visibilityOfElementLocated(checkoutBtn));
        try {
            wait.until(ExpectedConditions.elementToBeClickable(checkoutBtn));
            // scroll into view first
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", btn);
            btn.click();
        } catch (Exception e) {
            // fallback to JS click
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
        }
    }

    public int countCartItems(){
        return driver.findElements(cartItems).size();
    }
}
