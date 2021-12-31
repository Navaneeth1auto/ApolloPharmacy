package pageClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import utility.OperationHelper;

public class DocterSpecialistPage {
	WebDriver driver;
	OperationHelper helper;
	public DocterSpecialistPage(WebDriver driver) {
		this.driver=driver;
		helper=new OperationHelper(driver);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(how=How.XPATH, using="//h6[text()='Popular Cities']/following-sibling::ul/descendant::a[text()='New Delhi']")
	WebElement new_delhi_btn;
	
	public void clickOnDelhi() {
		helper.clickOperation(new_delhi_btn);
	}
}
