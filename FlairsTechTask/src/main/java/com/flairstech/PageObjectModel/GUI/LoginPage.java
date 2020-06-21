package com.flairstech.PageObjectModel.GUI;

import org.openqa.selenium.By;

import com.flairstech.base.PageBase;
import com.flairstech.framework.ElementActions;

/**
 * 
 * @author SarahSaloum
 *
 */
public class LoginPage extends PageBase {

    // Define all elements
    By text_email = By.xpath("//input[ @placeholder='Email']");
    By text_password = By.xpath("//input[ @placeholder='Password']");
    By button_login = By.xpath("//button[ @type='submit']");

    public void Login(String email, String password) {
	ElementActions.setElementText(driver, text_email, "Email", email);
	ElementActions.setElementText(driver, text_password, "Password", password);
	ElementActions.clickElement(driver, button_login, "Login Button");
    }

}
