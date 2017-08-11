package com.projectge.elements;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AdvancedSearchForm {

	@FindBy(xpath = "//*[@id=\"js-known-search-advanced\"]/div[1]/div[1]/input")
	private WebElement inputPostcode; 
	
}
