package practice.Listenertest;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.comcast.crm.basetest.BaseClass;


@Listeners(com.comcast.crm.listenerutility.ListenerImplementationClass.class)
public class InvoiceTest extends BaseClass{

	@Test
	public void createInvoiceTest() {
		System.out.println("Execute createInvoiceTest");
		String actTitle = driver.getTitle();
	    Assert.assertEquals("actTitle", "Login");      //(intentionally failing the testcase)
		System.out.println("Step-1");
		System.out.println("Step-2");
		System.out.println("Step-3");
		System.out.println("Step-4");
	}
	
	@Test
	public void createInvoiceWithContactTestTest() {
		System.out.println("Execute createInvoiceWithContactTestTest");
		System.out.println("Step-1");
		System.out.println("Step-2");
		System.out.println("Step-3");
		System.out.println("Step-4");
	}
}
