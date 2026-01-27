package ui_tests;

import dto.User;
import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.PopUpPage;

public class LoginTests extends ApplicationManager {
    @Test
    public void loginPositiveTest(){
        User user = User.builder()
                .email("sima_simonova370@gmail.com")
                .password("BSas124!")
                .build();
        HomePage homePage = new HomePage(getDriver());
        homePage.clickBtnLogin();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginForm(user);
        loginPage.clickBtnYalla();
        Assert.assertTrue(loginPage.isLoggedInDisplayed());
    }
    @Test
    public void loginPositiveTest_WithPopUpPage(){
        User user = User.builder()
                .email("sima_simonova370@gmail.com")
                .password("BSas124!")
                .build();
        HomePage homePage = new HomePage(getDriver());
        homePage.clickBtnLogin();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginForm(user);
        loginPage.clickBtnYalla();
        Assert.assertTrue(new PopUpPage(getDriver()).isTextInPopUpMessagePresent("Logged in success"));
    }
}