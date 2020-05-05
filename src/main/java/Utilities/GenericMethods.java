package Utilities;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class GenericMethods {
	private AndroidDriver<AndroidElement> driver;
	WebDriverWait wait;
	public GenericMethods(AndroidDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		wait = new WebDriverWait(driver, 50);
	}
	public boolean scrollToText(String Text, WebElement ele) {
		try {
			wait.until(ExpectedConditions.visibilityOf(ele));
			((AndroidElement) ele).findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).setMaxSearchSwipes("+40+").scrollIntoView("
					+ "new UiSelector().textContains(\""+Text+"\"));");
			
			return true;
		
		}
		catch(Exception e) {			
			e.printStackTrace();
			return false;
		}
	}
}
