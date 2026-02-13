package ui_tests;

import dto.User;
import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HomePage;
import pages.LoginPage;
import pages.PopUpPage;
import utils.RetryAnalyser;

import java.lang.reflect.Method;

import static utils.PropertiesReader.*;

public class LoginTests extends ApplicationManager {
    SoftAssert softAssert = new SoftAssert();

    @Test
    public void loginPositiveTest(Method method){
        User user = User.builder()
                .email(getProperty("base.properties", "email"))
                .password(getProperty("base.properties", "password"))
                .build();
        logger.info("start test " + method.getName() + " with user " + user);
        HomePage homePage = new HomePage(getDriver());
        homePage.clickBtnLogin();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginForm(user);
        loginPage.clickBtnYalla();
        Assert.assertTrue(loginPage.isLoggedInDisplayed());
    }

    @Test(retryAnalyzer = RetryAnalyser.class)
    public void loginPositiveTest_WithPopUpPage(){
        User user = User.builder()
                .email(getProperty("base.properties", "email"))
                .password(getProperty("base.properties", "password"))
                .build();
        HomePage homePage = new HomePage(getDriver());
        homePage.clickBtnLogin();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginForm(user);
        loginPage.clickBtnYalla();
        Assert.assertTrue(new PopUpPage(getDriver())
                .isTextInPopUpMessagePresent("Logged in success"));
    }

    @Test
    public void loginNegativeTest_WrongPassword_WOSpecSymbol(){
        User user = User.builder()
                .email(getProperty("base.properties", "email"))
                .password("BSas1243")
                .build();
        HomePage homePage = new HomePage(getDriver());
        homePage.clickBtnLogin();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginForm(user);
        loginPage.clickBtnYalla();
        Assert.assertTrue(new PopUpPage(getDriver())
                .isTextInPopUpMessagePresent("Login or Password incorrect"));
    }
    @Test
    public void loginNegativeTest_WrongPassword_Empty(){
        User user = User.builder()
                .email(getProperty("base.properties", "email"))
                .password("")
                .build();
        HomePage homePage = new HomePage(getDriver());
        homePage.clickBtnLogin();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginForm(user);
        loginPage.clickBtnYalla();
        softAssert.assertTrue(loginPage.isTextInErrorPresent
                ("It'snot look like email"), "validate field email");
        System.out.println("wrong text!!");
        softAssert.assertTrue(loginPage.isTextInErrorPresent
                ("Password is required"), "validate field password");
        System.out.println("right text!!");
        softAssert.assertAll();
    }
}