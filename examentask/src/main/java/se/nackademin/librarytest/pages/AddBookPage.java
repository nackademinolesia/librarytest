package se.nackademin.librarytest.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

/**
 *
 * @author olesia
 */
public class AddBookPage extends MenuPage {
    @FindBy(css = "#gwt-uid-3")
    SelenideElement titleField;
    @FindBy(css = ".v-select-twincol-options")
    SelenideElement availableAuthorsList;
    @FindBy(css = ".v-select-twincol-selections")
    SelenideElement selectedeAuthorsList;
    @FindBy(css = "#gwt-uid-9")
    SelenideElement descriptionField;
    @FindBy(css = "#gwt-uid-11")
    SelenideElement numberOfPagesField;
    @FindBy(css = "#gwt-uid-13")
    SelenideElement isbnField;        
    @FindBy(css = "#gwt-uid-5")
    SelenideElement numberInInventory;
    @FindBy(css = "#gwt-uid-7")
    SelenideElement dataPublishedField;
    @FindBy(css = "#add-book-button")
    SelenideElement addBookButton;
    @FindBy(css = ".v-select-twincol-buttons > div:nth-child(1)")
    SelenideElement addAuthorToListButton;
    @FindBy(css = "div.v-button:nth-child(3)")
    SelenideElement deleteAuthorFromListButton;

    @FindBy(css = ".v-select-twincol-options > option:nth-child(1)")
    SelenideElement firstAuthorLinkFromAvailable;
    @FindBy(css = ".v-select-twincol-selections > option:nth-child(1)")
    SelenideElement firstAuthorLinkFromSelected;
    @FindBy(css = ".v-label-undef-w")
    SelenideElement infoAboutOperationsResult;
    @FindBy(css = "div.v-slot:nth-child(17) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1)")
    SelenideElement linkToViewNewAddedBook;
    
                   
    
   /* .v-select-twincol-buttons > div:nth-child(1)
    div.v-button:nth-child(3)
    div.v-button:nth-child(3)
    div.v-button:nth-child(3)
    //div[@id='main-content']/div/div/div[7]/div/div[2]/div[3]
   */ 
    

    public void setTitle(String booksTitle) {
        setTextFieldValue("book's title", booksTitle, titleField);
        
    }

    /*public void setAuthor(String author) {
        //SHOULD BE DEVELOP setTextFieldValue("add book's author: ", author, selectedeAuthorsList);
    }*/

    public void setDescription(String description) {
        setTextFieldValue("book's description:", description, descriptionField);
    }

    public void setNumberOfPage(String nmbOfPage) {
        //setTextFieldValue("nuber of page: ", nmbOfPage, numberOfPagesField);
        setIntegerFieldValue("nuber of page: ", nmbOfPage, numberOfPagesField);
    }

    public void setIsbn(String isbn) {
        setTextFieldValue("ISBN: ", isbn, isbnField);
        
    }

    public void setNumberInInventiory(String nmbInInv) {
        //setTextFieldValue("number in inventory: ", nmbInInv, numberInInventory);
        setIntegerFieldValue("number in inventory: ", nmbInInv, numberInInventory);
        
    }
 
    public void setDataPublished(String dataPublished) {
        setTextFieldValue("data published: ", dataPublished, dataPublishedField);
        
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
    
    public void clickAddBookButton() {
        clickButton("add boook button", addBookButton);
    }
 
    public String getInfoAboutResultOfAddingBook() {
        return getTextFieldValue(infoAboutOperationsResult);
    }    
    
    public void clickLinkToViewNewAddedBook() {
        
        clickButton("link to view new added book", linkToViewNewAddedBook);
    }    
      
}
