package com.selenium1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class FeedbackFormTest {

    public static void main(String[] args) throws InterruptedException {

        // OPTIONAL (only if needed)
        // System.setProperty("webdriver.chrome.driver", "C:\\path\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        try {

            driver.get("http://127.0.0.1:5500/index.html");
            driver.manage().window().maximize();

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

            // ==============================
            // TEST CASE 1: Page Load
            // ==============================
            if (driver.getTitle().contains("Student Feedback Form")) {
                System.out.println("Page Loaded Successfully");
            } else {
                System.out.println("Page Load Failed");
            }

            // ==============================
            // TEST CASE 2: Valid Submission
            // ==============================
            driver.findElement(By.id("name")).sendKeys("Ravi Kumar");
            driver.findElement(By.id("email")).sendKeys("ravi@gmail.com");
            driver.findElement(By.id("mobile")).sendKeys("9876543210");

            driver.findElement(By.id("department")).sendKeys("Computer Science");
            driver.findElement(By.xpath("//input[@value='Male']")).click();

            driver.findElement(By.id("comments")).sendKeys(
                "This system is very useful and helps students to provide proper feedback easily and efficiently"
            );

            driver.findElement(By.xpath("//button[text()='Submit']")).click();

            // Wait for success message
            String success = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.id("successMessage"))
            ).getText();

            System.out.println("Success Message: " + success);

            if (success.contains("successfully")) {
                System.out.println("Valid Submission Passed");
            } else {
                System.out.println("Valid Submission Failed");
            }

            // ==============================
            // TEST CASE 3: Empty Fields
            // ==============================
            driver.navigate().refresh();

            driver.findElement(By.xpath("//button[text()='Submit']")).click();

            String nameError = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.id("nameError"))
            ).getText();

            if (!nameError.isEmpty()) {
                System.out.println("Empty Field Validation Passed");
            } else {
                System.out.println("Empty Field Validation Failed");
            }

            // ==============================
            // TEST CASE 4: Invalid Email
            // ==============================
            driver.navigate().refresh();

            driver.findElement(By.id("email")).sendKeys("invalidemail");
            driver.findElement(By.xpath("//button[text()='Submit']")).click();

            String emailError = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.id("emailError"))
            ).getText();

            if (!emailError.isEmpty()) {
                System.out.println("Email Validation Passed");
            } else {
                System.out.println("Email Validation Failed");
            }

            // ==============================
            // TEST CASE 5: Invalid Mobile
            // ==============================
            driver.navigate().refresh();

            driver.findElement(By.id("mobile")).sendKeys("123");
            driver.findElement(By.xpath("//button[text()='Submit']")).click();

            String mobileError = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.id("mobileError"))
            ).getText();

            if (!mobileError.isEmpty()) {
                System.out.println("Mobile Validation Passed");
            } else {
                System.out.println("Mobile Validation Failed");
            }

            // ==============================
            // TEST CASE 6: Dropdown Selection
            // ==============================
            driver.navigate().refresh();

            driver.findElement(By.id("department")).sendKeys("IT");

            String deptValue = driver.findElement(By.id("department")).getAttribute("value");

            if (deptValue.equals("IT")) {
                System.out.println("Dropdown Working");
            } else {
                System.out.println("Dropdown Failed");
            }

            // ==============================
            // TEST CASE 7: Reset Button
            // ==============================
            driver.findElement(By.id("name")).sendKeys("Test User");

            driver.findElement(By.xpath("//button[text()='Reset']")).click();

            String nameValue = driver.findElement(By.id("name")).getAttribute("value");

            if (nameValue.isEmpty()) {
                System.out.println("Reset Button Working");
            } else {
                System.out.println(" Reset Button Failed");
            }

            System.out.println("ALL TEST CASES EXECUTED");

        } catch (Exception e) {

            System.out.println("TEST FAILED");
            e.printStackTrace();

        } finally {

            Thread.sleep(2000);
            driver.quit();
        }
    }
}