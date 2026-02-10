package pages;

import dto.User;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class RegistrationPage extends BasePage {
    public RegistrationPage(WebDriver driver) {
        setDriver(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory
                (driver, 10), this);
    }

    @FindBy(id = "name")
    WebElement fieldFirstName;
    @FindBy(id = "lastName")
    WebElement fieldLastName;
    @FindBy(id = "email")
    WebElement fieldEmail;
    @FindBy(id = "password")
    WebElement fieldPassword;
    @FindBy(xpath = "//label[@for='terms-of-use']")
    WebElement checkBoxAgree;
    @FindBy(css = "button[type='submit']")
    WebElement btnYalla;


    public void typeRegistrationForm(User user) {
        fieldFirstName.sendKeys(user.getFirstName());
        fieldLastName.sendKeys(user.getLastName());
        fieldEmail.sendKeys(user.getEmail());
        fieldPassword.sendKeys(user.getPassword());
        pause(1);
    }

    public void clickBtnYalla(){
        btnYalla.click();
    }

    public void clickCheckBox() {
        checkBoxAgree.click();
    }

    public void setCheckBoxAgree(boolean value) {
        if (checkBoxAgree.isSelected() != value)
            ((JavascriptExecutor) driver)
                    .executeScript("arguments[0].click();",
                            checkBoxAgree);
    }

    public void setCheckBoxAgreeTermsOfUse() {
        if (!checkBoxAgree.isSelected()) {
            ((JavascriptExecutor) driver)
                    .executeScript("arguments[0].click();",
                            checkBoxAgree);
        }
    }

    public void clickCheckBoxWithActions() {
        int y = checkBoxAgree.getSize().getHeight();
        int x = checkBoxAgree.getSize().getWidth();
        System.out.println(x + " x " + y);

        Actions actions = new Actions(driver);
        actions.moveToElement(checkBoxAgree, -(x/10*4), -y/2).click().perform();
    }

}