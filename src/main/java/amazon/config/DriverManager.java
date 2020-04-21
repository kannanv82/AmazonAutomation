package amazon.config;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


public class DriverManager {


    public static AndroidDriver driver;
    public static Properties prop = new Properties();
    static InputStream input = null;
   


    public static AndroidDriver getDriver() throws IOException {
        input = new FileInputStream("property/android.properties");
        prop.load(input);
        if (prop.getProperty("platform").equalsIgnoreCase("android")) {
           
            androidSetup();

        } 
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        return driver;
    }

    public static AndroidDriver androidSetup() throws MalformedURLException {
       //"Setting Android Driver"
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceName", prop.getProperty("DeviceID"));
        caps.setCapability("automationName", "UiAutomator2");
        caps.setCapability("platformName", "Android");
		caps.setCapability("platformVersion", 10);
        caps.setCapability("app", System.getProperty("user.dir") + prop.getProperty("AppPath"));
        caps.setCapability("appPackage", prop.getProperty("Package"));
        caps.setCapability("appActivity", prop.getProperty("Activity"));
       caps.setCapability("appWaitActivity", prop.getProperty("waitActivity"));
        caps.setCapability("newCommandTimeout", 10000);
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), caps);
        //"Android Driver set succesfully"
        return driver;
    }
   

}
