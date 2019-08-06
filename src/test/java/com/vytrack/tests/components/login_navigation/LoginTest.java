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

public class LoginTest {


    WebDriver driver;
    @BeforeMethod
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver =new ChromeDriver();
    }

    @AfterMethod
    public void close() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
    }

    @Test (description = "positive test")
    public void loginTestPositive() throws InterruptedException {
//        1. Login to Vytrack as a store manager

        driver.get("http://qa3.vytrack.com/user/login");
        WebElement userBox = driver.findElement(By.id("prependedInput"));
        userBox.sendKeys("storemanager89");
        Thread.sleep(1000);
        WebElement passwordBox = driver.findElement(By.id("prependedInput2"));
        passwordBox.sendKeys("UserUser123");

        Thread.sleep(1000);
        driver.findElement(By.id("_submit")).click();

//        2. Verify name of the store manager is displayed on top right
        Thread.sleep(2000);
        String expectedName = "Florida Cremin";
        System.out.println("expectedName = " + expectedName);
        Thread.sleep(2000);
        String actualName = driver.findElement(By.xpath("//a[@href='javascript: void(0);']")).getText();
//        (//a[contains(text(),'Florida Cremin')])[1]

        System.out.println("actualName = " + actualName);

        Assert.assertEquals(expectedName,actualName);

//        3. Verify Dashboard page is open
Thread.sleep(2000);
        String expectedDashboard = "Dashboard";
        System.out.println("expectedDashboard = " + expectedDashboard);
        Thread.sleep(2000);
        String actualDashboard = driver.findElement(By.xpath("//h1[@class='oro-subtitle']")).getText();
        System.out.println("actualDashboard = " + actualDashboard);

        Assert.assertEquals(expectedDashboard, actualDashboard);

//        4. Log out

        Thread.sleep(1000);
        driver.findElement(By.xpath("//a[@href='javascript: void(0);']")).click();
        driver.findElement(By.cssSelector("a[href='/user/logout']")).click();

//        5. Login to Vytrack as a sales manager
        driver.get("http://qa3.vytrack.com/user/login");
        driver.findElement(By.id("prependedInput")).sendKeys("salesmanager255");
        Thread.sleep(1000);
        driver.findElement(By.id("prependedInput2")).sendKeys("UserUser123");
        driver.findElement(By.id("_submit")).click();

//       6. Verify Dashboad page is open

        String exp = "Dashboard";
        System.out.println("exp = " + exp);

        Thread.sleep(2000);

        String act = driver.findElement(By.xpath("//h1[@class='oro-subtitle']")).getText();
        //h1[contains(text(),'Dashboard')]
        System.out.println("act = " + act);
        Thread.sleep(2000);
        Assert.assertEquals(exp,act);

//        7. A different name should be displayed on top right
Thread.sleep(2000);
        String name = driver.findElement(By.xpath("(//a[contains(text(), 'Jeramie Kemmer')])[2]")).getText();
        System.out.println("name = " + name);
        if (name.equals("Jeramie Kemmer")){
            System.out.println("PASS");
        }else
            System.out.println("FALSE");

//        8. Log out

        driver.findElement(By.xpath("(//a[contains(text(), 'Jeramie Kemmer')])[2]")).click();
        driver.findElement(By.cssSelector("a[href='/user/logout']")).click();

//        9. Login to Vytrack as a driver
Thread.sleep(2000);
        driver.get("http://qa3.vytrack.com/user/login");
        driver.findElement(By.id("prependedInput")).sendKeys("user156");
        Thread.sleep(1000);
        driver.findElement(By.id("prependedInput2")).sendKeys("UserUser123");

        Thread.sleep(1000);
        driver.findElement(By.id("_submit")).click();

//        10. Verify Dashboad/Quick Launchpad page is open

        Thread.sleep(2000);
        String expected = "Quick Launchpad";
        String actual = driver.findElement(By.xpath("//h1[.='Quick Launchpad']")).getText();

        Assert.assertEquals(expected, actual);

//        11. A different name should be displayed on top right
        Thread.sleep(2000);
        String name2 = driver.findElement(By.xpath("//a[@href='javascript: void(0);']")).getText();//a[contains(text(), 'Mariam Sauer ')]
        System.out.println("name2 = " + name2);
    }

    @Test (description = "negative test")
    public void loginTestNegative() throws InterruptedException {
//        1. Open Vytrack login page
        driver.get("http://qa3.vytrack.com/user/login");

//        2. Enter valid username and invalid password information

        driver.get("http://qa3.vytrack.com/user/login");
        driver.findElement(By.id("prependedInput")).sendKeys("user156");
        Thread.sleep(1000);
        driver.findElement(By.id("prependedInput2")).sendKeys("abcdefffff111");
//        3. Click login
        Thread.sleep(1000);
        driver.findElement(By.id("_submit")).click();

//        4. Message Invalid user name or password. should be displayed
        Thread.sleep(2000);
        String expected = "Invalid user name or password.";
        String actual = driver.findElement(By.xpath("//div[.='Invalid user name or password.']")).getText();

        Assert.assertEquals(expected,actual);

//        5. Page title and url should be same
        String expectedURL = "http://qa3.vytrack.com/user/login";
        String actualURL = driver.getCurrentUrl();

        Assert.assertEquals(actualURL, expectedURL);

        String expectedTitle = "Login";
        String actualTitle = driver.getTitle();

        Assert.assertEquals(expectedTitle, actualTitle);


    }
}
