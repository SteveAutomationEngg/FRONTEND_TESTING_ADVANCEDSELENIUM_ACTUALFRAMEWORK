package practice.test;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.comcast.crm.generic.fileutility.ExcelUtility;

public class GetProductInfoTest {

	@Test(dataProvider = "getData")
	public void productInfo(String brandName, String productName) {

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		driver.get("https://www.amazon.in/");

		// searech for product
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys(brandName, Keys.ENTER);

		// capture Product info
		String x = "//span[text()='"+ productName +"']/../../../..//span[@class='a-price-whole']";

		String price = driver.findElement(By.xpath(x)).getText();

		System.out.println("product price ::" + price);

		driver.quit();

	}

	@DataProvider
	public Object[][] getData() throws Throwable {

		ExcelUtility elib = new ExcelUtility();

		int rowCount = elib.getRowcount("product");
		System.out.println("row count ="+rowCount);

		Object[][] objArr = new Object[rowCount][2];

		for (int i = 0; i < rowCount; i++) {
			objArr[i][0] = elib.getDataFromExcel("product", i+1, 0);
			objArr[i][1] = elib.getDataFromExcel("product", i+1, 1);
		}

		return objArr;

	}
}
