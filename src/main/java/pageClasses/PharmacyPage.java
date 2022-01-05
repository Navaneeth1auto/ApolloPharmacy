package pageClasses;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import baseFolder.BaseClass;
import utility.OperationHelper;
import utility.ScrollOptions;

public class PharmacyPage extends BaseClass{
	WebDriver driver;
	OperationHelper helper;
	ScrollOptions scroll;
	WebDriverWait wait;
	public PharmacyPage(WebDriver driver) {
		this.driver=driver;
		wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		scroll=new ScrollOptions(driver);
		helper=new OperationHelper(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(how=How.XPATH, using="//p[text()='Select your location']/parent::div")
	WebElement locationOption_header;

	@FindBy(how=How.XPATH, using="//p[text()='Enter delivery pincode']")
	WebElement enterPincode_btn;

	@FindBy(how=How.XPATH, using="//h2[text()='Hi! :)']/parent::div/descendant::input")
	WebElement enterPincodeHere_txtBox;

	@FindBy(how=How.XPATH, using="//button[text()='Submit']")
	WebElement submit_btn;

	@FindBy(how=How.XPATH, using="//div[contains(text(),'Sorry, we are not servicing in your area currently.')]")
	WebElement error_msg;

	@FindBy(how=How.XPATH, using="//span[text()='Hello,']")
	WebElement Hello_txt;

	@FindBy(how=How.XPATH, using="//div[text()='Diabetes Care']")
	WebElement diabetesCase_option;

	@FindBy(how=How.XPATH, using="//h3[text()='HOT SELLERS']")
	WebElement hotSellers_visibleText;												// Hot sellers Visible Text

	@FindBy(how=How.XPATH, using="//div[@id='hotsellers-HOT SELLERS-slider']/child::div")
	List<WebElement> hotSellersItems_list;

	public void clickOnLocationHeader() {
		helper.clickOperation(locationOption_header);
	}

	public String getTextFromEnterPincodeBtn() {
		try {
			return helper.returningVisibleText(enterPincode_btn);
		} catch (Exception e) {
			helper.clickOperation(locationOption_header);
			return helper.returningVisibleText(enterPincode_btn);
		}

	}

	public void clickOnEnterPincodeBtn() {
		try {
			helper.clickOperation(enterPincode_btn);
		} catch (Exception e) {
			helper.clickOperation(locationOption_header);
			helper.clickOperation(enterPincode_btn);
		}
	}

	public void enterPincodeInTxtField(String pincode) {
		try {
			helper.enterValueInTextField(enterPincodeHere_txtBox, pincode);
			helper.clickOperation(submit_btn);
			try {
				String msg=helper.returningVisibleText(error_msg);
				System.out.println(msg);
			} catch (TimeoutException e) {
				System.out.println("correct password");
			}
		} catch (ElementClickInterceptedException e) {
			System.out.println("The Pincode should not be Empty and can't be lessthan 6 digits");
		}

	}

	public void clickOnDiabetesCare() {
		helper.clickOperation(diabetesCase_option);
	}

	public void clickOnAddToCart() throws InterruptedException {
		ProductDetails details;
		ArrayList<Object> productDetailsList = new ArrayList<Object>();
		scroll.ScrollDownToElement(hotSellers_visibleText);
		for(int i=1;i<=5;i++) {
			try {
				By nameLoc=By.xpath("(//div[@id='hotsellers-HOT SELLERS-slider']/descendant::div[contains(@class,'HotSeller_productTitle')])["+i+"]");
				wait.until(ExpectedConditions.visibilityOfElementLocated(nameLoc));
			} catch (Exception e) {
				driver.findElement(By.xpath("(//div[@class='next'])[2]")).click();
				Thread.sleep(1000);
			}
			
			String name=driver.findElement(By.xpath("(//div[@id='hotsellers-HOT SELLERS-slider']/descendant::div[contains(@class,'HotSeller_productTitle')])["+i+"]")).getText();   
			log.info(name);
			String priceStr=driver.findElement(By.xpath("(//div[@id='hotsellers-HOT SELLERS-slider']/descendant::span[contains(text(),'Rs.')]/following-sibling::span)["+i+"]")).getText();
			String[] priceArray=priceStr.split(" ");
			log.info(Arrays.toString(priceArray));
			Float cost=Float.parseFloat(priceArray[priceArray.length-1]);
			WebElement addToCartEle=driver.findElement(By.xpath("(//div[@id='hotsellers-HOT SELLERS-slider']/descendant::button[text()='Add To Cart'])["+i+"]"));
			details=new ProductDetails(name, cost, addToCartEle);
			productDetailsList.add(details);
		}
		System.out.println();
//		for(int i=0;i<hotSellersItems_list.size();i++) {
//			System.out.print(((ProductDetails)productDetailsList.get(i)).getName());
//			System.out.print(" *** cost : "+((ProductDetails)productDetailsList.get(i)).getCost());
//			System.out.println();
//		}
		((ProductDetails)productDetailsList.get(0)).getElement().click();
		log.info(" ******  END  *****");
	}
}


class ProductDetails {																// Class to Store Product Details
	String name;
	Float cost;
	WebElement element;
	public ProductDetails(String itemName, Float cost, WebElement addToCart_ele) {
		this.name=itemName;
		this.cost=cost;
		this.element=addToCart_ele;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Float getCost() {
		return cost;
	}
	public void setCost(Float cost) {
		this.cost = cost;
	}
	public WebElement getElement() {
		return element;
	}
	public void setElement(WebElement element) {
		this.element = element;
	}
}











