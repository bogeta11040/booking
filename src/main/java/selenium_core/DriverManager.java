package selenium_core;

import org.openqa.selenium.WebDriver;

public abstract class DriverManager {
    WebDriver driver;

    public abstract void createWebDriver(String version);

    public void quitWebDriver(){
        if(driver != null) {
            driver.quit();
            driver = null;
        }
    }

    public WebDriver getWebDriver(String version){
        if (driver == null){
            createWebDriver(version);
        }
        return driver;
    }
}
