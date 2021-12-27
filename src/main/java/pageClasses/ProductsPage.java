package pageClasses;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import utility.JavaScriptOperations;
import utility.OperationHelper;
import utility.ScrollOptions;

public class ProductsPage {
	WebDriver driver;
	OperationHelper helper;
	ScrollOptions scroll;
	JavaScriptOperations jso;
	public ProductsPage(WebDriver driver) {
		this.driver=driver;
		helper=new OperationHelper(driver);
		PageFactory.initElements(driver, this);	
		scroll=new ScrollOptions(driver);
		jso=new JavaScriptOperations(driver);
	}
	
	@FindBy(how=How.XPATH, using="//span[text()='SORT BY : ']/i")
	WebElement sortOption_btn;
	
	@FindBy(how=How.XPATH, using="//span[text()='Price : High to Low']")
	WebElement highToLow_radioBtn;
	
	@FindBy(how=How.XPATH, using="//span[text()='SORT BY : ']/parent::button/parent::div/following-sibling::div/div")
	List<WebElement> productsList;
	
	@FindBy(how=How.XPATH, using="//span[text()='(₹']/parent::div")
	List<WebElement> products_priceList;
	
	public void clickOnSort() {
		helper.clickOperation(sortOption_btn);
	}
	
	public void clickOnHighToLowOption() {
		helper.clickOperation(highToLow_radioBtn);
	}
	
	public void verifingSortHighToLow() {
		List<Float> beforeSort = new ArrayList<Float>();
		List<Float> afterSort=null;
		int before=0, after=0;
		boolean value=true;
		while (value) {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			before=productsList.size();
			System.out.println(before);
			scroll.ScrollDownToElement(productsList.get(before-1));
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			after=productsList.size();
			System.out.println(after);
			value=false;
			if (after==before) {
				value=false;
			}
		}
		System.out.println("before value : "+before+" after value : "+after);
		scroll.ScrollDownToElement(sortOption_btn);
//		jso.clickUsingJS(sortOption_btn);
//		clickOnHighToLowOption();
		Iterator<WebElement> priceElement=products_priceList.iterator();
		while (priceElement.hasNext()) {
			WebElement webElement =  priceElement.next();
			String[] actualPrice=webElement.getText().split("\\₹");
			Float n=Float.valueOf(actualPrice[actualPrice.length-1]);
			beforeSort.add(n);
		}
		System.out.println("before : "+beforeSort);
		afterSort=beforeSort;
		Collections.sort(afterSort);
		System.out.println("After : "+afterSort);
	}

	
	

}
