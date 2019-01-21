package com.training.pom;

import java.util.List;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

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

	WebElement datepickerFrom;
	
	@FindBy(xpath="//*[@id='calendarTrigger1']")

	WebElement datepickerTo;

	@FindBy(xpath="//tr[2]/td[3]/div[1]")

	WebElement caldr_today;


	@FindBy(xpath="/html/body/div[3]/table/tbody/tr[3]/td[4]")

	WebElement date16Jan;

	@FindBy(xpath="/html/body/div[3]/table/tbody/tr[3]/td[1]")

	WebElement date13Jan;


	@FindBy(xpath="//tr[3]/td/table/tbody/tr[2]/td[n]")

	WebElement searchRes_row1;
	
	@FindBy(xpath="//*[contains(text(),'Home')]")
	WebElement menuHome;
	
	@FindBy(name="query(period).end")
	WebElement datepickTo;
	
	@FindBy(xpath="//*[@id='tdContents']/table[1]/tbody/tr[3]/td/")
	WebElement resultTable;
	
	@FindBy(xpath="//*[@title='Next page']")
	WebElement nextpage_searchResult;
	
	
	@FindBy(name="query(paymentFilter)")
	WebElement trxType;

	@FindBy(xpath="//*[@id=\"tdContents\"]/table[1]/tbody/tr[3]/td/table/tbody/tr[2]")
	WebElement tableExist;
	
	
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
	
	public void search_DateRange()
	{
		this.menuHome.click();
		this.AccountMenu.click();
		this.AccountInfoMenu.click();
		this.AdvancedBtn.click();
		this.memlogin.sendKeys("Soumita123");
		this.memname.sendKeys("SoumitaAAA");
		this.datepickerFrom.click();
		this.date13Jan.click();
//		this.datepickerTo.click();
//		this.date16Jan.click();
		this.datepickTo.sendKeys("16/01/2019");
		this.searchFilterBtn.click();
		
 
	
		try {
			
		
		do {
			
			List<WebElement> date = driver.findElements(By.xpath("//*[@id='tdContents']/table[1]/tbody/tr[3]/td/table/tbody/tr/td[1]"));
			int rows= date.size();
			System.out.println(rows);
		
		for(int i=2;i<=rows + 1;i++)
			
	{		
			//System.out.println("i=" +i);
		
			
			WebElement dateData = driver.findElement(By.xpath("//*[@id='tdContents']/table[1]/tbody/tr[3]/td/table/tbody/tr["+i+"]/td[1]"));
			boolean check = (dateData.getText().contentEquals("16/01/2019")) ||  (dateData.getText().contentEquals("15/01/2019")) ||  (dateData.getText().contentEquals("13/01/2019"));
			System.out.println(check);
		
			
			
	}
		System.out.println("next button" + nextpage_searchResult.isEnabled());
		if(this.nextpage_searchResult.isEnabled())
			{nextpage_searchResult.click();}
		else 
			{break;}
		
		}while(this.tableExist.isDisplayed());
		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	public void search_Loan()
	{
		this.menuHome.click();
		this.AccountMenu.click();
		this.AccountInfoMenu.click();
		this.AdvancedBtn.click();
		this.memlogin.sendKeys("Soumita123");
		this.memname.sendKeys("SoumitaAAA");
		Select select = new Select(this.trxType);
		select.selectByVisibleText("Loan payments");
		this.searchFilterBtn.click();
		
		
	}

}

