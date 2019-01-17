package com.training.pom;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;



public class ViewLoan_Admin {

	WebDriver driver;
	
	public ViewLoan_Admin(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
				
	}
	
	@FindBy(xpath="//*[@linkurl='searchLoans?memberId=35']")
	WebElement viewLoan_SubmitBtn;
	
	@FindBy(xpath = "(//*[@class='tdHeaderTable'])[1]")
	WebElement viewLoan_header;
	
	@FindBy(xpath ="//*[@type ='radio' and @value = 'CLOSED']")
	WebElement closedRadio;
	
	@FindBy(xpath ="//*[@type ='radio' and @value = 'OPEN']")
	WebElement openRadio;
	
	@FindBy(xpath ="//*[@id=\"tdContents\"]/table[1]/tbody/tr[2]/td/table/tbody/tr[2]/td[1]")
	WebElement openLoan_Rec1_desc;
	
	@FindBy(xpath ="//*[@id=\"tdContents\"]/table[1]/tbody/tr[2]/td/table/tbody/tr[2]/td[2]")
	WebElement openLoan_Rec1_amt;
	
	
	public void click_ViewLoanforMember()
	{
		this.viewLoan_SubmitBtn.click();
		String expHeader = "Loans of SoumitaAAA" ;
		String actHeader = viewLoan_header.getText();
		boolean headercheck=actHeader.equalsIgnoreCase(expHeader);
	}
	
	
	public void viewMemberLoans_Admin(String desc, String amount)
	{
		//click on Closed Radio button
		this.closedRadio.click();
		
		//click on Open Radio button and verify the record
		
		this.openRadio.click();
		String loandesc = this.openLoan_Rec1_desc.getText();
		String amt = this.openLoan_Rec1_amt.getText();
		
		try
		{
		boolean loandesccheck= loandesc.equalsIgnoreCase(desc);
		boolean loanamtcheck = amt.equalsIgnoreCase(amount);
		}catch (Exception e)
		{
			System.out.println(e);
		}
		
	
		
	}
}
