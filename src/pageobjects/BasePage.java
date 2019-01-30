package pageobjects;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {
	WebDriver driver;
	WebDriverWait wait;
	JavascriptExecutor js;

	public BasePage(WebDriver driver) {
		this.driver = driver;
		js = (JavascriptExecutor) driver;
		wait = new WebDriverWait(driver, 10,500);
		PageFactory.initElements(driver, this);
	}

	public void waitClick(By by){
		// using wait to reco if element is clickablefor 
		
		for(int i=0;i<=5;i++) {
			try {
				WebElement element =  wait.until(ExpectedConditions.elementToBeClickable(by));
				element.click();
				break;
			}
			catch(StaleElementReferenceException  e){
				e.printStackTrace();
				continue;
			}
			
		}
			
	}
	
	
	
	public String getURL() {
		return driver.getCurrentUrl();
	}
	
	public void alertOK() {
		driver.switchTo().alert().accept();
	}
	
	public void click(WebElement el) {
		//highlightElement(el);
		el.click();
	}

	public void fillText(WebElement el, String text) {
		highlightElement(el);
		wait(100);
		clearText(el);
		el.sendKeys(text);
	}

	public String getText(WebElement el) {
		highlightElement(el);
		return el.getText();
	}

	public void select(WebElement el,String value) {
		Select dropdown = new Select(el);
		dropdown.selectByValue(value);

	}

	public void wait(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}


	public void clearText(WebElement el) {
		el.clear();
	}
	
	public void pressEnter(WebElement el) {
		el.sendKeys(Keys.RETURN);
	}
	
	public void hoverMouse(WebElement el) {
		Actions action = new Actions(driver);
		action.moveToElement(el).build().perform();
	}
	
	public void refreshPage() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.navigate().refresh();
	}

	public WebElement elementByPositionAtList(By by,int posision) {
		
		List<WebElement> list = driver.findElements(by);
		return list.get(posision);
	}
		
	protected void highlightElement(WebElement el) {
		hoverMouse(el);
		String originalStyle = el.getAttribute("style");
		
		int r = 255;
		int g = 255;
		int b = 255;
		for (int i=255;i>=150;i-=4) {
			
			String color = String.format("#%02x%02x%02x", r, g, b);
			String newStyle = "background:" + color + ";" + originalStyle;
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("var tmpArguments = arguments;setTimeout(function () {tmpArguments[0].setAttribute('style', '" + newStyle + "');},0);",el);
			r=i;
			b=i;
		}

	}
	
}






















