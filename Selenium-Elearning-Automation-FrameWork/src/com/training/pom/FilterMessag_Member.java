package com.training.pom;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class FilterMessag_Member {
	
	WebDriver driver;
	
	public FilterMessag_Member() {
		// TODO Auto-generated constructor stub
		this.driver = driver; 

		PageFactory.initElements(driver, this);
	}

	
	@FindBy(xpath ="//*[contains(text(),'Personal')]")
	WebElement menu_Personal;
	
	@FindBy(xpath ="//*[contains(text(),'Messages')]")
	WebElement submenu_Messages;
	
	@FindBy(xpath="//*[@value='Advanced']")
	WebElement advanced_Btn;
	
	@FindBy(id="messageBoxSelect")
	WebElement msgbox_type;
	
	@FindBy(name="query(rootType)")
	WebElement msg_type;
	
	@FindBy(xpath="//*[@value='Submit']")
	WebElement submit_query;
	
	@FindBy(xpath="(//table[@class='defaultTable'])[2]/tbody/tr[2]")
	WebElement datatable;
	
	
	@FindBy(xpath="(//table[@class='defaultTable'])[2]/tbody/tr[2]/td[3]")
	WebElement subject;
	
	public void search_SentItems_Admin()
	{
		this.menu_Personal.click();
		this.submenu_Messages.click();
		this.advanced_Btn.click();
		Select select_type = new Select(msgbox_type);
		select_type.selectByVisibleText("Inbox");
		Select select_owner_type= new Select(msg_type);
		select_owner_type.selectByVisibleText("Administration");
		this.submit_query.click();
		
		validate_dataset(); 
		
	}
	
	public void validate_dataset()
	{
		
		List<WebElement> dataset = driver.findElements(By.xpath("(//table[@class='defaultTable'])[2]/tbody/tr[2]"));
		int dataset_cols = dataset.size();
		
		for (int i=1;i<=dataset_cols;i++)
		{
			List<WebElement> dataset_col_data = driver.findElements(By.xpath("(//table[@class='defaultTable'])[2]/tbody/tr[2]"));
		}
		
		
	}
}
