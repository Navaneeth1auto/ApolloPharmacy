package pageClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import utility.OperationHelper;
import utility.ScrollOptions;
import utility.WaitClass;

public class HalfPriceStoreInPharmacyPage {
	WebDriver driver;
	OperationHelper helper;
	ScrollOptions scroll;
	WaitClass wait;
	public HalfPriceStoreInPharmacyPage(WebDriver driver) {
		this.driver=driver;
		helper=new OperationHelper(driver);
		wait=new WaitClass(driver);
		scroll=new ScrollOptions(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(how=How.XPATH, using="//h3[text()='HALF PRICE STORE']")
	WebElement halfPriceStore_visibleText;

	@FindBy(how=How.XPATH, using="//h3[text()='HALF PRICE STORE']/parent::div/parent::div/descendant::*[@class='next']")
	WebElement Next_btn;

	public void clickOnNextSymbol() throws InterruptedException {
		scroll.ScrollDownToElement(halfPriceStore_visibleText);
		helper.clickOperation(Next_btn);
	}

	public void addToCart(String ProductName) {
		scroll.ScrollDownToElement(halfPriceStore_visibleText);
		By locator=By.xpath("//h3[text()='HALF PRICE STORE']/parent::div/parent::div/descendant::*[contains(text(),'"+ProductName+"')]/ancestor::div[@class='HotSeller_card__1jLpg']/descendant::button[text()='Add To Cart']");
		WebElement addToCartEle=null;
		try {
			//wait.visibilityOfElement(locator);
			addToCartEle=driver.findElement(locator);
		} catch (Exception e) {
			System.out.println("catch");
			try {
				while (Next_btn.isDisplayed()) {
					try {
						//wait.visibilityOfElement(locator);
						addToCartEle=driver.findElement(locator);
						break;
					} catch (Exception e2) {
						Next_btn.click();
					}
				}
			}
			catch (Exception e1) {
				System.out.println("No such Product in Half Price Store");
			}
		}

		addToCartEle.click();
	}
}
