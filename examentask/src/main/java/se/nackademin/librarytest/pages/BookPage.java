/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nackademin.librarytest.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author testautomatisering
 */
public class BookPage extends MenuPage{

    @FindBy(css = "#gwt-uid-3")
    SelenideElement titleField;
    @FindBy(css = "#gwt-uid-5")
    SelenideElement authorField;
    @FindBy(css = "#gwt-uid-7")
    SelenideElement descriptionField;
    @FindBy(css = "#gwt-uid-11")
    SelenideElement datePublishedField;
    @FindBy(css = "#gwt-uid-9")
    SelenideElement isbnField;
    @FindBy(css = "#gwt-uid-13")
    SelenideElement numberOfCopiesAvailableField;
    @FindBy(css = "#gwt-uid-5 > div:nth-child(1) > div:nth-child(1)")
    SelenideElement booksAuthorLink;  
    
          
    @FindBy(css = "#borrow-book-button")
    SelenideElement borrowBookButton;   

    @FindBy(css = "#return-book-button")
    SelenideElement returnBookButton;   

    @FindBy(css = "#edit-book-button")
    SelenideElement editBookButton;

    @FindBy(css = "#delete-book-button")
    SelenideElement deleteBookButton;

   
    public String getTitle() {
        return titleField.getText();
    }

    public String getAuthor() {
        return authorField.getText();
    }

    public String getDescription() {
        return descriptionField.getText();
    }

    public String getIsbn() {
        return isbnField.getText();
    }
    
    public String getDatePublished() {
        return datePublishedField.getText();
    }

    public String getNumberOfCopiesAvailable() {
        return numberOfCopiesAvailableField.getText();
    }

    public void clickBooksAuthorLinkButton() {
        clickButton("edit book button", booksAuthorLink);
    }  

    public void clickEditBookButton() {
        clickButton("edit book button", editBookButton);
    }  
    
    public void clickBorrowBookButton() {
        clickButton("borrow book button", borrowBookButton);
    }  

    public void clickReturnBookButton() {
        clickButton("return book button", returnBookButton);
    }  

    public void clickDeleteBookButton() {
        clickButton("delete book button", deleteBookButton);
    }  
    
}
