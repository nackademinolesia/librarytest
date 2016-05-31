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
public class MyProfilePage extends MenuPage {
    @FindBy(css = "#gwt-uid-5")
    SelenideElement userDisplayNameField;

   /* @FindBy(css = "#gwt-uid-5")
    private SelenideElement passwordField;*/
       
    @FindBy(css = "#gwt-uid-7")
    private SelenideElement firstNameField;
    
    @FindBy(css = "#gwt-uid-9")
    private SelenideElement lastNameField;

    @FindBy(css = "#gwt-uid-11")
    private SelenideElement phoneField;
    
    @FindBy(css = "#gwt-uid-13")
    SelenideElement emailField;
    
    @FindBy(css = "#edit-user")
    SelenideElement editUserButton;
    
    @FindBy(css = "#delete-user-button")
    SelenideElement deleteUserButton;
    
    @FindBy(css = ".v-label")
    SelenideElement infoField;
    
    
    
        
    public String getUserDisplayName() {
        return userDisplayNameField.getText();
    }
    
    public String getUserEmail() {
        return emailField.getText();
    }
    
   /* public String getPasswordField() {
        return passwordField.getText();
    }*/

    public String getFirstNameField() {
        return firstNameField.getText();
    }

    public String getLastNameField() {
        return lastNameField.getText();
    }

    public String getPhoneField() {
        return phoneField.getText();
    }

    public void clickEditUserButton() {
        clickButton("edit user button", editUserButton);
    }

    public void clickDeleteUserButton() {
        clickButton("delete user button", deleteUserButton);
    }
 
    public String getInfoAboutResultOfMyProfile() {
        return getTextFieldValue(infoField);
    }        
}
