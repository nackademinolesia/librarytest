package se.nackademin.librarytest.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

/**
 *
 * @author olesia
 */
public class EditBookPage extends MenuPage{
    @FindBy(css = "#gwt-uid-3")
    private SelenideElement titleField;
    @FindBy(css = "#gwt-uid-9")
    private SelenideElement descriptionField;
    @FindBy(css = "#gwt-uid-11")
    private SelenideElement numberOfPageField;   
    @FindBy(css = "#gwt-uid-13")
    private SelenideElement isbnField;       
    @FindBy(css = "#gwt-uid-5")
    private SelenideElement numberInInventoryField;       
    @FindBy(css = "#gwt-uid-7")
    private SelenideElement datePublishedField;       
    
    @FindBy(css = "#save-book-button")   
    private SelenideElement saveChangesButton;
    
    public void changeDatePublish(String newDatePublished) {
        setTextFieldValue("Date published: ", newDatePublished, datePublishedField);
        
    }

    public void clickSaveUserButton() {
        clickButton("save change", saveChangesButton);
    }
    
}
