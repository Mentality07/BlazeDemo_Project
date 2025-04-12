package Pages;
import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P02_SearchHomePage {
    private final WebDriver driver;

    public P02_SearchHomePage(WebDriver driver) {
        this.driver = driver;
    }
    private final By product1 = By.xpath("//a[normalize-space() ='Sony vaio i7']");

    private final By category = By.xpath("/html/body/div[5]/div/div[1]/div/a[4]");



//    public P02_SearchHomePage handleAlert(){
//        Utility.handlingAlerts(driver);
//        return this;
//
//    }
//    public P02_SearchHomePage filterCategory(){
//        Utility.clickOnElement(driver,category);
//        return this;
//    }


    public P02_SearchHomePage scrollToElement(){
        Utility.scroll(driver, product1);
        return this;
    }

    public void selectProduct(){

        Utility.clickOnElement(driver, product1);
    }


}
