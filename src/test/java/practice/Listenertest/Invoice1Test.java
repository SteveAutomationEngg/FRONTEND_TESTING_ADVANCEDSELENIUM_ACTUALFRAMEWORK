package practice.Listenertest;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.comcast.crm.basetest.BaseClass;


public class Invoice1Test extends BaseClass{

	@Test
	public void createInvoice1Test() {
		System.out.println("Execute createInvoiceTest");
		String actTitle = driver.getTitle();
		Assert.assertEquals("actTitle", "Login");
		System.out.println("Step-1");
		System.out.println("Step-2");
		System.out.println("Step-3");
		System.out.println("Step-4");
	}
	
	@Test
	public void createInvoice1WithContactTestTest() {
		System.out.println("Execute createInvoiceWithContactTestTest");
		System.out.println("Step-1");
		System.out.println("Step-2");
		System.out.println("Step-3");
		System.out.println("Step-4");
	}
}
