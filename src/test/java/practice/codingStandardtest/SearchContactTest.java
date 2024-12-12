package practice.codingStandardtest;

import org.testng.annotations.Test;

import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.objectrepositoryutility.LoginPage;

/**
 * test class for Contact module
 * @author MOHIT KUMAR
 */
                                                    //HOVER UR CUSOR OVER METHOD, CLASS, TESTCASE NAME U GET TOOL TIP I.E AUTHOR NAME,PURPOSE OF METHOD,PURPOSE OF TESTCASE AS I HAVE PROVIDED JAVA DOC

public class SearchContactTest extends BaseClass{
    /**
     * Scenario: login()==> navigateContact==>createcontact()==>verify
     */
	
	@Test
	public void searchContactTest() {
		/*step-1 : login to app*/
		LoginPage lp = new LoginPage(driver);
		lp.loginToApp("url", "username", "password");
	}
	
	
}
