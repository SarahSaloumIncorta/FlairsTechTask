package com.flairstech.PageObjectModel.GUI;

import org.openqa.selenium.By;

import com.flairstech.PageObjectModel.GUI.HomePage.myAccountMenuActions;
import com.flairstech.base.PageBase;
import com.flairstech.framework.Assertions;
import com.flairstech.framework.ElementActions;

/**
 * 
 * @author SarahSaloum
 *
 */
public class MyAccountPage extends PageBase {

    By label_pagetitle = By.xpath("//b[text()='My Account']");
    By text_lastName = By.name("lastName");
    By button_update = By.xpath("//button[text()='Update']");
    By popup_successMessage = By.xpath("//b[text()='Your information was updated successfully']");
    By popup_button_Ok = By.xpath("//span[text()='Ok']");

    // TODO: Handle all fields editing options
    public void updateMyAccountInfo_lastName(String lastName) {
	ElementActions.setElementText(driver, text_lastName, "Last Name", lastName);
	ElementActions.clickElement(driver, button_update, "Update");
    }

    public void navigateToAnotherTab(myAccountMenuActions action) {
	By tabName = By.xpath("// b[text()='" + action.getAction() + "']");
	ElementActions.clickElement(driver, tabName, action.getAction());
    }

    public void Assert_pageOpenedSuccessfully() {
	Assertions.assert_elementExists(driver, label_pagetitle, "My Account page header");
    }

    public void Assert_accountInfoUpdatedSuccessfully() {
	Assertions.assert_elementExists(driver, popup_successMessage, "Successful update confirmation Message");
	ElementActions.clickElement(driver, popup_button_Ok, "Ok button");
    }

    public void Assert_lastNameValue(String expectedLastName) {
	String ActualText = ElementActions.findElement(driver, text_lastName).getAttribute("value");
	Assertions.assert_stringsEqual(ActualText, expectedLastName, "Last Name");
    }
}
