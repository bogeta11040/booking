package pages;

import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class BasePage {
    WebDriver driver;

    int wait = Integer.parseInt(Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("WAIT"));

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickElement(WebElement element, String log){
        WebDriverWait webDriverWait = new WebDriverWait(driver, wait);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(element));

        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().build().perform();

        System.out.println("Clicked: "+log+" element!");
    }

    /**
     * Will wait for an element to e clickable
     * then it will hover over an element and click it.
     * @param element WebElement to be clicked
     */
    public void clickElement(WebElement element){
        WebDriverWait webDriverWait = new WebDriverWait(driver, wait);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(element));

        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().build().perform();
    }

    public void typeText(WebElement element,String text, String log){
        WebDriverWait webDriverWait = new WebDriverWait(driver, wait);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(element));

        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().build().perform();

        element.clear();
        element.sendKeys(text);

        System.out.println("Typed: "+text+" in "+log+" element!");
    }

    public String getText(WebElement element, String log){
        WebDriverWait webDriverWait = new WebDriverWait(driver, wait);
        webDriverWait.until(ExpectedConditions.visibilityOf(element));

        System.out.println("Getting text from: "+log+" element!");

        return element.getText().trim();
    }

    public void selectByValue(WebElement element, String value){
        WebDriverWait webDriverWait = new WebDriverWait(driver, wait);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(element));

        Select select = new Select(element);
        select.selectByValue(value);
    }

    public boolean hasValue(String data){
        try {
            return !data.equals("") && !data.equals(" ");
        }catch (Exception e){
            return false;
        }
    }

    public void takeScreenshot(String name) throws IOException {
        File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file, new File("src/screenshots/"+name+".png"));
    }

    public void reportScreenshot(String name, String allureName) throws IOException {
        takeScreenshot(name);
        Path content = Paths.get("src/screenshots/"+name+".png");
        try(InputStream is = Files.newInputStream(content)){
            Allure.addAttachment(allureName, is);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void scrollToWebElement(WebElement element){
        WebDriverWait webDriverWait = new WebDriverWait(driver, wait);
        webDriverWait.until(ExpectedConditions.visibilityOf(element));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true)",element);
    }

}