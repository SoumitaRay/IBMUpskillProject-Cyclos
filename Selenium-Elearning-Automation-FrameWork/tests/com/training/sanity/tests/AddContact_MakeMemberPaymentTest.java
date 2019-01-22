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
import com.training.pom.AddContact_Member;
import com.training.pom.LoginPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class AddContact_MakeMemberPaymentTest {
	
	private WebDriver driver;
	private static String baseUrl;
	private LoginPOM loginPOM;
	private AddContact_Member addcontact_member;
	
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
		   addcontact_member = new AddContact_Member(driver);
			
		   
		// open the browser 
		   driver.get(baseUrl);
	  }
	
	  @AfterClass
	  public void afterClass() {
	  }

	

	
  @Test(dataProvider="makePayment")
  public void addPayee_Member(String username, int amount, String desc)
 {
	  System.out.println("------Testcase : CYTC_050 : Add contact_Member and Make payment");
	 	loginPOM.sendUserName("Soumita123");
		loginPOM.sendPassword("test1234");
		loginPOM.clickLoginBtn();
		addcontact_member.addContact(username);
		addcontact_member.makepayment_member(username, amount, desc);
		
  }
  
  @DataProvider(name="makePayment")
  public Object[][] getDataFromDataprovider1(){
  return new Object[][] 
  	{
          {"artish", 220, "joining bonus"},
        
      };
  
  }
  
}
