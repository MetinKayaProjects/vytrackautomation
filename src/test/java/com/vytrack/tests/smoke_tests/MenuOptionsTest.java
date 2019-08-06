package com.vytrack.tests.smoke_tests;

import com.vytrack.utilities.VerificationUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MenuOptionsTest {

    WebDriver driver;

    @BeforeMethod
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @AfterMethod
    public void close() throws InterruptedException {
        Thread.sleep(3000);
       // driver.quit();
    }

    /*
    TEST CASE: Menu options, driver
1. Login to Vytrack as a driver
2. Navigate to Fleet à Vehicles, verify page title Car - Entities - System - Car - Entities –
System, page name Cars
3. Navigate to Customers à Accounts, verify page title Accounts - Customers, verify page
name Accounts
4. Navigate to Customers à Contacts, verify page title Accounts - Customers, verify page name Contacts
5. Navigate to Activities à Calendar Events, verify page title Calendar Events - Activities, page name Calendar Events
     */

    @Test
    public void menuOptionsDriver() throws InterruptedException {
//1. Login to Vytrack as a driver
        driver.get("http://qa3.vytrack.com/user/login");
        WebElement userBox = driver.findElement(By.id("prependedInput"));
        userBox.sendKeys("user156");
Thread.sleep(1000);
        WebElement passwordBox = driver.findElement(By.id("prependedInput2"));
        passwordBox.sendKeys("UserUser123");


        Thread.sleep(1000);
        driver.findElement(By.id("_submit")).click();

//2. Navigate to Fleet à Vehicles, verify page title Car - Entities - System - Car - Entities – System, page name Cars
        Thread.sleep(2000);
        driver.findElement(By.xpath("(//span[@class='title title-level-1'])[1]")).click();
        //  <span class="title title-level-2">Vehicles</span>
        Thread.sleep(1000);
        driver.findElement(By.xpath("//span[.='Vehicles']")).click();
        //(//span[@class='title title-level-2'])[1]

        String expectedTitle =  "Car - Entities - System - Car - Entities - System";
        Thread.sleep(2000);
        String actualTitle = driver.getTitle();

        VerificationUtils.verifyEquals(expectedTitle, actualTitle);

//3. Navigate to Customers à Accounts, verify page title Accounts - Customers, verify page name Accounts
        Thread.sleep(2000);
        WebElement customer = driver.findElement(By.xpath("(//span[@class='title title-level-1'])[2]"));
        customer.click();

        Thread.sleep(1000);
        WebElement account = driver.findElement(By.xpath("//span[.='Accounts']"));
        account.click();
Thread.sleep(2000);

        String expectedAccauntTitle = "Accounts - Customers";
        String actualAccountTitle = driver.getTitle();
       // System.out.println(driver.getTitle()); //Accounts - Customers

        VerificationUtils.verifyEquals(expectedAccauntTitle,actualAccountTitle);


        String expectedPageName = "Accounts";
        Thread.sleep(2500);
        String actualPageName = driver.findElement(By.xpath("//h1[@class='oro-subtitle']")).getText();
       // System.out.println(expectedPageName + " = " +actualPageName);

        VerificationUtils.verifyEquals(expectedPageName, actualPageName);

// 4. Navigate to Customers à Contacts, verify page title Contacts - Customers, verify page name Contacts

        customer = driver.findElement(By.xpath("(//span[@class='title title-level-1'])[2]"));
        customer.click();

        //     <span class="title title-level-2">Contacts</span>
        WebElement contacts = driver.findElement(By.xpath("//span[.='Contacts']"));
        contacts.click();

        String expectedCustomerTitle = "Contacts - Customers";
        System.out.println("expectedCustomerTitle = " + expectedCustomerTitle);

        Thread.sleep(2500);
        String actualCustomerTitle = driver.getTitle();
        System.out.println("actualCustomerTitle = " + actualCustomerTitle);


        VerificationUtils.verifyEquals(expectedCustomerTitle, actualCustomerTitle);

       // Thread.sleep(2000);
        WebElement contactsPageName = driver.findElement(By.xpath("//h1[@class='oro-subtitle']"));

        Thread.sleep(2500);
        String expectedContactPageName = "Contacts";
        System.out.println("expectedContactPageName = " + expectedContactPageName);
        String actualContactPageName = contactsPageName.getText();
        System.out.println("actualContactPageName = " + actualContactPageName);

        VerificationUtils.verifyEquals(expectedContactPageName, actualContactPageName);


//5. Navigate to Activities à Calendar Events, verify page title Calendar Events - Activities, page name Calendar Events
        Thread.sleep(2000);
        WebElement activitiesMenu =driver.findElement(By.xpath("(//span[@class='title title-level-1'])[3]"));//(//span[@class='title title-level-1'])[3]
        activitiesMenu.click();
        Thread.sleep(2000);

        WebElement calendarEvents = driver.findElement(By.xpath("//span[.='Calendar Events']"));
        calendarEvents.click();

        Thread.sleep(2000);
        String expectedCalendarEventTitle = "Calendar Events - Activities";
        System.out.println("expectedCalendarEventTitle = " + expectedCalendarEventTitle);
        String actualCalendarEventTitle = driver.getTitle();
        System.out.println("actualCalendarEventTitle = " + actualCalendarEventTitle);

        VerificationUtils.verifyEquals(expectedCalendarEventTitle, actualCalendarEventTitle);

        Thread.sleep(2000);
        String expectedCalendarEventPageName = "Calendar Events";
        System.out.println("expectedCalendarEventPageName = " + expectedCalendarEventPageName);

        String actualCalendarEventPageName = driver.findElement(By.xpath("//h1[.='Calendar Events']")).getText();
        System.out.println("actualCalendarEventPageName = " + actualCalendarEventPageName);
        Thread.sleep(1000);
        VerificationUtils.verifyEquals(expectedCalendarEventPageName, actualCalendarEventPageName);
      //  Assert.assertEquals(expectedAccauntTitle,actualAccountTitle);

    }
        @Test
        public void menuOptionsStoreManager() throws InterruptedException {

//    TEST CASE: Menu options, store manager
//1. Login to Vytrack as a store manager

            driver.get("http://qa3.vytrack.com/user/login");
            WebElement userBox = driver.findElement(By.id("prependedInput"));
            userBox.sendKeys("storemanager89");
            Thread.sleep(1000);
            WebElement passwordBox = driver.findElement(By.id("prependedInput2"));
            passwordBox.sendKeys("UserUser123");

            Thread.sleep(1000);
            driver.findElement(By.id("_submit")).click();

//2. Navigate to Dashboards à Dashboard, verify page title Dashboard - Dashboards, verify page name Dashboard
            Thread.sleep(3000);
            WebElement dashBoards = driver.findElement(By.xpath("//li[@class='dropdown dropdown-level-1 first']"));
            dashBoards.click();
            Thread.sleep(3000);
            WebElement dashboard1 = driver.findElement(By.xpath("(//span[contains(text(), 'Dashboard')])[3] "));
            dashboard1.click();

//            //button[contains(text(), 'click')]     ---> any button that contains the String 'click'
//            From shortVideo    //tagName[text()=’text’]   //a[text()=’Amazon’]

            String expectedTitle = "Dashboard - Dashboards";
            System.out.println("expectedTitle = " + expectedTitle);
            Thread.sleep(2000);
            String actualTitle = driver.getTitle();
            System.out.println("actualTitle = " + actualTitle);

            Assert.assertEquals(expectedTitle, actualTitle);

            //verify page name: Dashboard
            String expectedPageName = "Dashboard";
            System.out.println("expectedPageName = " + expectedPageName);
            String actualPageName = driver.findElement(By.xpath("//h1[contains(text(), 'Dashboard')]")).getText();
            System.out.println("actualPageName = " + actualPageName);

            Assert.assertEquals(expectedPageName, actualPageName);


// 3. Navigate to Fleet à Vehicles, verify page title All - Car - Entities - System - Car - Entities - System, page name All Cars

            Thread.sleep(2000);
            WebElement fleetMenu = driver.findElement(By.xpath("(//li[@class='dropdown dropdown-level-1'])[1]"));
            fleetMenu.click();

            Thread.sleep(2000);
            WebElement vehiclesMenu =driver.findElement(By.xpath("//span[.='Vehicles']"));
            vehiclesMenu.click();

//  verify page title All - Car - Entities - System - Car - Entities - System
            Thread.sleep(2000);
            String expectedPageTitle = "All - Car - Entities - System - Car - Entities - System";
            System.out.println("expectedPageTitle = " + expectedPageTitle);
            Thread.sleep(2000);
            String actualPageTitle = driver.getTitle();
            System.out.println("actualPageTitle = " + actualPageTitle);

            Assert.assertEquals(expectedPageTitle, actualPageTitle);

//          verify page name Cars
            Thread.sleep(1000);
            String expectedVehiclePageName = "All Cars";
            System.out.println("expectedVehiclePageName = " + expectedVehiclePageName);
            Thread.sleep(1500);
            String actualVehiclePageName = driver.findElement(By.xpath("//h1[@class='oro-subtitle']")).getText();
            System.out.println("actualVehiclePageName = " + actualVehiclePageName);

            Assert.assertEquals(expectedVehiclePageName, actualVehiclePageName);

// 4. Navigate to Customers à Accounts, verify page title Accounts - Customers, verify page name Accounts

            Thread.sleep(1500);
            WebElement customerMenu = driver.findElement(By.xpath("(//span[@class='title title-level-1'])[3]"));
            customerMenu.click();
            Thread.sleep(1000);
            WebElement customerAccounts =driver.findElement(By.xpath("(//span[.='Accounts'])[1]"));
            customerAccounts.click();

            Thread.sleep(1500);
            //verify page title Accounts
            String expectedCostumerAccountTitle = "All - Accounts - Customers";
            System.out.println("expectedCostumerAccountTitle = " + expectedCostumerAccountTitle);
            Thread.sleep(2000);
            String actualCostumerAccountsTitle = driver.getTitle();
            System.out.println("actualCostumerAccountsTitle = " + actualCostumerAccountsTitle);

            Assert.assertEquals(expectedCostumerAccountTitle, actualCostumerAccountsTitle);
//5. Navigate to Customers à Contacts, verify page title Accounts - Customers, verify page name All Contacts
            Thread.sleep(2000);
            driver.findElement(By.xpath("(//span[@class='title title-level-1'])[3]")).click();
            Thread.sleep(1000);
            driver.findElement(By.xpath("//span[.='Contacts']")).click();
            Thread.sleep(2000);
            String expectedCustomerContactTitle = "All - Contacts - Customers";
            System.out.println("expectedCustomerContactTitle = " + expectedCustomerContactTitle);
            String actualCustomerContactTitle = driver.getTitle();
            System.out.println("actualCustomerContactTitle = " + actualCustomerContactTitle);

            Assert.assertEquals(expectedCustomerContactTitle, actualCustomerContactTitle);

            Thread.sleep(1000);
            String expectedCustomerContactPageName = "All Contacts";
            System.out.println("expectedCustomerContactPageName = " + expectedCustomerContactPageName);
            String actualCustomerContactPageName = driver.findElement(By.xpath("//h1[@class='oro-subtitle'] ")).getText();
            System.out.println("actualCustomerContactPageName = " + actualCustomerContactPageName);

            Assert.assertEquals(expectedCustomerContactPageName, actualCustomerContactPageName);

//6. Navigate to Sales à Opportunities, verify page title Open Opportunities - Opportunities - Sales, verify page name Open Opportunities
Thread.sleep(1500);
            driver.findElement(By.xpath("(//span[@class='title title-level-1'])[4]")).click();
            Thread.sleep(1000);
            driver.findElement(By.xpath("(//span[.='Opportunities'])[1]")).click();
Thread.sleep(2000);

            String expectedOpportunitiesPageTitle = "Open Opportunities - Opportunities - Sales";

            String actualOpportunitiesPageTitle = driver.getTitle();
            System.out.println("actualOpportunitiesPageTitle = " + actualOpportunitiesPageTitle);

            Assert.assertEquals(expectedOpportunitiesPageTitle, actualOpportunitiesPageTitle);
Thread.sleep(1500);
            String expectedPageNameOp = "Open Opportunities";
            System.out.println("expectedPageName = " + expectedPageNameOp);
            String actualPageNameOp = driver.findElement(By.cssSelector("#container>div>div>div>div>div>div>div>div>div>h1")).getText();
            System.out.println("actualPageNameOp = " + actualPageNameOp);

            Assert.assertEquals(expectedPageNameOp, actualPageNameOp);

//7. Navigate to Activities à Calls verify page title Calendar Events - Activities, page name All Calls
            Thread.sleep(2000);
            driver.findElement(By.xpath("(//span[contains(text(), 'Activities')])[1]")).click();
            Thread.sleep(1000);
            driver.findElement(By.xpath("//span[.='Calls']")).click();
//span[.='Calls']
            Thread.sleep(2000);
            String expectedTitleCals = "All - Calls - Activities";
            System.out.println("expectedTitleCals = " + expectedTitleCals);
            String actualTitleCals = driver.getTitle();
            System.out.println("actualTitleCals = " + actualTitleCals);

            Assert.assertEquals(expectedTitleCals, actualTitleCals);

            Thread.sleep(2000);
            String expectedPageNameCalls = "All Calls";
            System.out.println("expectedPageNameCalls = " + expectedPageNameCalls);
            String actualPageNameCalls = driver.findElement(By.cssSelector("#container>div>div>div>div>div>div>div>div>div>h1")).getText();
            System.out.println("actualPageNameCalls = " + actualPageNameCalls);

            Assert.assertEquals(expectedPageNameCalls,actualPageNameCalls);


//8. Navigate to Activities à Calendar Events, verify page title Calendar Events - Activities, page name Calendar Events
            Thread.sleep(2000);
            driver.findElement(By.xpath("(//span[contains(text(), 'Activities')])[1]")).click();
            Thread.sleep(1000);
            driver.findElement(By.xpath("//span[text()='Calendar Events']")).click();

            Thread.sleep(2000);
            String expectetTitleCalendar = "All - Calendar Events - Activities";
            System.out.println("expectetTitleCalendar = " + expectetTitleCalendar);
            String actualTitleCalendar = driver.getTitle();
            System.out.println("actualTitleCalendar = " + actualTitleCalendar);

            Assert.assertEquals(expectetTitleCalendar, actualTitleCalendar);

            Thread.sleep(2000);
            String expectedPageNameCalendar = "All Calendar Events";
            System.out.println("expectedPageNameCalendar = " + expectedPageNameCalendar);
            String actualPageNAmeCalendar = driver.findElement(By.cssSelector("#container>div>div>div>div>div>div>div>div>div>h1")).getText();
            System.out.println("actualPageNAmeCalendar = " + actualPageNAmeCalendar);

            Assert.assertEquals(expectedPageNameCalendar, actualPageNAmeCalendar);




    }
}
