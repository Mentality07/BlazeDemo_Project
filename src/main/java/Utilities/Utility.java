package Utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Utility {

    public static void clickOnElement(WebDriver driver, By locator){
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.elementToBeClickable(locator));
        driver.findElement(locator).click();

    }
    public static void sendData(WebDriver driver, By locator, String data){
        new WebDriverWait(driver,Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(locator));
        driver.findElement(locator).sendKeys(data);
    }

    public static void scroll(WebDriver driver, By locator){
        new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(locator));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,1200)");

    }

    public static void handlingAlerts(WebDriver driver){
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
    }

    public static String waitForConfirmationPopup(WebDriver driver) {
        By confirmationMessageLocator = By.xpath("//h2[contains(text(),'Thank you for your purchase!')]");
        return new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(confirmationMessageLocator))
                .getText();
    }

    public static String getPurchaseDetails(WebDriver driver) {
        By detailsLocator = By.cssSelector("body > div.sweet-alert.showSweetAlert.visible > p");
        return new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(detailsLocator))
                .getText();
    }
}