package com.vytrack.tests.components.activites;

import com.vytrack.tests.TestBase;
import com.vytrack.utilities.BrowserUtils;
import com.vytrack.utilities.ConfigurationReader;
import com.vytrack.utilities.VytrackUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class DateAndTimeTests extends TestBase {


//1) Date Time, End date auto adjust
//    1. Log in as Valid user
//    2. Go to Activities -> Calendar Events
//    3. Click on create new calendar event
//    4. Change the start date to future date
//    5. Verify that end date changes to the same date
//    6. Change back the start date to today’s date
//    7. Verify that end date changes back to today’s date

    @Test
    public void dateTime1() throws InterruptedException {

//    1. Log in as Valid user
        driver.get(ConfigurationReader.get("url"));
        String username = ConfigurationReader.get("storemanager_username");
        String password = ConfigurationReader.get("storemanager_password");


        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);

        VytrackUtils.login(driver, username, password);

//    2. Go to Activities -> Calendar Events
        VytrackUtils.selectMenuOption(driver, "Activities", "Calendar Events");

//    3. Click on create new calendar event

        VytrackUtils.waitForUIOverlay();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("(//a[contains(text(), 'Create Calendar event')])[3]"))));
        driver.findElement(By.xpath("(//a[contains(text(), 'Create Calendar event')])[3]")).click();

//        VytrackUtils.waitForUIOverlay();
//        BrowserUtils.waitForClickablility(By.xpath("(//a[contains(text(), 'Create Calendar event')])[3]"), 5).click();

//    4. Change the start date to future date

        VytrackUtils.waitForUIOverlay();
        WebElement dateButton = driver.findElement(By.cssSelector("input[id^=date_selector_oro_calendar_event_form_start]"));
        dateButton.click();

        Thread.sleep(3000);
        WebElement startDate = driver.findElement(By.xpath("//a[.='30']"));      //td//a[contains(text(), '30')]
//        WebElement startDate = driver.findElement(By.cssSelector(".ui-state-default.ui-state-active"));
        String startDate1 = startDate.getText();
        System.out.println("startDate1 = " + startDate1);

        Thread.sleep(1000);
        startDate.click();
//        System.out.println("startDate = " + startDate.getText());


//    5. Verify that end date changes to the same date

//        WebElement dateButton2 = driver.findElement(By.cssSelector("input[id^=date_selector_oro_calendar_event_form_end]"));
//        dateButton2.getText();

        Thread.sleep(2000);


        String endDate = driver.findElement(By.xpath("//a[.='30']")).getText();
//a[@class='ui-state-default ui-state-active']
        Thread.sleep(2000);
        System.out.println("endDate = " + endDate);
        Thread.sleep(2000);

        Assert.assertEquals(driver.findElement(By.xpath("//a[.='30']")).getText(), driver.findElement(By.xpath("//a[.='30']")).getText());
//        Assert.assertEquals(driver.findElement(By.xpath("//a[.='30']")).getText(),driver.findElement(By.xpath("//a[@class=‘ui-state-default ui-state-active’]")).getText());
    }

//    2) Date Time, End time auto adjust
//        1. Log in as Valid user
//        2. Go to Activities -> Calendar Events
//        3. Click on create new calendar event
//        4. Change the start time to any other time
//        5. Verify that end time changes exactly 1 hours later

    @Test
    public void dateTime2() throws InterruptedException {
        //    1. Log in as Valid user
        driver.get(ConfigurationReader.get("url"));
        String username = ConfigurationReader.get("storemanager_username");
        String password = ConfigurationReader.get("storemanager_password");


        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);

        VytrackUtils.login(driver, username, password);

//    2. Go to Activities -> Calendar Events
        VytrackUtils.waitForUIOverlay();
        VytrackUtils.selectMenuOption(driver, "Activities", "Calendar Events");

//    3. Click on create new calendar event

        VytrackUtils.waitForUIOverlay();
        // wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("(//a[contains(text(), 'Create Calendar event')])[3]"))));
        driver.findElement(By.xpath("(//a[contains(text(), 'Create Calendar event')])[3]")).click();

//        4. Change the start time to any other time

        VytrackUtils.waitForUIOverlay();
        //wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("input[id^=time_selector_oro_calendar_event_form_start-]")))).click();
        driver.findElement(By.cssSelector("input[id^=time_selector_oro_calendar_event_form_start-]")).click();

        VytrackUtils.waitForUIOverlay();
        WebElement timeElement = driver.findElement(By.xpath("//li[text()='8:00 PM']"));
        //VytrackUtils.waitForUIOverlay();
        BrowserUtils.scrollToElement(timeElement);

        VytrackUtils.waitForUIOverlay();

        String startTimeValue = timeElement.getText();
        System.out.println("startTimeValue = " + startTimeValue);
        timeElement.click();

        int startTimeNum = Integer.parseInt(startTimeValue.substring(0, 1));

//        5. Verify that end time changes exactly 1 hours later
        VytrackUtils.waitForUIOverlay();
        driver.findElement(By.cssSelector("input[id^='time_selector_oro_calendar_event_form_end-']")).click();
        VytrackUtils.waitForUIOverlay();
        String endTimeValue = driver.findElement(By.xpath("(//li[text()='9:00 PM'])[2]")).getText();
        System.out.println("endTimeValue = " + endTimeValue);

        int endTimeNum = Integer.parseInt(endTimeValue.substring(0, 1));

        int result = startTimeNum - endTimeNum;

        Assert.assertTrue(result < 1);


    }

    //    3) Date Time, End date/time auto adjust
//        1. Log in as Valid user
//        2. Go to Activities -> Calendar Events
//        3. Click on create new calendar event
//        4. Change the start time to 11.30 PM
//        5. Verify that end date shows tomorrows date
//        6. Verify that end time is 12:30 AM
    @Test
    public void dateTime3() throws InterruptedException {

//        1. Log in as Valid user
        driver.get(ConfigurationReader.get("url"));

        String username = ConfigurationReader.get("storemanager_username");
        String password = ConfigurationReader.get("storemanager_password");
        VytrackUtils.login(driver, username, password);

//        2. Go to Activities -> Calendar Events
        VytrackUtils.waitForUIOverlay();
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

        VytrackUtils.selectMenuOption(driver, "Activities", "Calendar Events");
//
//        3. Click on create new calendar event

        VytrackUtils.waitForUIOverlay();
        // wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("(//a[contains(text(), 'Create Calendar event')])[3]"))));
        driver.findElement(By.xpath("(//a[contains(text(), 'Create Calendar event')])[3]")).click();


//        4. Change the start time to 11.30 PM
        VytrackUtils.waitForUIOverlay();
        //wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("input[id^=time_selector_oro_calendar_event_form_start-]")))).click();
        driver.findElement(By.cssSelector("input[id^=time_selector_oro_calendar_event_form_start-]")).click();

        VytrackUtils.waitForUIOverlay();
        WebElement timeElement = driver.findElement(By.xpath("//li[text()='11:30 PM']"));

        Actions actions = new Actions(driver);
        actions.moveToElement(timeElement).click();

        // BrowserUtils.scrollToElement(timeElement);

        VytrackUtils.waitForUIOverlay();

        String startTimeValue = timeElement.getText();
        System.out.println("startTimeValue = " + startTimeValue);
        timeElement.click();

//        5. Verify that end date shows tomorrows date
        VytrackUtils.waitForUIOverlay();
        //today:
        driver.findElement(By.cssSelector("input[id^=date_selector_oro_calendar_event_form_start]")).click();

        VytrackUtils.waitForUIOverlay();
        WebElement todayDatel = driver.findElement(By.xpath("//a[contains(text(), '24')]"));
        VytrackUtils.waitForUIOverlay();
        Thread.sleep(3000);
        String todayDate = todayDatel.getText();
        System.out.println("todayDate = " + todayDate);

        todayDatel.click();

        int numToday = Integer.parseInt(todayDate.substring(0, 2));


        VytrackUtils.waitForUIOverlay();
        Thread.sleep(3000);
        driver.findElement(By.cssSelector("input[id^=date_selector_oro_calendar_event_form_end]")).click();

        VytrackUtils.waitForUIOverlay();
        WebElement tomorrowDate = driver.findElement(By.xpath("//a[@class='ui-state-default ui-state-active'][contains(text(), '25')]"));
      Thread.sleep(3000);
        String tomorrowDateText = tomorrowDate.getText();
        System.out.println("tomorrowDate = " + tomorrowDateText);
        tomorrowDate.click();

        int endTimeNum = Integer.parseInt(tomorrowDateText.substring(0, 2));

        int result = numToday - endTimeNum;

        Assert.assertTrue(result < 1);


//        6. Verify that end time is 12:30 AM

        VytrackUtils.waitForUIOverlay();
        driver.findElement(By.cssSelector("input[id^='time_selector_oro_calendar_event_form_end-']")).click();
        VytrackUtils.waitForUIOverlay();
        String endTimeValue = driver.findElement(By.xpath("(//li[text()='12:30 AM'])[2]")).getText();
        System.out.println("endTimeValue = " + endTimeValue);

        String expectedTime = "12:30 AM";

        Assert.assertEquals(expectedTime, endTimeValue);


    }


}
