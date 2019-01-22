package com.training.pom;


import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;



public class FilterMessag_Member {
	
	WebDriver driver;
	
	public FilterMessag_Member(WebDriver driver)
	{
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
	
	@FindBy(xpath = "(//*[@class='moveToTrash'])[2]")
	WebElement delete_secondMsg;
	
	
	public void search_Inbox_All()
	{
		this.menu_Personal.click();
		this.submenu_Messages.click();
		this.advanced_Btn.click();
		Select select_type = new Select(msgbox_type);
		select_type.selectByVisibleText("Inbox");
		Select select_owner_type= new Select(msg_type);
		select_owner_type.selectByVisibleText("All");
		this.submit_query.click();
		
		validate_dataset(); 
		delete_message();
		
		
	}
	
	public void validate_dataset()
	{
		System.out.println("in validate method");
		List<WebElement> dataset = driver.findElements(By.xpath("(//table[@class='defaultTable'])[2]/tbody/tr[2]/td"));
		int dataset_cols = dataset.size();
		System.out.println(dataset_cols);
		String []recordset = new String[dataset_cols]; 
		
		for (int i=2;i<=dataset_cols;i++)
		{
			WebElement dataset_col_data = driver.findElement(By.xpath("(//table[@class='defaultTable'])[2]/tbody/tr[2]/td["+i+"]"));
			System.out.println(i+" " + dataset_col_data);
			recordset[i-2] = dataset_col_data.getText();
						
			switch(i)
			{
			case 1:
				if(dataset_col_data.isSelected())
				{continue;}
				else
				{
					dataset_col_data.click();
				}
				break;	
				
			//assertFalse((WebElement) dataset_col_data).isSelected());
			
			case 2:				
				
				boolean check_col2 = (dataset_col_data.getText().equalsIgnoreCase("20/01/2019"));
				System.out.println(check_col2);
				break;
										
			case 3:
			
				boolean check_col3 = (dataset_col_data.getText().equalsIgnoreCase("Transaction feedback"));
				System.out.println(check_col3);
				break;
			
			case 4:
			//	assertEquals(dataset_col_data.getText(), "System");
				
				boolean check_col4 = dataset_col_data.getText().equalsIgnoreCase("System");
				System.out.println(check_col4);
				break;
				
			default:
				break;
			}	
				
		}
		
	}
	
	public void delete_message()
	{
		//delete a message from Inbox and check in trash if row exist
		this.delete_secondMsg.click();
		Alert alert = driver.switchTo().alert();
		alert.getText();  
		assertTrue(alert.getText().contains("The messages were moved to trash"));
		alert.accept();
		Select select_type = new Select(msgbox_type);
		select_type.selectByVisibleText("Trash");
		assertTrue(datatable != null); 
		
	}
}
