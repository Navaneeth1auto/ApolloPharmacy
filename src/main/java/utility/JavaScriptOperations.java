package utility;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptOperations {
	WebDriver driver;
	JavascriptExecutor js;
	public JavaScriptOperations(WebDriver driver) {
		this.driver=driver;
		js=(JavascriptExecutor)driver;
	}

	public void clickUsingJS(WebElement element) {
		js.executeScript("arguments[0].click();", element);
	}
}
