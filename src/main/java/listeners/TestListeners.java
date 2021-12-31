package listeners;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListeners implements ITestListener{
	public static Logger log;
	public TestListeners() {
		log=LogManager.getLogger();
	}

	public void onFinish(ITestContext context) {
		log.info(context+" is finished");
	}

	public void onStart(ITestContext context) {
		log.info(context+" is started");
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
	}

	public void onTestFailedWithTimeout(ITestResult result) {
		
	}

	public void onTestFailure(ITestResult result) {
		log.error("Test "+result.getMethod()+" is Failed");
	}

	public void onTestSkipped(ITestResult result) {
		log.error("Test "+result.getMethod()+" is Skipped");
	}

	public void onTestStart(ITestResult result) {
		log.info("Test "+result.getTestName()+" is started.");
	}

	public void onTestSuccess(ITestResult result) {
		log.info("Test "+result.getTestName()+" is Success.");
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

	@Override
	protected void finalize() throws Throwable {
		
	}

}
