package TestSuites;

import java.io.IOException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import Base.Base;
import Pages.FListing;
import Pages.RetrieveItems;
import utils.ExtentReportManager;
import Pages.CarWash;

public class TestCases extends Base {

	FListing fl = new FListing();
	RetrieveItems ri = new RetrieveItems();
	CarWash cw = new CarWash();
	public ExtentReports report = ExtentReportManager.getReportInstance();
	
	
	
	@BeforeTest
	public void invokeBrowser() throws InterruptedException {
		logger = report.createTest("Invoking Browser to launch the website");
		fl.invokeBrowser();
		reportPass("Browser is Invoked");
		System.out.println("Browser Launched Successfully.!");
	}

	@Test(priority = 2)
	public void Listing() throws IOException {
		logger = report.createTest("Trying to list in Free Listing");
		fl.register();
		reportPass("Free listing testcase passed/filed");
	}

	@Test(priority = 1)
	public void Retrieve() throws IOException {
		logger = report.createTest("Getting the submenu's");
		ri.items();
		reportPass("Sub Menus testcaee passed/failed");
	}

	@Test(priority = 3)
	public void CarDetails() throws IOException, InterruptedException {
		logger = report.createTest("Trying to list the Service centre details");
		cw.Wash();
		reportPass("Car Washing Centre testcase passed/failed");
	}

	@AfterTest
	public void closeBrowser() {
		fl.endReport();
		fl.closeBrowser();
	}

}
