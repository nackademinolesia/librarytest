package se.nackademin.librarytest.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author olesia
 */
public class AuthorPage extends MenuPage{

    @FindBy(css = "#gwt-uid-3")
    SelenideElement authorsNameField;
    @FindBy(css = "#gwt-uid-5")
    SelenideElement authorsCountryField;
    @FindBy(css = "#gwt-uid-7")
    SelenideElement authorsBiographyField;
    @FindBy(css = "#edit-author-button")
    SelenideElement editAuthorButton;
    @FindBy(css = "#delete-author-button")
    SelenideElement deleteAuthorButton;
    @FindBy(css = ".v-Notification")      
    SelenideElement messageAboutMistake;

            
    


    public String getAuthorsName() {
        return authorsNameField.getText();
    }

    public String getAuthorsCountry() {
        return authorsCountryField.getText();
    }

    public String getAuthorsBiography() {
        return authorsBiographyField.getText();
    }
    
    public void clickEditAuthorButton() {
        clickButton("edit author button", editAuthorButton);
    }  
 
    public void clickDeleteAuthorButton() {
        clickButton("delete author button", deleteAuthorButton);
    }  

    public String getInfoAboutMistake() {
        return getTextFieldValue(messageAboutMistake);
    }    
    
    public void clickOnFieldInfoAboutMistake() {
        clickButton("hide message about mistake", messageAboutMistake);
    }    
    
}
