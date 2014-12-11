package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ForgotUserNamePage {
	 private WebDriver driver;

	 
	    @FindBy(id="fu_first")
	    private WebElement fu_first;
	    @FindBy(id="fu_last")
	    private WebElement fu_last; 
	   
	    
	    public ForgotUserNamePage(WebDriver driver) throws InterruptedException{
	    	this.driver=driver;
	    	PageFactory.initElements(driver, this);	    	
	    }
	    
	  
	    
	    public void fillRecoveryData(String fname,String lname) throws InterruptedException{
	    	fu_first.clear();
	    	fu_first.sendKeys(fname);
	    	fu_last.sendKeys(lname);
	    	
	    }
	    
}
