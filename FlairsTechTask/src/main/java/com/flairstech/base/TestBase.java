package com.flairstech.base;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.flairstech.framework.PropertiesLoader;
import com.flairstech.framework.ReportManager;

/**
 * 
 * @author SarahSaloum
 *
 */

public class TestBase {

    public static WebDriver driver;
    public static ReportManager reportManager;
    PropertiesLoader propsLoader;
    private final static String driversFilesPath = System.getProperty("user.dir") + "/resources/drivers/Mac/";

    public TestBase() {

	// Initialize report manager
	reportManager = new ReportManager();

	// load properties
	propsLoader = new PropertiesLoader();

    }

    public static void initializeDriver(String driverType) {

	switch (driverType.toLowerCase().trim()) {

	case "chrome":
	    ChromeOptions options = new ChromeOptions();
	    options.addArguments("start-maximized");
	    options.addArguments("--disable-notifications");
	    System.setProperty("webdriver.chrome.driver", driversFilesPath + "chromedriver");
	    driver = new ChromeDriver(options);
	    break;

	case "firefox":
	    System.setProperty("webdriver.gecko.driver", driversFilesPath + "geckodriver");
	    driver = new FirefoxDriver();
	    break;
	}

    }

    public static void openURL(String URL) {
	driver.get(URL);
	driver.manage().window().maximize();
    }

    public void openBrowser() {
	// Initialize driver
	initializeDriver(System.getProperty("browserType"));

	// open URL
	openURL(System.getProperty("URL"));
    }

    public void closeBrowser() {
	driver.quit();
    }

    public void refresh() {
	driver.navigate().refresh();
    }

    public void scrollToEnd() {
	JavascriptExecutor js = (JavascriptExecutor) driver;
	js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }
}
