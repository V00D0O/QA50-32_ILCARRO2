package ui_tests;

import dto.User;
import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HomePage;
import pages.PopUpPage;
import pages.RegistrationPage;

import java.util.Random;

import static utils.UserFactory.*;

public class RegistrationTests extends ApplicationManager {
    RegistrationPage registrationPage;
    SoftAssert softAssert = new SoftAssert();

    @BeforeMethod
    public void goToRegistrationPage() {
        new HomePage(getDriver()).clickBtnSignUp();
        registrationPage = new RegistrationPage(getDriver());
    }

    @Test
    public void registrationPositiveTest() {
        int i = new Random().nextInt(1000);
        User user = User.builder()
                .firstName("Mar")
                .lastName("Dor")
                .email("sssmkjiu" + i + "@defrt.bhy")
                .password("Marat116!!")
                .build();
        registrationPage.typeRegistrationForm(user);
        registrationPage.clickCheckBoxWithActions();
        registrationPage.clickBtnYalla();
        Assert.assertTrue(new PopUpPage(getDriver())
                .isTextInPopUpMessagePresent("You are logged in success"));
    }

    @Test
    public void registrationPositiveTest_WithFaker() {
        User user = positiveUser();
        registrationPage.typeRegistrationForm(user);
        registrationPage.clickCheckBoxWithActions();
        registrationPage.clickBtnYalla();
        Assert.assertTrue(new PopUpPage(getDriver())
                .isTextInPopUpMessagePresent("You are logged in success"));
    }

    @Test
    public void registrationNegativeTest_UserAlreadyExists() {
        User user = User.builder()
                .firstName("MAR")
                .lastName("DOR")
                .email("car116@gmail.com")
                .password("Car116116!")
                .build();
        registrationPage.typeRegistrationForm(user);
        registrationPage.clickCheckBoxWithActions();
        registrationPage.clickBtnYalla();
        Assert.assertTrue(new PopUpPage(getDriver())
                .isTextInPopUpMessagePresent("User already exists"));
    }

    @Test
    public void registrationNegativeTest_WithSpaceInFirstName() {
        User user = User.builder()
                .firstName(" ")
                .lastName("Dor")
                .email("marat11@mail.com")
                .password("Marat116!")
                .build();
        registrationPage.typeRegistrationForm(user);
        registrationPage.clickCheckBoxWithActions();
        registrationPage.clickBtnYalla();
        Assert.assertTrue(new PopUpPage(getDriver())
                .isTextInPopUpMessagePresent("не должно быть пустым"));
    }

    @Test
    public void registrationNegativeTest_WithAllEmptyFields() {
        User user = User.builder()
                .firstName("")
                .lastName("")
                .email("")
                .password("")
                .build();
        registrationPage.clickCheckBoxWithActions();
        registrationPage.typeRegistrationForm(user);
        registrationPage.clickBtnYalla();
        softAssert.assertTrue(registrationPage
                        .isTextInErrorPresent("Name is required"),
                "validate error message Name is required");
        softAssert.assertTrue(registrationPage
                        .isTextInErrorPresent("Last name is required"),
                "validate error message Last name is required");
        softAssert.assertTrue(registrationPage
                        .isTextInErrorPresent("Email is required"),
                "validate error message Email is required");
        softAssert.assertTrue(registrationPage
                        .isTextInErrorPresent("Password is required"),
                "validate error message Password is required");
        softAssert.assertAll();
    }
}