package util.driver.type;

import com.thoughtworks.selenium.SeleniumException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import util.app.Properties;
import util.testData.TestData;


/**
 * Author: anilc
 * Description: Factory class which will call driver constructors
 */

public class DriverFactory {
    private static  String OS = System.getProperty("os.name").toLowerCase();
    private static ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();
    static WebDriver driver = webDriver.get();

    //To get the driver instance by browser,environment passed from testng.xml
    public static WebDriver getWebDriverInstanceForBrowser(String browser, String environment) throws MalformedURLException {
        DriverFactory driverFactory=new DriverFactory();
        String appURL=driverFactory.getParameterFromTestNgXML("consoleUrl",environment);
        if (browser.toLowerCase().contains("firefox"))
            return firefox(appURL);
        else if (browser.toLowerCase().contains("chrome"))
            return chrome(appURL);
        else if (browser.toLowerCase().contains("ie"))
            return ie(appURL);
        else
            return null;
    }
    //To get the driver instance by Ibrowser interface
    public static WebDriver getWebDriverForBrowser(Class<? extends IBrowser> browser) throws MalformedURLException{
        String appURL = TestData.URL;
        if (browser.isAssignableFrom(Firefox.class))
            return firefox( appURL );
        else if (browser.isAssignableFrom(Chrome.class))
            return chrome(appURL);
        else if (browser.isAssignableFrom(InternetExplorer.class))
            return ie(appURL);
        else
            return null;
    }

    private static WebDriver firefox(String appURL) {
        try {
            //we can set the profile as
            /* FirefoxProfile profile = new FirefoxProfile();
            profile.setPreference("browser.safebrowsing.malware.enabled", false);*/
           // DesiredCapabilities capability = DesiredCapabilities.firefox();
           // driver = new RemoteWebDriver(capability);
            driver = new org.openqa.selenium.firefox.FirefoxDriver();
            System.out.println("Driver***"+driver);
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Properties.ImplicitWait, TimeUnit.SECONDS );
            driver.get( appURL );
        } catch (SeleniumException ex) {
            ex.printStackTrace();
        }
        return driver;
    }

    private static WebDriver chrome(String appURL) {
        System.out.println( "Running tests on" +OS );
        if(OS.contains( "win" )){
            //download chrome driver from http://chromedriver.storage.googleapis.com/index.html
            System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        } else if ( (OS.contains( "nix" )) || (OS.contains( "nux" )) ||(OS.contains( "aix" ))) {
            System.setProperty("webdriver.chrome.driver", "src/main/resources/linux_chromedriver");
        }  else if ( (OS.contains( "mac" ))) {
            System.setProperty("webdriver.chrome.driver", "src/main/resources/mac_chromedriver");
        }
        try {
            System.out.println( "Starting chrome browser" );
            //We can set capabilities for chrome driver as
            //ChromeOptions options = new ChromeOptions();
            //options.addArguments("window-size=800,600");
            //DesiredCapabilities capabilities = DesiredCapabilities.chrome();
            //capabilities.setCapability(ChromeOptions.CAPABILITY, options);
            //driver = new ChromeDriver(capabilities);
            driver = new ChromeDriver();
            System.out.println( "got the capabilities" );
            driver.manage().window().maximize();
            System.out.println( "url to open" +appURL );
            driver.get(appURL);
            System.out.println( "launch the url" );
        } catch (SeleniumException ex) {
            ex.printStackTrace();
        }
        return driver;
    }

    private static WebDriver ie(String appURL) {
        try {
            //download ie driver from http://docs.seleniumhq.org/download/
            System.setProperty("webdriver.ie.driver","src/main/resources/IEDriverServer.exe");
            driver = new InternetExplorerDriver();
            driver.get(appURL);
        } catch (SeleniumException ex) {
            ex.printStackTrace();
        }
        return driver;
    }

    //we can read the properties from testng.xml using ItestContext as well
     @DataProvider(name="Provider")
     public String provider(ITestContext context)
     {
      Map<String, String> testParams = context.getCurrentXmlTest().getParameters();

      return testParams.get("environment");
      }

    public String getParameterFromTestNgXML(String key,String environment) throws NullPointerException{
        String environmentSelectedForTest =environment; //System.getProperty("environment")!=null ? System.getProperty("environment") : getParameterFromTestNgXML( "environment" );
        System.out.println("Environment selected for test execution:" + environmentSelectedForTest);
        String configFilePath = String.format("config/%s/Conf", environment.toLowerCase());
        System.out.println("file path:" + configFilePath);
        String propertyValueFromConf = "";
        try {
            java.util.Properties properties = new java.util.Properties();
            System.out.println("entered file stream:" + configFilePath);
            //FileInputStream propertyIn = new FileInputStream(DriverFactory.class.getClassLoader().getResource(configFilePath).getFile());
            FileInputStream propertyIn = new FileInputStream(Thread.currentThread().getContextClassLoader().getResource(configFilePath).getFile());
            System.out.println("propertyIn:" + propertyIn);
            properties.load(propertyIn);
            propertyValueFromConf = properties.getProperty(key);
            System.out.println("entered file stream:" + propertyValueFromConf);
        } catch (IOException e) {
            DriverFactory.logger("Unable to get property value");
            e.printStackTrace();
        }
        return propertyValueFromConf;
    }

      public static void logger(String message) {
        Reporter.log( message );
      }
}
