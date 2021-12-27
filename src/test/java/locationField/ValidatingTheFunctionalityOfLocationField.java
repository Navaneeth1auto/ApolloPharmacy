package locationField;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import baseFolder.BaseClass;
import pageClasses.HomePage;
import pageClasses.PharmacyPage;

public class ValidatingTheFunctionalityOfLocationField extends BaseClass{
	HomePage home;
	PharmacyPage pharmacy;
	@BeforeMethod
	public void openingApp() {
		browserSetup();
		openApplication();
		home=new HomePage(driver);
		home.clickOnPharmacyOption();
		log.info("opening App is done succesfully");
	}
	@Test(enabled=false)
	public void checkingTheClickOperetionOnLocationField() {
		String expected="Enter delivery pincode";
		pharmacy=new PharmacyPage(driver);
		pharmacy.clickOnLocationHeader();
		String data=pharmacy.getTextFromEnterPincodeBtn();
		System.out.println(data);
		Assert.assertEquals(data, expected);
	}

	@Test()
	public void checkinTheFunctionalityOfEnterPinCodePopUp() {
		pharmacy=new PharmacyPage(driver);
		pharmacy.clickOnLocationHeader();
		pharmacy.clickOnEnterPincodeBtn();
		pharmacy.enterPincodeInTxtField("583101");
	}
	@Test()
	public void checkinTheFunctionalityOfEnterPinCode() {
		pharmacy=new PharmacyPage(driver);
		pharmacy.clickOnLocationHeader();
		pharmacy.clickOnEnterPincodeBtn();
		pharmacy.enterPincodeInTxtField("555555");
	}
	@Test()
	public void checkinTheFunctionalityOfEnterPinCodePop() {
		pharmacy=new PharmacyPage(driver);
		pharmacy.clickOnLocationHeader();
		pharmacy.clickOnEnterPincodeBtn();
		pharmacy.enterPincodeInTxtField("58310");
	}
}
