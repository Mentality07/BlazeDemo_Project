package Pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P05_ConfirmOrderPage {
    private WebDriver driver;

    public P05_ConfirmOrderPage(WebDriver driver){
        this.driver = driver;
    }


    By acceptPurchaseDetails = By.cssSelector("body > div.sweet-alert.showSweetAlert.visible > div.sa-button-container > div > button");
    By logoutBtn = By.id("logout2");


    public P05_ConfirmOrderPage confirmOrder(){

        Utility.clickOnElement(driver,acceptPurchaseDetails);
        return this;
    }

    public P05_ConfirmOrderPage logout(){
        Utility.clickOnElement(driver,logoutBtn);
        return this;
    }
}
