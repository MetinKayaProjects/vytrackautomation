package com.vytrack.tests;

import com.vytrack.utilities.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

//import com.cybertek.utilities.Driver;

public class TestBase {

       protected WebDriver driver;
       protected Actions actions;
       protected WebDriverWait wait;

    @BeforeMethod
    public void setUpMethod() throws InterruptedException {
        // initilializes the webdriver object in test base class using the Driver utility
        driver = Driver.get();

        // setting implicit wait --> when elements not found, it will keep trying to find it for 10 seconds
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        // set up the explicit wait object.
        wait = new WebDriverWait(driver, 10);

        // Actions class enable advanced interactions like double click, drag drop ...
        actions = new Actions(driver);
        driver.manage().window().maximize();

    }

    @AfterMethod
    public void tearDownMethod() throws InterruptedException {

        Thread.sleep(4000);
        Driver.closeDriver();
    }


}