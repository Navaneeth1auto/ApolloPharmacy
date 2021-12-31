package pageClasses;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.util.Timer.Status;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import baseFolder.BaseClass;
import lombok.ToString;
import utility.OperationHelper;

public class DoctersListPage extends BaseClass{
	WebDriver driver;
	OperationHelper helper;
	public DoctersListPage(WebDriver driver) {
		this.driver=driver;
		helper=new OperationHelper(driver);
		PageFactory.initElements(driver, this);
	}

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
	
	public void clickOnSortBy() {
		helper.clickOperation(sortBy_dropdown);
	}

	public void clickOnYearsOfExp() {
		helper.clickOperation(yearsOfExp_option);
	}

	public List<Object> collectingDoctorsDetails() {
		List<Object> doctorsInfo=new ArrayList<Object>();
		DocterDetails detail=null;
		//System.out.println("Name size "+namesList.size()+" specialigation size : "+specialigationList.size()+" experiance size : "+experianceList.size()+" fees size : "+feesList.size());
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
		System.out.println(doctorsInfo.size());
//		Collections.sort(doctorsInfo, null);
//		for(int i=0;i<doctorsInfo.size();i++) {
//			System.out.println("names : "+((DocterDetails) doctorsInfo.get(i)).getName()+"***"+"Fees : "+((DocterDetails) doctorsInfo.get(i)).getFee());
//		}
		return doctorsInfo;
	}
	
	public void compareLocation(String location) {
		List<Object> docterDetails=collectingDoctorsDetails();
		System.out.println(docterDetails.size());
		Iterator<Object> details=docterDetails.iterator();
		while(details.hasNext()) {
			Object info=details.next();
			String expected=((DocterDetails) info).getAddress();
			try {
				if(location.contentEquals(expected))
					log.info("PASS : "+expected);
				else
					log.info("Fail : "+expected);
			} catch (Exception e) {
				System.out.println(e);
			}
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
