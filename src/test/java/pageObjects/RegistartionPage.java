package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegistartionPage {
	 private WebDriver driver;

	   
	    @FindBy(id="FirstName")
	    private WebElement FirstName;
	    @FindBy(id = "LastName")
	    private WebElement LastName;
	    @FindBy(id = "GmailAddress")
	    private WebElement GmailAddress;
	    @FindBy(id="Passwd")
	    private WebElement Passwd;
	    @FindBy(id="PasswdAgain")
	    private WebElement PasswdAgain;
	    @FindBy(id="errormsg_0_Passwd")
	    private WebElement errormsg_0_Passwd;
	    
	    public RegistartionPage(WebDriver driver) throws InterruptedException{
	    	this.driver=driver;
	    	PageFactory.initElements(driver, this);	    	
	    }
	    
	   
	    public WebElement geterrormsg_0_Passwd() throws InterruptedException{
	    	return errormsg_0_Passwd;
	    }
	    
	    public void fillRegistartionData(String fname,String lname,String email,String pwd)
	    		throws InterruptedException{
	    	FirstName.clear();
	    	FirstName.sendKeys(fname);
	    	LastName.sendKeys(lname);
	    	GmailAddress.sendKeys(email);
	    	Passwd.sendKeys(pwd);
	    	PasswdAgain.sendKeys(pwd);
	    }
	    
	   
	    
}
