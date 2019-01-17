
package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class TransactionDetails {
	
	WebDriver driver;
	
	public TransactionDetails(WebDriver driver) 
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
				
	}

	@FindBy(xpath="//*[@id=\"tdContents\"]/table[1]/tbody/tr[3]/td/table/tbody/tr[2]/td[5]/img")
	WebElement viewIcon_Trx1;
	
	@FindBy(xpath="//td[contains(text(),'Search result')]")
	WebElement SearchResult_header;
	
	@FindBy(xpath="//*[@id=\"tdContents\"]/table[1]/tbody/tr[2]/td/table/tbody/tr[5]/td[2]")
	WebElement amountText;
	
	@FindBy(xpath="//*[@id=\"tdContents\"]/table[1]/tbody/tr[2]/td/table/tbody/tr[6]/td[2]")
	WebElement descText;
	
	@FindBy(xpath="//*[@class='tdHeaderTable']")
	WebElement transaction_header;
	
	
	public void clickView_trx1(String description , String amount)
	{
		
		try {
		
			boolean headercheck=SearchResult_header.getText().equalsIgnoreCase("Search results");
			this.viewIcon_Trx1.click();
			boolean trxheader = transaction_header.getText().equalsIgnoreCase("Transaction details");
			boolean desccheck = descText.getText().equalsIgnoreCase(description);
			boolean amtcheck = amountText.getText().equalsIgnoreCase(amount);
			
			}catch (Exception e)
		{System.out.println(e);
		
		}
	
	}
}