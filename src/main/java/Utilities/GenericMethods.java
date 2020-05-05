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
//		String scrollViewContainer_finder = "new UiSelector().className('android.widget.ListView')";
//		String neededElement_finder ="new UiSelector().textContains(\"REORDER\")"; 
//		
//		try {
//		ele = driver.findElement(By.xpath("//android.widget.FrameLayout[contains(@resource-id,'content')]/android.webkit.WebView/android.webkit.WebView"));
//		}
//		catch(Exception e) {
//			
//		}
//		AndroidElement ele1 = driver.findElement(By.xpath("//android.webkit.WebView"));
		try {

//			((AndroidElement) ele)
//					.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView("
//							+ "new UiSelector().textContains(\""+str+"\"));");

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
