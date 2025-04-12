package Pages;
import Utilities.Utility;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class P03_ProductPage {

    private final WebDriver driver;
    public P03_ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    By cartBTn = By.cssSelector("#tbodyid > div.row > div > a");


    public P03_ProductPage addToCart(){
        Utility.clickOnElement(driver,cartBTn);
        return this;

    }
    public P03_ProductPage handleAlert(){
        Utility.handlingAlerts(driver);
        return this;
    }
}
