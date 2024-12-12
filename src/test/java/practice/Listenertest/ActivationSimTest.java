package practice.Listenertest;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.comcast.crm.basetest.BaseClass;

public class ActivationSimTest {

	@Test(retryAnalyzer = com.comcast.crm.listenerutility.RetryImplementationClass.class)
	public void createOrderAndActivateSimTest() {
		System.out.println("Execute createOrderAndActivateSimTest");
		Assert.assertEquals(" ", "Login");      //(intentionally failing the testCase)
		System.out.println("Step-1");
		System.out.println("Step-2");
		System.out.println("Step-3");
		System.out.println("Step-4");
	}
}
