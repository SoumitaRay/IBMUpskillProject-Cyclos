package com.training.sanity.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.LoginPOM;
import com.training.pom.MakeMemberPayment_Admin;
import com.training.pom.MemberProfile;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class MemberPayment_Admin {
	
	private WebDriver driver;
	private static String baseUrl;
	private LoginPOM loginPOM;
	private MemberProfile memberprofile;
	private MakeMemberPayment_Admin makemempayment_admin;

	
	
	
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

	public void MemberPayment_Admin() throws InterruptedException
	{
	System.out.println("------Testcase : CYTC_017 :Make payment as Admin to member -------");
	
	makemempayment_admin.gotoHome();
	
	memberprofile.sendMemberUserName("SoumitaAAA");
	makemempayment_admin.click_PaymentToMember();
	String ExpMemname = "SoumitaAAA";
	String ActMemname = makemempayment_admin.assertMembername();
	Assert.assertEquals(ActMemname, ExpMemname);
	makemempayment_admin.makememberpayment_Admin(50, "Debit to member", "bonus");
	
	}

}
