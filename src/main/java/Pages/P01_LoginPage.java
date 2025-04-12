package Pages;

import Utilities.Utility;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P01_LoginPage {
    private final WebDriver driver;

    public P01_LoginPage(WebDriver driver){
        this.driver= driver;
    }

    By loginTAB = By.id("login2");
    By userName = By.id("loginusername");
    By passwd = By.id("loginpassword");
    By loginBTN = By.xpath("//*[@id=\"logInModal\"]/div/div/div[3]/button[2]");

    public P01_LoginPage clickOnLogIn(){
        Utility.clickOnElement(driver,loginTAB);
        return this;
    }
    public P01_LoginPage  enterUserName(String usrName){
        Utility.sendData(driver,userName,usrName);
        return this;
    }
    public P01_LoginPage  enterPassword(String password){
        Utility.sendData(driver,passwd,password);
        return this;
    }

    public P01_LoginPage confirmLogin(){
        Utility.clickOnElement(driver,loginBTN);
        return this;
    }

}
