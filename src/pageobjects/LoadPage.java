package pageobjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import locators.LoadPageLocators;

public class LoadPage extends BasePage{

		
	
	public LoadPage(WebDriver driver) {
		super(driver);
	}

	public void reset() {
		waitClick(LoadPageLocators.DOWN_ARROW);
		waitClick(LoadPageLocators.UP_ARROW);
	}
	
	
	public List<String> getUrlList() {
		reset();
		
		
		List<String> links = new ArrayList<String>();
		
		while(isDownEnabled()) {
			links.add(getURL().substring(30));
			waitClick(LoadPageLocators.DOWN_ARROW);
		}
		links.add(getURL().substring(30));
		
		while(isUpEnabled())
			waitClick(LoadPageLocators.UP_ARROW);
		
		return links;
		
	}
	
	
	public void download(int posision) {
		click(elementByPositionAtList(LoadPageLocators.DOWNLOAD_TEXT, posision));
	}
	
	public void clickDownArrow() {
		waitClick(LoadPageLocators.DOWN_ARROW);
	}
	
	
	public boolean isDownEnabled() {
		if(LoadPageLocators.DOWN_ARROW.equals(By.cssSelector(".down")))
			return true;
		else return false;
	}
	
	public boolean isUpEnabled() {
		if(LoadPageLocators.UP_ARROW.equals(By.cssSelector(".up")))
			return true;
		else return false;
	}

}
 