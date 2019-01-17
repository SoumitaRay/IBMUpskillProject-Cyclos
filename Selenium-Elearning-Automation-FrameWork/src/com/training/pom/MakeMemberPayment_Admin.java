package com.training.pom;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class MakeMemberPayment_Admin {

	WebDriver driver;
	
	public MakeMemberPayment_Admin(WebDriver driver) 
	{
			this.driver = driver; 
			PageFactory.initElements(driver, this);
			
	}
	
	@FindBy(xpath = "//*[@id=\"menu0\"]/span[2]")
	WebElement menu_Home;
	
	@FindBy(xpath="//*[@linkurl='payment?to=35']")
	WebElement Payment_member_Submit_Btn;
	
	@FindBy(xpath="//*[@class=\"InputBoxDisabled large\"]")
	WebElement Payment_system_To;
	
	@FindBy(name ="amount")
	WebElement makepayAmount ;
	
	@FindBy(name ="type")
	WebElement trxType ;
	
	
	@FindBy(name ="description")
	WebElement paymentdesc ;
	
	@FindBy(id ="submitButton")
	WebElement submitpayment ;
	
	@FindBy(xpath = "//*[@id=\"tdContents\"]/form/table[1]/tbody/tr[2]/td/table/tbody/tr[1]/td")
	WebElement trxConfirmationText;
	
	@FindBy(xpath="//*[@value='Submit']")
	WebElement makePayment_SubmitBtn;
	
	
	
	
	@FindBy(xpath="//*[contains(text(),'Payment system to member')]")
	WebElement PaymentSystem_header;
	
	public void click_PaymentToMember()
	{
		this.Payment_member_Submit_Btn.click();
		System.out.println("Payment btn clicked");
		//  Assert.IsTrue(verifyTextPresent("Selenium Wiki"));
		String ActHeader=  this.PaymentSystem_header.getText();
		String ExpHeader = "Payment system to member";
		//Assert.verify(ActHeader.equalsIgnoreCase(ExpHeader));
		boolean headercheck = ActHeader.equalsIgnoreCase(ExpHeader);
	}
	
	public String assertMembername()
	{
		String Memname= this.Payment_system_To.getAttribute("value");
		return(Memname);
	}
	
	public void makememberpayment_Admin(int amount, String transactiontype , String desc)
	{
	String amt =Integer.toString(amount);
	this.makepayAmount.sendKeys(amt);	
	Select select = new Select(trxType);  
	select.selectByVisibleText(transactiontype);
	this.paymentdesc.sendKeys(desc);
	this.submitpayment.click();
	String text = this.trxConfirmationText.getText();
	String Exptext = "You are about to perform the following payment:";
	//Assert.verify(text.equalsIgnoreCase(Exptext));
	boolean headercheck = text.equalsIgnoreCase(Exptext);
	this.makePayment_SubmitBtn.click();

	
	}
	
	public void gotoHome()
	{
	this.menu_Home.click();
	
	}
	
}
