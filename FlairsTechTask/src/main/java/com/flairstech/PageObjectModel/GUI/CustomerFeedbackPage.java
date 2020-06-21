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
public class CustomerFeedbackPage extends PageBase {

    // TODO :to add all other buttons
    By button_notAtAllSatisfied = By.xpath("//button[text()='Not at all Satisfied']");
    By text_feedbackMessage = By.id("feedback-text");
    By checkBox_imNotRobot = By.xpath("//div[@class='rc-anchor-center-item rc-anchor-checkbox-holder']");
    By button_submitFeedback = By.id("submit-feedback");
    By successFeedbackMessage = By.id("feedback-success");

    public enum customerSatisfactionLevel {
	notSatisfiedAtAll, slightlySatisfied, moderatelySatisfied, quiteSatisfied, exteremelySatisfied;
    }

    public void sumbitFeedBack(customerSatisfactionLevel level, int effortLevel, int recommendationLevel,
	    String otherFeedback) {

	By selectedEffortLevel = By
		.xpath("(//div[@class='btn-group btn-group-justified'])[1]//button[text()='" + effortLevel + "']");

	By selectedRecommendationLevel = By.xpath(
		"(//div[@class='btn-group btn-group-justified'])[2]//button[text()='" + recommendationLevel + "']");

	ElementActions.clickElement(driver, determineSatisfactionButton(level), "Satisfaction level");
	ElementActions.clickElement(driver, selectedEffortLevel, "Effort level");
	ElementActions.clickElement(driver, selectedRecommendationLevel, "Recommendation level");
	ElementActions.setElementText(driver, text_feedbackMessage, "Feedback message", otherFeedback);
	ElementActions.clickElement(driver, checkBox_imNotRobot, "Im not robot");
	ElementActions.clickElement(driver, button_submitFeedback, "Submit feedback");
    }

    // TODO :All cases should be handled in the future
    public By determineSatisfactionButton(customerSatisfactionLevel level) {
	By selectedlevel = null;
	switch (level) {
	case notSatisfiedAtAll:
	    selectedlevel = button_notAtAllSatisfied;
	default:
	    break;
	}
	return selectedlevel;
    }

    public void Assert_feedbackSubmittedSuccessfully() {
	String expectedMessage1 = "Thank you for your feedback!";
	String expectedMessage2 = "We value every feedback we receive";
	String actualMessageText = ElementActions
		.getElementText(ElementActions.findElement(driver, successFeedbackMessage));
	Assertions.assert_stringscontains(actualMessageText, expectedMessage1, "Message 1");
	Assertions.assert_stringscontains(actualMessageText, expectedMessage2, "Message 2");

    }
}
