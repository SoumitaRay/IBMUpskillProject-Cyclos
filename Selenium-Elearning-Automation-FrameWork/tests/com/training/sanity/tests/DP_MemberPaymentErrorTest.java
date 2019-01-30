package com.training.sanity.tests;

import static org.junit.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.training.dataproviders.LoginDataProviders;
import com.training.generics.ScreenShot;
import com.training.pom.LoginPOM;
import com.training.pom.MakeMemberPayment_Admin;
import com.training.pom.Member_to_MemberPayment;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class DP_MemberPaymentErrorTest {
	private WebDriver driver;
	private static String baseUrl;
	private LoginPOM loginPOM;
	
	private MakeMemberPayment_Admin makemempayment_admin;
	private Member_to_MemberPayment memtomemPay;

	
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
		makemempayment_admin = new MakeMemberPayment_Admin(driver);
		memtomemPay= new Member_to_MemberPayment(driver);
		
	   
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
	//	driver.quit();
	}
	

	  @Test(priority=1)
		
		public void loginPassTest() 
		{

			System.out.println("------Testcase : CYTC_080 : Member Login -------");
			loginPOM.sendUserName("Soumita123");
			loginPOM.sendPassword("test1234");
			loginPOM.clickLoginBtn(); 
			
		}
	  
	  
  @Test(priority=2 ,dataProvider="excel-inputs_TC80", dataProviderClass = LoginDataProviders.class)
  
  public void MemberPayment_member(String name,String amount ,String desc) throws InterruptedException
  {
	  System.out.println("------Testcase : CYTC_080 : Member Payment"+name+ "-------");
	 	memtomemPay.makeMemberPayment_Member(name, amount, desc);
	 	Thread.sleep(2000);
		makemempayment_admin.gotoHome();
	//	schpay_member.validate_searchResult_SchPayment("19/02/2019","Selenium1","0,55","Processed");
  }
  
  
	
}
