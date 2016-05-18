package se.nackademin.librarytest;

import static com.codeborne.selenide.Selenide.*;

import java.util.UUID;

import org.junit.Test;

import static org.junit.Assert.*;

import org.junit.Ignore;
import se.nackademin.librarytest.helpers.AuthorHelper;
import se.nackademin.librarytest.helpers.Table;
import se.nackademin.librarytest.helpers.UserHelper;
import se.nackademin.librarytest.model.Author;
import se.nackademin.librarytest.pages.BookPage;
import se.nackademin.librarytest.pages.BrowseBooksPage;
import se.nackademin.librarytest.pages.EditBookPage;
import se.nackademin.librarytest.pages.EditUserPage;
import se.nackademin.librarytest.pages.MenuPage;
import se.nackademin.librarytest.pages.MyProfilePage;
import se.nackademin.librarytest.pages.PleaseConfirmDialog;

public class SelenideTest extends TestBase {

    public SelenideTest() {
    }
    
    //@Ignore
    @Test
    public void createNewAuthor() {
        /*1. Logga in som administratör (admin/1234567890)
          2. Skapa en ny författare. Tänk på att väldigt långa namn kan skapa problem med tabellerna.
          3. String uuid = "olesya";
          4. Sök efter din nya författare
          5. Klicka in på författaren och verifiera att den författardata som 
             visas är samma som användes då ni skapade författaren
        */
        giveInfoAboutAction("test#", "1");
        UserHelper.logInAsUser("admin", "1234567890");
        String uuid = (UUID.randomUUID().toString()).substring(0,10);
        AuthorHelper.createNewAuthor(uuid, uuid, uuid, uuid);
        Author author = AuthorHelper.fetchAuthor(uuid);
        assertEquals("Authors name should be "+ uuid, uuid+" "+uuid, author.getAuthorsName());
        assertEquals("Authors country should be "+ uuid, uuid, author.getCountry());
        assertEquals("Authors biography should be "+ uuid, uuid, author.getBiography()); 
        giveInfoAboutAction("checking that autors name is", uuid);
        sleep(3000);
        
    }   
}
