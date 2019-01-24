package com.training.pom;



import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;




public class GrantLoan_Admin
	{
	
	WebDriver driver;
	
	public GrantLoan_Admin(WebDriver driver)
	{
			this.driver = driver; 
			PageFactory.initElements(driver, this);
			
	}
	
	@FindBy(xpath="//*[@linkurl='grantLoan?memberId=35']")
	// //*[contains(text(),'Grant loan')]//following-sibling::[@value='Submit']
	WebElement GrantLoan_SubmitBtn;
	
	@FindBy(xpath="//*[@class='tdHeaderTable']")
	WebElement grantLoan_header;
	
	@FindBy(xpath="//*[@class='large InputBoxDisabled']")
	WebElement memName_grantLoan;
	
	@FindBy(xpath ="//*[@id='transferType']")
	WebElement loanType;
	
	@FindBy(id="amount")
	WebElement loanAmt;
	
	@FindBy(id="description")
	WebElement loanDescription;
	
	@FindBy(xpath="//*[@value='Submit']")
	WebElement loanSubmitBtn;
	
	@FindBy(xpath="(//*[@class='label'])[1]")
	WebElement loanConfirmation_header;
	
	@FindBy(xpath = "(//*[@class='headerField'])[1]")
	WebElement loanConfirmation_loantype;
	
	
	@FindBy(xpath = "(//*[@class='headerField'])[3]")
	WebElement loanConfirmation_loanamt;
	

	@FindBy(xpath = "(//*[@class='headerField'])[4]")
	WebElement loanConfirmation_loandesc;
	
	@FindBy(xpath = "//*[@value='Submit']")
	WebElement loanConfirmation_Submit;
	
	
	
	
	
	public void click_GrantLoanToMember()
	{
		GrantLoan_SubmitBtn.click();
		System.out.println("grant loan btn clicked");
			
		
	}
	
	public String assertMembername()
	{
		String Memname= this.memName_grantLoan.getAttribute("value");
		return(Memname);
	}
	
	
	/**
	 * 
	 */
	public void  grantMemberLoan_Admin(String amt, String desc)
	{		
		Select select = new Select(loanType);
		select.selectByVisibleText("Loan");
		WebElement option = select.getFirstSelectedOption();
		assertEquals(option.getText(), "Loan");
		
		System.out.println("loan selected");
			//	String amount = Integer.toString(amt);
		this.loanAmt.sendKeys(amt);
		this.loanDescription.sendKeys(desc);
		this.loanSubmitBtn.click();
	//	String text = this.loanConfirmation_header.getText();
	//	String Exptext = "You are about to grant the following loan:";
	//	Assert.verify(text.equalsIgnoreCase(Exptext));
		
//		Assert.verify((this.loanConfirmation_loantype.getText().equalsIgnoreCase("Loan")));
//		Assert.verify((this.loanConfirmation_loanamt.getText().replaceAll(",", "").contains(amount)));
//		Assert.verify((this.loanConfirmation_loandesc.getText().equalsIgnoreCase(desc)));
	// Assert.assertEquals(this.loanConfirmation_loandesc.getText(), desc);
		
		verify_LoanDetails(amt,desc);		
		
		this.loanConfirmation_Submit.click();
		driver.switchTo().alert().accept();
			
		
	}
	
	public void verify_LoanDetails(String amount, String desc)
	{
		String ActHeader=  this.grantLoan_header.getText();
		String ExpHeader = "Grant loan to SoumitaAAA";
		String amt = String.valueOf(amount);
		//Assert.verify(ActHeader.equalsIgnoreCase(ExpHeader));
		
		try {
			boolean headercheck=ActHeader.equalsIgnoreCase(ExpHeader);
		
			boolean loantypecheck = this.loanConfirmation_loantype.getText().equalsIgnoreCase("Loan");	
			
			boolean loanamtcheck = this.loanConfirmation_loanamt.getText().replaceAll(",", "").contains(amt);
			
			boolean loandesccheck= this.loanConfirmation_loandesc.getText().equalsIgnoreCase(desc);		
			
			
		}catch (Exception e)
		{
			System.out.println(e);
		}
	
	}
	
	

}
