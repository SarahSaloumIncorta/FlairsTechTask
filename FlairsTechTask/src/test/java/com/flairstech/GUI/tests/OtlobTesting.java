package com.flairstech.GUI.tests;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.flairstech.PageObjectModel.GUI.*;
import com.flairstech.PageObjectModel.GUI.CustomerFeedbackPage.customerSatisfactionLevel;
import com.flairstech.PageObjectModel.GUI.HomePage.cuisine;
import com.flairstech.PageObjectModel.GUI.HomePage.customerActions;
import com.flairstech.PageObjectModel.GUI.HomePage.myAccountMenuActions;
import com.flairstech.PageObjectModel.GUI.SearchResultsPage.sortingTypes;
import com.flairstech.framework.TestDataActions;
import com.flairstech.framework.ReportManager;

/**
 * 
 * @author SarahSaloum
 *
 */

public class OtlobTesting {

    WebDriver driver;
    private LoginPage login;
    private HomePage home;
    private FindResturantPage findResturant;
    private SearchResultsPage searchResults;
    private CustomerFeedbackPage feedback;
    private MyAccountPage myAccount;
    private OffersPage offers;
    private ResturantPage resturant;

    private TestDataActions testDataActions;

    @Test
    public void userPlaceOrderOfferFromResutrantNotInHisArea() {
	ReportManager.create_test("userPlaceOrderOfferFromResutrantNotInHisArea", "Positive test");
	home.clickLogin();
	login.Login(testDataActions.ReadCellData("UserName"), testDataActions.ReadCellData("Password"));
	home.AssertUserLoggedInSuccessfully(testDataActions.ReadCellData("FirstName"));
	home.selectOffers();
	offers.selectOffer(testDataActions.ReadCellData("TC1_ResturantName"));
	offers.selectResturantOffer(1);
	resturant.Assert_selectedResturantPageOpenedSuccessfully(testDataActions.ReadCellData("TC1_ResturantName"));
	resturant.selectLocation(testDataActions.ReadCellData("TC1_City"));
	resturant.Assert_resturantNotDeliverInThisLocation();
    }

    @Test
    public void searchRestaurant_by_cusinieTypeAndLocation() {
	ReportManager.create_test("searchRestaurant_by_cusinieTypeAndLocation", "Positive test");
	home.selectCuisine(cuisine.Pizza);
	findResturant.findResturants(testDataActions.ReadCellData("City"), testDataActions.ReadCellData("Area"));
	searchResults.sortResults(sortingTypes.AtoZ);
	searchResults.Assert_SelectedAreaNameAppearsInPageHeader(testDataActions.ReadCellData("Area"));
	searchResults.Assert_resultRecordResturantName(1, testDataActions.ReadCellData("ResturantName"));
    }

    @Test
    public void submitSuccessfulFeedbackWithoutLogin() {
	ReportManager.create_test("submitSuccessfulFeedbackWithoutLogin", "Positive test");
	home.selectCustomerAction(customerActions.Feedback);
	feedback.sumbitFeedBack(customerSatisfactionLevel.notSatisfiedAtAll, 1, 1,
		testDataActions.ReadCellData("FeebackMessage"));
	feedback.Assert_feedbackSubmittedSuccessfully();
    }

    @Test
    public void userChangeAccountDetails() {
	ReportManager.create_test("userChangeAccountDetails", "Positive test");
	home.clickLogin();
	login.Login(testDataActions.ReadCellData("UserName"), testDataActions.ReadCellData("Password"));
	home.AssertUserLoggedInSuccessfully(testDataActions.ReadCellData("FirstName"));
	home.selectActionFromMyAccountMenu(myAccountMenuActions.AccountInfo);
	myAccount.Assert_pageOpenedSuccessfully();
	myAccount.updateMyAccountInfo_lastName(testDataActions.ReadCellData("LastNameToBeUpdated"));
	myAccount.Assert_accountInfoUpdatedSuccessfully();
	myAccount.refresh();
	myAccount.Assert_lastNameValue(testDataActions.ReadCellData("LastNameToBeUpdated"));
    }

    @BeforeClass
    public void beforeClass() {
	login = new LoginPage();
	home = new HomePage();
	findResturant = new FindResturantPage();
	searchResults = new SearchResultsPage();
	feedback = new CustomerFeedbackPage();
	myAccount = new MyAccountPage();
	offers = new OffersPage();
	resturant = new ResturantPage();
	testDataActions = new TestDataActions("OtlobTest.xlsx");
    }

    @BeforeMethod
    public void beforeMethod() {
	home.openBrowser();
    }

    @AfterMethod
    public void afterMethod(ITestResult result) {
	ReportManager.get_result(result);
	home.closeBrowser();
    }

    @AfterTest
    public void afterTest() {
	ReportManager.flush_report();
    }
}
