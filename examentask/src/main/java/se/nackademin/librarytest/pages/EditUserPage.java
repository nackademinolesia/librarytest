package se.nackademin.librarytest.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

/**
 *
 * @author olesia
 */
public class EditUserPage extends MenuPage{
    @FindBy(css = "#gwt-uid-3")
    private SelenideElement userDisplayNameField;
    @FindBy(css = "#gwt-uid-5")
    private SelenideElement passwordField;
    @FindBy(css = "#gwt-uid-7")
    private SelenideElement firstNameField;
    @FindBy(css = "#gwt-uid-9")
    private SelenideElement lastNameField;
    @FindBy(css = "#gwt-uid-11")
    private SelenideElement phoneField;    
    @FindBy(css = "#gwt-uid-13")
    private SelenideElement emailField;       
    @FindBy(css = "#save-user-button")
    private SelenideElement saveUserButton;

    @FindBy(css = "#gwt-uid-17") 
    private SelenideElement loanerRadioButton;       
    @FindBy(css = "#gwt-uid-16")
    private SelenideElement librarianRadioButton;
    @FindBy(css = "#gwt-uid-15")
    private SelenideElement tmp3;       
    
    
    @FindBy(css = "span.v-radiobutton:nth-child(1) > label:nth-child(2)")
    private SelenideElement librarianRadioButtonLink;       
    @FindBy(css = "span.v-radiobutton:nth-child(2) > label:nth-child(2)")
    private SelenideElement loanerRadioButtonLink;
    @FindBy(css = ".v-label-undef-w")
    private SelenideElement infoAboutOperationsResult;       
    

 

    
    public void changeUsersDisplayName(String newDisplayName) {
        setTextFieldValue("display name", newDisplayName, userDisplayNameField);       
    }

    public void changePassword(String newPassword) {
        setTextFieldValue("password", newPassword, passwordField);       
    }
    
    public void changeUsersEmail(String newEmail) {
        setTextFieldValue("email field", newEmail, emailField);        
    }

    public void changeUsersFirstName(String newFirstName) {
        setTextFieldValue("first name", newFirstName, firstNameField);       
    }   

    public void changeUsersLastName(String newLastName) {
        setTextFieldValue("last name", newLastName, lastNameField);        
    }   

    public void changePhone(String newPhone) {
        setTextFieldValue("phone", newPhone, phoneField);        
    }   
    
    public String checkRadioButtonsStatus() {
        String isChecked = loanerRadioButton.attr("checked");
        if (isChecked == null) {
            isChecked = librarianRadioButton.attr("checked");
            if (isChecked == null) {
                throw new NullPointerException("Should be chosen one type of the user");
            }
            else isChecked = "Librarian";
        }
        else isChecked = "Loaner";
        return isChecked;                
    }   
    
    public void clickSaveUserButton() {
        clickButton("save user", saveUserButton);
    }
    
    public void clickLoanerRadioButton() {
         clickButton("Loaner", loanerRadioButtonLink);
    }

    
    public void clickLibrarianRadioButton() {
        
        clickButton("Librarian", librarianRadioButtonLink);
    
    
    }

/*DOSNT WORK    public void selectRadioButton(String descriptor) {
        
        selectRadioButton(descriptor, loanerRadioButton);
        
    }
*/

    public String getInfoAboutResultOfChangingUser() {
        return getTextFieldValue(infoAboutOperationsResult);
    }    
    
}
