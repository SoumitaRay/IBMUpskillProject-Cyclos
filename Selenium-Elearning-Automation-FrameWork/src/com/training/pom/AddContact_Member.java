package com.training.pom;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.logging.Logger;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddContact_Member {

	WebDriver driver;
	
	public AddContact_Member(WebDriver driver)
	{
		// TODO Auto-generated constructor stub
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath ="//*[contains(text(),'Personal')]")
	WebElement menu_Personal;
	
	@FindBy(xpath ="//*[@class='subMenuText' and contains(text(),'Contacts')]")
	WebElement submenu_Contacts;
	
	@FindBy(id="memberUsername")
	WebElement login;
	
	@FindBy(id="memberName")
	WebElement memName;
	
	@FindBy(xpath="//*[@value='Submit']")
	WebElement submit_btn;
	
	@FindBy(xpath = "//*[@id=\"menu0\"]/span[2]")
	WebElement menu_Home;
	
	@FindBy(name ="amount")
	WebElement makepayAmount ;
	
	@FindBy(name ="type")
	WebElement trxType ;
	
	
	@FindBy(name ="description")
	WebElement paymentdesc ;
	
	@FindBy(id ="submitButton")
	WebElement submitpayment ;
	
//	@FindBy(xpath ="(//*[@class='profileLink' and contains(text(),"+username+"')])[1]")
//	WebElement payee_name;
	
	@FindBy(xpath ="(//*[@class='profileLink'])[1]")
	WebElement payee;
	
	@FindBy(xpath ="//*[contains(text(),'Member Payment')]")
	WebElement submenu_MemPayment;
	
	@FindBy(xpath ="(//*[@class='tdHeaderTable'])[1]")
	WebElement page_header;
	
	@FindBy(xpath ="//*[@linkurl='payment?to=34']")
	WebElement makePayment_SubmitBtn;
	
	
	
	public void addContact(String username)
	{
		this.menu_Personal.click();
		this.submenu_Contacts.click();
		
		try {
			
		
		this.login.sendKeys(username);
		this.memName.clear();
		this.memName.sendKeys("arti testnew");
		WebDriverWait wait = new WebDriverWait(driver,10);
		//wait.until(ExpectedConditions.attributeToBe(memName, username, "arti testnew"));
	//	wait.until(ExpectedConditions.textToBePresentInElementValue(memName.getText(), "arti testnew"));
		this.submit_btn.click();
		Alert alert = driver.switchTo().alert();
		assertTrue(alert.getText().equalsIgnoreCase("The contact was inserted"));
		alert.accept();
		}catch(Exception e)
		{
			e.printStackTrace();
		}

	}
	
	public void makepayment_member(String username,int amount,String desc)
	{
		WebElement payee_name = driver.findElement(By.xpath("(//*[@class='profileLink' and contains(text(),'"+username+"')])[1]"));
		//this.payee_name.click();
		
		assertEquals(page_header.getText(), "Contact list");
		
		this.payee.click();
		this.makePayment_SubmitBtn.click();
		String amt = Integer.toString(amount);
		this.makepayAmount.sendKeys(amt);
		this.paymentdesc.sendKeys(desc);
		this.submitpayment.click();
		this.submit_btn.click();
		assertTrue(page_header.getText().equalsIgnoreCase("Successful payment"));
				
		System.out.println("execution completed");
	}
	

}
