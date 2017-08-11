package com.projectge.elements;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ResultsNav {

	@FindBy(xpath = "/html/body/main/section[1]/div[1]/form/div/div/h1")
	private WebElement resultCount;
	
	public WebElement getResultsElement() {
		return resultCount;
	}
}
