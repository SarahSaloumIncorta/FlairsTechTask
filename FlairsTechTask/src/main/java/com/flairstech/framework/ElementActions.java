package com.flairstech.framework;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.flairstech.base.PageBase;
import com.flairstech.framework.ReportManager;

/**
 * 
 * @author SarahSaloum
 *
 */
public class ElementActions {

    public static void setElementText(WebDriver driver, By locator, String elementName, String textToEnter) {
	ReportManager.test.info("In field " + elementName + " , Set text = " + textToEnter);
	PageBase.wait_until_element_clickable(30, locator);
	WebElement element = driver.findElement(locator);
	element.clear();
	element.sendKeys(textToEnter);
    }

    public static void clickElement(WebDriver driver, By locator, String elementName) {
	ReportManager.test.info("Click on " + elementName);
	PageBase.wait_until_element_clickable(30, locator);
	WebElement element = driver.findElement(locator);
	element.click();
    }

    public static void hoverElement(WebDriver driver, By locator, String elementName) {
	ReportManager.test.info("Hover on " + elementName);
	PageBase.wait_until_element_clickable(30, locator);
	WebElement element = driver.findElement(locator);
	Actions actions = new Actions(driver);
	actions.moveToElement(element).perform();
    }

    public static void selectValueFromList(WebDriver driver, By locator, String elementName, String selectedText) {
	ReportManager.test.info(" select " + elementName);
	PageBase.wait_until_element_clickable(30, locator);
	WebElement element = driver.findElement(locator);
	Select list = new Select(element);
	list.selectByValue(selectedText);
    }

    public static void selectValueFromList_writeTextFirst(WebDriver driver, By locator, String elementName,
	    String selectedText) {
	ReportManager.test.info(" select " + elementName);
	PageBase.wait_until_element_clickable(30, locator);
	WebElement element = driver.findElement(locator);
	setElementText(driver, locator, elementName, selectedText);
	element.sendKeys(Keys.ENTER);
    }

    public static String getElementText(WebElement element) {
	return element.getText();
    }

    public static WebElement findElement(WebDriver driver, By locator) {
	PageBase.wait_until_element_clickable(30, locator);
	return driver.findElement(locator);
    }

    public static List<WebElement> findElements(WebDriver driver, By locator) {
	PageBase.wait_until_element_clickable(30, locator);
	return driver.findElements(locator);
    }
}
