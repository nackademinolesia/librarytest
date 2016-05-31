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
                   
    @FindBy(css = ".v-select-twincol-buttons > div:nth-child(1)")
    SelenideElement addAuthorToListButton;
    @FindBy(css = "div.v-button:nth-child(3)")
    SelenideElement deleteAuthorFromListButton;
    @FindBy(css = ".v-select-twincol-options > option:nth-child(1)")
    SelenideElement firstAuthorLinkFromAvailable;
    @FindBy(css = ".v-select-twincol-selections > option:nth-child(1)")
    SelenideElement firstAuthorLinkFromSelected;
    
    
    @FindBy(css = "#save-book-button")   
    private SelenideElement saveChangesButton;
    @FindBy(css = ".v-label-undef-w")   
    private SelenideElement infoAboutOperationsResult;
    @FindBy(css = "div.v-slot:nth-child(17) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1)")   
    private SelenideElement linkToViewChangedBook;
    
    
    
    
    public void changeTitle(String newTitle) {
        setTextFieldValue("Title: ", newTitle, titleField);
        
    }

/*    public void changeAuthors(String newAuthor) {
//NEED BE DEVELOPPED        
    } */  
    
    public void changeDescription(String newDescription) {
        setTextFieldValue("Description: ", newDescription, descriptionField);
        
    }

    public void clickAddAuthorButton() {
        clickButton("add author button", addAuthorToListButton);
    }

    public void clickDeleteAuthorButton() {
        clickButton("add author button", deleteAuthorFromListButton);
    }
    
    public void choseFirstAuthorNameFromAvailable() {
        firstAuthorLinkFromAvailable.click();
    }

    public void choseFirstAuthorNamefromSelected() {
        firstAuthorLinkFromSelected.click();
    }
    

    public void changeNumberOfPages(String newNmbOfPg) {
        setIntegerFieldValue("Number of pages: ", newNmbOfPg, numberOfPageField);
        
    }
    
    public void changeIsbn(String newIsbn) {
        setTextFieldValue("ISBN: ", newIsbn, isbnField);
        
    }
    
    public void changeNumberIninventory(String newNmbInInv) {
        setIntegerFieldValue("Number in inventory: ", newNmbInInv, numberInInventoryField);
        
    }

    public void changeDatePublish(String newDatePublished) {
        setTextFieldValue("Date published: ", newDatePublished, datePublishedField);
        
    }

    public String getInfoAboutResultOfChangingBook() {
        return getTextFieldValue(infoAboutOperationsResult);
    }    
      
    public void clickSaveBookButton() {
        clickButton("save change", saveChangesButton);
    }
    
    public void clickLinkToViewChangedBook() {
        
        clickButton("link to view changed book", linkToViewChangedBook);
    }    

}
