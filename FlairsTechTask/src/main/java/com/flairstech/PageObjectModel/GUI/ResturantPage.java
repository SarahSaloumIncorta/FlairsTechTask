package com.flairstech.PageObjectModel.GUI;

import org.openqa.selenium.By;

import com.flairstech.base.PageBase;
import com.flairstech.framework.Assertions;
import com.flairstech.framework.ElementActions;

/**
 * 
 * @author SarahSaloum
 *
 */
public class ResturantPage extends PageBase {

    // Define all elements
    By list_location = By.xpath("//input[@placeholder= 'Search for area, street name, landmark...']");
    By submitButton = By.id("getAddress");

    public void Assert_selectedResturantPageOpenedSuccessfully(String resturantName) {
	By resturantNameLocator = By.xpath("(//b[text()= '" + resturantName + "'])[1]");
	Assertions.assert_elementExists(driver, resturantNameLocator, resturantName);
    }

    public void selectLocation(String city) {
	ElementActions.selectValueFromList_writeTextFirst(driver, list_location, "Location", city);
    }

    public void Assert_resturantNotDeliverInThisLocation() {
	Assertions.assert_elementDisabled(driver, submitButton, "submit button");
	Assertions.assert_stringsEqual(ElementActions.getElementText(ElementActions.findElement(driver, submitButton)),
		"Sorry, this brand doesn’t deliver here.", "Error message ");
    }

}
