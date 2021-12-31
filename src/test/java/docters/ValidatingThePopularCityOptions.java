package docters;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import baseFolder.BaseClass;
import pageClasses.DocterSpecialistPage;
import pageClasses.DoctersListPage;

public class ValidatingThePopularCityOptions extends BaseClass{
	public ExtentTest extentLog;
	DocterSpecialistPage spList;
	DoctersListPage dcList;
	@BeforeClass
	public void classSetup() {
		File reportFile=new File(System.getProperty("user.dir")+"/ExtentReports/"+this.getClass()+".html");
		extentSpark=new ExtentSparkReporter(reportFile);
		extentReport=new ExtentReports();
		extentReport.attachReporter(extentSpark);
		browserSetup();
	}
	
	@BeforeMethod
	public void methodSetup() {
		//openApplication();
		driver.get("https://www.apollo247.com/specialties");
	}
	
	@Test(enabled=false)
	public void chekingTheFunctionalityOfCityButtons() {
		extentLog=extentReport.createTest("Checking the functionality of Delhi button");
		spList=new DocterSpecialistPage(driver);
		spList.clickOnDelhi();
		extentLog.info("Clicked on Delhi succesful");
	}
	
	@Test
	public void verifyingTheResultsOfCitysButton() {
		extentLog=extentReport.createTest("checking the results of City list docter");
		spList=new DocterSpecialistPage(driver);
		spList.clickOnDelhi();
		extentLog.info("Clicked on Delhi succesful");
		dcList=new DoctersListPage(driver);
		dcList.compareLocation("Hyderabad");
		extentLog.info("completed");
	}
	
	@AfterClass
	public void closingClass() {
		extentReport.flush();
	}
}
