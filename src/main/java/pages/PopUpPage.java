package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class PopUpPage extends BasePage {
     public PopUpPage(WebDriver driver) {
        setDriver(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory
                (driver, 10), this);
    }

    @FindBy(xpath = "//mat-dialog-container//h2")
    WebElement message;
    public  boolean isTextInPopUpMessagePresent(String text){
        return isTextInElementPresentWait(message, text);
    }

}
