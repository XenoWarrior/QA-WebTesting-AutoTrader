package com.projectge.elements;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import com.aventstack.extentreports.Status;
import com.projectge.main.AppTest;

public class AdvancedSearchForm {

	@FindBy(xpath = "//*[@id=\"js-known-search-advanced\"]/div[1]/div[10]")
	private WebElement divAvailableCars;
	
	@FindBy(xpath = "//*[@id=\"js-known-search-advanced\"]/div[1]/div[1]/input")
	private WebElement inputPostcode;

	@FindBy(xpath = "//*[@id=\"js-known-search-advanced\"]/div[1]/div[1]/div/select")
	private WebElement selectDistance;

	@FindBy(xpath = "//*[@id=\"js-known-search-advanced\"]/div[1]/div[5]/div[1]/label/span/input")
	private WebElement checkboxUsed;

	@FindBy(xpath = "//*[@id=\"js-known-search-advanced\"]/div[1]/div[5]/div[2]/label/span/input")
	private WebElement checkboxNewlyNew;

	@FindBy(xpath = "//*[@id=\"js-known-search-advanced\"]/div[1]/div[5]/div[3]/label/span/input")
	private WebElement checkboxNew;

	@FindBy(xpath = "//*[@id=\"js-known-search-advanced\"]/div[1]/div[6]/div/select")
	private WebElement selectMake;

	@FindBy(xpath = "//*[@id=\"js-known-search-advanced\"]/div[1]/div[7]/div/select")
	private WebElement selectModel;

	@FindBy(xpath = "//*[@id=\"js-known-search-advanced\"]/div[1]/div[8]/div[1]/select")
	private WebElement selectMinPrice;

	@FindBy(xpath = "//*[@id=\"js-known-search-advanced\"]/div[1]/div[8]/div[1]/select")
	private WebElement selectMaxPrice;
	
	@FindBy(xpath = "//*[@id=\"js-known-search-advanced\"]/div[2]/button")
	private WebElement searchButton;

	/**
	 * Advanced: input postcode
	 */
	public void sendPostcode(String pc) {
		inputPostcode.click();
		inputPostcode.sendKeys(pc);
		this.checkCarsAvailable();
	}

	/**
	 * Advanced: select by distance
	 */
	public void selectDistanceByValue(String value) {
		AppTest.advancedSearchTest.debug("selectDistanceByValue(): " + value);
		try {
			Select select = new Select(selectDistance);
			select.selectByValue(value);
			this.checkCarsAvailable();
		}
		catch (NoSuchElementException ex) {
			AppTest.advancedSearchTest.warning("Searching by distance [by select value] " + value + " had no results, results upon search may differ.");
		}
	}

	public void selectDistanceByText(String value) {
		AppTest.advancedSearchTest.debug("selectDistanceByText(): " + value);
		try {
			Select select = new Select(selectDistance);
			select.selectByVisibleText(value);
			this.checkCarsAvailable();
		}
		catch (NoSuchElementException ex) {
			AppTest.advancedSearchTest.warning("Searching by distance [by select text] " + value + " had no results, results upon search may differ.");
		}
	}

	/**
	 * Advanced: select by make
	 */
	public void selectMakeByValue(String value) {
		AppTest.advancedSearchTest.debug("selectMakeByValue(): " + value);
		try {
			Select select = new Select(selectMake);
			select.selectByValue(value);
			this.checkCarsAvailable();
		}
		catch(NoSuchElementException ex) {
			AppTest.advancedSearchTest.warning("Searching by make [by select value] " + value + " had no results, results upon search may differ");
		}
	}

	public void selectMakeByText(String value) {
		AppTest.advancedSearchTest.debug("selectMakeByText(): " + value);
		try {
			Select select = new Select(selectMake);
			select.selectByVisibleText(value);
			this.checkCarsAvailable();
		}
		catch(NoSuchElementException ex) {
			AppTest.advancedSearchTest.warning("Searching by make [by select text] " + value + " had no results, results upon search may differ");
		}
	}

	public void selectMakeByPartialText(String value) {
		AppTest.advancedSearchTest.debug("selectMakeByPartialText(): " + value);
		try {
			List<WebElement> options = selectMake.findElements(By.tagName("option"));
	
			for (WebElement option : options) {
				String optionText = option.getText();
				if (optionText.contains(value)) {
					option.click();
					this.checkCarsAvailable();
					break;
				}
			}
		}
		catch(NoSuchElementException ex) {
			AppTest.advancedSearchTest.warning("Searching by make [by select partial text] " + value + " had no results, results upon search may differ");
		}
	}

	/**
	 * Advanced: select by model
	 */
	public void selectModelByValue(String value) {
		AppTest.advancedSearchTest.debug("selectModelByValue(): " + value);
		try {
			Select select = new Select(selectModel);
			select.selectByValue(value);
			this.checkCarsAvailable();
		}
		catch(NoSuchElementException ex) {
			AppTest.advancedSearchTest.warning("Searching by model [by select value] " + value + " had no results, results upon search may differ");
		}
	}

	public void selectModelByText(String value) {
		AppTest.advancedSearchTest.debug("selectModelByText(): " + value);
		try {
			Select select = new Select(selectModel);
			select.selectByVisibleText(value);
			this.checkCarsAvailable();
		}
		catch(NoSuchElementException ex) {
			AppTest.advancedSearchTest.warning("Searching by model [by select text] " + value + " had no results, results upon search may differ");
		}
	}

	public void selectModelByPartialText(String value) {
		AppTest.advancedSearchTest.debug("selectModelByPartialText(): " + value);
		try {
			List<WebElement> options = selectModel.findElements(By.tagName("option"));
	
			for (WebElement option : options) {
				String optionText = option.getText();
				if (optionText.contains(value)) {
					option.click();
					this.checkCarsAvailable();
					break;
				}
			}
		}
		catch(NoSuchElementException ex) {
			AppTest.advancedSearchTest.warning("Searching by model [by select partial text] " + value + " had no results, results upon search may differ");
		}
	}
	
	/**
	 * Advanced: min price filters
	 */
	public void selectMinPriceByValue(String value) {
		AppTest.advancedSearchTest.debug("selectMinPriceByValue(): " + value);
		try {
			Select select = new Select(selectMinPrice);
			select.selectByValue(value);
			this.checkCarsAvailable();
		}
		catch(NoSuchElementException ex) {
			AppTest.advancedSearchTest.warning("Searching by min price [by select value] " + value + " had no results, results upon search may differ");
		}
	}
	public void selectMinPriceByText(String value) {
		AppTest.advancedSearchTest.debug("selectMinPriceByText(): " + value);
		try {
			Select select = new Select(selectMinPrice);
			select.selectByVisibleText(value);
			this.checkCarsAvailable();
		}
		catch(NoSuchElementException ex) {
			AppTest.advancedSearchTest.warning("Searching by min price [by select text] " + value + " had no results, results upon search may differ");
		}
	}
	public void selectMinPriceByPartialText(String value) {
		AppTest.advancedSearchTest.debug("selectMinPriceByPartialText(): " + value);
		try {
			List<WebElement> options = selectMinPrice.findElements(By.tagName("option"));
	
			for (WebElement option : options) {
				String optionText = option.getText();
				if (optionText.contains(value)) {
					option.click();
					this.checkCarsAvailable();
					break;
				}
			}
		}
		catch(NoSuchElementException ex) {
			AppTest.advancedSearchTest.warning("Searching by min price [by select partial text] " + value + " had no results, results upon search may differ");
		}
	}

	/**
	 * Advanced: max price filters
	 */
	public void selectMaxPriceByValue(String value) {
		AppTest.advancedSearchTest.debug("selectMaxPriceByValue(): " + value);
		try {
			Select select = new Select(selectMaxPrice);
			select.selectByValue(value);
			this.checkCarsAvailable();
		}
		catch(NoSuchElementException ex) {
			AppTest.advancedSearchTest.warning("Searching by max price [by select value] " + value + " had no results, results upon search may differ");
		}
	}
	public void selectMaxPriceByText(String value) {
		AppTest.advancedSearchTest.debug("selectMaxPriceByText(): " + value);
		try {
			Select select = new Select(selectMaxPrice);
			select.selectByVisibleText(value);
			this.checkCarsAvailable();
		}
		catch(NoSuchElementException ex) {
			AppTest.advancedSearchTest.warning("Searching by max price [by select text] " + value + " had no results, results upon search may differ");
		}
	}
	public void selectMaxPriceByPartialText(String value) {
		AppTest.advancedSearchTest.debug("selectMaxPriceByPartialText(): " + value);
		try {
			List<WebElement> options = selectMaxPrice.findElements(By.tagName("option"));
	
			for (WebElement option : options) {
				String optionText = option.getText();
				if (optionText.contains(value)) {
					option.click();
					this.checkCarsAvailable();
					break;
				}
			}
		}
		catch(NoSuchElementException ex) {
			AppTest.advancedSearchTest.warning("Searching by max price [by select partial text] " + value + " had no results, results upon search may differ");
		}
	}

	/**
	 * Advanced: checkbox toggle
	 */
	public void toggleCheckboxUsed() {
		AppTest.advancedSearchTest.debug("toggleCheckboxUsed()");
		try {
			checkboxUsed.click();
			this.checkCarsAvailable();
		}
		catch(NoSuchElementException ex) {
			AppTest.advancedSearchTest.warning("Searching by used [textbox toggle] was not found, results upon search may differ");
		}
	}

	public void toggleCheckboxNearlyNew() {
		AppTest.advancedSearchTest.debug("toggleCheckboxNearlyNew()");
		try {
			checkboxNewlyNew.click();
			this.checkCarsAvailable();
		}
		catch(NoSuchElementException ex) {
			AppTest.advancedSearchTest.warning("Searching by nearly new [textbox toggle] was not found, results upon search may differ");
		}
	}

	public void toggleCheckboxNew() {
		AppTest.advancedSearchTest.debug("toggleCheckboxNew()");
		try {
			checkboxNew.click();
			this.checkCarsAvailable();
		}
		catch(NoSuchElementException ex) {
			AppTest.advancedSearchTest.warning("Searching by new [textbox toggle] was not found, results upon search may differ");
		}
	}
	
	/**
	 * Advanced: search with filters
	 */
	public void clickSearch() {
		searchButton.click();
	}
	
	/**
	 * Helper utilities
	 */
	public void checkCarsAvailable() {
		AppTest.advancedSearchTest.debug("Advanced search reports: " + divAvailableCars.getText());
		
		if(divAvailableCars.getText().equals("1 car found")) {
			AppTest.advancedSearchTest.warning("The search filter has returned one car, all future filters will be ignored");
		}
	}

}
