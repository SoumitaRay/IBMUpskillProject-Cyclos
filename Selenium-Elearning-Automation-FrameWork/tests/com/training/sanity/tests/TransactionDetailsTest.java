package com.training.sanity.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.LoginPOM;
import com.training.pom.MakeMemberPayment_Admin;
import com.training.pom.MemberProfile;
import com.training.pom.TransactionDetails;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class TransactionDetailsTest {
	


	private WebDriver driver;
	private static String baseUrl;
	private LoginPOM loginPOM;
	private MemberProfile memberprofile;
	private TransactionDetails trxDetails;
	
	
	private static Properties properties;
	private static ScreenShot screenShot;

	@BeforeClass
	public void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
		baseUrl = properties.getProperty("baseURL");
	   screenShot = new ScreenShot(driver); 
	   driver = DriverFactory.getDriver(DriverNames.CHROME);
		loginPOM = new LoginPOM(driver); 
		memberprofile = new MemberProfile(driver);
		trxDetails = new TransactionDetails(driver);
		
	   
	// open the browser 
	   driver.get(baseUrl);
	}

	@BeforeMethod
	public void setUp() throws Exception {
//		driver = DriverFactory.getDriver(DriverNames.CHROME);
//		loginPOM = new LoginPOM(driver); 
//		memberprofile = new MemberProfile(driver);
//		makemempayment_admin = new MakeMemberPayment_Admin(driver);
//		
//		baseUrl = properties.getProperty("baseURL");
//		screenShot = new ScreenShot(driver); 
//		// open the browser 
//		driver.get(baseUrl);
		
	}
	
	@AfterMethod
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}
	
		
		
  @Test(dataProvider="ViewLoanRecords")

  
  public void ViewTrxDetails(String description , String amount) throws InterruptedException
	{
		 System.out.println("------Testcase : CYTC_020 -------");
		 	loginPOM.sendUserName("admin");
			loginPOM.sendPassword("12345");
			loginPOM.clickLoginBtn();
			memberprofile.sendMemberUserName("SoumitaAAA");
			memberprofile.click_AccountInfo();
			trxDetails.clickView_trx1(description, amount);
		
	}
  
  @DataProvider(name="ViewLoanRecords")
  public Object[][] getDataFromDataprovider(){
  return new Object[][] 
  	{
          { "test loan - 1", "3,00 units" },
        
      };
}

}