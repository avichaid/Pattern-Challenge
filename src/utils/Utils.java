package utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class Utils {

	public static String readProp(String key){
		Properties prop = new Properties();
		String value="";
		try {

			InputStream input = new FileInputStream(System.getProperty("user.dir") + "\\src\\data\\config.properties");
			// load a properties file
			prop.load(input);

			// get the property value 
			value= prop.getProperty(key);
		}
		catch(Exception err)
		{}
		System.out.println("value =" + value);
		return value;
	}

	/**
	 * It is not recommended to use sleep but explicit wait, 
	 * but sometimes we want to save programming time so we will use it and then we will improve it later on.
	 * @param time
	 */
	public static void sleep(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * Read the driver type from data\config.propoerties 
	 */
	public static WebDriver getDriver(){
		WebDriver driver = null;
		//read the driver according to your definition in Const.browser
		String driverType = readProp("browser");
		switch (driverType){
		case "FireFox": 
			System.setProperty("webdriver.gecko.driver", readProp("driverPath") + "geckodriver.exe");
			driver =  new FirefoxDriver();
			break;
		case "Chrome":
			System.setProperty("webdriver.chrome.driver", readProp("driverPath") + "chromedriver.exe");
			driver = new ChromeDriver();
			break;
		case "IE":
			System.setProperty("webdriver.ie.driver", readProp("driverPath") + "IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			break;
		case "Edge":
			System.setProperty("webdriver.edge.driver", readProp("driverPath") + "MicrosoftWebDriver.exe");
			driver = new EdgeDriver();
		default:
			//here you should define the driver for default browser
			break;
		}
		return driver;
	}

}
