package com.vytrack.tests.components.activites;

import com.vytrack.tests.TestBase;
import com.vytrack.utilities.ConfigurationReader;
import com.vytrack.utilities.VytrackUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DailyRepeatTests extends TestBase {

//    1) Daily repeat option, Repeat every, summary
//        1. Log in as Valid user
//        2. Go to Activities -> Calendar Events
//        3. Click on create new calendar event
//        4. Click on Repeat checkbox
//        5. Verify that Daily is selected by default
//        6. Verify day(s) checkbox is selected and default value is 1
//        7. Verify summary says Daily every 1 day
//        8. Check the weekday checkbox
//        9. Verify that days input now disabled
//        10. Verify summary says Daily every weekday

    @Test
    public void optionTest1() throws InterruptedException {

        //        WebDriverManager.chromedriver().setup();
        //        WebDriver driver = new ChromeDriver();

        driver.get(ConfigurationReader.get("url"));

//        1. Log in as Valid user
        VytrackUtils.login(driver, ConfigurationReader.get("storemanager_username"), ConfigurationReader.get("storemanager_password"));

//        2. Go to Activities -> Calendar Events

        VytrackUtils.selectMenuOption(driver, "Activities", "Calendar Events");

//        3. Click on create new calendar event

//       Thread.sleep(4000);
        VytrackUtils.waitForUIOverlay();
        // wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@class='btn-group']//a[@href='/calendar/event/create']"))));
        driver.findElement(By.xpath("//div[@class='btn-group']//a[@href='/calendar/event/create']")).click();

//        4. Click on Repeat checkbox
        VytrackUtils.waitForUIOverlay();
        driver.findElement(By.cssSelector("input[id^=recurrence-repeat]")).click();

//        5. Verify that Daily is selected by default

        Select select = new Select(driver.findElement(By.cssSelector("select[id^=recurrence-repeats]")));
        String defaultOption = select.getFirstSelectedOption().getText();
        System.out.println("defaultOption = " + defaultOption);  //Daily

        Assert.assertEquals(defaultOption, "Daily");

        VytrackUtils.waitForUIOverlay();


//        6. Verify day(s) checkbox is selected and default value is 1

        WebElement repeatEveryBox = driver.findElement(By.xpath("(//div//input[@type='radio'])[1]"));
        Assert.assertTrue(repeatEveryBox.isSelected());

        VytrackUtils.waitForUIOverlay();

        String defaultValue = driver.findElement(By.xpath("(//div//input[@value='1'])[2]")).getAttribute("value");
        System.out.println(defaultValue);
        String expected = "1";


        Assert.assertEquals(defaultValue, expected);

//        7. Verify summary says Daily every 1 day

        VytrackUtils.waitForUIOverlay();

        String expecSummary = "Daily every 1 day";
        System.out.println("expecSummary = " + expecSummary);
        String actSummary = driver.findElement(By.xpath("//span[.='Daily every 1 day']")).getText();
        System.out.println("actSummary = " + actSummary);

        Assert.assertEquals(actSummary, expecSummary);

//        8. Check the weekday checkbox

        driver.findElement(By.xpath("//span[.='Weekday']")).click();

//        9. Verify that days input now disabled

        Assert.assertFalse(driver.findElement(By.xpath("(//div//input[@type='radio'])[1]")).isSelected());

//        10. Verify summary says Daily every weekday

        String expecSummaryWeek = "Daily, every weekday";
        System.out.println("expecSummaryWeek = " + expecSummaryWeek);
        String actSummaryWeek = driver.findElement(By.xpath("//span[.='Daily, every weekday']")).getText();
        System.out.println("actSummaryWeek = " + actSummaryWeek);

        Assert.assertEquals(actSummaryWeek, expecSummaryWeek);


    }

//    2) Daily repeat option, Repeat every, default values
//        1. Log in as Valid user
//        2. Go to Activities -> Calendar Events
//        3. Click on create new calendar event
//        4. Click on Repeat checkbox
//        5. Verify that Daily is selected by default
//        6. Verify day(s) checkbox is selected and default value is 1
//        7. Verify summary says Daily every 1 day

    @Test
    public void optionTest2() {

//        1. Log in as Valid user
        driver.get(ConfigurationReader.get("url"));

        VytrackUtils.login(driver, ConfigurationReader.get("storemanager_username"), ConfigurationReader.get("storemanager_password"));


//        2. Go to Activities -> Calendar Events

        VytrackUtils.waitForUIOverlay();
        VytrackUtils.selectMenuOption(driver, "Activities", "Calendar Events");

//        3. Click on create new calendar event

        VytrackUtils.waitForUIOverlay();
        // wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@class='btn-group']//a[@href='/calendar/event/create']"))));
        driver.findElement(By.xpath("//div[@class='btn-group']//a[@href='/calendar/event/create']")).click();

//        4. Click on Repeat checkbox

        VytrackUtils.waitForUIOverlay();
        driver.findElement(By.cssSelector("input[id^=recurrence-repeat]")).click();


//        5. Verify that Daily is selected by default

        Select select = new Select(driver.findElement(By.cssSelector("select[id^=recurrence-repeats]")));
        String defaultOption = select.getFirstSelectedOption().getText();
        System.out.println("defaultOption = " + defaultOption);  //Daily

        Assert.assertEquals(defaultOption, "Daily");


//        6. Verify day(s) checkbox is selected and default value is 1

        WebElement repeatEveryBox = driver.findElement(By.xpath("(//div//input[@type='radio'])[1]"));
        Assert.assertTrue(repeatEveryBox.isSelected());

        VytrackUtils.waitForUIOverlay();

        String defaultValue = driver.findElement(By.xpath("(//div//input[@value='1'])[2]")).getAttribute("value");
        System.out.println(defaultValue);
        String expected = "1";


        Assert.assertEquals(defaultValue, expected);

//        7. Verify summary says Daily every 1 day

        VytrackUtils.waitForUIOverlay();

        String expecSummary = "Daily every 1 day";
        System.out.println("expecSummary = " + expecSummary);
        String actSummary = driver.findElement(By.xpath("//span[.='Daily every 1 day']")).getText();
        System.out.println("actSummary = " + actSummary);

        Assert.assertEquals(actSummary, expecSummary);

    }

//    3) Daily repeat option, Repeat every day(s), error messages
//        1. Log in as Valid user
//        2. Go to Activities -> Calendar Events
//        3. Click on create new calendar event
//        4. Click on Repeat checkbox
//        5. Test the day(s) input entering different values (boundary value analysis)
//        6. Verify error messages The value have not to be less than 1. and The value have not to be
//            more than 99. occur when values are too big or small
//        7. Verify that error messages disappear when valid values are entered

    @Test
    public void optionTest3(){

//        1. Log in as Valid user

        driver.get(ConfigurationReader.get("url"));
        VytrackUtils.login(driver, ConfigurationReader.get("storemanager_username"), ConfigurationReader.get("storemanager_password"));

//        2. Go to Activities -> Calendar Events

        VytrackUtils.selectMenuOption(driver, "Activities", "Calendar Events");

//        3. Click on create new calendar event

        VytrackUtils.waitForUIOverlay();
        // wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@class='btn-group']//a[@href='/calendar/event/create']"))));
        driver.findElement(By.xpath("//div[@class='btn-group']//a[@href='/calendar/event/create']")).click();


//        4. Click on Repeat checkbox

        VytrackUtils.waitForUIOverlay();
        driver.findElement(By.cssSelector("input[id^=recurrence-repeat]")).click();

//        5. Test the day(s) input entering different values (boundary value analysis)
//        6. Verify error messages The value have not to be less than 1. and The value have not to be
//            more than 99. occur when values are too big or small

        VytrackUtils.waitForUIOverlay();

        //Delete the default value:
        driver.findElement(By.xpath("(//div//input[@value='1'])[2]")).clear();

        Assert.assertTrue(driver.findElement(By.xpath("(//span[.='This value should not be blank.'])[2]")).isDisplayed());

        //put 0

        driver.findElement(By.xpath("(//div//input[@value='1'])[2]")).sendKeys("0" + Keys.ENTER);

        Assert.assertTrue(driver.findElement(By.xpath("(//span[.='The value have not to be less than 1.'])[2]")).isDisplayed());


        //put 100

        //Delete the previous value:
        driver.findElement(By.xpath("(//div//input[@value='1'])[2]")).clear();

        driver.findElement(By.xpath("(//div//input[@value='1'])[2]")).sendKeys("100" + Keys.ENTER);

        Assert.assertTrue(driver.findElement(By.xpath("(//span[.='The value have not to be more than 99.'])[2]")).isDisplayed());




//        7. Verify that error messages disappear when valid values are entered

        //Delete the previous value:
        driver.findElement(By.xpath("(//div//input[@value='1'])[2]")).clear();
        driver.findElement(By.xpath("(//div//input[@value='1'])[2]")).sendKeys("10" + Keys.ENTER);

        //Assert.assertFalse(driver.findElement(By.xpath("(//span[.='The value have not to be more than 99.'])[2]")).isDisplayed());

       // Assert.assertFalse(driver.findElement(By.xpath("(//span[.='The value have not to be less than 1.'])[2]")).getText().isEmpty());

        Assert.assertTrue(driver.findElement(By.xpath("//span[contains(@id,'temp-validation-name-')]")).getText().isEmpty());


    }

//    4) Daily repeat option, Repeat every day(s), functionality
//        1. Log in as Valid user
//        2. Go to Activities -> Calendar Events
//        3. Click on create new calendar event
//        4. Click on Repeat checkbox
//        5. Enter random value to the day(s) field
//        6. Verify that Summary says Daily every <random number> day
//        7. Enter another random value to the day(s) field
//        8. Verify that Summary updated with Daily every <random number> day

    @Test
    public void optionTest4(){




    }

}
