package se.nackademin.librarytest.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.FindBy;

/**
 * @author olesia
 */
public class BrowseAuthorsPage extends MenuPage {
    @FindBy(css = "#gwt-uid-3")
    private SelenideElement authorsTitleField;
    @FindBy(css = "#search-authors-button")
    private SelenideElement searchAuthorsButton;
    @FindBy(css = ".v-grid-cell-focused > a:nth-child(1)") 
    private SelenideElement authorsFirstResultTitle;
    @FindBy(css = ".v-grid-tablewrapper") 
    private SelenideElement authorsTable;

    public boolean clickAuthorsFirstResultTitle() {
        if (authorsFirstResultTitle.exists()) {
            clickButton("authors first result title", authorsFirstResultTitle);
            return true;
        }
        //else throw new NoSuchElementException("Could not find this author");
        else return false;

    }

    public void setAuthorsNameField(String title) {
        setTextFieldValue("authors title field", title, authorsTitleField);
    }

    public void clickSearchAuthorButton() {
        clickButton("search author", searchAuthorsButton);
    }
}
