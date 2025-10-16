package com.example.tests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By firstName = By.id("first-name");
    private By lastName = By.id("last-name");
    private By postalCode = By.id("postal-code");
    private By continueBtn = By.id("continue");
    private By finishBtn = By.id("finish");
    private By completeHeader = By.className("complete-header");

    public CheckoutPage(WebDriver driver, WebDriverWait wait){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15)); // longer wait here
    }

    public boolean isCheckoutStepOneLoaded() {
        try {
            // prefer URL check if possible
            if (driver.getCurrentUrl().contains("checkout-step-one")) {
                wait.until(ExpectedConditions.visibilityOfElementLocated(firstName));
                return true;
            }
            wait.until(ExpectedConditions.visibilityOfElementLocated(firstName));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void fillInformation(String fName, String lName, String pCode){
        // ensure loaded
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstName));
        wait.until(ExpectedConditions.elementToBeClickable(firstName)).clear();
        driver.findElement(firstName).sendKeys(fName);

        wait.until(ExpectedConditions.elementToBeClickable(lastName)).clear();
        driver.findElement(lastName).sendKeys(lName);

        wait.until(ExpectedConditions.elementToBeClickable(postalCode)).clear();
        driver.findElement(postalCode).sendKeys(pCode);

        wait.until(ExpectedConditions.elementToBeClickable(continueBtn)).click();
    }

    public void enterFirstName(String fName) {
        wait.until(ExpectedConditions.elementToBeClickable(firstName)).clear();
        driver.findElement(firstName).sendKeys(fName);
    }

    public void enterLastName(String lName) {
        wait.until(ExpectedConditions.elementToBeClickable(lastName)).clear();
        driver.findElement(lastName).sendKeys(lName);
    }

    public void enterPostalCode(String pCode) {
        wait.until(ExpectedConditions.elementToBeClickable(postalCode)).clear();
        driver.findElement(postalCode).sendKeys(pCode);
    }

    public void clickContinue() {
        wait.until(ExpectedConditions.elementToBeClickable(continueBtn)).click();
    }

    public void clickFinish() {
        wait.until(ExpectedConditions.elementToBeClickable(finishBtn)).click();
    }

    public boolean isCompletePageDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(completeHeader));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
