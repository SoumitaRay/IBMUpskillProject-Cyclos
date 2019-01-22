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
import com.training.pom.FilterMessag_Member;
import com.training.pom.LoginPOM;
import com.training.pom.SendMessage_Member;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class SendMessageTest {
	
	private WebDriver driver;
	private static String baseUrl;
	private LoginPOM loginPOM;
	private SendMessage_Member sendmsg_member;
	
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
		   sendmsg_member = new SendMessage_Member(driver);
			
		   
		// open the browser 
		   driver.get(baseUrl);
	  }
	
	  @AfterClass
	  public void afterClass() {
	  }

	
	 
  @Test(dataProvider="Sendmessage" , priority = 1)
  public void sendmessage_Member(String name,String subject,String msg)
  {
	  System.out.println("------Testcase : CYTC_049 : Send Msg -------");
	 	loginPOM.sendUserName("Soumita123");
		loginPOM.sendPassword("test1234");
		loginPOM.clickLoginBtn();
		sendmsg_member.type_msg_Member(name, subject, msg);
		
  }
  
  @Test(dataProvider="Inbox" , priority = 2)
  public void recievemessage_Member(String name,String subject,String msg)
  {
	  System.out.println("------Testcase : CYTC_049 : Receive Msg -------");
	 	loginPOM.sendUserName("artish");
		loginPOM.sendPassword("12345");
		loginPOM.clickLoginBtn();
		sendmsg_member.type_msg_Member(name, subject, msg);
		
  }
  
  @DataProvider(name="Sendmessage")
  public Object[][] getDataFromDataprovider1(){
  return new Object[][] 
  	{
          {"arti testnew", "Hello","welcome to cyclos-testmail 1"},
        
      };
  
  }
  
  @DataProvider(name="Inbox")
  public Object[][] getDataFromDataprovider2(){
  return new Object[][] 
  	{
          {"Soumita123", "Hello","welcome to cyclos-testmail 1"},
        
      };
  
  }
  
}
