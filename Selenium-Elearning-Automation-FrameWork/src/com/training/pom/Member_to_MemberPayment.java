package com.training.pom;



import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class Member_to_MemberPayment {
	WebDriver driver;

	public Member_to_MemberPayment(WebDriver driver)

	{
			this.driver = driver; 

			PageFactory.initElements(driver, this);

	}
	
	@FindBy(xpath="//li[@id='menu2']/span[2]")
	WebElement AccountMenu;

	@FindBy(xpath="//*[contains(text(),'Home')]")
	WebElement menuHome;
	
	@FindBy(xpath="//*[contains(text(),'Scheduled payments')]")
	WebElement submenuSchPayments;
	
		
	@FindBy(xpath="//*[@class='subMenuText'][contains(text(),'Member Payment')]")
	WebElement memberPaymentMenu;
	
	@FindBy(id="memberUsername")
	WebElement login;
	
	@FindBy(id="memberName")
	WebElement memName;
	
	@FindBy(name="amount")
	WebElement amount;
	
	@FindBy(name="description")
	WebElement description;
	
	@FindBy(id="submitButton")
	WebElement submit_MemPay;
	
	@FindBy(xpath="//*[@class='tdHeaderTable']")
	WebElement trxConfirm_header;
	
	@FindBy(xpath="(//*[@class='headerField'])[2]")
	WebElement trxConfirm_memName;
	
	@FindBy(xpath="(//*[@class='headerField'])[3]")
	WebElement trxConfirm_memAmt;
			
	@FindBy(xpath="(//*[@class='headerField'])[4]")
	WebElement trxConfirm_desc;
		
	@FindBy(xpath="//*[@value='Submit']")
	WebElement trxConfirm_Submit;
	
	@FindBy(xpath = "//*[@class='defaultTable']/tbody/tr/td[1]/br[1]")
	WebElement successmsg;
	
	public void makeMemberPayment_Member(String name, String amount,String desc) throws InterruptedException
	{
		try {

		this.menuHome.click();
		this.AccountMenu.click();
		this.memberPaymentMenu.click();
		this.memName.clear();
		this.memName.sendKeys(name);
		Thread.sleep(3000);
//	  int payamount=Integer.valueOf(amount);
//		this.amount.sendKeys(payamount);
		
		this.amount.sendKeys(amount);

		this.description.sendKeys(desc);
		this.submit_MemPay.click();
		System.out.println("submit clicked");
		trxConfirm(name, amount, desc);
	
		//assertTrue(successmsg.getText().contains("The payment has been performed"));
//		String successmessage = this.successmsg.getText();
//		System.out.println(successmessage);
		
		}catch(Exception e)
		{
			System.out.println("in catch block");
			ExpectedCondition<Alert> alert = ExpectedConditions.alertIsPresent();
			String Errtext = driver.switchTo().alert().getText();
			System.out.println(Errtext);
			boolean checkamtErr = Errtext.contains("Amount is required");
			System.out.println(checkamtErr);
			driver.switchTo().alert().accept();
			this.amount.sendKeys("100");
			this.submit_MemPay.click();
			
			trxConfirm(name, amount, desc);
			//assertTrue(condition);
		}
	}
	
	public void trxConfirm(String name, String amount,String desc)
	{
		
		boolean checkheader = (trxConfirm_header.getText()).equalsIgnoreCase("Transaction confirmation");
		System.out.println(checkheader);
		boolean checkname = (trxConfirm_memName.getText()).contains(name);
		System.out.println(checkname);
		boolean checkamt = (trxConfirm_memAmt.getText()).contains(amount);
		System.out.println(checkamt);
		System.out.println(trxConfirm_memAmt.getText()+" "+amount);
		if (trxConfirm_desc.getText() == " ")
			{boolean checkdesc = (trxConfirm_desc.getText()).contains(desc);
		System.out.println(checkdesc);
			}
		this.trxConfirm_Submit.click();
	}
}
