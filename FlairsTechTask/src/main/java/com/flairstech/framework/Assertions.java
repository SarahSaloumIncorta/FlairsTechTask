package com.flairstech.framework;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import com.flairstech.base.PageBase;

/**
 * 
 * @author SarahSaloum
 *
 */
public class Assertions {

    public static void assert_elementExists(WebDriver driver, By by, String elementName) {

	ReportManager.test.info("Assert that element " + elementName + " exists ");
	try {
	    PageBase.wait_until_element_clickable(30, by);
	    driver.findElement(by);
	    ReportManager.test.pass("Assertion passed , Element " + elementName + " exists ");
	} catch (NoSuchElementException e) {
	    ReportManager.test.fail("Assertion failed , Element " + elementName + " not exists , Exception Message : "
		    + e.getMessage());
	}

    }

    public static void assert_stringsEqual(String actualValue, String expectedValue, String description) {
	ReportManager.test.info("Assert that " + description + " value is " + expectedValue);

	if (actualValue.trim().equals(expectedValue.trim())) {
	    ReportManager.test.pass("Assertion passed , Actual value of  " + description + " is " + actualValue
		    + " match the expected value  " + expectedValue);
	} else {
	    ReportManager.test.fail("Assertion failed , Actual value of  " + description + " is " + actualValue
		    + " does not match the expected value  " + expectedValue);
	}

    }

    public static void assert_stringscontains(String actualValue, String expectedValue, String description) {
	ReportManager.test.info("Assert that " + description + " value is " + expectedValue);

	if (actualValue.trim().contains(expectedValue.trim())) {
	    ReportManager.test.pass("Assertion passed , Actual value of  " + description + " is " + actualValue
		    + " contains the expected value  " + expectedValue);
	} else {
	    ReportManager.test.fail("Assertion failed , Actual value of  " + description + " is " + actualValue
		    + " does not contain the expected value  " + expectedValue);
	}

    }

    public static void assert_elementDisabled(WebDriver driver, By by, String elementName) {

	ReportManager.test.info("Assert that element " + elementName + " Disabled ");
	PageBase.wait_until_element_clickable(30, by);

	if (!driver.findElement(by).isEnabled()) {
	    ReportManager.test.pass("Assertion passed , Element " + elementName + " Disabled ");
	} else {
	    ReportManager.test.fail("Assertion failed , Element " + elementName + " Enabled");
	}

    }

}
