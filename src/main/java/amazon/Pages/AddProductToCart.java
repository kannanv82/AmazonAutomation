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
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Utilities.ExcelUtil;
import Utilities.GenericMethods;

public class AddProductToCart {
    AndroidDriver driver;
    WebDriverWait wait;
    GenericMethods genericmethods;
    
    String productName_SearchPage="";
    String productPrice_SearchPage="";
   
    
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
    private WebElement productName;
    
    @AndroidFindBy(xpath = "//android.view.View[contains(@resource-id,'atfRedesign_priceblock_priceToPay')]/child::android.widget.EditText")
    private WebElement productPrice;
    
    @AndroidFindBy(id="a-autoid-1")
    public WebElement buyNow;
    
    @AndroidFindBy(xpath="//android.widget.Button[contains(text(),'Continue')]")
    public WebElement Continue;
    
    @AndroidFindBy(xpath="//android.view.View[4]/android.view.View[4]")
    public WebElement productName_ChechoutPage;
    
    @AndroidFindBy(xpath="//android.view.View[contains(text(),'Items:')]/following-child::android.view.View")
    public WebElement productPrice_ChechoutPage;


    public void SearchBox()
    {
    	waitForSearchBox();
        SearchBox.click();
       //"Search button clicked"
    }

    public void SearchItemFromSearchBox()
    {
        //"In search bar searching the Item for purchase"
    	SearchBox.sendKeys(ExcelUtil.getDataFromExcel("TESTDATA", "SearchProduct1", 0));
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

    public void getProductInfo(){
    	wait.until(ExpectedConditions.elementToBeClickable(productName));
    	 productName_SearchPage=productName.getText();
    	 productPrice_SearchPage=productPrice.getText();
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
       
      
    }
    //Swipe down till Buy Now is visible
    public void swipeDownToClickBuyNow(){
    	 genericmethods = new GenericMethods(driver);
    	 genericmethods.scrollToText("Buy Now", buyNow);
    	 buyNow.click();
    }
    
    //Continue with default payment method
   public void clickOnContinue(){
	   wait.until(ExpectedConditions.elementToBeClickable(Continue));
	   Continue.click();
   }
  //Verify the product name, description, price from between product search and checkout page
    public void verifyProductInfo(){
    	 wait.until(ExpectedConditions.elementToBeClickable(Continue));
    	 Assert.assertEquals(productPrice_ChechoutPage.getText(), productPrice_SearchPage);
    	 genericmethods = new GenericMethods(driver);
    	 genericmethods.scrollToText(ExcelUtil.getDataFromExcel("TESTDATA", "SearchProduct1", 0), productName_ChechoutPage);
        Assert.assertEquals(productName_ChechoutPage.getText(), productName_SearchPage);
        
    }
    
    public void waitForSearchBox()
    {
       wait.until(ExpectedConditions.elementToBeClickable(SearchBox));
       
    }

    public void waitForProductInfoLoad()
    {
       wait.until(ExpectedConditions.elementToBeClickable(productName));
       
    }
}
