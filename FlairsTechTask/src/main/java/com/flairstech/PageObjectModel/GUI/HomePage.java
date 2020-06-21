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
public class HomePage extends PageBase {

    By link_Login = By.xpath("//li[@class= 'nav-list-li login-li']");
    By link_feedBack = By.xpath("(//a[text()='Feedback'])[1]");
    By list_myAccount = By.xpath("//a[@class='nav-link accountPopup']");
    By link_offers = By.xpath("(//a[contains(., 'Offers')])[1] /parent::li[@class='dd-li']");

    public enum cuisine {
	Pizza, Italian, Mexican, Sandwiches, Japanese;
    }

    public enum customerActions {
	Feedback, Careers, Terms, FAQ;
    }

    public enum myAccountMenuActions {
	AccountInfo("Account Info"), SavedAddresses("Saved Addresses");

	// constructor
	private myAccountMenuActions(final String accountOption) {
	    this.AccountOption = accountOption;
	}

	private String AccountOption;

	public String getAction() {
	    return AccountOption;
	}

    }

    public void selectCuisine(cuisine cuisine) {
	By locator = By.xpath("(//div[@class='home-footer']//a[text()='" + cuisine + "'])[1]");
	ElementActions.clickElement(driver, locator, cuisine.toString());
    }

    public void selectCustomerAction(customerActions action) {
	By locator = By.xpath("(//a[text()='" + action + "'])[1]");
	ElementActions.clickElement(driver, locator, "Customer Actions");
    }

    public void clickLogin() {
	ElementActions.clickElement(driver, link_Login, "Login");
    }

    public void selectActionFromMyAccountMenu(myAccountMenuActions action) {
	By menuItem = By.xpath("// b[text()='" + action.getAction() + "']");
	ElementActions.clickElement(driver, list_myAccount, "My Account");
	ElementActions.clickElement(driver, menuItem, action.getAction());

    }

    public void selectOffers() {
	ElementActions.clickElement(driver, By.xpath("//div[@class='nav-content clearfix']"), "Main header");
	//ElementActions.clickElement(driver, link_offers, "Offers Link");
    }

    public void AssertUserLoggedInSuccessfully(String expectedUserFirstName) {
	By welcomeMessage = By.xpath("//span[ text()='Welcome " + expectedUserFirstName + ",']");
	String ActualText = ElementActions.findElement(driver, welcomeMessage).getText();
	Assertions.assert_stringscontains(ActualText, expectedUserFirstName, "User first name");
    }
}
