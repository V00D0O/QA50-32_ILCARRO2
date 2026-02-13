package manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.LocalDate;
import java.time.LocalTime;

public class ApplicationManager {
    public final static Logger logger =
            LoggerFactory.getLogger(ApplicationManager.class);
    private WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    @BeforeMethod
    public void setup() {
        logger.info("Start testing " + LocalDate.now() +
                " : " + LocalTime.now());
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterMethod(enabled = false)
    public void tearDown() {
        logger.info("Stop testing " + LocalDate.now() +
                " : " + LocalTime.now());
        if (driver != null)
            driver.quit();
    }
}