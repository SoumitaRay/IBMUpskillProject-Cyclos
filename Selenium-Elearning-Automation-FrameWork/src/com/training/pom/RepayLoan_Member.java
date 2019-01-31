package com.training.pom;


import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RepayLoan_Member {

	WebDriver driver;

	public RepayLoan_Member(WebDriver driver)

	{
			this.driver = driver; 
			PageFactory.initElements(driver, this);

	}
	
	
	@FindBy(xpath="//*[@class='menuText' and contains(text(),'Account')]")
	WebElement Account_Menu;
	
	@FindBy(xpath="//*[@class='subMenuText' and contains(text(),'Loans')]")
	WebElement Loans_submenu;
	
	@FindBy(xpath="//*[@class='defaultTable']/tbody/tr[4]/td[4]")
	WebElement loan_remain;
			
	@FindBy(id="amountText")
	WebElement repayamount_text;
	
	@FindBy(xpath="//*[@value='Repay']")
	WebElement repay_btn;
	
	
	
	public void assertRepayamount(String memlogin,String pwd,String loan_amt,String loan_desc,String repay_amt)
	{
		this.Account_Menu.click();
		this.Loans_submenu.click();
		
		int datarow= (driver.findElements(By.xpath("(//*[@class='defaultTable'])[2]/tbody/tr")).size()) - 1;
		
		List<WebElement> result = driver.findElements(By.xpath("(//*[@class='defaultTable'])[2]/tbody/tr["+datarow+"]/td"));
		
		
		int col = result.size();
		System.out.println(datarow + " " +col);
		 for(int i = 1 ; i <=col ;i++)
		 {
			 String data = driver.findElement(By.xpath("(//*[@class='defaultTable'])[2]/tbody/tr["+datarow+"]/td["+i+"]")).getText();
			 WebElement column = driver.findElement(By.xpath("(//*[@class='defaultTable'])[2]/tbody/tr["+datarow+"]/td["+i+"]"));
			if (i==1)
			{	System.out.println(data+" "+loan_desc);
				assertTrue(data.equalsIgnoreCase(loan_desc));
				
			}
			if(i==2)
			{	System.out.println(data+" "+loan_amt);
				assertTrue(data.equalsIgnoreCase(loan_amt)); 
			}
			if(i==3) continue;
			if(i==4)
				{ column.click(); 
				System.out.println("view clicked");
				}
		}		 
	 
		assertTrue((stringToInt(loan_amt)) >= (stringToInt(repay_amt)));
		 this.repayamount_text.sendKeys(repay_amt);
		 repay_btn.click();
		 Alert alert = driver.switchTo().alert();
		assertTrue((alert.getText()).contains(repay_amt));
		alert.accept();
		assertTrue((driver.switchTo().alert().getText()).contains("The repayment was succesfully processed"));
		alert.accept();
		System.out.println("repayment done");
		 
	}
	
	 public int stringToInt(String amount)
	 {
		 String result = amount.replaceFirst("[a-zA-Z,\\s]", "");
		 int result_int = Integer.parseInt(result);
		 return(result_int);
	 }
	
	
}
