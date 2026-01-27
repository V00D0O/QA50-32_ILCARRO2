package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {
    static WebDriver driver;

    public static void setDriver(WebDriver wd) {
        driver = wd;
    }

    public void pause(int time) {
        try {
            Thread.sleep(time * 1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isElementDisplayed(WebElement element){
        return element.isDisplayed();
    }
    public boolean isTextInElementPresentWait(WebElement element, String text){
        return new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.textToBePresentInElement(element, text));
    }
}