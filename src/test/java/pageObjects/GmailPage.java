package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GmailPage {
	 private WebDriver driver;

	    @FindBy(linkText="Create an account")
	    private WebElement createAccountLink;
	    @FindBy(linkText="Need help?")
	    private WebElement needHelpLink;
	   
	    
	    public GmailPage(WebDriver driver) throws InterruptedException{
	    	this.driver=driver;
	    	PageFactory.initElements(driver, this);	    	
	    }
	    
	    public void navigateToRegistartion() throws InterruptedException{
	    	createAccountLink.click();
	    }
	    
	    public void naviagetToNeedHelp() throws InterruptedException{
	    	needHelpLink.click();
	    }
	    
	   
}
