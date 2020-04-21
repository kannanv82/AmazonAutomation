package amazon.Pages;


import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import static amazon.config.DriverManager.prop;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WelcomePage {
    AndroidDriver driver;
    WebDriverWait wait;
    
    public WelcomePage(AndroidDriver ldriver)
    {
        this.driver=ldriver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        wait = new WebDriverWait(driver, 30);
    }

    @AndroidFindBy(id = "in.amazon.mShop.android.shopping:id/sign_in_button")
    public WebElement SignInWelcomePage;

   

    
    public void waitForAppToLoad()
    {
            //Waiting for App to load"
        
        wait.until(ExpectedConditions.elementToBeClickable(SignInWelcomePage));
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //"App to loaded successfully");

    }

    public void ClickOnSignIn()
    {
        //"Click on Sign in button");
        
            SignInWelcomePage.click();
           
        
       
    }

   }
