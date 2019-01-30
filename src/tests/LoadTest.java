package tests;

import java.util.List;

import org.testng.annotations.Test;

import pageobjects.LoadPage;

public class LoadTest extends BaseTest {
	
	@Test(priority=1, description="download")
	public void download() {
		LoadPage loadPage = new LoadPage(driver);
		//List<String> urlList = loadPage.getUrlList();
		loadPage.reset();
			
		int i=0;
		while(loadPage.isDownEnabled()) {
				loadPage.download(i);
				i++;
				loadPage.wait(1000);
				loadPage.clickDownArrow();
				
			}

		}


	}
	

