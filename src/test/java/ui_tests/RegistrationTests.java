package ui_tests;

import dto.User;
import manager.ApplicationManager;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.RegistrationPage;

import java.util.Random;


public class RegistrationTests extends ApplicationManager {
    RegistrationPage registrationPage;

    @BeforeMethod
    public void goToRegistrationPage() {
        new HomePage(getDriver()).clickBtnSignUp();
        registrationPage = new RegistrationPage(getDriver());
    }

    @Test
    public void registrationPositiveTest() {
        int i = new Random().nextInt(1000);
        User user = User.builder()
                .firstName("NNU")
                .lastName("SSS")
                .email("asdsc"+i+"@mail.ru")
                .password("Pqwerty45r3!")
                .build();
        registrationPage.typeRegistrationForm(user);
        registrationPage.clickCheckBox();
    }
}