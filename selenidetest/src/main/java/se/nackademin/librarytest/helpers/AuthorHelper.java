package se.nackademin.librarytest.helpers;

import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.Selenide.sleep;
import se.nackademin.librarytest.model.Author;
import se.nackademin.librarytest.pages.AddAuthorPage;
import se.nackademin.librarytest.pages.AuthorPage;
import se.nackademin.librarytest.pages.BrowseAuthorsPage;
import se.nackademin.librarytest.pages.MenuPage;

/**
 *
 * @author olesia
 */
public class AuthorHelper {
    

    public static void createNewAuthor(String firstName, String lastName, String country, String biography) {
        MenuPage menuPage = page(MenuPage.class);
        menuPage.navigateToAddAuthor();
        AddAuthorPage addAuthorPage = page(AddAuthorPage.class);
        addAuthorPage.setAuthorsName(firstName);
        addAuthorPage.setAuthorsLastName(lastName);
        addAuthorPage.setAuthorsCountry(country);
        addAuthorPage.setAuthorsBiography(biography);
        addAuthorPage.clickAddNewAuthorButton();
    }
    
        public static Author fetchAuthor(String searchQuery) {
        MenuPage menuPage = page(MenuPage.class);
        menuPage.navigateToBrowseAuthors();
        BrowseAuthorsPage browseAuthorsPage = page(BrowseAuthorsPage.class);
        browseAuthorsPage.setAuthorsTitleField(searchQuery);
        browseAuthorsPage.clickSearchAuthorButton();
        browseAuthorsPage.clickAuthorsFirstResultTitle();
        sleep(5000);
        AuthorPage authorPage = page(AuthorPage.class);
        Author author = new Author();
        author.setAuthorsName(authorPage.getAuthorsName());
        author.setCountry(authorPage.getAuthorsCountry());
        author.setBiography(authorPage.getAuthorsBiography());
        return author;
    }

}
