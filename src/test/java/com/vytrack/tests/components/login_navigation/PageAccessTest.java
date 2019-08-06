package com.vytrack.tests.components.login_navigation;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PageAccessTest {

    WebDriver driver;
    @BeforeMethod
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver =new ChromeDriver();
    }

    @AfterMethod
    public void close() throws InterruptedException {
        Thread.sleep(3000);
        //driver.quit();
    }

    @Test
    public void loginStoreManager() throws InterruptedException {
//        1. Login to Vytrack as a store manager
        driver.get("http://qa3.vytrack.com/user/login");
        WebElement userBox = driver.findElement(By.id("prependedInput"));
        userBox.sendKeys("storemanager89");
        Thread.sleep(1000);
        WebElement passwordBox = driver.findElement(By.id("prependedInput2"));
        passwordBox.sendKeys("UserUser123");

        Thread.sleep(1000);
        driver.findElement(By.id("_submit")).click();

//        2. Verify that you can access Vehicle contracts page
Thread.sleep(2000);
        driver.findElement(By.xpath("(//li[@class='dropdown dropdown-level-1'])[1]")).click();
        driver.findElement(By.xpath("//span[.='Vehicle Contracts']")).click();

        String expPageTitle = "All - Vehicle Contract - Entities - System - Car - Entities - System";

        Thread.sleep(2000);
        String actPageTitle = driver.getTitle();
       // System.out.println("actPageTitle = " + actPageTitle);
       Thread.sleep(1000);
        Assert.assertEquals(expPageTitle,actPageTitle);


    }

    @Test
    public void loginSalesManager() throws InterruptedException {
//  1. Login to Vytrack as a sales manager
        driver.get("http://qa3.vytrack.com/user/login");
        driver.findElement(By.id("prependedInput")).sendKeys("salesmanager255");
        Thread.sleep(1000);
        driver.findElement(By.id("prependedInput2")).sendKeys("UserUser123");
        driver.findElement(By.id("_submit")).click();

//  2. Verify that you can access Vehicle contracts page
Thread.sleep(2000);
        driver.findElement(By.xpath("(//li[@class='dropdown dropdown-level-1'])[1]")).click();
        driver.findElement(By.xpath("//span[.='Vehicle Contracts']")).click();

        String expPageTitle = "All - Vehicle Contract - Entities - System - Car - Entities - System";

        Thread.sleep(2000);
        String actPageTitle = driver.getTitle();
        // System.out.println("actPageTitle = " + actPageTitle);
        Thread.sleep(1000);
        Assert.assertEquals(expPageTitle,actPageTitle);

    }
    @Test
    public void loginDriver() throws InterruptedException {
//        1. Login to Vytrack as a driver

        Thread.sleep(2000);
        driver.get("http://qa3.vytrack.com/user/login");
        driver.findElement(By.id("prependedInput")).sendKeys("user156");
        Thread.sleep(1000);
        driver.findElement(By.id("prependedInput2")).sendKeys("UserUser123");

        Thread.sleep(1000);
        driver.findElement(By.id("_submit")).click();


//        2. Verify that you cannot access Vehicle contracts page

        Thread.sleep(2000);
        driver.findElement(By.xpath("(//li[@class='dropdown dropdown-level-1'])[1]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//span[.='Vehicle Contracts']")).click();

//        3. Message You do not have permission to perform this action. should be displayed
        Thread.sleep(2000);
        String expectedMessage = "You do not have permission to perform this action.";
        String actualMessage = driver.findElement(By.xpath("(//div[@class='message'])[2]")).getText();
//        System.out.println("actualMessage = " + actualMessage);
        Thread.sleep(1000);
        Assert.assertEquals(expectedMessage, actualMessage);

    }



}
