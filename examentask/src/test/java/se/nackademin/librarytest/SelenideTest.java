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
import se.nackademin.librarytest.pages.AddAuthorPage;
import se.nackademin.librarytest.pages.AddBookPage;
import se.nackademin.librarytest.pages.AddUserPage;
import se.nackademin.librarytest.pages.AuthorPage;
import se.nackademin.librarytest.pages.BookPage;
import se.nackademin.librarytest.pages.BrowseAuthorsPage;
import se.nackademin.librarytest.pages.BrowseBooksPage;
import se.nackademin.librarytest.pages.EditAuthorPage;
import se.nackademin.librarytest.pages.EditBookPage;
import se.nackademin.librarytest.pages.EditUserPage;
import se.nackademin.librarytest.pages.MenuPage;
import se.nackademin.librarytest.pages.MyProfilePage;
import se.nackademin.librarytest.pages.PleaseConfirmDialog;
import se.nackademin.librarytest.pages.SignOutPage;
import se.nackademin.librarytest.pages.WelcomePage;

public class SelenideTest extends TestBase {
    private int sleepTime = 500;
    
    //@Ignore
    @Test /*NOBODY IS REGISTRED*/
    public void nobodyIsRegistered() {
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////
        //Browse Books
        String info;
        usersRole = "";
        MenuPage menuPage = page(MenuPage.class);
        BrowseBooksPage browseBooksPage = page(BrowseBooksPage.class);
        BrowseAuthorsPage browseAuthorsPage = page(BrowseAuthorsPage.class);
        BookPage bookPage = page(BookPage.class);
        AuthorPage authorPage = page(AuthorPage.class);
        
        menuPage.navigateToBrowseBooks();
        browseBooksPage.setTitleField("Oryx and Crake");
        browseBooksPage.clickSearchBookButton();
        browseBooksPage.clickFirstResultTitle();
        assertEquals("Book's name should be 'Oryx and Crake'", "Oryx and Crake", bookPage.getTitle());
        assertEquals("Book's author should be 'Margaret Atwood'", "Margaret Atwood", bookPage.getAuthor());
        assertEquals("Book's description should be 'The novel focuses on a post-apocalyptic character named Snowman, living near a group of primitive human-like creatures whom he calls Crakers.'", "The novel focuses on a post-apocalyptic character named Snowman, living near a group of primitive human-like creatures whom he calls Crakers.", bookPage.getDescription()); 
        assertEquals("Book's ISBN should be '0-7710-0868-6'", "0-7710-0868-6", bookPage.getIsbn());
        assertEquals("Book's Date published should be '2003-04-01'", "2003-04-01", bookPage.getDatePublished());
                /*numbers of copies available */
        bookPage.clickBooksAuthorLinkButton();
        sleep(sleepTime);
        assertEquals("Author's name should be 'Margaret Atwood'", "Margaret Atwood", authorPage.getAuthorsName());
        
        //////////////////////////////////////////////////////////////////////////////////////////////////////////
        
        //BROWSE AUTHORS
        
        menuPage.navigateToBrowseAuthors();
        browseAuthorsPage.setAuthorsNameField("Stephen Baxter");
        browseAuthorsPage.clickSearchAuthorButton();
        sleep(sleepTime);
        browseAuthorsPage.clickAuthorsFirstResultTitle();
        sleep(sleepTime);
        assertEquals("Author's name should be 'Stephen Baxter'", "Stephen Baxter", authorPage.getAuthorsName());
        assertEquals("Author's country should be 'Great Britain'", "Great Britain", authorPage.getAuthorsCountry());
        assertEquals("Author's biography should be 'Stephen Baxter (born 13 November 1957) is a prolific British hard science fiction author. He has degrees in mathematics and engineering. Strongly influenced by SF pioneer H. G. Wells, Baxter has been a distinguished Vice-President of the international H. G. Wells Society since 2006.'", "Stephen Baxter (born 13 November 1957) is a prolific British hard science fiction author. He has degrees in mathematics and engineering. Strongly influenced by SF pioneer H. G. Wells, Baxter has been a distinguished Vice-President of the international H. G. Wells Society since 2006.", authorPage.getAuthorsBiography()); 
        //////////////////////////////////////////////////////////////////////////////////////////////////////////
        
        //CREATE A NEW USER
        String uuid = (UUID.randomUUID().toString()).substring(0,10);
        //try to create user with empty name
        UserHelper.createNewUser("", uuid, uuid+"@example.com", false);        
        info = UserHelper.getMessageAboutReasonAnableToAddUser();
        assertEquals("Should be swown message 'Invalid data, please try again.'", "Invalid data, please try again.", info);        
        //create user
        UserHelper.createNewUser(uuid, uuid, uuid+"@example.com", false);
        //try to create user with already existed name
        UserHelper.createNewUser(uuid, uuid, uuid+"@example.com", false);
        info = UserHelper.getMessageAboutReasonAnableToAddUser();
        assertEquals("Should be swown message 'Unable to add user: Unable to create entity: Bad Request, User with same display name already exists.'", "Unable to add user: Unable to create entity: Bad Request, User with same display name already exists.", info);
        //////////////////////////////////////////////////////////////////////////////////////////////////////////

        //SIGN IN
        //try to sign in with wrong data
        UserHelper.logInAsUser("8888", "8");
        sleep(sleepTime);
        info = UserHelper.getMessageAboutReasonAnableToLoggIn();
        assertEquals("Should be swown message 'Error: Wrong password.'", "Error: Wrong password.", info);
        //try to sign in with empty data
        UserHelper.logInAsUser("", "");
        info = UserHelper.getMessageAboutReasonAnableToLoggIn();       
        assertEquals("Should be swown message 'Error:'", "Error:", info);        
        UserHelper.logInAsUser(uuid, uuid);
        WelcomePage welcomePage = page(WelcomePage.class);
        sleep(sleepTime);
        info = welcomePage.getinfoAboutWhichUserLoggedIn();       
        assertEquals("Should be swown message 'Logged in as "+uuid+"'", "Logged in as "+uuid+".", info);
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //SIGN OUT
        UserHelper.logOutUser();
        SignOutPage signOutPage = page(SignOutPage.class);
        sleep(sleepTime);
        info = signOutPage.getinfoAboutWhichUserLoggedOut();
        assertEquals("Should be swown message 'Signed out user "+uuid+"'", "Signed out user "+uuid+".", info);       
        //try to logg out second time
        UserHelper.logOutUser();
        sleep(sleepTime);
        info = signOutPage.getinfoAboutWhichUserLoggedOut();
        assertEquals("Should be swown message 'Not signed in.'", "Not signed in.", info);       
        
    }
    
    //@Ignore
    @Test /*NORMLAL USER IS REGISTRED*/
    public void normalUserIsRegistered() {

        String info;
        usersRole = "loaner";
        MenuPage menuPage = page(MenuPage.class);
        BrowseBooksPage browseBooksPage = page(BrowseBooksPage.class);
//        BrowseAuthorsPage browseAuthorsPage = page(BrowseAuthorsPage.class);
        BookPage bookPage = page(BookPage.class);
        AuthorPage authorPage = page(AuthorPage.class);
        AddUserPage addUserPage = page(AddUserPage.class);
        PleaseConfirmDialog pleaseConfirmDialog = page(PleaseConfirmDialog.class);
        WelcomePage welcomePage = page(WelcomePage.class);
        MyProfilePage myProfilePage = page(MyProfilePage.class);
        EditUserPage editUserPage = page(EditUserPage.class);

        //CREATE A NEW USER AND TRY TO LOGG IN/LOGG OUT
        String uuid = (UUID.randomUUID().toString()).substring(0,10);
        UserHelper.createNewUser(uuid, uuid, uuid+"@example.com", false);
        
        UserHelper.createNewUser("admin", "admin", "admin"+"@example.com", false);
        sleep(sleepTime);
        String message = addUserPage.getMessageAboutOperationsResult();
        assertEquals("Should be given message: ", "Unable to add user: Unable to create entity: Bad Request, User with same display name already exists.", message);
        UserHelper.logInAsUser(uuid, uuid);
        sleep(sleepTime);
        message = welcomePage.getinfoAboutWhichUserLoggedIn();       
        assertEquals("Should be swown message 'Logged in as "+uuid+"'", "Logged in as "+uuid+".", message);
        //////////////////////////////////////////////////////////////////////////////////////////////////////////
        
        //BROWSE BOOKS
        
        menuPage.navigateToBrowseBooks();
        sleep(sleepTime);
        browseBooksPage.setTitleField("American");
        browseBooksPage.clickSearchBookButton();
        browseBooksPage.clickFirstResultTitle();
        //should cheek if there some numbers of this book able to loan
        
        String numberBooksAvailable = bookPage.getNumberOfCopiesAvailable();
        String borrowBooksName = bookPage.getTitle();
        Integer i = convertToInteger(numberBooksAvailable);
        bookPage.clickBorrowBookButton();
        sleep(sleepTime);
        pleaseConfirmDialog.clickNoButton();
        sleep(sleepTime);       
        assertEquals("Book's name should be 'American Gods'", "American Gods", bookPage.getTitle());
        bookPage.clickBorrowBookButton();
        sleep(sleepTime);
        pleaseConfirmDialog.clickYesButton();
        sleep(sleepTime);
        i--;
        String s1=i.toString();
        assertEquals("Numbers of available books should be equal "+s1, s1, bookPage.getNumberOfCopiesAvailable());
        assertEquals("Book's name should be 'American Gods'", "American Gods", bookPage.getTitle());
                /*numbers of copies available */
        bookPage.clickBooksAuthorLinkButton();
        assertEquals("Author's name should be 'Neil Gaiman'", "Neil Gaiman", authorPage.getAuthorsName());


        menuPage.navigateToMyProfile();
        sleep(sleepTime);
        Table table = new Table($(".v-grid-tablewrapper"));
        table.searchAndClick(borrowBooksName, 0);
        
        bookPage.clickReturnBookButton();
        pleaseConfirmDialog.clickNoButton();
        sleep(sleepTime);
        assertEquals("Book's name should be 'American Gods'", "American Gods", bookPage.getTitle());
        bookPage.clickReturnBookButton();
        sleep(sleepTime);
        pleaseConfirmDialog.clickYesButton();
        sleep(sleepTime);
        
        assertEquals("Numbers of available books should be equal "+numberBooksAvailable, numberBooksAvailable, bookPage.getNumberOfCopiesAvailable());
        giveInfoAboutAction("checking that numbers of available books is", numberBooksAvailable);
        
        //MY PROFILE
        menuPage.navigateToMyProfile();        
        sleep(sleepTime);
        assertEquals("Users displayName should be "+uuid+"'", uuid, myProfilePage.getUserDisplayName());
        myProfilePage.clickEditUserButton();
        sleep(sleepTime);
        String uuid1 = (UUID.randomUUID().toString()).substring(0,10);
        //try to change user's name to other, which already exist
        editUserPage.changeUsersDisplayName("admin");
        //editUserPage.changePassword("1234567890");
        
        editUserPage.clickSaveUserButton();
        sleep(sleepTime);
        assertEquals("Should be given message:  ", "Unable to save changes to user: Unable to update entity: Bad Request, Another user with same display name already exists.", editUserPage.getInfoAboutResultOfChangingUser());
        
        //editUserPage.changeUsersDisplayName(uuid1); //ERROR IN PROGRAM smotri opisanie v faile
        editUserPage.changeUsersDisplayName(uuid);//ispravlyaem obratno posle togo kak vveli "admin"
        editUserPage.changeUsersFirstName(uuid1);
        editUserPage.changeUsersLastName(uuid1);
        editUserPage.changePassword(uuid1);
        editUserPage.changePhone("5555555");
        editUserPage.changeUsersEmail(uuid1+"@example.com");
        sleep(sleepTime);        
        editUserPage.clickSaveUserButton();
        sleep(sleepTime);
        //assertEquals("Should be given message:  ", "Updated user:", editUserPage.getInfoAboutResultOfChangingUser());
        
        menuPage.navigateToMyProfile();
/*        assertEquals("Users new display name should be shown in profile '"+uuid1+"'", uuid1, myProfilePage.getUserDisplayName());
        giveInfoAboutAction("checking that Users display name is", uuid1);////ERROR IN PROGRAM smotri opisanie v faile*/
        
        assertEquals("Users new first name should be shown in profile '"+uuid1+"'", uuid1, myProfilePage.getFirstNameField());
        giveInfoAboutAction("checking that Users first name is", uuid1);

        assertEquals("Users new last name should be shown in profile  '"+uuid1+"'", uuid1, myProfilePage.getLastNameField());
        giveInfoAboutAction("checking that Users last name is", uuid1);
        
        /*Ne proverit parol, ego net na stranice MyProfilePage assertEquals("Users new password should be shown in profile '"+uuid1+"'", uuid1, myProfilePage.getPasswordField());
        giveInfoAboutAction("checking that Users password is", uuid1);*/

        assertEquals("Users new phone should be shown in profile '5555555'", "5555555", myProfilePage.getPhoneField());
        giveInfoAboutAction("checking that Users password is", "5555555");
        
        assertEquals("Users email should be shown in profile  '"+uuid1+"@example.com'", uuid1+"@example.com", myProfilePage.getUserEmail());
        giveInfoAboutAction("checking that Users email is", uuid1+"@example.com");
        
        
        //SIGN OUT
        UserHelper.logOutUser();
        SignOutPage signOutPage = page(SignOutPage.class);
        sleep(sleepTime);
        info = signOutPage.getinfoAboutWhichUserLoggedOut();
        assertEquals("Should be swown message 'Signed out user "+uuid+"'", "Signed out user "+uuid+".", info);       
        
        UserHelper.logOutUser();
        sleep(sleepTime);
        info = signOutPage.getinfoAboutWhichUserLoggedOut();
        assertEquals("Should be swown message 'Not signed in.'", "Not signed in.", info);       
        
    }
    
    //@Ignore
    @Test /*ADMIN IS REGISTRED*/
    public void adminIsRegistered() {
        MenuPage menuPage = page(MenuPage.class);
        MyProfilePage myProfilePage = page(MyProfilePage.class);
        EditUserPage editUserPage = page(EditUserPage.class);
//        BrowseBooksPage browseBooksPage = page(BrowseBooksPage.class);
//        BrowseAuthorsPage browseAuthorsPage = page(BrowseAuthorsPage.class);
        BookPage bookPage = page(BookPage.class);
        EditBookPage editBookPage = page(EditBookPage.class);
        AddUserPage addUserPage = page(AddUserPage.class);
        AddBookPage addBookPage = page(AddBookPage.class);
        PleaseConfirmDialog pleaseConfirmDialog = page(PleaseConfirmDialog.class);
        WelcomePage welcomePage = page(WelcomePage.class);
        String uuid;
        
        //LOGG IN AS ADMIN
//        String info;
//        usersRole = "librarian";
        UserHelper.logInAsUser("admin", "1234567890");
        menuPage.navigateToMyProfile();
        myProfilePage.clickEditUserButton();
        sleep(sleepTime);
        String checking;
        editUserPage.clickLoanerRadioButton();
        sleep(sleepTime);
        checking = editUserPage.checkRadioButtonsStatus();
        assertEquals("user should be registred as 'Loaner'", "Loaner", checking); 
        
        editUserPage.clickLibrarianRadioButton();
        sleep(sleepTime);
        checking = editUserPage.checkRadioButtonsStatus();
        assertEquals("user should be registred as 'Librarian'", "Librarian", checking);
        sleep(sleepTime);
        ////////////////////////////////
        //admin create other user with librarian rigths, delete him after that.
        uuid = (UUID.randomUUID().toString()).substring(0,10);

        UserHelper.createNewUser(uuid, uuid, uuid+"@example.com", true);
        String message = addUserPage.getMessageAboutOperationsResult();
        assertEquals("Should be given message: 'Added user:' ", "Added user:", message);                
        sleep(sleepTime);
        UserHelper.logInAsUser(uuid, uuid);
        menuPage.navigateToMyProfile();
        sleep(sleepTime);
        myProfilePage.clickDeleteUserButton();
        sleep(sleepTime);
        pleaseConfirmDialog.clickYesButton();
        sleep(sleepTime);
        assertEquals("Should be given the message: 'Logged in as "+uuid, "Logged in as "+uuid+".", welcomePage.getinfoAboutWhichUserLoggedIn());
        menuPage.navigateToMyProfile();
        sleep(sleepTime);
        assertEquals("Should be given the message: 'You lack permission to view this page.", "You lack permission to view this page.", myProfilePage.getInfoAboutResultOfMyProfile());
        /////////////////////////////
        UserHelper.logInAsUser("admin", "1234567890");

        
        /*ADD BOOK*/
        uuid = (UUID.randomUUID().toString()).substring(0,10);
        menuPage.navigateToAddBook();
        sleep(sleepTime);
        //try to add book with empty data
        addBookPage.clickAddBookButton();
        assertEquals("should be given the message 'Invalid data, please try again.'", "Invalid data, please try again.", addBookPage.getInfoAboutResultOfAddingBook());
        sleep(sleepTime);
        //try to add book with wrong data
        addBookPage.setTitle("try");
        addBookPage.setDescription("try");
        addBookPage.setNumberOfPage("try");
        addBookPage.setIsbn("try");
        addBookPage.setNumberInInventiory("try");
        addBookPage.setDataPublished("try");
        addBookPage.clickAddBookButton();
        assertEquals("should be given the message 'Invalid data, please try again.'", "Invalid data, please try again.", addBookPage.getInfoAboutResultOfAddingBook());
        sleep(sleepTime);
        
        //add a book
        addBookPage.setTitle(uuid);
        addBookPage.setDescription(uuid);
        addBookPage.setNumberOfPage("100");
        addBookPage.setIsbn("22222");
        addBookPage.setNumberInInventiory("5");
        addBookPage.setDataPublished("2011-11-11");
        addBookPage.choseFirstAuthorNameFromAvailable();
        addBookPage.clickAddAuthorButton();
        addBookPage.clickAddBookButton();
        sleep(sleepTime);
        assertEquals("should be given the message 'Added book:'", "Added book:", addBookPage.getInfoAboutResultOfAddingBook());
        addBookPage.clickLinkToViewNewAddedBook();
        sleep(sleepTime);
        assertEquals("book's name should be '"+uuid+"'", uuid, bookPage.getTitle());    
        
        
        /*EDIT BOOK*/
        bookPage.clickEditBookButton();
        sleep(sleepTime);
        //try to add book with wrong data
        editBookPage.changeTitle("");
        editBookPage.clickSaveBookButton();
        sleep(sleepTime);
        assertEquals("should be given the message 'Invalid data, please try again.'", "Invalid data, please try again.", editBookPage.getInfoAboutResultOfChangingBook());
        
        //change book's information
        editBookPage.changeTitle("My Book"); 
        //editBookPage.changeAuthors("Olesia"); NEED TO BE DEVELOPED
        editBookPage.choseFirstAuthorNameFromAvailable();
        editBookPage.clickAddAuthorButton();
        sleep(sleepTime);
        editBookPage.choseFirstAuthorNamefromSelected();
        editBookPage.clickDeleteAuthorButton();
        sleep(sleepTime);

        editBookPage.changeNumberOfPages("111");
        editBookPage.changeDescription("One interesting Book");        
        editBookPage.changeIsbn("11111");
        editBookPage.changeNumberIninventory("15");
        editBookPage.changeDatePublish("1212-12-12");
        editBookPage.clickSaveBookButton();
        sleep(sleepTime);
        assertEquals("should be given the message 'Saved changes to book:'", "Saved changes to book:", editBookPage.getInfoAboutResultOfChangingBook());
        editBookPage.clickLinkToViewChangedBook();
        sleep(sleepTime);
        assertEquals("book's name should be 'My Book'", "My Book", bookPage.getTitle());

        
        /*DELETE BOOK*/
        bookPage.clickDeleteBookButton();
        sleep(sleepTime);
        pleaseConfirmDialog.clickNoButton();
        sleep(sleepTime);       
        assertEquals("Book's name should be 'My Book'", "My Book", bookPage.getTitle());
        bookPage.clickDeleteBookButton();
        sleep(sleepTime);
        pleaseConfirmDialog.clickYesButton();
        sleep(sleepTime);
        assertEquals("Should be given the message: 'Logged in as "+"admin.'", "Logged in as "+"admin.", welcomePage.getinfoAboutWhichUserLoggedIn());
        
        /*ADD AUTHOR*/
        AddAuthorPage addAuthorPage = page(AddAuthorPage.class);
        AuthorPage authorPage = page(AuthorPage.class);
        String authorsInfo = (UUID.randomUUID().toString()).substring(0,10);
        AuthorHelper.createNewAuthor(authorsInfo, authorsInfo, authorsInfo, authorsInfo);
        assertEquals("should be given the message 'Added author:'", "Added author:", addAuthorPage.getInfoAboutResultOfAddingAuthor());
        addAuthorPage.clickLinkToViewNewAddedAuthor();
        sleep(sleepTime);

        assertEquals("Authors name should be "+ authorsInfo, authorsInfo+" "+authorsInfo, authorPage.getAuthorsName());
        assertEquals("Authors country should be "+ authorsInfo, authorsInfo, authorPage.getAuthorsCountry());
        assertEquals("Authors biography should be "+ authorsInfo, authorsInfo, authorPage.getAuthorsBiography()); 
        giveInfoAboutAction("checking that autors name is", authorsInfo);
        sleep(sleepTime);
        
        /*EDIT AUTHOR*/
        authorPage.clickEditAuthorButton();
        sleep(sleepTime);
        EditAuthorPage editAuthorPage = page(EditAuthorPage.class);
        editAuthorPage.changeAuthorFirstName("Olesia");
        editAuthorPage.changeAuthorLastName("Boiarchuk");
        editAuthorPage.changeAuthorCountry("Russia");
        editAuthorPage.changeAuthorBiography("My biography");
        editAuthorPage.clickSaveAuthorButton();
        sleep(sleepTime);
        assertEquals("should be given the message 'Saved changes to author:'", "Saved changes to author:", editAuthorPage.getInfoAboutResultOfChangingAuthor());
        editAuthorPage.clickLinkToViewChangedAuthor();
        sleep(sleepTime);
        assertEquals("author's name should be 'Olesia Boiarchuk'", "Olesia Boiarchuk", authorPage.getAuthorsName());
        giveInfoAboutAction("checking that autor's name is", "Olesia Boiarchuk");
        assertEquals("author's country should be 'Russia'", "Russia", authorPage.getAuthorsCountry());
        giveInfoAboutAction("checking that autor's country is", "Russia");
        assertEquals("author's biography should be 'My biography'", "My biography", authorPage.getAuthorsBiography());
        giveInfoAboutAction("checking that autor's biography is", "My biography");
        
        
        /*DELETE AUTHOR*/
        authorPage.clickDeleteAuthorButton();
        //posle udalenia proverit chto avtora net v base!!!
        pleaseConfirmDialog.clickNoButton();
        sleep(sleepTime);       
        assertEquals("Author's name should be 'Olesia Boiarchuk'", "Olesia Boiarchuk", authorPage.getAuthorsName());
        authorPage.clickDeleteAuthorButton();
        sleep(sleepTime);
        pleaseConfirmDialog.clickYesButton();
        sleep(sleepTime);
        assertEquals("Should be given the message: 'Logged in as "+"admin.'", "Logged in as "+"admin.", welcomePage.getinfoAboutWhichUserLoggedIn());
        Author author = page (Author.class);
        author = AuthorHelper.fetchAuthor("Olesia");
        assertEquals("Author should not exist more in the list", null, author);
        sleep(sleepTime);
        
        /////////////////////
        //try to delete author, if he still has a book
        author = AuthorHelper.fetchAuthor("Terry");
        sleep(sleepTime);
        authorPage.clickDeleteAuthorButton();
        sleep(sleepTime);
        pleaseConfirmDialog.clickYesButton();
        sleep(sleepTime);
        assertEquals("Should be given the message: 'ErrorUnable to delete entity: Conflict, Unable to delete author - author still has books in the database?'", "ErrorUnable to delete entity: Conflict, Unable to delete author - author still has books in the database?", authorPage.getInfoAboutMistake());
        authorPage.clickOnFieldInfoAboutMistake();
        sleep(sleepTime);
        /////////////////////
        
    }
    
    
    
    /**********************************************************/    
    @Ignore
    //@Test
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
        assertEquals("Authors name should be "+ uuid, uuid+" "+uuid, author.getFirstName());//.getAuthorsName());
        assertEquals("Authors country should be "+ uuid, uuid, author.getCountry());
        assertEquals("Authors biography should be "+ uuid, uuid, author.getBiography()); 
        giveInfoAboutAction("checking that autors name is", uuid);
        sleep(300);
        
    }

    
    @Ignore
    //@Test
     public void changeUsersEmail() {
     /* 1.Skapa en ny användare
        2.Logga in med din nya användare
        3.Gå in på "My profile"
        4.Ändra e-mailadressen till en ny adress
        5.Gå in på "My profile" igen
        6.Verifiera att e-mailadressen har ändrats*/
        giveInfoAboutAction("test#", "2");
        MenuPage menuPage = page(MenuPage.class);
        MyProfilePage myProfilePage = page(MyProfilePage.class);
        EditUserPage editUserPage = page(EditUserPage.class);
        String uuid = (UUID.randomUUID().toString()).substring(0,10);
        UserHelper.createNewUser(uuid, uuid, uuid+"@example.com", false);
        UserHelper.logInAsUser(uuid, uuid);
        
        menuPage.navigateToMyProfile();
        myProfilePage.clickEditUserButton();
        editUserPage.changeUsersEmail("1@example.com");
        editUserPage.clickSaveUserButton();
        menuPage.navigateToMyProfile();
        assertEquals("Users email should be shown in profile", "1@example.com", myProfilePage.getUserEmail());
        giveInfoAboutAction("checking that Users email is", "1@example.com");
        sleep(300);
    
     }
     
     @Ignore
     //@Test
     public void changePublicateDate() {
        /*Ändra publiceringsdatum
        1. Logga in som administratör (admin/1234567890)
        2. Navigera till boken "Good Omens" (ex. genom att söka böcker eller författare)
        3. Tryck "Edit book"
        4. Ändra publiceringsdatumet till ett nytt datum. Datumet ska följa formatet YYYY-MM-DD och vara ett korrekt datum.
        5. Navigera till boken och verifiera att publiceringsdatumet har uppdaterats*/
        giveInfoAboutAction("test#", "3");
        BrowseBooksPage browseBooksPage = page(BrowseBooksPage.class);
        BookPage bookPage = page(BookPage.class);
        EditBookPage editBookPage = page(EditBookPage.class);
        UserHelper.logInAsUser("admin", "1234567890");
        
        MenuPage menuPage = page(MenuPage.class);
        menuPage.navigateToBrowseBooks();
        browseBooksPage.setTitleField("Good Omens");
        browseBooksPage.clickSearchBookButton();
        browseBooksPage.clickFirstResultTitle();
        sleep(300);
        
        bookPage.clickEditBookButton();
        editBookPage.changeDatePublish("1212-12-12");
        editBookPage.clickSaveBookButton();
        
        menuPage.navigateToBrowseBooks();
        browseBooksPage.setTitleField("Good Omens");
        browseBooksPage.clickSearchBookButton();
        browseBooksPage.clickFirstResultTitle();
        assertEquals("Book's date published should be equal", "1212-12-12", bookPage.getDatePublished());
        giveInfoAboutAction("checking that book's date published is", "1212-12-12");
        
        sleep(300);

     }
     
     @Ignore
     //@Test
     public void borrowBook() {
         /*Låna en bok
        1.Skapa en ny användare
        2.Logga in som din nya användare
        3.Navigera till en bok
        4.Välj "Borrow book" för att låna boken
        5.Välj Yes
        6.Verifiera att det nu finns en bok mindre tillgänglig
        7.Navigera till "My Profile"
        8.Verifiera att den valda boken finns listad
        9.Lämna tillbaka boken*/
        giveInfoAboutAction("test#", "4");
        BrowseBooksPage browseBooksPage = page(BrowseBooksPage.class);
        
        PleaseConfirmDialog pleaseConfirmDialog = page(PleaseConfirmDialog.class);
        MenuPage menuPage = page(MenuPage.class);
        BookPage bookPage = page(BookPage.class);
        String uuid = (UUID.randomUUID().toString()).substring(0,10);
        UserHelper.createNewUser(uuid, uuid, uuid+"@example.com", false);
        UserHelper.logInAsUser(uuid, uuid);
         
        menuPage.navigateToBrowseBooks();
        browseBooksPage.clickSearchBookButton();
        browseBooksPage.clickFirstResultTitle();
        String numberBooksAvailable = bookPage.getNumberOfCopiesAvailable();
        String borrowBooksName = bookPage.getTitle();
        Integer i = convertToInteger(numberBooksAvailable);
        bookPage.clickBorrowBookButton();
        pleaseConfirmDialog.clickYesButton();
        sleep(3000);
        i--;
        String s1=i.toString();
        assertEquals("Numbers of available books should be equal "+s1, s1, bookPage.getNumberOfCopiesAvailable());
        menuPage.navigateToMyProfile();
        Table table = new Table($(".v-grid-tablewrapper"));
        table.searchAndClick(borrowBooksName, 0);
        
        bookPage.clickReturnBookButton();
        pleaseConfirmDialog.clickYesButton();
        sleep(300);
        assertEquals("Numbers of available books should be equal "+numberBooksAvailable, numberBooksAvailable, bookPage.getNumberOfCopiesAvailable());
        giveInfoAboutAction("checking that numbers of available books is", numberBooksAvailable);
     
     }

/********************************************/    

    //@Test
    @Ignore
    public void testUsingTable() {
        page(MenuPage.class).navigateToBrowseBooks();
        BrowseBooksPage browseBooksPage = page(BrowseBooksPage.class);
        //browseBooksPage.setTitleField("G");
        browseBooksPage.clickSearchBookButton();
        Table table = new Table($(".v-grid-tablewrapper"));
        System.out.println(table.getColumnCount());
        System.out.println(table.getRowCount());
        System.out.println(table.getCellValue(0, 0));
        System.out.println(table.getCellValue(1, 1));
        table.searchAndClick("American Gods", 0);
        sleep(300);
    }

}
