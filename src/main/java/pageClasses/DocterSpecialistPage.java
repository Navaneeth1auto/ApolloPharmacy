package pageClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import utility.OperationHelper;
import utility.WaitClass;

public class DocterSpecialistPage {
	WebDriver driver;
	OperationHelper helper;
	WaitClass wait;
	public DocterSpecialistPage(WebDriver driver) {
		this.driver=driver;
		helper=new OperationHelper(driver);
		wait=new WaitClass(driver);
		PageFactory.initElements(driver, this);
		isLoaded();
	}
	
	@FindBy(how=How.XPATH, using="//h6[text()='Popular Cities']/following-sibling::ul/descendant::a[text()='New Delhi']")
	WebElement new_delhi_btn;
	
	@FindBy(how=How.XPATH, using="//h2[text()='Top Specialties']/parent::div/following-sibling::div/descendant::h3[text()='Dermatology']")
	WebElement dermatologies_btn;
	
	public void isLoaded() {
		helper.visibiltyOfElement(dermatologies_btn);
	}
	public void clickOnDelhi() {
		helper.clickOperation(new_delhi_btn);
	}
	
	public void clickOnDermatologies() {
		helper.clickOperation(dermatologies_btn);
	}
	
	public void clickOnSpecialization(String specialization) {
		By locator=By.xpath("//h2[text()='Other Specialties']/parent::div/following-sibling::div/descendant::div[contains(text(),'"+specialization+"')]");
		WebElement spe_element=wait.visibilityOfElement(locator);
		helper.clickOperation(spe_element);
	}
}
