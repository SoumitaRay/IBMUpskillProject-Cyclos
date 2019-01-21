package com.training.pom;

import static org.junit.Assert.assertTrue;
import static org.testng.Assert.assertEquals;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class SchdulePayment_Member {
	
	WebDriver driver;

	public SchdulePayment_Member(WebDriver driver)

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
	
	@FindBy(id="schedulingTypeSelect")
	WebElement schType;
	
	@FindBy(id="calendarTrigger0")
	WebElement datepicker;
	
	@FindBy(xpath= "(//div[contains(text(),'‹')])[2]")
	WebElement prevMonth;
	
	@FindBy(xpath="//div[4]/table/tbody/tr[5]/td[5]")
	WebElement jan31;
	
	@FindBy(id="scheduleForText")
	WebElement enterdate;
	
	
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
	WebElement trxConfirm_futuredate;
	
	
	@FindBy(xpath="(//*[@class='headerField'])[5]")
	WebElement trxConfirm_desc;
		
	@FindBy(xpath="//*[@value='Submit']")
	WebElement trxConfirm_Submit;
	
	@FindBy(id="payNowButton")
	WebElement trxConfirm_payNow;
	
	@FindBy(xpath="//*[@value='Submit']")
	WebElement memPay_Submit;

	@FindBy(xpath="//*[@id='tdContents']/table[1]/tbody/tr[2]/td/table/tbody/tr[7]/td[2]")
	WebElement trx_Status;
	
	@FindBy(id="statusSelect")
	WebElement schPay_statusDD;
	
				
			
	
	public void makeMemberPayment_Member(String name, String amount,String desc) throws InterruptedException
	{
		this.menuHome.click();
		this.AccountMenu.click();
		this.memberPaymentMenu.click();
		this.login.clear();
		this.login.sendKeys(name);
		Thread.sleep(3000);
		this.amount.sendKeys(amount);
		Select select = new Select(schType);
		select.selectByVisibleText("Scheduled for future date");
//		this.datepicker.click();
//		this.prevMonth.click();
//		this.jan31.click();
		this.enterdate.sendKeys("31/01/2019");
		this.description.sendKeys(desc);
		this.submit_MemPay.click();
		boolean checkheader = (trxConfirm_header.getText()).equalsIgnoreCase("Transaction confirmation");
		System.out.println(trxConfirm_header.getText());
		boolean checkname = (trxConfirm_memName.getText()).contains(name);
		System.out.println(trxConfirm_memName.getText());
		System.out.println(trxConfirm_memAmt.getText());
		String sch_date = trxConfirm_futuredate.getText();
		this.trxConfirm_Submit.click();
		this.trxConfirm_payNow.click();
		this.memPay_Submit.click();
		String status = trx_Status.getText();
		
		this.submenuSchPayments.click();
		Select mempaystatus = new Select(schPay_statusDD);
		mempaystatus.selectByVisibleText("Closed (entirely paid)");
		
		
			validate_searchResult_SchPayment(sch_date,name,amount,status);
	
	}

	
	public void validate_searchResult_SchPayment(String sch_date,String name,String amount,String status)
	{
		this.AccountMenu.click();
		this.submenuSchPayments.click();
		Select mempaystatus = new Select(schPay_statusDD);
		mempaystatus.selectByVisibleText("Closed (entirely paid)");
		List<WebElement> result_table = driver.findElements(By.xpath("(//*[@class='defaultTable'])[2]/tbody/tr[2]/td"));
		int columns = result_table.size();
		String[] get_result_table = new String[columns];
		
		try {
			
		
		for (int col = 1;col <columns; col++)
		{
			WebElement result_table_data=driver.findElement(By.xpath("(//*[@class='defaultTable'])[2]/tbody/tr[2]/td["+col+"]"));
			
			get_result_table[col-1]= result_table_data.getText();
			System.out.print(col+" ");
			System.out.println(result_table_data.getText());
					
				switch (col - 1)
				{
				case 0:
					System.out.println(result_table_data);
					assertEquals(get_result_table[col - 1], sch_date);
					break;
				case 1:
					System.out.println(result_table_data);
					assertEquals(get_result_table[col - 1], "Member account");
					break;
				case 2:
					System.out.println(result_table_data);
					assertEquals(get_result_table[col - 1], name);
					break;
				case 3:
					System.out.println(result_table_data);
					assertTrue(get_result_table[col - 1].contains(amount));
					break;
				case 5:
					System.out.print(col);
					assertEquals(get_result_table[col - 1], status);
					break;

				default: 
					break;
				}
			}
		
		}catch (Exception e)
		{ 
			e.printStackTrace();
		}
		

	}


	

}
