package com.projectge.elements;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NavigationBar {

	@FindBy(xpath = "//*[@id=\"js-header-nav\"]/ul/li[1]/a/span")
	private WebElement mainBuyingSearch;
	
	@FindBy(xpath = "//*[@id=\"top-nav__buying\"]/li[1]/a")
	private WebElement subBuyingSearch;
	
	public void openSearch() {
		mainBuyingSearch.click();
		subBuyingSearch.click();
	}
	
}
