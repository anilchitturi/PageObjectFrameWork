package util.driver;

/**
 * Author: anilc
 * Description: Util methods for driver
 */

import com.google.common.base.Function;
import com.thoughtworks.selenium.SeleniumException;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.app.Properties;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class Util extends Properties {
   private static WebDriver driver;

   //use this method when we need to wait until certain item present, it will wait till the object present, ideally infinite loop if that object is not exist
   public static void waitForE(WebDriver driver, int msec, WebElement id) throws InterruptedException {
    WebDriverWait wait = new WebDriverWait(driver, msec);
       while(!id.isDisplayed())
       {
           wait( msec );
       }
   }
   //use this method when we need to wait until certain item present for a certain amount of time and exist with error if that item not found
   public static void waitFor(WebDriver driver, long msec, WebElement id) throws InterruptedException {
      WebElement element = (new WebDriverWait(driver, msec)).until(ExpectedConditions.visibilityOf(id));
   }

   //Explicit wait function
   public static void wait(int MilliSec) throws InterruptedException {
      Thread.sleep(MilliSec);
   }

   /**use to select a dynamic value from a dropdown,
   *parentElement - the parent select which will expand the list of options
   *targetedTag - the unique selector from the child , like ul or option etc..,*/
   public static void selectOptionFromDropdown(WebElement parentElement, String expectedValue, String targetedTag) {
     // Then instantiate the Select class with that WebElement
     //  Select select = new Select(listxpath);
     // Get a list of the options
     // List<WebElement> options = select.getOptions();
     List<WebElement> dropdownOptions = parentElement.findElements( By.tagName( targetedTag ));
     //System.out.println("**********found options"+dropdownOptions);
     for (WebElement option : dropdownOptions) {
        if(option.getText().equals(expectedValue)) {
           option.click();
           break;
        }
     }
   }



    /**
     *Get the date which we need to pass for flight dates DD/MM/YYYY
     *
     */
    public static void getDates(int day) {
        Date m = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(m);
        cal.add(Calendar.DAY_OF_MONTH, day);// 10 is the days you want to add or subtract
        m = cal.getTime();
        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy");
        String date = DATE_FORMAT.format(m);
        System.out.println("Selected date is : " + date);
    }


}

