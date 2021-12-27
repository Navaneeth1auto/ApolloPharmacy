package utility;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ScrollOptions {
	WebDriver driver;
	JavascriptExecutor js;
	OperationHelper helper;
	public ScrollOptions(WebDriver driver) {
		this.driver=driver;
		js=(JavascriptExecutor)driver;
	}
	
	public void scrollDown() {
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void ScrollDownToElement(WebElement element) {
		//helper.visibiltyOfElement(element);
		js.executeScript("arguments[0].scrollIntoView();", element);
	}
}
