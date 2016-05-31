/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nackademin.librarytest.pages;

import static com.codeborne.selenide.Selenide.$;
import com.codeborne.selenide.SelenideElement;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.By;

/**
 * @author testautomatisering
 */
public class PageBase {
    public String usersRole;


    private static final Logger LOG = Logger.getLogger(PageBase.class.getName());
    
    protected void clickButton(String descriptor, SelenideElement element) {
        LOG.log(Level.INFO, "Clicking {0}", descriptor);
        element.click();
    }

    protected void selectRadioButton(String descriptor, SelenideElement element) {
        /*DOSNT WORK*/
        //LOG.log(Level.INFO, "Clicking {0}", descriptor);
        //element.selectRadio(By.name("Role"),descriptor);
        //$(By.name("Role")).selectRadio(descriptor);
        //element.selectRadio(element.getValue());
        //element.attr("checked");
        //getSelectedRadio();
        element.selectRadio(element.getValue());
       
        
    }
    
    protected void setTextFieldValue(String descriptor, String value, SelenideElement element) {
        LOG.log(Level.INFO, "Setting {0} to {1}", new Object[]{descriptor, value});
        element.clear();
        element.sendKeys(value);
    }

    protected void setIntegerFieldValue(String descriptor, String value, SelenideElement element) {
        LOG.log(Level.INFO, "Setting {0} to {1}", new Object[]{descriptor, value});
        element.clear();
        element.setValue(value);
    }
    
    protected String getTextFieldValue(SelenideElement element) {
        String text = element.getText();
        //LOG.log(Level.INFO, "Showing message {0}{1}", new Object[]{descriptor, text});
        LOG.log(Level.INFO, "Showing message {0}", text);
        return text;
    }
   
}
