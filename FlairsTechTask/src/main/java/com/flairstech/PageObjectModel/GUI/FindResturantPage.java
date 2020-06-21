package com.flairstech.PageObjectModel.GUI;

import org.openqa.selenium.By;

import com.flairstech.base.PageBase;
import com.flairstech.framework.ElementActions;

/**
 * 
 * @author SarahSaloum
 *
 */
public class FindResturantPage extends PageBase {

    // Define all elements
    By list_cityMenu = By.xpath("//input[@placeholder = 'Select Your City']");
    By list_areaMenu = By.xpath("//input[@placeholder = 'Select Your Area']");
    By button_findResturant = By.xpath("//button[text()='Find Restaurants']");

    public void selectCity(String city) {
	ElementActions.selectValueFromList_writeTextFirst(driver, list_cityMenu, "City", city);
    }

    public void selectArea(String area) {
	ElementActions.selectValueFromList_writeTextFirst(driver, list_areaMenu, "Area", area);
    }

    public void findResturants(String city, String area) {
	selectCity(city);
	selectArea(area);
	ElementActions.clickElement(driver, button_findResturant, "Find resuturant button");

    }

}
