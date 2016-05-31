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
public class WelcomePage extends MenuPage {
    
    @FindBy(css = "div.v-margin-top > div:nth-child(5) > div:nth-child(1)")
    //@FindBy(css = ".v-margin-top > div:nth-child(3) > div:nth-child(1)")
    SelenideElement loginInAsField;
    
    public String getinfoAboutWhichUserLoggedIn() {
        return getTextFieldValue(loginInAsField);
    }        
    
}
