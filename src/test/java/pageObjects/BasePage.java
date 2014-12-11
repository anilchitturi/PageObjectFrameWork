package pageObjects;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import util.driver.type.DriverFactory;

import java.io.*;

/**
 * Author: anilc
 * Description:
 */
public class BasePage {

    public static ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();
    public WebDriver driver = webDriver.get();

    @BeforeClass(alwaysRun = true)
    @Parameters({"browser","environment"})
    public void startDriver(String browser, String environment) throws Exception{
        driver = DriverFactory.getWebDriverInstanceForBrowser( browser,environment );
        webDriver.set(driver);
        System.out.println(driver.getTitle());
        if (null == driver) {
            //To-do : Implement exception handler
        }
    }
    @AfterClass(alwaysRun = true)
    public void tearDown() throws Exception {
        if (driver != null)
        {
            driver.quit();
        }
    }

    /**
     * Method that runs after TestNG invokes any test.
     */
    @AfterMethod
    public void afterMethodInvocation(ITestResult testResult) {
        WebDriver driver = webDriver.get();
        if (!testResult.isSuccess()) {
            try {
               Long time = System.currentTimeMillis();
                File scrFile = ((TakesScreenshot )driver).getScreenshotAs( OutputType.FILE);
                FileUtils.copyFile( scrFile, new File( "./test-output/" + testResult.getName() + time + ".jpg" ) );
                Reporter.log( "<a href='" + testResult.getName() + time + ".jpg'> <img src='" + testResult.getName() + time + ".jpg' height='300' width='500'/> </a>" );
            } catch (IOException e) {

                System.out.println("Failed taking Screenshot.");
                e.printStackTrace();
            }
        }
    }


    public GmailPage gmailPage() throws Exception{
        return new GmailPage(driver);
    }

    public RegistartionPage registrationPage() throws Exception{
        return new RegistartionPage(driver);
    }

    public ForgotUserNamePage forgotUserNamePage() throws Exception{
        return new ForgotUserNamePage(driver);
    }
    public TroubleShootPage troubleSignInPage() throws Exception{
        return new TroubleShootPage(driver);
    }
   
}
