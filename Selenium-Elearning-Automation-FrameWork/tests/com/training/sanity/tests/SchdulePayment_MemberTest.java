package com.training.sanity.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.FilterTrxDetails_Member;
import com.training.pom.LoginPOM;
import com.training.pom.SchdulePayment_Member;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class SchdulePayment_MemberTest {
	
	private WebDriver driver;
	private static String baseUrl;
	private LoginPOM loginPOM;
	private SchdulePayment_Member schpay_member;
	
	private static Properties properties;
	private static ScreenShot screenShot;
	
	
	 @BeforeMethod
	  public void beforeMethod() {
	  }

	  @AfterMethod
	  public void afterMethod() {
	  }

	  @BeforeClass
	  public void beforeClass() throws IOException {
		  properties = new Properties();
			FileInputStream inStream = new FileInputStream("./resources/others.properties");
			properties.load(inStream);
			baseUrl = properties.getProperty("baseURL");
		   screenShot = new ScreenShot(driver); 
		   driver = DriverFactory.getDriver(DriverNames.CHROME);
		   loginPOM = new LoginPOM(driver); 
		   schpay_member = new SchdulePayment_Member(driver);
			
		   
		// open the browser 
		   driver.get(baseUrl);
	  }
	
	  @AfterClass
	  public void afterClass() {
	  }

	
  @Test(dataProvider="FuturePay")
  public void SchedulePayment_Member(String name,String amount ,String desc) throws InterruptedException
  {
	  System.out.println("------Testcase : CYTC_047 : Schedule Future payment-------");
	 	loginPOM.sendUserName("Soumita123");
		loginPOM.sendPassword("test1234");
		loginPOM.clickLoginBtn();
		schpay_member.makeMemberPayment_Member(name, amount, desc);
	//	schpay_member.validate_searchResult_SchPayment("19/02/2019","Selenium1","0,55","Processed");
  }
  
  @DataProvider(name="FuturePay")
  public Object[][] getDataFromDataprovider1(){
  return new Object[][] 
  	{
          {"Selenium1", "0,66","welcome-test"},
        
      };
  
  }
}
