package Pages;

import Utilities.DataUtil;
import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.FileNotFoundException;

public class P04_CartPage {
    private final WebDriver driver;

    public P04_CartPage(WebDriver driver){
        this.driver = driver;
    }

    public String getConfirmationMessage() {
        return Utility.waitForConfirmationPopup(driver);
    }

    By cartPageNav = By.cssSelector("#cartur");
    By placeOrderBTN = By.cssSelector("#page-wrapper > div > div.col-lg-1 > button");
    By nameField = By.id("name");
    By countryField = By.id("country");
    By cityField = By.id("city");
    By creditCard = By.id("card");
    By monthField = By.id("month");
    By yearField = By.id("year");
    By purchaseBTN = By.cssSelector("#orderModal > div > div > div.modal-footer > button.btn.btn-primary");
    By deleteProduct = By.cssSelector("#tbodyid > tr > td:nth-child(4) > a");

    public P04_CartPage placeOrder(){
        Utility.clickOnElement(driver,cartPageNav);
        Utility.clickOnElement(driver,placeOrderBTN);
        return this;
    }

    public P04_CartPage fillForm() throws FileNotFoundException {
        Utility.sendData(driver,nameField, DataUtil.getJsonData("ValidFormData", "name"));
        Utility.sendData(driver,countryField,DataUtil.getJsonData("ValidFormData", "country"));
        Utility.sendData(driver,cityField,DataUtil.getJsonData("ValidFormData", "city"));
        Utility.sendData(driver,creditCard,DataUtil.getJsonData("ValidFormData", "credit"));
        Utility.sendData(driver,monthField,DataUtil.getJsonData("ValidFormData", "month"));
        Utility.sendData(driver,yearField,DataUtil.getJsonData("ValidFormData", "year"));
        return this;
    }

    public P04_CartPage clickOnPurchase(){
        Utility.clickOnElement(driver, purchaseBTN);
        return this;
    }

    public P04_CartPage deleteProduct(){
        Utility.clickOnElement(driver,cartPageNav);
        Utility.clickOnElement(driver, deleteProduct);
        return this;
    }


}
