/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nackademin.librarytest.helpers;

import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.Selenide.sleep;

import se.nackademin.librarytest.pages.AddUserPage;
import se.nackademin.librarytest.pages.EditUserPage;
import se.nackademin.librarytest.pages.MenuPage;
import se.nackademin.librarytest.pages.SignInPage;
import static com.codeborne.selenide.Selenide.page;

/**
 * @author testautomatisering
 */
public class UserHelper {
    public static void createNewUser(/*String usersRole,*/ String username, String password, String email, boolean usersRole) {
        MenuPage menuPage = page(MenuPage.class);
        menuPage.navigateToAddUser();
        sleep(300);
        AddUserPage addUserPage = page(AddUserPage.class);
        addUserPage.setUsername(username);
        addUserPage.setPassword(password);
        addUserPage.setEmail(email);
        
        ////////////////////////////////////////////
        if (usersRole == true) {
            sleep(300);
            addUserPage.clickRudioButtonLibrarian();
            sleep(300);            
        }
        
        ////////////////////////////////////////////
        
        addUserPage.clickAddUserButton();
    }

    public static void logInAsUser(String username, String password) {
        MenuPage menuPage = page(MenuPage.class);
        menuPage.navigateToSignIn();
        SignInPage signInPage = page(SignInPage.class);
        signInPage.setUsername(username);
        signInPage.setPassword(password);
        sleep(300);
        signInPage.clickLogIn();
    }

    public static void logOutUser() {
        MenuPage menuPage = page(MenuPage.class);
        menuPage.navigateToSignOut();
    }
    
    public static void changeUsersEmail(String email) {
        EditUserPage editUserPage = page(EditUserPage.class);
        editUserPage.changeUsersEmail(email);
        editUserPage.clickSaveUserButton();            
    }

    public static String getMessageAboutReasonAnableToAddUser() {
        AddUserPage addUserPage = page(AddUserPage.class);
        return addUserPage.getMessageAboutOperationsResult();
    }    
    
    public static String getMessageAboutReasonAnableToLoggIn() {
        SignInPage signInPage = page(SignInPage.class);
        return signInPage.getinfoAboutReasonForLogginProblem();
    }    

}
