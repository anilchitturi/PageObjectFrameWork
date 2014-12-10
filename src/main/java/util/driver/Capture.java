package tv.adap.util.driver;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.*;
import java.util.*;
import java.lang.*;
import java.io.IOException;

/**
 * Author: anilc
 * Description: screen capture methods can be used at different levels
 */

public class Capture {
    private WebDriver driver;

    public String captureScreen() {

        String path;
        try {
            WebDriver augmentedDriver = new Augmenter().augment(driver);
            File source = ((TakesScreenshot)augmentedDriver).getScreenshotAs(OutputType.FILE);
            path = "./target/screenshots/" + source.getName();
            FileUtils.copyFile(source, new File(path));
        }
        catch(IOException e) {
            path = "Failed to capture screenshot: " + e.getMessage();
        }
        return path;

    }
}
