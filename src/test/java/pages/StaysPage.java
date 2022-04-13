package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class StaysPage extends BasePage {

    public StaysPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "ss")
    WebElement locationInput;
    @FindBy(css = ".xp__dates__checkin")
    WebElement datesInput;
    @FindBy(css = "[data-bui-ref='calendar-next']")
    WebElement calendarNext;
    @FindBy(xpath = "//label[text()='Adults']/../..//button[contains(@class,'subtract')]")
    WebElement removeAdults;
    @FindBy(xpath = "//label[text()='Adults']/../..//button[contains(@class,'add')]")
    WebElement addAdults;
    @FindBy(xpath = "//label[text()='Children']/../..//button[contains(@class,'subtract')]")
    WebElement removeChildren;
    @FindBy(xpath = "//label[text()='Children']/../..//button[contains(@class,'add')]")
    WebElement addChildren;
    @FindBy(xpath = "//label[text()='Rooms']/../..//button[contains(@class,'subtract')]")
    WebElement removeRooms;
    @FindBy(xpath = "//label[text()='Rooms']/../..//button[contains(@class,'add')]")
    WebElement addRooms;
    @FindBy(xpath = "//span[contains(text(),'Search')]/..")
    WebElement search;
    @FindBy(css = ".xp__guests__count")
    WebElement guests;

    public void booking(Map<String,String> data) throws IOException {
        setLocation(data.get("Location"));
        bookAStay(data.get("CheckInDate"),data.get("CheckOutDate"));
        addGuests(data);
//        clickSearch();
    }

    public void setLocation(String location) {
        typeText(locationInput, location, "");
    }

    public void bookAStay(String checkInDate, String checkOutDate) {
        clickElement(datesInput);
        setDate(checkInDate);
        setDate(checkOutDate);
    }

    public void addGuests(Map<String,String> data) {
        clickGuests();
        addAdults(Integer.parseInt(data.get("AdultsNum")));
        addChildren(data);
        addRooms(Integer.parseInt(data.get("RoomsNum")));
    }

    public void addAdults(int num) {
        if (num == 1) {
            clickElement(removeAdults);
        } else if (num == 2) {
            //
        } else {
            for (int i = 0; i < num - 2; i++) {
                clickElement(addAdults);
            }
        }
    }

    public void addRooms(int num) {
        for (int i = 0; i < num - 1; i++) {
            clickElement(addRooms);
        }
    }

    public void addChildren(Map<String,String> data){
        if(hasValue(data.get("Children"))) {
            for (int i = 0; i < data.get("Children").split(";").length; i++) {
                clickElement(addChildren);
                selectByValue(driver.findElement(By.cssSelector("[aria-label='Child " + (i + 1) + " age']")), data.get("ChildAge_" + (i + 1)));
            }
        }
    }

    public void setDate(String date) {
        for (int i = 1; i < 4; i++) {
            List<WebElement> checkInDates = driver.findElements(By.cssSelector("[aria-label='" + date + "']"));

            if (checkInDates.size() > 0) {
                clickElement(checkInDates.get(0));
                break;
            } else {
                clickElement(calendarNext);
            }
        }
    }

    public void clickSearch(){
        clickElement(search);
    }

    public void clickGuests(){
        clickElement(guests);
    }
}
