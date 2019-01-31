package com.training.sanity.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.training.dataproviders.LoginDataProviders;
import com.training.generics.ScreenShot;
import com.training.pom.GrantLoan_Admin;
import com.training.pom.LoginPOM;
import com.training.pom.MakeMemberPayment_Admin;
import com.training.pom.MemberProfile;
import com.training.pom.RepayLoan_Member;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class DPExcel_RepayLoan {
	
	private WebDriver driver;
	private static String baseUrl;
	private LoginPOM loginPOM;
	private MemberProfile memberprofile;
	private MakeMemberPayment_Admin makemempayment_admin;
	private GrantLoan_Admin grantLoan_Admin;
	private RepayLoan_Member repayloan;

	
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
		repayloan = new RepayLoan_Member(driver);
		
	   
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

		System.out.println("------Testcase : CYTC_078 :Admin Login -------");
		loginPOM.sendUserName("admin");
		loginPOM.sendPassword("12345");
		loginPOM.clickLoginBtn(); 
		
	}
	
		
  @Test(priority=2 ,dataProvider="excel-inputs_TC78", dataProviderClass = LoginDataProviders.class)
//	@Test(dataProvider="DP_grantLoan")
	
	public void DP_GrantLoanToMember_Admin(String memlogin,String pwd,String loan_amt,String loan_desc,String repay_amt) throws InterruptedException
	
		{	  
	 
		System.out.println("------Testcase : CYTC_078 :Grant Loan to Member using Excel input : " +memlogin+ "-------");
		
		memberprofile.sendMemberUserName(memlogin);
		grantLoan_Admin.click_GrantLoanToMember();
		grantLoan_Admin.grantMemberLoan_Admin(loan_amt, loan_desc);
		Thread.sleep(2000);
		makemempayment_admin.gotoHome();
		
				
	}
  
  
  @Test(priority=3)
	
	public void logoutTest() 
	{

		System.out.println("------Testcase : CYTC_078 :Admin Logout -------");
		loginPOM.logout();
		
	}
	
  
  @Test(priority=4 ,dataProvider="excel-inputs_TC78", dataProviderClass = LoginDataProviders.class,dependsOnMethods = {"DP_GrantLoanToMember_Admin"})
  public void repayLoan_Member(String memlogin,String pwd,String loan_amt,String loan_desc,String repay_amt)
  {
		System.out.println("------Testcase : CYTC_078 : Repay Loan for : "+ memlogin +" -------");
		loginPOM.sendUserName(memlogin);
		loginPOM.sendPassword(pwd);
		loginPOM.clickLoginBtn(); 
		repayloan.assertRepayamount(memlogin, pwd, loan_amt, loan_desc, repay_amt);
		
		boolean paymentsuccess = (new WebDriverWait(driver, 10)).until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//*[contains(text(),'Repaid')]"), "Repaid"));
		System.out.println("flag = " +paymentsuccess);
		loginPOM.logout();
		driver.navigate().refresh();
  }

	
	
}
