package pageClasses;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import baseFolder.BaseClass;
import utility.OperationHelper;

public class HomePage extends BaseClass {
	WebDriver driver;
	OperationHelper helper;
	public HomePage(WebDriver driver) {
		log=LogManager.getLogger();
		this.driver=driver;
		helper=new OperationHelper(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(how=How.XPATH, using="//span[text()='Pharmacy']")
	WebElement pharmacy_header;
	
	@FindBy(how=How.XPATH, using="//button[text()='No thanks']")
	WebElement No_ThanksBtn;
	
	public void clickOnPharmacyOption() {
		try {
			helper.clickOperation(pharmacy_header);
		} catch (Exception e) {
			helper.clickOperation(No_ThanksBtn);
			helper.clickOperation(pharmacy_header);
		}
		
	}
}
