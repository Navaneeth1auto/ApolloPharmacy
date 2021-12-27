package utility;

import java.time.Duration;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import baseFolder.BaseClass;

public class OperationHelper extends BaseClass {
	WebDriver driver;
	WebDriverWait wait;
	public OperationHelper(WebDriver driver) {
		log=LogManager.getLogger();
		this.driver=driver;
		wait=new WebDriverWait(driver, Duration.ofSeconds(2));
	}

	public void clickOperation(WebElement element) {
		wait.until(ExpectedConditions.elementToBeClickable(element)).click();
		log.info("Click operation successful");
	}
	
	public String returningVisibleText(WebElement element) {
		return wait.until(ExpectedConditions.visibilityOf(element)).getText();	
	}
	
	public void enterValueInTextField(WebElement element,String data) {
		 wait.until(ExpectedConditions.visibilityOf(element)).sendKeys(data);
	}
	
	public void visibiltyOfElement(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void visibilityOfElementsList(List<WebElement> elements) {
		wait.until(ExpectedConditions.visibilityOfAllElements(elements));
	}
}
