package se.nackademin.librarytest.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

/**
 *
 * @author olesia
 */
public class EditAuthorPage extends MenuPage{
    
    @FindBy(css = "#gwt-uid-7")
    private SelenideElement firstNameField;
    @FindBy(css = "#gwt-uid-9")
    private SelenideElement lastNameField;
    @FindBy(css = "#gwt-uid-3")
    private SelenideElement countryField;
    @FindBy(css = "#gwt-uid-5")
    private SelenideElement biographyField;
    
    @FindBy(css = "#save-author-button")   
    private SelenideElement saveChangesButton;
    @FindBy(css = ".v-label-undef-w")   
    private SelenideElement infoAboutOperationsResult;
    @FindBy(css = "div.v-slot:nth-child(13) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1)") 
    private SelenideElement linkToViewChangedAuthor;
    
    
    
    
    public void changeAuthorFirstName(String newFirstName) {
        setTextFieldValue("first name: ", newFirstName, firstNameField);
        
    }

    public void changeAuthorLastName(String newLastName) {
        setTextFieldValue("last name: ", newLastName, lastNameField);
        
    }

    public void changeAuthorCountry(String newCountry) {
        setTextFieldValue("country: ", newCountry, countryField);
        
    }

    public void changeAuthorBiography(String newBiography) {
        setTextFieldValue("biography: ", newBiography, biographyField);
        
    }
 
    public void clickSaveAuthorButton() {
        clickButton("save change", saveChangesButton);
    }
    
    public String getInfoAboutResultOfChangingAuthor() {
        return getTextFieldValue(infoAboutOperationsResult);
    }  
    
    public void clickLinkToViewChangedAuthor() {
        
        clickButton("link to view changed author", linkToViewChangedAuthor);
    }    
    
    
}
