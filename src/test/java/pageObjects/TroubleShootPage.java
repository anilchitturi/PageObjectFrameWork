package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TroubleShootPage {
	 private WebDriver driver;

	  
	    @FindBy(id="2")
	    private WebElement secondoption;
	   
	    @FindBy(xpath="//*[@value='Continue']")
	    private WebElement continue_Btn;
	    
	    public TroubleShootPage(WebDriver driver) throws InterruptedException{
	    	this.driver=driver;
	    	PageFactory.initElements(driver, this);	    	
	    }
	    
	   
	    public void secondOption() throws InterruptedException{
	    	secondoption.click();
	    	continue_Btn.click();
	    }
	    
	   
}
