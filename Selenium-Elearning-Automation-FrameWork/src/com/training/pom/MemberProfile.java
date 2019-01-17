
package com.training.pom;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import javafx.scene.control.Tab;

public class MemberProfile {

private WebDriver driver; 
WebDriverWait wait ;
	
	 public MemberProfile(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver,10);
	}
	
//	@FindBy(id="memberUsername")
//	private WebElement memberUsername; 
	 
	 
	 @FindBy(id="memberName")
	 private WebElement memberName; 
	 
	 
	
	@FindBy(name ="member(user).username")
	private WebElement mName;
	
	
	@FindBy(xpath="//*[@linkurl='accountOverview?memberId=35']")
	private WebElement AccSubmitBtn;
	
	
	@FindBy(xpath="//*[@value='Search']")
	private WebElement searchBtn;
	
	@FindBy(xpath = "//*[@name =\"query(paymentFilter)\"]")
	WebElement PaymentType;
	
	@FindBy(xpath = "(//input[@value=\"Search\"])")
	WebElement SearchPaymentType;
	
	
	
	public void sendMemberUserName(String name) throws InterruptedException {
		this.memberName.sendKeys(Keys.F5);
		this.memberName.clear();
	    this.memberName.sendKeys(name);
		Thread.sleep(5000);
	//	this.memberName.sendKeys(Keys.ENTER);
		Thread.sleep(2000);
//	System.out.println("member clicked");
//		Actions moveto = new Actions(driver);
//	
//		moveto.moveToElement(memberUsername).build().perform();
//		Thread.sleep(5000);
//		this.memberUsername.sendKeys(Keys.ENTER);
		
//		moveto.click(this.memberUsername).sendKeys(memberUsername).keyUp(Keys.ENTER);
		//WebElement selectmember =memberUsername;
	}
	
	
	public void click_AccountInfo() throws InterruptedException {
		//wait.until(ExpectedConditions.visibilityOfElementLocated(AccSubmitBtn);
		
		this.AccSubmitBtn.click();
		Thread.sleep(2000);
	}
	
	public boolean assertPaymentTypes(String type1, String type2,String type3)
	{
		Select select = new Select(PaymentType);  
		//String[] paytype = {"All","Commission Payments","Loan Payments"};
		List<WebElement> paytypeDD = select.getOptions();
		
//		for(WebElement PaymentType: paytypeDD )
//		{
//			boolean match = false;
//			for(int i=0; i<paytype.length; i++)
//			{
//			if(PaymentType.getText().equals(paytype[i]))
//				match = true; 
//		}
		boolean match = false;
		int counter = 0;
		for(WebElement PaymentType: paytypeDD )
			{
				
				
				if(PaymentType.getText().equals(type1)) 
				{
					counter ++; continue;
				}
				if(PaymentType.getText().equals(type2)) 
				{
					counter ++; continue;
				}
				if(PaymentType.getText().equals(type3)) 
				{
					counter ++; continue;
				}
	
		
			}
		if (counter == 3)
			{match = true;
		System.out.println(match);
		}
		
		return(match);

	}
	
	public void selectCommPaymentType(String CommPayment)
	{
		Select select = new Select(PaymentType);  
	//	select.selectByValue("4");
		select.selectByVisibleText(CommPayment);
		this.SearchPaymentType.click();
	}
	
	
		
	
}
