package se.nackademin.librarytest.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

/**
 *
 * @author olesia
 */
public class PleaseConfirmDialog extends MenuPage{
    @FindBy(css = "#confirmdialog-ok-button")
    SelenideElement confirmDialogOkButton;

    @FindBy(css = "#confirmdialog-cancel-button")
    SelenideElement confirmDialogCancelButton;
    
    public void clickYesButton() {
        clickButton("click Yes button", confirmDialogOkButton);
    }
    
}
