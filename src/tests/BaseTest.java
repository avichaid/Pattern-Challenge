package tests;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import utils.Utils;

public abstract class BaseTest {
	WebDriver driver;

	@BeforeClass
	public void setup() {
		driver = Utils.getDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(Utils.readProp("url"));
	}


	/*
	 * This method will run after wach test,
	 * it will take screen shot only for tests that failed
	 */
	@AfterMethod
	public void failedTest(ITestResult result) {
		//check if the test failed
		if (result.getStatus() == ITestResult.FAILURE ){
			TakesScreenshot ts = (TakesScreenshot)driver;
			File srcFile = ts.getScreenshotAs(OutputType.FILE);
			try {
				FileUtils.copyFile(srcFile, new File("./ScreenShots/"+result.getName()+".jpg"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//result.getname() method will give you current test case name. 
			//./ScreenShots/ tell you that, in your current directory, create folder ScreenShots. dot represents current directory
		}
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}
