package com.flairstech.PageObjectModel.GUI;

import org.openqa.selenium.By;

import com.flairstech.base.PageBase;
import com.flairstech.framework.ElementActions;

/**
 * 
 * @author SarahSaloum
 *
 */
public class OffersPage extends PageBase {

    // Define all elements

    public void selectOffer(String resturantName) {
	By resturantLink = By.xpath("(//b[text()= '" + resturantName + "'])[1]");
	ElementActions.clickElement(driver, resturantLink, resturantName);
    }

    public void selectResturantOffer(int offerNo) {
	By offerLink = By.xpath("(//tr)[" + offerNo + "]");
	ElementActions.clickElement(driver, offerLink, "Offer No " + String.valueOf(offerNo));
    }

}
