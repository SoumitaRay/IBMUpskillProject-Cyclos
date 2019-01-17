package com.training.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FilterTrxDetails_Member {
	
WebDriver driver;
	
	public FilterTrxDetails_Member(WebDriver driver)
	{
			this.driver = driver; 
			PageFactory.initElements(driver, this);
			
	}

	@FindBy(xpath="(//*[@class='tdHeaderTable'])[1]")
	WebElement memberAccheader;

	@FindBy(xpath="//li[@id='menu2']/span[2]")
	WebElement AccountMenu;
	
	@FindBy(xpath="//span[contains(text(),'Account Information')]")
	WebElement AccountInfoMenu;
	
	@FindBy(xpath="//input[@id='modeButton' and @value='Advanced']")
	WebElement AdvancedBtn;
	
	@FindBy(xpath="//*[@id='memberUsername']")
	WebElement memlogin;
	
	@FindBy(xpath="//*[@id='memberName']")
	WebElement memname;
	
	
	@FindBy(xpath="//input[@value='Search']")
	WebElement searchFilterBtn;
	
	@FindBy(xpath="//*[@id='calendarTrigger0']")
	WebElement datepicker;
	
	
	@FindBy(xpath="//tr[2]/td[3]/div[1]")
	WebElement caldr_today;
	
	
	@FindBy(xpath="/html/body/div[3]/table/tbody/tr[3]/td[4]")
	WebElement date16Jan;
	

	@FindBy(xpath="/html/body/div[3]/table/tbody/tr[3]/td[1]")
	WebElement date13Jan;
	
	@FindBy(xpath="//tr[3]/td/table/tbody/tr[2]/td[n]")
	WebElement searchRes_row1;
	
	
	public void searchAll()
	{
		String expheader = "Search transactions on Member account";
		boolean headercheck = memberAccheader.getText().equalsIgnoreCase(expheader);
		this.AccountMenu.click();
		this.AccountInfoMenu.click();
		this.AdvancedBtn.click();
		this.memname.sendKeys("SoumitaAAA");
		this.memlogin.sendKeys("Soumita123");
		this.searchFilterBtn.click();
		
	}	
	
	
	public void validateResultAll(String date, String name, String description, String amount) {
		// TODO Auto-generated method stub
		System.out.println("validate row1 data");
		String []row1data = new String[4];
		
		
		for(int i = 1;i<5;i++)
		{
			WebElement rowdata = driver.findElement(By.xpath("//tr[3]/td/table/tbody/tr[2]/td["+i+"]"));
			row1data[i-1]=rowdata.getText();
			System.out.println(row1data[i-1]);
			try {
							
			switch(i-1)
			{
			case 0:
				boolean check_td1 = row1data[i-1].contentEquals(date);
			case 1:
				boolean check_td2 = row1data[i-1].contentEquals(name);
			case 2:
				boolean check_td3 = row1data[i-1].contentEquals(description);
			case 3:
				boolean check_td4 = row1data[i-1].contentEquals(amount);
			
			}
			
		}catch (Exception e)
			
		{System.out.println(e);}
		
	
		
	}
	}
}