package tests;

import excel_core.ExcelReader;
import io.qameta.allure.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.StaysPage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class BookingTest extends BaseTest{
    @BeforeMethod
    @Parameters({"BROWSER","BROWSER_VERSION","WAIT","ENV"})
    public void init(String BROWSER, String BROWSER_VERSION, String WAIT, String ENV){
        startApplication(BROWSER,BROWSER_VERSION,Integer.parseInt(WAIT),ENV);
    }

    @AfterMethod
    public void tearDown() throws IOException {
        new BasePage(driver).reportScreenshot("OnFailure_"+System.currentTimeMillis(), "OnFailure");
//        quitApplication();
    }

    @Test(description = "Name of the test")
    @Parameters({"TC_ID"})
    @Description("Some description")
    @TmsLink("test-1")
    @Issue("Issue")
    @Link("TEST")
    @Severity(SeverityLevel.CRITICAL)
    @Epic("EP001")
    @Feature("FE001")
    @Story("US001")
    public void booking(String TC_ID) throws IOException {
        Map<String,String> data = new ExcelReader().getRowDataByID("src/test/test_data/BookingData.xlsx", "Stays", TC_ID, "1", false);
        data.putAll(new ExcelReader().mergeData(data,"src/test/test_data/BookingData.xlsx","Children","Children"));

        StaysPage staysPage = new StaysPage(driver);
        staysPage.booking(data);

//        System.out.println(driver.findElement(By.cssSelector(".bui-button__text")).getAttribute("class"));
//
//        setAttribute(driver.findElement(By.cssSelector(".bui-button__text")),"class","cao svima");
//
//        System.out.println(driver.findElement(By.cssSelector(".cao.svima")).getAttribute("class"));

//        staysPage.scrollToWebElement(driver.findElement(By.id("newsletter_button_footer")));
//        staysPage.reportScreenshot("After booking "+TC_ID+"_"+System.currentTimeMillis(),"Booking");

//        for (int i = 0; i<10;i++) {
//            ((JavascriptExecutor)driver).executeScript("window.open()");
//            ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
//            driver.switchTo().window(tabs.get(i+1));
//            driver.get("http://www.google.com");
//        }
//
//        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
//
//        for (int i = tabs.size()-1; i>0;i--) {
//            driver.switchTo().window(tabs.get(i));
//            driver.close();
//        }
//
//        driver.switchTo().window(tabs.get(0));
    }

    public void setAttribute(WebElement element, String attribute, String value ){
        ((JavascriptExecutor)driver).executeScript("arguments[0].setAttribute(arguments[1],arguments[2])", element,attribute,value);
    }
}
