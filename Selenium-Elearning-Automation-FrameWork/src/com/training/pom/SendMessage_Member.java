package com.training.pom;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SendMessage_Member {
	
	
	WebDriver driver;
	
	public SendMessage_Member(WebDriver driver)
	{
		// TODO Auto-generated constructor stub
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath ="//*[contains(text(),'Personal')]")
	WebElement menu_Personal;
	
	@FindBy(xpath ="//*[contains(text(),'Messages')]")
	WebElement submenu_Messages;

	@FindBy(xpath="//*[@value='Submit']")
	WebElement submit_btn;

	@FindBy(id="memberUsername")
	WebElement login;
	
	@FindBy(id="memberName")
	WebElement memName;
	
	@FindBy(name="message(subject)")
	WebElement subject;
	
	@FindBy(xpath ="//*[@class='cke_icon']")
	WebElement boldtext;

	@FindBy(xpath ="//*[@class='CSS1Compat']")  
	WebElement msgbody;
	
	@FindBy(xpath="//*[@class='menuText' and contains(text(),'Logout')]")
	WebElement logout;
	
	@FindBy(xpath ="(//*[@class='defaultTable'])[2]/tbody/tr[2]/td[3]")
	WebElement result_table_subject;
	
	@FindBy(xpath="(//*[@class='defaultTable'])[2]/tbody/tr[2]/td[3]//*[@class='linkList messageDetails']")
	WebElement subject_link;
	
	@FindBy(xpath ="(//*[@class='defaultTable'])[2]/tbody/tr[2]/td[4]")
	WebElement result_table_from;
	
	@FindBy(xpath="//*[@class='defaultTable']/tbody/tr[5]/td[2]")
	WebElement recipient_msgbody;
	
	@FindBy(xpath="//*[@value='Advanced']")
	WebElement advanced_Btn;
	
	@FindBy(id="messageBoxSelect")
	WebElement msgbox_type;
	
	@FindBy(name="query(rootType)")
	WebElement msg_type;
	
	@FindBy(id="replyButton")
	WebElement reply_btn;
	
	@FindBy(xpath="//*[@class='cke_editor']")
	WebElement mail_editor;
	
	public void type_msg_Member(String login,String name, String subject,String msg)
	{
		
		this.menu_Personal.click();
		this.submenu_Messages.click();
		this.submit_btn.click();
		this.login.sendKeys(login);
		this.memName.sendKeys(name);
		WebDriverWait wait = new WebDriverWait(driver, 20);
		//wait.until(ExpectedCondition<Boolean>()
			
		this.subject.sendKeys(subject);
		this.boldtext.click();
		
		driver.switchTo().frame(0);
		this.msgbody.clear();
		this.msgbody.sendKeys(msg);
		driver.switchTo().defaultContent();
		this.submit_btn.click();
		Alert alert = driver.switchTo().alert();
		alert.accept();
				
	}
	
	public void logout()
	{
		this.logout.click();
		Alert alert = driver.switchTo().alert();
		alert.accept();
	}
	
	public void checkmsg(String name, String subject,String msg)
	{
		this.menu_Personal.click();
		this.submenu_Messages.click();
		this.advanced_Btn.click();
		Select select_type = new Select(msgbox_type);
		select_type.selectByVisibleText("Inbox");
		Select select_owner_type= new Select(msg_type);
		select_owner_type.selectByVisibleText("Member");
		this.submit_btn.click();
		
		assertTrue(this.result_table_subject.getText().equalsIgnoreCase(subject));
		assertTrue(this.result_table_from.getText().equalsIgnoreCase(name));
		this.subject_link.click();
		assertTrue(this.recipient_msgbody.getText().equalsIgnoreCase(msg));
		
		replymail_chkSentItems(subject,name);
	}
	
	public void replymail_chkSentItems(String subject,String name)
	{
		this.reply_btn.click();
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(mail_editor));
		driver.switchTo().frame(0);
		this.msgbody.sendKeys(Keys.CONTROL.HOME);
		this.msgbody.sendKeys("Thank you");
		driver.switchTo().defaultContent();
		this.submit_btn.click();
		Alert alert = driver.switchTo().alert();
		alert.accept();
		Select select_type = new Select(msgbox_type);
		select_type.selectByVisibleText("Sent items");
		Select select_owner_type= new Select(msg_type);
		select_owner_type.selectByVisibleText("Member");
		assertTrue(this.result_table_from.getText().contains(name));
		assertTrue(this.result_table_subject.getText().contains(subject));
		this.subject_link.click();
		assertTrue(this.recipient_msgbody.getText().contains("Thank you"));
	}
	
}
