package pharmacy;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import baseFolder.BaseClass;
import pageClasses.HomePage;
import pageClasses.PharmacyPage;
import pageClasses.ProductsPage;

public class ValidatingTheFunctionalityOfSortInRecomendation extends BaseClass{
	HomePage home;
	PharmacyPage pharmacy;
	ProductsPage prod;
	@BeforeMethod
	public void setup() {
		browserSetup();
//		openApplication();
//		home=new HomePage(driver);
//		home.clickOnPharmacyOption();
//		pharmacy=new PharmacyPage(driver);
//		pharmacy.clickOnDiabetesCare();	
		driver.get("https://www.apollopharmacy.in/shop-by-health-conditions/diabetic");
	}
	
	@Test
	public void checkingTheFunctionalityOfHighToLow() {
		prod=new ProductsPage(driver);
//		prod.clickOnSort();
//		prod.clickOnHighToLowOption();
		prod.verifingSortHighToLow();
	}
}
