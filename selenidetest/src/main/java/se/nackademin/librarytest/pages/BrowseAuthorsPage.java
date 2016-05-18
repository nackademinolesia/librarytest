package se.nackademin.librarytest.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author olesia
 */
public class BrowseAuthorsPage extends MenuPage /**/{
    @FindBy(css = "#gwt-uid-3")
    private SelenideElement authorsTitleField;
    @FindBy(css = "#search-authors-button")
    private SelenideElement searchAuthorsButton;
    @FindBy(css = ".v-grid-cell-focused > a:nth-child(1)") 
    private SelenideElement authorsFirstResultTitle;

    public void clickAuthorsFirstResultTitle() {
        clickButton("authors first result title", authorsFirstResultTitle);
    }

    public void setAuthorsTitleField(String title) {
        setTextFieldValue("authors title field", title, authorsTitleField);
    }

    public void clickSearchAuthorButton() {
        clickButton("search author", searchAuthorsButton);
    }
}
