package amazonTests;

import org.testng.annotations.Test;

import Utilities.GenericMethods;
import amazon.Pages.AddProductToCart;
import amazon.Pages.LoginPage;
import amazon.Pages.WelcomePage;
import amazon.config.DriverManager;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

public class AmazonShopping extends BaseClass{

    DriverManager driverManager;
    WelcomePage welcomepage;
    LoginPage loginPage;
    AddProductToCart addProductToCart;
    AndroidDriver driver;
    

    boolean status=true;

    @Test
    public void SelectItems() throws InterruptedException, IOException
    {
       //"Add product Test","Login and add product you cart"
        driver=DriverManager.getDriver();
        welcomepage = new WelcomePage(driver);
        //"Opening app on device"
        //"Waiting for app to load device"
        welcomepage.waitForAppToLoad();
        //"Clicking on Sign in Button"
        welcomepage.ClickOnSignIn();
        
       loginPage=new LoginPage(driver);
        
        //"Performing login action"
        loginPage.LoginInApp();
       
        //"Login succesfull"
        addProductToCart=new AddProductToCart(driver);
        
        //"Clicking on Search box for product search"
        addProductToCart.SearchBox();
        //"Searched Item in search box"
        addProductToCart.SearchItemFromSearchBox();
      //"Select product "
        addProductToCart.SelectProduct();
        //Get the product name and price from product search page
        addProductToCart.getProductInfo();
       //swipe down to Buy Now button and click
        addProductToCart.swipeDownToClickBuyNow();
        //click continue with default payment method
        addProductToCart.clickOnContinue();
        //Verify the product name and price from between product search and checkout page
        addProductToCart.verifyProductInfo();
       
    }

}
