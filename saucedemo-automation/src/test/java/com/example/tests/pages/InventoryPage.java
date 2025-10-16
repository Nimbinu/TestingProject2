// src/test/java/com/example/tests/pages/InventoryPage.java
package com.example.tests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.stream.Collectors;

public class InventoryPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By inventoryContainer = By.id("inventory_container");
    private By itemNames = By.className("inventory_item_name");
    private By addToCartButtons = By.cssSelector(".inventory_list button");

    public InventoryPage(WebDriver driver, WebDriverWait wait){
        this.driver = driver;
        this.wait = wait;
    }

    public boolean isLoaded(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(inventoryContainer)).isDisplayed();
    }

    public void addFirstItemToCart(){
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButtons)).click();
    }

    public List<String> getAllItemNames(){
        List<WebElement> items = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(itemNames));
        return items.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public void openCart(){
        driver.findElement(By.cssSelector(".shopping_cart_link")).click();
    }
}

