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
public class AddUserPage extends MenuPage {

    @FindBy(css = "#gwt-uid-3")
    SelenideElement userDisplayNameField;
    @FindBy(css = "#gwt-uid-5")
    SelenideElement passwordField;
    @FindBy(css = "#gwt-uid-13")
    SelenideElement emailField;

    @FindBy(css = "#add-user-button")
    SelenideElement addUserButton;
    
//////////////////////////////////////////
    @FindBy(css = "#gwt-uid-16")
    SelenideElement roleLibrarion;
    
    @FindBy(css = "#gwt-uid-17")
    SelenideElement roleLoaner;

    @FindBy(css = "span.v-radiobutton:nth-child(1) > label:nth-child(2)")
    SelenideElement roleLibrarionLink;
    
    @FindBy(css = "span.v-radiobutton:nth-child(2) > label:nth-child(2)")
    SelenideElement roleLoanerLink;
    
    
    @FindBy(css = ".v-label-undef-w")
    SelenideElement infoAboutWrongUserName;
/////////////////////////////////////////    

    public void setUsername(String username) {
        setTextFieldValue("user name field", username, userDisplayNameField);
    }

    public void setPassword(String password) {
        setTextFieldValue("password field", password, passwordField);
    }

    public void setEmail(String email) {
        setTextFieldValue("email field", email, emailField);
    }

    public void clickRudioButtonLibrarian() {
        clickButton("librarian", roleLibrarionLink);
    }
   
    public void clickRudioButtonLoaner() {
        clickButton("loaner", roleLoanerLink);
    }
    
    public void clickAddUserButton() {
        clickButton("add user button", addUserButton);
    }    

    public String getMessageAboutOperationsResult() {
        return getTextFieldValue(/*"user with this name already exist", */infoAboutWrongUserName);
    }    
}
