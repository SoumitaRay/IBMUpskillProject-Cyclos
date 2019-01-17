package com.training.sanity.tests;

import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.GrantLoan_Admin;
import com.training.pom.LoginPOM;
import com.training.pom.MakeMemberPayment_Admin;
import com.training.pom.MemberProfile;
import com.training.pom.ViewLoan_Admin;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;



public class ViewLoanRecordTest {
	
	

	private WebDriver driver;
	private static String baseUrl;
	private LoginPOM loginPOM;
	private MemberProfile memberprofile;
	private MakeMemberPayment_Admin makemempayment_admin;
	private GrantLoan_Admin grantLoan_Admin;
	private ViewLoan_Admin viewloan_Admin;
	
	
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
		makemempayment_admin = new MakeMemberPayment_Admin(driver);
		grantLoan_Admin= new GrantLoan_Admin(driver);
		viewloan_Admin = new ViewLoan_Admin(driver);
		
		
	   
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
   
  public void ViewLoanforMember_Admin(String description , String amount) throws InterruptedException
  {
 	 System.out.println("------Testcase : CYTC_019 -------");
 	 loginPOM.sendUserName("admin");
 	 loginPOM.sendPassword("12345");
 	 loginPOM.clickLoginBtn(); 
 	 makemempayment_admin.gotoHome();
 	memberprofile.sendMemberUserName("SoumitaAAA");
 	viewloan_Admin.click_ViewLoanforMember();
 	viewloan_Admin.viewMemberLoans_Admin(description, amount);
  }

  @DataProvider(name="ViewLoanRecords")
  public Object[][] getDataFromDataprovider(){
  return new Object[][] 
  	{
          { "test loan - 1", "3,00 units" },
        
      };

  
}

}