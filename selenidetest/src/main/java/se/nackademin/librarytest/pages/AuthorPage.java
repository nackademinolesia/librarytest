package se.nackademin.librarytest.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author olesia
 */
public class AuthorPage {

    @FindBy(css = "#gwt-uid-3")
    SelenideElement authorsNameField;
    @FindBy(css = "#gwt-uid-5")
    SelenideElement authorsCountryField;
    @FindBy(css = "#gwt-uid-7")
    SelenideElement authorsBiographyField;

    public String getAuthorsName() {
        return authorsNameField.getText();
    }

    public String getAuthorsCountry() {
        return authorsCountryField.getText();
    }

    public String getAuthorsBiography() {
        return authorsBiographyField.getText();
    }
}
