package tests;

import org.openqa.selenium.WebDriver;
import selenium_core.DriverManager;
import selenium_core.DriverManagerFactory;

import java.util.concurrent.TimeUnit;

public class BaseTest {
    WebDriver driver;
    DriverManager driverManager;

    public void startApplication(String browser, String browserVersion,int wait, String env) {
        driverManager = DriverManagerFactory.getDriverManager(browser);
        driver = driverManager.getWebDriver(browserVersion);

        driver.manage().timeouts().implicitlyWait(wait, TimeUnit.SECONDS);
        initApp(env);
    }

    public void quitApplication() {
        driverManager.quitWebDriver();
    }

    public void initApp(String env) {
        switch (env) {
            case "QA": {
                driver.get("https://www.booking.com/");
            }
            case "STG": {
                driver.get("https://www.booking.com/");
            }
            case "PROD":{
                driver.get("https://www.booking.com/");
            }
        }
    }

}