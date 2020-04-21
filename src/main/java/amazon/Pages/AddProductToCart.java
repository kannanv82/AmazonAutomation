package amazon.Pages;



import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import static amazon.config.DriverManager.prop;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class AddProductToCart {
    AndroidDriver driver;
    WebDriverWait wait;
    
    public AddProductToCart(AndroidDriver ldriver)
    {
        this.driver=ldriver;
        PageFactory.initElements(new AppiumFieldDecorator(driver),this);
        wait = new WebDriverWait(driver, 30);
    }

    @AndroidFindBy(className="android.widget.EditText")
    public WebElement SearchBox;

    @AndroidFindBy(xpath = "//android.view.View[1]/android.view.View/android.view.View[8]")
    private WebElement Product;

    @AndroidFindBy(id="bylineInfo")
    public WebElement productInfo;


    public void SearchBox()
    {
    	waitForSearchBox();
        SearchBox.click();
       //"Search button clicked"
    }

    public void SearchItemFromSearchBox()
    {
        //"In search bar searching the Item for purchase"
    	SearchBox.sendKeys(prop.getProperty("SearchItemForPurchase"));
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Robot robot;
        try {
            robot = new Robot();
             robot.keyPress(KeyEvent.VK_ENTER);
             robot.keyRelease(KeyEvent.VK_ENTER);
        } catch (AWTException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void SelectProduct()
    {
        //"In select product"
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
       
        Product.click();
       waitForProductInfoLoad();
       
       //Verify the selected product info
       Assert.assertEquals(productInfo.getText(), prop.getProperty("productText"));

    }

    public void waitForSearchBox()
    {
       wait.until(ExpectedConditions.elementToBeClickable(SearchBox));
       
    }

    public void waitForProductInfoLoad()
    {
       wait.until(ExpectedConditions.elementToBeClickable(productInfo));
       
    }
}
