package amazonTests;


import io.appium.java_client.AppiumDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import amazon.config.DriverManager;

import java.io.IOException;


public class BaseClass {
    public AppiumDriver driver;
    
   
    @AfterClass
    public void tearDown()
    {
        driver.quit();
        
    }


}
