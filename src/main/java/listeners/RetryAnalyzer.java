package listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public  class RetryAnalyzer implements IRetryAnalyzer{

	public boolean retry(ITestResult result) {
		int Count=0;
		int retryLimit=3;
		if(retryLimit>Count)
			return true;
		return false;
	}

}
