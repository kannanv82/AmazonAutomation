package amazon.Pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static amazon.config.DriverManager.prop;

import java.io.IOException;

public class LoginPage {

    AndroidDriver driver;
    WebDriverWait wait;

    public  LoginPage(AndroidDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        wait = new WebDriverWait(driver, 30);
    }

    @AndroidFindBy(className="android.widget.EditText")
    private WebElement username;
    
    @AndroidFindBy(className="android.widget.Button")
    private WebElement continueButton;

    @AndroidFindBy(className="android.widget.EditText")
    private WebElement password;

    @AndroidFindBy(id="signInSubmit")
    private WebElement SignInButton;

    public void LoginInApp()
    {
        
        
    	waitForContinueButton();
        //"Entering Username"
        username.sendKeys(prop.getProperty("username"));
        //Click on Continue
        continueButton.click();
        waitForSignInButton();
        password.sendKeys(prop.getProperty("Password"));
        SignInButton.click();
           
    }
    
    public void waitForContinueButton()
    {
        wait.until(ExpectedConditions.elementToBeClickable(continueButton));
    }
    
    public void waitForSignInButton()
    {
       wait.until(ExpectedConditions.elementToBeClickable(SignInButton));
       
    }
}
