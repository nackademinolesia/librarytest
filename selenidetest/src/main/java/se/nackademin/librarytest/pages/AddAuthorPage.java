package se.nackademin.librarytest.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

/**
 *
 * @author olesia
 */
public class AddAuthorPage extends MenuPage {

    @FindBy(css = "#gwt-uid-7")
    SelenideElement firstNameField;
    @FindBy(css = "#gwt-uid-9")
    SelenideElement lastNameField;  
    @FindBy(css = "#gwt-uid-3")
    SelenideElement countryField;  
    @FindBy(css = "#gwt-uid-5")
    SelenideElement biographyField;  
    @FindBy(css = "#add-author-button")
    SelenideElement addAuthorButton;

    public void setAuthorsName(String firstName) {
        setTextFieldValue("authors first name field", firstName, firstNameField);
    }

    public void setAuthorsLastName(String lastName) {
        setTextFieldValue("authors last name field", lastName, lastNameField);
    }

    public void setAuthorsCountry(String countriesName) {
        setTextFieldValue("author countries name field", countriesName, countryField);
    }

    public void setAuthorsBiography(String biography) {
        setTextFieldValue("author biography field", biography, biographyField);
    }

    public void clickAddNewAuthorButton() {
        clickButton("add user button", addAuthorButton);
    }
}
