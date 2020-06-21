package com.flairstech.PageObjectModel.GUI;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.flairstech.base.PageBase;
import com.flairstech.framework.Assertions;
import com.flairstech.framework.ElementActions;

/**
 * 
 * @author SarahSaloum
 *
 */
public class SearchResultsPage extends PageBase {

    // Define all elements
    By resultsList = By.xpath("//div[@class ='rest-cont ng-scope']");

    public enum sortingTypes {
	AtoZ("A to Z");

	// constructor
	private sortingTypes(final String type) {
	    this.Type = type;
	}

	private String Type;

	public String getSortType() {
	    return Type;
	}
    }

    public WebElement getSearchResultRecord(int recordNumber) {
	List<WebElement> listOfRows = ElementActions.findElements(driver, resultsList);
	return listOfRows.get(recordNumber);
    }

    public void sortResults(sortingTypes sortType) {
	By sortTypeLocator = By.xpath("//a[text()='" + sortingTypes.AtoZ.getSortType() + "']");
	ElementActions.clickElement(driver, sortTypeLocator, sortingTypes.AtoZ.getSortType());
    }

    public void Assert_SelectedAreaNameAppearsInPageHeader(String expectedAreaName) {
	PageBase.wait_until_element_clickable(30, By.xpath("//h1[text()= 'Restaurants in " + expectedAreaName + "']"));
	String actualAreaName = ElementActions
		.findElement(driver, By.xpath("//h1[text()= 'Restaurants in " + expectedAreaName + "']")).getText();
	Assertions.assert_stringscontains(actualAreaName, expectedAreaName, " Area label ");

    }

    public void Assert_resultRecordResturantName(int recordNumber, String expectedResturantName) {
	WebElement resultRecord = getSearchResultRecord(recordNumber).findElement(By.xpath("//h4"));
	String actualElementText = ElementActions.getElementText(resultRecord);
	Assertions.assert_stringsEqual(actualElementText, expectedResturantName, " check resturant name");
    }

}
