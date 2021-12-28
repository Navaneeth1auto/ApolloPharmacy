package utility;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitClass {
	WebDriver dirver;
	WebDriverWait wait;
	public WaitClass(WebDriver driver) {
		this.dirver=driver;
		wait=new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	public void waitForPageLoad(String title) {
		wait.until(ExpectedConditions.titleContains(title));
	}
	
	public void visibilityOfElement(By locator) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
}
