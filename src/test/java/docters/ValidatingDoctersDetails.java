package docters;

import org.apache.logging.log4j.LogManager;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import baseFolder.BaseClass;
import pageClasses.DoctersListPage;

public class ValidatingDoctersDetails  extends BaseClass{
	DoctersListPage docList;
	@BeforeMethod
	public void setup() {
		browserSetup();
		log=LogManager.getLogger();
		driver.get("https://www.apollo247.com/doctors/doctors-in-bangalore-dcity");
		log.info("Page Title : "+driver.getTitle());
	}
	
	@Test()
	public void gettingDocterDetailsBasedOnExperiance() {
		docList=new DoctersListPage(driver);
//		docList.clickOnSortBy();
//		docList.clickOnYearsOfExp();
		docList.collectingDoctorsDetails();
	}
}
