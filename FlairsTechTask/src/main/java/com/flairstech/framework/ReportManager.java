package com.flairstech.framework;

import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

/**
 * 
 * @author SarahSaloum
 *
 */
public class ReportManager {

    static ExtentHtmlReporter htmlReporter; // builds a new report using the html template
    static ExtentReports extent; // helps to generate the logs in test report.
    public static ExtentTest test;

    public ReportManager() {
	start_report();
    }

    public void start_report() {
	// initialize the HtmlReporter
	htmlReporter = new ExtentHtmlReporter(
		System.getProperty("user.dir") + "/test-output/ExtentReport/testReport.html");

	// initialize ExtentReports and attach the HtmlReporter
	extent = new ExtentReports();
	extent.attachReporter(htmlReporter);

	// To add system or environment info by using the setSystemInfo method.
	extent.setSystemInfo("OS", "Mac-64");
	extent.setSystemInfo("Browser", "Chrome");

	// configuration items to change the look and feel
	// add content, manage tests etc
	htmlReporter.config().setChartVisibilityOnOpen(true);
	htmlReporter.config().setDocumentTitle("Execution Extent Report");
	htmlReporter.config().setReportName("Execution Test Report");
	htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
	htmlReporter.config().setTheme(Theme.STANDARD);
    }

    /**
     * 
     * @param test_name Test case name
     * @param test_type postive - negative test
     */
    public static void create_test(String test_name, String test_type) {
	test = extent.createTest(test_name, test_type);
    }

    public static void get_result(ITestResult result) {
	if (result.getStatus() == ITestResult.FAILURE) {
	    test.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " FAILED ", ExtentColor.RED));
	    test.fail(result.getThrowable());
	} else if (result.getStatus() == ITestResult.SUCCESS) {
	    test.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " PASSED ", ExtentColor.GREEN));
	} else {
	    test.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " SKIPPED ", ExtentColor.ORANGE));
	    test.skip(result.getThrowable());
	}
    }

    public static void flush_report() {
	// to write or update test information to reporter
	extent.flush();
    }

}