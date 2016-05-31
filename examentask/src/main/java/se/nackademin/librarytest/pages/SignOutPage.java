/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nackademin.librarytest.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

/**
 *
 * @author olesia
 */
public class SignOutPage extends MenuPage{
    @FindBy(css = ".v-label")
    private SelenideElement logOutField;
    public String getinfoAboutWhichUserLoggedOut() {
        return getTextFieldValue(logOutField);
    }        
    
}
