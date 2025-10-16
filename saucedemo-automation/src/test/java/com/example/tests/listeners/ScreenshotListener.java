package com.example.tests.listeners;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScreenshotListener implements ITestListener {

    private final String screenshotFolder = "test-output/screenshots";

    @Override
    public void onTestFailure(ITestResult result) {
        ITestContext context = result.getTestContext();
        Object driverObj = context.getAttribute("driver");
        if (driverObj instanceof WebDriver) {
            WebDriver driver = (WebDriver) driverObj;
            try {
                File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                String ts = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
                String testName = result.getName();
                Path targetDir = Path.of(screenshotFolder);
                Files.createDirectories(targetDir);
                Path target = targetDir.resolve(testName + "_" + ts + ".png");
                Files.copy(src.toPath(), target, StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Saved screenshot: " + target.toAbsolutePath());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("WebDriver not found in test context. No screenshot taken.");
        }
    }

    // other ITestListener methods (no-op)
    @Override public void onTestStart(ITestResult result) {}
    @Override public void onTestSuccess(ITestResult result) {}
    @Override public void onTestSkipped(ITestResult result) {}
    @Override public void onTestFailedButWithinSuccessPercentage(ITestResult result) {}
    @Override public void onStart(ITestContext context) {}
    @Override public void onFinish(ITestContext context) {}
}

