package pharmacy;

import org.apache.logging.log4j.LogManager;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import baseFolder.BaseClass;
import pageClasses.PharmacyPage;

public class ValidatingTheFunctionalityOfAddToCartInPharmacy extends BaseClass {
	PharmacyPage pharmacy;
	@BeforeMethod
	public void setup() {
		log=LogManager.getLogger();
		browserSetup();
		driver.get("https://www.apollopharmacy.in/");
		log.info("URL : "+driver.getTitle());
	}
	
	@Test
	public void clickOnAddToCart() throws Throwable {
		pharmacy=new PharmacyPage(driver);
		pharmacy.clickOnAddToCart();
	}
}
