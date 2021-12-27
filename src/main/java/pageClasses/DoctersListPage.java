package pageClasses;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import utility.OperationHelper;

public class DoctersListPage {
	WebDriver driver;
	OperationHelper helper;
	public DoctersListPage(WebDriver driver) {
		this.driver=driver;
		helper=new OperationHelper(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(how=How.XPATH, using="//span[text()='Availability']/parent::p/following-sibling::*")
	WebElement sortBy_dropdown;

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

	public void clickOnSortBy() {
		helper.clickOperation(sortBy_dropdown);
	}

	public void clickOnYearsOfExp() {
		helper.clickOperation(yearsOfExp_option);
	}

	public void collectingDoctorsDetails() {
		List<Object> details=new ArrayList<Object>();
		DocterDetails detail=null;
		System.out.println("Name size "+namesList.size()+" specialigation size : "+specialigationList.size()+" experiance size : "+experianceList.size()+" fees size : "+feesList.size());
		for(int i=0; i<namesList.size();i++) {
			String name=namesList.get(i).getText();

			String special=specialigationList.get(i).getText();

			String[] exp=experianceList.get(i).getText().split(" ");
			int years=Integer.parseInt(exp[0]);

			String[] payment=feesList.get(i).getText().split(" ");
			int fee=Integer.parseInt(payment[payment.length-1]);

			detail=new DocterDetails(name, special, years, fee);
			details.add(detail);
		}
		System.out.println(details.size());
		Collections.sort(details, null);
		for(int i=0;i<details.size();i++) {
			System.out.println("names : "+((DocterDetails) details.get(i)).getName()+"***"+"Fees : "+((DocterDetails) details.get(i)).getFee());
		}
	}
}

class DocterDetails implements Comparable<DocterDetails>{
	String name; String expert; int experiance; int fees;
	public DocterDetails(String name, String expert, int experiance, int fees) {
		this.name=name;
		this.expert=expert;
		this.experiance=experiance;
		this.fees=fees;
	}
	public String getName() {
		return this.name;
	}

	public int getFee() {
		return this.fees;
	}
	public int compareTo(DocterDetails detail) {
		return fees-detail.fees;
	}

}
