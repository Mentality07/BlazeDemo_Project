import Pages.*;
import Utilities.DataUtil;
import Utilities.Utility;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.FileNotFoundException;
import java.time.Duration;

import static org.testng.Assert.assertEquals;

public class SC_01_E2E {
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
    new P01_LoginPage(driver).clickOnLogIn().enterUserName(DataUtil.getJsonData("ValidLoginData", "username1")).enterPassword(DataUtil.getJsonData("ValidLoginData", "password1")).confirmLogin();
    assertEquals(driver.getCurrentUrl(), "https://www.demoblaze.com/index.html");


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
    public void placingOrder() throws FileNotFoundException {
            String confirmationText = new P04_CartPage(driver)
                    .placeOrder()
                    .fillForm()
                    .clickOnPurchase()
                    .getConfirmationMessage();

            Assert.assertEquals(confirmationText, "Thank you for your purchase!", "Confirmation message mismatch!");
        }


    @Test(dependsOnMethods = "placingOrder")
    public void orderConfirmation(){
        new P05_ConfirmOrderPage(driver).confirmOrder();
//        String details = Utility.getPurchaseDetails(driver);
//        System.out.println("Order Details:\n" + details);
//        Assert.assertTrue(details.contains("Amount"), "Purchase details do not contain expected info.");
    }

    @AfterSuite
    public void finish(){
        driver.quit();
    }
}
