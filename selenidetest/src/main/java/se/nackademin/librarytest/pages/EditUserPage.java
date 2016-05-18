package se.nackademin.librarytest.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

/**
 *
 * @author olesia
 */
public class EditUserPage extends MenuPage{
    @FindBy(css = "#gwt-uid-3")
    private SelenideElement userNameField;
    @FindBy(css = "#gwt-uid-5")
    private SelenideElement passwordField;
    @FindBy(css = "#gwt-uid-13")
    private SelenideElement emailField;       
    @FindBy(css = "#save-user-button")
    private SelenideElement saveUserButton;
    
    public void changeUsersPassword(String newEmail) {
        setTextFieldValue("email field", newEmail, emailField);
        
    }

    public void clickSaveUserButton() {
        clickButton("save user", saveUserButton);
    }
    
}
