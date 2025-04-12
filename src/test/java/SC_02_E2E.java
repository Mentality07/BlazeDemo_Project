import Pages.*;
import Utilities.DataUtil;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.time.Duration;

import static org.testng.Assert.assertEquals;

public class SC_02_E2E {
    private WebDriver driver;


    @BeforeSuite
    public void pageSetup() throws FileNotFoundException {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(DataUtil.getJsonData("ValidLoginData", "baseURL"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    }
    @Test(priority = 1)
    public void userLogin() throws FileNotFoundException {
        new P01_LoginPage(driver)
                .clickOnLogIn()
                .enterUserName(DataUtil.getJsonData("ValidLoginData", "username2"))
                .enterPassword(DataUtil.getJsonData("ValidLoginData", "password2"))
                .confirmLogin();

        assertEquals(driver.getCurrentUrl(), "https://www.demoblaze.com/index.html");

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.textToBePresentInElementLocated(By.id("nameofuser"), "Welcome"));

        assertEquals(driver.findElement(By.id("nameofuser")).getText(), "Welcome admin2");
    }

    @Test(dependsOnMethods ="userLogin")
    public void searchProduct() throws InterruptedException {
        Thread.sleep(1500);
        new P02_SearchHomePage(driver).scrollToElement().selectProduct();
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.demoblaze.com/prod.html?idp_=9");

    }

    @Test(dependsOnMethods = "searchProduct")
    public void createOrder(){
        P03_ProductPage productPage = new P03_ProductPage(driver);


        productPage.addToCart();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());

        String alertText = alert.getText();
        Assert.assertEquals(alertText, "Product added.", "Alert text is incorrect!");

        alert.accept();
    }
   @Test(dependsOnMethods = "createOrder")
   public void removingProduct() {
       P04_CartPage cartPage = new P04_CartPage(driver);
       cartPage.deleteProduct();
    }

    @Test(dependsOnMethods = "removingProduct")
    public void logout() throws FileNotFoundException {
        new P05_ConfirmOrderPage(driver).logout();
        assertEquals(driver.getCurrentUrl(), DataUtil.getJsonData("ValidLoginData", "baseURL"));
    }




    @AfterSuite
    public void finish(){
        driver.quit();
    }

}
