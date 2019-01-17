package com.training.sanity.tests;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import com.training.generics.ScreenShot;
import com.training.pom.FilterTrxDetails_Member;
import com.training.pom.LoginPOM;

import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;


public class FilterTrxDetails_MemberTest {
	
	private WebDriver driver;
	private static String baseUrl;
	private LoginPOM loginPOM;
	private FilterTrxDetails_Member filtertrx_member;
	
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
			filtertrx_member = new FilterTrxDetails_Member(driver);
			
		   
		// open the browser 
		   driver.get(baseUrl);
	  }
	
	  @AfterClass
	  public void afterClass() {
	  }
	  
  @Test(dataProvider = "SearchResult_All")
  public void filterMemberTransaction_All(String date, String name, String description, String amount) throws InterruptedException
  
  {
		  
	  System.out.println("------Testcase : CYTC_046 : All -------");
	 	loginPOM.sendUserName("Soumita123");
		loginPOM.sendPassword("test1234");
		loginPOM.clickLoginBtn();
		filtertrx_member.searchAll();
		filtertrx_member.validateResultAll(date,name,description,amount);
  }
 
  
  @Test
  public void filterMemberTransaction_Date() 
  
  {
		  
	  System.out.println("------Testcase : CYTC_046 : Date Range -------");
	 	
	  filtertrx_member.search_DateRange();
		//filtertrx_member.validateResultAll(date,name,description,amount);
	  
  }

  
  @Test(enabled = false,dataProvider="SearchResult_Loan")
  public void filterMemberTransaction_Loan(String date, String name, String description, String amount) 
  
  {
		  
	  System.out.println("------Testcase : CYTC_046 : Loan -------");
	 	
	  filtertrx_member.search_Loan();
	  filtertrx_member.validateResultAll(date,name,description,amount);
	  
  }

  

  @DataProvider(name="SearchResult_All")
  public Object[][] getDataFromDataprovider(){
  return new Object[][] 
  	{
          {"16/01/2019", "artish","Test3","+0,30"},
        
      };
  
  }
  
  @DataProvider(name="SearchResult_Loan")
  public Object[][] getDataFromDataprovider1(){
  return new Object[][] 
  	{
          {"15/01/2019", "Debit account","home loan","+3,00"},
        
      };
  
  }
  
  
}
