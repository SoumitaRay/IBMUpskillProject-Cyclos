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
import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.GrantLoan_Admin;
import com.training.pom.LoginPOM;
import com.training.pom.MakeMemberPayment_Admin;
import com.training.pom.MemberProfile;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class GrantLoan_AdminTest {
	
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
		driver.quit();
	}
	

	
		
  @Test
	public void GrantLoanToMember_Admin() throws InterruptedException
	
		{
	  
	 
		System.out.println("------Testcase : CYTC_018 -------");
		
		loginPOM.sendUserName("admin");
		loginPOM.sendPassword("12345");
		loginPOM.clickLoginBtn(); 
		
		makemempayment_admin.gotoHome();
		memberprofile.sendMemberUserName("SoumitaAAA");
		grantLoan_Admin.click_GrantLoanToMember();
		String ExpMemname = "SoumitaAAA";
		String ActMemname = grantLoan_Admin.assertMembername();
		Assert.assertEquals(ActMemname, ExpMemname);
		grantLoan_Admin.grantMemberLoan_Admin(300, "home loan");
		
	}


}
