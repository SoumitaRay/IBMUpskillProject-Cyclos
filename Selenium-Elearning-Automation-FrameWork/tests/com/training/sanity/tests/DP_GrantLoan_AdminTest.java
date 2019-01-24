package com.training.sanity.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.training.dataproviders.LoginDataProviders;
import com.training.generics.ScreenShot;
import com.training.pom.GrantLoan_Admin;
import com.training.pom.LoginPOM;
import com.training.pom.MakeMemberPayment_Admin;
import com.training.pom.MemberProfile;
import com.training.readexcel.ApachePOIExcelRead;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class DP_GrantLoan_AdminTest {
	
	private WebDriver driver;
	private static String baseUrl;
	private LoginPOM loginPOM;
	private MemberProfile memberprofile;
	private MakeMemberPayment_Admin makemempayment_admin;
	private GrantLoan_Admin grantLoan_Admin;

	
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
	

	
		
  @Test(dataProvider="excel-inputs_TC76", dataProviderClass = LoginDataProviders.class)
//	@Test(dataProvider="DP_grantLoan")
	
	public void DP_GrantLoanToMember_Admin(String memlogin,String amount,String desc) throws InterruptedException
	
		{	  
	 
		System.out.println("------Testcase : CYTC_076 :Grant Loan as Admin to multiple Member using Excel input -------");
		
		loginPOM.sendUserName("admin");
		loginPOM.sendPassword("12345");
		loginPOM.clickLoginBtn(); 
		
		makemempayment_admin.gotoHome();
		memberprofile.sendMemberUserName(memlogin);
		grantLoan_Admin.click_GrantLoanToMember();
		String ExpMemname = memlogin;
		String ActMemname = grantLoan_Admin.assertMembername();
		Assert.assertEquals(ActMemname, ExpMemname);
		grantLoan_Admin.grantMemberLoan_Admin(amount, desc);
		
	}

	@ DataProvider(name="DP_grantLoan")
	  public Object[] MemberList()throws Exception{
		  String filename = "C:\\Users\\SOUMITARAY\\ProjectCyclos\\Login_GrantLoan.xlsx";
		  Object[] testObjArray = new ApachePOIExcelRead().getExcelContent(filename);
	      return(testObjArray);
	
}
}
