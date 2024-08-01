package com.Generic_Utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryImplclass implements IRetryAnalyzer {

	int count=0;
	int retryLimit=2;
	
	public boolean retry(ITestResult result)
	{
		if(count<retryLimit)
		{
			count++;
			return true;
		}
		return false;
	}
	
}
