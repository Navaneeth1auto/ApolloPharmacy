package pharmacy;

import org.apache.logging.log4j.LogManager;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import baseFolder.BaseClass;
import pageClasses.HalfPriceStoreInPharmacyPage;
import utility.ScreenShotClass;

public class ValidatingTheHalfPriceStoreFunctionality extends BaseClass{
	HalfPriceStoreInPharmacyPage halfPrice;
	public static ExtentSparkReporter extentSpark;
	public static ExtentReports extentReport;
	public static ExtentTest extentLog;
	ScreenShotClass shot;
	@BeforeClass
	public void classSetup() {
		log=LogManager.getLogger();
		extentReport=new ExtentReports();
		extentSpark=new ExtentSparkReporter(System.getProperty("user.dir")+"/ExtentReports/"+this.getClass()+".html");
		extentReport.attachReporter(extentSpark);
	}
	
	@BeforeMethod
	public void setup() {
		browserSetup();
		driver.get("https://www.apollopharmacy.in/");
		log.info("Page Title : "+driver.getTitle());
	}
	
	@Test(priority=2)
	public void checkingTheFunctionalityOfNextSymbol() throws InterruptedException {
		shot=new ScreenShotClass(driver);
		extentLog=extentReport.createTest("Next Symbol click");
		halfPrice=new HalfPriceStoreInPharmacyPage(driver);
		extentLog.addScreenCaptureFromPath(shot.takeScreenshot("before"), "Before Click");
		halfPrice.clickOnNextSymbol();
		extentLog.addScreenCaptureFromPath(shot.takeScreenshot("after"), "After Click");
		extentLog.pass("click on Next Sysmbel is working");
		extentLog.info("before Next click", MediaEntityBuilder.createScreenCaptureFromPath(shot.takeScreenshot("next")).build());
	}
	
	@Test(enabled = false)
	public void addToCartByProductName() {
		extentLog=extentReport.createTest("addToCartByProductName");
		halfPrice=new HalfPriceStoreInPharmacyPage(driver);
		halfPrice.addToCart("Set Wet");
		extentLog.pass("Add to Cart is succesfull");
	}
	
	
	@AfterMethod
	public void closing() {
		extentReport.flush();
	}
}
