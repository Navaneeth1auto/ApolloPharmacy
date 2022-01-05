package pageClasses;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.util.Timer.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import baseFolder.BaseClass;
import lombok.ToString;
import utility.OperationHelper;
import utility.ScrollOptions;
import utility.WaitClass;

public class DoctersListPage extends BaseClass{
	WebDriver driver;
	OperationHelper helper;
	WaitClass wait;
	ScrollOptions scroll;
	public DoctersListPage(WebDriver driver) {
		this.driver=driver;
		helper=new OperationHelper(driver);
		wait=new WaitClass(driver);
		scroll=new ScrollOptions(driver);
		PageFactory.initElements(driver, this);
		isLoaded();
	}
	
	@FindBy(how=How.XPATH, using="//span[text()='Book Appointment']")
	WebElement bookAppointment_btn;

	@FindBy(how=How.XPATH, using="//span[text()='Availability']/parent::p/following-sibling::*")
	WebElement sortBy_dropdown;
	
	@FindBy(how=How.XPATH, using="//div[contains(@class,'MuiGrid-root MuiGrid-container')]/div")
	List<WebElement> noOfDoctersInPage;

	@FindBy(how=How.XPATH, using="//li[text()='Years of Experience']")
	WebElement yearsOfExp_option;

	@FindBy(how=How.XPATH, using="//div[contains(text(),'available ')]/following-sibling::div[2]")
	List<WebElement> namesList;

	@FindBy(how=How.XPATH, using="//div[contains(text(),'available ')]/following-sibling::div/h2[@title='Specialty']")
	List<WebElement> specialigationList;

	@FindBy(how=How.XPATH, using="//div[contains(text(),'available ')]/following-sibling::div/span[@title='Experience']")
	List<WebElement> experianceList;

	@FindBy(how=How.XPATH, using="//div[contains(text(),'available ')]/following-sibling::div/descendant::span[text()='You pay ']/p")
	List<WebElement> feesList;

	@FindBy(how=How.XPATH, using="//div[contains(@class,'MuiGrid-root MuiGrid-container')]/descendant::div[@title='Location']/p[2]")
	List<WebElement> locationList;
	
	@FindBy(how=How.XPATH, using="(//p[text()='All Cities'])[2]")
	WebElement citiesOptions_dropdown;
	
	void isLoaded(){
		helper.visibiltyOfElement(bookAppointment_btn);
	}
	
	public void clickOnSortBy() {
		helper.clickOperation(sortBy_dropdown);
	}

	public void clickOnYearsOfExp() {
		helper.clickOperation(yearsOfExp_option);
	}
	
	public void selectCity(String cityName) {
		helper.clickOperation(citiesOptions_dropdown);
		By locator=By.xpath("(//h6[text()='Popular Cities']/following-sibling::button/span[contains(text(),'"+cityName+"')])[2]");
		wait.visibilityOfElement(locator).click();
	}

	public List<Object> collectingDoctorsDetails() {
		isLoaded();
		List<Object> doctorsInfo=null;
		doctorsInfo=new ArrayList<Object>();
		DocterDetails detail=null;
		boolean value=true;
		while(value) {
			int beforeScroll=namesList.size();
			System.out.println(beforeScroll);
			WebElement element=namesList.get(beforeScroll-1);
			//scroll.ScrollDownToElement(element);
//			try {
//				Thread.sleep(2000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			int afterScroll=namesList.size();
			System.out.println(afterScroll);
			if(beforeScroll==afterScroll) {
				value=false;
			}
		}
		System.out.println(noOfDoctersInPage.size());
		for(int i=0; i<namesList.size();i++) {
			String name=namesList.get(i).getText();

			String special=specialigationList.get(i).getText();

			String[] exp=experianceList.get(i).getText().split(" ");
			int years=Integer.parseInt(exp[0]);

			String[] payment=feesList.get(i).getText().split(" ");
			int fee=Integer.parseInt(payment[payment.length-1]);
			
			String[] location=locationList.get(i).getText().split(", ");
			String address=location[location.length-1];

			detail=new DocterDetails(name, special, years, fee, address);
			doctorsInfo.add(detail);
		}
		System.out.println("No of Docters are : "+doctorsInfo.size());
		
		return doctorsInfo;
	}
	
	public void compareLocation(String location) {
		boolean status=true;
		List<Object> docterDetails=collectingDoctorsDetails();
		System.out.println(docterDetails.size());
		Iterator<Object> details=docterDetails.iterator();
		while(details.hasNext()) {
			Object info=details.next();
			String expected=((DocterDetails) info).getAddress();
			try {
				if(location.contentEquals(expected))
					log.info("PASS : "+expected);
				else {
					log.info("Fail : "+expected);
					status=false;
				}
			} catch (Exception e) {
				System.out.println(e);
			}
		}
			Assert.assertEquals(status, true);
			log.info("After assert statement in Compare Location");
	}
	
	public void printDocterDetails() {
		List<Object> docterDetails=collectingDoctorsDetails();
		System.out.println("No of Docters are : "+docterDetails.size());
		Iterator<Object> details=docterDetails.iterator();
		while(details.hasNext()) {
			DocterDetails obj=(DocterDetails)details.next();
			log.info(obj);
		}
	}
}

class DocterDetails implements Comparable<DocterDetails>{
	String name; String expert; int experiance; int fees; String address;
	public DocterDetails(String name, String expert, int experiance, int fees, String address) {
		this.name=name;
		this.expert=expert;
		this.experiance=experiance;
		this.fees=fees;
		this.address=address;
	}
	public String getName() {
		return this.name;
	}

	public int getFee() {
		return this.fees;
	}
	
	public String getAddress() {
		return this.address;
	}
	
	
	@Override
	public String toString() {
		return "DocterDetails [name=" + name + ", expert=" + expert + ", experiance=" + experiance + ", fees=" + fees
				+ ", address=" + address + "]";
	}
	public int compareTo(DocterDetails detail) {
		return fees-detail.fees;
	}

}
